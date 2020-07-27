import processing.core.*; 
import processing.xml.*; 

import peasy.*; 
import shapes3d.utils.*; 
import shapes3d.*; 

import java.applet.*; 
import java.awt.*; 
import java.awt.image.*; 
import java.awt.event.*; 
import java.io.*; 
import java.net.*; 
import java.text.*; 
import java.util.*; 
import java.util.zip.*; 
import java.util.regex.*; 

public class Chess1b extends PApplet {



// MAIN ===================================================

//chess. 
//
//Makes use of shapes3D by Quark with picking. 
//You can only pick the Board's Fields and not 
//the figures. 
// Use peasy-cam.
// The general design is from the famous Baushaus-chess. 
// There are no checks if the move is valid etc. 

//Be careful: the Board runs from [0 to 7] [0 to 7].
//The internal command to make a move is:  MakeAMove ("E2E4").
//So we use E2E4 (not e2e4) which comes to 5,2 moves to 5,4, 
//but those number refer to a frame 1 to 8 so we have substract 1
//to get a frame 0 to 7 as required by Board. 

// imports ================================================

/** opengl **/
// import processing.opengl.*;

/** PeasyCam **/


/**
 * library for the shape picking feature. 
 * This uses the off-screen buffer version of the pick
 * shape algorithm.
 * created by Peter Lager 2010
 */



// ====================================================================================

// Board 
String [][] Board ;  

// color for Board (from Wikipedia)
int col1 = 0xffd18b47; 
int col2 = 0xffffce9e;   

// color for figures
int colWhite = 253;  // figures 
int colBlack = 5; 

// Camera
PeasyCam cam;
CameraState MyState; 
CameraState MyStateMove; 

// boxes 
Box[] box = new Box[66];
int iBox=0;

// get Input
final int constStatusGetFromField = 0 ; 
final int constStatusGetToField = 1 ; 
int status1 = constStatusGetFromField ; 

// get Input
int FromField = 0; 
int ToField = 0; 

// ====================================================================================

public void setup()
{ 
  // P3D or OPENGL 
  // size( screen.width, screen.height, OPENGL );  
  size( 880, 680, P3D );    

  //  camera(-20, -120, (height/2.0) / tan(PI*60.0 / 360.0) * .3, 
  //  -20, 0, 90, 
  //  0, 1, 0);  
  cam = new PeasyCam(this, 400);
  // cam.setMinimumDistance(40);
  // cam.setMaximumDistance(6550); 
  // move
  // cam.pan(0,0);
  // cam.setDistance(830);
  cam.setResetOnDoubleClick (false); 

  Board= SetField();
  InitBoxes (); 
}  

public void draw () {
  background (100);
  lights();
  directionalLight(254,254,254, 1, 1, 1);
  // directionalLight(254,254,254, -1, 1, 1);  
  // ambientLight(0,0,0, 1, 1, 1); // (0,0,0);
  CheckeredBoard () ; 
  ShowFigures (); 
}

public void InitBoxes () {

  for (int i=0; i<64;i++) {
    box[i] = new Box( this, 40, 5, 40 );
  }

}







public String [][]SetField () {

  String[][] Buffer = new String[8][8]; 

  for (int i = 0; i < 8; i = i+1) {
    for (int j = 0; j < 8; j = j+1) {
      Buffer [i][j]  = " ";
    }
  }

  for (int i = 0; i < 8; i = i+1) {
    Buffer [i][6]  = "p"; 
  }  
  for (int i = 0; i < 8; i = i+1) {
    Buffer [i][1]  = "P";
  }    

  Buffer [3][0] = "Q"; //   queen (3,0,11);
  Buffer [3][7] = "q"; //   queen (3,7,230); 
  Buffer [4][0] = "K"; //   king (4,0,230);     
  Buffer [4][7] = "k"; //   king (4,7,11);

  Buffer [0][0] = "R"; //   rook (0,0,11);
  Buffer [7][7] = "r"; //   rook (7,7,230);   

  Buffer [7][0] = "R"; //   rook (7,0,11);
  Buffer [0][7] = "r"; //   rook (0,7,230);   

  Buffer [2][0] = "B"; //   bishop (2,0,11);
  Buffer [5][7] = "b"; //   bishop (5,7,230);   

  Buffer [5][0] = "B"; //   bishop (5,0,11);
  Buffer [2][7] = "b"; //   bishop (2,7,230);   

  Buffer [1][0] = "N"; //   knight (1,0,11);
  Buffer [6][7] = "n"; //   knight (6,7,230);   

  Buffer [6][0] = "N"; //   knight (6,0,11);
  Buffer [1][7] = "n"; //   knight (1,7,230);     

  return (Buffer); 

}      





// ==========================================================

public void mouseClicked () 
{

  // mouse evaluate. 
  // Check what's been 
  // pressed by the mouse.

  boolean done = false; 

  // Wenn linke Taste  
  if  (mouseButton == LEFT) {  
    //println("\u00a7");
    // by Quark
    Shape3D picked = Shape3D.pickShape(this,mouseX, mouseY);
    if (picked != null) {
      // picked.fill(color (254,0,0)); // randomColor()); 
      // println("\u00a71111 "+ picked.tagNo);
      done=true; 
      if (status1 == constStatusGetFromField) {
        if (fieldIsOk(picked.tagNo)) {
          FromField = picked.tagNo; 
          status1 = constStatusGetToField;
        } 
        else {
          // do nothing 
        }
      } 
      else if (status1 == constStatusGetToField)  {
        ToField = picked.tagNo;       
        status1 = constStatusGetFromField; 
        MakeAMove (makeToFieldExpression(FromField)+makeToFieldExpression(ToField)); 
      }
    }
  }
}

public void setup_TESTING () {
  // test
  println(makeToFieldExpression(42));
  println ( DivideWithoutModulo (483,100) ); 
  println ( DivideWithoutModulo (83,10) );   
  println ( DivideWithoutModulo (3,1) );   
  exit();
}

public boolean fieldIsOk (int Number1) {
  // delivers true if Field is not empty
  int LeftNumber = DivideWithoutModulo(Number1, 10); 
  int RightNumber = Number1 - (LeftNumber*10); 
  LeftNumber=LeftNumber-1; 
  RightNumber=RightNumber-1;
  if (Board[LeftNumber][RightNumber].equals (" ")) {
    println(LeftNumber + " " + RightNumber + ": EMPTY.");    
    return (false); 
  }
  else {  
    return (true); 
  }
} 

public String makeToFieldExpression(int Number1) {
  // delivers A1 from 11 
  String Buffer =""; 
  int LeftNumber = DivideWithoutModulo(Number1, 10); 
  int RightNumber =  Number1 - LeftNumber*10;   
  Buffer = str(PApplet.parseChar(LeftNumber+64)) + str(RightNumber); 
  Buffer=trim(Buffer);
  return (Buffer); 
}

public int DivideWithoutModulo ( int dividend1, int divisor1  ) {
  // Division: Give the answer as an integer quotient without the remainder. 
  // This is sometimes called integer division.
  // With 83, 10 the result is 8. 

  int Buffer = -1; 

  int ModuloResult = dividend1 % divisor1 ; 
  dividend1 = dividend1 - ModuloResult; 
  Buffer =  dividend1 / divisor1; 

  return (Buffer);

}



public void MakeAMove (String Move1) {

  int x1;
  int y1;  

  int x2;
  int y2;  

  boolean done = false; 

  x1= PApplet.parseInt(Move1.charAt(0))-65;   
  y1= integerFromChar(Move1.charAt(1))-1; 

  x2= PApplet.parseInt(Move1.charAt(2))-65; 
  y2= integerFromChar(Move1.charAt(3))-1;     

  print(x1 + " " + y1 + " to ");  
  println(x2 + " " + y2 + ".");    

  if (!((x1==x2) && (y1==y2))) {
    if (Board [x1][y1].charAt(0) != ' ' ) {
      Board [x2][y2] = Board [x1][y1]; 
      Board [x1][y1] = " ";
      done=true; 
    } 
  }
  if (done!=true) {
    println("Illegal."); 
  }
}

public int integerFromChar(char MyChar){

  int Buffer = 0; 

  switch (MyChar) {
  case '0':
    Buffer = 0; 
    break; 

  case '1':
    Buffer = 1; 
    break; 

  case '2':
    Buffer = 2; 
    break; 

  case '3':
    Buffer = 3; 
    break;   

  case '4':
    Buffer = 4; 
    break;   

  case '5':
    Buffer = 5; 
    break;   

  case '6':
    Buffer = 6; 
    break;   

  case '7':
    Buffer = 7; 
    break;   

  case '8':
    Buffer = 8; 
    break;   

  case '9':
    Buffer = 9; 
    break;   

  default: 
    // 
    break; 

  } // switch 

  return ( Buffer ) ;

}






// Outputs, tools and minor functions ========================================

public void CheckeredBoard() {
  int colLocal=0;
  stroke(col1);
  iBox=0;
  for (int i = 0; i < 8; i = i+1) {
    for (int j = 0; j < 8; j = j+1) {

      // % is modulo, meaning rest of division 
      if (i%2 == 0) { 
        if (j%2 == 0) { 
          fill ( col1 ); 
          colLocal=col1;
        }
        else 
        {
          fill ( col2 ); 
          colLocal=col2;          
        }
      }  
      else {
        if (j%2 == 0) { 
          fill ( col2 ); 
          colLocal=col2;
        }
        else 
        {
          fill ( col1 ); 
          colLocal=col1;
        }
      } // if

      pushMatrix();
      PVector MyPVector = new PVector( 0.0f, 0.0f, 0.0f );
      MyPVector =  GetValuePVector (i,j);
      box[iBox].moveTo(MyPVector.x, MyPVector.y+11, MyPVector.z);
      box[iBox].fill(colLocal,63); // ALL_SIDES
      box[iBox].tagNo = (i+1)*10 + (j+1);
      box[iBox].draw();
      iBox++; 
      popMatrix();

    } // for 
  } // for 

} // function   

public void ShowFigures () {
int col1 = 0xffd18b47; 
int col2 = 0xffffce9e;   
  stroke(col1);

  for (int i = 0; i < 8; i = i+1) {
    for (int j = 0; j < 8; j = j+1) {
      // println (Board[i][j]);

      switch (Board[i][j].charAt(0)) {
      case 'P':
        pawn ( i,j, colWhite )  ; 
        break; 
      case 'p':
        pawn ( i,j, colBlack )  ; 
        break;         
      case 'Q':
        queen ( i,j, colWhite )  ; 
        break; 
      case 'q':
        queen ( i,j, colBlack )  ; 
        break;
      case 'K':
        king ( i,j, colWhite )  ; 
        break; 
      case 'k':
        king ( i,j, colBlack )  ; 
        break;        
      case 'R':
        rook ( i,j, colWhite )  ; 
        break; 
      case 'r':
        rook ( i,j, colBlack )  ; 
        break;
      case 'B':
        bishop ( i,j, colWhite )  ; 
        break; 
      case 'b':
        bishop ( i,j, colBlack )  ; 
        break;        
      case 'N':
        knight ( i,j, colWhite )  ; 
        break; 
      case 'n':
        knight ( i,j, colBlack )  ; 
        break;                
      case ' ':
        break;   
      default:
        println("ERROR 234");
        break; 
      } // switch 
    } // for 
  } // for 

} // function   

public void queen (int i,int j,int col3) {
  int col1 = 103; 
  int col2 = 11;   
  fill(col3);
  stroke(OtherFigureColor(col3));  
  pushMatrix();
  PVector MyPVector = new PVector( 0.0f, 0.0f, 0.0f );
  MyPVector =  GetValuePVector (i,j);
  translate ( MyPVector.x, MyPVector.y, MyPVector.z );
  box (20);
  translate ( 0,-17,0 );
  // sphereDetail(11, 11);
  noStroke(); 
  fill(OtherFigureColorQueen(col3));    
  sphere(8); 
  popMatrix();
}

public void king (int i,int j,int col3) {
  int col1 = 103; 
  int col2 = 11;   
  fill(col3);
  stroke(OtherFigureColor(col3));  
  pushMatrix();
  PVector MyPVector = new PVector( 0.0f, 0.0f, 0.0f );
  MyPVector =  GetValuePVector (i,j);
  translate ( MyPVector.x, MyPVector.y, MyPVector.z );
  box (20);
  translate ( 0,-15,0 );
  rotateY(radians(45));
  box (10);
  popMatrix();
}

public void pawn (int i,int j,int col3) {
  int col1 = 103; 
  int col2 = 11;   
  fill(col3);
  stroke(OtherFigureColor(col3));  
  pushMatrix();
  PVector MyPVector = new PVector( 0.0f, 0.0f, 0.0f );
  MyPVector =  GetValuePVector (i,j);
  translate ( MyPVector.x, MyPVector.y, MyPVector.z );
  box (17);
  noStroke();
  popMatrix();
}

public void rook (int i,int j,int col3) {
  // castle, tower, marquess, rector, 
  // and comes (Sunnucks 1970), and 
  // non-players still often call it a "castle". 
  // (from Wikipedia)
  int col1 = 103; 
  int col2 = 11;   
  fill(col3);
  stroke(OtherFigureColor(col3));  
  pushMatrix();
  PVector MyPVector = new PVector( 0.0f, 0.0f, 0.0f );
  MyPVector =  GetValuePVector (i,j);
  translate ( MyPVector.x, MyPVector.y, MyPVector.z );
  box (25);
  popMatrix();
}

public void bishop (int i,int j,int col3) {
  int col1 = 103; 
  int col2 = 11;   
  fill(col3);
  stroke(OtherFigureColor(col3));  
  pushMatrix();
  PVector MyPVector = new PVector( 0.0f, 0.0f, 0.0f );
  MyPVector =  GetValuePVector (i,j);
  translate ( MyPVector.x, MyPVector.y, MyPVector.z );
  rotateY(radians(45));
  box (5,25,25);
  rotateY(radians(90));
  box (5,25,25);  
  noStroke();
  popMatrix();
}

public void knight (int i,int j,int col3) {
  // jumps 2 by 1
  int col1 = 103; 
  int col2 = 11;   
  //int i=4; 
  //int j=0; 
  fill(col3);
  stroke(OtherFigureColor(col3));
  pushMatrix();
  // translate ( 40*i-505,352,40*j-645 );
  PVector MyPVector = new PVector( 0.0f, 0.0f, 0.0f );
  MyPVector =  GetValuePVector (i,j);
  translate ( MyPVector.x-5, MyPVector.y+5, MyPVector.z-4 );
  ShowL (col3); 
  rotateY(radians(180));
  translate ( -10,-10,-10 );
  ShowL (col3); 
  popMatrix();
}

public void ShowL (int col3) {
  // help-sub for the knight
  int col1 = 103; 
  int col2 = 11;   
  int Size1 = 10; 

  //int i=4; 
  //int j=0; 
  fill(col3);
  stroke(OtherFigureColor(col3));
  pushMatrix();
  box (Size1);
  translate ( Size1,0,0 );
  // rotateY(radians(90));
  box (Size1);  
  translate ( 0,0,Size1 );
  box (Size1);  
  noStroke();
  popMatrix();
}

public int OtherFigureColor (int col3) {
  // This gives you a similar color 
  // for the figure's color. 
  // E.g. you have a white figure 
  // you here get another white-ish color
  // for the stroke lines of the figure.
  // For a black figure you get another 
  // shade of black or dark gray.

  int Buffer=0;

  if (col3==colWhite) 
  {
    Buffer=colWhite-60; // colBlack;
  } 
  else 
  {
    Buffer=50; // colBlack-0; // colWhite;
  };

  return(Buffer);

}

public int OtherFigureColorQueen (int col3) {
  // QUEEN -----------------
  // This gives you a similar color 
  // for the figure's color. 
  // E.g. you have a white figure 
  // you here get another white-ish color
  // for the stroke lines of the figure.
  // For a black figure you get another 
  // shade of black or dark gray.

  int Buffer=0;

  if (col3==colWhite) 
  {
    Buffer=colWhite-17; // colBlack;
  } 
  else 
  {
    Buffer=50; // colBlack-0; // colWhite;
  };

  return(Buffer);

}

// ------------------------------------------------------------------------

public PVector GetValuePVector ( int x1, int y1 ) {
  // here the calculation for the positions of 
  // figures and board is made. 
  PVector MyPVector = new PVector( 0.0f, 0.0f, 0.0f );
  MyPVector.set (  40*x1-160,160,40*(7-y1)-230 );
  return(MyPVector);
}



  static public void main(String args[]) {
    PApplet.main(new String[] { "--bgcolor=#ece9d8", "Chess1b" });
  }
}
