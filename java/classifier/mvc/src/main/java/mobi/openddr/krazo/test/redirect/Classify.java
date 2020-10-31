/*
 * Copyright (c) 2011-2020 OpenDDR LLC and others. All rights reserved.
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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import mobi.openddr.classifier.Classifier;
import mobi.openddr.classifier.loader.LoaderOption;
import mobi.openddr.classifier.model.Device;

/**
 * @author Werner Keil
 *
 */
public class Classify {
	private static final String DEFAULT_URL = "http://dl.bintray.com/openddr/ddr/1.33/";
	
	private static final Logger log = LogManager.getLogger(Classify.class);
    private Classifier classifier;
    
    public synchronized void init() throws Exception {
        long start = System.nanoTime();        
        //classifier.initDeviceData(LoaderOption.JAR);
        //classifier.initDeviceData(
        // "http://openddr.mobi/data/snapshot/"
        classifier = Classifier.builder().with(LoaderOption.URL, DEFAULT_URL).build();
        long diff = (System.nanoTime() - start) / 1000;
        log.info("OpenDDR Classifier loaded " + classifier.getDeviceCount() + " devices and " + classifier.getPatternCount() + " patterns in " + diff + "ms");
    }
    
    public Device classify(String text) {
        return classifier.classifyDevice(text);
    }
    
    public static boolean isWireless(Device device) {
        //log.info("Device: " + device);
        return "true".equals(device.getAttribute("is_wireless_device"));
    }
    
    public static boolean isTablet(Device device) {        
    	return "true".equals(device.getAttribute("is_tablet")); 
    }
}
