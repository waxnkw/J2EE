package html;

import model.BuyInfo;

import java.util.List;

public class CartHtml {
    private static String html = "<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <title>Cart</title>\n" +
            "\n" +
            "    <style type=\"text/css\">\n" +
            "        .rTable {\n" +
            "            display: table;\n" +
            "            width: 80%;\n" +
            "        }\n" +
            "        .rTableRow {\n" +
            "            display: table-row;\n" +
            "        }\n" +
            "        .rTableHeading {\n" +
            "            display: table-header-group;\n" +
            "            background-color: #ddd;\n" +
            "        }\n" +
            "        .rTableCell, .rTableHead {\n" +
            "            display: table-cell;\n" +
            "            padding: 3px 10px;\n" +
            "            border: 1px solid #999999;\n" +
            "        }\n" +
            "        .rTableHeading {\n" +
            "            display: table-header-group;\n" +
            "            background-color: #ddd;\n" +
            "            font-weight: bold;\n" +
            "        }\n" +
            "        .rTableFoot {\n" +
            "            display: table-footer-group;\n" +
            "            font-weight: bold;\n" +
            "            background-color: #ddd;\n" +
            "        }\n" +
            "        .rTableBody {\n" +
            "            display: table-row-group;\n" +
            "        }\n" +
            "    </style>\n" +
            "\n" +
            "</head>\n" +
            "<body>\n" +
            "<header>\n" +
            "    <h1>购物车</h1>\n" +
            "</header>\n" +
            "\n" +
            "<div style=\"position: relative\">\n" +
            "    <form action=\"/Logout?jid\" method=\"get\">\n" +
            "        <input type=\"submit\" value=\"登出\" style=\"color: red; position: absolute; top: 10px; right: 10px\">\n" +
            "    </form>\n" +
            "</div>\n" +
            "\n" +
            "</br>\n" +
            "\n" +
            "<div class=\"container\">\n" +
            "\n" +
            "    <div class=\"rTable\">\n" +
            "        <div class=\"rTableHeading\">\n" +
            "            <div class=\"rTableHead\">种类</div>\n" +
            "            <div class=\"rTableHead\">商品id</div>\n" +
            "            <div class=\"rTableHead\">商品名</div>\n" +
            "            <div class=\"rTableHead\">单价</div>\n" +
            "            <div class=\"rTableHead\">描述</div>\n" +
            "            <div class=\"rTableHead\">购买数量</div>\n" +
            "            <div class=\"rTableHead\">花费</div>\n" +
            "            <div class=\"rTableHead\">#</div>\n" +
            "        </div>\n" +
            "\n" +
            "        <div class=\"rTableBody\">\n" +
            "            ?buyinfos\n" +
            "\n" +
            "            <div class=\"rTableFoot\">\n" +
            "                <div class=\"rTableCell\">总共</div>\n" +
            "                <div class=\"rTableCell\">?cost</div>\n" +
            "            </div>\n" +
            "\n" +
            "        </div>\n" +
            "    </div>\n" +
            "</div>\n" +
            "\n" +
            "<div>\n" +
            "    <form action=\"/Cart?jid\" method=\"get\">\n" +
            "        <input type=\"submit\" value=\"结账\">\n" +
            "        <input type=\"hidden\" name=\"op\" value=\"buy\">\n" +
            "    </form>\n" +
            "</div>\n" +
            "\n" +
            "<div>\n" +
            "    <form action=\"/Mall?jid\" method=\"get\">\n" +
            "        <input type=\"submit\" value=\"商场\">\n" +
            "        <input type=\"hidden\" name=\"op\" value=\"mall\">\n" +
            "    </form>\n" +
            "</div>\n" +
            "\n" +
            "</body>\n" +
            "</html>";


    public static String getHtml(List<BuyInfo> buyInfoList, Long uid, String jid, Double cost){
        String ret;
        ret = html.replaceAll("\\?cost", cost+"");
        ret = ret.replaceFirst("\\?buyinfos", generateList(buyInfoList, uid));
        ret = ret.replaceAll("\\?jid", jid);
        return ret;
    }

    protected static String generateList(List<BuyInfo> buyInfoList, Long uid){
        StringBuilder stb = new StringBuilder();
        if (buyInfoList == null){return "";}
        for (BuyInfo buyInfo:buyInfoList){
            stb.append(generate(buyInfo, uid));
//            System.out.println(generate(buyInfo,uid ));
        }
//        System.out.println(stb.toString());
        return stb.toString();
    }

    private static String generate(BuyInfo buyInfo, Long uid){
        StringBuilder bd = new StringBuilder();
        String s = " </form>\n" +
                "                    </div>\n" +
                "                </div>\n";
        bd.append("            <div class=\"rTableRow\">\n")
                .append("                 <div class=\"rTableCell\">").append(buyInfo.getCategory()).append("</div>\n")
                .append("                <div class=\"rTableCell\">").append(buyInfo.getGid()).append("</div>\n")
                .append("                <div class=\"rTableCell\">").append(buyInfo.getGname()).append("</div>\n")
                .append("                <div class=\"rTableCell\">").append(buyInfo.getPrice()).append("</div>\n")
                .append("                <div class=\"rTableCell\">").append(buyInfo.getDescription()).append("</div>\n")
                .append("                <div class=\"rTableCell\">").append(buyInfo.getNum()).append("</div>\n")
                .append("                <div class=\"rTableCell\">").append(buyInfo.getCost()).append("</div>\n")
                .append("                <div class=\"rTableCell\">")
                .append("                <form action=\"/Cart?jid\" method=\"get\">\n")
                .append("<div class=\"rTableCell\"><input type=\"submit\" value=\"删除\"></div>\n" )
                .append("                        <input type=\"hidden\" name=\"gid\" value=\"").append(buyInfo.getGid()).append("\">\n")
                .append("                        <input type=\"hidden\" name=\"op\" value=\"delete\">")
                .append(s);
        return bd.toString();
    }
}
