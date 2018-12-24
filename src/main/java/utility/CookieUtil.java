package utility;

import javax.servlet.http.Cookie;

public class CookieUtil {
    private static CookieUtil ourInstance = new CookieUtil();

    public static CookieUtil getInstance() {
        return ourInstance;
    }

    private CookieUtil() {
    }

    public String findCookie(Cookie [] cookies, String name){
        if (cookies==null){return null;}

        for (int i = 0; i < cookies.length; i++) {
            Cookie cookie = cookies[i];
            if (cookie.getName().equals(name)) {
                return cookie.getValue();
            }
        }
        return null;
    }
}
