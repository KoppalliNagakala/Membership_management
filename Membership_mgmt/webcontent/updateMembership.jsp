<%@ page import="java.sql.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="test.Membership" %>
<%@page import ="test.DBUtils" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.Connection" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  <%
    int membershipId = Integer.parseInt(request.getParameter("membershipId"));
    Membership membership = null;

    // Check if the membership object is available
    if (request.getAttribute("membership") != null) {
        membership = (Membership) request.getAttribute("membership");
    } else {
        // If membership object is not set, display error message and return
        %>
        <div class="error-message">${errorMessage}</div>
        
        <%
        return;
    }

    if (request.getParameter("update") != null) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // Establish database connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DBUtils.getConnection();
            
            // Prepare SQL statement for updating membership details
            String sql = "UPDATE membership SET customer_id=?, start_date=?, end_date=?, type=? WHERE membership_id=?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(request.getParameter("customer_id")));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date startDate = sdf.parse(request.getParameter("start_date"));
            java.util.Date endDate = sdf.parse(request.getParameter("end_date"));
            statement.setDate(2, new java.sql.Date(startDate.getTime()));
            statement.setDate(3, new java.sql.Date(endDate.getTime()));
            statement.setString(4, request.getParameter("type"));
            statement.setInt(5, membershipId);
            
            // Execute the update query
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                // Print success message and redirect 
                response.sendRedirect(request.getContextPath() + "/ViewMembershipServlet");
                request.setAttribute("sucessMessage","Membership updated successfully!");
               
            } else {
                out.println("Error updating membership.");
            }
        } catch (SQLException | ClassNotFoundException e) {
            // Handle database and class loading exceptions
            out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            // Handle other exceptions
            out.println("Error: " + e.getMessage());
        } finally {
            // Close resources in finally block
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // Ignore
            }
        }
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Update Membership</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <h1 class="mt-4">Update Membership</h1>
   
    <form action="UpdateMembershipServlet" method="post">
        <input type="text" name="membershipId" value="<%= membershipId %>">
        <div class="form-group">
            <label for="customer_id">Customer ID:</label>
            <input type="text" class="form-control" id="customer_id" name="customer_id" value="<%= membership.getCustomerId() %>">
        </div>
        <div class="form-group">
            <label for="start_date">Start Date:</label>
            <input type="date" class="form-control" id="start_date" name="start_date" value="<%= new SimpleDateFormat("yyyy-MM-dd").format(membership.getStartDate()) %>">
       </div>
        <div class="form-group">
            <label for="end_date">End Date:</label>
            <input type="date" class="form-control" id="end_date" name="end_date" value="<%= new SimpleDateFormat("yyyy-MM-dd").format(membership.getEndDate()) %>">
        </div>
        <div class="form-group">
            <label for="type">Type:</label>
            <select name="type" class="form-control">
                <option value="<%= membership.getType() %>"><%= membership.getType() %></option>
                <option value="Annual">Annual</option>
                <option value="Student">Basic</option>
                <option value="Faculty">Premium</option>
                <option value="Free">Free</option>
            </select>
        </div>
        <button type="submit" name="update" class="btn btn-primary">Update Membership</button>
    </form>
</div>
</body>
</html>
