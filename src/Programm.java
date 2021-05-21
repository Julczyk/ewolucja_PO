import java.util.ArrayList;
import java.util.Set;

public class Programm {
    int penalty;
    String raw;
    int lenght;
    Set available;
    ArrayList<Instruction> instructions;
    Rob owner;
    Board board;

    public Programm(Rob rob, Board board, String raw) {
        this.owner = rob;
        this.board = board;
        this.raw = raw;
        this.lenght = raw.length();
        this.instructions = new ArrayList<Instruction>();

        //dodatkowy koszt za zbyt długi program (dla punktu równowagi)
        this.penalty = Math.max(
                (int)(
                        Math.log((double)((lenght - owner.world.params.movesWithousEnergyLoss) * owner.world.params.turnCost))
                ), 0);


        parse();
    }

    //zamienia string na ciąg instrukcji programu
    private void parse(){
        char[] arr = raw.toCharArray();
        for(int i=0; i<lenght; i++) {
            switch (arr[i]) {
                case 'l':
                    instructions.add(new LeftTurn(this));
                    break;
                case 'p':
                    instructions.add(new RightTurn(this));
                    break;
                case 'i':
                    instructions.add(new MoveFoward(this));
                    break;
                case 'w':
                    instructions.add(new Sniff(this));
                    break;
                case 'j':
                    instructions.add(new Eat(this));
                    break;
                default:
                    break;
            }
        }
    }

    protected boolean execute(){
        boolean dead = false;
        for(int i=0; i<instructions.size() && !dead; i++) {
            instructions.get(i).execute();
            dead = owner.checkIfDead();
        }
        if(!dead)
            owner.energy -= penalty;

        return owner.checkIfDead();
    }


}
