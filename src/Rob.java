import java.util.ArrayList;

public class Rob {
    protected int robID;

    Field home;
    protected int angle;
    /*
    Konwencja kierunku zaczerpnięta z nawigacji morskiej i powietrznej. Kąt 0 to kurs na północ (do góry).
    Dodatnia liczba to wielokrotność 90 stopni od kąta 0 w prawo.
     */
    Simulation world;
    ArrayList program;
    protected int energy;
    protected int age;

    public Rob(int ID, int angle, Field home, int energy, Simulation sim){
        this.robID = ID;
        this.home = home;
        this.age = 0;
        this.angle = angle;
        this.energy = energy;
        this.world = sim;
    }

    protected Rob reproduce(){
        Rob
    }

    protected boolean checkIfDead(){
        return this.energy<=0;
    }

    protected void move(int steps) {
        int xPosition = home.xPos;
        int yPosition = home.yPos;
        switch (this.angle) {
            case 0:
                yPosition+=steps;
                while(yPosition >= world.params.boardWidth)
                    yPosition -= world.params.boardWidth;
                break;

            case 1:
                xPosition+=steps;
                while(xPosition >= world.params.boardHeight)
                    xPosition -= world.params.boardHeight;
                break;

            case 2:
                yPosition-=steps;
                while(yPosition <= 0)
                    yPosition += world.params.boardWidth;
                break;

            case 3:
                xPosition-=steps;
                while(xPosition <= 0)
                    xPosition += world.params.boardWidth;
                break;
        }

        Field next = home.board.at(xPosition, yPosition);
        setHome(next);

    }

    protected void rotate(int angle, boolean left){
        if(!left) {
            this.angle += angle % 4;
        } else {
            this.angle += 4 - (angle % 4);
        }
        angle = angle % 4;
    }

    protected void setAngle(int goal) {
        this.angle=goal;
    }

    protected void setHome(Field goal) {
        home.leave();
        this.home = goal;
        home.visit();
    }

    protected void replenish() {
        this.energy += world.params.nutritiousValue;
    }

}
