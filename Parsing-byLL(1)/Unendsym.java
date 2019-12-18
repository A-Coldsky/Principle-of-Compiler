import java.util.ArrayList;
import java.util.Arrays;

public class Unendsym {//用来管理非终结符的类
    private ArrayList<Character> first = new ArrayList<Character>();
    private ArrayList<Character> follow = new ArrayList<Character>();
    private ArrayList<String> parse = new ArrayList<String>();//存储的是非终结符可以推导的句型
    private char thesym;
    private Boolean reta=null;

    Unendsym(char thesym) {
        this.thesym = thesym;
    }

    void parseadd(String aparese) {
        parse.add(aparese);
    }

    void firstadd(char achar) {
        first.add(achar);
    }

    void followadd(char achar) {
        if (follow.indexOf(achar) == -1) {
            follow.add(achar);
        }
    }

    void followadd(ArrayList<Character> afollow) {
        for (char achar:afollow) {
            if (follow.indexOf(achar) == -1) {
                follow.add(achar);
            }
        }
    }

    void setReta(boolean reta) {
        this.reta = reta;
    }

    ArrayList<String> getParse() {
        return parse;
    }

    ArrayList<Character> getFollow() {
        return follow;
    }

    ArrayList<Character> getFirst() {
        return first;
    }

    void followremovereta() {
        follow.remove('ε');
    }

    boolean getreta() {
        return reta;
    }

    char getThesym() {
        return thesym;
    }
}
