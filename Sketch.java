import processing.core.PApplet;

public class Sketch extends PApplet {
  
	int CELL_WIDTH = 20;
  int CELL_HEIGHT = 20;
  int MARGIN = 5;
  int ROW_COUNT = 10;
  int COLUMN_COUNT = 10;
  int SCREEN_WIDTH = (ROW_COUNT * CELL_WIDTH) + ((ROW_COUNT + 1) * MARGIN);
  int SCREEN_HEIGHT = (COLUMN_COUNT * CELL_HEIGHT) + ((COLUMN_COUNT + 1) * MARGIN);
  
  int intGrid[][] = new int[10][10];

  public void settings() {
    size(SCREEN_WIDTH, SCREEN_HEIGHT);
  }

  public void setup() {
    //Initialize all cells to 0 (white)
    background(0,0,0);
    for(int i = 0; i < 10; i++){
      for(int j = 0; j < 10; j++){
        intGrid[i][j] = 0;
      }
    }
  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {
    // Draw all the squares, green or white depending on if they are selected
    for(int column = 0; column < COLUMN_COUNT; column++){
      for(int row = 0; row < ROW_COUNT; row++){
        if(intGrid[row][column] == 1){
          fill(12, 232, 52);
        }
        else{
          fill(255);
        }
        
        rect(MARGIN + (column * (CELL_WIDTH + MARGIN)),MARGIN + (row * (CELL_HEIGHT + MARGIN)),CELL_WIDTH, CELL_HEIGHT);
      }
    }
    
  }

  public void mousePressed(){
    //Calculate and print the mouse/grid coordinate that the mouse just clicked
    int gridX = (mouseY - MARGIN)/(CELL_HEIGHT + MARGIN);
    int gridY = (mouseX - MARGIN)/(CELL_WIDTH + MARGIN);
    System.out.print("mouse coordinates: (" + mouseX +","+ mouseY +"); ");
    System.out.println("grid coordinates: (" + gridX +","+ gridY +")");

    // Changes surrounding blocks and one that is clicked, if not on edge
    intGrid[gridX][gridY] = switchColor(intGrid[gridX][gridY]);
    //Left
    if(gridX - 1 >= 0){
      intGrid[gridX - 1][gridY] = switchColor(intGrid[gridX - 1][gridY]);
    }
    //Right
    if(gridX + 1 < 10){
      intGrid[gridX + 1][gridY] = switchColor(intGrid[gridX + 1][gridY]);
    }
    //Up
    if(gridY - 1 >= 0){
      intGrid[gridX][gridY - 1] = switchColor(intGrid[gridX][gridY - 1]);
    }
    //Down
    if(gridY + 1 < 10){
      intGrid[gridX][gridY + 1] = switchColor(intGrid[gridX][gridY + 1]);
    }

    // Count total green cells by looping through grid and counting 1s
    int totalSelected = 0;
    for(int row = 0; row < ROW_COUNT; row++){
      for(int column = 0; column < COLUMN_COUNT; column++){
        if(intGrid[row][column] == 1){
          totalSelected++;
        }
      }
    }
    System.out.println("Total of "+ totalSelected+" cells are selected.");
    
    // Print Row Totals, and also biggest continuous block if bigger than 2
    for(int row = 0; row < ROW_COUNT; row++){
      int rowTotal = 0;
      int continuousSelected = 0;
      int mostContinuous = 0;
      for(int column = 0; column < COLUMN_COUNT; column++){
        if(intGrid[row][column] == 1){
          rowTotal++;
          continuousSelected++;
        }
        else{
          if(continuousSelected > mostContinuous){
            mostContinuous = continuousSelected;
          }
          continuousSelected = 0;
        }
        if(continuousSelected > mostContinuous){
            mostContinuous = continuousSelected;
        }
      }
      if(mostContinuous > 2){
        System.out.println("There are "+ mostContinuous +" continuous blocks selected on row " + row +".");
      }
      System.out.println("Row "+ row +" has " + rowTotal +" cells selected.");
    }

    // Prints Column Totals
    for(int column = 0; column < COLUMN_COUNT; column++){
      int columnTotal = 0;
      for(int row = 0; row < ROW_COUNT; row++){
        if(intGrid[row][column] == 1){
          columnTotal++;
        }
      }
      System.out.println("Column "+ column +" has " + columnTotal +" cells selected.");
    }

    
  }

  // Switches the color from 0 to 1 when color is inputted, and returns it to change in  mousePressed()
  public int switchColor(int currentColor){
    if(currentColor == 1){
      return 0;
    }
    else{
      return 1;
    }
  }
}