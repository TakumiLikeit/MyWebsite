package util;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.bind.DatatypeConverter;

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
  // ユーザー追加ページ
  public static final String USER_ADD_PAGE = "/WEB-INF/jsp/userAdd.jsp";
  // ユーザー詳細ページ
  public static final String USER_DETAIL_PAGE = "/WEB-INF/jsp/userDetail.jsp";
  // ユーザー編集ページ
  public static final String USER_UPDATE_PAGE = "/WEB-INF/jsp/userUpdate.jsp";
  // ユーザー退会ページ
  public static final String USER_WITHDRAW_PAGE = "/WEB-INF/jsp/userWithdraw.jsp";



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

  // MD5、アルゴリズムによるパスワード暗号化
  public static String encodePassword(String password) {

    Charset charset = StandardCharsets.UTF_8; // ハッシュ生成前にバイト配列に置き換える際のCharset
    String algorithm = "MD5"; // ハッシュ関数の種類

    try {
      byte[] bytes = MessageDigest.getInstance(algorithm).digest(password.getBytes(charset));
      String encodeStr = DatatypeConverter.printHexBinary(bytes);

      // System.out.println(encodeStr);
      return encodeStr;

    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }

    return "password";
  }

  // String.matchesによる、数字かどうかを判別するメソッド
  public static boolean isNumeric(String str) {
    return str.matches("[+-]?\\d*(\\.\\d+)?");
  }

  // + - のサインだけで入力されたかどうかを判別するメソッド
  public static boolean isOnlySign(String str) {
    if (str.equals("+") || str.equals("-")) {
      return true;
    } else {
      return false;
    }
  }

  // 0以下かどうかを判別するメソッド（0未満の値はisNumericで処理されるよう）
  public static boolean isNegative(String str) {

    if (str.equals("")) {
      System.out.println("price: \"\" (priceは空欄)");
      return false;
    } else {
      int value = Integer.valueOf(str);
      if (value <= 0) {
        return true;
      } else {
        return false;
      }
    }
  }





}
