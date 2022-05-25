package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import beans.UserDataBeans;
import dao.ExpenseDAO;
import util.ExpenseHelper;

/**
 * Servlet implementation class AddExpenseServlet
 */
@WebServlet("/AddExpenseServlet")
public class AddExpenseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddExpenseServlet() {
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

      // addExpense.jspへフォワード
      request.getRequestDispatcher(ExpenseHelper.EXPENSE_ADD_PAGE).forward(request, response);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      request.setCharacterEncoding("UTF-8"); // 文字化け防止

      // ここの段階では、まだ全部Stringの方が空欄かどうか判別しやすい
      String expenseName = request.getParameter("expense-name");
      // int price = Integer.valueOf(request.getParameter("price"));
      String price = request.getParameter("price");
      String categoryId = request.getParameter("category");
      // Date expenseDate = Date.valueOf(request.getParameter("expense-date"));
      String expenseDate = request.getParameter("expense-date");
      String note = request.getParameter("note");

      // 例外処理。（現段階では、空欄がある場合のみ）
      // ExpenseHelper内に、note以外が空欄なら、エラーを出すようなメソッドを作成
      if (ExpenseHelper.isEmpty(expenseName, price, categoryId, expenseDate)) {
        System.out.println("AddExpenseServlet、doPost内、isEmptyの場合");
        request.setAttribute("errMsg", "入力必須項目に空欄があります");

        request.setAttribute("expenseName", expenseName);
        request.setAttribute("price", price);
        request.setAttribute("categoryId", categoryId);// ここ変える、5/25
        request.setAttribute("expenseDate", expenseDate);
        request.setAttribute("note", note);

        request.getRequestDispatcher(ExpenseHelper.EXPENSE_ADD_PAGE).forward(request, response);
        // return;
      } else {
        System.out.println("AddExpenseServlet、doPost内、isEmptyでない場合");

        HttpSession session = request.getSession();
        UserDataBeans udb = (UserDataBeans) session.getAttribute("userInfo");
        if (udb == null) {
          System.out.println("udbはnullです");
        }
        {
          System.out.println("udbはnullじゃないです");
        }
        String userId = String.valueOf(udb.getId());// ここの値がnullで問題が生じている可能性が高い

        System.out.println("userId: " + userId);


        if (ExpenseDAO.addExpenseSuccess(userId, expenseName, price, categoryId, expenseDate, note)) {
          System.out.println("出費の追加、成功");
        } else {
          System.out.println("出費の追加、失敗");
        }

        response.sendRedirect("ExpenseListServlet");
      }

	}

}
