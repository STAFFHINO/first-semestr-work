<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>My ToDos</title>
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
            max-height: 70vh;
            overflow-y: auto;
        }
        .todo {
            width: 800px;
            border: 1px solid #ccc;
            border-radius: 5px;
            padding: 10px;
            margin-bottom: 10px;
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        .create-todo-button {
            margin-top: 20px;
        }
        .create-todo-button button {
            padding: 12px 20px;
            font-size: 18px;
            border-radius: 5px;
            background-color: #007bff;
            color: #fff;
            border: none;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        .create-todo-button button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<div class="container">
    <#include "menu.ftl">
    <div class="title"><strong>My ToDos</strong></div>
    <div id="todos" class="white-container">
        <#list todos as todo>
            <div class="todo">
                <a href="todo?id=${todo.id}">${todo.name}</a>
            </div>
        </#list>
        <a href="create-todo" class="create-todo-button">
            <button>Create New Todo</button>
        </a>
    </div>
</div>
</body>
</html>