import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Parameters {
    int boardWidth; //x
    int boardHeight; //y
    int beginRobzAm; //pocz_ile_robów
    String beginRobzProgramm; //pocz_progr
    int beginEnergyAmmount; //pocz_energia
    int nutritiousValue; //ile_daje_jedzenie
    int seasonLenght; //ile_rośnie_jedzenie
    int turnCost; //koszt_tury 3
    double breedingProbability; //pr_powielenia
    double breedingPart; //ułamek_energii_rodzica
    int breedingMinimum;
    Instruction[] Excluded; //wył_instr
    double mutationRemovalProbability; //pr_usunięcia_instr
    double mutationAdditionProbability; //pr_dodania_instr
    double mutationModifierProbability; //pr_zmiany_instr
    int graphingPeriod; //co_ile_wypisz
    int simDuration; //ile_tur


    public Parameters(int x, int y){
        this.boardWidth = x;
        this.boardHeight = y;
    }

    protected void readFromFile(String filepath) {
        try {
            File parameters = new File(filepath);
            Scanner paramReader = new Scanner(parameters);
            while (paramReader.hasNextLine()) {
                String param = paramReader.next();

                switch (param) {
                    case "pocz_ile_robów":
                        this.beginRobzAm = paramReader.nextInt();
                        break;

                    case "pocz_progr":
                        this.beginRobzProgramm = paramReader.next();
                        break;

                    case "pocz_energia":
                        this.beginEnergyAmmount = paramReader.nextInt();
                        break;

                    case "ile_daje_jedzenie":
                        this.nutritiousValue = paramReader.nextInt();
                        break;

                    case "ile_rośnie_jedzenie":
                        this.seasonLenght = paramReader.nextInt();
                        break;

                    case "koszt_tury ":
                        this.turnCost = paramReader.nextInt();
                        break;

                    case "pr_powielenia":
                        this.breedingProbability = paramReader.nextDouble();
                        break;

                    case "ułamek_energii_rodzica":
                        this.breedingPart = paramReader.nextDouble();
                        break;

                    case "limit_powielania":
                        this.breedingMinimum = paramReader.nextInt();
                        break;

                    case "pr_usunięcia_instr":
                        this.mutationRemovalProbability = paramReader.nextDouble();
                        break;

                    case "pr_dodania_inst":
                        this.mutationAdditionProbability = paramReader.nextDouble();
                        break;

                    case "pr_zmiany_instr":
                        this.mutationModifierProbability = paramReader.nextDouble();
                        break;

                    case "co_ile_wypisz":
                        this.graphingPeriod = paramReader.nextInt();
                        break;

                    case "ile_tur":
                        this.simDuration = paramReader.nextInt();
                        break;
                }
            }
            paramReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


    }
}
