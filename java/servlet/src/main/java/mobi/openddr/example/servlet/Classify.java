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
package mobi.openddr.example.servlet;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mobi.openddr.classifier.ClassifierBuilder;
import mobi.openddr.classifier.ClassifierFactory;
import mobi.openddr.classifier.loader.LoaderOption;
import mobi.openddr.classifier.model.Device;

/**
 *
 * @author Reza Naghibi
 * @author Werner Keil
 */
public class Classify extends HttpServlet {
    
    /**
     * 
     */
    private static final long serialVersionUID = -5231604595735057872L;
    
    private static final String DEFAULT_URL = "http://dl.bintray.com/openddr/ddr/";

    @Override
    protected void doGet(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	String ua = request.getParameter("ua");

	if (ua == null || ua.isEmpty()) {
	    ua = request.getHeader("User-Agent");
	}

	long start = System.nanoTime();
	final Device device = ClassifierFactory.getClient(LoaderOption.URL, DEFAULT_URL)
		.classifyDevice(ua);
	long diff = (System.nanoTime() - start) / 1000;

	final StringBuilder sb = new StringBuilder();
	sb.append("\n{\n\"success\":true,\n\"user-agent\":\"").append(ua)
		.append("\",\n\"time_microseconds\":");
	sb.append(diff).append(",\n\"result\":").append(device.toString())
		.append("\n}");

	response.setHeader("Content-Type", "application/json");

	Writer writer = response.getWriter();
	writer.write(sb.toString());
	writer.flush();
    }
}
