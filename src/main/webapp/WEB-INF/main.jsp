<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%--<link rel="stylesheet" type="text/css" href="style.css">--%>
    <title>Main Page</title>
</head>
<body>
<center>
    <div id="mystyle" class="myform">
        <form id="login" name="login" method="post" action="${pageContext.request.contextPath}/do/login">
            <h1>Log in</h1>
            <label>Login</label>
            <input type="text" name="login"/>

            <label>Password</label>
            <input type="password" name="password"/>

            <button type="submit">Login</button>
            <div class="spacer"></div>
        </form>

        <form id="register" name="register" method="get" action="${pageContext.request.contextPath}/do/register">
            <h1>Register</h1>
            <button type="submit" > </button>
        </form>
    </div>
</center>
</body>
</html>



