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

var oddrClient={

  DMAP_ENDPOINT:"http://devicemap-vm.apache.org/javaservice.js",

  jsonp:function(s,callback)
  {
    var stime=(new Date()).getTime();
    var ua=encodeURIComponent(s);
    var cb="oddrjc_cb"+Math.floor(Math.random()*1000*1000*1000);

    window[cb]=function(json)
      {
        json.clientTime=(new Date()).getTime()-stime;
        json.method="jsonp";
        if(callback)
          callback(json);
      };

    var st=document.createElement("script");
    st.async=true;
    st.src=this.DMAP_ENDPOINT+"?callback="+cb+"&ua="+ua;
    document.getElementsByTagName("head")[0].appendChild(st);
  },

  xhr:function(s,callback)
  {
    var stime=(new Date()).getTime();
    var ua=encodeURIComponent(s);
    var dxhr=new XMLHttpRequest();

    dxhr.open("GET",this.DMAP_ENDPOINT+"?ua="+ua,true);
    dxhr.onreadystatechange=function()
      {
        if(dxhr!=null && dxhr.readyState==4 && dxhr.status==200)
        {
          var json=JSON.parse(dxhr.responseText);
          json.clientTime=(new Date()).getTime()-stime;
          json.method="xhr";
          if(callback)
            callback(json);
        }
      };

    dxhr.send();
  }
};
