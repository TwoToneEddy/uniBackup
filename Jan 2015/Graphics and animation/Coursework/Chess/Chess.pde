/*
Author: Lee Hudson 09092543
Date: 31/03/2015
Description: Game of chess. A piece is selected by left clicking on the piece, the selected piece
is then moved by right clicking on desired square. Only valid moves may be made, these are made 
apparent by a green "isValid" move indicator. When the king is in check the usual valid move 
indicators are display but only a move that gets the king out of check is allowed. Checkmate is
detected, once checkmate is detected no user interaction is permitted. Castling has been implemented.
*/
final static float scaleFactor = 1.2;
final static int colomns = 8;
final static int rows = colomns;
final static float w = 502*scaleFactor, h = 672*scaleFactor;
final static float boardSize = 300*scaleFactor;
final static float boardOffsetX = 100*scaleFactor;
final static float boardOffsetY = 235*scaleFactor;
final static int moveVelocity = 2;
float tileSize = boardSize/colomns;
PImage backgroundImage;
Board gameBoard;
Piece[] teamA,teamB;
Tile selectedTile;
boolean player,check,checkMate;
color black = color(0,0,0);
color white = color(255,255,255);
color maroon = color(155,89,91);
color pink = color(255,239,206);
color red = color(255,0,0);
color green = color(0,255,0);

void setup(){
  size(int(w),int(h));
  backgroundImage = loadImage("table.jpg");
  gameBoard = new Board(boardSize,maroon,pink);
  gameBoard.initialiseBoard();
  teamA = createTeam(true,boardSize/8);
  teamB = createTeam(false,boardSize/8);
  assignPiecesToTiles();
  player = false;
  check = false;
  checkMate = false;
}

void draw(){
  image(backgroundImage,0,0,w,h);
  
  pushMatrix();
    translate(boardOffsetX,boardOffsetY);
    gameBoard.refresh();
    for(int i = 0; i < teamA.length; i++){
      teamA[i].refresh();
      teamB[i].refresh();
    }
  popMatrix();
  if(player){
    fill(0, 0, 0);
    textSize(32);
    text("Black", 250, 750);
  }else{
    fill(255, 255, 255);
    textSize(32);
    text("White", 250, 750);
  }
  if(check){
    if(checkMate){
      fill(white);
      textSize(32);
      text("CHECKMATE!!", 240, 150);
    }else{
      fill(white);
      textSize(32);
      text("CHECK!!", 240, 150);
    }
    
    
  }
  
}

//Sets up the array of pieces for each team. These pieces are also referenced in the tiles
//themselves but a reference in the main class is useful for drawing purposes. In hindsight
//this could probably have been avoided but as java uses only references this isn't so bad.
Piece[] createTeam(boolean  team, float side){
  Piece[] teamPieces = new Piece[16];
  int pawnRow,kingRow;
  if(team){
    pawnRow = 1;
    kingRow = 0;
  }else{
    pawnRow = 6;
    kingRow = 7;
  }
  
  int i;
  for(i = 0; i < 8; i++){
    teamPieces[i] = new Pawn(team,(i*side)+(side/2),(pawnRow*side)+(side/2),side,moveVelocity);
  }
    teamPieces[i] = new Rook(team,((i-8)*side)+(side/2),(kingRow*side)+side/2,side,moveVelocity);
    i++;
    teamPieces[i] = new Knight(team,((i-8)*side)+(side/2),(kingRow*side)+side/2,side,moveVelocity);
    i++;
    teamPieces[i] = new Bishop(team,((i-8)*side)+(side/2),(kingRow*side)+side/2,side,moveVelocity);
    i++;
    teamPieces[i] = new Queen(team,((i-8)*side)+(side/2),(kingRow*side)+side/2,side,moveVelocity);
    i++;
    teamPieces[i] = new King(team,((i-8)*side)+(side/2),(kingRow*side)+side/2,side,moveVelocity);
    i++;
    teamPieces[i] = new Bishop(team,((i-8)*side)+(side/2),(kingRow*side)+side/2,side,moveVelocity);
    i++;
    teamPieces[i] = new Knight(team,((i-8)*side)+(side/2),(kingRow*side)+side/2,side,moveVelocity);
    i++;
    teamPieces[i] = new Rook(team,((i-8)*side)+(side/2),(kingRow*side)+side/2,side,moveVelocity);
      
  return teamPieces;
}

