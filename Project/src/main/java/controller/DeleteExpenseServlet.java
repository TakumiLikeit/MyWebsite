package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import beans.ExpenseDataBeans;
import beans.UserDataBeans;
import dao.ExpenseDAO;
import util.ExpenseHelper;

/**
 * Servlet implementation class ExpenseDeleteServlet
 */
@WebServlet("/DeleteExpenseServlet")
public class DeleteExpenseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteExpenseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

      HttpSession session = request.getSession();

      UserDataBeans udb = (UserDataBeans) session.getAttribute("userInfo");

      if (udb == null) {
        response.sendRedirect("LoginServlet");
        return;
      }

      
      int expenseId = Integer.valueOf(request.getParameter("id"));
      // System.out.println("expenseId" + expenseId);
      ExpenseDataBeans edb = ExpenseDAO.findById(expenseId);
      request.setAttribute("expense", edb);

      // deleteExpense.jspへフォワード
      request.getRequestDispatcher(ExpenseHelper.EXPENSE_DELETE_PAGE).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      request.setCharacterEncoding("UTF-8"); // 文字化け防止


      String expenseId = request.getParameter("id");

      // ExpenseDAOに、出費を削除するメソッドを作成
      ExpenseDAO.deleteExpense(expenseId);


      // request.getRequestDispatcher(ExpenseHelper.EXPENSE_LIST_PAGE).forward(request, response);
      response.sendRedirect("ExpenseListServlet");

	}

}
