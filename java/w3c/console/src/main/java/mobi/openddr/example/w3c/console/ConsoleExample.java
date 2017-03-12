/*
 * Copyright (c) 2011-2017 OpenDDR LLC and others. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package mobi.openddr.example.w3c.console;

import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.ddr.simple.Service;
import org.w3c.ddr.simple.ServiceFactory;

import mobi.openddr.simple.DDRService;

public class ConsoleExample {
	private static final Logger log = LogManager.getLogger(ConsoleExample.class);
	private static Service identificationService = null;

	public static void main(String[] args) {
//		double d = 2.567;
//		int i = 3;
//		
//		System.out.println(d);
//		System.out.println(i);
//		System.out.println(i * (int)d);
		
	    log.info("Initialize filter");
	    Properties initializationProperties = new Properties();             
	
	    try {
	        initializationProperties.load(ConsoleExample.class.getResourceAsStream("WEB-INF/classes/oddr.properties"));
	        identificationService = ServiceFactory.newService("mobi.openddr.simple.DDRService", initializationProperties.getProperty(DDRService.ODDR_VOCABULARY_IRI), initializationProperties);
	    } catch (Exception ex) {
	    	log.error(ex);
	        throw new RuntimeException(ex);
	    }
	}
}
