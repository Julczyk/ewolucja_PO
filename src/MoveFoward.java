public class MoveFoward extends Instruction{
    Programm partOf;
    public MoveFoward(Programm partOf) {

    }
    protected void execute(){
        partOf.owner.move(1);
    }
}
