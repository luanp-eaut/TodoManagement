<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Register - Todo App</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="d-flex justify-content-center align-items-center vh-100 bg-light">
    <div class="card shadow p-4" style="width: 400px;">
        <h2 class="text-center text-primary">Register</h2>
        <form:form modelAttribute="user" action="/users/register" method="post">
            <div class="mb-3">
                <form:label path="username" class="form-label">Username:</form:label>
                <form:input path="username" class="form-control"/>
                <form:errors path="username" class="text-danger"/>
            </div>
            <div class="mb-3">
                <form:label path="password" class="form-label">Password:</form:label>
                <form:password path="password" class="form-control"/>
                <form:errors path="password" class="text-danger"/>
            </div>
            <div class="mb-3">
                <form:label path="role" class="form-label">Role:</form:label>
                <form:select path="role" class="form-select">
                    <form:option value="" label="Select Role"/>
                    <form:option value="USER" label="User"/>
                    <form:option value="ADMIN" label="Admin"/>
                </form:select>
                <form:errors path="role" class="text-danger"/>
            </div>
            <button type="submit" class="btn btn-primary w-100">Register</button>
        </form:form>
        <p class="text-center mt-3">
            <a href="/login" class="text-decoration-none">Back to Login</a>
        </p>
    </div>
    <!-- Bootstrap JS (optional, for interactive components) -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
