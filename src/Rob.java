import java.util.ArrayList;
import java.util.Arrays;

public class Rob {
    protected int robID;

    protected Field home;
    protected int angle;
    /*
    Konwencja kierunku zaczerpnięta z nawigacji morskiej i powietrznej. Kąt 0 to kurs na północ (do góry).
    Dodatnia liczba to wielokrotność 90 stopni od kąta 0 w prawo.
     */
    protected Simulation world;
    protected Programm program;
    protected int energy;
    protected int birth;
    public boolean dead;

    public Rob(int ID, int birth, int angle, Field home, int energy, Simulation sim, String programm){
        this.robID = ID;
        this.birth = birth;
        this.angle = angle;
        this.home = home;
        this.energy = energy;
        this.world = sim;
        this.program=new Programm(this, sim.board, programm);
        this.home.visit();
        this.dead = false;
    }


    protected boolean doATurn(){
        boolean alive = true;
        alive = this.program.execute();
        this.energy -= world.params.turnCost;
        alive = (!checkIfDead());

        if (this.energy >= world.params.breedingMinimum){
            boolean breed = Math.random() <= world.params.breedingProbability;
            if(breed) {
                world.newBodyReported(
                        breed()
                );
            }
        }

        return alive;
    }

    //sprawdza, czy rob "jeszcze żyje"
    public boolean checkIfDead(){
        if(this.dead)
            return true;
        else {
            if(this.energy<=0) {
                this.dead = true;
                world.deadBodyReported(this);
                return true;
            } else {
                return false;
            }
        }
    }

    //rozmnaża roba
    protected Rob breed(){
        boolean add = Math.random() <= world.params.mutationAdditionProbability;
        boolean remove = Math.random() <= world.params.mutationRemovalProbability;
        boolean modify = Math.random() <= world.params.mutationModifierProbability;


        String childProgram = "";
        char[] parentProgram = program.raw.toCharArray();
        int modIndex = -1; //ujemna, by nigdy nie była równa indeksowi - wtedy nie zastąpi
        if(modify)
            modIndex = (int) (Math.random() * program.lenght);

        int i=0;
        while(i<program.lenght-1) {
            if(i == modIndex) {
                childProgram += world.params.includedInstructions[
                        (int)(Math.random() * world.params.includedInstructions.length) ];
            } else {
                childProgram += parentProgram[i];
            }
            i++;
        }

        if(!remove && parentProgram.length>0) {
            childProgram += parentProgram[i];
            i++;
        }
        if(add) {
            childProgram += world.params.includedInstructions[
                    (int)(Math.random() * world.params.includedInstructions.length) ];
        }

        int energyForChild = (int)(this.energy * world.params.breedingPart);
        this.energy -= energyForChild;

        Rob child = new Rob(
                world.getFreeID(),
                world.turn,
                (this.angle + 2)%4,     //obrócony w przeciwną stronę
                this.home,
                energyForChild,
                world,
                childProgram
        );

        return child;
    }

    //porusza się o <steps> pól w stronę w którą jest zwrócony
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

    //obraca o kąt angle, w stronę definiowaną przez drugi argument
    protected void rotate(int angle, boolean left){
        if(!left) {
            this.angle += angle % 4;
        } else {
            this.angle += 4 - (angle % 4);
        }
        angle = angle % 4;
    }

    public int age() {
        return world.turn-birth;
    }

    //ustawia kąt
    protected void setAngle(int goal) {
        this.angle=goal;
    }

    //ustawia nową pozycję
    protected void setHome(Field goal) {
        home.leave();
        this.home = goal;
        home.visit();
        if(home.hasFood())
            replenish();
    }

    //żywi Roba
    protected void replenish() {
        this.energy += world.params.nutritiousValue;
    }

}
