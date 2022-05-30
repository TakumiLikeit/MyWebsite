package util;

public class UserHelper {


  // ユーザーの入力項目に空欄がないか確認するメソッド（UserAddServlet内で使用）
  public static boolean isEmpty (String loginId, String password, String passwordConfirm, String userName) {
    
    if (loginId.equals("") || password.equals("") || passwordConfirm.equals("")
        || userName.equals("")) {
    
      return true;
    } else {
      return false;
    }
  }

  // String型のデータが２つとも空欄でないか確認するメソッド（UserDAO内で使用）
  public static boolean isEmptyBoth(String str1, String str2) {

    if (str1.equals("") && str2.equals("")) {
      return true;
    } else {
      return false;
    }
  }

}
