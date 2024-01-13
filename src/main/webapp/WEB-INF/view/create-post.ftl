<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create Post</title>
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
        input[type="text"], input[type="email"], select {
            margin-bottom: 10px;
            padding: 8px;
            border-radius: 3px;
            border: 1px solid #ccc;
            width: 100%;
            box-sizing: border-box;
        }
        .add-button {
            padding: 8px 16px;
            border: none;
            border-radius: 3px;
            background-color: #007bff;
            color: white;
            cursor: pointer;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="title"><strong>Create Post in Todo</strong></div>
    <div class="white-container">
        <#include "menu.ftl">
        <form action="create-post" method="post">
            <#if errorMessage??>
                <div class="error_message">${errorMessage}</div>
            </#if>
            <input type="hidden" id="id_todo" name="id_todo" value="${id_todo}">
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required><br>

            <label for="is_admin">Appoint as Admin?</label>
            <select id="is_admin" name="is_admin">
                <option value="true">Yes</option>
                <option value="false">No</option>
            </select><br>
            <button type="submit" class="add-button">Create Post</button>
            <br>
            <button class="back-button" onclick="goToMembersPage()">Back</button>
        </form>
    </div>
</div>
<script>
    function goToMembersPage(){
        window.location.href ='posts?id_todo=${id_todo}';
    }
</script>
</body>
</html>