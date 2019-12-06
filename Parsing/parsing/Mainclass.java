public class Mainclass {
    static String codes="adbaba#";
    static int num=0;
    static char sym;
    public static void main(String[] args){
        sym=getsym();
        parseS();
        if (sym=='#'){
            System.out.println("sucess!!");

        }
        else error();
    }
   static char getsym(){
        if (num>=codes.length()){
            error();
        }
            char a = codes.charAt(num);
            num++;
            return a;
    }
    static void error(){
        System.out.println("error");
        System.exit(0);
    }
    static void Matchtoken(char a){
        if (sym==a){
            sym=getsym();
        }
        else {
            error();
        }
    }
    static void Matchtoken(char a,char b){
        if (sym==a||sym==b){
            sym=getsym();
        }
        else {
            error();
        }
    }
    static void parseS(){
        if (sym=='a'){
            Matchtoken('a');
            parseA();
            Matchtoken('a');
            parseB();
        }
            else if (sym=='b'){
            Matchtoken('b');
            parseA();
            Matchtoken('b');
            parseB();
        }
                else error();
    }
    static void parseA(){
        if (sym=='a'||sym=='b'){
            parseS();
        }
        else if(sym=='d'){
            Matchtoken('d');
            Matchtoken('b');
        }
        else error();

    }
    static void parseB(){
        if (sym=='b'){
            Matchtoken('b');
            parseB();
        }
        else if (sym=='a'){
            Matchtoken('a');
        }
        else error();
    }
}
