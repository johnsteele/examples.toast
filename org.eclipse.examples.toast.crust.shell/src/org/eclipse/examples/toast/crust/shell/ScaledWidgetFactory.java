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

import java.io.InputStream;
import org.eclipse.examples.toast.core.LogUtility;
import org.eclipse.examples.toast.crust.widgets.ImageButton;
import org.eclipse.examples.toast.crust.widgets.ImageProgressBar;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.Region;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;

public class ScaledWidgetFactory {
	private Class clazz;
	private int screenWidth;
	private int screenHeight;
	private int referenceHeight;
	private int referenceWidth;

	public ScaledWidgetFactory(Class clazz, Point size, int referenceHeight, int referenceWidth) {
		this.clazz = clazz;
		this.screenWidth = size.x;
		this.screenHeight = size.y;
		this.referenceHeight = referenceHeight;
		this.referenceWidth = referenceWidth;
	}

	public ScaledWidgetFactory(Class clazz) {
		this.clazz = clazz;
	}

	public Image getSizedImageForWidget(String filename, Control control) {
		if (filename == null)
			return null;
		InputStream stream = clazz.getResourceAsStream(filename);
		if (stream == null)
			throw new RuntimeException(filename + " not found");
		Point size = control.getSize();
		int width = size.x;
		int height = size.y;
		ImageData data = new ImageData(stream);
		if (width != 0 && height != 0 && data.width != width || data.height != height) {
			LogUtility.logDebug("ScaledWidgetFactory", "scaling image for " + filename);
			data = data.scaledTo(width, height);
		}
		return new Image(Display.getCurrent(), data);
	}

	public Image getSizedImage(String filename, int width, int height) {
		if (filename == null)
			return null;
		InputStream stream = clazz.getResourceAsStream(filename);
		if (stream == null)
			throw new RuntimeException(filename + " not found");
		ImageData data = new ImageData(stream);
		if (width != 0 && height != 0 && data.width != width || data.height != height) {
			LogUtility.logDebug("ScaledWidgetFactory", "scaling image for " + filename);
			data = data.scaledTo(width, height);
		}
		return new Image(Display.getCurrent(), data);
	}

	public ImageButton createImageButton(Composite parent, int flags, int x, int y, int width, int height) {
		return createImageButton(parent, flags, x, y, width, height, null, null, null, false, null, null);
	}

	public ImageButton createImageButton(Composite parent, int flags, int x, int y, int width, int height, String imageUpFileName, String imageDownFileName, boolean setRegion, SelectionListener listener) {
		return createImageButton(parent, flags, x, y, width, height, imageUpFileName, imageDownFileName, null, setRegion, listener, null);
	}

	public ImageButton createImageButton(Composite parent, int flags, int x, int y, int width, int height, String imageUpFileName, String imageDownFileName, boolean setRegion, SelectionListener listener, Font font) {
		return createImageButton(parent, flags, x, y, width, height, imageUpFileName, imageDownFileName, null, setRegion, listener, font);
	}

	public ImageButton createImageButton(Composite parent, int flags, int x, int y, int width, int height, String imageUpFileName, String imageDownFileName, String extraImageFileName, boolean setRegion, SelectionListener listener) {
		return createImageButton(parent, flags, x, y, width, height, imageUpFileName, imageDownFileName, extraImageFileName, setRegion, listener, null);
	}

	public ImageButton createImageButton(Composite parent, int flags, int x, int y, int width, int height, String imageUpFileName, String imageDownFileName, String extraImageFileName, boolean setRegion, SelectionListener listener, Font font) {
		ImageButton imageButton = new ImageButton(parent, flags);
		imageButton.setBounds(getScaledBounds(x, y, width, height));
		imageButton.setImages(getSizedImageForWidget(imageUpFileName, imageButton), getSizedImageForWidget(imageDownFileName, imageButton), getSizedImageForWidget(extraImageFileName, imageButton));
		if (setRegion)
			imageButton.setRegionFromImage();
		if (font != null)
			imageButton.setFont(font);
		if (listener != null)
			imageButton.addSelectionListener(listener);
		return imageButton;
	}

	public void disposeImageButtonImages(ImageButton imageButton) {
		disposeRegion(imageButton);
		Image[] images = imageButton.getImages();
		imageButton.setImages(null, null, null);
		for (int i = 0; i < images.length; i++) {
			if (images[i] != null)
				images[i].dispose();
		}
	}

