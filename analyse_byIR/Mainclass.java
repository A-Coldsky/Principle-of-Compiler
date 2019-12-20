import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Mainclass {
    static ArrayList<Character>zhuang = new ArrayList<Character>(Arrays.asList('0'));
    static ArrayList<Character> fuhao = new ArrayList<Character>(Arrays.asList('#'));
    static ArrayList<Character> thein = new ArrayList<Character>(Arrays.asList('a','b','#'));
    static ArrayList<Parse> parses=new ArrayList<Parse>();
    static HashMap<String,String> action=new HashMap<String, String>();
    public static void main(String[] args) {
        char S = zhuang.get(zhuang.size() - 1);
        char a = thein.get(0);
        init();
        String lin = "";
        lin = lin + S + a;
        int test='1'-48;
        while (!action.get(lin).equals("acc")){
            System.out.print(zhuang+"  "+fuhao+"  "+thein+" ");
            if (!action.containsKey(lin)){
                error();
                break;
            }
            else if (action.get(lin).charAt(0)=='r'){
                int parsenum=action.get(lin).charAt(1)-48;
                Parse lparse=parses.get(parsenum);
                for (int i=0;i<lparse.end.length();i++){
                    zhuang.remove(zhuang.size()-1);
                    fuhao.remove(fuhao.size()-1);
                }
                String gotob="";
                fuhao.add(lparse.start);
                gotob=gotob+zhuang.get(zhuang.size()-1)+lparse.start;
                zhuang.add(action.get(gotob).charAt(1));
                System.out.println(action.get(lin)+"  goto"+action.get(gotob).charAt(1));
            }
            else if(action.get(lin).charAt(0)=='s'){
                zhuang.add(action.get(lin).charAt(1));
                fuhao.add(thein.get(0));
                thein.remove(0);
                System.out.println(action.get(lin));
            }
            S=zhuang.get(zhuang.size() - 1);
            a=thein.get(0);
            lin="";
            lin=lin+S+a;
        }
        System.out.print(zhuang+"  "+fuhao+"  "+thein+" ");
System.out.println("success");

    }
    static void error(){
        System.out.println();
        System.out.println("error");
        System.exit(0);
    }
    static void init(){
        Parse parse=new Parse('S',"A");
        parses.add(parse);

        parse=new Parse('A',"BaBb");
        parses.add(parse);

        parse=new Parse('A',"DdDa");
        parses.add(parse);

        parse=new Parse('B',"");
        parses.add(parse);

        parse=new Parse('D',"");
        parses.add(parse);

        action.put("0a","r3");
        action.put("0b","r4");
        action.put("0A","g1");
        action.put("0B","g2");
        action.put("0D","g3");
        action.put("1#","acc");
        action.put("2a","s4");
        action.put("3b","s5");
        action.put("4b","r3");
        action.put("4B","g6");
        action.put("5a","r4");
        action.put("5D","g7");
        action.put("6b","s8");
        action.put("7a","S9");
        action.put("8#","r1");
        action.put("9#","r2");
    }

}
