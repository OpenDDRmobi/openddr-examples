/*
   Licensed to the Apache Software Foundation (ASF) under one
   or more contributor license agreements.  See the NOTICE file
   distributed with this work for additional information
   regarding copyright ownership.  The ASF licenses this file
   to you under the Apache License, Version 2.0 (the
   "License"); you may not use this file except in compliance
   with the License.  You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing,
   software distributed under the License is distributed on an
   "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
   KIND, either express or implied.  See the License for the
   specific language governing permissions and limitations
   under the License.
 */

package org.apache.devicemap.simpleddr.web.filter;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.devicemap.simpleddr.DDRService;
import org.apache.devicemap.simpleddr.model.ODDRHTTPEvidence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.ddr.simple.Evidence;
import org.w3c.ddr.simple.PropertyRef;
import org.w3c.ddr.simple.PropertyValue;
import org.w3c.ddr.simple.PropertyValues;
import org.w3c.ddr.simple.Service;
import org.w3c.ddr.simple.ServiceFactory;
import org.w3c.ddr.simple.exception.NameException;

public class IdentificationFilter implements Filter {

    private static final Logger log = LogManager.getLogger(IdentificationFilter.class);

    private Service identificationService = null;

    public void init(FilterConfig filterConfig) throws ServletException {
	    log.info("Initialize filter");
	    Properties initializationProperties = new Properties();
	    ServletContext context = filterConfig.getServletContext();                

	    try {
	        initializationProperties.load(context.getResourceAsStream("/WEB-INF/classes/oddr.properties"));
	        identificationService = ServiceFactory.newService("org.apache.devicemap.simpleddr.DDRService", initializationProperties.getProperty(DDRService.ODDR_VOCABULARY_IRI), initializationProperties);
	
	    } catch (Exception ex) {
	        throw new RuntimeException(ex);
	    }
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    	log.info("Do filter");
    	PropertyRef vendorRef;
	    PropertyRef modelRef;
	    PropertyRef displayWidthRef;
	    PropertyRef displayHeightRef;
	    PropertyRef inputDevicesRef;
	    PropertyRef idRef;
	    PropertyRef tabletRef;
	    PropertyRef wirelessRef;
	
	    try {
	        vendorRef = identificationService.newPropertyRef("vendor");
	        modelRef = identificationService.newPropertyRef("model");
	        displayWidthRef = identificationService.newPropertyRef("displayWidth");
	        displayHeightRef = identificationService.newPropertyRef("displayHeight");
	        inputDevicesRef = identificationService.newPropertyRef("inputDevices");
	        idRef = identificationService.newPropertyRef("id");
	        tabletRef = identificationService.newPropertyRef("is_tablet");
	        wirelessRef = identificationService.newPropertyRef("is_wireless_device");
	
	    } catch (NameException ex) {
	        throw new RuntimeException(ex);
	    }
	
	    PropertyRef[] propertyRefs = new PropertyRef[] {vendorRef, modelRef, displayWidthRef, displayHeightRef, inputDevicesRef,
	                                                    idRef, tabletRef, wirelessRef};
	    Evidence e = new ODDRHTTPEvidence();
	
	    String ua=((HttpServletRequest)request).getParameter("ua");
	    if(ua==null || ua.isEmpty()) {
	      ua=((HttpServletRequest)request).getHeader("User-Agent");
	    }
	
	   ((HttpServletRequest)request).setAttribute("dmapUA", ua);
	
	    e.put("User-Agent", ua);
	
	    try {
	        PropertyValues propertyValues = identificationService.getPropertyValues(e, propertyRefs);
	        PropertyValue vendor = propertyValues.getValue(vendorRef);
	        PropertyValue model = propertyValues.getValue(modelRef);
	        PropertyValue displayWidth = propertyValues.getValue(displayWidthRef);
	        PropertyValue displayHeight = propertyValues.getValue(displayHeightRef);
	        PropertyValue inputDevices = propertyValues.getValue(inputDevicesRef);
	        PropertyValue id = propertyValues.getValue(idRef);
	        PropertyValue tablet = propertyValues.getValue(tabletRef);
	        PropertyValue wireless = propertyValues.getValue(wirelessRef);
	
	        if (vendor!=null && vendor.exists()) {
	            ((HttpServletRequest)request).setAttribute("dmapVendor", vendor.getString());
	        }
	        if (model!=null && model.exists()) {
	            ((HttpServletRequest)request).setAttribute("dmapModel", model.getString());
	        }
	        if (displayWidth!=null && displayWidth.exists()) {
	            ((HttpServletRequest)request).setAttribute("dmapDisplayWidth", displayWidth.getInteger());
	        }
	        if (displayWidth!=null && displayHeight.exists()) {
	            ((HttpServletRequest)request).setAttribute("dmapDisplayHeight", displayHeight.getInteger());
	        }
	        if (inputDevices!=null && inputDevices.exists()) {
	            ((HttpServletRequest)request).setAttribute("dmapInputDevices", inputDevices.getString());
	        }
	        if (id!=null && id.exists()) {
	            ((HttpServletRequest)request).setAttribute("dmapId", id.getString());
	        }
	        if (tablet!=null && tablet.exists()) {
	            ((HttpServletRequest)request).setAttribute("dmapTablet", tablet.getString());
	        }
	        if (wireless!=null && wireless.exists()) {
	            ((HttpServletRequest)request).setAttribute("dmapWireless", wireless.getString());
	        }
	
	    } catch (Exception ex) {
	        throw new RuntimeException(ex);
	    }
	
	    chain.doFilter(request, response);
    }

    public void destroy() {
      log.debug("Destroy Filter");
    }
}
