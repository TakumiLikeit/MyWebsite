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

      // - - - - - - - - - - - - - - - - -
      String expenseIdStr = request.getParameter("id");
      if (expenseIdStr == null) {
        response.sendRedirect("ExpenseListServlet");
        return;
      }

      int expenseId = Integer.valueOf(expenseIdStr);
      // ExpenseDAO内にfindByIdというメソッドをつくる
      ExpenseDataBeans edb = ExpenseDAO.findById(expenseId);
      request.setAttribute("expense", edb);
      // - - - - - - - - - - - - - - - - -


      // updateList.jspへフォワード
      request.getRequestDispatcher(ExpenseHelper.EXPENSE_UPDATE_PAGE).forward(request, response);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      request.setCharacterEncoding("UTF-8"); // 文字化け防止
      
      //expenseのidを取得（出費id）
      String expenseId = request.getParameter("id"); // ここでnullが発生している可能性があり 5/26 jspのa
                                                     // hrefでリンクのidを飛ばせているかチェック(expenseList.jsp)
                                                     // input hiddenを用意してあげないといけない
      System.out.println("expenseId: " + expenseId);

      // ここの段階では、まだ全部Stringの方が空欄かどうか判別しやすい
      String expenseName = request.getParameter("expense-name");
      String price = request.getParameter("price");
      String categoryId = request.getParameter("category");
      String expenseDate = request.getParameter("expense-date");
      String note = request.getParameter("note");

      StringBuilder errMsg = new StringBuilder();


      boolean existsErr = false;

      if (ExpenseHelper.isEmpty(expenseName, price, categoryId, expenseDate)) {
        // request.setAttribute("errMsg", "入力必須項目に空欄があります");
        errMsg.append("<ul><li>入力必須項目に空欄があります</li>");
        existsErr = true;
      }

      if (!ExpenseHelper.isNumeric(price) || ExpenseHelper.isOnlySign(price)) {
        // request.setAttribute("errMsgPrice", "値段は正の半角数字で入力してください");
        if (!existsErr) {
          errMsg.append("<ul>");
        }
        errMsg.append("<li>値段は正の半角数字で入力してください</li>");

        existsErr = true;
      } else if (ExpenseHelper.isNegative(price)) {
        // request.setAttribute("errMsgPrice", "値段は0より大きいものを入力してください");
        if (!existsErr) {
          errMsg.append("<ul>");
        }
        errMsg.append("<li>値段は0より大きいものを入力してください</li>");

        existsErr = true;
      }



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

        HttpSession session = request.getSession();
        UserDataBeans udb = (UserDataBeans) session.getAttribute("userInfo");

        String userId = String.valueOf(udb.getId());

        if (ExpenseDAO.updateExpenseSuccess(expenseId, userId, expenseName, price, categoryId,
            expenseDate, note)) {
          System.out.println("出費の追加、成功");
        } else {
          System.out.println("出費の追加、失敗");
        }


        response.sendRedirect("ExpenseListServlet");


	}

}
