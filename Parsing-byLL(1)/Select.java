import java.util.ArrayList;

public class Select {
    char begin;
    private ArrayList<Character> theselect = new ArrayList<Character>();
    String end;
    boolean g=false;
    Select (char begin,String end){
        this.begin=begin;
        this.end=end;
        g=true;
    }
    Select(){
    }
    void theselectadd(char achar) {
        if (theselect.indexOf(achar) == -1) {
            theselect.add(achar);
        }
    }

    void theselectadd(ArrayList<Character> aslect) {
        for (char achar:aslect) {
            if (theselect.indexOf(achar) == -1) {
                theselect.add(achar);
            }
        }
    }
    void selectremovereta() {
        theselect.remove('ε');
    }
    ArrayList<Character> getselects() {
        return theselect;
    }
    ArrayList<Character> daoxv(){
         ArrayList<Character> daoxv = new ArrayList<Character>();
         for (int i=end.length()-1;i>=0;i--){
             daoxv.add(end.charAt(i));
         }
         return daoxv;
    }

}
