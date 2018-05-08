<%-- 
    Document   : index
    Created on : 2018-01-30, 19:15:02
    Author     : Kahol
--%>
<%@page import="projekt.script"%>
<%@page import="java.util.LinkedList"%>
<%@page import="javax.ejb.EJB"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

    

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>KC Skrypty</title>
    </head>
    <body>
        <jsp:useBean id="sb" class="projekt.SBean" scope="session" /> 
        <h1>Lista skryptów</h1>
        <table border="1">
                    <tr>
                        <th>nazwa</th>
                        <th>data dodania</th>
                        <th>usuń</th>
                    </tr>
                    <%
                        LinkedList<script> list =sb.getList();
                       for (script s : list) {

                                      %>
                           <tr><td><a href="info.jsp?sname=<%=s.getname() %>"><%=s.getname() %></a></td>
                            <td><%=s.getdate() %></td>
                           <td><form action="app/file/delete/<%=s.getname()%>" method="post"> 
                <input type="submit" value="Usuń"> 
                               </form></td></tr>
                                <%        }           %>
                        </table>
                        <% 
                    %>
        </table>
        <form action="upload.jsp">                    
            <input type="submit" value="dodaj skrypt">
            
        </form>
    </body>
</html>
