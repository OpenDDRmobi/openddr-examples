/*
 * Copyright (c) 2014-2015 Oracle and/or its affiliates. All rights reserved.
 * Copyright (c) 2018, 2019 Eclipse Krazo committers and contributors
 * Copyright (c) 2020 OpenDDR committers and contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * SPDX-License-Identifier: Apache-2.0
 */
package mobi.openddr.krazo.test.redirect;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.mvc.event.ControllerRedirectEvent;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import mobi.openddr.classifier.model.Device;

/**
 * ClassifyController test.
 *
 * @author Santiago Pericas-Geertsen
 * @author Werner Keil
 * @author Ivar Grimstad
 */
@Path("redirect")
@Controller
@ApplicationScoped
public class ClassifyController {
	private static final Logger log = LogManager.getLogger(ClassifyController.class);
	
    /**
     * Inject instance of Classify in request scope.
     */
    @Inject
    private Classify cl;
	
    private String ua;
    
    private Device device;
    
    private boolean eventReceived;
    
    private boolean isMobile;
    private boolean isTablet;

    @GET
    @Path("classify")
    public Response getResponse2(@HeaderParam("user-agent") String userAgent) {
        eventReceived = false;
        reset();
        this.ua = userAgent;
        log.info("initializing...");
        try {
			cl.init();
		} catch (Exception e) { 
			log.error("Error", e);
		}
        log.info("classify() ua: '" + ua + "'");
        
        this.device = cl.classify(ua);
        //log.info("Device: " + device);
        if (Classify.isWireless(device)) {
        	if(Classify.isTablet(device)) {
            	isTablet = true;
            } else {
            	isMobile = true;
            }
        }        
        return Response.status(Response.Status.FOUND)
                .header("Location", "redirect/here")
                .build();
    }

    @GET
    @Path("here")
    @Produces("text/html")
    public String getSub() {
    	if (eventReceived) {    	
    		if (isMobile) {
    			return "mobile.jsp";
    		} else if (isTablet) {
    			return "tablet.jsp";
    		} else {
    			return "redirect.jsp";
    		}
    	} else {
    		return "error.jsp";
    	}
    }

    public void beforeControllerEvent(@Observes ControllerRedirectEvent event) {
        eventReceived = true;
    }
    
    private void reset() {
    	isMobile = false;
    	isTablet = false;
    }
}
