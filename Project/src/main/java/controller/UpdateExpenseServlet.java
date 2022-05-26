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
      int expenseId = Integer.valueOf(request.getParameter("id"));
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

      System.out.println("expenseName: " + expenseName);
      System.out.println("price: " + price);
      System.out.println("categoryId: " + categoryId);
      System.out.println("expenseDate: " + expenseDate);
      System.out.println("note: " + note);

      // 例外処理。（現段階では、空欄がある場合のみ）
      // ExpenseHelper内に、note以外が空欄なら、エラーを出すようなメソッドを作成
      if (ExpenseHelper.isEmpty(expenseName, price, categoryId, expenseDate)) {
        System.out.println("AddExpenseServlet、doPost内、isEmptyの場合");
        request.setAttribute("errMsg", "入力必須項目に空欄があります");

        request.setAttribute("id", expenseId);
        request.setAttribute("expenseName", expenseName);
        request.setAttribute("price", price);
        request.setAttribute("categoryId", categoryId);
        request.setAttribute("expenseDate", expenseDate);
        request.setAttribute("note", note);

        request.getRequestDispatcher(ExpenseHelper.EXPENSE_UPDATE_PAGE).forward(request, response);
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

        System.out.println("あいうえお");


        // 変更中 int id = udb.getId();
        String userId = String.valueOf(udb.getId());// ここの値がnullで問題が生じている可能性が高い //
                                                    // //valueOfの中がString型じゃない、int型じゃない可能性。型をチェックする必要がある

        if (ExpenseDAO.updateExpenseSuccess(expenseId, userId, expenseName, price, categoryId,
            expenseDate, note)) {
          System.out.println("出費の追加、成功");
        } else {
          System.out.println("出費の追加、失敗");
        }


        response.sendRedirect("ExpenseListServlet");

      }
	}

}
