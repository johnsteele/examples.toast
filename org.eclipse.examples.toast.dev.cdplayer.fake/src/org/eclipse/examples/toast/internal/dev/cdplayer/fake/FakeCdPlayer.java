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
package org.eclipse.examples.toast.internal.dev.cdplayer.fake;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.examples.toast.dev.cdplayer.ICdPlayer;
import org.eclipse.examples.toast.dev.cdplayer.ICdPlayerListener;

public class FakeCdPlayer implements ICdPlayer {
	private static final int BIG_PRIME = 83;
	private static final int FAST_RATE = 7; // seconds of music
	private static final int FAKE_TRACK_COUNT = 8;
	private static final int[] FAKE_TRACK_TIMES = {253, 344, 381, 444, 199, 288, 202, 317};
	private static final int NO_EVENT = 0;
	private static final int FAST_FORWARD_EVENT = 1;
	private static final int NEXT_TRACK_EVENT = 2;
	private static final int PAUSE_EVENT = 3;
	private static final int PLAY_EVENT = 4;
	private static final int PREVIOUS_TRACK_EVENT = 5;
	private static final int REWIND_EVENT = 6;
	private static final int STOP_EVENT = 7;
	private static final int EJECT_EVENT = 8;
	private static final int TICK_EVENT = 9;
	private List listeners;
	private boolean hasCd = true;
	private boolean isPlaying = false;
	private boolean isPaused = false;
	private boolean isFast = false;
	private boolean isForward = true;
	private boolean isRandomOn = false;
	private int track = 1;
	private int trackCount = FAKE_TRACK_COUNT;
	private int time = 0;
	private int[] trackTimes = FAKE_TRACK_TIMES;
	private Job job;
	private List eventQueue;

	public FakeCdPlayer() {
		super();
		listeners = new ArrayList(2);
		eventQueue = new ArrayList(5);
		createJob();
	}

	public void shutdown() {
		stopJob();
	}

	// ICdPlayerService implementation
	public void addListener(ICdPlayerListener listener) {
		synchronized (listeners) {
			listeners.add(listener);
		}
	}

	public void removeListener(ICdPlayerListener listener) {
		synchronized (listeners) {
			listeners.remove(listener);
		}
	}

	public void eject() {
		addEvent(EJECT_EVENT);
	}

	public void fastForward() {
		addEvent(FAST_FORWARD_EVENT);
	}

	public int getTime() {
		return time;
	}

	public int getTrack() {
		return track;
	}

	public boolean hasCd() {
		return hasCd;
	}

	public boolean isPlaying() {
		return isPlaying;
	}

	public boolean isPausable() {
		return isPlaying && isPaused == false;
	}

	public boolean isPaused() {
		return isPaused;
	}

	public boolean isRandomOn() {
		return isRandomOn;
	}

	public void nextTrack() {
		addEvent(NEXT_TRACK_EVENT);
	}

	public void pause() {
		addEvent(PAUSE_EVENT);
	}

	public void play() {
		addEvent(PLAY_EVENT);
	}

	public void previousTrack() {
		addEvent(PREVIOUS_TRACK_EVENT);
	}

	public void rewind() {
		addEvent(REWIND_EVENT);
	}

	public void stop() {
		addEvent(STOP_EVENT);
	}

	public void turnRandomOff() {
		if (isRandomOn == true) {
			isRandomOn = false;
			notifyRandomChanged();
		}
	}

	public void turnRandomOn() {
		if (isRandomOn == false) {
			isRandomOn = true;
			notifyRandomChanged();
		}
	}

	// Private
	private void addEvent(int event) {
		synchronized (eventQueue) {
			eventQueue.add(new Integer(event));
		}
		job.schedule();
	}

	private void stopJob() {
		job.cancel();
		try {
			job.join();
		} catch (InterruptedException e) {
			// shutting down, ok to ignore
		}
	}

