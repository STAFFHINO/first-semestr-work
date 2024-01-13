<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Mission Information</title>
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
            width: 600px;
        }
        .mission-info {
            margin-bottom: 10px;
        }
        .description {
            text-align: center;
            word-wrap: break-word;
            max-width: 95%;
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
    <div class="title"><strong>Mission Information</strong></div>
    <div class="white-container">
        <div class="mission-info">
            <strong>Mission Name:</strong> ${mission.name}
        </div>
        <div class="description">
            <strong>Description:</strong> ${mission.description}
        </div>
        <div class="mission-info">
            <strong>Deadline:</strong> ${date}  ${time}
        </div>
        <div class="mission-info">
            <strong>Status:</strong> ${mission.status}
        </div>
        <br>
        <#if isAdmin>
            <button onclick="goToUpdatePage()">Update</button>
            <br>
            <br>
            <form action="delete-mission" method="post">
                <input type="hidden" name="id_todo" value="${mission.id_todo}">
                <input type="hidden" name="id" value="${mission.id}">
                <button type="submit" class="delete-button">Delete mission</button>
                <br>
            </form>
            <br>
        </#if>
        <button onclick="goToRemarksPage()">Show remarks</button>
        <br>
        <button class="back-button" onclick="goBack()">Back to Todo</button>
    </div>
</div>
<script>
    function goBack() {
        window.location.href = 'todo?id=${mission.id_todo}'
    }
    function goToUpdatePage(){
        window.location.href = 'update-mission?id=${mission.id}'
    }
    function goToRemarksPage(){
        window.location.href = 'remarks?mission_id=${mission.id}'
    }
</script>
</body>
</html>
