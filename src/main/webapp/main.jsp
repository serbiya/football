<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Main Page</title>
</head>
<body>
<center>
    <div id="mystyle" class="myform">
        <h1>Main Page</h1>
        <form id="login-form" name="login-form" method="post" action="/toLogin">
            <button type="login">Login</button>
            <div class="spacer"></div>
        </form>
        <form id="reg-form" name="reg-form" method="post" action="/toRegister">
            <button type="register">Register</button>
            <div class="spacer"></div>
        </form>
    </div>
</center>
</body>
</html>


