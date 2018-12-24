package html;

import model.Goods;

import java.util.List;

public class MallHtml {
    private static String html = "<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <title>mall</title>\n" +
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
            "\n" +
            "    </style>\n" +
            "\n" +
            "</head>\n" +
            "<body>\n" +
            "<header>\n" +
            "    <h1>商场</h1>\n" +
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
            "<div>\n" +
            "    <form id=\"category-form\" action=\"/Mall?jid\" method=\"get\">\n" +
            "        <select form=\"category-form\" name=\"category\">\n" +
            "            <option value =\"全部\">全部</option>\n" +
            "            <option value =\"男装\">男装</option>\n" +
            "            <option value=\"饮料\">饮料</option>\n" +
            "            <option value=\"手机\">手机</option>\n" +
            "        </select>\n" +
            "        <input type=\"hidden\" name=\"op\" value=\"category\">\n" +
            "        <input type=\"submit\" value=\"choose\">\n" +
            "    </form>\n" +
            "</div>\n" +
            "\n" +
            "<div class=\"container\">\n" +
            "\n" +
            "        <div class=\"rTable\">\n" +
            "            <div class=\"rTableHeading\">\n" +
            "                <div class=\"rTableHead\">种类</div>\n" +
            "                <div class=\"rTableHead\">商品id</div>\n" +
            "                <div class=\"rTableHead\">商品名</div>\n" +
            "                <div class=\"rTableHead\">单价</div>\n" +
            "                <div class=\"rTableHead\">描述</div>\n" +
            "                <div class=\"rTableHead\">购买数量</div>\n" +
            "                <!--<div class=\"rTableHead\">#</div>-->\n" +
            "            </div>\n" +
            "\n" +
            "            <div class=\"rTableBody\">\n" +
            "                ?goods\n" +
            "                \n" +
            "            </div>\n" +
            "        </div>\n" +
            "    <!-- pin -->\n" +
            "\n" +
            "\n" +
            "</div>\n" +
            "\n" +
            "<table>\n" +
            "    <tr>\n" +
            "        <td>\n" +
            "            <form action=\"/Mall?jid\" method=\"get\">\n" +
            "                <!-- pin -->\n" +
            "                <input type=\"hidden\" name=\"op\" value=\"last\">\n" +
            "                <input type=\"submit\" value=\"<-\" style=\"white-space: nowrap\">\n" +
            "            </form>\n" +
            "        </td>\n" +
            "        <td>\n" +
            "            <form action=\"/Mall?jid\" method=\"get\" style=\"white-space: nowrap\">\n" +
            "                <!-- pin -->\n" +
            "                <input type=\"hidden\" name=\"op\" value=\"next\">\n" +
            "                <input type=\"submit\" value=\"->\">\n" +
            "            </form>\n" +
            "        </td>\n" +
            "    </tr>\n" +
            "</table>\n" +
            "\n" +
            "<div>\n" +
            "    <form action=\"/Cart?jid\" method=\"get\">\n" +
            "        <input type=\"submit\" value=\"购物车\">\n" +
            "        <!-- pin -->\n" +
            "        <input type=\"hidden\" name=\"op\" value=\"cart\">\n" +
            "    </form>\n" +
            "</div>\n" +
            "\n" +
            "</body>\n" +
            "</html>";


    public static String getHtml(List<Goods> goodsList, Long uid, String jid){
        String ret;
        ret = html.replaceAll("\\?goods", generateList(goodsList, uid));
        ret = ret.replaceAll("\\?jid", jid);
        return ret;
    }

    private static String generateList(List<Goods> goodsList, Long uid){
        StringBuilder stb = new StringBuilder();
        if (goodsList == null){return "";}
        for (Goods goods:goodsList){
            stb.append(generate(goods, uid));
//            System.out.println(generate(goods,uid ));
        }
        return stb.toString();
    }

    private static String generate(Goods goods, Long uid){
        StringBuilder bd = new StringBuilder();
        String s = " </form>\n" +
                "                    </div>\n" +
                "                </div>\n";
        bd.append("            <div class=\"rTableRow\">\n")
            .append("                 <div class=\"rTableCell\">").append(goods.getCategory()).append("</div>\n")
            .append("                <div class=\"rTableCell\">").append(goods.getGid()).append("</div>\n")
            .append("                <div class=\"rTableCell\">").append(goods.getGname()).append("</div>\n")
            .append("                <div class=\"rTableCell\">").append(goods.getPrice()).append("</div>\n")
            .append("                <div class=\"rTableCell\">").append(goods.getDescription()).append("</div>\n")
            .append("                <div class=\"rTableCell\">")
            .append("                <form action=\"/Mall?jid\" method=\"get\">\n")
            .append("                <div class=\"rTableCell\"><input type=\"number\" name=\"num\" min=\"0\" style=\"text-align: center\"></div>\n")
            .append("                <div class=\"rTableCell\"><input type=\"submit\" value=\"加入购物车\"></div>\n")
            .append("                <input type=\"hidden\" name=\"gid\" value=\"").append(goods.getGid()).append("\">\n")
            .append("                <input type=\"hidden\" name=\"op\" value=\"add\">\n")
            .append(s);
        return bd.toString();
    }
}