//Converts colomns and rows to actual coordinates for the piece to move to.
//This should have been implemented in the piece class but was overlooked
//and hence covered here.
float convertLocation(int loc){
  return (loc*(boardSize/8))+((boardSize/8)/2);
}

//Allocated every piece to a tile.
void assignPiecesToTiles(){
  for(int i = 0; i < colomns; i++){
    gameBoard.tiles[i][0].setPiece(teamA[8+i]);
    gameBoard.tiles[i][1].setPiece(teamA[i]);
    gameBoard.tiles[i][7].setPiece(teamB[8+i]);
    gameBoard.tiles[i][6].setPiece(teamB[i]);
  }
  
}

//Clicks are not registered if there is a piece moving or a player is in checkmate.
//Left clicks select a piece, right clicks select the tile to move the selected piece to.
void mousePressed(){
  if(!gameBoard.pieceMoving()&& !checkMate){
   float x,y;
   Piece tempPiece;
   Tile tempTile;
   
   if(gameBoard.boardClicked(mouseX-int(boardOffsetX),mouseY-int(boardOffsetY))){
     //Left click selects the piece
     if(mouseButton == LEFT){
       tempTile = gameBoard.tileClicked(mouseX-int(boardOffsetX),mouseY-int(boardOffsetY));
       if(tempTile.getPiece()!=null)
         tempPiece = tempTile.getPiece();
       else
         tempPiece = null;
       
       
       //if the tile clicked is empty
       if(tempPiece == null){
         if(selectedTile != null){
           clearAllMoveIndicators();
           selectedTile.getPiece().resetSelected();
           selectedTile = null;
         }
       }else{
         if(selectedTile == null){
           //Piece Selected
           if(tempTile.getPiece().team == player){
             selectedTile = tempTile;
             setMoveIndicators(selectedTile,0);
             selectedTile.getPiece().setSelected();
           }
         }else{
           if(tempTile.getPiece().team == player){
             selectedTile.getPiece().resetSelected();
             selectedTile = tempTile;
             setMoveIndicators(selectedTile,0);
             selectedTile.getPiece().setSelected();
           }
           
         }
       }
     }else{
       //Right click moves the piece
       tempTile = gameBoard.tileClicked(mouseX-int(boardOffsetX),mouseY-int(boardOffsetY));
       
       //If the tile to move to is empty just do it
       if(tempTile.isEmpty()){
         //If we have a piece selected
         if(selectedTile != null){
           if(moveRemovesCheckStatus(tempTile.col,tempTile.row,selectedTile.col,selectedTile.row)){
             movePieceToTile(tempTile);
           }
         }
       }else{
         //If it's not empty is it a take?
         if(tempTile.getPiece()!=null){
           if(tempTile.getPiece().team != selectedTile.getPiece().team){
             if(moveRemovesCheckStatus(tempTile.col,tempTile.row,selectedTile.col,selectedTile.row)){
             tempTile.getPiece().taken();
             tempTile.setPiece(null);
             movePieceToTile(tempTile);
             }
           }else{
           }
       }
       }
     }
   }
  }

}

