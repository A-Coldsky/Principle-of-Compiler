import java.util.ArrayList;
import java.util.Arrays;

public class Words {
    String str;
    String label;
    Words(String str,String label){
        this.str=str;
        this.label=label;
    }
    public void printfall(){
        System.out.println("{"+label+","+str+'}');
    }
}
