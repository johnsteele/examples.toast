/*******************************************************************************
 * Copyright (c) 2009 Paul VanderLei, Simon Archer, Jeff McAffer and others. All
 * rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 and Eclipse
 * Distribution License v1.0 which accompanies this distribution. The Eclipse
 * Public License is available at http://www.eclipse.org/legal/epl-v10.html and
 * the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 * 
 * Contributors: Paul VanderLei, Simon Archer, Jeff McAffer - initial API and
 * implementation
 *******************************************************************************/
package org.eclipse.examples.toast.crust.widgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.Region;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.TypedListener;

public class ImageButton extends Canvas implements Listener {

	private Image downImage;
	private Image upImage;
	private Image image;
	private Image depressedImage;
	private String text;
	private boolean selected;
	private boolean tempSelected;

	public ImageButton(Composite parent, int style) {
		super(parent, style);
		addListener(SWT.Paint, this);
		addListener(SWT.Dispose, this);
		addListener(SWT.MouseDown, this);
		addListener(SWT.MouseUp, this);
	}

	public void setImages(Image upImage, Image downImage) {
		setImages(upImage, downImage, null);
	}

	public void setImages(Image upImage, Image downImage, Image depressedImage) {
		this.upImage = upImage;
		this.downImage = downImage;
		this.depressedImage = depressedImage != null ? depressedImage : downImage;
		setImage(this.upImage);
	}

	public void setRegionFromImage() {
		Region region = getRegion();
		if (region != null)
			region.dispose();
		region = new Region();
		ImageData imageData = image.getImageData();
		if (imageData.alphaData != null) {
			Rectangle pixel = new Rectangle(0, 0, 1, 1);
			for (int y = 0; y < imageData.height; y++) {
				for (int x = 0; x < imageData.width; x++)
					if (imageData.getAlpha(x, y) == 255) {
						pixel.x = imageData.x + x;
						pixel.y = imageData.y + y;
						region.add(pixel);
					}

			}

		} else {
			ImageData mask = imageData.getTransparencyMask();
			Rectangle pixel = new Rectangle(0, 0, 1, 1);
			for (int y = 0; y < mask.height; y++) {
				for (int x = 0; x < mask.width; x++)
					if (mask.getPixel(x, y) != 0) {
						pixel.x = imageData.x + x;
						pixel.y = imageData.y + y;
						region.add(pixel);
					}

			}

		}
		setRegion(region);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
		redraw();
	}

	public Point computeSize(int wHint, int hHint, boolean changed) {
		int width = 0;
		int height = 0;
		if (image != null) {
			Rectangle bounds = image.getBounds();
			width = bounds.width;
			height = bounds.height;
		}
		if (text != null) {
			GC gc = new GC(this);
			Point extent = gc.stringExtent(text);
			gc.dispose();
			width = Math.max(width, extent.x);
			height = Math.max(height, extent.y);
		}
		if (wHint != -1)
			width = wHint;
		if (hHint != -1)
			height = hHint;
		return new Point(width, height);
	}

	public void addSelectionListener(SelectionListener listener) {
		checkWidget();
		if (listener == null)
			SWT.error(SWT.ERROR_NULL_ARGUMENT);
		TypedListener typedListener = new TypedListener(listener);
		addListener(SWT.Selection, typedListener);
		addListener(SWT.DefaultSelection, typedListener);
	}

	public void removeSelectionListener(SelectionListener listener) {
		checkWidget();
		if (listener == null)
			SWT.error(SWT.ERROR_NULL_ARGUMENT);
		removeListener(SWT.Selection, listener);
		removeListener(SWT.DefaultSelection, listener);
	}

	public void setSelection(boolean selected) {
		this.selected = selected;
		setImage(selected ? downImage : upImage);
	}

	public boolean getSelection() {
		return selected;
	}

	private void paintControl(Event e) {
		GC gc = e.gc;
		Rectangle size = getBounds();
		if (image != null) {
			Rectangle iSize = image.getBounds();
			int widthOffset = computeOffset(size.width, iSize.width);
			int heightOffset = computeOffset(size.height, iSize.height);
			gc.drawImage(image, widthOffset, heightOffset);
		}
		if (text != null) {
			Point tSize = gc.textExtent(text);
			int widthOffset = computeOffset(size.width, tSize.x);
			int heightOffset = computeOffset(size.height, tSize.y);
			gc.drawString(text, widthOffset, heightOffset, true);
		}
	}

	private int computeOffset(int i, int j) {
		if (j >= i)
			return 0;
		else
			return (i - j) / 2;
	}

	private void widgetDisposed(Event e) {
		Region region = getRegion();
		if (region != null)
			region.dispose();
	}

	private void mouseUp(Event e) {
		if (!tempSelected)
			return;
		tempSelected = false;
		if ((getStyle() & SWT.TOGGLE) != 0) {
			if (selected) {
				selected = false;
				setImage(upImage);
			} else {
				selected = true;
				setImage(downImage);
			}
		} else {
			setImage(selected ? downImage : upImage);
			if ((getStyle() & SWT.RADIO) != 0 && (getParent().getStyle() & SWT.NO_RADIO_GROUP) == 0)
				selectRadio();
		}
		notifyListeners(SWT.Selection, new Event());
	}

	private void mouseDown(Event e) {
		Region r = getRegion();
		if (r == null || r.contains(e.x, e.y))
			pickImage();
	}

	private void pickImage() {
		if ((getStyle() & SWT.TOGGLE | SWT.CHECK) != 0)
			setImage(depressedImage);
		else
			setImage(selected ? downImage : depressedImage);
		tempSelected = true;
	}

	private void setImage(Image image) {
		this.image = image;
		redraw();
	}

	public Image getImage() {
		return image;
	}

	public Image[] getImages() {
		return (new Image[] {upImage, downImage, depressedImage});
	}

	private void selectRadio() {
		org.eclipse.swt.widgets.Control children[] = getParent().getChildren();
		for (int i = 0; i < children.length; i++)
			if (children[i] instanceof ImageButton) {
				ImageButton child = (ImageButton) children[i];
				if ((this != child) & ((child.getStyle() & SWT.RADIO) != 0))
					child.setRadioSelection(false);
			}

		if ((getStyle() & SWT.PUSH) == 0)
			setSelection(true);
	}

	private void setRadioSelection(boolean value) {
		if ((getStyle() & SWT.RADIO) == 0)
			return;
		if (getSelection() != value) {
			setSelection(value);
			notifyListeners(SWT.Selection, new Event());
		}
	}

	public void handleEvent(Event event) {
		switch (event.type) {
			case SWT.Paint :
				paintControl(event);
				break;

			case SWT.MouseUp :
				mouseUp(event);
				break;

			case SWT.MouseDown :
				mouseDown(event);
				break;

			case SWT.Dispose :
				widgetDisposed(event);
				break;
		}
	}
}