//This method moves a piece to its destination.
void movePieceToTile(Tile destination){
    Tile rookToCastle=null;
    int colToCastleTo = -1,rowToCastle = -1;
    boolean castling = false;
    if(destination.isValidIndicator){
      if((destination.col == selectedTile.col+2)||(destination.col == selectedTile.col-2)){
        if(selectedTile.getPiece().type == 6){
          if(player){
            //black
            if(destination.col==6){
              rookToCastle = gameBoard.tiles[7][0];
              colToCastleTo = 5;
              rowToCastle=0;
            }
            if(destination.col==2){
              rookToCastle = gameBoard.tiles[0][0];
              colToCastleTo = 3;
              rowToCastle=0;
            }
          }else{
            //white
            if(destination.col==6){
              rookToCastle = gameBoard.tiles[7][7];
              colToCastleTo = 5;
              rowToCastle=7;
            }
            if(destination.col==2){
              rookToCastle = gameBoard.tiles[0][7];
              colToCastleTo = 3;
              rowToCastle=7;
            }
          }
          castling = true;
        }
      }
      clearAllMoveIndicators();
      selectedTile.getPiece().resetSelected();
      Piece tempPiece = selectedTile.getPiece();
      selectedTile.getPiece().move(convertLocation(destination.col),convertLocation(destination.row));
      destination.setPiece(tempPiece);
      selectedTile.setPiece(null);
      selectedTile=null;
      
      if(castling){
        destination = gameBoard.tiles[colToCastleTo][rowToCastle];
        selectedTile = rookToCastle;
        selectedTile.getPiece().resetSelected();
        Piece castleTempPiece = selectedTile.getPiece();
        selectedTile.getPiece().move(convertLocation(destination.col),convertLocation(destination.row));
        destination.setPiece(castleTempPiece);
        selectedTile.setPiece(null);
        selectedTile=null;
      }
      
      player=!player;
      if(isCheck(gameBoard)){
        check = true;
      }else{
        check = false;
      }
      if(checkForCheckMate()){
        println("Checkmate");
        checkMate = true;
      }
    }
    
  //}
}

//This returns true if a potential move results in the check status being removed.
boolean moveRemovesCheckStatus(int potentialMoveCol, int potentialMoveRow, int currentCol , int currentRow){
   
  Tile potentialMove = gameBoard.tiles[potentialMoveCol][potentialMoveRow];
  Tile currentTile = gameBoard.tiles[currentCol][currentRow];
  
  Piece potentialStorePiece;
  if(potentialMove.getPiece() != null)
    potentialStorePiece = potentialMove.getPiece();
  else
    potentialStorePiece = null;
    
  boolean result;
 // if(check){
    Piece storePiece = currentTile.getPiece();
    potentialMove.setPiece(currentTile.getPiece());
    currentTile.setPiece(null);
    if(isCheck(gameBoard)){
      println("false,still int check");
      result = false;
    }else{
      println("true,no longer in check");
      result = true;
    }
    potentialMove.setPiece(potentialStorePiece);
    currentTile.setPiece(storePiece);;
    return result;
  
}

void clearAllMoveIndicators(){
  gameBoard.clearAllMoveIndicators();
}
void clearAllCheckIndicators(){
  gameBoard.clearAllCheckIndicators();
}

void clearAllCheckMateIndicators(){
  gameBoard.clearAllCheckMateIndicators();
}

