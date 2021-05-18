public class RightTurn extends Instruction{

    public RightTurn(){}

    protected void execute(){
        partOf.owner.rotate(1, false);
    }
}
