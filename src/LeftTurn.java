public class LeftTurn extends Instruction{
    public LeftTurn(){}

    protected void execute(){
        partOf.owner.rotate(1, true);
    }
}