//This method sets the move indicators for a given piece. The type of indicator
//it sets is given in the parameter checkType:
//checkType = 0 Usual move indicator
//checkType = 1 Check indicator (not displayed)
//checkType = 2 Check mate indicator (not displayed)
void setMoveIndicators(Tile tile, int checkType){
  if(checkType==0)
    clearAllMoveIndicators();
  if(tile != null){
    if(tile.getPiece() != null){
      switch(tile.getPiece().type){
        case 1:
          //If selected piece is a Pawn
          if(tile.getPiece().team){
            setPawnIndicator(tile,1,checkType);
            setPawnTakeIndicator(tile,-1,1,checkType);
            setPawnTakeIndicator(tile,1,1,checkType);
            
          }else{
            setPawnIndicator(tile,-1,checkType);
            setPawnTakeIndicator(tile,-1,-1,checkType);
            setPawnTakeIndicator(tile,1,-1,checkType);
            
          }
          
        break;
        case 2:
          //If selected piece is a Rook
          setNextIndicator(tile,1,0,tile.row, tile.col+1,-1,checkType);
          setNextIndicator(tile,-1,0,tile.row, tile.col-1,-1,checkType);
          setNextIndicator(tile,0,1,tile.row+1, tile.col,-1,checkType);
          setNextIndicator(tile,0,-1,tile.row-1, tile.col,-1,checkType); 
        
        break;
        case 3:
          //If selected piece is a Knight
          setKnightIndicator(tile,-2,-1,checkType);
          setKnightIndicator(tile,-1,-2,checkType);
          setKnightIndicator(tile,1,-2,checkType);
          setKnightIndicator(tile,2,-1,checkType);
         
          setKnightIndicator(tile,-2,1,checkType);
          setKnightIndicator(tile,-1,2,checkType);
          setKnightIndicator(tile,1,2,checkType);
          setKnightIndicator(tile,2,1,checkType);
        
        break;
        case 4:
          //If selected piece is a Bishop
          setNextIndicator(tile,1,1,tile.row+1, tile.col+1,-1,checkType);
          setNextIndicator(tile,1,-1,tile.row-1, tile.col+1,-1,checkType);
          setNextIndicator(tile,-1,1,tile.row+1, tile.col-1,-1,checkType);
          setNextIndicator(tile,-1,-1,tile.row-1, tile.col-1,-1,checkType);
        
        break;
        case 5:
          //If selected piece is a Queen
          setNextIndicator(tile,1,0,tile.row, tile.col+1,-1,checkType);
          setNextIndicator(tile,-1,0,tile.row, tile.col-1,-1,checkType);
          setNextIndicator(tile,0,1,tile.row+1, tile.col,-1,checkType);
          setNextIndicator(tile,0,-1,tile.row-1, tile.col,-1,checkType);
          
          setNextIndicator(tile,1,1,tile.row+1, tile.col+1,-1,checkType);
          setNextIndicator(tile,1,-1,tile.row-1, tile.col+1,-1,checkType);
          setNextIndicator(tile,-1,1,tile.row+1, tile.col-1,-1,checkType);
          setNextIndicator(tile,-1,-1,tile.row-1, tile.col-1,-1,checkType);
        
        break;
        case 6:
          //If selected piece is a King
          if(checkType==0){
            if(castlingKingsidePossible(tile)){
              if(checkType == 1)
                gameBoard.tiles[tile.col+2][tile.row].setCheckIndicator(true);
              else{
                if(checkType == 0)
                  gameBoard.tiles[tile.col+2][tile.row].setIsValidIndicator(true);
                else
                  gameBoard.tiles[tile.col+2][tile.row].setCheckMateIndicator(true);
              }
              
            }
            if(castlingQueensidePossible(tile)){
              if(checkType == 1)
                gameBoard.tiles[tile.col-2][tile.row].setCheckIndicator(true);
              else{
                if(checkType == 0)
                  gameBoard.tiles[tile.col-2][tile.row].setIsValidIndicator(true);
                else
                  gameBoard.tiles[tile.col-2][tile.row].setCheckMateIndicator(true);
              }
              
            }
          }
          setNextIndicator(tile,1,0,tile.row, tile.col+1,1,checkType);
          setNextIndicator(tile,-1,0,tile.row, tile.col-1,1,checkType);
          setNextIndicator(tile,0,1,tile.row+1, tile.col,1,checkType);
          setNextIndicator(tile,0,-1,tile.row-1, tile.col,1,checkType);
          
          setNextIndicator(tile,1,1,tile.row+1, tile.col+1,1,checkType);
          setNextIndicator(tile,1,-1,tile.row-1, tile.col+1,1,checkType);
          setNextIndicator(tile,-1,1,tile.row+1, tile.col-1,1,checkType);
          setNextIndicator(tile,-1,-1,tile.row-1, tile.col-1,1,checkType);
          
          
        break;
        default:
        break;
        
      }
    }
  }
  
}

