package test;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rollNumber = request.getParameter("username");
        String password = request.getParameter("password");
        
        // Validate credentials (for simplicity, assuming roll number is the password)
        if (rollNumber.equals(password)) {
            // Successful login, store user information in session
            HttpSession session = request.getSession();
            session.setAttribute("username", rollNumber);
            response.sendRedirect("home.jsp");
        } else {
            // Failed login, redirect back to login page
            response.sendRedirect("login.html");
        }
    }
}
