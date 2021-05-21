import java.util.ArrayList;

public class Simulation {

    Board board;
    Parameters params;
    ArrayList<Rob> robs;
    int cementery;
    int freeID;
    int turn;

    public Simulation(){
        this.turn = 0;
        this.freeID = 0;
        this.robs = new ArrayList<Rob>();
        this.cementery = 0;
    }

    private void init(String boardFilePath, String paramsFilePath) {
        this.board = new Board();
        board.readFromFile(boardFilePath);

        this.params = new Parameters(board.xSize, board.ySize);
        params.readFromFile(paramsFilePath);

        board.setSeasonLenght(params.seasonLenght);
        populate();
    }

    public int getFreeID(){
        freeID++;
        return freeID;
    }

    protected void populate(){
        for(int i=0; i<params.beginRobzAm; i++) {
            robs.add(new Rob(
                        getFreeID(),
                        turn,
                        (int)(Math.random()*4),
                        board.getRandomField(),
                        params.beginEnergyAmmount,
                        this,
                        params.beginRobzProgramm
                        )
            );
        }
    }

    protected void deadBodyReported(Rob corpse){
        robs.remove(corpse);
        corpse.home.leave();
        cementery++;
    }

    protected void newBodyReported(Rob newborn){
        newborn.home.visit();
        robs.add(newborn);
    }

    private void run(){

        printReport();

        while(turn < params.simDuration) {
            if(robs.size() <= 0) {
                System.out.print("Symulacja przerwana, ostatni rob wyginął w turze");
                System.out.println(turn);
                break;
            }

            turn++;
            doATurn();

            if(turn % params.graphingPeriod == 0)
                printReport();
        }

        printReport();
        printRobList();
    }

    private void doATurn(){
        for(int i=0; i<robs.size(); i++) {
            robs.get(i).doATurn();
        }
        for(int y=board.ySize-1; y>=0; y--){
            for(int x=0; x<board.xSize; x++) {
                board.at(x,y).newTurn();
            }
        }
    }

    public void printReport(){
        int minProgramLenght = 2000000000;
        int maxProgramLenght = 0;
        double averageProgramLenght = 0;

        int minEnergy = 2000000000;
        int maxEnergy = 0;
        double averageEnergy = 0;

        int maxAge = 0;
        double averageAge = 0;

        int population = robs.size();

        for(int i=0; i<population; i++) {
            Rob current = robs.get(i);

            maxEnergy = Math.max(maxEnergy, current.energy);
            minEnergy = Math.min(minEnergy, current.energy);
            averageEnergy += current.energy;

            maxProgramLenght = Math.max(maxProgramLenght, current.program.lenght);
            minProgramLenght = Math.min(minProgramLenght, current.program.lenght);
            averageProgramLenght += current.program.lenght;

            maxAge = Math.max(maxAge, current.age());
            averageAge += current.age();
        }

        averageProgramLenght /= population;
        averageEnergy /= population;
        averageAge /= population;



        System.out.println("RobZ Simulation Report:");

        System.out.print("Turn ");
        System.out.print(turn);
        System.out.println(":");

        System.out.print("There are ");
        System.out.print(population);
        System.out.print(" robs alive and ");
        System.out.print(cementery);
        System.out.println(" dead.");

        System.out.print("Energy: ");
        System.out.print(minEnergy);
        System.out.print(" (min) / ");
        System.out.print(maxEnergy);
        System.out.print(" (max) / ");
        System.out.print(averageEnergy);
        System.out.println("(average).");

        System.out.print("Lenght of a program: ");
        System.out.print(minProgramLenght);
        System.out.print(" (min) / ");
        System.out.print(maxProgramLenght);
        System.out.print(" (max) / ");
        System.out.print(averageProgramLenght);
        System.out.println("(average).");

        System.out.print("Age of Robs: ");
        System.out.print(maxAge);
        System.out.print(" (max) / ");
        System.out.print(averageAge);
        System.out.println("(average).");


        System.out.println("State of food:");
        for(int y=board.ySize-1; y>=0; y--){
            for(int x=0; x<board.xSize; x++) {
                if(board.at(x, y).hasFood())
                    System.out.print("x");
                else
                    System.out.print(".");
            }
            System.out.println();
        }
        System.out.println("\nState of population:");
        for(int y=board.ySize-1; y>=0; y--){
            for(int x=0; x<board.xSize; x++) {
                if(board.at(x, y).standing > 0) {
                    if(board.at(x, y).standing < 10)
                        System.out.print( board.at(x, y).standing );
                    else
                        System.out.print("#");
                }
                else
                    System.out.print(".");
            }
            System.out.println();
        }

        System.out.println("   ---");

    }

    protected void printRobList(){
        System.out.println("Alive Robs:");
        for(int i=0; i<robs.size(); i++){
            Rob current = robs.get(i);
            System.out.print("Rob nr ");
            System.out.print(current.robID);
            System.out.print(" | BD: ");
            System.out.print(current.birth);
            System.out.print(" | A: ");
            System.out.print(turn - current.birth);
            System.out.print(" | Prg: ");
            System.out.print(current.program.raw);
            System.out.print(" | L: (");
            System.out.print(current.home.xPos);
            System.out.print(", ");
            System.out.print(current.home.yPos);
            System.out.println(").");
        }
    }







    public static void main(String[] args) {
        Simulation world = new Simulation();
        world.init("plansza.txt","parametry.txt");
        world.run();
    }


}