boolean castlingKingsidePossible(Tile tile){
  if(tile.getPiece() != null)
    if(tile.getPiece().hasMoved)
    return false;
  if(!gameBoard.tiles[tile.col+1][tile.row].isOccupied()&&
     !gameBoard.tiles[tile.col+2][tile.row].isOccupied()&&
      gameBoard.tiles[tile.col+3][tile.row].isOccupied()){
 
   if(!gameBoard.tiles[tile.col+3][tile.row].getPiece().hasMoved){
     return true;
   }      
 
  }
  return false;
}
boolean castlingQueensidePossible(Tile tile){
  if(tile.getPiece() != null)
    if(tile.getPiece().hasMoved)
    return false;
  if(!gameBoard.tiles[tile.col-1][tile.row].isOccupied()&&
     !gameBoard.tiles[tile.col-2][tile.row].isOccupied()&&
     !gameBoard.tiles[tile.col-3][tile.row].isOccupied()&&
      gameBoard.tiles[tile.col-4][tile.row].isOccupied()){
 
   if(!gameBoard.tiles[tile.col-4][tile.row].getPiece().hasMoved){
     return true;
   }      
 
  }
  return false;
}

//Sets knight indicators
void setKnightIndicator(Tile tile,int x, int y,int checkType){
  if(isOnBoard(tile.col+x,tile.row+y)){
    if(gameBoard.tiles[tile.col+x][tile.row+y].isOccupied()){
      if(gameBoard.tiles[tile.col+x][tile.row+y].getPiece().team != tile.getPiece().team)
        if(checkType == 1)
            gameBoard.tiles[tile.col+x][tile.row+y].setCheckIndicator(true);
          else{
            if(checkType == 0)
              gameBoard.tiles[tile.col+x][tile.row+y].setIsValidIndicator(true);
            else
              gameBoard.tiles[tile.col+x][tile.row+y].setCheckMateIndicator(true);
          }
          
    }else{
      if(checkType == 1)
        gameBoard.tiles[tile.col+x][tile.row+y].setCheckIndicator(true);
      else{
        if(checkType == 0)
          gameBoard.tiles[tile.col+x][tile.row+y].setIsValidIndicator(true);
        else
          gameBoard.tiles[tile.col+x][tile.row+y].setCheckMateIndicator(true);
      }
          
    }
   }
}

//Sets pawn forward move indicators (not diagonal takes)
void setPawnIndicator(Tile tile,int y, int checkType){
  
  int singleMove = 1*y;
  int doubleMove = 2*y;
  if(isOnBoard(1,tile.row+singleMove)){
    if(tile.getPiece().hasMoved){
      if(!gameBoard.tiles[tile.col][tile.row+singleMove].isOccupied())
        setNextIndicator(tile,0,1,tile.row+singleMove, tile.col,1,checkType);
    }else{
      if(!gameBoard.tiles[tile.col][tile.row+doubleMove].isOccupied()&&
         !gameBoard.tiles[tile.col][tile.row+singleMove].isOccupied()){
        setNextIndicator(tile,0,singleMove,tile.row+singleMove, tile.col,2,checkType);
      }else{
        if(!gameBoard.tiles[tile.col][tile.row+singleMove].isOccupied()){
          println("here");
          setNextIndicator(tile,0,singleMove,tile.row+singleMove, tile.col,1,checkType);
        }
      }
    }
  }
}

