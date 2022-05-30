package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import beans.UserDataBeans;
import dao.UserDAO;
import util.ExpenseHelper;

/**
 * Servlet implementation class UserUpdateServlet
 */
@WebServlet("/UserUpdateServlet")
public class UserUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserUpdateServlet() {
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

      // userUpdate.jspへフォワード
      request.getRequestDispatcher(ExpenseHelper.USER_UPDATE_PAGE).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      request.setCharacterEncoding("UTF-8"); // 文字化け防止

      // requestパラメターの取得
      // String loginId = request.getParameter("login-id");
      String password = request.getParameter("password");
      String passwordConfirm = request.getParameter("password-confirm");
      String userName = request.getParameter("user-name");

      // 例外処理
      // 問題がある場合、requestパラメターに値をセットして、userUpdate.jspへフォワード

      StringBuilder errMsg = new StringBuilder();
      boolean existsErr = false;

      if (userName.equals("")) {
        errMsg.append("<ul><li>入力必須項目に空欄があります</li>");
        existsErr = true;
      }
      if (!password.equals(passwordConfirm)) {
        if (!existsErr) {
          errMsg.append("<ul>");
        }
        errMsg.append("<li>パスワードが一致しません</li>");

        existsErr = true;
      }

      if (existsErr) {
        errMsg.append("</ul>");
        request.setAttribute("errMsg", String.valueOf(errMsg));
        request.setAttribute("password", password);
        request.setAttribute("userName", userName);
        request.getRequestDispatcher(ExpenseHelper.USER_UPDATE_PAGE).forward(request, response);
        return;
      }

      // ログインしているユーザーのユーザーIDを取得
      HttpSession session = request.getSession();
      UserDataBeans udb = (UserDataBeans) session.getAttribute("userInfo");
      int userId = 0;
      if (udb != null) {
        userId = udb.getId();
      } else {
        response.sendRedirect("LoginServlet");
        return;
      }

      // 問題がない場合、データを更新し、ユーザー詳細画面へリダイレクト
      // UserDAO、updateUser内で、パスワードの暗号化、空欄かどうかの判断、を行う
      UserDAO.updateUser(userId, password, passwordConfirm, userName);

      // ユーザーIDから該当するユーザーを取得
      UserDataBeans updatedUdb = null;
      updatedUdb = UserDAO.getUserById(userId);

      // sessionスコープのユーザーのインスタンスを削除し、新しいものを追加
      session.removeAttribute("userInfo");
      session.setAttribute("userInfo", updatedUdb);


      response.sendRedirect("UserDetailServlet");


	}
}
