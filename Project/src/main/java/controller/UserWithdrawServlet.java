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
import dao.UserDAO;
import util.ExpenseHelper;

/**
 * Servlet implementation class UserWithdrawServlet
 */
@WebServlet("/UserWithdrawServlet")
public class UserWithdrawServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserWithdrawServlet() {
        super();
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

      request.setAttribute("user", udb);

      // userWithdraw.jspへフォワード
      request.getRequestDispatcher(ExpenseHelper.USER_WITHDRAW_PAGE).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      request.setCharacterEncoding("UTF-8"); // 文字化け防止

      // ユーザーのデータを削除する処理、と、ユーザーIDが一致する出費データの削除をする処理

      // requestパラメター取得（セッションで良い）
      HttpSession session = request.getSession();
      UserDataBeans udb = (UserDataBeans) session.getAttribute("userInfo");

      // ユーザーIDは必ず取得
      int userId = udb.getId();
      System.out.println("userId: " + userId);

      // UserDAOにユーザーのデータを削除するメソッドを追加
      UserDAO.deleteUser(userId);

      // ExpenseDAOに、ユーザーIDを元に出費を削除するメソッドを追加
      ExpenseDAO.deleteExpenseByUserId(userId);


      // LogoutServletへへ遷移（sessionパラメターを削除したいため）
      response.sendRedirect("LogoutServlet");


	}

}
