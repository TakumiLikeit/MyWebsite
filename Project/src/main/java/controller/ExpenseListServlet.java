package controller;

import java.io.IOException;
import java.util.List;
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
 * Servlet implementation class ExpenseListServlet
 */
@WebServlet("/ExpenseListServlet")
public class ExpenseListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExpenseListServlet() {
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

      int userId = udb.getId();
      System.out.println("ExpenseListServlet内、doGet、userId: " + userId);

      // 今ログインしているユーザーのIDをもとに、全ての出費を取得
      List<ExpenseDataBeans> expenseList = ExpenseDAO.findAll(userId);
      request.setAttribute("expenseList", expenseList);

      // 合計金額の計算（for_loop,他のやり方がありそう）
      int totalExpense = 0;
      for (int i = 0; i < expenseList.size(); i++) {
        ExpenseDataBeans expense = expenseList.get(i);
        totalExpense += expense.getPrice();
      }
      request.setAttribute("totalExpense", totalExpense);


      // expenseList.jspへフォワード
      request.getRequestDispatcher(ExpenseHelper.EXPENSE_LIST_PAGE).forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // 検索条件はあとで追加する。
	}

}
