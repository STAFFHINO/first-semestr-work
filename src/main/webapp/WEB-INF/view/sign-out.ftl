<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Sign Out</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .container {
            text-align: center;
        }
        .title {
            font-size: 24px;
            font-weight: bold;
            margin-bottom: 20px;
        }
        .message {
            margin-bottom: 15px;
        }
        .sign-out-button {
            padding: 10px 20px;
            background-color: #007bff;
            border: none;
            border-radius: 3px;
            color: #fff;
            font-weight: bold;
            cursor: pointer;
            text-decoration: none;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="title"><strong>Sign Out</strong></div>
    <div class="message">Are you sure you want to sign out?</div>
    <form action="sign-out" method="post">
        <button type="submit" class="sign-out-button">Sign Out</button>
        <br>
        <a href="javascript:history.go(-1)" class="back-button">Back</a>
    </form>
</div>
</body>
</html>
