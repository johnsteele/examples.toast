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
package org.eclipse.examples.toast.internal.backend.provisioning;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.equinox.internal.provisional.p2.artifact.repository.IArtifactRepositoryManager;
import org.eclipse.equinox.internal.provisional.p2.core.ProvisionException;
import org.eclipse.equinox.internal.provisional.p2.core.Version;
import org.eclipse.equinox.internal.provisional.p2.director.IPlanner;
import org.eclipse.equinox.internal.provisional.p2.director.ProfileChangeRequest;
import org.eclipse.equinox.internal.provisional.p2.director.ProvisioningPlan;
import org.eclipse.equinox.internal.provisional.p2.engine.DefaultPhaseSet;
import org.eclipse.equinox.internal.provisional.p2.engine.IEngine;
import org.eclipse.equinox.internal.provisional.p2.engine.IProfile;
import org.eclipse.equinox.internal.provisional.p2.engine.IProfileRegistry;
import org.eclipse.equinox.internal.provisional.p2.engine.IUProfilePropertyQuery;
import org.eclipse.equinox.internal.provisional.p2.engine.InstallableUnitOperand;
import org.eclipse.equinox.internal.provisional.p2.engine.Operand;
import org.eclipse.equinox.internal.provisional.p2.engine.ProvisioningContext;
import org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.internal.provisional.p2.metadata.ITouchpointType;
import org.eclipse.equinox.internal.provisional.p2.metadata.MetadataFactory;
import org.eclipse.equinox.internal.provisional.p2.metadata.MetadataFactory.InstallableUnitDescription;
import org.eclipse.equinox.internal.provisional.p2.metadata.query.IUPropertyQuery;
import org.eclipse.equinox.internal.provisional.p2.metadata.query.InstallableUnitQuery;
import org.eclipse.equinox.internal.provisional.p2.metadata.repository.IMetadataRepository;
import org.eclipse.equinox.internal.provisional.p2.metadata.repository.IMetadataRepositoryManager;
import org.eclipse.equinox.internal.provisional.p2.query.Collector;
import org.eclipse.equinox.internal.provisional.p2.query.Query;
import org.eclipse.examples.toast.backend.provisioning.IProvisioner;
import org.eclipse.examples.toast.core.ICoreConstants;
import org.eclipse.examples.toast.core.LogUtility;
import org.eclipse.examples.toast.core.PropertyManager;

public class Provisioner implements IProvisioner {
	private static final String PROP_TOAST_ROOT = "toast.root"; //$NON-NLS-1$
	private static final String PROFILE_LOCATION = "toast.profile.location"; //$NON-NLS-1$
	private static final String REPO_LOCATIONS = "toast.repos"; //$NON-NLS-1$

	private IProfileRegistry registry;
	private IPlanner planner;
	private IEngine engine;
	private IMetadataRepository[] repos;
	private File dataLocation;
	private IMetadataRepositoryManager metadataManager;
	private IArtifactRepositoryManager artifactManager;

	public Provisioner() {
	}

	protected void setProfileRegistry(IProfileRegistry value) {
		registry = value;
	}

	protected void setPlanner(IPlanner value) {
		planner = value;
	}

	protected void setEngine(IEngine value) {
		engine = value;
	}

	protected void setMetadataManager(IMetadataRepositoryManager value) {
		metadataManager = value;
	}

	protected void setArtifactManager(IArtifactRepositoryManager value) {
		artifactManager = value;
	}

	protected void startup() {
		String location = PropertyManager.getProperty(PROFILE_LOCATION);
		if (location != null)
			dataLocation = new File(location);
		String spec = PropertyManager.getProperty(REPO_LOCATIONS);
		if (spec != null)
			repos = addRepositories(spec);
	}

	protected void shutdown() {
	}

	private IMetadataRepository[] addRepositories(String spec) {
		String[] locations = spec.split(",");
		ArrayList result = new ArrayList();
		for (int i = 0; i < locations.length; i++) {
			URI location = null;
			try {
				location = new URI(locations[i].trim());
				metadataManager.loadRepository(location, null);
				result.add(metadataManager.loadRepository(location, null));
				artifactManager.addRepository(location);
			} catch (URISyntaxException e) {
				LogUtility.logError("Invalid URI for repository " + location, e);
			} catch (ProvisionException e) {
				LogUtility.logWarning("Unable to load repository " + location, e);
			}
		}
		return (IMetadataRepository[]) result.toArray(new IMetadataRepository[result.size()]);
	}

	public Collection getInstalled(String id) {
		IProfile profile = registry.getProfile(id);
		if (profile == null)
			return Collections.EMPTY_LIST;
		Query query = new IUProfilePropertyQuery(profile, PROP_TOAST_ROOT, "true");
		Collector collector = profile.query(query, new Collector(), new NullProgressMonitor());
		return collector.toCollection();
	}

	private IInstallableUnit findFeature(String feature) {
		Collection features = getAvailableFeatures();
		for (Iterator i = features.iterator(); i.hasNext();) {
			IInstallableUnit unit = (IInstallableUnit) i.next();
			if (unit.getId().equals(feature))
				return unit;
		}
		return null;
	}

