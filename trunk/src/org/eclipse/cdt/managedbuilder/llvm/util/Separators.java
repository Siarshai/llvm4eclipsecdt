/*******************************************************************************
 * Copyright (c) 2010 Nokia Siemens Networks Oyj, Finland.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *      Nokia Siemens Networks - initial implementation
 *******************************************************************************/
package org.eclipse.cdt.managedbuilder.llvm.util;

/**
 * Provides different separators such as file and path separators.
 * 
 * @author Petri Tuononen
 */
public class Separators {

	private static String pathSeparator = System.getProperty("path.separator");
	private static String fileSeparator = System.getProperty("file.separator");
	
	/**
	 * Get path separator.
	 * 
	 * @return  path separator
	 */
	public static String getPathSeparator() {
		return pathSeparator;
	}
	
	/**
	 * Get file separator.
	 * 
	 * @return  file separator
	 */
	public static String getFileSeparator() {
		return fileSeparator;
	}
	
}
