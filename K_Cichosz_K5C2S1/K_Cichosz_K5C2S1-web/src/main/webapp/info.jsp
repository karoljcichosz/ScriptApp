<%-- 
    Document   : info
    Created on : 2018-01-30, 20:31:36
    Author     : Kahol
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>info</title>
    </head>
    <body>
        <jsp:useBean id="sb" class="projekt.SBean" scope="session" />
        <% 
                sb.getScript(request.getParameter("sname"));
                String name=sb.getSName(request.getParameter("sname"));
                String date=sb.getSDate(name);
            
        
        %>
        <h1>Nazwa: <%= name %></h1>
        Data dodania: <%= date %>
        Opis: <%= sb.getSDesc(name) %>
        
             
             
            <form action="app/file/download/<%=name%>/" method="get"> 
                <input type="submit" value="Pobierz"/> 
            </form>
            
            <form action="/K_Cichosz_K5C2S1-web/" method="get"> 
                <input type="submit" value="Powrót"/> 
            </form>
            
            
            
    </body>
</html>
