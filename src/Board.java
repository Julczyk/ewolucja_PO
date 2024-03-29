import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Board {

    protected int xSize;
    protected int ySize;
    String rawData;
    protected Field[][] fields;
    int seasonLenght;

    public Board() {
        this.rawData="";
        this.ySize = 0;
        this.xSize = 0;
        this.fields = new Field[1][1];
    }

    protected void readFromFile(String filepath) {
        this.ySize=0;
        this.rawData="";
        try {
            File board = new File(filepath);
            Scanner boardReader = new Scanner(board);
            while (boardReader.hasNextLine()) {
                ySize++;
                String addLine = boardReader.nextLine();
                if(ySize == 1)
                    xSize = addLine.length();

                rawData = rawData + addLine;
            }
            boardReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


        this.fields = new Field[xSize][ySize];
        char arrayData[] = rawData.toCharArray();
        char cr;
        for(int i=0; i<ySize; i++) {
            for(int j=0; j<xSize; j++) {
                cr = arrayData[i*xSize+j];
                if(cr == ' ')
                    fields[j][i] = new Empty(j,i,this);
                else if(cr == 'x')
                    fields[j][i] = new Feedable(j,i,this);
            }
        }

    }

    protected void setSeasonLenght(int lenght) {
        this.seasonLenght = lenght;
    }

    protected void print() {
        System.out.println("RAW:");
        System.out.println(rawData);
        System.out.println("Table:");
        for(int i=0; i<ySize; i++){
            for(int j=0; j<xSize; j++) {
                System.out.print(fields[j][i].printDistinct());
            }
            System.out.println();
        }
    }

    protected Field getRandomField(){
        int randX = (int)Math.random() * xSize;
        int randY = (int)Math.random() * ySize;
        return fields[randX][randY];
    }

    protected Field at(int x, int y) {
        return fields[(x+xSize)%xSize] [(y+ySize)%ySize];
    }

}
