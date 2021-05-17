public class Feedable extends Field {

    boolean hasFood;
    int timeToCrop;

    public Feedable(int x, int y,Plansza brd){
        super(x,y,brd);
        hasFood = true;
        timeToCrop = 0;
    }

    protected char printDistinct(){
        return 'x';
    }

}
