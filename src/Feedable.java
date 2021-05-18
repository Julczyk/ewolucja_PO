public class Feedable extends Field {

    int timeToCrop;

    public Feedable(int x, int y,Plansza brd){
        super(x,y,brd);
        timeToCrop = 0;
    }

    protected char printDistinct(){
        return 'x';
    }

    protected boolean visit() {
        super.visit();
        return true;
    }

    protected void newTurn(){
        if(timeToCrop > 0)
            timeToCrop--;
        else {
            if(visited>0)
                timeToCrop = board.seasonLenght * visited;
        }
        super.newTurn();
    }

    protected boolean hasFood(){
        return timeToCrop==0;
    }

}
