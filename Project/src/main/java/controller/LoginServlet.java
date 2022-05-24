package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
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
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        UserDataBeans udb = (UserDataBeans) session.getAttribute("user");

        if (udb != null) {
          response.sendRedirect("UserListServlet");
          return;
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(ExpenseHelper.LOGIN_PAGE);
        dispatcher.forward(request, response);
	}

    /** @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response) */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
      request.setCharacterEncoding("UTF-8"); // リクエストパラメータの文字化け防止

      String loginId = request.getParameter("login-id");
      String password = request.getParameter("password");

      // パスワードの暗号化

      try {

        UserDataBeans udb = UserDAO.getUser(loginId, password);

        /** テーブルに該当のデータが見つからなかった場合 * */
        if (udb == null) {
          request.setAttribute("errMsg", "ログインIDまたはパスワードが異なります。");
          request.setAttribute("loginId", loginId);
          request.setAttribute("password", password);

          // login.jspへフォワード
          request.getRequestDispatcher(ExpenseHelper.LOGIN_PAGE).forward(request, response);
          return;
        }

        /** テーブルに該当のデータが見つかった場合 * */
        // セッションにユーザの情報をセット
        HttpSession session = request.getSession();
        session.setAttribute("userInfo", udb);

        // 出費一覧のサーブレットにリダイレクト
        response.sendRedirect("ExpenseListServlet");

      } catch (Exception e) {
        e.printStackTrace();
        // session.setAttribute("errorMessage", e.toString());
        // response.sendRedirect("Error");
      }

    }


}
