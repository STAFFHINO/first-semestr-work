<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create Mission</title>
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
        .form-container {
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            max-width: 400px;
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
        input[type="text"],
        textarea,
        select {
            width: calc(100% - 10px);
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 3px;
        }
        .submit-button {
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

    <div class="title"><strong>Create Mission</strong></div>
    <#if errorMessage??>
        <div class="error_message">${errorMessage}</div>
    </#if>
    <div class="form-container">
        <form method="post">
            <div class="form-group">
                <label for="name">Mission Name:</label>
                <input type="text" id="name" name="name" required>
            </div>
            <div class="form-group">
                <label for="description">Description:</label>
                <textarea id="description" name="description" rows="4" required></textarea>
            </div>
            <div class="form-group">
                <label for="deadline">Deadline:</label>
                <input type="datetime-local" id="deadline" name="deadline" required>
            </div>
            <div class="form-group">
                not_started,
                started,
                finished
                <label for="status">Status:</label>
                <select id="status" name="status" required>
                    <option value="not_started">not started</option>
                    <option value="started">started</option>
                    <option value="finished">finished</option>
                </select>
            </div>
            <a href="javascript:history.go(-1)" class="back-button">Back</a> <br>
            <button type="submit" class="submit-button">Create Mission</button>
        </form>
    </div>
</div>
</body>
</html>-