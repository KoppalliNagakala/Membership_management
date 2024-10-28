<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <!-- Bootstrap CSS link -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        /* Custom CSS */
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }
        h1 {
            text-align: center;
            margin-top: 10px;
            color: #007bff; 
            display="hidden"; /* Blue color for h1 */
        }
        /* Navbar styling */
        .navbar {
            background-color: #f3f6f4; /* Dark gray background */
        }
        .navbar-brand {
            color: #ffffff; /* White text color */
        }
        .navbar-brand img {
            width: 30px; /* Adjust the width of the user avatar */
            height: auto;
            margin-right: 10px;
            border-radius: 50%; /* Make the avatar rounded */
        }
        .navbar-nav .nav-link {
            color: #ffffff; /* White text color */
            margin-right: 10px; /* Adjust spacing between menu items */
        }
        .navbar-nav .nav-link:hover {
            color: Blue; /* Light text color on hover */
        }
        /* End Navbar styling */
        .user-info {
            position: absolute;
            top: 10px;
            right: 10px;
        }
        .user-info img {
            width: 20px;
            height: 20px;
            border-radius: 50%;
            margin-right: 5px;
        }
    </style>
</head>
    <h1>Membership Management System</h1>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container">
            <a class="navbar-brand" href="#">
                <img src="images/user-avatar.jpg" alt="User Avatar">
                <%= session.getAttribute("username") %>
            </a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="addMembershipForm.html">Add Membership</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/Membership_mgmt/ViewMembershipServlet">View Memberships</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="updateMembershipForm.html">Update Membership</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="deleteMembership.jsp">Delete Membership</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

