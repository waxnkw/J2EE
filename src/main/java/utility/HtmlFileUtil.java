package utility;

import java.io.*;

public class HtmlFileUtil {
    private static HtmlFileUtil ourInstance = new HtmlFileUtil();

    public static HtmlFileUtil getInstance() {
        return ourInstance;
    }

    private HtmlFileUtil() {
    }

    public String readHtmlFile(String path){
        File file = new File(path);
        FileReader in;
        StringBuilder strb = new StringBuilder();
        String line = null;

        if (!file.isFile()){return null;}

        try {
            in = new FileReader(file);
            BufferedReader reader = new BufferedReader(in);
            while((line=reader.readLine())!=null){
                strb.append(line);
                strb.append(System.lineSeparator());
            }

            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strb.toString();
    }
}
