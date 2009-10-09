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
package org.eclipse.examples.toast.internal.swt.climate;

import org.eclipse.examples.toast.core.LogUtility;
import org.eclipse.examples.toast.crust.shell.DisplayBlock;
import org.eclipse.examples.toast.crust.shell.ICrustScreenListener;
import org.eclipse.examples.toast.crust.shell.ICrustShell;
import org.eclipse.examples.toast.crust.shell.ScaledWidgetFactory;
import org.eclipse.examples.toast.crust.widgets.ImageButton;
import org.eclipse.examples.toast.crust.widgets.ImageProgressBar;
import org.eclipse.examples.toast.dev.climate.IClimateControl;
import org.eclipse.examples.toast.dev.climate.IClimateControlListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class ClimateControlScreen implements ICrustScreenListener, SelectionListener, IClimateControlListener {
	private static final int SLOT = 6;
	private static final String TOPBAR_ICON_ON_IMAGE = "artwork/SnowOn.png"; //$NON-NLS-1$
	private static final String TOPBAR_ICON_OFF_IMAGE = "artwork/SnowOff.png"; //$NON-NLS-1$
	private static final int NUM_FLOW_BUTTONS = 5;
	private static final int[] FAN_BAR_WIDTHS = {0, 25, 50, 75, 100};
	private static final String CLIMATE_BACKGROUND = "artwork/ClimatePanel.png"; //$NON-NLS-1$
	private static final String AC_ON_IMAGE = "artwork/ACDownOn.png"; //$NON-NLS-1$
	private static final String AC_OFF_IMAGE = "artwork/ACUpOff.png"; //$NON-NLS-1$
	private static final String AC_DEPRESSED_IMAGE = "artwork/ACDownOff.png"; //$NON-NLS-1$
	private static final String DEFOG_ON_IMAGE = "artwork/DefogDownOn.png"; //$NON-NLS-1$
	private static final String DEFOG_OFF_IMAGE = "artwork/DefogUpOff.png"; //$NON-NLS-1$
	private static final String DEFOG_DEPRESSED_IMAGE = "artwork/DefogDownOff.png"; //$NON-NLS-1$
	private static final String LOWER_DEF_ON_IMAGE = "artwork/LowerDefDownOn.png"; //$NON-NLS-1$
	private static final String LOWER_DEF_OFF_IMAGE = "artwork/LowerDefUpOff.png"; //$NON-NLS-1$
	private static final String LOWER_DEF_DEPRESSED_IMAGE = "artwork/LowerDefDownOff.png"; //$NON-NLS-1$
	private static final String LOWER_ON_IMAGE = "artwork/LowerDownOn.png"; //$NON-NLS-1$
	private static final String LOWER_OFF_IMAGE = "artwork/LowerUpOff.png"; //$NON-NLS-1$
	private static final String LOWER_DEPRESSED_IMAGE = "artwork/LowerDownOff.png"; //$NON-NLS-1$
	private static final String R_DEF_ON_IMAGE = "artwork/RDefDownOn.png"; //$NON-NLS-1$
	private static final String R_DEF_OFF_IMAGE = "artwork/RDefUpOff.png"; //$NON-NLS-1$
	private static final String R_DEF_DEPRESSED_IMAGE = "artwork/RDefDownOff.png"; //$NON-NLS-1$
	private static final String UPPER_ON_IMAGE = "artwork/UpperDownOn.png"; //$NON-NLS-1$
	private static final String UPPER_OFF_IMAGE = "artwork/UpperUpOff.png"; //$NON-NLS-1$
	private static final String UPPER_DEPRESSED_IMAGE = "artwork/UpperDownOff.png"; //$NON-NLS-1$
	private static final String UPPER_LOWER_ON_IMAGE = "artwork/UpperLowerDownOn.png"; //$NON-NLS-1$
	private static final String UPPER_LOWER_OFF_IMAGE = "artwork/UpperLowerUpOff.png"; //$NON-NLS-1$
	private static final String UPPER_LOWER_DEPRESSED_IMAGE = "artwork/UpperLowerDownOff.png"; //$NON-NLS-1$
	private static final String LEFT_TEMP_INC_UP = "artwork/LeftTempIncUp.png"; //$NON-NLS-1$
	private static final String LEFT_TEMP_INC_DOWN = "artwork/LeftTempIncDown.png"; //$NON-NLS-1$
	private static final String RIGHT_TEMP_INC_UP = "artwork/RightTempIncUp.png"; //$NON-NLS-1$
	private static final String RIGHT_TEMP_INC_DOWN = "artwork/RightTempIncDown.png"; //$NON-NLS-1$
	private static final String LEFT_TEMP_DEC_UP = "artwork/LeftTempDecUp.png"; //$NON-NLS-1$
	private static final String LEFT_TEMP_DEC_DOWN = "artwork/LeftTempDecDown.png"; //$NON-NLS-1$
	private static final String RIGHT_TEMP_DEC_UP = "artwork/RightTempDecUp.png"; //$NON-NLS-1$
	private static final String RIGHT_TEMP_DEC_DOWN = "artwork/RightTempDecDown.png"; //$NON-NLS-1$
	private static final String LEFT_FAN_INC_UP = "artwork/LeftFanIncUp.png"; //$NON-NLS-1$
	private static final String LEFT_FAN_INC_DOWN = "artwork/LeftFanIncDown.png"; //$NON-NLS-1$
	private static final String RIGHT_FAN_INC_UP = "artwork/RightFanIncUp.png"; //$NON-NLS-1$
	private static final String RIGHT_FAN_INC_DOWN = "artwork/RightFanIncDown.png"; //$NON-NLS-1$
	private static final String LEFT_FAN_DEC_UP = "artwork/LeftFanDecUp.png"; //$NON-NLS-1$
	private static final String LEFT_FAN_DEC_DOWN = "artwork/LeftFanDecDown.png"; //$NON-NLS-1$
	private static final String RIGHT_FAN_DEC_UP = "artwork/RightFanDecUp.png"; //$NON-NLS-1$
	private static final String RIGHT_FAN_DEC_DOWN = "artwork/RightFanDecDown.png"; //$NON-NLS-1$
	private static final String LEFT_FAN_SPEED = "artwork/LeftFanBars.png"; //$NON-NLS-1$
	private static final String RIGHT_FAN_SPEED = "artwork/RightFanBars.png"; //$NON-NLS-1$
	private static final int REFERENCE_WIDTH = 640;
	private static final int REFERENCE_HEIGHT = 400;
	private ICrustShell crustShell;
	private IClimateControl climate;
	private ScaledWidgetFactory f;
	private Composite screenComposite;
	private Label driverTemp;
	private Label passengerTemp;
	private ImageButton ac;
	private ImageButton rdef;
	private ImageButton[] flows;
	private ImageButton driverTempUp;
	private ImageButton driverTempDown;
	private ImageProgressBar driverFanSpeed;
	private ImageButton driverFanUp;
	private ImageButton driverFanDown;
	private ImageButton passengerTempDown;
	private ImageButton passengerTempUp;
	private ImageProgressBar passengerFanSpeed;
	private ImageButton passengerFanUp;
	private ImageButton passengerFanDown;

	public void setShell(ICrustShell value) {
		crustShell = value;
	}

	public void setClimate(IClimateControl value) {
		climate = value;
	}

	public void startup() {
		screenComposite = crustShell.installScreen(SLOT, this.getClass(), TOPBAR_ICON_OFF_IMAGE, TOPBAR_ICON_ON_IMAGE, null, this);
		new DisplayBlock() {
			public void run() {
				populateScreenComposite();
			}
		}.sync();
		climate.addListener(this);
		updateWidgetsFromModel();
	}

	public void shutdown() {
		climate.removeListener(this);
		new DisplayBlock() {
			public void run() {
				unpopulateScreenComposite();
			}
		}.sync();
		crustShell.uninstallScreen(SLOT, this);
	}

	// SelectionListener
	public void widgetDefaultSelected(SelectionEvent e) {
	}

	public void widgetSelected(SelectionEvent e) {
		if (e.widget == ac) {
			toggleAC();
		} else if (e.widget == rdef) {
			toggleRearDefrost();
		} else if (e.widget == flows[IClimateControl.AIR_FLOW_DEFROST]) {
			climate.setAirFlow(IClimateControl.AIR_FLOW_DEFROST);
		} else if (e.widget == flows[IClimateControl.AIR_FLOW_DEFROST_LOWER]) {
			climate.setAirFlow(IClimateControl.AIR_FLOW_DEFROST_LOWER);
		} else if (e.widget == flows[IClimateControl.AIR_FLOW_LOWER]) {
			climate.setAirFlow(IClimateControl.AIR_FLOW_LOWER);
		} else if (e.widget == flows[IClimateControl.AIR_FLOW_MIDDLE]) {
			climate.setAirFlow(IClimateControl.AIR_FLOW_MIDDLE);
		} else if (e.widget == flows[IClimateControl.AIR_FLOW_UPPER]) {
			climate.setAirFlow(IClimateControl.AIR_FLOW_UPPER);
		} else if (e.widget == driverTempUp) {
			climate.driverTemperatureUp();
		} else if (e.widget == driverTempDown) {
			climate.driverTemperatureDown();
		} else if (e.widget == driverFanUp) {
			climate.driverFanSpeedUp();
		} else if (e.widget == driverFanDown) {
			climate.driverFanSpeedDown();
		} else if (e.widget == passengerTempDown) {
			climate.passengerTemperatureDown();
		} else if (e.widget == passengerTempUp) {
			climate.passengerTemperatureUp();
		} else if (e.widget == passengerFanUp) {
			climate.passengerFanSpeedUp();
		} else if (e.widget == passengerFanDown) {
			climate.passengerFanSpeedDown();
		}
	}

	// IClimateControlListener
	public void airConditioningChanged(boolean isOn) {
		updateAirConditioning();
	}

	public void airFlowChanged(short flow) {
		updateFlow();
	}

	public void driverTemperatureChanged(int temperature) {
		updateDriver();
	}

	public void driverFanSpeedChanged(int speed) {
		updateDriverFan();
	}

	public void passengerFanSpeedChanged(int speed) {
		updatePassengerFan();
	}

	public void passengerTemperatureChanged(int temperature) {
		updatePassenger();
	}

	public void rearDefrostChanged(boolean isOn) {
		updateRearDefrost();
	}

	public void recirculationChanged(boolean isOn) {
		// we do not have a recirc button
	}

	// ICrustScreenListener implementation
	public void activate() {
		LogUtility.logDebug(this, "activated");
	}

	public void deactivate() {
		LogUtility.logDebug(this, "deactivated");
	}

	// Private
	private void populateScreenComposite() {
		f = new ScaledWidgetFactory(this.getClass(), screenComposite.getSize(), REFERENCE_HEIGHT, REFERENCE_WIDTH);
		screenComposite.setBackgroundImage(f.getSizedImageForWidget(CLIMATE_BACKGROUND, screenComposite));
		screenComposite.setBackgroundMode(SWT.INHERIT_FORCE);
		ac = f.createImageButton(screenComposite, SWT.CHECK, 214, 13, 101, 85, AC_OFF_IMAGE, AC_ON_IMAGE, AC_DEPRESSED_IMAGE, true, this);
		rdef = f.createImageButton(screenComposite, SWT.CHECK, 315, 13, 101, 85, R_DEF_OFF_IMAGE, R_DEF_ON_IMAGE, R_DEF_DEPRESSED_IMAGE, true, this);
		Font fontH1 = crustShell.getFont(ICrustShell.FONT_H1);
		driverTempUp = f.createImageButton(screenComposite, SWT.PUSH, 9, 35, 77, 50, LEFT_TEMP_INC_UP, LEFT_TEMP_INC_DOWN, true, this);
		driverTempDown = f.createImageButton(screenComposite, SWT.PUSH, 63, 35, 112, 50, LEFT_TEMP_DEC_UP, LEFT_TEMP_DEC_DOWN, true, this);
		driverTemp = f.createLabel(screenComposite, SWT.CENTER, 18, 96, 160, 50, fontH1);
		driverFanSpeed = f.createImageProgressBar(screenComposite, SWT.HORIZONTAL | SWT.LEFT_TO_RIGHT, 79, 147, 100, 28, LEFT_FAN_SPEED);
		driverFanUp = f.createImageButton(screenComposite, SWT.PUSH, 9, 188, 65, 52, LEFT_FAN_INC_UP, LEFT_FAN_INC_DOWN, true, this);
		driverFanDown = f.createImageButton(screenComposite, SWT.PUSH, 63, 188, 113, 52, LEFT_FAN_DEC_UP, LEFT_FAN_DEC_DOWN, true, this);
		passengerTempDown = f.createImageButton(screenComposite, SWT.PUSH, 463, 35, 114, 50, RIGHT_TEMP_DEC_UP, RIGHT_TEMP_DEC_DOWN, true, this);
		passengerTempUp = f.createImageButton(screenComposite, SWT.PUSH, 553, 35, 77, 50, RIGHT_TEMP_INC_UP, RIGHT_TEMP_INC_DOWN, true, this);
		passengerTemp = f.createLabel(screenComposite, SWT.CENTER, 463, 96, 160, 50, fontH1);
		passengerFanSpeed = f.createImageProgressBar(screenComposite, SWT.HORIZONTAL | SWT.RIGHT_TO_LEFT, 461, 147, 100, 28, RIGHT_FAN_SPEED);
		passengerFanUp = f.createImageButton(screenComposite, SWT.PUSH, 565, 188, 66, 52, RIGHT_FAN_INC_UP, RIGHT_FAN_INC_DOWN, true, this);
		passengerFanDown = f.createImageButton(screenComposite, SWT.PUSH, 463, 188, 114, 52, RIGHT_FAN_DEC_UP, RIGHT_FAN_DEC_DOWN, true, this);
		flows = new ImageButton[NUM_FLOW_BUTTONS];
		flows[IClimateControl.AIR_FLOW_UPPER] = f.createImageButton(screenComposite, SWT.PUSH, 109, 280, 105, 101, UPPER_OFF_IMAGE, UPPER_ON_IMAGE, UPPER_DEPRESSED_IMAGE, true, this);
		flows[IClimateControl.AIR_FLOW_MIDDLE] = f.createImageButton(screenComposite, SWT.PUSH, 165, 242, 112, 101, UPPER_LOWER_OFF_IMAGE, UPPER_LOWER_ON_IMAGE, UPPER_LOWER_DEPRESSED_IMAGE, true, this);
		flows[IClimateControl.AIR_FLOW_LOWER] = f.createImageButton(screenComposite, SWT.PUSH, 260, 236, 113, 82, LOWER_OFF_IMAGE, LOWER_ON_IMAGE, LOWER_DEPRESSED_IMAGE, true, this);
		flows[IClimateControl.AIR_FLOW_DEFROST_LOWER] = f.createImageButton(screenComposite, SWT.PUSH, 358, 242, 112, 101, LOWER_DEF_OFF_IMAGE, LOWER_DEF_ON_IMAGE, LOWER_DEF_DEPRESSED_IMAGE, true, this);
		flows[IClimateControl.AIR_FLOW_DEFROST] = f.createImageButton(screenComposite, SWT.PUSH, 421, 280, 105, 101, DEFOG_OFF_IMAGE, DEFOG_ON_IMAGE, DEFOG_DEPRESSED_IMAGE, true, this);
	}

	private void unpopulateScreenComposite() {
		f.disposeImageButtonImages(flows[IClimateControl.AIR_FLOW_DEFROST]);
		f.disposeImageButtonImages(flows[IClimateControl.AIR_FLOW_DEFROST_LOWER]);
		f.disposeImageButtonImages(flows[IClimateControl.AIR_FLOW_LOWER]);
		f.disposeImageButtonImages(flows[IClimateControl.AIR_FLOW_MIDDLE]);
		f.disposeImageButtonImages(flows[IClimateControl.AIR_FLOW_UPPER]);
		flows = null;
		f.disposeImageButtonImages(passengerFanDown);
		f.disposeImageButtonImages(passengerFanUp);
		f.disposeImageProgressBarImage(passengerFanSpeed);
		f.disposeLabelImage(passengerTemp);
		f.disposeImageButtonImages(passengerTempUp);
		f.disposeImageButtonImages(passengerTempDown);
		f.disposeImageButtonImages(driverFanDown);
		f.disposeImageButtonImages(driverFanUp);
		f.disposeImageProgressBarImage(driverFanSpeed);
		f.disposeLabelImage(driverTemp);
		f.disposeImageButtonImages(driverTempUp);
		f.disposeImageButtonImages(driverTempDown);
		f.disposeImageButtonImages(rdef);
		f.disposeImageButtonImages(ac);
		f.disposeCompositeImage(screenComposite);
		f = null;
	}

	private void updateWidgetsFromModel() {
		updateDriver();
		updateDriverFan();
		updatePassenger();
		updatePassengerFan();
		updateFlow();
	}

	// update screen from model
	private void updateDriver() {
		new DisplayBlock() {
			public void run() {
				driverTemp.setText("" + climate.getDriverTemperature()); //$NON-NLS-1$
				driverTemp.redraw();
			}
		}.sync();
	}

	private void updatePassenger() {
		new DisplayBlock() {
			public void run() {
				passengerTemp.setText("" + climate.getPassengerTemperature()); //$NON-NLS-1$
				passengerTemp.redraw();
			}
		}.sync();
	}

	private void updateDriverFan() {
		final int fanSpeed = FAN_BAR_WIDTHS[climate.getDriverFanSpeed()];
		new DisplayBlock() {
			public void run() {
				driverFanSpeed.setSelection(fanSpeed);
			}
		}.sync();
	}

	private void updatePassengerFan() {
		final int fanSpeed = FAN_BAR_WIDTHS[climate.getPassengerFanSpeed()];
		new DisplayBlock() {
			public void run() {
				passengerFanSpeed.setSelection(fanSpeed);
			}
		}.sync();
	}

	private void updateAirConditioning() {
		new DisplayBlock() {
			public void run() {
				ac.setSelection(climate.isAirConditioningOn());
			}
		}.sync();
	}

	private void updateRearDefrost() {
		new DisplayBlock() {
			public void run() {
				rdef.setSelection(climate.isRearDefrostOn());
			}
		}.sync();
	}

	private void updateFlow() {
		final int flow = climate.getAirFlow();
		new DisplayBlock() {
			public void run() {
				for (int i = 0; i < NUM_FLOW_BUTTONS; i++) {
					boolean selected = (flow == i);
					flows[i].setSelection(selected);
				}
			}
		}.sync();
	}

	private void toggleRearDefrost() {
		if (climate.isRearDefrostOn()) {
			climate.turnOffRearDefrost();
		} else {
			climate.turnOnRearDefrost();
		}
	}

	private void toggleAC() {
		if (climate.isAirConditioningOn()) {
			climate.turnOffAirConditioning();
		} else {
			climate.turnOnAirConditioning();
		}
	}
}
