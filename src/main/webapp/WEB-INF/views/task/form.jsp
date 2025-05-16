<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title><c:choose><c:when test="${isEdit}">Edit Task</c:when><c:otherwise>Create Task</c:otherwise></c:choose> - Todo App</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container py-5">
    <h2 class="text-primary mb-4"><c:choose><c:when test="${isEdit}">Edit Task</c:when><c:otherwise>Create Task</c:otherwise></c:choose></h2>
    <form:form modelAttribute="task" action="/tasks/save" method="post" class="needs-validation">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <c:if test="${isEdit}">
            <form:hidden path="id"/>
        </c:if>
        <div class="mb-3">
            <form:label path="title" class="form-label">Title:</form:label>
            <form:input path="title" class="form-control"/>
            <form:errors path="title" cssClass="text-danger"/>
        </div>
        <div class="mb-3">
            <form:label path="description" class="form-label">Description:</form:label>
            <form:textarea path="description" class="form-control"/>
            <form:errors path="description" cssClass="text-danger"/>
        </div>
        <div class="mb-3 form-check">
            <form:label path="completed" class="form-check-label">Completed:</form:label>
            <form:checkbox path="completed" class="form-check-input"/>
            <form:errors path="completed" cssClass="text-danger"/>
        </div>
        <button type="submit" class="btn btn-primary">
            <c:choose><c:when test="${isEdit}">Update Task</c:when><c:otherwise>Create Task</c:otherwise></c:choose>
        </button>
    </form:form>
    <p class="mt-3"><a href="/tasks" class="btn btn-secondary">Back to Task List</a></p>
</body>
</html>
