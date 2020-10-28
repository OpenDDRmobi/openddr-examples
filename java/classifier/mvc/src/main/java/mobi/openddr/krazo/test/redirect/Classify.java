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
package mobi.openddr.krazo.test.redirect;

import org.apache.log4j.Logger;

import mobi.openddr.classifier.Classifier;
import mobi.openddr.classifier.loader.LoaderOption;
import mobi.openddr.classifier.model.Device;

/**
 * @author Werner
 *
 */
public class Classify {
    private static final Logger log = Logger.getLogger(Classify.class);
    private Classifier cl;
    
    public synchronized void init() throws Exception {
        long start = System.nanoTime();
        cl = new Classifier();
        cl.initDeviceData(LoaderOption.JAR);
//        cl.initDeviceData(LoaderOption.URL);
        long diff = (System.nanoTime() - start) / 1000;
        log.info("OpenDDR Classifier loaded " + cl.getDeviceCount() + " devices and " + cl.getPatternCount() + " patterns in " + diff + "ms");
    }
    
    public Device classify(String text) {
        return cl.classifyDevice(text);
    }
}
