<%@ page trimDirectiveWhitespaces="true" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<body>
<h1>OpenDDR Java Service</h1>   
<pre>
user agent:     ${oddrUA}
vendor:         ${oddrVendor}
model:          ${oddrModel}
displayWidth:   ${oddrDisplayWidth}
displayHeight:  ${oddrDisplayHeight}
inputDevices:   ${oddrInputDevices}
id:             ${oddrId}
tablet:         ${oddrTablet}
wireless:       ${oddrWireless}
</pre>
</body>
</html>
