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
  int i, j;
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

// Flips Q_Run and updates state button colour
void UpdateState() {
  if (Q_Run) {
    Action.FillCol = Green;//NOT Q_Run next
  }
  else {
    Action.FillCol = Blue;//Q_Run next
  }
  Q_Run = ! Q_Run;
}

void FlipSquare() {
  Square S;
  
  S = Get_SquareClicked();
  if (S != null) {
    if (S.FillCol == Yellow) {
      S.FillCol = Black;
    }
    else {
      S.FillCol = Yellow;
    }
  }
}

// If a square on the board has been clicked
//   returns that Square
// Else
//   returns null
Square Get_SquareClicked() {
  Square S;
  int i,
      j;
  for(i = 0; i < Rank; i++) {
    for(j = 0; j < Rank; j++) {
      S = Board[i][j];
      if(S.Q_Contains(mouseX, mouseY)) {
        return(S);
      }
    }
  }
  return(null);
} 

// Map the Square array on to the boolean array thus saving info
void UpdateBooleans() {
  int i,
      j;
  for(i = 0; i < Rank; i++) {
    for(j = 0; j < Rank; j++) {
      if (Board[i][j].FillCol == Black) {
        Q_States[i][j] = false;
      }
      else {
        Q_States[i][j] = true;
      }
    }
  }
}

void UpdateBoard() {
  if (!Q_Run) {
    FlipSquare();
  }
  else {
    UpdateBooleans();
    //UpdateDisplay();
  }
}

void mousePressed() {
  
  if (Flip.Q_Contains(mouseX, mouseY)) {// flips between set up and run
    UpdateState();
  }
  else {
    UpdateBoard();// update the whole board
  }
}
