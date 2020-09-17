import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.StandardTokenizer;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 回答处理器
 */
public class AnswerProcessor {

    /**
     * 测试时使用的控制台输出
     * @param ans LCS算法计算结果
     * @param standardLength 原文文件字数
     */
    private static String mockPrint(int ans,int standardLength){
        float num = (float)ans/standardLength;
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        String result = decimalFormat.format(num);
        System.out.println("查重率为："+result);
        return result;
    }

    /**
     * 输出文件
     * @param outputPath 输出文件路径
     * @param result 字符串形式传入结果
     */
    public static void output(String outputPath,String result){
        File f = new File(outputPath);
        try (FileWriter fr = new FileWriter(f)) {
            String data = result;
            char[] cs = data.toCharArray();
            fr.write(cs);
            fr.close();
            System.out.println("结果已写入"+outputPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 把字符串分词到term列表
     * @param text 待处理的文本
     * @return 返回split后的term列表
     */
    private static List<Term> split(String text){
        return StandardTokenizer.segment(text);
    }

    /**
     * 移除标点符号
     * @param terms 输入terms列表
     * @return 返回移除后的字符串列表
     */
    private static List<String> removePunctuation(List<Term> terms){
        List<String> strings = new ArrayList<String>();
        for(Term term:terms){
            if(!term.toString().endsWith("/w")){
                strings.add(term.word);
            }
        }
        return strings;
    }

    /**
     * 处理总流程方法（Jaro+LCS）
     * @param orgFileName 原文文件路径
     * @param orgAddFileName 抄袭版论文的文件路径
     * @param outputPath 输出文件路径
     */
    public static void process(String orgFileName,String orgAddFileName,String outputPath){
        String org = TextProcessor.transferTextToString(orgFileName);
        String orgAdd = TextProcessor.transferTextToString(orgAddFileName);
        List<String> orgList = removePunctuation(split(org));
        List<String> orgAddList = removePunctuation(split(orgAdd));
        int standardLength = orgList.size();
        int ans = AlgorithmProcessor.JaroDisBasedOnLCS(orgList,orgAddList);
        output(outputPath,mockPrint(ans,standardLength));
        if(ans/standardLength==1.00 && !orgFileName.equals(orgAddFileName)){
            try {
                throw new ResultEqualsOneException("非相同文本，查重率怎么会为1呢？");
            } catch (ResultEqualsOneException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 处理总流程方法（纯Jaro）
     * @param orgFileName 原文文件路径
     * @param orgAddFileName 抄袭版论文的文件路径
     * @param outputPath 输出文件路径
     */
    public static void processJustByJaro(String orgFileName,String orgAddFileName,String outputPath){
        String org = TextProcessor.transferTextToString(orgFileName);
        String orgAdd = TextProcessor.transferTextToString(orgAddFileName);
        String result = String.valueOf(StringUtils.getJaroWinklerDistance(org,orgAdd));
        System.out.println("查重结果为："+result);
        output(outputPath,result);
    }

    /**
     * 处理总流程方法（纯LCS）
     * @param orgFileName 原文文件路径
     * @param orgAddFileName 抄袭版论文的文件路径
     * @param outputPath 输出文件路径
     */
    public static void processJustByLCS(String orgFileName,String orgAddFileName,String outputPath){
        String org = TextProcessor.transferTextToString(orgFileName);
        String orgAdd = TextProcessor.transferTextToString(orgAddFileName);
        int standardLength = org.length();
        int ans = AlgorithmProcessor.LCS(org,orgAdd);
        output(outputPath,mockPrint(ans,standardLength));
        if(ans/standardLength==1.00 && !orgFileName.equals(orgAddFileName)){
            try {
                throw new ResultEqualsOneException("非相同文本，查重率怎么会为1呢？");
            } catch (ResultEqualsOneException e) {
                e.printStackTrace();
            }
        }
    }
}
