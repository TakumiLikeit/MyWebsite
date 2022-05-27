package util;

public class UserHelper {

  public static boolean isSame(String password, String passwordConfirm) {

    if (password.equals(passwordConfirm)) {
      return true;
    } else {
      return false;
    }
  }

  public static boolean isEmpty (String loginId, String password, String passwordConfirm, String userName) {
    
    if (loginId.equals("") || password.equals("") || passwordConfirm.equals("")
        || userName.equals("")) {
    
      return true;
    } else {
      return false;
    }
  }

  public static boolean isEmptyBoth(String str1, String str2) {

    if (str1.equals("") && str2.equals("")) {
      return true;
    } else {
      return false;
    }
  }

  public static boolean isEmptyEither(String str1, String str2) {

    if (str1.equals("") || str2.equals("")) {
      return true;
    } else {
      return false;
    }
  }

}
