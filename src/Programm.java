import java.util.ArrayList;
import java.util.Set;

public class Programm {
    String raw;
    int lenght;
    Set available;
    ArrayList Instructions;
    Rob owner;
    Plansza board;


    public Programm(Rob rob, Plansza board, String raw) {
        this.owner = rob;
        this.board = board;
        this.raw = raw;
        this.lenght = raw.length();
        parse();
    }

    private void parse(){
        char[] arrayed = raw.toCharArray();
        for(int i=0; i<lenght; i++) {

        }
    }


}
