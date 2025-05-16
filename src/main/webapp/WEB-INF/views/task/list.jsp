<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Task List</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
    <div class="container py-5">
        <h2 class="text-center text-primary mb-4">Your Tasks</h2>
        <div class="d-flex justify-content-between mb-3">
            <a href="/tasks/create" class="btn btn-success">Create New Task</a>
            <a href="/logout" class="btn btn-outline-danger">Logout</a>
        </div>
        <table class="table table-bordered table-hover">
            <thead class="table-primary">
                <tr>
                    <th>Title</th>
                    <th>Description</th>
                    <th>Completed</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${tasks}" var="task">
                    <tr>
                        <td>${task.title}</td>
                        <td>${task.description}</td>
                        <td>${task.completed ? 'Yes' : 'No'}</td>
                        <td>
                            <a href="/tasks/edit/${task.id}" class="btn btn-warning btn-sm">Edit</a>
                            <form action="/tasks/delete/${task.id}" method="post" style="display:inline;">
                                <button type="submit" class="btn btn-danger btn-sm">Delete</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <!-- Bootstrap JS (optional, for interactive components) -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