	private void createJob() {
		job = new Job("FakeCdPlayer") {
			protected IStatus run(IProgressMonitor monitor) {
				int event = getNextEvent();
				processEvent(event);
				int reschedule = getTickDelay();
				if (reschedule > 0)
					schedule(reschedule);
				return Status.OK_STATUS;
			}
		};
	}

	private int getNextEvent() {
		synchronized (eventQueue) {
			if (eventQueue.isEmpty()) {
				return getTickDelay() > 0 ? TICK_EVENT : NO_EVENT;
			}
			Integer event = (Integer) eventQueue.remove(0);
			return event.intValue();
		}
	}

	private int getTickDelay() {
		if (isPlaying && isPaused == false || isFast == true) {
			return isFast ? 500 : 1000;
		}
		return 0;
	}

	private void processEvent(int event) {
		switch (event) {
			case FAST_FORWARD_EVENT :
				fastForwardEvent();
				break;
			case NEXT_TRACK_EVENT :
				nextTrackEvent();
				break;
			case PAUSE_EVENT :
				pauseEvent();
				break;
			case PLAY_EVENT :
				playEvent();
				break;
			case PREVIOUS_TRACK_EVENT :
				previousTrackEvent();
				break;
			case REWIND_EVENT :
				rewindEvent();
				break;
			case STOP_EVENT :
				stopEvent();
				break;
			case EJECT_EVENT :
				ejectEvent();
				break;
			case TICK_EVENT :
				updateTime();
				break;
			case NO_EVENT :
			default :
				break;
		}
	}

	// Notification
	private void notifyInserted() {
		synchronized (listeners) {
			Iterator iterator = listeners.iterator();
			while (iterator.hasNext()) {
				ICdPlayerListener listener = (ICdPlayerListener) iterator.next();
				listener.inserted();
			}
		}
	}

	private void notifyEjected() {
		synchronized (listeners) {
			Iterator iterator = listeners.iterator();
			while (iterator.hasNext()) {
				ICdPlayerListener listener = (ICdPlayerListener) iterator.next();
				listener.ejected();
			}
		}
	}

	private void notifyTrackChanged() {
		synchronized (listeners) {
			Iterator iterator = listeners.iterator();
			while (iterator.hasNext()) {
				ICdPlayerListener listener = (ICdPlayerListener) iterator.next();
				listener.trackChanged(track);
			}
		}
	}

	private void notifyTimeChanged() {
		synchronized (listeners) {
			Iterator iterator = listeners.iterator();
			while (iterator.hasNext()) {
				ICdPlayerListener listener = (ICdPlayerListener) iterator.next();
				listener.timeChanged(time);
			}
		}
	}

	private void notifyPaused() {
		synchronized (listeners) {
			Iterator iterator = listeners.iterator();
			while (iterator.hasNext()) {
				ICdPlayerListener listener = (ICdPlayerListener) iterator.next();
				listener.paused();
			}
		}
	}

	private void notifyStarted() {
		synchronized (listeners) {
			Iterator iterator = listeners.iterator();
			while (iterator.hasNext()) {
				ICdPlayerListener listener = (ICdPlayerListener) iterator.next();
				listener.started();
			}
		}
	}

	private void notifyStopped() {
		synchronized (listeners) {
			Iterator iterator = listeners.iterator();
			while (iterator.hasNext()) {
				ICdPlayerListener listener = (ICdPlayerListener) iterator.next();
				listener.stopped();
			}
		}
	}

	private void notifyTrackCountChanged() {
		synchronized (listeners) {
			Iterator iterator = listeners.iterator();
			while (iterator.hasNext()) {
				ICdPlayerListener listener = (ICdPlayerListener) iterator.next();
				listener.trackCountChanged(trackCount);
			}
		}
	}

	private void notifyRandomChanged() {
		synchronized (listeners) {
			Iterator iterator = listeners.iterator();
			while (iterator.hasNext()) {
				ICdPlayerListener listener = (ICdPlayerListener) iterator.next();
				listener.randomChanged(isRandomOn);
			}
		}
	}

