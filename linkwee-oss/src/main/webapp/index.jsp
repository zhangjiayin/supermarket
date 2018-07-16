<%-- <%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
response.sendRedirect("rest/index");
%> --%>
<jsp:forward page="rest/index"/>