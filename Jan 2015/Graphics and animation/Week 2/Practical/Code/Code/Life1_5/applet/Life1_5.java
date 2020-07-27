import processing.core.*; 
import processing.xml.*; 

import java.applet.*; 
import java.awt.Dimension; 
import java.awt.Frame; 
import java.awt.event.MouseEvent; 
import java.awt.event.KeyEvent; 
import java.awt.event.FocusEvent; 
import java.awt.Image; 
import java.io.*; 
import java.net.*; 
import java.text.*; 
import java.util.*; 
import java.util.zip.*; 
import java.util.regex.*; 

public class Life1_5 extends PApplet {

int SquareSide = 20;//Side of individual squares
int Rank = 20;// Number of squares per side
int Side = SquareSide * Rank; // Clearly the width and height of the canvas
int Yellow = color(255, 255, 0),
      Red = color(255, 0, 0),
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
       Action = new Square(3 * SquareSide/2, Side + SquareSide/2, SquareSide, Green, White),
       RandSetUp = new Square(5 * SquareSide/2, Side + SquareSide/2, SquareSide, Red, White);
// Initially we set up not to run       
boolean Q_Run = false;  

public void setup() {
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
public void DrawBoard() {
  int i,
      j;
  for(i = 0; i < Rank; i++) {
    for(j = 0; j < Rank; j++) {      
      Board[i][j].Draw(); // draw the board
    }
  }  
} 

public void draw() {
  int i, j;
  int Code;
  DrawBoard();
  Action.Draw();//draw the buttons
  Flip.Draw();
  RandSetUp.Draw();  //
}

// Flips Q_Run and updates state button colour
public void UpdateState() {
  if (Q_Run) {
    Action.FillCol = Green;//NOT Q_Run next
  }
  else {
    Action.FillCol = Blue;//Q_Run next
  }
  Q_Run = ! Q_Run;
}

public void FlipSquare() {
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
public Square Get_SquareClicked() {
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
public void UpdateBooleans() {
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

// Counts up the neighbouring states
public int get_Code(int x, int y){
  int i,
      j;
  int Sum = 0;      
  for (i = -1; i < 2; i++) {
    for(j = -1; j < 2; j++) {
      if (i!=0 || j!= 0) {
        // Use of modulus operator % enables 'wrap around'
        if (Q_States[(i + x + Rank) % Rank][(j + y + Rank) % Rank]){
          Sum++;
        }
      }
    }
  }  
  return(Sum);
} 

public void UpdateDisplay() {
  int i,
      j;
  int Code;
  
  for(i = 0; i < Rank; i++) {
    for(j = 0; j < Rank; j++) {
      Code = get_Code(i, j); //get_Code tots up how many neighbours square (i, j) has
      // Updates the board according to the boolean array and the value of Code
      if (Q_States[i][j]) {//square is currently yellow
        if (Code < 2 || 3 < Code) {
          Board[i][j].FillCol = Black;
        }
      }
      else {//square is currently black
        if (Code == 3) {
          Board[i][j].FillCol = Yellow;
        }
      }
    }
  }
}

public void UpdateBoard() {
  if (!Q_Run) {
    FlipSquare();
  }
  else {
    UpdateBooleans();
    UpdateDisplay();
  }
}

public void DoRandomSetUp() {
  float R;
  float End = 0.01f;
  R = random(1);
  while(R > End) {
    Board[PApplet.parseInt(random(Rank))][PApplet.parseInt(random(Rank))].FillCol = Yellow;
    R = random(1);
  }
}

public void mousePressed() {  
  if (Flip.Q_Contains(mouseX, mouseY)) {// flips between set up and run
    UpdateState();
  }
  else {
    if(RandSetUp.Q_Contains(mouseX, mouseY)) {
      DoRandomSetUp();
    } 
    UpdateBoard();// update the whole board
  }
}
class Square {
  int xC,
      yC;
  int Side;
  int FillCol,
        EdgeCol;

  public Square(int xC, int yC, int Side, int FillCol, int EdgeCol) {
    this.xC = xC;
    this.yC = yC;
    this.Side = Side;
    this.FillCol = FillCol;
    this.EdgeCol = EdgeCol;
  }
  
  public void Draw() {
    fill(FillCol);
    stroke(EdgeCol);
    rectMode(CENTER);
    rect(xC, yC, Side,Side);
  }
  
  public boolean Q_Contains(int x, int y) {
    return((x >= xC - Side/2) && (xC + Side/2 > x) &&
           (y >= yC - Side/2) && (yC + Side/2 > y));
  }  
} 
  static public void main(String args[]) {
    PApplet.main(new String[] { "--bgcolor=#F0F0F0", "Life1_5" });
  }
}
