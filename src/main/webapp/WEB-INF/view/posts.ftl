<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Posts</title>
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
        .white-container {
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        table {
            border-collapse: collapse;
            width: 100%;
            margin-bottom: 20px;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="title"><strong>Posts</strong></div>
    <div class="white-container">
        <#include "menu.ftl">
        <table>
            <thead>
            <tr>
                <th>Id_user</th>
                <th>Admin</th>
                <th>Delete</th>
            </tr>
            </thead>
            <tbody>
            <#list posts as post>
                <tr>
                    <td>${post.id_user}</td>
                    <td>${post.isAdmin?string("Yes", "No")}</td>
                </tr>
            </#list>
            </tbody>
        </table>
        <br>
        <button onclick="goToCreatePostPage()">Create a Post</button>
        <br>
        <a href="javascript:history.go(-1)" class="back-button">Back</a>
    </div>
</div>
<script>
    function goToCreatePostPage() {
        window.location.href = "add-member?project_id=${id_todo}"
    }
</script>
</body>
</html>