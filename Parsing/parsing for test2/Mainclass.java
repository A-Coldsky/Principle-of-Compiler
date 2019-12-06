import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Mainclass {
    static ArrayList<String> strArray = new ArrayList<String>();
    static int arrynum = 0;
    static int num = 0;
    static String words;
    static ArrayList<String> impotWords =
            new ArrayList<String>(Arrays.asList("begin", "if", "then", "end", "a", "b"));

    public static void main(String[] args) {
        InputStreamReader isr = null;
        BufferedReader br = null;
        File filename = new File("D:\\codetest\\java for work\\parsing for test2\\src\\text.txt");
        try {
            isr = new InputStreamReader(new FileInputStream(filename));
            br = new BufferedReader(isr);
            String line = "";
            line = br.readLine();
            while (line != null) {
                strArray.add(line);
                line = br.readLine();
            }
            br.close();
            isr.close();
        } catch (IOException e) {

        }//读取文件
        words=getwords();
        ParseS();
        if (words.equals("#")){
            System.out.println("sucess!!");
        }
        else error();
    }

    static String getwords() {
        String word = "";
        while (arrynum < strArray.size()) {
            word = "";
            String str = strArray.get(arrynum);
            while (num <= str.length()) {
                    while (num < str.length() && (str.charAt(num) == '\t' || str.charAt(num) == ' ')) {
                        num++;
                    }
                if (num >= str.length()) {
                    num = 0;
                    arrynum++;
                    break;
                } else if (Character.isLowerCase(str.charAt(num)) || Character.isUpperCase(str.charAt(num)) || Character.isDigit(str.charAt(num))) {
                    word += Character.toString(str.charAt(num));
                    num++;
                    while ((num < str.length()) && (Character.isLowerCase(str.charAt(num)) || Character.isUpperCase(str.charAt(num)) || Character.isDigit(str.charAt(num)))) {
                        word += Character.toString(str.charAt(num));
                        num++;
                    }
                    for (String important : impotWords) {
                        if (word.equals(important)) {
                            return word;
                        }
                    }
                } else if (str.charAt(num) == '#' || str.charAt(num) == ';') {
                    word += Character.toString(str.charAt(num));
                    num++;
                    return word;
                } else {
                    num++;
                    error1();
                }

            }
        }
        error1();
        return word;
    }

    static void error1() {
        System.out.println("error word");
        System.exit(0);
    }

    static void error() {
        System.out.println("error pasing");
        System.exit(0);
    }
    static void Matchtoken(String adj){
        if (words.equals(adj)){
            words=getwords();
        }
        else error();
    }
    static void Matchtoken(String adj,String adj2){
        if (words.equals(adj)||words.equals(adj2)){
            words=getwords();
        }
        else error();
    }
    static void ParseS(){
        if (words.equals("begin")){
            Matchtoken("begin");
            ParseA();
            Matchtoken("end");
        }
        else error();
    }
    static void ParseA(){
        if (words.equals("a")||words.equals("if")){
            Matchtoken("a","if");
            ParseA1();
        }
        else error();
    }
    static void  ParseA1(){
        if (words.equals(";")){
            Matchtoken(";");
            ParseB();
            ParseA1();
        }
        else if (words.equals("end")){

        }
        else error();
    }
    static void ParseB(){
        if (words.equals("a")){
            ParseC();
        }
        else if (words.equals("if")){
            ParseD();
        }
        else error();
    }
    static void ParseC(){
        if (words.equals("a")){
            Matchtoken("a");
        }
        else error();
    }
    static void ParseD(){
        if (words.equals("if")){
            ParseE();
            ParseD1();
        }
        else error();
    }
    static void ParseD1(){
        if (words.equals("else")){
            Matchtoken("else");
            ParseB();
        }
        else if (words.equals("#")){

        }
        else error();
    }
    static void ParseE(){
        if (words.equals("if")){
            Matchtoken("if");
            Matchtoken("b");
            Matchtoken("then");
        }
    }
}


