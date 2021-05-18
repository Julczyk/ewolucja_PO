import java.util.ArrayList;

public class Sniff extends Instruction{
    public Sniff(){};

    protected void execute(){
        ArrayList<Integer> possible = new ArrayList<Integer>();
        Field root = partOf.owner.home;
        if(partOf.board.at(root.xPos, root.yPos+1).hasFood())
            possible.add(0);
        if(partOf.board.at(root.xPos+1, root.yPos).hasFood())
            possible.add(1);
        if(partOf.board.at(root.xPos, root.yPos-1).hasFood())
            possible.add(2);
        if(partOf.board.at(root.xPos-1, root.yPos).hasFood())
            possible.add(3);

        if(possible.size() > 0) {
            int which = (int) (Math.random() * possible.size());
            partOf.owner.setAngle( possible.get(which) );
        }


    }
}
