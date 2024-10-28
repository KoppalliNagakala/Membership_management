<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="home.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Delete Membership</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <h1 class="mt-4">Delete Membership</h1>
        
        <form action="DeleteMembershipServlet" method="post">
            <div class="form-group">
                <label for="membershipId">Membership ID:</label>
                <input type="text" class="form-control" id="membershipId" name="membershipId" required>
            </div>
            <button type="submit" class="btn btn-danger" onclick="return confirm('Are you sure you want to delete this membership?')">Delete</button>
        </form>
        <%-- Display success or error messages if available --%>
        <% if (request.getAttribute("successMessage") != null) { %>
            <div class="alert alert-success mt-3" role="alert">
                <%= request.getAttribute("successMessage") %>
            </div>
        <% } else if (request.getAttribute("errorMessage") != null) { %>
            <div class="alert alert-danger mt-3" role="alert">
                <%= request.getAttribute("errorMessage") %>
            </div>
        <% } %>
    </div>
</body>
</html>
