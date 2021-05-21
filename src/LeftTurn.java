public class LeftTurn extends Instruction{
    public LeftTurn(Programm partOf){
        super(partOf);
    }

    protected void execute(){
        super.execute();
        partOf.owner.rotate(1, true);
    }
}
