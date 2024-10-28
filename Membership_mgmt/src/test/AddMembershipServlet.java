package test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/AddMembershipServlet")
public class AddMembershipServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Assuming date format
	// private static final String CHECK_CUSTOMER_QUERY = "SELECT 1 FROM customer
	// WHERE customer_id = ?";
	// SQL query to insert a new membership record (using placeholders for Date
	// arguments)
	private static final String INSERT_MEMBERSHIP_QUERY = "INSERT INTO Membership (customer_id, start_date, end_date, type) VALUES (?, ?, ?, ?)";

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {

	    response.setContentType("text/html");
	    PrintWriter out = response.getWriter();

	    try (Connection connection = (DBUtils.getConnection());
	    		//response.getWriter().print("Datbase Connected");
	            PreparedStatement insertStatement = connection.prepareStatement(INSERT_MEMBERSHIP_QUERY)) {

	        // Get form data and parse dates (with error handling)
	        int customerId = Integer.parseInt(request.getParameter("customerId"));
	        String startDateStr = request.getParameter("startDate");
	        String endDateStr = request.getParameter("endDate");
	        String type = request.getParameter("type");
	        Date startDate = null;
	        Date endDate = null;

	        try {
	            startDate = dateFormat.parse(startDateStr);
	            endDate = dateFormat.parse(endDateStr);
	        } catch (ParseException e) {
	            out.println("<p>Error: Invalid date format. Please use YYYY-MM-DD.</p>");
	            return; // Exit if parsing fails
	        }
	        // Set parameter values for PreparedStatement
	        insertStatement.setInt(1, customerId);
	        insertStatement.setDate(2, new java.sql.Date(startDate.getTime()));
	        insertStatement.setDate(3, new java.sql.Date(endDate.getTime()));
	        insertStatement.setString(4, type);

	        // Execute the query and handle success/failure
	        int rowsInserted = insertStatement.executeUpdate();
	        if (rowsInserted > 0) {
	            out.println("<p>A new membership record was inserted successfully!</p>");
	            // JavaScript code for delayed redirection after 3 seconds
	            out.println("<script>");
	            out.println("setTimeout(function() { window.location.href = 'addMembership.jsp'; }, 3000);");
	            out.println("</script>");
	        } else {
	            out.println("<p>Error: Unable to insert membership record.</p>");
	        }

	    } catch (SQLException |ClassNotFoundException e) {
	        e.printStackTrace(out);
	        out.println("<p>Error: Database operation failed.</p>");
	    }  finally {
	        out.close();
	    }
	}}
