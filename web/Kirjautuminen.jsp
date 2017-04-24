<%-- 
    Document   : Kirjautuminen
    Created on : Apr 24, 2017, 2:26:12 PM
    Author     : karoliina1506
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Diginide vaalikone</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="style.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <div id="container">

            <img id="headerimg" src="Logo.png" width="720" />
            <div class="kysymys">
                <h1>Vaalikone</h1>
            </div>

            <form id="vastausformi" action="Kirjaudu">
                Syötä salasana</br>
                <input id="salasana" type="text" name="salasana">
                <input id="submitnappi" type="submit" name="kirjaudu" value="Kirjaudu" />                   
            </form></br>

        </div>
    </body>
</html>
