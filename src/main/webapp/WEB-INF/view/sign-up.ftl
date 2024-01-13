<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Sign Up</title>
    <style>
        body {
            text-align: center;
        }
        h1 {
            font-weight: bold;
        }
        label {
            display: block;
            margin-bottom: 10px;
        }
        .form-control {
            width: 200px;
            margin-bottom: 10px;
            padding: 5px;
            border-radius: 3px;
            border: 1px solid #ccc;
        }
        .error_message {
            color: red;
            margin-top: 10px;
        }
        .login-button {
            margin-top: 10px;
            padding: 5px 10px;
            border: none;
            border-radius: 3px;
            background-color: #007bff;
            color: white;
            cursor: pointer;
        }
        .login-button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<form method="post">
    <h1>Sign Up</h1>
    <label>First name
        <input class="form-control" name="first_name" type="text">
    </label>
    <label>Last name
        <input class="form-control" name="last_name" type="text">
    </label>
    <label>Email
        <input class="form-control" name="email" type="email">
    </label>
    <label>Password
        <input class="form-control" name="password" type="password">
    </label>
    <#if errorMessage??>
        <div class="error_message">${errorMessage}</div>
    </#if>
    <input class="login-button" type="submit" value="Sign Up">
    <br> <br> <br>
    <div>Already have an account?</div>
    <a href="sign-in">Sign In</a>
</form>

</body>
</html>