//Sets pawn take indicators (Diagonals)
void setPawnTakeIndicator(Tile tile,int x, int y, int checkType){
  if(isOnBoard(tile.col+x,tile.row+y)){
    if(gameBoard.tiles[tile.col+x][tile.row+y].isOccupied()){
      if(gameBoard.tiles[tile.col+x][tile.row+y].getPiece().team != tile.getPiece().team)
        if(checkType == 1)
          gameBoard.tiles[tile.col+x][tile.row+y].setCheckIndicator(true);
        else{
          if(checkType == 0)
            gameBoard.tiles[tile.col+x][tile.row+y].setIsValidIndicator(true);
          else
            gameBoard.tiles[tile.col+x][tile.row+y].setCheckMateIndicator(true);
        }
          
          
    }
  }
}

//Recursively sets move indicators in the direction specified by xDir and yDir. The parameter
//"recursionLevel" stipulates how deep the recursion goes, -1 is until the end of the board.
int setNextIndicator(Tile tile,int xDir,int yDir,int row, int col, int recursionLevel, int checkType){
  int found = 0;
  if(recursionLevel == 0)
    return 0;
  recursionLevel--;
  if((row>=rows||row<0)||(col>=colomns||col<0))
            return 0;
  if(gameBoard.tiles[col][row].isOccupied()){
    if(gameBoard.tiles[col][row].getPiece().team == tile.getPiece().team){ 
      return 0;
    }else{
      if(checkType == 1)
        gameBoard.tiles[col][row].setCheckIndicator(true);
      else{
        if(checkType == 0)
          gameBoard.tiles[col][row].setIsValidIndicator(true);
        else
          gameBoard.tiles[col][row].setCheckMateIndicator(true);
      }
      return 1;
    }
  }else{
    if((xDir == 1)&&(yDir == 0)){
      if(checkType == 1)
        gameBoard.tiles[col][row].setCheckIndicator(true);
      else{
        if(checkType == 0)
          gameBoard.tiles[col][row].setIsValidIndicator(true);
        else
          gameBoard.tiles[col][row].setCheckMateIndicator(true);
      }
      return found + setNextIndicator(tile,xDir,yDir,row,col+1,recursionLevel,checkType);
    }
    if((xDir == -1)&&(yDir == 0)){
      if(checkType == 1)
        gameBoard.tiles[col][row].setCheckIndicator(true);
      else{
        if(checkType == 0)
          gameBoard.tiles[col][row].setIsValidIndicator(true);
        else
          gameBoard.tiles[col][row].setCheckMateIndicator(true);
      }
      return found + setNextIndicator(tile,xDir,yDir,row,col-1,recursionLevel,checkType);
    }
    if((xDir == 0)&&(yDir == 1)){
      if(checkType == 1)
        gameBoard.tiles[col][row].setCheckIndicator(true);
      else{
        if(checkType == 0)
          gameBoard.tiles[col][row].setIsValidIndicator(true);
        else
          gameBoard.tiles[col][row].setCheckMateIndicator(true);
      }
      return found + setNextIndicator(tile,xDir,yDir,row+1,col,recursionLevel,checkType);
    }
    if((xDir == 0)&&(yDir == -1)){
      if(checkType == 1)
        gameBoard.tiles[col][row].setCheckIndicator(true);
      else{
        if(checkType == 0)
          gameBoard.tiles[col][row].setIsValidIndicator(true);
        else
          gameBoard.tiles[col][row].setCheckMateIndicator(true);
      }
      return found + setNextIndicator(tile,xDir,yDir,row-1,col,recursionLevel,checkType);
    }
    if((xDir == 1)&&(yDir == 1)){
      if(checkType == 1)
        gameBoard.tiles[col][row].setCheckIndicator(true);
      else{
        if(checkType == 0)
          gameBoard.tiles[col][row].setIsValidIndicator(true);
        else
          gameBoard.tiles[col][row].setCheckMateIndicator(true);
      }
      return found + setNextIndicator(tile,xDir,yDir,row+1,col+1,recursionLevel,checkType);
    }
    if((xDir == 1)&&(yDir == -1)){
      if(checkType == 1)
        gameBoard.tiles[col][row].setCheckIndicator(true);
      else{
        if(checkType == 0)
          gameBoard.tiles[col][row].setIsValidIndicator(true);
        else
          gameBoard.tiles[col][row].setCheckMateIndicator(true);
      }
      return found + setNextIndicator(tile,xDir,yDir,row-1,col+1,recursionLevel,checkType);
    }
    if((xDir == -1)&&(yDir == 1)){
      if(checkType == 1)
        gameBoard.tiles[col][row].setCheckIndicator(true);
      else{
        if(checkType == 0)
          gameBoard.tiles[col][row].setIsValidIndicator(true);
        else
          gameBoard.tiles[col][row].setCheckMateIndicator(true);
      }
      return found + setNextIndicator(tile,xDir,yDir,row+1,col-1,recursionLevel,checkType);
    }
    if((xDir == -1)&&(yDir == -1)){
      if(checkType == 1)
        gameBoard.tiles[col][row].setCheckIndicator(true);
      else{
        if(checkType == 0)
          gameBoard.tiles[col][row].setIsValidIndicator(true);
        else
          gameBoard.tiles[col][row].setCheckMateIndicator(true);
      }
      return found + setNextIndicator(tile,xDir,yDir,row-1,col-1,recursionLevel,checkType);
    }
    
    return 0;
    
  }
}


