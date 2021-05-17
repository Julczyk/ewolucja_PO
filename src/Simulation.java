import java.util.ArrayList;

public class Simulation {

    Plansza board;
    Parameters params;
    ArrayList<Rob> robs;
    int freeID;
    int turn;

    public Simulation(){
        this.turn = 0;
        this.freeID = 0;
    }

    private void init(String boardFilePath, String paramsFilePath) {

    }


    public static void main(String[] args) {
        Simulation world = new Simulation();

    }

}
