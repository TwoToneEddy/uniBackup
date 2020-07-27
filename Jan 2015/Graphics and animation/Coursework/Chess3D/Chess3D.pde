
final static int colomns = 8;
final static int rows = 8;
final static int boardSize = 600;
Board gameBoard;
color black = color(0,0,0);
color white = color(255,255,255);
color red = color(255,0,0);
color green = color(0,255,0);

void setup(){
  size(boardSize,boardSize,P3D);
  gameBoard = new Board(boardSize,black,white);
  gameBoard.initialiseBoard();
  gameBoard.draw();
}

void draw(){
  //gameBoard.draw();
 
}
void mousePressed(){
  println("X:"+(mouseX-100)+" Y:"+(mouseY-250));
}

