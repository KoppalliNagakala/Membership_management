package test;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UpdateMembershipServlet")
public class UpdateMembershipServlet extends HttpServlet {
   public MembershipDAO membershipDAO;

    public void init() {
        membershipDAO = new MembershipDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	 String membershipIdString = request.getParameter("membershipId");
         if (membershipIdString == null || membershipIdString.isEmpty()) {
             request.setAttribute("errorMessage", "Membership ID is required.");
             forwardToErrorPage(request, response);
             return;
         }

         int membershipId = Integer.parseInt(membershipIdString);

         try {
             Membership membership = membershipDAO.getMembershipById(membershipId);

             if (membership == null) {
                 request.setAttribute("errorMessage", "Membership not found for ID: " + membershipId);
                 forwardToErrorPage(request, response);
                 return;
             }

             request.setAttribute("membership", membership);  
           

            if (request.getParameter("update") != null) {
            	
                int customerId = Integer.parseInt(request.getParameter("customer_id"));
                String startDateStr = request.getParameter("start_date");
                String endDateStr = request.getParameter("end_date");

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date startDate = null;
                Date endDate = null;
                try {
                    startDate = new Date(dateFormat.parse(startDateStr).getTime());
                    endDate = new Date(dateFormat.parse(endDateStr).getTime());
                } catch (ParseException e) {
                    e.printStackTrace();
                    request.setAttribute("errorMessage", "Invalid date format. Please use yyyy-MM-dd.");
                    forwardToUpdateForm(request, response);
                    return;
                }

                String type = request.getParameter("type");

                if (!updateMembership(membershipId, customerId, startDate, endDate, type)) {
                    request.setAttribute("errorMessage", "Error updating membership. Please try again.");
                    forwardToUpdateForm(request, response);
                    return;
                }

                request.setAttribute("successMessage", "Membership updated successfully!");
                forwardToUpdateForm(request, response);
                return;
            }

            forwardToUpdateForm(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Database error: " + e.getMessage());
            forwardToErrorPage(request, response);
        }
    }

    private void forwardToUpdateForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("updateMembership.jsp");
        dispatcher.forward(request, response);
    }

    private void forwardToErrorPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
        dispatcher.forward(request, response);
    }

    private boolean updateMembership(  int membershipId, int customerId, Date startDate, Date endDate, String type)
            throws SQLException {
        return membershipDAO.updateMembership( membershipId, customerId, startDate, endDate, type);
    }
}
