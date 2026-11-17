
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login - Medical Store</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f4f6f9;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            margin: 0;
        }

        .container {
            background: white;
            padding: 30px;
            width: 350px;
            border-radius: 10px;
            box-shadow: 0 3px 12px rgba(0,0,0,0.1);
        }

        h2 {
            text-align: center;
            color: #173b57;
        }

        input {
            width: 100%;
            padding: 12px;
            margin: 10px 0;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        button {
            width: 100%;
            padding: 12px;
            background: #173b57;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        p {
            text-align: center;
        }

        a {
            color: #2878b5;
        }

        .message {
            text-align: center;
            color: green;
        }

        .error {
            text-align: center;
            color: red;
        }
    </style>
</head>
<body>

<div class="container">
    <h2>Medical Store Login</h2>

    <%
        String registered = request.getParameter("registered");
        String error = request.getParameter("error");

        if ("success".equals(registered)) {
    %>
        <p class="message">Registration successful! Please login.</p>
    <%
        }

        if ("invalid".equals(error)) {
    %>
        <p class="error">Invalid email or password.</p>
    <%
        } else if ("empty".equals(error)) {
    %>
        <p class="error">Please enter email and password.</p>
    <%
        }
    %>

    <form action="login" method="post">
        <input type="email" name="email"
               placeholder="Email Address" required>

        <input type="password" name="password"
               placeholder="Password" required>

        <button type="submit">Login</button>
    </form>

    <p>Don't have an account?
       <a href="register.jsp">Register</a>
    </p>

    <p><a href="index.html">Back to Home</a></p>
</div>

</body>
</html>
