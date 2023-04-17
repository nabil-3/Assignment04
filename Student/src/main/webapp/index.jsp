<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Student Database</title>
</head>
<body>
<h1>Add New Student</h1>
<form method="post" action="rest/student">
    <label for="studentId">Student ID:</label>
    <input type="number" name="studentId" id="studentId" required><br><br>
    <label for="name">Name:</label>
    <input type="text" name="name" id="name" required><br><br>
    <label for="semester">Semester:</label>
    <input type="number" name="semester" id="semester" required><br><br>
    <label for="cgpa">CGPA:</label>
    <input type="number" step="0.01" name="cgpa" id="cgpa" required><br><br>
    <input type="submit" value="Add Student">
</form>
</body>
</html>
