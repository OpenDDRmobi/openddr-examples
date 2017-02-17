<%@ page session="false" %>
<%--
    http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable 
	law or agreed to in writing, software distributed under the License is distributed 
	on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either 
	express or implied. See the License for the specific language governing permissions 
	and limitations under the License. -->
--%> 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>OpenDDR Spring Example</title>
</head>
<body>
<pre>
<h1>OpenDDR Spring Example</h1>
<a href="version.jsp">version</a>

<b>classify</b>
GET /classify
Classify a device using a user-agent header or param
-params: ua [optional]
<form action="classify" method="GET">
ua <input type="text" name="ua">
<input type="submit" value="Submit">
</form>
</pre>
</body>
</html>
