/*******************************************************************************
 * Copyright (c) 2009 Paul VanderLei, Simon Archer, Jeff McAffer and others. All 
 * rights reserved. This program and the accompanying materials are made available 
 * under the terms of the Eclipse Public License v1.0 and Eclipse Distribution License
 * v1.0 which accompanies this distribution. The Eclipse Public License is available at 
 * http://www.eclipse.org/legal/epl-v10.html and the Eclipse Distribution License 
 * is available at http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors: 
 *     Paul VanderLei, Simon Archer, Jeff McAffer - initial API and implementation
 *******************************************************************************/
package org.eclipse.examples.toast.crust.shell;

import org.eclipse.examples.toast.crust.widgets.ImageButton;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;

public class ScreenManager implements SelectionListener {
	private int slots;
	private Point screenSize;
	private Composite parentComposite;
	private ImageButton[] buttons;
	private Composite[] composites;
	private ICrustScreenListener[] screenListeners;
	private int activeSlot;
	private boolean[] isSlotOccupied;

	public ScreenManager(Composite parentComposite, ImageButton[] buttons) {
		this.parentComposite = parentComposite;
		this.buttons = buttons;
		slots = buttons.length;
		screenSize = parentComposite.getSize();
		composites = new Composite[slots];
		screenListeners = new ICrustScreenListener[slots];
		isSlotOccupied = new boolean[slots];
		activeSlot = -1; // no slot active
	}

	public Composite installScreen(final int slot, final Class clazz, final String upImage, final String downImage, final String depressedImage, final ICrustScreenListener screenListener) {
		if (slot >= slots) {
			throw new RuntimeException("Attempted to install panel at slot " + slot + " (max is " + slots + ")");
		}
		if (isSlotOccupied[slot]) {
			throw new RuntimeException("Attempted to install panel at slot " + slot + " (already occupied)");
		}
		isSlotOccupied[slot] = true;
		new DisplayBlock() {
			public void run() {
				Composite composite = new Composite(parentComposite, SWT.NONE);
				composite.setSize(screenSize);
				composite.setVisible(false);
				composites[slot] = composite;
				ImageButton button = buttons[slot];
				ScaledWidgetFactory f = new ScaledWidgetFactory(clazz);
				button.setImages(f.getSizedImageForWidget(upImage, button), f.getSizedImageForWidget(downImage, button), f.getSizedImageForWidget(depressedImage, button));
				button.setVisible(true);
				button.addSelectionListener(ScreenManager.this);
				screenListeners[slot] = screenListener;
				// shell.layout();
			}
		}.sync();
		return composites[slot];
	}

	public void uninstallScreen(final int slot, ICrustScreenListener screenListener) {
		verifySlotOwner(slot, screenListener);
		new DisplayBlock() {
			public void run() {
				ImageButton button = buttons[slot];
				button.removeSelectionListener(ScreenManager.this);
				button.setVisible(false);
				Image[] images = button.getImages();
				button.setImages(null, null, null);
				for (int i = 0; i < images.length; i++) {
					if (images[i] != null)
						images[i].dispose();
				}
				composites[slot].dispose();
				composites[slot] = null;
			}
		}.sync();
		screenListeners[slot] = null;
		isSlotOccupied[slot] = false;
		if (slot == activeSlot)
			activeSlot = -1;
	}

	public void deactivateScreen(final int slot, ICrustScreenListener screenListener) {
		verifySlotOwner(slot, screenListener);
		if (slot != activeSlot)
			return;
		new DisplayBlock() {
			public void run() {
				deactivateSlot();
				activeSlot = -1;
			}
		}.sync();
	}

	public void widgetDefaultSelected(SelectionEvent e) {
		// Do Nothing
	}

	public void widgetSelected(SelectionEvent e) {
		for (int i = 0; i < slots; i++) {
			if (buttons[i] == e.widget) {
				if (activeSlot != i) {
					deactivateSlot();
					activeSlot = i;
					activateSlot();
				}
				break;
			}
		}
	}

	// Private
	private void verifySlotOwner(final int slot, ICrustScreenListener screenListener) {
		if (slot >= slots) {
			throw new RuntimeException("Attempted to uninstall panel at slot " + slot + " (max is " + slots + ")");
		}
		if (screenListeners[slot] != screenListener) {
			throw new RuntimeException("Attempted to uninstall incorrect panel at slot " + slot);
		}
	}

	private void activateSlot() {
		composites[activeSlot].setVisible(true);
		buttons[activeSlot].setSelection(true);
		screenListeners[activeSlot].activate();
	}

	private void deactivateSlot() {
		if (activeSlot == -1)
			return;
		composites[activeSlot].setVisible(false);
		screenListeners[activeSlot].deactivate();
		buttons[activeSlot].setSelection(false);
	}
}
