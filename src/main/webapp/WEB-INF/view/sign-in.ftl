<!DOCTYPE html>
<html lang="en">
<link rel="stylesheet" href="/resources/css/style.css">
<head>
    <meta charset="UTF-8">
    <title>Sign In</title>
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
<form class="container" method="post">
    <h1>Sign In</h1>
    <label>Email
        <input class="form-control" name="email" type="email" placeholder="Email">
    </label>
    <label>Password
        <input class="form-control" name="password" type="password" placeholder="Password">
    </label>
    <#if errorMessage??>
        <div class="error_message">${errorMessage}</div>
    </#if>
    <input class="login-button" type="submit" value="Sign In">
    <br> <br> <br>
    <div>Don't have an account?</div>
    <a href="sign-up">Sign Up</a>

</form>
</body>
</html>