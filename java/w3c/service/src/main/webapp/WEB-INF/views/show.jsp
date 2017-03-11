<%@ page trimDirectiveWhitespaces="true" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<body>
<h1>DeviceMap Java Service</h1>   
<pre>
user agent:     ${dmapUA}
vendor:         ${dmapVendor}
model:          ${dmapModel}
displayWidth:   ${dmapDisplayWidth}
displayHeight:  ${dmapDisplayHeight}
inputDevices:   ${dmapInputDevices}
id:             ${dmapId}
tablet:         ${dmapTablet}
wireless:       ${dmapWireless}
</pre>
</body>
</html>
