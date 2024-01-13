<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Remarks</title>
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
        .remark-container {
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            max-height: 70vh;
            overflow-y: auto;
            width: 600px;
            margin-bottom: 20px;
        }
        .remark {
            margin-bottom: 10px;
            border-bottom: 1px solid #ccc;
            padding-bottom: 10px;
        }
        .user-info {
            font-style: italic;
        }
        .content {
            margin-top: 5px;
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
    <div class="title"><strong>Remarks</strong></div>
    <#include "menu.ftl">
    <div class="remark-container">
        <#list remarks as remark>
            <div class="remark">
                <div class="content">${comment.email} : ${comment.content}</div>
            </div>
        </#list>
    </div>
    <br><br>
    <button onclick="goToAddRemarkPage()">Write a remark</button>
    <br><br>
    <button class="back-button" onclick="goBackToMission()">Back</button>

</div>

</div>
<script>
    function goBackToMission(){
        window.location.href = 'task?id=${id_mission}'
    }
    function goToAddRemarkPage(){
        window.location.href = 'add-comment?task_id=${id_mission}'
    }
</script>
</body>
</html>
