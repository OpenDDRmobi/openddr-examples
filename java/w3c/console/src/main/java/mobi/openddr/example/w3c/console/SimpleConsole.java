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
import org.w3c.ddr.simple.Evidence;
import org.w3c.ddr.simple.PropertyRef;
import org.w3c.ddr.simple.PropertyValue;
import org.w3c.ddr.simple.PropertyValues;
import org.w3c.ddr.simple.Service;
import org.w3c.ddr.simple.ServiceFactory;
import org.w3c.ddr.simple.exception.NameException;

import mobi.openddr.simple.DDRService;
import mobi.openddr.simple.model.ODDRHTTPEvidence;

public class SimpleConsole {
	private static final String CONFIG_FILE = "/oddr.properties";

	private static final Logger log = LogManager.getLogger(SimpleConsole.class);
	private static Service identificationService = null;

	public static void main(String[] args) {
		// double d = 2.567;
		// int i = 3;
		//
		// System.out.println(d);
		// System.out.println(i);
		// System.out.println(i * (int)d);

		init();
		if (args.length>0) {
			identify(args[0]);
		} else {
			identify(null);
		}
	}

	private static void init() {
		log.info("Initialize console");
		Properties initializationProperties = new Properties();

		try {
			initializationProperties.load(SimpleConsole.class.getResourceAsStream(CONFIG_FILE));
			identificationService = ServiceFactory.newService("mobi.openddr.simple.DDRService",
					initializationProperties.getProperty(DDRService.ODDR_VOCABULARY_IRI), initializationProperties);
		} catch (Exception ex) {
			log.error(ex);
			throw new RuntimeException(ex);
		}
	}

	private static void identify(String ua) {
		log.info("Identify");
		PropertyRef vendorRef;
		PropertyRef modelRef;
		PropertyRef displayWidthRef;
		PropertyRef displayHeightRef;
		PropertyRef osVersionRef;
		PropertyRef osRef;
		try {
			vendorRef = identificationService.newPropertyRef("vendor");
			modelRef = identificationService.newPropertyRef("model");
			displayWidthRef = identificationService.newPropertyRef("displayWidth");
			displayHeightRef = identificationService.newPropertyRef("displayHeight");
			osRef = identificationService.newPropertyRef("device_os");
			osVersionRef = identificationService.newPropertyRef("device_os_version");
		} catch (NameException ex) {
			log.error(ex);
			throw new RuntimeException(ex);
		}

		final PropertyRef[] propertyRefs = new PropertyRef[] { vendorRef, modelRef, displayWidthRef, displayHeightRef, osRef, osVersionRef };
		final Evidence e = new ODDRHTTPEvidence();

		if (ua == null || ua.isEmpty()) {
			ua = "Mozilla/5.0 (Linux; U; Android 2.2; en; HTC Aria A6380 Build/ERE27) AppleWebKit/540.13+ (KHTML, like Gecko) Version/3.1 Mobile Safari/524.15.0";
		}
		log.info("User-Agent: " + ua);
		e.put("User-Agent", ua);

		try {
			PropertyValues propertyValues = identificationService.getPropertyValues(e, propertyRefs);
			PropertyValue vendor = propertyValues.getValue(vendorRef);
			PropertyValue model = propertyValues.getValue(modelRef);
			PropertyValue displayWidth = propertyValues.getValue(displayWidthRef);
			PropertyValue displayHeight = propertyValues.getValue(displayHeightRef);
			PropertyValue os = propertyValues.getValue(osRef);
			PropertyValue osVersion = propertyValues.getValue(osVersionRef);
			log.debug("Vendor: " + vendor + "(" + vendor.exists() + ")");
			log.debug("Model: " + model + "(" + model.exists() + ")");
//			log.info("OS: " + osVersion + "(" + osVersion.exists() + ")");

			if (vendor.exists())
				log.info("vendor: {}", vendor.getString());
			if (model.exists())
				log.info("model: {}", model.getString());
			if (displayWidth.exists()) {
				log.debug("W: " + displayWidth + "(" + displayWidth.exists() + ")");
			}
			if (displayHeight.exists()) {
				log.debug("H: " + displayHeight + "(" + displayHeight.exists() + ")");
			}
			if (os.exists()) {
				log.info("OS: {}", os.getString());
			}
			if (osVersion.exists()) {
				log.info("OS Version: {}", osVersion.getString());
			}
		} catch (Exception ex) {
			System.err.println(ex.getLocalizedMessage());
			throw new RuntimeException(ex);
		}
	}
}
