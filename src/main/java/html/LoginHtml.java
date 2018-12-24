package html;

public class LoginHtml {
    private static String html = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "<head>\n" +
            "    <meta charset=\"utf-8\">\n" +
            "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\">\n" +
            "    <title>Login</title>\n" +
            "\n" +
            "    <!-- bootstrap+jQuery -->\n" +
            "    <!--CDN-->\n" +
            "    <link href=\"https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css\" rel=\"stylesheet\">\n" +
            "    <!--<script src=\"https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js\"></script>-->\n" +
            "    <!--<script src=\"https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>-->\n" +
            "\n" +
            "</head>\n" +
            "<body>\n" +
            "\n" +
            "    <header class=\"page-header text-center\">\n" +
            "        <h1> 登录页面 </h1>\n" +
            "    </header>\n" +
            "\n" +
            "    <div class=\"container\">\n" +
            "        <form action=\"/Login\" method=\"post\">\n" +
            "            <label class=\"col-sm-2 control-label\">用户id</label>\n" +
            "            <input type=\"number\" class=\"form-control\" name=\"uid\" value=\"?uid\" placeholder=\"请输入用户名\">\n" +
            "            <label class=\"col-sm-2 control-label\">密码</label>\n" +
            "            <input type=\"password\" class=\"form-control\" name=\"password\" placeholder=\"请输入密码\">\n" +
            "\n" +
            "            </br>\n" +
            "\n" +
            "            <div align=\"center\">\n" +
            "                <input type=\"submit\" class=\"btn btn-primary btn-lg btn-block login-button\" value=\"登录\">\n" +
            "            </div>\n" +
            "        </form>\n" +
            "    </div>\n" +
            "\n" +
            "</body>\n" +
            "</html>\n";

    public static String getHtml(String uid){
        String name = uid==null?"":uid+"";
        String ret = html.replaceAll("\\?uid", name);
        return ret;
    }
}
