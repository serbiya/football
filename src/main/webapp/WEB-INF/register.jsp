<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Registration Page</title>
</head>
<body>
<center>
    <div id="mystyle" class="myform">
        <form id="form" name="form" method="post" action="register">
            <h1>Registration</h1>

            <label>Login</label>
            <input type="text" name="login"/>
            <label>Password</label>
            <input type="password" name="password"/>

            <button type="submit">Register</button>

            <div class="spacer"></div>
        </form>
    </div>
</center>
</body>
</html>
