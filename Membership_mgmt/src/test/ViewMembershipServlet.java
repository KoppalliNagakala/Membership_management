package test;
import test.DBUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/ViewMembershipServlet")
public class ViewMembershipServlet extends HttpServlet {
   
    private DataSource dataSource;
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Membership> membership = null;
        String errorMessage = null;

        Connection connection = null;

        try {
            connection = DBUtils.getConnection();
            if (connection != null) {
                
                MembershipDAO dao = new MembershipDAO();
                membership = dao.getAllMemberships();
            } else {
                errorMessage = "Failed to connect to the database.";
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            errorMessage = "An error occurred while retrieving membership data.";
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        request.setAttribute("membership", membership);
       request.setAttribute("errorMessage", errorMessage);
        request.getRequestDispatcher("viewMembership.jsp").forward(request, response);
    }

	
	
}
