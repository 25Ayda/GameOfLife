public class Cell {
    private final double randomStateRate = 0.95;
    private int x;
    private int y;
    private int size;
    private int row;
    private int column;
    private CellState cellState;
    private MooreRules rules;


    public Cell(int x, int y, int size, int row, int column, CellState cellState, MooreRules rules) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.row = row;
        this.column = column;
        this.rules = rules;
        this.cellState = cellState;

    }

    /**
     * The applyRules method calls countLiveNeighbors on the cells 2D Array that it takes in as a param
     * then it uses liveNeighbors and cellState to apply the rules in MooreRules to determine if the
     * cellState is going to be WILL_REVIVE or WILL_DIE.
     * @param cells is the 2D Array that contains all the cells.
     */
    public void applyRules(Cell[][] cells) {
        int liveNeighbors = countLiveNeighbors(cells);
        cellState = rules.applyRules(cellState, liveNeighbors);
    }

    /**
     * evolve is a method that checks the state of the cell object and if the state is determined to be
     * WILL_REVIVE by MooreRules then it sets the cells state to ALIVE and if the state is determined
     * to be WILL_DIE by MooreRules then it sets the cells state to DEAD.
     */
    public void evolve() {
        if (cellState == CellState.WILL_REVIVE){
            cellState = CellState.ALIVE;
        } else if (cellState == CellState.WILL_DIE){
            cellState = CellState.DEAD;
        }
    }

    /**
     * display uses the state to determine the color of the cell. ALIVE is black and DEAD is white.
     */
    public void display() {
        if(cellState == CellState.ALIVE) {
            Main.app.fill(0);
            Main.app.rect(x, y, size, size);
        }else if(cellState == CellState.DEAD){
            Main.app.fill(255);
            Main.app.rect(x, y, size, size);
        }
    }

    /**
     * handleClick changes the cellState of the cell that is clicked on. If the cell is ALIVE then it
     * changes cellState to DEAD and vice versa.
     */
    public void handleClick() {
        if(cellState == CellState.ALIVE) {
            cellState = CellState.DEAD;
        }else{
            cellState = CellState.ALIVE;
        }
    }

    /**
     * This method randomly assigns dead or alive state to cells at the beginning of the
     * program running.
     */
    public void randomState() {
        if(Math.random() < randomStateRate) {
            cellState = CellState.DEAD;
        }else{
            cellState = CellState.ALIVE;
        }
    }

    /**
     * method countLiveNeighbors iterates through the eight cells surrounding the cell that
     * countLiveNeighbors is being called on. The method checks if each of the cells cellState is
     * ALIVE or WILL_DIE
     * @param cells is the 2D Array of all the cells.
     * @return
     */
    private int countLiveNeighbors(Cell[][] cells) {
        int numLiveNeighbors = 0;
        for(int r = (this.row-1); r < (this.row+2); r+=1){
            for(int c = (this.column-1); c < (this.column+2); c+=1){
                if(cells[r][c].cellState == CellState.ALIVE || cells[r][c].cellState == CellState.WILL_DIE){
                    numLiveNeighbors ++;
                }
            }
        }
        if(cellState == CellState.ALIVE){
            numLiveNeighbors --;
        }
        return numLiveNeighbors;
    }

}
