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

      System.out.println(encodeStr);
      return encodeStr;

    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }

    return "password";
  }

}
