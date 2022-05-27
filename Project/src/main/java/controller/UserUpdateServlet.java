package controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import beans.UserDataBeans;
import dao.UserDAO;
import util.ExpenseHelper;
import util.UserHelper;

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
      HttpSession session = request.getSession();
      UserDataBeans udb = (UserDataBeans) session.getAttribute("userInfo");

      if (udb == null) {
        response.sendRedirect("LoginServlet");
        return;
      }

      // request.setAttribute("user", udb);

      // userUpdate.jspへフォワード
      request.getRequestDispatcher(ExpenseHelper.USER_UPDATE_PAGE).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      request.setCharacterEncoding("UTF-8"); // 文字化け防止

      // requestパラメターの取得
      // jspファイルでログインIDは変更できないようにしないといけない
      // String loginId = request.getParameter("login-id");
      String password = request.getParameter("password");
      String passwordConfirm = request.getParameter("password-confirm");
      String userName = request.getParameter("user-name");

      // ここで、jspからパスワードゲットしていない時には、passwordに入っている値は違う


      // 例外処理
      // 問題がある場合、requestパラメターに値をセットして、フォワード

      boolean existsErr = false;

      if (userName.equals("")) {
        request.setAttribute("errMsg", "ユーザー名が空欄です");
        existsErr = true;
      }

      if (!UserHelper.isSame(password, passwordConfirm)) {
        request.setAttribute("errMsgPassword", "パスワードが一致しません");
        existsErr = true;
      }

      if (existsErr) {
        // request.setAttribute("loginId", loginId);
        request.setAttribute("password", password);
        request.setAttribute("userName", userName);
        request.getRequestDispatcher(ExpenseHelper.USER_UPDATE_PAGE).forward(request, response);
        return;
      }

      HttpSession session = request.getSession();
      UserDataBeans udb = (UserDataBeans) session.getAttribute("userInfo");

      int userId = udb.getId();
      String loginId = udb.getLoginId();



      // 問題がない場合、はUserDAOのメソッドによってデータを更新し、ユーザー詳細画面へリダイレクト
      // パスワードがどちらも、空欄かどうか（""かどうか）は、UserDAOのメソッド内で判断する
      UserDAO.updateUser(userId, password, passwordConfirm, userName);
      

      /*
       * String encodedPassword = password;
       * 
       * if (!password.equals("")) { encodedPassword = ExpenseHelper.encodePassword(password);
       * System.out.println("encodedPassword:"); System.out.println(encodedPassword); }
       * 
       * // パスワード暗号化
       */

      UserDataBeans updatedUdb = null;
      System.out.println("loginId: " + loginId);

      try {
        System.out.println("try-catchの中");


        // UserDAO内に、新しくメソッドを追加, ログインID（もしくはユーザーID）だけでユーザーのインスタンスを取得できるようにする


        updatedUdb = UserDAO.getUserById(userId);// ちゃんとupdateはできてるが、ここでudbがnullになっている
      } catch (SQLException e) {
        e.printStackTrace();
      }

      if (updatedUdb == null) {
        System.out.println("updatedUdb: null");
      } else {
        System.out.println("updatedUdb: not null");

      }

      // System.out.println("name: " + updatedUdb.getName());

      session.removeAttribute("userInfo");
      session.setAttribute("userInfo", updatedUdb);


      response.sendRedirect("UserDetailServlet");


	}

}
