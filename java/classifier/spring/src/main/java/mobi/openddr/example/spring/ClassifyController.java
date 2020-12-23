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
package mobi.openddr.example.spring;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import mobi.openddr.classifier.model.Device;

/**
 * @author Werner Keil
 * @version 1.0
 */
@Controller
@RequestMapping("/")
public class ClassifyController {
	private static final Logger log = LogManager.getLogger(ClassifyController.class);

    @Autowired
    private Classify classifier;

    @RequestMapping(value = "classify", method = RequestMethod.GET)
    public String classify(HttpServletRequest request, HttpServletResponse response, ModelMap model,
            @RequestParam(value = "ua", required = false) String ua) throws Exception {

        log.info("classify() ua: '" + ua + "'");

        if (ua == null || ua.isEmpty()) {
            ua = request.getHeader("User-Agent");
        }

        long start = System.nanoTime();

        Device device = classifier.classify(ua);

        Long diff = (System.nanoTime() - start) / 1000;

        model.addAttribute("ua", ua);
        model.addAttribute("device", device);
        model.addAttribute("time", diff);

        return "device";
    }
}
