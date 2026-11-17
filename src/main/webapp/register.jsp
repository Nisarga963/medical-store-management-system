<!DOCTYPE html>
<html>
<head>
    <title>Create Account</title>
    <style>
        body {
            font-family: Arial;
            background: #f2f5f8;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }

        .container {
            background: white;
            padding: 30px;
            width: 350px;
            border-radius: 10px;
            box-shadow: 0 0 10px #ccc;
        }

        h2 {
            text-align: center;
            color: #173b57;
        }

        input {
            width: 100%;
            padding: 11px;
            margin: 8px 0 15px;
            box-sizing: border-box;
        }

        button {
            width: 100%;
            padding: 12px;
            background: #173b57;
            color: white;
            border: none;
            cursor: pointer;
        }

        a {
            color: #173b57;
        }
    </style>
</head>
<body>

<div class="container">
    <h2>Create Account</h2>

    <form action="register" method="post">

        <label>Full Name</label>
        <input type="text" name="fullName"
               placeholder="Enter your full name" required>

        <label>Email</label>
        <input type="email" name="email"
               placeholder="Enter your email" required>

        <label>Password</label>
        <input type="password" name="password"
               placeholder="Minimum 8 characters"
               minlength="8" required>

        <label>Confirm Password</label>
        <input type="password" name="confirmPassword"
               placeholder="Confirm your password" required>

        <button type="submit">Register</button>

    </form>

    <p>Already have an account?
        <a href="login.jsp">Login</a>
    </p>

    <p><a href="index.html">Back to Home</a></p>
</div>

</body>
</html>