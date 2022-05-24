package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import beans.UserDataBeans;
import util.ExpenseHelper;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

        HttpSession session = request.getSession();

        UserDataBeans udb = (UserDataBeans) session.getAttribute("user");

        if (udb != null) {
          response.sendRedirect("UserListServlet");
          return;
        }

        String loginId = (String) request.getAttribute("login-id");
        String password = (String) request.getAttribute("password");

        request.setAttribute("loginId", loginId);
        request.setAttribute("password", password);


        RequestDispatcher dispatcher = request.getRequestDispatcher(ExpenseHelper.LOGIN_PAGE);
        dispatcher.forward(request, response);
	}
}
