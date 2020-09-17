import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 文本处理器
 */
public class TextProcessor {

    /**
     * 将txt文本转化为内存中的字符串
     * @param filepath 为文件名
     * @return 将该文件转化成的字符串
     */
    public static String transferTextToString(String filepath){
        String str="";
        File file = new File(filepath);
        try {
            FileInputStream in=new FileInputStream(file);
            int size = in.available();
            byte[] buffer=new byte[size];
            in.read(buffer);
            in.close();
            str=new String(buffer,"UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }


}
