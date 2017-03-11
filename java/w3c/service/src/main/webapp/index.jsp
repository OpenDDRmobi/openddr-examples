<!DOCTYPE html>
<html>
<head>
<title>OpenDDR Java Service</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<script src="js/oddrclient.js"></script>
</head>
<body onload="main();">
<h1>OpenDDR W3C DDR Simple Java Service</h1>
User-Agent: <input type="text" name="useragent" id="useragent" size="100" value="">
<input type="button" name="submit" value="Submit!" onclick="doSubmit()">
<br>
<br>
<div id="uaresults"></div>
<br>
<br>
<a href="http://devicemap.apache.org/">OpenDDR</a> W3C Service<!-- <a href="javaservice.html">HTML</a> <a href="javaservice.js">JSON</a>--><br>
<script>

function main()
{
  document.getElementById("useragent").value=navigator.userAgent;
  submit();
}

function renderResults(json)
{
  var r="";
  r+="Device: "+json.vendor+" "+json.model+"<br>";
  r+="Res: "+json.displayWidth+"x"+json.displayHeight+"<br>";
  r+="Input: "+json.inputDevices+"<br>";
  r+="Tablet: "+json.tablet+"<br>";
  r+="Wireless: "+json.wireless+"<br>";
  r+="ID: "+json.id+"<br>";
  r+="Time: "+json.clientTime+"ms<br>";
  r+="Method: "+json.method+"<br>";
  document.getElementById("uaresults").innerHTML=r;
}

function doSubmit()
{
  alert('Click')
  oddrClient.jsonp(document.getElementById("useragent").value,renderResults);
}

</script>
</body>
</html>
