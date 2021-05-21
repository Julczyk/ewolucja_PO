public class MoveFoward extends Instruction{

    public MoveFoward(Programm partOf) {
        super(partOf);
    }
    protected void execute(){
        super.execute();

        partOf.owner.move(1);
    }
}