boolean checkForCheckMate(){
        
  clearAllCheckMateIndicators();
  if(check){
    //Set check indicators for all friendly pieces
    for(int i = 0; i < rows; i++){
      for(int j = 0; j < rows; j++){
        if(gameBoard.tiles[i][j].getPiece()!=null){
	  if(gameBoard.tiles[i][j].getPiece().team == player){
            println("setting check indicators for col:"+i+" Row:"+j);
	    setMoveIndicators(gameBoard.tiles[i][j],2);
				
	    //Now that the check indicators are set see if any of those 
	    //potential moves remove check status
	    for(int k = 0; k < rows; k++){
	      for(int l = 0; l < rows; l++){
	        //This is a potential move, test to see if it results in check
	        // being removed
	        if(gameBoard.tiles[k][l].checkMateIndicator == true){
		  //If moving to this tile removes check status we can't be in checkmate
	          //so return false;
		  if(moveRemovesCheckStatus(k,l,i,j)){
                    println("move "+i+" : "+j);
                    println("to "+k+" : "+l);
		    return false;
		  }
	        }
	      }
	    }
				
	    clearAllCheckMateIndicators();
	  }
         }
        }
    }
    return true;
  }else{
    return false;
  }
	
}


Board copyBoard(){
  Board result = new Board(boardSize,maroon,pink);
  result.initialiseBoard();
  for(int i = 0; i < rows; i++){
      for(int j = 0; j < rows; j++){
        result.tiles[i][j] = gameBoard.tiles[i][j].generateCopy();
      }
    }
    return result;
}

boolean isOnBoard(int x, int y){
  if((x >= 0)&&(x < colomns)){
    if((y >= 0)&&(y < rows)){
      return true;
    }
  }
  return false;
}

boolean isCheck(Board board){
  
    board.clearAllCheckIndicators();
    for(int i = 0; i < rows; i++){
      for(int j = 0; j < rows; j++){
        if(board.tiles[i][j].getPiece()!=null){
          if(board.tiles[i][j].getPiece().team != player){
            setMoveIndicators(board.tiles[i][j],1);
          }
        }
      }
    }
    for(int i = 0; i < rows; i++){
      for(int j = 0; j < rows; j++){
        //If the piece is the king and its the current players king
        if(board.tiles[i][j].getPiece()!=null){
          if((board.tiles[i][j].getPiece().type == 6)&&(board.tiles[i][j].getPiece().team == player)){
            if(board.tiles[i][j].checkIndicator == true)
              return true;
          }
        }
      }
    }
    return false;
    
}