	// Event handlers
	private void updateTime() {
		int delta;
		if (isFast) {
			if (isForward) {
				delta = FAST_RATE;
			} else {
				delta = -FAST_RATE;
			}
		} else {
			delta = 1;
		}
		time += delta;
		if (time < 0) {
			if (track == 1) {
				// front of first track: stop
				stopEvent();
				return;
			}
			track--;
			time = trackTimes[track - 1];
			notifyTrackChanged();
		} else if (time > trackTimes[track - 1]) {
			if (track == trackCount) {
				// end of last track: stop
				stopEvent();
				return;
			}
			track++;
			time = 0;
			notifyTrackChanged();
		}
		notifyTimeChanged();
	}

	private void ejectEvent() {
		if (hasCd) {
			stopEvent();
			hasCd = false;
			trackCount = 0;
			notifyTrackCountChanged();
			track = 0;
			notifyTrackChanged();
			notifyEjected();
		} else {
			hasCd = true;
			notifyInserted();
			trackCount = FAKE_TRACK_COUNT;
			notifyTrackCountChanged();
			trackTimes = FAKE_TRACK_TIMES;
			track = 1;
			notifyTrackChanged();
			notifyTimeChanged();
		}
	}

	private void fastForwardEvent() {
		if (hasCd == false) {
			return;
		}
		if (isPlaying == false) {
			return;
		}
		if (isFast == false || isForward == false) {
			isFast = true;
			isForward = true;
		}
	}

	private void nextTrackEvent() {
		if (hasCd == false) {
			return;
		}
		isForward = true;
		isFast = false;
		int nextTrack = isRandomOn ? getRandomTrack() : track + 1;
		if (nextTrack > trackCount) {
			nextTrack = 1;
		}
		if (nextTrack <= trackCount) {
			track = nextTrack;
			notifyTrackChanged();
			if (time != 0) {
				time = 0;
				notifyTimeChanged();
			}
		}
	}

	private int getRandomTrack() {
		int randomTrack = track;
		for (int seed = BIG_PRIME; randomTrack == track; seed++) {
			int random = (int) (System.currentTimeMillis() % seed);
			randomTrack = random % trackCount + 1;
		}
		return randomTrack;
	}

	private void pauseEvent() {
		if (hasCd == false) {
			return;
		}
		if (isPlaying == false) {
			return;
		}
		if (isFast == true) {
			isFast = false;
		}
		if (isPaused == false) {
			isPaused = true;
			notifyPaused();
		}
	}

	private void playEvent() {
		if (hasCd == false) {
			return;
		}
		if (isFast == true || isForward == false) {
			isFast = false;
			isForward = true;
		}
		if (isPaused || isPlaying == false) {
			isPaused = false;
			isPlaying = true;
			notifyStarted();
		}
	}

	private void previousTrackEvent() {
		if (hasCd == false) {
			return;
		}
		isForward = true;
		isFast = false;
		if (time > 0) {
			time = 0;
			notifyTimeChanged();
		} else { // start of a track
			int prevTrack = isRandomOn ? getRandomTrack() : track - 1;
			if (prevTrack == 0) {
				prevTrack = trackCount;
			}
			if (prevTrack >= 1) {
				track = prevTrack;
				notifyTrackChanged();
				if (time != 0) {
					time = 0;
					notifyTimeChanged();
				}
			}
		}
	}

	private void rewindEvent() {
		if (hasCd == false) {
			return;
		}
		if (isPlaying == false) {
			return;
		}
		if (isFast == false || isForward == true) {
			isFast = true;
			isForward = false;
		}
	}

	private void stopEvent() {
		if (hasCd == false) {
			return;
		}
		if (isPlaying == true) {
			isPlaying = false;
			isPaused = false;
			isFast = false;
			isForward = true;
			notifyStopped();
			if (time != 0) {
				time = 0;
				notifyTimeChanged();
			}
		}
	}
}
