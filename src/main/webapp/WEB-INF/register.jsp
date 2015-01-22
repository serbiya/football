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
        <form id="form" name="form" method="post" action="${pageContext.request.contextPath}/do/register">
            <h1>Registration</h1>

            <label>Login</label>
            <input type="text" name="login"/>
            <br/><br/>
            <label>Password</label>
            <input type="password" name="password"/>
            <br/><br/>

            <label>Name</label>
            <input type="text" name="name"/>
            <br/><br/>
            <label>Last name</label>
            <input type="text" name="lastName"/>
            <br/><br/>
            <label>Email</label>
            <input type="text" name="email"/>
            <br/><br/>

            <button type="submit">Register</button>

            <div class="spacer"></div>
        </form>
    </div>
</center>
</body>
</html>
