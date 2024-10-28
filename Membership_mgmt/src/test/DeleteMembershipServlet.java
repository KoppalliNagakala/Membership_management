package test;
import test.MembershipDAO;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DeleteMembershipServlet")
public class DeleteMembershipServlet extends HttpServlet {
    private MembershipDAO membershipDAO;

    public void init() {
        membershipDAO = new MembershipDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int membershipId = Integer.parseInt(request.getParameter("membershipId"));

        try {
        	if(membershipDAO.getMembershipById(membershipId) != null) {
        	
            boolean deleted = membershipDAO.deleteMembershipById(membershipId);
            if (deleted) {
                // Set success message
                request.setAttribute("successMessage", "Membership deleted successfully!");

                // Redirect to ViewMembershipServlet
                response.sendRedirect(request.getContextPath() + "/ViewMembershipServlet");
                return; // Exit doPost method after redirection
            } else {
                request.setAttribute("errorMessage", "Failed to delete membership. Please try again.");
            }
        	}else {
        		request.setAttribute("errorMessage", "Membership with ID " + membershipId + " does not exist.");
        	}
        }catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Database error: " + e.getMessage());
        }

        // Forward to deleteMembership.jsp if deletion was unsuccessful
        request.getRequestDispatcher("deleteMembership.jsp").forward(request, response);
        }
}


