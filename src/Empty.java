public class Empty extends Field{

    public Empty(int x, int y, Plansza brd){
        super(x,y,brd);
    }

    protected char printDistinct(){
        super.printDistinct();
        return '.';
    }
}
