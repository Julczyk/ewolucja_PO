public class RightTurn extends Instruction{

    public RightTurn(Programm partOf){
        super(partOf);
    }

    protected void execute(){
        super.execute();
        partOf.owner.rotate(1, false);
    }
}
