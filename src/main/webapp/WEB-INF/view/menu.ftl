<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Navigation</title>
    <style>
        .navbar {
            background-color: #fff;
            border: 2px solid #007bff;
            border-radius: 5px;
            padding: 10px;
            width: 200px;
            position: fixed;
            left: 0;
            top: 50%;
            transform: translateY(-50%);
        }
        .navbar ul {
            list-style-type: none;
            padding: 0;
            margin: 0;
        }
        .navbar li {
            margin-bottom: 10px;
        }
        .navbar a {
            text-decoration: none;
            color: black;
            font-weight: bold;
            display: block;
        }
        .navbar a:hover {
            color: #0056b3;
        }
    </style>
</head>
<body>
<nav role="navigation" class="navbar">
    <ul class="nav navbar-nav">
        <li><a href="home">Home</a></li>
        <li><a href="my-todos">My ToDos</a></li>
        <a href="sign-out">Sign Out</a>
    </ul>
</nav>
</body>
</html>