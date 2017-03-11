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

package org.apache.devicemap.simpleddr.web.controller;
 
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/service")
public class Service
{
    private static final Logger log = LogManager.getLogger(Service.class);

    @RequestMapping(method=RequestMethod.GET)
    public String root(ModelMap model)
    {
        log.debug("root() called, redirecting to /");

        return "redirect:/service/";
    }

    @RequestMapping(value="/",method=RequestMethod.GET)
    public String showService(ModelMap model)
    {
        log.debug("showService() called");

        model.addAttribute("service","true");

        return "show";
    }

    @RequestMapping(value="/service.js",method=RequestMethod.GET)
    public String showServiceJS(@RequestParam(value="callback",defaultValue="") String callback, ModelMap model)
    {
        log.debug("showServiceJS() called");

        model.addAttribute("service","true");
        model.addAttribute("servicejs","true");

        if(!callback.isEmpty()) {
            model.addAttribute("callback",callback);
            return "showjscb";
        }

        return "showjs";
    }
}
