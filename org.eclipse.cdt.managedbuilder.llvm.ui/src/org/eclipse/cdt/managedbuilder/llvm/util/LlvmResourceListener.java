/*******************************************************************************
 * Copyright (c) 2010, 2011 Nokia Siemens Networks Oyj, Finland.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *      Nokia Siemens Networks - initial implementation
 *      Petri Tuononen - Initial implementation
 *******************************************************************************/
package org.eclipse.cdt.managedbuilder.llvm.util;

import org.eclipse.cdt.managedbuilder.llvm.ui.preferences.LlvmPreferenceStore;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.runtime.CoreException;

/**
 * Implements Resource listener.
 */
public class LlvmResourceListener implements IResourceChangeListener {

	/**
	 * Defines what happens when resources have changed.
	 * 
	 * @param event IResourceChangeEvent
	 */
	public void resourceChanged(IResourceChangeEvent event) {

		if (event.getType() == IResourceChangeEvent.POST_BUILD) { //refresh every project after build
			//get all projects
			IProject[] projects = LlvmToolOptionPathUtil.getProjectsInWorkspace();

			//refresh the projects
			for (IProject proj : projects) {
				try {
					proj.refreshLocal(IResource.DEPTH_INFINITE, null);
				} catch (CoreException e) {
					e.printStackTrace();
				}
			}
		} else if (event.getType() == IResourceChangeEvent.PRE_BUILD) {
			String os = System.getProperty("os.name").toLowerCase(); //$NON-NLS-1$
			if (os.indexOf("win") >= 0) { //$NON-NLS-1$
				LlvmPreferenceStore.addMinGWStdLib();
//				LlvmToolOptionPathUtil.addMissingCppIncludesForMingw(); //TODO: Remove when Scanner Discovery has been fixed
			} else if (os.indexOf( "nix") >=0 || os.indexOf( "nux") >=0 /*|| os.indexOf( "mac") >=0 */) { //$NON-NLS-1$ //$NON-NLS-2$
				LlvmPreferenceStore.addStdLibUnix();
			}
			
			/*
			 * try to add values (include and library paths and libraries) to
			 * projects's build configurations to ensure that newly added projects
			 * have necessary paths.
			 */
			LlvmToolOptionPathUtil.addAllIncludesToBuildConf();
			LlvmToolOptionPathUtil.addAllLibsToBuildConf();
			LlvmToolOptionPathUtil.addAllLibPathsToBuildConf();
		} else {
			return;
		}

	}

}