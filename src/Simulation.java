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
        this.board = new Plansza();
        board.readFromFile(boardFilePath);

        this.params = new Parameters(board.xSize, board.ySize);
        params.readFromFile(paramsFilePath);

        board.setSeasonLenght(params.seasonLenght);
    }

    public int getFreeID(){
        freeID++;
        return freeID;
    }

    protected void populate(){
        for(int i=0; i<params.beginRobzAm; i++) {

        }
    }


    public static void main(String[] args) {
        Simulation world = new Simulation();
        world.init("plansza.txt","parametry.txt");
    }

}
