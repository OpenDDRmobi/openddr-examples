/*
 * Copyright (c) 2011-2021 OpenDDR LLC and others. All rights reserved.
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
package mobi.openddr.krazo.test.redirect;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import mobi.openddr.classifier.Classifier;
import mobi.openddr.classifier.loader.LoaderOption;
import mobi.openddr.classifier.model.Device;

/**
 * @author Werner Keil
 * @author Ivar Grimstad
 */
@ApplicationScoped
class ClassifyService {
	// TODO make this configurable
	private static final String DEFAULT_URL = "http://openddr.mobi/data/latest/";
	
	private static final Logger log = LogManager.getLogger(ClassifyService.class);
    private Classifier classifier;

    @PostConstruct
    private synchronized void init() {
        log.info("initializing...");
        long start = System.nanoTime();

        classifier = Classifier.builder().with(LoaderOption.URL, DEFAULT_URL).build();
        long diff = (System.nanoTime() - start) / 1000;
        log.info("OpenDDR Classifier loaded " + classifier.getDeviceCount() + " devices and " + classifier.getPatternCount() + " patterns in " + diff + "ms");
    }
    
    Device classify(String text) {
        return classifier.classifyDevice(text);
    }
    
    boolean isWireless(Device device) {
        return "true".equals(device.getProperty("is_wireless_device"));
    }
    
    boolean isTablet(Device device) {
    	return "true".equals(device.getProperty("is_tablet")); 
    }
}
