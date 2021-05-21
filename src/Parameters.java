import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Parameters {
    public int boardWidth; //x
    public int boardHeight; //y
    public int beginRobzAm; //pocz_ile_robów
    public String beginRobzProgramm; //pocz_progr
    public int beginEnergyAmmount; //pocz_energia
    public int nutritiousValue; //ile_daje_jedzenie
    public int seasonLenght; //ile_rośnie_jedzenie
    public int turnCost; //koszt_tury 3
    public double breedingProbability; //pr_powielenia
    public double breedingPart; //ułamek_energii_rodzica
    public int breedingMinimum;
    public String excludedInstructions; //wył_instr
    public char[] includedInstructions;
    public String fulllist= "lriwj";
    public double mutationRemovalProbability; //pr_usunięcia_instr
    public double mutationAdditionProbability; //pr_dodania_instr
    public double mutationModifierProbability; //pr_zmiany_instr
    public int graphingPeriod; //co_ile_wypisz
    public int simDuration; //ile_tur
    public int movesWithousEnergyLoss; //ruch_bez_kar - koszt ruchów pod koniec tury rośnie nieliniowo powyżej tej wartości. Dzięki temu punkt równowagi nigdy nie dąży do niesakończoności



    public Parameters(int x, int y){
        this.boardWidth = x;
        this.boardHeight = y;
    }

    protected void readFromFile(String filepath) {
        int numberOfArgs = 0;
        try {
            File parameters = new File(filepath);
            Scanner lineReader = new Scanner(parameters);
            while (lineReader.hasNextLine()) {
                String paramLine = lineReader.nextLine();
                Scanner paramReader = new Scanner(paramLine);
                if(paramReader.hasNext())
                {
                    String param = paramReader.next();


                    switch (param) {
                        case "pocz_ile_robów":
                            this.beginRobzAm = paramReader.nextInt();
                            numberOfArgs++;
                            break;

                        case "pocz_progr":
                            this.beginRobzProgramm = paramReader.next();
                            numberOfArgs++;
                            break;

                        case "pocz_energia":
                            this.beginEnergyAmmount = paramReader.nextInt();
                            numberOfArgs++;
                            break;

                        case "ile_daje_jedzenie":
                            this.nutritiousValue = paramReader.nextInt();
                            numberOfArgs++;
                            break;

                        case "ile_rośnie_jedzenie":
                            this.seasonLenght = paramReader.nextInt();
                            numberOfArgs++;
                            break;

                        case "koszt_tury":
                            this.turnCost = paramReader.nextInt();
                            numberOfArgs++;
                            break;

                        case "pr_powielenia":
                            this.breedingProbability = paramReader.nextDouble();
                            numberOfArgs++;
                            break;

                        case "ułamek_energii_rodzica":
                            this.breedingPart = paramReader.nextDouble();
                            numberOfArgs++;
                            break;

                        case "limit_powielania":
                            this.breedingMinimum = paramReader.nextInt();
                            numberOfArgs++;
                            break;

                        case "pr_usunięcia_instr":
                            this.mutationRemovalProbability = paramReader.nextDouble();
                            numberOfArgs++;
                            break;

                        case "pr_dodania_inst":
                            this.mutationAdditionProbability = paramReader.nextDouble();
                            numberOfArgs++;
                            break;

                        case "pr_zmiany_instr":
                            this.mutationModifierProbability = paramReader.nextDouble();
                            numberOfArgs++;
                            break;

                        case "co_ile_wypisz":
                            this.graphingPeriod = paramReader.nextInt();
                            numberOfArgs++;
                            break;

                        case "ile_tur":
                            this.simDuration = paramReader.nextInt();
                            numberOfArgs++;
                            break;

                        case "wył_instr":
                            this.excludedInstructions = paramReader.next();
                            numberOfArgs++;
                            break;

                        case "ruch_bez_kar":
                            this.movesWithousEnergyLoss = paramReader.nextInt();
                            numberOfArgs++;
                            break;
                    }
                }
                paramReader.close();

            }
            if(numberOfArgs < 16)
                System.err.println("Parameter(s) are missing from file. Check the file and rerun the sim.");
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        if(excludedInstructions.length() != 0) {
            includedInstructions = new char[fulllist.length()];
            char[] a = fulllist.toCharArray();
            char[] b = excludedInstructions.toCharArray();
            int itr=0;
            for(int i=0; i<fulllist.length(); i++){
                boolean excluded = false;

                for(int j=0; j<excludedInstructions.length(); j++) {
                    if (a[i]==b[j])
                        excluded = true;
                }

                if(!excluded){
                    includedInstructions[itr] = a[i];
                    itr++;
                }
            }
        } else {
            includedInstructions = fulllist.toCharArray();
        }
    }
}
