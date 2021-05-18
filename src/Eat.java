import java.util.ArrayList;

public class Eat extends Instruction{
    public Eat(){}

    protected void execute(){
        ArrayList<Field> possible = new ArrayList<Field>();
        Field root = partOf.owner.home;

        //top-left
        if(partOf.board.at(root.xPos-1, root.yPos+1).hasFood()) {
            possible.add(partOf.board.at( root.xPos-1, root.yPos+1 ));
        }
        //top
        if(partOf.board.at(root.xPos, root.yPos+1).hasFood()) {
            possible.add(partOf.board.at( root.xPos, root.yPos+1 ));
        }
        //top-right
        if(partOf.board.at(root.xPos+1, root.yPos+1).hasFood()) {
            possible.add(partOf.board.at( root.xPos+1, root.yPos+1 ));
        }
        //left
        if(partOf.board.at(root.xPos-1, root.yPos).hasFood()) {
            possible.add(partOf.board.at( root.xPos-1, root.yPos));
        }
        //right
        if(partOf.board.at(root.xPos+1, root.yPos).hasFood()) {
            possible.add(partOf.board.at( root.xPos+1, root.yPos ));
        }
        //bottom-left
        if(partOf.board.at(root.xPos-1, root.yPos-1).hasFood()) {
            possible.add(partOf.board.at( root.xPos, root.yPos-1 ));
        }
        //bottom
        if(partOf.board.at(root.xPos, root.yPos-1).hasFood()) {
            possible.add(partOf.board.at( root.xPos, root.yPos-1 ));
        }
        //bottom-right
        if(partOf.board.at(root.xPos+1, root.yPos-1).hasFood()) {
            possible.add(partOf.board.at( root.xPos+1, root.yPos-1 ));
        }

        if(possible.size() > 0) {
            int which = (int)(Math.random()*possible.size());
            partOf.owner.setHome(possible.get(which));
        }
    }
}

