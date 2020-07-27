/*
Author: Lee Hudson 09092543
Date: 31/03/2015
Description: Board class. This class is responsible for controlling access and interaction with 
the pieces. It contains a 2d array of tiles
*/
class Board {
  float side,tileW,tileH;
  color colorA,colorB;
  Tile[][] tiles;
  final static int rows = 8;
  
  Board(float side,color colorA, color colorB){
    this.side=side;
    this.colorA=colorA;
    this.colorB=colorB;
    
  }
  
  void initialiseBoard(){
    tiles = new Tile[rows][rows];
    
    tileW = side/rows;
    tileH = side/rows;
    
    boolean colourA = false;
    for(int i = 0; i < rows; i++){
      if(colourA)
        colourA = false;
      else
        colourA = true;
      for(int j = 0; j < rows; j++){
        if(isEven(j,colourA))
          tiles[i][j] = new Tile(i*tileW,j*tileH,tileW,tileH,colorA,j,i);
        else
          tiles[i][j] = new Tile(i*tileW,j*tileH,tileW,tileH,colorB,j,i);
      }
    }
  }
  
  void refresh() {
    for(int i = 0; i < rows; i++){
      for(int j = 0; j < rows; j++){
        tiles[i][j].refresh();
      } 
    }
  }

  boolean isEven(int i,boolean invert){
    if((i%2)==0){
      if(invert)
        return false;
      else
        return true;
    }else{
      if(invert)
        return true;
      else
        return false;
    }
  }
  
  Tile tileClicked(int x, int y){
    for(int i = 0; i < rows; i++){
      for(int j = 0; j < rows; j++){
        if(tiles[i][j].contains(x,y)){
          return tiles[i][j];
        }
      } 
    }
    return null;
  }
  
  boolean boardClicked(int x, int y){
    if((x>=0)&&(x<=(tileW*rows))){
      if((y>=0)&&(y<=(tileH*rows))){
        return true;
      }
    }
    return false;
  }
  
  void clearAllMoveIndicators(){
    for(int i = 0; i < rows; i++){
      for(int j = 0; j < rows; j++){
        tiles[i][j].setIsValidIndicator(false);
      }
    }
  }
  
  void clearAllCheckIndicators(){
    for(int i = 0; i < rows; i++){
      for(int j = 0; j < rows; j++){
        tiles[i][j].setCheckIndicator(false);
      }
    }
  }
  void clearAllCheckMateIndicators(){
    for(int i = 0; i < rows; i++){
      for(int j = 0; j < rows; j++){
        tiles[i][j].setCheckMateIndicator(false);
      }
    }
  }
  boolean pieceMoving(){
    for(int i = 0; i < rows; i++){
      for(int j = 0; j < rows; j++){
        if(tiles[i][j].isOccupied())
          if(tiles[i][j].getPiece().isMoving())
            return true;
      }
    }
    return false;
  }
}
