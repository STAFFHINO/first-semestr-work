<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create Remark</title>
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
            max-width: 400px;
            margin: auto;
            padding: 20px;
            border-radius: 5px;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .title {
            font-size: 24px;
            font-weight: bold;
            margin-bottom: 20px;
        }
        .form-group {
            margin-bottom: 15px;
            text-align: left;
        }
        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }
        textarea {
            width: calc(100% - 10px);
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 3px;
        }
        button[type="submit"] {
            padding: 10px 20px;
            background-color: #007bff;
            border: none;
            border-radius: 3px;
            color: #fff;
            font-weight: bold;
            cursor: pointer;
        }
    </style>
</head>
<body>
<div class="container">
    <#include "menu.ftl">
    <#if errorMessage??>
        <div class="error_message">${errorMessage}</div>
    </#if>
    <div class="title"><strong>Create Remark</strong></div>
    <form method="post" action="create-remark">
        <div class="form-group">
            <label for="content">Remark:</label>
            <textarea id="content" name="content" rows="4" required></textarea>
        </div>
        <input type="hidden" id="id_mission" name="id_mission" value="${id_mission}">
        <button type="submit">Create Remark</button>
        <br><br>
    </form>
    <br><br>
    <button class="back-button" onclick="goBack()">Back</button>
</div>
<script>
    function goBack() {
        window.history.back();
    }
</script>
</body>
</html>