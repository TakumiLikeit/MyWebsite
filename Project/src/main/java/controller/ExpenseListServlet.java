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
      request.setCharacterEncoding("UTF-8"); // 文字化け防止


      // requestパラメターの取得
      String expenseName = request.getParameter("expense-name");
      // String price = request.getParameter("price");
      String categoryId = request.getParameter("category");
      String startDate = request.getParameter("start-date");
      String endDate = request.getParameter("end-date");
      // String note = request.getParameter("note");

      // ログイン中ユーザーのid取得
      HttpSession session = request.getSession();
      UserDataBeans udb = (UserDataBeans) session.getAttribute("userInfo");
      int userId = udb.getId();


      // ExpenseDAOに、検索用のメソッドを作成
      List<ExpenseDataBeans> expenseList =
          ExpenseDAO.searchExpense(userId, expenseName, categoryId, startDate, endDate);

      // 合計金額の計算（for_loop,他のやり方がありそう）
      int totalExpense = 0;
      for (int i = 0; i < expenseList.size(); i++) {
        ExpenseDataBeans expense = expenseList.get(i);
        totalExpense += expense.getPrice();
      }

      // requestパラメターのセット
      request.setAttribute("expenseList", expenseList);
      request.setAttribute("totalExpense", totalExpense);
      request.setAttribute("expenseName", expenseName);
      request.setAttribute("categoryId", categoryId);
      request.setAttribute("startDate", startDate);
      request.setAttribute("endDate", endDate);



      // expenseList.jspへフォワード
      request.getRequestDispatcher(ExpenseHelper.EXPENSE_LIST_PAGE).forward(request, response);


	}

}