	public IStatus install(String id, String feature, IProgressMonitor monitor) {
		IProfile profile = registry.getProfile(id);
		if (profile == null)
			return new Status(IStatus.ERROR, LogUtility.getStatusId(this), "Cannot find profile for: " + id);
		IInstallableUnit unit = findFeature(feature);
		if (unit == null)
			return new Status(IStatus.ERROR, LogUtility.getStatusId(this), "Cannot find feature : " + feature);
		ProfileChangeRequest request = new ProfileChangeRequest(profile);
		request.addInstallableUnits(new IInstallableUnit[] {unit});
		request.setInstallableUnitProfileProperty(unit, PROP_TOAST_ROOT, "true");
		return performOperation(profile, request, monitor);
	}

	public IStatus uninstall(String id, String feature, IProgressMonitor monitor) {
		IProfile profile = registry.getProfile(id);
		if (profile == null)
			return new Status(IStatus.ERROR, LogUtility.getStatusId(this), "Cannot find profile for: " + id);
		IInstallableUnit unit = findFeature(feature);
		if (unit == null)
			return new Status(IStatus.ERROR, LogUtility.getStatusId(this), "Cannot find feature : " + feature);
		ProfileChangeRequest request = new ProfileChangeRequest(profile);
		request.removeInstallableUnits(new IInstallableUnit[] {unit});
		return performOperation(profile, request, monitor);
	}

	private IStatus performOperation(IProfile profile, ProfileChangeRequest request, IProgressMonitor monitor) {
		ProvisioningContext context = new ProvisioningContext();
		ProvisioningPlan result = planner.getProvisioningPlan(request, context, monitor);
		if (!result.getStatus().isOK())
			return result.getStatus();
		// add the id IU if its not already installed.
		Operand[] operands = installIdIU(profile, result.getOperands());
		return engine.perform(profile, new DefaultPhaseSet(), operands, context, monitor);
	}

	public void addProfile(String id, Map properties) {
		IProfile profile = registry.getProfile(id);
		if (profile != null || properties == null)
			return;
		String location = new File(dataLocation, id).toString();
		String environment = "osgi.os=" + properties.get("osgi.os");
		environment += ",osgi.ws=" + properties.get("osgi.ws");
		environment += ",osgi.arch=" + properties.get("osgi.arch");
		Map props = new HashMap();
		props.put(IProfile.PROP_INSTALL_FOLDER, location);
		props.put(IProfile.PROP_CACHE, location);
		props.put(IProfile.PROP_ENVIRONMENTS, environment);

		try {
			profile = registry.addProfile(id, props);
		} catch (ProvisionException e) {
			LogUtility.logError("Error adding profile: " + id, e);
		}
	}

	public Collection getProfiles() {
		IProfile[] profiles = registry.getProfiles();
		Collection result = new ArrayList(profiles.length);
		for (int i = 0; i < profiles.length; i++)
			result.add(profiles[i].getProfileId());
		return result;
	}

	public void removeProfile(String id) {
		registry.removeProfile(id);
	}

	/**
	 * Return a collection of features that are available to be installed.
	 */
	public Collection getAvailableFeatures() {
		Query query = new IUPropertyQuery(PROP_TOAST_ROOT, Boolean.TRUE.toString());
		Collector collector = new Collector();
		for (int i = 0; i < repos.length; i++)
			repos[i].query(query, collector, new NullProgressMonitor());
		return collector.toCollection();
	}

	/**
	 * Return the collection of features that are available to be installed on
	 * the device with the given id.
	 */
	public Collection getAvailableFeatures(String id) {
		Collection result = getAvailableFeatures();
		result.removeAll(getInstalled(id));
		return result;
	}

	/**
	 * Add in an IU that sets the toast.id system property to identify the 
	 * client running this profile
	 */
	private Operand[] installIdIU(IProfile profile, Operand[] operands) {
		Query query = new InstallableUnitQuery("toast.id");
		Collector collector = profile.query(query, new Collector(), null);
		// If we find the id IU then it is already installed.
		if (!collector.isEmpty())
			return operands;
		IInstallableUnit iu = createIdIU(profile.getProfileId());
		Operand[] result = new Operand[operands.length + 1];
		System.arraycopy(operands, 0, result, 0, operands.length);
		result[operands.length] = new InstallableUnitOperand(null, iu);
		return result;
	}

	private IInstallableUnit createIdIU(String id) {
		InstallableUnitDescription iu = new MetadataFactory.InstallableUnitDescription();
		String time = Long.toString(System.currentTimeMillis());
		iu.setId("toast.id");
		iu.setVersion(new Version(0, 0, 0, time));
		Map touchpointData = new HashMap();
		String data = "addJvmArg(jvmArg:-D" + ICoreConstants.ID_PROPERTY + "=" + id + ");";
		touchpointData.put("configure", data);
		data = "removeJvmArg(jvmArg:-D" + ICoreConstants.ID_PROPERTY + "=" + id + ");";
		touchpointData.put("unconfigure", data);
		iu.addTouchpointData(MetadataFactory.createTouchpointData(touchpointData));
		ITouchpointType touchpoint = MetadataFactory.createTouchpointType("org.eclipse.equinox.p2.osgi", new Version(1, 0, 0));
		iu.setTouchpointType(touchpoint);
		return MetadataFactory.createInstallableUnit(iu);
	}
}
