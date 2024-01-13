<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Home Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            justify-content: center;
            align-items: center;
        }
        .title {
            font-size: 24px;
            font-weight: bold;
            margin-bottom: 20px;
            text-align: center;
        }
        .white-container {
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .sign-out-button {
            margin-top: 10px;
            padding: 5px 10px;
            border: none;
            border-radius: 3px;
            background-color: #007bff;
            color: white;
            cursor: pointer;
        }
        #profile {
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
        }
        #profile > div {
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
<div>
    <div>
        <div class="title">Profile</div>
        <#include "menu.ftl">
        <div id="profile" class="white-container">
            <div>${user.fistName +" "+ user.lastName}</div>
            <div>${user.email}</div>
            <br>
            <a href="sign-out" class="sign-out-button">Sign Out</a>
        </div>
    </div>
</div>
</body>
</html>