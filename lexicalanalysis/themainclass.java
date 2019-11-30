import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class themainclass {

    static ArrayList<String> impotWords =
            new ArrayList<String>(Arrays.asList("begin", "if", "then", "end", "while", "do"));
    static ArrayList<String> Sign =
            new ArrayList<String>(Arrays.asList("=", "+", "-", "*", "/", ">", "<", ">=", "<=", "!=", "=="));

    public static void main(String[] args) {

        InputStreamReader isr = null;
        ArrayList<String> strArray = new ArrayList<String>();
        ArrayList<Words> secfuc = new ArrayList<Words>();
        BufferedReader br = null;
        File filename = new File("D:\\codetest\\java for work\\lexicalanalysis\\src\\resources\\test.txt");
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
        } finally {

        }
        for (String str : strArray) {
            int i = 0;
            int abc = str.length();
            while (i < str.length()) {
                String word = "";
                while (i<str.length()&&(str.charAt(i) == '\t' || str.charAt(i) == ' ')) {
                    i++;
                }
                if (i>=str.length()) break;
                 else if (Character.isLowerCase(str.charAt(i)) || Character.isUpperCase(str.charAt(i))) {
                    word += Character.toString(str.charAt(i));
                    i++;
                    while ((i<str.length())&&(Character.isLowerCase(str.charAt(i)) || Character.isUpperCase(str.charAt(i)) || Character.isDigit(str.charAt(i)))) {
                        word += Character.toString(str.charAt(i));
                        i++;
                    }
                    for (String important : impotWords) {
                        if (word.equals(important)) {
                            Words words = new Words(word, "sym" + word);
                            secfuc.add(words);
                            word = "";
                            break;
                        }
                    }
                    if (!word.isEmpty()) {
                        Words words = new Words(word, "indent");
                        secfuc.add(words);
                    }
                }//处理保留字和标识符
                else if (Character.isDigit(str.charAt(i))) {
                    word += Character.toString(str.charAt(i));
                    i++;
                    while (i<str.length()&&Character.isDigit(str.charAt(i))) {
                        word += Character.toString(str.charAt(i));
                        i++;
                    }
                    Words words = new Words(word, "number");
                    secfuc.add(words);
                }//识别数字
                else if (str.charAt(i) == ';') {
                    word += Character.toString(str.charAt(i));
                    i++;
                    Words words = new Words(word, "semicolon");
                    secfuc.add(words);
                }//识别分界符
                else {
                    int label = issign(str.charAt(i));
                    if (label==0) {
                        word += Character.toString(str.charAt(i));
                        i++;
                        Words words = new Words(word, "error");
                        secfuc.add(words);
                    }
                    else if (label == 1) {
                        word += Character.toString(str.charAt(i));
                        i++;
                        Words words = new Words(word, "sign");
                        secfuc.add(words);
                    } else if (label == 2) {
                        word += Character.toString(str.charAt(i));
                        i++;
                        if (i<str.length()&&str.charAt(i) == '=') {
                            word += Character.toString(str.charAt(i));
                            i++;
                            Words words = new Words(word, "sign");
                            secfuc.add(words);
                        }
                        else if (i<str.length()&&word.equals("=")){
                            Words words = new Words(word, "sign");
                            secfuc.add(words);
                        }
                    } else if (label == 3) {
                        word += Character.toString(str.charAt(i));
                        i++;
                        if (i<str.length()&&str.charAt(i) == '=') {
                            word += Character.toString(str.charAt(i));
                            i++;
                            Words words = new Words(word, "sign");
                            secfuc.add(words);
                        } else {
                            Words words = new Words(word, "error");
                            secfuc.add(words);
                        }
                    }
                }
            }
        }
        for (Words words : secfuc) {
            words.printfall();
        }
    }

    static int issign(char a) {
        for (String str : Sign) {
            if (str.equals(Character.toString(a))) {
                if ((a == '+') || (a == '-') || (a == '*') || (a == '/') || (a == '+')) {
                    return 1;
                } else if (a == '!') {
                    return 3;
                } else return 2;
            }
        }
        return 0;
    }
}
