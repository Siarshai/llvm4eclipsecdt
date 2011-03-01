/*******************************************************************************
 * Copyright (c) 2010, 2011 Nokia Siemens Networks Oyj, Finland.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *      Nokia Siemens Networks - initial implementation
 *      Leo Hippelainen - Initial implementation
 *      Petri Tuononen - Initial implementation
 *******************************************************************************/
package org.eclipse.cdt.managedbuilder.llvm.ui.preferences;

import org.eclipse.cdt.managedbuilder.llvm.ui.LlvmUIPlugin;
import org.eclipse.jface.preference.*;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.IWorkbench;

/**
 * This class represents a preference page that
 * is contributed to the Preferences dialog. By 
 * subclassing <samp>FieldEditorPreferencePage</samp>, we
 * can use the field support built into JFace that allows
 * us to create a page that is small and knows how to 
 * save, restore and apply itself.
 * <p>
 * This page is used to modify preferences only. They
 * are stored in the preference store that belongs to
 * the main plug-in class. That way, preferences can
 * be accessed directly via the preference store.
 * 
 * TODO: When creating a new project with LLVM, automatically get values
 *       from PreferencePage to Paths & Symbols. 
 */
public class LlvmPreferencePage
	extends FieldEditorPreferencePage
	implements IWorkbenchPreferencePage {

	/**
	 * Constructor.
	 * Sets a preference store to use the LLVM preference store.
	 */
	public LlvmPreferencePage() {
		setPreferenceStore(LlvmUIPlugin.getDefault().getPreferenceStore());
		setDescription("Preferences for LLVM tool chain."); //$NON-NLS-1$
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
		// Nothing here.
	}

	@Override
	/**
	 * Get Description name.
	 * @param String Description
	 */
	public String getDescription() {
		return null;
	}

	@Override
	/**
	 * Creates field editors for the preference page.
	 */
	protected void createFieldEditors() {
		//field for installation path
		addField(new DirectoryFieldEditor(PreferenceConstants.P_LLVM_PATH, 
				"LLVM &Installation folder:", getFieldEditorParent())); //$NON-NLS-1$
		//list editor for include paths
		IncludePathListEditor includePathListEditor = new IncludePathListEditor(
				PreferenceConstants.P_LLVM_INCLUDE_PATH, "Include directories", //$NON-NLS-1$
				getFieldEditorParent());
		addField(includePathListEditor);
		//list editor for libraries
		LibraryListEditor libraryListEditor = new LibraryListEditor(
				PreferenceConstants.P_LLVM_LIBRARIES, "Libraries", //$NON-NLS-1$
				getFieldEditorParent());
		addField(libraryListEditor);
		//list editor for library paths
		LibraryPathListEditor libraryPathListEditor = new LibraryPathListEditor(
				PreferenceConstants.P_LLVM_LIBRARY_PATH, "Library search path directories", //$NON-NLS-1$
				getFieldEditorParent());
		addField(libraryPathListEditor);
	}
	
}
