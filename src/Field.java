public class Field {

    protected int standing;
    protected int visited;
    public int xPos;
    public int yPos;
    protected Board board;

    public Field(int x, int y, Board brd) {
        this.xPos = x;
        this.yPos = y;
        this.standing = 0;
        this.visited = 0;
        this.board = brd;
    }

    protected char printDistinct(){
        return 'E';
    }

    protected boolean visit() {
        this.standing++;
        this.visited++;
        return false;
    }

    protected void leave(){
        standing--;
    }

    protected void newTurn(){
        visited = 0;
    }

    protected boolean hasFood(){
        return false;
    }

}
