public class Instruction {

    int cost;
    Programm partOf;
    public Instruction(Programm partOf){
        this.cost = 1;
        this.partOf = partOf;
    }

    protected void execute(){
        partOf.owner.energy -= cost;
    }
}

