package util;

public class ExpenseHelper {
  // ログインページ
  public static final String LOGIN_PAGE = "/WEB-INF/jsp/login.jsp";
  // 出費一覧ページ
  public static final String EXPENSE_LIST_PAGE = "/WEB-INF/jsp/expenseList.jsp";
  // 出費追加ページ
  public static final String EXPENSE_ADD_PAGE = "/WEB-INF/jsp/addExpense.jsp";
  // 出費編集ページ
  public static final String EXPENSE_UPDATE_PAGE = "/WEB-INF/jsp/updateExpense.jsp";
  // 出費削除ページ
  public static final String EXPENSE_DELETE_PAGE = "/WEB-INF/jsp/deleteExpense.jsp";


  // String, int, String, Date
  // 入力項目が空欄じゃないか確認

  public static boolean isEmpty(String expenseName, String price, String categoryId,
      String expenseDate) {

    if (expenseName.equals("") || price.equals("") || categoryId.equals("")
        || expenseDate.equals("")) {
      return true;
    } else {
      return false;
    }
  }

}
