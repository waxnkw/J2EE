package html;

public class BuyFailedHtml {
    private static String html = "<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <title>Title</title>\n" +
            "</head>\n" +
            "<body>\n" +
            "<header>\n" +
            "    <h1>\n" +
            "        购买失败\n" +
            "    </h1>\n" +
            "    <h2>\n" +
            "        余额： ?remain\n" +
            "    </h2>\n" +
            "</header>\n" +
            "\n" +
            "\n" +
            "<div>\n" +
            "    <form action=\"/Cart?jid\" method=\"get\">\n" +
            "        <input type=\"submit\" value=\"返回\">\n" +
            "    </form>\n" +
            "</div>\n" +
            "\n" +
            "</body>\n" +
            "</html>";

    public static String getHtml(String jid, Double remain){
        String ret = html.replaceAll("\\?remain", remain+"");
        ret = ret.replaceAll("\\?jid", jid);
        return ret;
    }
}
