<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.*" %>
<%@ page import="java.net.URL" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>WebJars Test</title>
</head>
<body>
    <h2>WebJars Resources:</h2>
    <%
    ServletContext context = request.getServletContext();
    Set<String> paths = context.getResourcePaths("/webjars");
    if (paths != null) {
        for (String path : paths) {
            out.println(path + "<br>");
            Set<String> subPaths = context.getResourcePaths(path);
            if (subPaths != null) {
                for (String subPath : subPaths) {
                    out.println("&nbsp;&nbsp;" + subPath + "<br>");
                }
            }
        }
    } else {
        out.println("No resources found in /webjars");
    }
    %>
</body>
</html>