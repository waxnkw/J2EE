package utilityTest;


import org.junit.Test;
import utility.HtmlFileUtil;

public class HtmlFIleUtilityTest {
    @Test
    public void testRead(){
        String path = "/usr/local/ideaProjs/shoppingCart/src/main/webapp/html/Login.html";
        System.out.println(HtmlFileUtil.getInstance().readHtmlFile(path));
    }
}
