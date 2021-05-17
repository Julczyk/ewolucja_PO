import java.util.ArrayList;

public class Rob {

    public Rob(int ID, int angle, int x, int y, int energy, Simulation sim){
        this.robID = ID;
        this.age = 0;
        this.angle = angle;
        this.energy = energy;
        this.world = sim;
    }

    protected boolean checkIfDead(){
        return this.energy<=0;
    }

    protected void move(int steps) {
        switch (this.angle) {
            case 0:
                xPosition++;
                if(xPosition == world)
        }
    }

    protected int robID;

    protected int angle;
    protected int xPosition;
    protected int yPosition;
    Simulation world;

    ArrayList program;

    protected int energy;

    protected int age;
}
