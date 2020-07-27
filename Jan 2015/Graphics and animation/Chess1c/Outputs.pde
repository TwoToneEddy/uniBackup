



// Board and Figures outputs are here. 
// Outputs, tools and minor functions ========================================

void CheckeredBoard() {
  color colLocal=0;
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
      PVector MyPVector = new PVector( 0.0, 0.0, 0.0 );
      MyPVector =  GetValuePVectorBoard (i,j);
      box[iBox].moveTo(MyPVector.x, MyPVector.y+11, MyPVector.z);
      if (showFiguresAsImages) { 
        if (Board [i][j]!= " ") {
          box[iBox].setTexture (GetFileName(i,j), 16 ) ; 
        }
      } 
      box[iBox].fill(colLocal,63); // ALL_SIDES
      box[iBox].fill(colLocal); // ALL_SIDES      
      box[iBox].tagNo = (i+1)*10 + (j+1);
      box[iBox].draw();
      iBox++; 
      popMatrix();

    } // for 
  } // for 

} // function   

void ShowFigures () {

int col1 = #d18b47; 
int col2 = #ffce9e;   

  stroke(col1);

  for (int i = 0; i < 8; i = i+1) {
    for (int j = 0; j < 8; j = j+1) {

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

String GetFileName (int i, int j) {
  // The images are from Wikipedia. 
  // The name of the images are: 
  //    "Chess_" + strNameFigure (k,q,p etc.) +  
  //    strColorFigure (l or d) + 
  //    strColorBoard (l or d) + 
  //    "44.png"
  // e.g.  Chess_kll44.png ;   
  String theName = " "; 
  String strColorFigure = " "; 
  String strColorBoard = " "; 
  String strNameFigure = " "; 

  strNameFigure = trim( Board [i][j].toLowerCase() );
  if (GetFigureColor(Board [i][j]) == PlayerBlack) { 
    strColorFigure = "d"; 
  } 
  else   { 
    strColorFigure = "l"; 
  } 

  strColorBoard = GetColorFieldAsString (i,j); 

  if (Board [i][j] != " ") {
    theName = "Chess_" + strNameFigure +  
      strColorFigure + strColorBoard + "44.png" ;
  }

  return(theName);

} // function   

String GetColorFieldAsString (int i,int j) {
  // delivers l or d for field-color
  final String strCol1 = "d"; 
  final String strCol2 = "l";   
  String Buffer = ""; 
  // % is modulo, meaning rest of division 
  if (i%2 == 0) { 
    if (j%2 == 0) { 
      Buffer=strCol1;
    }
    else 
    {
      Buffer=strCol2;          
    }
  }  
  else {
    if (j%2 == 0) { 
      Buffer=strCol2;
    }
    else 
    {
      Buffer =strCol1;
    }
  } // if
  return(Buffer); 
}

void queen (int i,int j,int col3) {
  int col1 = 103; 
  int col2 = 11;   
  fill(col3);
  stroke(OtherFigureColor(col3));  
  pushMatrix();
  PVector MyPVector = new PVector( 0.0, 0.0, 0.0 );
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

void king (int i,int j,int col3) {
  int col1 = 103; 
  int col2 = 11;   
  fill(col3);
  stroke(OtherFigureColor(col3));  
  pushMatrix();
  PVector MyPVector = new PVector( 0.0, 0.0, 0.0 );
  MyPVector =  GetValuePVector (i,j);
  translate ( MyPVector.x, MyPVector.y, MyPVector.z );
  box (20);
  translate ( 0,-15,0 );
  rotateY(radians(45));
  box (10);
  popMatrix();
}

void pawn (int i,int j,int col3) {
  int col1 = 103; 
  int col2 = 11;   
  fill(col3);
  stroke(OtherFigureColor(col3));  
  pushMatrix();
  PVector MyPVector = new PVector( 0.0, 0.0, 0.0 );
  MyPVector =  GetValuePVector (i,j);
  translate ( MyPVector.x, MyPVector.y, MyPVector.z );
  box (17);
  noStroke();
  popMatrix();
}

void rook (int i,int j,int col3) {
  // tower, marquess, rector, 
  // and comes, and 
  // non-players still 
  // often call it a "castle". 
  // (from Wikipedia)
  int col1 = 103; 
  int col2 = 11;   
  fill(col3);
  stroke(OtherFigureColor(col3));  
  pushMatrix();
  PVector MyPVector = new PVector( 0.0, 0.0, 0.0 );
  MyPVector =  GetValuePVector (i,j);
  translate ( MyPVector.x, MyPVector.y, MyPVector.z );
  box (25);
  popMatrix();
}

void bishop (int i,int j,int col3) {
  int col1 = 103; 
  int col2 = 11;   
  fill(col3);
  stroke(OtherFigureColor(col3));  
  pushMatrix();
  PVector MyPVector = new PVector( 0.0, 0.0, 0.0 );
  MyPVector =  GetValuePVector (i,j);
  translate ( MyPVector.x, MyPVector.y, MyPVector.z );
  rotateY(radians(45));
  box (5,25,25);
  rotateY(radians(90));
  box (5,25,25);  
  noStroke();
  popMatrix();
}

void knight (int i,int j,int col3) {
  // jumps 2 and then 1
  int col1 = 103; 
  int col2 = 11;   
  fill(col3);
  stroke(OtherFigureColor(col3));
  pushMatrix();
  PVector MyPVector = new PVector( 0.0, 0.0, 0.0 );
  MyPVector =  GetValuePVector (i,j);
  translate ( MyPVector.x-5, MyPVector.y+5, MyPVector.z-4 );
  ShowL (col3); 
  rotateY(radians(180));
  translate ( -10,-10,-10 );
  ShowL (col3); 
  popMatrix();
}

void ShowL (int col3) {
  // help-sub for the knight
  int col1 = 103; 
  int col2 = 11;   
  int Size1 = 10; 
  fill(col3);
  stroke(OtherFigureColor(col3));
  pushMatrix();
  box (Size1);
  translate ( Size1,0,0 );
  box (Size1);  
  translate ( 0,0,Size1 );
  box (Size1);  
  noStroke();
  popMatrix();
}

int OtherFigureColor (int col3) {
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
    Buffer=colWhite-60;
  } 
  else 
  {
    Buffer=50;
  };

  return(Buffer);

}

int OtherFigureColorQueen (int col3) {
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
    Buffer=colWhite-17; 
  } 
  else 
  {
    Buffer=70; 
  };

  return(Buffer);

}

// ------------------------------------------------------------------------

PVector GetValuePVector ( int x1, int y1 ) {
  // here the calculation for the positions of 
  // figures is made. 
  if ((x1==moveRunningX_From) && (y1==moveRunningY_From)) {
    PVector MyPVector = new PVector( 0.0, 0.0, 0.0 );
    MyPVector.set ( moveRunningX_ScreenFrom,160,moveRunningY_ScreenFrom);
    return(MyPVector);
  } 
  else {
    PVector MyPVector = new PVector( 0.0, 0.0, 0.0 );
    MyPVector.set (  40*x1-160,160,40*(7-y1)-230 );
    return(MyPVector);
  }
}

PVector GetValuePVectorBoard ( int x1, int y1 ) {
  // here the calculation for the positions of 
  // board is made. 
  PVector MyPVector = new PVector( 0.0, 0.0, 0.0 );
  MyPVector.set (  40*x1-160,160,40*(7-y1)-230 );
  return(MyPVector);
}



