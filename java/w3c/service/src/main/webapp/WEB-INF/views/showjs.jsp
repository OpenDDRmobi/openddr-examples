<%@ page trimDirectiveWhitespaces="true" %>
<%@ page contentType="application/json; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
{
  "ua":"${dmapUA}",
  "vendor":"${dmapVendor}",
  "model":"${dmapModel}",
  "displayWidth":"${dmapDisplayWidth}",
  "displayHeight":"${dmapDisplayHeight}",
  "inputDevices":"${dmapInputDevices}",
  "id":"${dmapId}",
  "tablet":"${dmapTablet}",
  "wireless":"${dmapWireless}"
}
