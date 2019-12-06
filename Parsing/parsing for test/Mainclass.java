import javafx.scene.shape.SVGPath;

public class Mainclass {
    static String codes = ".1#";
    static int num = 0;
    static char sym;

    public static void main(String[] args) {
        sym=getsym();
        ParseN();
        if (sym=='#'){
            System.out.println("sucess!!");

        }
        else error();
    }

    static char getsym() {
        if (num >= codes.length()) {
            error();
        }
        char a = codes.charAt(num);
        num++;
        return a;
    }

    static void error() {
        System.out.println("error");
        System.exit(0);
    }

    static void Matchtoken(char a) {
        if (sym == a) {
            sym = getsym();
        } else {
            error();
        }
    }

    static void Matchtoken(char a, char b) {
        if (sym == a || sym == b) {
            sym = getsym();
        } else {
            error();
        }
    }
    static void ParseN(){
        Matchtoken('.');
        double Sf=1;
        double Sv=ParseS(Sf);
        System.out.println(Sv);
    }
    static double ParseS(double f){
        double Sv=0;
        if(sym=='0'||sym=='1'){
            double Bf=f;
            double Bv=ParseB(Bf);
            double S1f=f+1;
            double S1v=ParseS(S1f);
            Sv=S1v+Bv;
        }
        else if (sym=='#'){
            Sv=0;
        }
        else {
            error();
        }
        return Sv;
    }
        static double ParseB(double f){
        double Bv=0;
        if (sym=='0'){
            Matchtoken('0');
            Bv=0;
        }
        else if (sym=='1'){
            Matchtoken('1');
            Bv=Math.pow(2,-f);
        }
        else error();
        return Bv;
        }
}
