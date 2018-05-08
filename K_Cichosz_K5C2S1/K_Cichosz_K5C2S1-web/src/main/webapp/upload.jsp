<%-- 
    Document   : add
    Created on : 2018-01-30, 20:27:15
    Author     : Kahol
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>KC dodaj skrypt</title>
    </head>
    <body>
        <form action="app/file/upload" method="post" enctype="multipart/form-data">

        Opis skryptu: <input id="description" type="text" name="description" size="300">
        <p>
            Wybierz plik : <input type="file" name="file" size="40" />
        </p>

        <input type="submit" value="Wyślij" />
    </form>
    </body>
</html>
