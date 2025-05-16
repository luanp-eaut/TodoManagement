<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>Todo App - Home</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
    <div class="container text-center py-5">
        <h1 class="text-primary mb-4">Welcome to Todo App</h1>
        <div class="d-flex justify-content-center">
            <div class="btn-group">
                <sec:authorize access="!isAuthenticated()">
                    <a href="/users/register" class="btn btn-outline-primary">Đăng ký</a>
                    <a href="/login" class="btn btn-outline-primary">Đăng nhập</a>
                </sec:authorize>
                <sec:authorize access="hasAuthority('USER')">
                    <a href="/tasks" class="btn btn-outline-primary">Tác vụ</a>
                    <a href="/logout" class="btn btn-outline-danger">Đăng xuất</a>
                </sec:authorize>
                <sec:authorize access="hasAuthority('ADMIN')">
                    <a href="/tasks" class="btn btn-outline-primary">Tác vụ</a>
                    <a href="/users/admin" class="btn btn-outline-primary">Người dùng</a>
                    <a href="/logout" class="btn btn-outline-danger">Đăng xuất</a>
                </sec:authorize>
            </div>
        </div>
    </div>
    <!-- Bootstrap JS (optional, for interactive components) -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
