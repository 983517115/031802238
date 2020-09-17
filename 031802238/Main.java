/**
 * 主函数入口
 */
public class Main {

    public static void main(String[] args) {
        AnswerProcessor.process(args[0],args[1],args[2]);
        //AnswerProcessor.process("tests/orig.txt","tests/orig_0.8_add.txt","ans.txt");
        System.exit(0);
    }

}