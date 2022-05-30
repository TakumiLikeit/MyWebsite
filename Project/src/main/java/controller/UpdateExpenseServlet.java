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
 * Servlet implementation class UpdateExpenseServlet
 */
@WebServlet("/UpdateExpenseServlet")
public class UpdateExpenseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateExpenseServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

      // sessionスコープにログイン中のユーザーがいるか確認
      HttpSession session = request.getSession();
      UserDataBeans udb = (UserDataBeans) session.getAttribute("userInfo");
      if (udb == null) {
        response.sendRedirect("LoginServlet");
        return;
      }

      // 出費IDを取得
      String expenseIdStr = request.getParameter("id");
      if (expenseIdStr == null) {
        response.sendRedirect("ExpenseListServlet");
        return;
      }

      // 取得した出費IDを元に、出費データを取得
      int expenseId = Integer.valueOf(expenseIdStr);
      ExpenseDataBeans edb = ExpenseDAO.findById(expenseId);
      request.setAttribute("expense", edb);


      // updateList.jspへフォワード
      request.getRequestDispatcher(ExpenseHelper.EXPENSE_UPDATE_PAGE).forward(request, response);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      request.setCharacterEncoding("UTF-8"); // 文字化け防止
      
      //expenseのidを取得（出費id）
      String expenseId = request.getParameter("id");
      System.out.println("expenseId: " + expenseId);

      // Stringのままの方が、入力内容を判別しやすい
      String expenseName = request.getParameter("expense-name");
      String price = request.getParameter("price");
      String categoryId = request.getParameter("category");
      String expenseDate = request.getParameter("expense-date");
      String note = request.getParameter("note");


      // 例外処理
      // StringBuilderを使って、エラーメッセージを作成
      StringBuilder errMsg = new StringBuilder();
      boolean existsErr = false;

      // 入力項目に空欄がある場合（note以外で）
      if (ExpenseHelper.isEmpty(expenseName, price, categoryId, expenseDate)) {
        errMsg.append("<ul><li>入力必須項目に空欄があります</li>");
        existsErr = true;
      }

      // priceが数字でない、もしくは0以下の場合
      if (!ExpenseHelper.isNumeric(price) || ExpenseHelper.isOnlySign(price)) {
        if (!existsErr) {
          errMsg.append("<ul>");
        }
        errMsg.append("<li>値段は正の半角数字で入力してください</li>");
        existsErr = true;

      } else if (ExpenseHelper.isNegative(price)) {
        if (!existsErr) {
          errMsg.append("<ul>");
        }
        errMsg.append("<li>値段は0より大きいものを入力してください</li>");
        existsErr = true;
      }


      // 入力に問題がある場合、requestパラメターに値をセットして、updateExpense.jspへフォワード
      if (existsErr) {
        errMsg.append("</ul>");

        request.setAttribute("errMsg", String.valueOf(errMsg));

        request.setAttribute("expenseName", expenseName);
        request.setAttribute("price", price);
        request.setAttribute("categoryId", categoryId);
        request.setAttribute("expenseDate", expenseDate);
        request.setAttribute("note", note);

        request.getRequestDispatcher(ExpenseHelper.EXPENSE_ADD_PAGE).forward(request, response);
        return;
      }

      // ログイン中のユーザーのユーザーIDをString型で取得
      HttpSession session = request.getSession();
      UserDataBeans udb = (UserDataBeans) session.getAttribute("userInfo");
      String userId = String.valueOf(udb.getId());

      // 出費を更新
      ExpenseDAO.updateExpense(expenseId, userId, expenseName, price, categoryId, expenseDate,
          note);
      // ExpenseListServletへリダイレクト
      response.sendRedirect("ExpenseListServlet");


	}

}
