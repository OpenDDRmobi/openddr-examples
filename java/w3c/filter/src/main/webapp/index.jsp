<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>OpenDDR Simple DDR Sample</title>
    </head>
    <body>
    	<h1>OpenDDR W3C DDR Simple Example</h1>
        <h2><%= (request.getAttribute("vendor") != null ? request.getAttribute("vendor") : "No vendor") %>
        <%= (request.getAttribute("model") != null ? request.getAttribute("model") : "No model") %> resolution: <%= request.getAttribute("displayWidth") %> x <%= request.getAttribute("displayHeight") %></h2>
        
        <form action="/dmap-ddr-filter" method="GET">
ua <input type="text" name="ua">
<input type="submit" value="Submit">
</form>
    </body>
</html>