public class Field {

    protected int standing;
    public int xPos;
    public int yPos;
    protected Plansza board;

    public Field(int x, int y, Plansza brd) {
        this.xPos = x;
        this.yPos = y;
        this.standing = 0;
        this.board = brd;
    }

    protected char printDistinct(){
        return 'E';
    }


}
