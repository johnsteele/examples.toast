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
import org.eclipse.core.runtime.URIUtil;
import org.eclipse.equinox.p2.core.IProvisioningAgent;
import org.eclipse.equinox.p2.core.IProvisioningAgentProvider;
import org.eclipse.equinox.p2.core.ProvisionException;
import org.eclipse.equinox.p2.engine.IEngine;
import org.eclipse.equinox.p2.engine.IProfile;
import org.eclipse.equinox.p2.engine.IProfileRegistry;
import org.eclipse.equinox.p2.engine.IProvisioningPlan;
import org.eclipse.equinox.p2.engine.ProvisioningContext;
import org.eclipse.equinox.p2.engine.query.IUProfilePropertyQuery;
import org.eclipse.equinox.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.p2.metadata.ITouchpointType;
import org.eclipse.equinox.p2.metadata.MetadataFactory;
import org.eclipse.equinox.p2.metadata.MetadataFactory.InstallableUnitDescription;
import org.eclipse.equinox.p2.metadata.Version;
import org.eclipse.equinox.p2.planner.IPlanner;
import org.eclipse.equinox.p2.planner.IProfileChangeRequest;
import org.eclipse.equinox.p2.query.IQuery;
import org.eclipse.equinox.p2.query.IQueryResult;
import org.eclipse.equinox.p2.query.QueryUtil;
import org.eclipse.equinox.p2.repository.artifact.IArtifactRepositoryManager;
import org.eclipse.equinox.p2.repository.metadata.IMetadataRepository;
import org.eclipse.equinox.p2.repository.metadata.IMetadataRepositoryManager;
import org.eclipse.examples.toast.backend.provisioning.IProvisioner;
import org.eclipse.examples.toast.core.ICoreConstants;
import org.eclipse.examples.toast.core.LogUtility;
import org.eclipse.examples.toast.core.PropertyManager;

public class Provisioner implements IProvisioner {
	private static final String PROP_TOAST_ROOT = "toast.root"; //$NON-NLS-1$
	private static final String PROFILE_LOCATION = "toast.profile.location"; //$NON-NLS-1$
	private static final String REPO_LOCATIONS = "toast.repos"; //$NON-NLS-1$

	private URI dataLocation;
	private IProvisioningAgentProvider agentProvider;
	private IProvisioningAgent agent;
	private IProfileRegistry registry;
	private IArtifactRepositoryManager artifactManager;
	private IMetadataRepositoryManager metadataManager;
	private IPlanner planner;
	private IEngine engine;

	public Provisioner() {
	}

	protected void setAgentProvider(IProvisioningAgentProvider value) {
		agentProvider = value;
	}

	protected void startup() {
		String location = PropertyManager.getProperty(PROFILE_LOCATION);
		if (location != null)
			try {
				dataLocation = new URI(location);
			} catch (URISyntaxException e) {
				// do nothing.  will deal with it below
			}
		if (dataLocation == null) {
			LogUtility.logError("Invalid profile location URI - " + location);
			return;
		}
		try {
			agent = agentProvider.createAgent(dataLocation);
		} catch (ProvisionException e) {
			LogUtility.logError("Unable to initialize p2 agent", e);
			return;
		}
		registry = (IProfileRegistry) agent.getService(IProfileRegistry.SERVICE_NAME);
		metadataManager = (IMetadataRepositoryManager) agent.getService(IMetadataRepositoryManager.SERVICE_NAME);
		artifactManager = (IArtifactRepositoryManager) agent.getService(IArtifactRepositoryManager.SERVICE_NAME);
		planner = (IPlanner) agent.getService(IPlanner.SERVICE_NAME);
		engine = (IEngine) agent.getService(IEngine.SERVICE_NAME);

		String spec = PropertyManager.getProperty(REPO_LOCATIONS);
		if (spec != null)
			addRepositories(spec);
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
		IQuery query = new IUProfilePropertyQuery(PROP_TOAST_ROOT, "true");
		IQueryResult result = profile.query(query, new NullProgressMonitor());
		return result.toUnmodifiableSet();
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

		IProfileChangeRequest request = planner.createChangeRequest(profile);
		request.add(unit);
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
		IProfileChangeRequest request = planner.createChangeRequest(profile);
		request.remove(unit);
		return performOperation(profile, request, monitor);
	}

	private IStatus performOperation(IProfile profile, IProfileChangeRequest request, IProgressMonitor monitor) {
		ProvisioningContext context = new ProvisioningContext(agent);
		IProvisioningPlan plan = planner.getProvisioningPlan(request, context, monitor);
		if (!plan.getStatus().isOK())
			return plan.getStatus();
		// add the id IU if its not already installed.
		plan = installIdIU(plan);
		return engine.perform(plan, monitor);
	}

	public void addProfile(String id, Map properties) {
		IProfile profile = registry.getProfile(id);
		if (profile != null || properties == null)
			return;
		String location = new File(URIUtil.append(dataLocation, id)).toString();
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
		IQuery query = QueryUtil.createIUPropertyQuery(PROP_TOAST_ROOT, Boolean.TRUE.toString());
		IQueryResult result = metadataManager.query(query, new NullProgressMonitor());
		return result.toSet();
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
	private IProvisioningPlan installIdIU(IProvisioningPlan plan) {
		IQuery query = QueryUtil.createIUQuery("toast.id");
		IQueryResult toastIU = plan.getProfile().query(query, null);
		// If we find the id IU then it is already installed.
		//		if (!toastIU.isEmpty())
		//			return plan;
		IInstallableUnit idIU = createIdIU(plan.getProfile().getProfileId());
		plan.addInstallableUnit(idIU);
		return plan;
	}

	private IInstallableUnit createIdIU(String id) {
		InstallableUnitDescription iu = new MetadataFactory.InstallableUnitDescription();
		String time = Long.toString(System.currentTimeMillis());
		iu.setId("toast.id");
		iu.setVersion(Version.createOSGi(0, 0, 0, time));
		Map touchpointData = new HashMap();
		String data = "addJvmArg(jvmArg:-D" + ICoreConstants.ID_PROPERTY + "=" + id + ");";
		touchpointData.put("configure", data);
		data = "removeJvmArg(jvmArg:-D" + ICoreConstants.ID_PROPERTY + "=" + id + ");";
		touchpointData.put("unconfigure", data);
		iu.addTouchpointData(MetadataFactory.createTouchpointData(touchpointData));
		ITouchpointType touchpoint = MetadataFactory.createTouchpointType("org.eclipse.equinox.p2.osgi", Version.createOSGi(1, 0, 0));
		iu.setTouchpointType(touchpoint);
		return MetadataFactory.createInstallableUnit(iu);
	}
}
