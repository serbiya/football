<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%--<link rel="stylesheet" type="text/css" href="WEB-INF/style.css">--%>
    <title>Main Page</title>
</head>
<body>
<center>
    <div>
        <form id="login" name="login" method="post" action="${pageContext.request.contextPath}/do/login">
            <h1>Log in</h1>
            <label>Login</label>
            <input type="text" name="login"/>

            <label>Password</label>
            <input type="password" name="password"/>

            <button type="submit">Login</button>
        </form>

        <form id="register" name="register" method="get" action="${pageContext.request.contextPath}/do/register">
            <h1>Register</h1>
            <button type="submit">Register</button>
        </form>
    </div>
</center>
</body>
</html>



