/*******************************************************************************
 * Copyright (c) 2009 Oracle.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors: 
 *     Shaun Smith - initial API and implementation
 *******************************************************************************/
package org.eclipse.examples.toast.backend.data.persistence;

import java.net.URI;
import java.net.URISyntaxException;
import org.eclipse.persistence.mappings.DatabaseMapping;
import org.eclipse.persistence.mappings.converters.Converter;
import org.eclipse.persistence.sessions.Session;

public class UriConverter implements Converter {
	private static final long serialVersionUID = -9030760833721299817L;

	public Object convertDataValueToObjectValue(Object dataValue, Session session) {
		try {
			if (dataValue != null) {
				return new URI((String) dataValue);
			} else {
				return null;
			}
		} catch (URISyntaxException e) {
			throw new RuntimeException("URI column does not contian valid URI");
		}
	}

	public Object convertObjectValueToDataValue(Object objectValue, Session session) {
		if (objectValue != null) {
			return ((URI) objectValue).toString();
		} else {
			return null;
		}
	}

	public void initialize(DatabaseMapping mapping, Session session) {
	}

	public boolean isMutable() {
		return false;
	}

}
