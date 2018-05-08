<%-- 
    Document   : del
    Created on : 2018-01-30, 20:47:40
    Author     : Kahol
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Usuwanie</title>
    </head>
    <body>
            <jsp:useBean id="sb" class="projekt.SBean" scope="session" />
            <% sb.delete(request.getParameter("sname")); %>
            

            <%
                String redirectURL = "index1.jsp";
                response.sendRedirect(redirectURL);
            %>


    </body>
</html>
