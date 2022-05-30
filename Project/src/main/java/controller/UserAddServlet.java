package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.UserDAO;
import util.ExpenseHelper;
import util.UserHelper;

/**
 * Servlet implementation class UserAddServlet
 */
@WebServlet("/UserAddServlet")
public class UserAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserAddServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

      // userAdd.jspへフォワード
      request.getRequestDispatcher(ExpenseHelper.USER_ADD_PAGE).forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      request.setCharacterEncoding("UTF-8"); // 文字化け防止

      // requestパラメターの値取得
      String loginId = request.getParameter("login-id");
      String password = request.getParameter("password");
      String passwordConfirm = request.getParameter("password-confirm");
      String userName = request.getParameter("user-name");


      // 例外処理
      // password == passwordConfirmかチェック
      // ExpenseHelper(?)に、空欄があるかどうかのメソッドを追加
      // 問題がある場合、userAdd.jspへフォワードしなおす


      StringBuilder errMsg = new StringBuilder();
      boolean existsErr = false;


      // - - - - - - - - - - - - - コピペして、修正を加えた部分
      if (UserHelper.isEmpty(loginId, password, passwordConfirm, userName)) {
        errMsg.append("<ul><li>入力必須項目に空欄があります</li>");
        existsErr = true;
      }

      if (UserDAO.existsLoginId(loginId)) {
        // request.setAttribute("errMsgPrice", "値段は正の半角数字で入力してください");
        if (!existsErr) {
          errMsg.append("<ul>");
        }
        errMsg.append("<li>同じログインIDの人が既に存在します</li>");

        existsErr = true;
      }

      if (!password.equals(passwordConfirm)) {
        // request.setAttribute("errMsgPrice", "値段は0より大きいものを入力してください");
        if (!existsErr) {
          errMsg.append("<ul>");
        }
        errMsg.append("<li>パスワードが一致しません</li>");

        existsErr = true;
      }
      // - - - - - - - - - - - - -


      /*
       * if (UserHelper.isEmpty(loginId, password, passwordConfirm, userName)) {
       * request.setAttribute("errMsg", "必須項目に空欄のものがあります"); existsErr = true; }
       * 
       * // !UserHelper.isSame(password, passwordConfirm) if (!password.equals(passwordConfirm)) {
       * request.setAttribute("errMsgPassword", "パスワードが一致しません"); existsErr = true; }
       * 
       * if (UserDAO.existsLoginId(loginId)) { request.setAttribute("errMsgLoginId",
       * "同じログインIDの人が既に存在します"); existsErr = true; }
       */

      // パスワード暗号化
      String encodedPassword = ExpenseHelper.encodePassword(password);


      if (existsErr) {
        errMsg.append("</ul>");
        request.setAttribute("errMsg", String.valueOf(errMsg));
        request.setAttribute("loginId", loginId);
        request.setAttribute("password", password);
        request.setAttribute("userName", userName);
        request.getRequestDispatcher(ExpenseHelper.USER_ADD_PAGE).forward(request, response);
        return;
      }

      // 問題がない場合、UserDAOにユーザーを追加するメソッドを追加して
      // LoginServletへリダイレクト

      UserDAO.addUser(loginId, userName, encodedPassword);
      response.sendRedirect("LoginServlet");

	}

}
