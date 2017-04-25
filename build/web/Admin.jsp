<%-- 
    Document   : Admin
    Created on : Apr 25, 2017, 9:27:56 AM
    Author     : toni1523
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="style.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <div id="container">

            <img id="headerimg" src="Logo.png" width="720" />
            <div class="kysymys">
                <h1>Ylläpito</h1>
            </div>
            
            <form id="vastausformi" action="HaeEhdokas">
                Syötä ehdokkaan id</br>
                <input id="ehdokas_id" type="text" name="ehdokas_id">
                <input id="submitnappi" type="submit" name="haeEhdokas" value="Hae" />                   
            </form></br>
            
            <h3><a href="">Ehdokkaan vastausten poisto</a></h3>
            <h3><a href="">Kysymysten muokkaaminen</h3>
            <small>Kirjaudu ulos</small>

        </div>
    </body>
</html>
