int SquareSide = 20;//Side of individual squares
int Rank = 20;// Number of squares per side
int Side = SquareSide * Rank; // Clearly the width and height of the canvas
color Yellow = color(255, 255, 0),
      Green = color(0, 255, 0),
      Blue = color(0, 0, 255),
      Orange = color(255, 160, 0),
      White = color(255, 255, 255),
      Black = color(0);
// A board is a set of locations, here squares       
Square[][] Board = new Square[Rank][Rank];   
// The isomorphic boolean array
boolean[][] Q_States = new boolean[Rank][Rank];
// Control buttons
Square Flip = new Square(SquareSide/2, Side + SquareSide/2, SquareSide, Orange, White),
       Action = new Square(3 * SquareSide/2, Side + SquareSide/2, SquareSide, Green, White);
// Initially we set up not to run       
boolean Q_Run = false;  

void setup() {
  int i,
      j;
  size(Side, Side + SquareSide);
  for(i = 0; i < Rank; i++) {
    for(j = 0; j < Rank; j++) {
      Board[i][j] = new Square(i * SquareSide + SquareSide/2, j * SquareSide + SquareSide/2, SquareSide, Black, White); //All squares are black 
      Q_States[i][j] = false;// all booleans are false
     }
  }
}
void DrawBoard() {
  int i,
      j;
  for(i = 0; i < Rank; i++) {
    for(j = 0; j < Rank; j++) {      
      Board[i][j].Draw(); // draw the board
    }
  }  
} 

void draw() {
  DrawBoard();
  Action.Draw();//draw the buttons
  Flip.Draw();  //
}

