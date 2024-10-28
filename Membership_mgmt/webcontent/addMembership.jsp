<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ include file="home.jsp" %>

<html>
<head>
    <title>Sucess Message</title>
    <!-- Bootstrap CSS link -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <style>
        /* Add your custom CSS styles here */
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        h1 {
            color: #007bff;
            margin: 5px;
            padding:.5em;
            
        }
        /* Custom styling for buttons */
        .btn-primary {
        gap: 3em;
            background-color: #007bff;
            border-color: #007bff;
        }
        .btn-primary:hover {
            background-color: #0056b3;
            border-color: #0056b3;
        }
        .btn-primary:focus,
        .btn-primary.focus {
            box-shadow: 0 0 0 0.2rem rgba(0, 123, 255, 0.5);
        }
        .btn-primary.disabled,
        .btn-primary:disabled {
            background-color: #007bff;
            border-color: #007bff;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Membership Added Successfully!</h1>
        <p>Thank you for adding the membership.</p>
        <a href="addMembershipForm.html" class="btn btn-primary">Add Another Membership</a>
        <a href="/Membership_mgmt/ViewMembershipServlet" class="btn btn-primary">View Membership</a>
        <a href="updateMembershipForm.html" class="btn btn-primary">Update Membership</a>
    </div>
    <!-- Bootstrap JS script -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
