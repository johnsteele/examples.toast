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
package org.eclipse.examples.toast.backend.data.loader;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;

public class DataLoaderApplication implements IApplication {

	protected DataLoader loader;

	public Object start(IApplicationContext context) throws Exception {
		return run((String[]) context.getArguments().get("application.args")); //$NON-NLS-1$
	}

	public void stop() {
	}

	public Object run(String args[]) throws Exception {
		loader = createDataLoader();
		processCommandLineArguments(args);
		if (loader.locationSource == null)
			loader.locationSource = DataLoader.class.getResource("sanfran.txt");

		return loader.run();
	}

	protected DataLoader createDataLoader() {
		return new DataLoader();
	}

	protected void processCommandLineArguments(String[] args) throws Exception {
		if (args == null)
			return;
		for (int i = 0; i < args.length; i++) {
			// check for args without parameters (i.e., a flag arg)
			processFlag(args[i]);

			// check for args with parameters. If we are at the last argument or if the next one
			// has a '-' as the first character, then we can't have an arg with a parm so continue.
			if (i == args.length - 1 || args[i + 1].startsWith("-"))
				continue;
			processParameter(args[i], args[++i]);
		}
	}

	protected void processParameter(String arg, String parameter) {
		if (arg.equalsIgnoreCase("-perZone"))
			loader.perZone = Integer.parseInt(parameter);
		if (arg.equalsIgnoreCase("-waybils"))
			loader.perZone = Integer.parseInt(parameter);
		if (arg.equalsIgnoreCase("-locations"))
			try {
				loader.locationSource = new URL(parameter);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		if (arg.equalsIgnoreCase("-states"))
			loader.states = Arrays.asList(parameter.split(","));
		if (arg.equalsIgnoreCase("-zips"))
			loader.zips = Arrays.asList(parameter.split(","));
		if (arg.equalsIgnoreCase("-cities"))
			loader.cities = Arrays.asList(parameter.split(","));
		if (arg.equalsIgnoreCase("-factor"))
			loader.factor = Integer.parseInt(parameter);
	}

	protected void processFlag(String arg) {
		if (arg.equalsIgnoreCase("-byState"))
			loader.mode = DataLoader.STATE_MODE;
		if (arg.equalsIgnoreCase("-byZip"))
			loader.mode = DataLoader.ZIP_MODE;
		if (arg.equalsIgnoreCase("-byCity"))
			loader.mode = DataLoader.CITY_MODE;
	}
}
