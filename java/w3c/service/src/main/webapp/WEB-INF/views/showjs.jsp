<%@ page trimDirectiveWhitespaces="true" %>
<%@ page contentType="application/json; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
{
  "ua":"${oddrUA}",
  "vendor":"${oddrVendor}",
  "model":"${oddrModel}",
  "displayWidth":"${oddrDisplayWidth}",
  "displayHeight":"${oddrDisplayHeight}",
  "inputDevices":"${oddrInputDevices}",
  "id":"${oddrId}",
  "tablet":"${oddrTablet}",
  "wireless":"${oddrWireless}"
}
