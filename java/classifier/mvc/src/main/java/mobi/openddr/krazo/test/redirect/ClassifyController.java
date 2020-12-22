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


import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import mobi.openddr.classifier.model.Device;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * ClassifyController test.
 *
 * @author Santiago Pericas-Geertsen
 * @author Werner Keil
 * @author Ivar Grimstad
 */
@Path("redirect")
@Controller
@RequestScoped
public class ClassifyController {
    private static final Logger log = LogManager.getLogger(ClassifyController.class);

    @Inject
    private DeviceInfo deviceInfo;

    /**
     * Inject instance of ClassifyService in request scope.
     */
    @Inject
    private ClassifyService cl;

    @GET
    @Path("classify")
    public Response getResponse(@HeaderParam("user-agent") String userAgent) {

        log.info("classify() ua: '" + userAgent + "'");
        Device device = cl.classify(userAgent);

        if (cl.isWireless(device)) {
            deviceInfo.setTablet(cl.isTablet(device));
        }
        return Response.ok("redirect:redirect/here")
                .build();
    }

    @GET
    @Path("here")
    @Produces("text/html")
    public String getSub() {

        if (deviceInfo.isMobile()) {
            return "mobile.jsp";
        } else if (deviceInfo.isTablet()) {
            return "tablet.jsp";
        } else {
            return "redirect.jsp";
        }
    }
}
