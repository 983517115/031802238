import org.junit.Test;
import java.io.File;

/**
 * 测试
 */
public class TestCase {

    /**
     * 测试全部样例（纯Jaro）
     */
    @Test
    public void testForAllFilesOnlyJaro(){
        File f = new File("tests");
        String[] files = f.list();
        int cnt = 1;
        for(String file : files){
            if(!file.equals("orig.txt")){
                System.out.println("开始处理"+file);
                AnswerProcessor.processJustByJaro("tests/orig.txt","tests/"+file,"result/ans"+cnt+".txt");
                cnt++;
            }
        }
    }

    /**
     * 测试全部样例（纯LCS）
     */
    @Test
    public void testForAllFilesOnlyLCS(){
        File f = new File("tests");
        String[] files = f.list();
        int cnt = 1;
        for(String file : files){
            if(!file.equals("orig.txt")){
                System.out.println("开始处理"+file);
                AnswerProcessor.processJustByLCS("tests/orig.txt","tests/"+file,"result/ans"+cnt+".txt");
                cnt++;
            }
        }
    }


    /**
     * 测试全部样例（Jaro+LCS）
     */
    @Test
    public void testAllFiles(){
        File f = new File("tests");
        String[] files = f.list();
        int cnt = 1;
        for(String file : files){
            if(!file.equals("orig.txt")){
                System.out.println("开始处理"+file);
                AnswerProcessor.process("tests/orig.txt","tests/"+file,"result/ans"+cnt+".txt");
                cnt++;
            }
        }
    }

    /**
     * 测试20%文本添加情况：orig_0.8_add.txt
     */
    @Test
    public void testForAdd(){
        AnswerProcessor.process("tests/orig.txt","tests/orig_0.8_add.txt","ans.txt");
    }

    /**
     * 测试20%文本删除情况：orig_0.8_del.txt
     */
    @Test
    public void testForDel(){
        AnswerProcessor.process("tests/orig.txt","tests/orig_0.8_del.txt","ans.txt");
    }

    /**
     * 测试20%文本乱序情况：orig_0.8_dis_1.txt
     */
    @Test
    public void testForDis1(){
        AnswerProcessor.process("tests/orig.txt","tests/orig_0.8_dis_1.txt","ans.txt");
    }

    /**
     * 测试20%文本乱序情况：orig_0.8_dis_3.txt
     */
    @Test
    public void testForDis3(){
        AnswerProcessor.process("tests/orig.txt","tests/orig_0.8_dis_3.txt","ans.txt");
    }

    /**
     * 测试20%文本乱序情况：orig_0.8_dis_7.txt
     */
    @Test
    public void testForDis7(){
        AnswerProcessor.process("tests/orig.txt","tests/orig_0.8_dis_7.txt","ans.txt");
    }

    /**
     * 测试20%文本乱序情况：orig_0.8_dis_10.txt
     */
    @Test
    public void testForDis10(){
        AnswerProcessor.process("tests/orig.txt","tests/orig_0.8_dis_10.txt","ans.txt");
    }

    /**
     * 测试20%文本乱序情况：orig_0.8_dis_15.txt
     */
    @Test
    public void testForDis15(){
        AnswerProcessor.process("tests/orig.txt","tests/orig_0.8_dis_15.txt","ans.txt");
    }

    /**
     * 测试20%文本格式错乱情况：orig_0.8_mix.txt
     */
    @Test
    public void testForMix(){
        AnswerProcessor.process("tests/orig.txt","tests/orig_0.8_mix.txt","ans.txt");
    }

    /**
     * 测试20%文本错别字情况：orig_0.8_rep.txt
     */
    @Test
    public void testForRep(){
        AnswerProcessor.process("tests/orig.txt","tests/orig_0.8_rep.txt","ans.txt");
    }

    /**
     * 测试相同文本：orig.txt
     */
    @Test
    public void testForSame(){
        AnswerProcessor.process("tests/orig.txt","tests/orig.txt","ans.txt");
    }

    /**
     * 测试20%文本添加情况：orig_0.8_add.txt
     */
    @Test
    public void testForAddByLCS(){
        AnswerProcessor.processJustByLCS("tests/orig.txt","tests/orig_0.8_add.txt","ans.txt");
    }
}