	public ImageProgressBar createImageProgressBar(Composite parent, int flags, int x, int y, int width, int height, String imageFileName) {
		ImageProgressBar slider = new ImageProgressBar(parent, flags);
		slider.setBounds(getScaledBounds(x, y, width, height));
		slider.setImage(getSizedImageForWidget(imageFileName, slider));
		return slider;
	}

	public void disposeImageProgressBarImage(ImageProgressBar imageProgressBar) {
		disposeRegion(imageProgressBar);
		Image image = imageProgressBar.getImage();
		if (image != null) {
			imageProgressBar.setImage(null);
			image.dispose();
		}
	}

	public Label createLabel(Composite parent, int flags, int x, int y, int width, int height, Font font) {
		return createLabel(parent, flags, x, y, width, height, font, null);
	}

	public Label createLabel(Composite parent, int flags, int x, int y, int width, int height, Font font, String text) {
		Label label = new Label(parent, flags);
		if (font != null)
			label.setFont(font);
		if (text != null)
			label.setText(text);
		label.setBounds(getScaledBounds(x, y, width, height));
		return label;
	}

	public void disposeLabelImage(Label label) {
		disposeRegion(label);
		Image image = label.getImage();
		if (image != null) {
			label.setImage(null);
			image.dispose();
		}
	}

	public Composite createComposite(Composite parent, int flags, int x, int y, int width, int height) {
		return createComposite(parent, flags, x, y, width, height, null, null);
	}

	public Composite createComposite(Composite parent, int flags, int x, int y, int width, int height, String imagefileName, Layout layout) {
		Composite composite = new Composite(parent, flags);
		composite.setBounds(getScaledBounds(x, y, width, height));
		if (imagefileName != null) {
			composite.setBackgroundImage(getSizedImageForWidget(imagefileName, composite));
			composite.setBackgroundMode(SWT.INHERIT_FORCE);
		}
		if (layout != null)
			composite.setLayout(layout);
		return composite;
	}

	public void disposeCompositeImage(Composite composite) {
		disposeRegion(composite);
		// background Images are special if there is no background on a given
		// composite
		// it appears that its parents background image will be given, that
		// image CANNOT
		// be disposed so we have to check for that case
		Image image = composite.getBackgroundImage();
		Image parentImage = composite.getParent().getBackgroundImage();
		if (image != null && parentImage != image) {
			composite.setBackgroundImage(null);
			image.dispose();
		}
	}

	public Button createButton(Composite parent, int flags, int x, int y, int width, int height, String imageFileName, SelectionListener listener, Font font, String text) {
		Button button = new Button(parent, flags);
		button.setBounds(getScaledBounds(x, y, width, height));
		if (imageFileName != null) {
			button.setImage(getSizedImageForWidget(imageFileName, button));
		}
		if (font != null)
			button.setFont(font);
		if (listener != null)
			button.addSelectionListener(listener);
		if (text != null)
			button.setText(text);
		return button;
	}

	public void disposeButtonImage(Button button) {
		disposeRegion(button);
		Image image = button.getImage();
		if (image != null) {
			button.setImage(null);
			image.dispose();
		}
	}

	public Region getRegion(Image image) {
		Region region = new Region();
		final ImageData imageData = image.getImageData();
		if (imageData.alphaData != null) {
			Rectangle pixel = new Rectangle(0, 0, 1, 1);
			for (int y = 0; y < imageData.height; y++) {
				for (int x = 0; x < imageData.width; x++) {
					if (imageData.getAlpha(x, y) == 255) {
						pixel.x = imageData.x + x;
						pixel.y = imageData.y + y;
						region.add(pixel);
					}
				}
			}
		} else {
			ImageData mask = imageData.getTransparencyMask();
			Rectangle pixel = new Rectangle(0, 0, 1, 1);
			for (int y = 0; y < mask.height; y++) {
				for (int x = 0; x < mask.width; x++) {
					if (mask.getPixel(x, y) != 0) {
						pixel.x = imageData.x + x;
						pixel.y = imageData.y + y;
						region.add(pixel);
					}
				}
			}
		}
		return region;
	}

	public Rectangle getScaledBounds(int x, int y, int width, int height) {
		if (referenceWidth == screenWidth && referenceHeight == screenHeight) {
			return new Rectangle(x, y, width, height);
		}
		return new Rectangle(x(x), y(y), x(width), y(height));
	}

	private int x(int xToScale) {
		return xToScale * screenWidth / referenceWidth;
	}

	private int y(int yToScale) {
		return yToScale * screenHeight / referenceHeight;
	}

	private void disposeRegion(Control control) {
		Region region = control.getRegion();
		if (region != null) {
			control.setRegion(null);
			region.dispose();
		}
	}
}
