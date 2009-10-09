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
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.Region;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

public class ImageSlider extends Canvas {

	private Image image;
	private Image sliderImage;
	private int selection;
	private int privateStyle;
	private int minimum;
	private int maximum;

	public ImageSlider(Composite parent, int style) {
		super(parent, checkStyle(style));
		privateStyle = checkPrivateStyle(style);
		addDisposeListener(new DisposeListener() {

			public void widgetDisposed(DisposeEvent e) {
				ImageSlider.this.widgetDisposed(e);
			}

		});
		addPaintListener(new PaintListener() {

			public void paintControl(PaintEvent e) {
				ImageSlider.this.paintControl(e);
			}

		});
		selection = 0;
		minimum = 0;
		maximum = 100;
	}

	public void setSelection(int selection) {
		if (selection < minimum)
			this.selection = minimum;
		else if (selection > maximum)
			this.selection = maximum;
		else
			this.selection = selection;
		redraw();
	}

	public int getSelection() {
		return selection;
	}

	private void paintControl(PaintEvent e) {
		GC gc = e.gc;
		Rectangle size = getBounds();
		if (image != null) {
			Rectangle iSize = image.getBounds();
			int widthOffset = computeOffset(size.width, iSize.width);
			int heightOffset = computeOffset(size.height, iSize.height);
			gc.drawImage(image, widthOffset, heightOffset);
		}
		if (sliderImage != null) {
			Rectangle sSize = sliderImage.getBounds();
			int percent = ((selection - minimum) * 100) / maximum;
			int style = privateStyle;
			int widthOffset;
			int heightOffset;
			if ((style & SWT.HORIZONTAL) != 0) {
				heightOffset = computeOffset(size.height, sSize.height);
				if ((style & SWT.RIGHT_TO_LEFT) != 0)
					percent = 100 - percent;
				widthOffset = (size.width * percent) / 100 - sSize.width / 2;
			} else {
				widthOffset = computeOffset(size.width, sSize.width);
				if ((style & SWT.UP) != 0)
					percent = 100 - percent;
				heightOffset = (size.height * percent) / 100 - sSize.height / 2;
			}
			gc.drawImage(sliderImage, widthOffset, heightOffset);
		}
	}

	private int computeOffset(int i, int j) {
		if (j >= i)
			return 0;
		else
			return (i - j) / 2;
	}

	private void widgetDisposed(DisposeEvent e) {
		Region region = getRegion();
		if (region != null)
			region.dispose();
	}

	public void setImages(Image image, Image sliderImage) {
		this.image = image;
		this.sliderImage = sliderImage;
		redraw();
	}

	public Image[] getImages() {
		return (new Image[] {image, sliderImage});
	}

	public int getMinimum() {
		return minimum;
	}

	public void setMinimum(int minimum) {
		if (minimum < 0) {
			return;
		} else {
			this.minimum = minimum;
			redraw();
			return;
		}
	}

	public int getMaximum() {
		return maximum;
	}

	public void setMaximum(int maximum) {
		if ((maximum < 0) | (maximum < minimum)) {
			return;
		} else {
			this.maximum = maximum;
			redraw();
			return;
		}
	}

	private static int checkStyle(int style) {
		// SWT.HORIZONTAL and SWT.VERTICAL cause scroll bars which we don't want
		// so eliminate them
		style |= SWT.NO_FOCUS;
		int mask = SWT.HORIZONTAL | SWT.VERTICAL | SWT.LEFT_TO_RIGHT | SWT.RIGHT_TO_LEFT | SWT.UP | SWT.DOWN;
		return style & ~mask;
	}

	private int checkBits(int style, int int0, int int1) {
		int mask = int0 | int1;
		if ((style & mask) == 0)
			style |= int0;
		if ((style & int0) != 0)
			style = style & ~mask | int0;
		if ((style & int1) != 0)
			style = style & ~mask | int1;
		return style;
	}

	private int checkPrivateStyle(int style) {
		style = checkBits(style, SWT.HORIZONTAL, SWT.VERTICAL);
		if ((style & SWT.HORIZONTAL) != 0) {
			return checkBits(style, SWT.LEFT_TO_RIGHT, SWT.RIGHT_TO_LEFT);
		}
		if ((style & SWT.VERTICAL) != 0) {
			return checkBits(style, SWT.UP, SWT.DOWN);
		}
		return style;
	}

}
