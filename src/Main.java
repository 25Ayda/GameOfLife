import processing.core.PApplet;

public class Main extends PApplet {
    private final int NUM_ROWS = 50;
    private final int NUM_COLUMNS = 100;
    private final int CELL_SIZE = 10;
    private Cell[][] cells;
    private boolean doEvolve;
    public static PApplet app;
    public Main(){
        app = this;
    }
    
    public static void main(String[] args) {
        PApplet.main("Main");
    }

    @Override
    public void settings() {
        super.settings();
        size(NUM_COLUMNS * CELL_SIZE, NUM_ROWS * CELL_SIZE);
    }

    @Override
    public void setup() {
        super.setup();
        doEvolve = false;
        cells = new Cell[NUM_ROWS][NUM_COLUMNS];
        for(int r = 0; r < NUM_ROWS; r+=1){ //iterates through rows of 2D Array
            for(int c = 0; c < NUM_COLUMNS; c+=1){ //iterates through columns of 2D Array
                MooreRules mooreRules = new MooreRules(new int[]{3}, new int[]{2, 3}); //mooreRules arrays
                cells[r][c] = new Cell(c * CELL_SIZE, r * CELL_SIZE, CELL_SIZE, r, c, CellState.DEAD, mooreRules);
                //creates new cell at each row and column.
            }
        }
        for(int r = 1; r < (NUM_ROWS-1); r+=1){
            for(int c = 1; c < (NUM_COLUMNS-1); c+=1){
                cells[r][c].randomState();
            }
        }
    }

    @Override
    public void draw() {
        //super.draw();
        if(doEvolve == true){
            applyRules();
            evolve();
        }
        for(int r = 0; r < NUM_ROWS; r+=1){
            for(int c = 0; c < NUM_COLUMNS; c+=1){
                cells[r][c].display();
            }
        }
        //noLoop();
    }

    @Override
    public void mouseClicked() {
        super.mouseClicked();
        cells[mouseY/CELL_SIZE][mouseX/CELL_SIZE].handleClick();
        redraw();
    }

    @Override
    public void keyPressed() {
        super.keyPressed();
        doEvolve = !doEvolve;
    }

    public void applyRules(){
        for(int r = 1; r < (NUM_ROWS-1); r+=1){
            for(int c = 1; c < (NUM_COLUMNS-1); c+=1){
                cells[r][c].applyRules(cells);
            }
        }
    }

    public void evolve(){
        for(int r = 1; r < (NUM_ROWS-1); r+=1){
            for(int c = 1; c < (NUM_COLUMNS-1); c+=1){
                cells[r][c].evolve();
            }
        }
    }

}