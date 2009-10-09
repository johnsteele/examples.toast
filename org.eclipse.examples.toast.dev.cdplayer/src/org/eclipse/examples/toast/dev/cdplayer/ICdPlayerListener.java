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
package org.eclipse.examples.toast.dev.cdplayer;

public interface ICdPlayerListener {
	public void inserted();

	public void ejected();

	public void trackCountChanged(int trackCount);

	public void started();

	public void paused();

	public void stopped();

	public void trackChanged(int track);

	public void timeChanged(int seconds);

	public void randomChanged(boolean isRandomOn);
}
