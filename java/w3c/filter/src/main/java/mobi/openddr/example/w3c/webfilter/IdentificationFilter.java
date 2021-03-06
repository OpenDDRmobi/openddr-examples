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
package mobi.openddr.example.w3c.webfilter;

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

import org.w3c.ddr.simple.Evidence;
import org.w3c.ddr.simple.PropertyRef;
import org.w3c.ddr.simple.PropertyValue;
import org.w3c.ddr.simple.PropertyValues;
import org.w3c.ddr.simple.Service;
import org.w3c.ddr.simple.ServiceFactory;
import org.w3c.ddr.simple.exception.NameException;

import mobi.openddr.simple.DDRService;
import mobi.openddr.simple.model.ODDRHTTPEvidence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Servlet Filter implementation class
 */
public class IdentificationFilter implements Filter {
	private static final Logger log = LogManager.getLogger(IdentificationFilter.class);
	
	private Service identificationService = null;
	
    /**
     * Default constructor. 
     */
    public IdentificationFilter() {
    }
    
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
	    log.info("Initialize filter");
	    Properties initializationProperties = new Properties();
	    ServletContext context = filterConfig.getServletContext();                
	
	    try {
	        initializationProperties.load(context.getResourceAsStream("WEB-INF/classes/oddr.properties"));
	        identificationService = ServiceFactory.newService("mobi.openddr.simple.DDRService", initializationProperties.getProperty(DDRService.ODDR_VOCABULARY_IRI), initializationProperties);
	    } catch (Exception ex) {
	    	log.error(ex);
	        throw new RuntimeException(ex);
	    }
	}
	
	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		log.info("Destroy Filter");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		log.info("Do filter");
		PropertyRef vendorRef;
        PropertyRef modelRef;
        PropertyRef displayWidthRef;
        PropertyRef displayHeightRef;
        PropertyRef osVersionRef;
        try {
            vendorRef = identificationService.newPropertyRef("vendor");
            modelRef = identificationService.newPropertyRef("model");
            displayWidthRef = identificationService.newPropertyRef("displayWidth");
            displayHeightRef = identificationService.newPropertyRef("displayHeight");
            osVersionRef = identificationService.newPropertyRef("device_os_version");
        } catch (NameException ex) {
        	log.error(ex);
            throw new RuntimeException(ex);
        }

        final PropertyRef[] propertyRefs = new PropertyRef[] {vendorRef, modelRef, displayWidthRef, displayHeightRef};
        final Evidence e = new ODDRHTTPEvidence();
        
        String ua=((HttpServletRequest)request).getParameter("ua");
        if(ua==null || ua.isEmpty()) {
          ua=((HttpServletRequest)request).getHeader("User-Agent");
          log.info("User-Agent: " + ua);
        } else {
        	log.info("User-Agent (*): " + ua);
        }
        e.put("User-Agent", ua);

        try {
            PropertyValues propertyValues = identificationService.getPropertyValues(e, propertyRefs);
            PropertyValue vendor = propertyValues.getValue(vendorRef);
            PropertyValue model = propertyValues.getValue(modelRef);
            PropertyValue displayWidth = propertyValues.getValue(displayWidthRef);
            PropertyValue displayHeight = propertyValues.getValue(displayHeightRef);
            PropertyValue osVersion = propertyValues.getValue(osVersionRef);
            log.debug("Vendor: " + vendor + "(" + vendor.exists() + ")");
            log.debug("Model: " + model + "(" + model.exists() + ")");
//            log.info("OS: " + osVersion + "(" + osVersion.exists() + ")");

            if (vendor.exists())
                ((HttpServletRequest)request).setAttribute("vendor", vendor.getString());
            if (model.exists())
                ((HttpServletRequest)request).setAttribute("model", model.getString());
            if (displayWidth.exists()) {
            	log.debug("W: " + displayWidth + "(" + displayWidth.exists() + ")");
                request.setAttribute("displayWidth", displayWidth.getInteger());
            }
            if (displayHeight.exists()) {
            	log.debug("H: " + displayHeight + "(" + displayHeight.exists() + ")");
            	request.setAttribute("displayHeight", displayHeight.getInteger());
            }
        } catch (Exception ex) {
            System.err.println(ex.getLocalizedMessage());
            throw new RuntimeException(ex);
        }
        chain.doFilter(request, response);
	}
}
