package html;

import model.BuyInfo;

import java.util.List;

public class BuySuccessHtml {
    private static String html = "<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <title>buySuccess</title>\n" +
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
            "    \n" +
            "</head>\n" +
            "<body>\n" +
            "<header>\n" +
            "    <h1>购买成功</h1>\n" +
            "    <h2>商品总价： ?cost</h2>\n" +
            "    <h2>实际花销： ?actual</h2>\n" +
            "    <h2>促销状况： ?rate</h2>\n" +
            "    <h2>余额： ?remain</h2>\n" +
            "</header>\n" +
            "\n" +
            "<div class=\"rTable\">\n" +
            "    <div class=\"rTableHeading\">\n" +
            "        <div class=\"rTableHead\">种类</div>\n" +
            "        <div class=\"rTableHead\">商品id</div>\n" +
            "        <div class=\"rTableHead\">商品名</div>\n" +
            "        <div class=\"rTableHead\">单价</div>\n" +
            "        <div class=\"rTableHead\">描述</div>\n" +
            "        <div class=\"rTableHead\">购买数量</div>\n" +
            "        <div class=\"rTableHead\">花费</div>\n" +
            "    </div>\n" +
            "\n" +
            "    <div class=\"rTableBody\">\n" +
            "        ?buyinfos\n" +
            "\n" +
            "        <div class=\"rTableFoot\">\n" +
            "            <div class=\"rTableCell\">总共</div>\n" +
            "            <div class=\"rTableCell\">?cost</div>\n" +
            "        </div>\n" +
            "    </div>\n" +
            "</div>\n" +
            "\n" +
            "<div>\n" +
            "    <form action=\"/Mall?jid\" method=\"get\">\n" +
            "        <input type=\"submit\" value=\"返回\">\n" +
            "    </form>\n" +
            "</div>\n" +
            "\n" +
            "</body>\n" +
            "</html>";

    public static String getHtml(List<BuyInfo> buyInfoList,
                                 Long uid, String jid,
                                 Double cost,
                                 Double actual,
                                 Double rate,
                                 Double remain){
        String ret;
        ret = html.replaceAll("\\?buyinfos", generateList(buyInfoList, uid));
        ret = ret.replaceAll("\\?cost", cost+"");
        ret = ret.replaceAll("\\?actual", actual+"");
        ret = ret.replaceAll("\\?rate", rate==null?"无":rate+"");
        ret = ret.replaceAll("\\?remain", remain+"");
        ret = ret.replaceAll("\\?jid", jid);
        return ret;
    }

    protected static String generateList(List<BuyInfo> buyInfoList, Long uid){
        StringBuilder stb = new StringBuilder();
        if (buyInfoList == null){return "";}
        for (BuyInfo buyInfo:buyInfoList){
            stb.append(generateBuyInfo(buyInfo, uid));
        }
        System.out.println(stb.toString());
        return stb.toString();
    }

    private static String generateBuyInfo(BuyInfo buyInfo, Long uid){
        StringBuilder bd = new StringBuilder();
        String s = "                    </div>\n" +
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
                .append(s);
        return bd.toString();
    }
}
