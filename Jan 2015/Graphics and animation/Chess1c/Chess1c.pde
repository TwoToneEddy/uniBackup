/* OpenProcessing Tweak of *@*http://www.openprocessing.org/sketch/8530*@* */
/* !do not delete the line above, required for linking your tweak if you upload again */


// MAIN ===================================================

//chess. 
//
//Makes use of shapes3D by Quark with picking. 
//Toggle view by cursor down and up.

//You can only pick the Board's Fields and not 
//the figures. 

// Use peasy-cam.

// The general design is from the famous Baushaus-chess. 

// There are no checks performed if the move is valid etc. 

//Be careful: the Board runs from [0 to 7] [0 to 7].
//The internal command to make a move is:  MakeAMove ("E2E4").
//So we use E2E4 (not e2e4) which comes to 5,2 moves to 5,4, 
//but those number refer to a frame 1 to 8 so we have substract 1
//to get a frame 0 to 7 as required by Board. 

// imports ================================================

/** opengl **/
import processing.opengl.*;

/** PeasyCam **/
import peasy.*;

/**
 * library for the shape picking feature. 
 * This uses the off-screen buffer version of the pick
 * shape algorithm.
 * created by Peter Lager 2010
 */
import shapes3d.utils.*;
import shapes3d.*;

// ====================================================================================

// Board 
String [][] Board ;  

// color for Board (from Wikipedia)
int col1 = #d18b47; 
int col2 = #ffce9e;   

// color for figures
color colWhite = 253;
color colBlack = 72; 

// Camera
PeasyCam cam;
CameraState MyState; 
CameraState MyStateMove; 

// boxes 
Box[] box = new Box[66];
int iBox=0;

// get Input: status 
final int constStatusGetFromField = 0 ; 
final int constStatusGetToField = 1 ; 
int status1 = constStatusGetFromField ; 

// get Input: from and to 
int FromField = 0; 
int ToField = 0; 

// Game logic 
final int PlayerHuman = 0; 
final int PlayerPC = 1; 
int statusPlayer = PlayerHuman; 

// Player colors 
final int PlayerBlack = 0; 
final int PlayerWhite = 1; 
int PC_playsColor = PlayerBlack; 
// 0 = PC against PC, 1 = PC against human, 
// 2 human against human
int NumberOfPlayer = 1; 

// move as animation
boolean moveRunning=false; 
int moveRunningX_From=-1; 
int moveRunningY_From=-1; 
int moveRunningX_To=-1;
int moveRunningY_To=-1;    

float moveRunningX_ScreenFrom = 0.0;
float moveRunningY_ScreenFrom = 0.0;
float moveRunningX_ScreenTo = 0.0;
float moveRunningY_ScreenTo = 0.0;
float moveRunningX_ScreenAdd =0.0;
float moveRunningY_ScreenAdd =0.0;
boolean moveRunningXGT_Flag = false; // x greater or smaller 
boolean moveRunningYGT_Flag = false; // y greater or smaller 

// showFiguresAsImages yes/no
boolean showFiguresAsImages = false; 

// new game wish 
boolean newGame = false; 

// ====================================================================================

void setup () { 
  // P3D or OPENGL 
  // size( screen.width, screen.height, OPENGL );  
  size( 580, 480, P3D );    
  cam = new PeasyCam(this, 400);
  cam.setMinimumDistance(250);
  cam.setMaximumDistance(600); 
  cam.setResetOnDoubleClick (false); 
  Board= SetField();
  InitBoxes (); 
}  

void draw () {
  background (100);
  if (newGame) {
    background (100);    
    status1 = constStatusGetFromField ;     
    moveRunning=false;
    InitBoxes (); 
    Board = SetField();
    CheckeredBoard () ; 
    if (!showFiguresAsImages) {
      ShowFigures (); 
    }      
    newGame=false; 
  }  
  lights();
  directionalLight(254,254,254, 1, 1, 1);
  // directionalLight(254,254,254, -1, 1, 1);  
  // ambientLight(0,0,0, 1, 1, 1); // (0,0,0);

  CheckeredBoard () ; 
  if (!showFiguresAsImages) {
    ShowFigures (); 
  }
  if (moveRunning) {
    moveRunningControl(); 
    delay(1);
  } 
  else if (statusPlayer == PlayerPC) {
    PC_Move ();
  }
} //draw 

void InitBoxes () {
  // boxes = fields of the board 
  for (int i=0; i<64;i++) {
    box[i] = new Box( this, 40, 5, 40 );
  }

}



