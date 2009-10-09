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
package org.eclipse.examples.toast.devsim.fw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import org.eclipse.examples.toast.core.LogUtility;

public class HtmlGenerator {
	private static final String DELIMITER = "%%%";
	private PrintWriter writer;
	private Map parameters;

	public HtmlGenerator(PrintWriter writer) {
		super();
		this.writer = writer;
		parameters = new HashMap(7);
	}

	public void replace(String key, String value) {
		parameters.put(key, value);
	}

	public void write(String templateFileName, Class classInBundle) {
		InputStream stream = classInBundle.getResourceAsStream(templateFileName);
		InputStreamReader reader = new InputStreamReader(stream);
		BufferedReader bufferedReader = new BufferedReader(reader);
		try {
			try {
				String line;
				while ((line = bufferedReader.readLine()) != null) {
					processLine(line);
				}
			} finally {
				bufferedReader.close();
			}
		} catch (IOException e) {
			LogUtility.logError(this, "Cannot write template file: " + templateFileName, e);
		}
	}

	// Private
	private void processLine(String line) {
		int spot = 0;
		while (true) {
			int index = line.indexOf(DELIMITER, spot);
			if (index == -1) {
				String tail = line.substring(spot);
				writer.println(tail);
				return;
			}
			String chunk = line.substring(spot, index);
			writer.print(chunk);
			spot = index + DELIMITER.length();
			int end = line.indexOf(DELIMITER, spot);
			String key = line.substring(spot, end);
			spot = end + DELIMITER.length();
			String value = (String) parameters.get(key);
			if (value == null)
				value = "{unknown key: " + key + "}";
			writer.print(value);
		}
	}
}
