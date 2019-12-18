import java.util.ArrayList;
import java.util.Arrays;

public class Mainclass {
    static String none = "ε";//字符串为空的符号
    static ArrayList<Character> unendsym = new ArrayList<Character>(Arrays.asList('S', 'A', 'B'));//终结符集合
    static ArrayList<Character> endsym = new ArrayList<Character>(Arrays.asList('a', 'b', 'd'));//非终结符集合


    static ArrayList<Unendsym> unendsyms = new ArrayList<Unendsym>();

    static ArrayList<Character> fenxi = new ArrayList<Character>(Arrays.asList('#', 'S'));
    static ArrayList<Character> shengyu = new ArrayList<Character>(Arrays.asList('a', 'd', 'b', 'a', 'b', 'a', '#'));
    static ArrayList<Select> selects = new ArrayList<Select>();//存储不同语句的select的集合

    public static void main(String[] args) {

        for (char a : unendsym) {
            Unendsym theunsym = new Unendsym(a);
            unendsyms.add(theunsym);
        }
        initparse();
        for (Unendsym test : unendsyms) {
            Boolean isempty=null;
            for (String aparse : test.getParse()) {
                if (aparse.equals(none)) {
                    test.setReta(true);
                    break;
                }
                else {
                    for (char sym:endsym){
                        if (aparse.indexOf(sym)!=-1){
                        }
                    }
                }
            }
        }
        unendsyms.get(0).followadd('#');
        for (int m = 0; m < 5; m++) {
            for (Unendsym test : unendsyms) {
                for (String aparse : test.getParse()) {
                    int i = 0;
                    if (aparse.equals(none)) {
                        continue;//语法不为空
                    } else {
                        while (i < aparse.length()) {
                            if (endsym.indexOf(aparse.charAt(i)) != -1) {//是终结符
                                i++;
                                continue;
                            } else {
                                int t = i + 1;
                                while (true) {
                                    Unendsym one_test = unendsyms.get(unendsym.indexOf(aparse.charAt(i)));
                                    if (t == aparse.length()) {
                                        one_test.followadd(test.getFollow());
                                        break;
                                    }//处于末尾
                                    else if (endsym.indexOf(aparse.charAt(t)) != -1) {
                                        one_test.followadd(aparse.charAt(t));//是终结符
                                        break;
                                    } else if ((unendsym.indexOf(aparse.charAt(t)) != -1) &&
                                            (!unendsyms.get(unendsym.indexOf(aparse.charAt(t))).getreta())) {
                                        one_test.followadd(unendsyms.get(unendsym.indexOf(aparse.charAt(t))).getFirst());
                                        break;//不为空的非终结符
                                    } else {
                                        one_test.followadd(unendsyms.get(unendsym.indexOf(aparse.charAt(t))).getFirst());
                                        one_test.followadd(unendsyms.get(unendsym.indexOf(aparse.charAt(t))).getFollow());
                                        one_test.followremovereta();//为空的终结符
                                        t++;
                                    }
                                }
                                i++;
                            }
                        }
                    }
                }
            }
        }//求follow集合
        for (Unendsym test : unendsyms) {
            for (String aparse : test.getParse()) {
                Select select = new Select(test.getThesym(), aparse);
                selects.add(select);
                int i = 0;
                if (aparse.equals(none)) {
                    select.theselectadd(test.getFollow());
                    break;//语法为空
                }
                while (true) {
                    if (i == aparse.length()) {
                        select.theselectadd(test.getFollow());
                        break;
                    } else if (endsym.indexOf(aparse.charAt(i)) != -1) {//是终结符
                        select.theselectadd(aparse.charAt(i));
                        break;
                    } else if ((unendsym.indexOf(aparse.charAt(i)) != -1) &&
                            (!unendsyms.get(unendsym.indexOf(aparse.charAt(i))).getreta())) {
                        select.theselectadd(unendsyms.get(unendsym.indexOf(aparse.charAt(i))).getFirst());
                        break;
                    } else {
                        select.theselectadd(unendsyms.get(unendsym.indexOf(aparse.charAt(i))).getFirst());
                        select.selectremovereta();
                        i++;
                    }
                }
            }
        }
        while (fenxi.get(0) != shengyu.get(0)) {//分析栈和剩余栈进行匹配操作
            if (fenxi.get(fenxi.size() - 1) == shengyu.get(0)) {
                System.out.println(fenxi + "  " + shengyu + " " + shengyu.get(0) + " 匹配");
                fenxi.remove(fenxi.size() - 1);
                shengyu.remove(0);
            } else {
                Select right = new Select();
                for (Select select : selects) {
                    if ((select.begin == fenxi.get(fenxi.size() - 1)) && (select.getselects().indexOf(shengyu.get(0)) != -1)) {
                        right = select;
                        break;
                    }
                }
                if (right.g) {
                    System.out.println(fenxi + "  " + shengyu + " " + right.begin + "->" + right.end);
                    fenxi.remove(fenxi.size() - 1);
                    fenxi.addAll(right.daoxv());
                }
            }
        }
        System.out.println("成功");
    }

    static void initparse() {
        //用来手动给予每个语法的first集合，以及是否为空
        unendsyms.get(0).parseadd("aAaB");
        unendsyms.get(0).parseadd("bAbB");
        unendsyms.get(0).setReta(false);
        unendsyms.get(0).firstadd('a');
        unendsyms.get(0).firstadd('b');

        unendsyms.get(1).parseadd("S");
        unendsyms.get(1).parseadd("db");
        unendsyms.get(1).setReta(false);
        unendsyms.get(1).firstadd('a');
        unendsyms.get(1).firstadd('b');
        unendsyms.get(1).firstadd('d');

        unendsyms.get(2).parseadd("bB");
        unendsyms.get(2).parseadd("a");
        unendsyms.get(2).setReta(false);
        unendsyms.get(2).firstadd('b');
        unendsyms.get(2).firstadd('a');
    }
}
