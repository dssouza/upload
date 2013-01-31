<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="br.com.teste.upload.bean.Notification"%>
<%
            Notification _sN = new Notification();
            if (session.getAttribute("error") != null) {
                _sN = (Notification) session.getAttribute("error");
            }

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>File Upload Example</title>
    </head>
    <body>
        <center>
            <div class="signal_box" style="width: 400px;height: 150px">
                <div class="head">File Upload - javaQuery.com</div>
                <form action="uploadfile" method="post"  enctype="multipart/form-data">
                <input type="file" name="myFile"/><br/>
                <input type="submit" value="Upload it"/>
                </form>
                <br/>
                <%=_sN.getError()%>
                <%_sN.setError("");%>
            </div>
        </center>
    </body>
</html>