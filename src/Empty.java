public class Empty extends Field{

    public Empty(int x, int y, Board brd){
        super(x,y,brd);
    }

    protected char printDistinct(){
        super.printDistinct();
        return '.';
    }

    protected boolean hasFood(){
        super.hasFood();
        return false;
    }
}
