<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Todo Information</title>
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
        .missions {
            margin-top: 20px;
        }
        .mission-link {
            display: block;
            padding: 5px 0;
            text-decoration: none;
            color: #333;
            transition: color 0.3s ease;
        }
        .mission-link:hover {
            color: #007bff;
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
            max-height: 70vh;
            overflow-y: auto;
            width: 600px;
        }
        .todo-info {
            margin-bottom: 10px;
        }
        .description {
            text-align: center;
            word-wrap: break-word;
            max-width: 95%;
        }
        .delete-button {
            margin-top: 10px;
            padding: 5px 10px;
            border: none;
            border-radius: 3px;
            background-color: #dc3545;
            color: white;
            cursor: pointer;
        }
        .back-button {
            margin-top: 10px;
            padding: 5px 10px;
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
    <#include "menu.ftl">
    <div class="title"><strong>Todo Information</strong></div>
    <div class="white-container">
        <div class="todo-info">
            <strong>Todo Name:</strong> ${todo.name}
        </div>
        <div class="description">
            <strong>Description:</strong> ${todo.description}
        </div>
        <br>
        <div class="missions">
            <ul>
                <div class="missions">
                    <strong>Missions:</strong>
                    <ul>
                        <#list missions as mission>
                            <a href="mission?id=${mission.id}" class="mission-link">${mission.name}</a><br>
                        </#list>
                    </ul>
                </div>
            </ul>
        </div>
        <br><br>
        <form action="delete-todo" method="post">
            <input type="hidden" name="id_todo" value="${todo.id}">
            <#if isAdmin>
                <button type="submit" class="delete-button">Delete todo</button>
            </#if>
            <br>
        </form>
        <br>
        <#if isAdmin>
            <button onclick="goToUpdatePage()">Update Todo</button>
            <br>
            <br>
            <button onclick="goToCreateMissionkPage()">Create Missionk</button>
            <br> <br>
            <button onclick="goToPostsPage()">Posts</button>
            <br>
        </#if>
        <br>
        <br>
        <button class="back-button" onclick="goBack()">Back to Todos</button>
    </div>
</div>
<script>
    function goToPostsPage(){
        window.location.href = "posts?id_todo=${todo.id}"
    }
    function goToCreateMissionPage() {
        window.location.href = `create-mission?id_todo=${todo.id}`;
    }
    function goToUpdatePage() {
        window.location.href = `update-todo?id=${todo.id}`;
    }
    function goBack() {
        window.location.href = 'my-todos';
    }
</script>
</body>
</html>
