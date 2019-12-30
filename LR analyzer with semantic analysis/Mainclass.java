import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static java.lang.Math.pow;

public class Mainclass {
    static ArrayList<Character>zhuang = new ArrayList<Character>(Arrays.asList('0'));
    static ArrayList<Character> fuhao = new ArrayList<Character>(Arrays.asList('#'));
    static ArrayList<Character> thein = new ArrayList<Character>(Arrays.asList('.','1','0','1','#'));
    static ArrayList<Double> means=new ArrayList<Double>(Arrays.asList(-1.0));
    static ArrayList<Parse> parses=new ArrayList<Parse>();
    static int top=0;
    static HashMap<String,String> action=new HashMap<String, String>();
    public static void main(String[] args) {
        char S = zhuang.get(zhuang.size() - 1);
        char a = thein.get(0);

        init();
        String lin = "";
        lin = lin + S + a;
        while (!action.get(lin).equals("acc")){
            System.out.print(zhuang+"  "+means+"  "+fuhao+"  "+thein+" ");
            if (!action.containsKey(lin)){
                error();
                break;
            }
            else if (action.get(lin).charAt(0)=='r'){
                int parsenum=action.get(lin).charAt(1)-48;
                Parse lparse=parses.get(parsenum-1);
                for (int i=0;i<lparse.end.length();i++){
                    zhuang.remove(zhuang.size()-1);
                    fuhao.remove(fuhao.size()-1);
                }//规约
                String gotob="";
                fuhao.add(lparse.start);
                gotob=gotob+zhuang.get(zhuang.size()-1)+lparse.start;
                zhuang.add(action.get(gotob).charAt(1));
                System.out.println(action.get(lin)+"  goto"+action.get(gotob).charAt(1));
                switch (parsenum){
                    case 1:means1();break;
                    case 2:means2();break;
                    case 3:means3();break;
                    case 4:means4();break;
                    case 5:means5();break;
                    case 6:means6();break;
                    case 7:means7();break;
                }
            }
            else if(action.get(lin).charAt(0)=='s'){
                zhuang.add(action.get(lin).charAt(1));
                fuhao.add(thein.get(0));
                means.add(-1.0);
                top++;
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
    static void means1(){
        System.out.println(means.get(top));
    }
    static void means2(){
        double value=means.get(top-2)+means.get(top);
        top=top-2;
        for(int i=means.size()-1;i>=top;i--)
            means.remove(i);
        means.add(value);
    }
    static void means3(){
        top++;
        means.add(0.00);
    }
    static void means4(){
        means.remove(top);
        means.add(0.00);
    }
    static void means5(){
        double value=pow(2,-means.get(top-1));
        means.remove(top);
        means.add(value);
    }
    static void means6(){
        means.add(1.00);
        top++;
    }
    static void means7(){
        double value=means.get(top-1)+1;
        top++;
        means.add(value);
    }
    static void   init(){
        Parse parse=new Parse('N',".MS");
        parses.add(parse);

        parse=new Parse('S',"BPS");
        parses.add(parse);

        parse=new Parse('S',"");
        parses.add(parse);

        parse=new Parse('B',"0");
        parses.add(parse);

        parse=new Parse('B',"1");
        parses.add(parse);

        parse=new Parse('M',"");
        parses.add(parse);

        parse=new Parse('P',"");
        parses.add(parse);

        action.put("0.","s2");
        action.put("0N","g1");

        action.put("1#","acc");

        action.put("20","r6");
        action.put("21","r6");
        action.put("2#","r6");
        action.put("2M","g3");

        action.put("30","s5");
        action.put("31","s6");
        action.put("3#","r3");
        action.put("3S","g4");
        action.put("3B","g7");

        action.put("4#","r1");


        action.put("50","r4");
        action.put("51","r4");
        action.put("5#","r4");

        action.put("60","r5");
        action.put("61","r5");
        action.put("6#","r5");


        action.put("70","r7");
        action.put("71","r7");
        action.put("7#","r7");
        action.put("7P","g8");

        action.put("80","s5");
        action.put("81","s6");
        action.put("8#","r3");
        action.put("8S","g9");
        action.put("8B","g7");

        action.put("9#","r2");


    }

}
