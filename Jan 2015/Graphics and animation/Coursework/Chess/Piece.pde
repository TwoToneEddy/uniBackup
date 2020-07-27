/*
Author: Lee Hudson 09092543
Date: 31/03/2015
Description: Piece super class. This class defines a basic piece. It is extended my specialised pieces.
The reason for using inheritance here is because there are many variables and functions all pieces will
have in common. This serves to encapsulate the commmon code and reduce duplication.
*/
class Piece{
  final int PAWN = 1;
  final int ROOK = 2;
  final int KNIGHT = 3;
  final int BISHOP = 4;
  final int QUEEN = 5;
  final int KING = 6;
  boolean team,selected,taken,hasMoved;
  float cX,cY,side;
  float destX,destY;
  int velocity,type;
  color pieceColor;
  color strokeColor;
  color black = color(0,0,0);
  color white = color(255,255,255);
  color gold = color(242,214,29);
  
  Piece(boolean team, float cX, float cY, float side, int velocity){
    this.team = team;
    this.cX = cX;
    this.cY = cY;
    this.side = side;
    this.velocity = velocity;
    destX = cX;
    destY = cY;
    if(team){
      pieceColor = black;
      strokeColor = white;
    }else{
      pieceColor = white;
      strokeColor = black;
    }
    selected = false;
    taken = false;
    hasMoved = false;
  }
  
  Piece generateCopy(){
    return null;
  }
  
  void move(float destX,float destY){
    if(!hasMoved)
      hasMoved = true;
    this.destX = destX;
    this.destY = destY;
    
  }
  
  void taken(){
    cX = -999;
    cY = -999;
    taken = true;
  }
  
  
  void refreshPosition(){
    
    /*If current position is not equal to destination position
    the current position needs to be incremented or decremented 
    until they are equal*/
    if(taken == false){
      //if(!inPosition(cX,destX)){
      if(cX!=destX){
        if(cX > destX){
          cX=cX-velocity;
        }
        if(cX < destX){
          cX=cX+velocity;
        }
      }
      
      //if(!inPosition(cY,destY)){
      if(cY!=destY){
        if(cY > destY){
          cY=cY-velocity;
        }
        if(cY < destY){
          cY=cY+velocity;
        }
      }
    }
    
  }
  
  void refresh(){
  }
  
  boolean inPosition(float current, float demand){
    if((current <= (demand + velocity))&&(current >=(demand - velocity))){
      return true;
    }else{
      return false;
    }
  }
  
  boolean isMoving(){
    if((!inPosition(cY,destY))||(!inPosition(cX,destX))){
      return true;
    }else{
      return false;
    }
  }
  
  void setSelected(){
    selected = true;
    pieceColor = gold;
    strokeColor = black;
    
  }
  
  void resetSelected(){
    selected = false;
    if(team){
      pieceColor = black;
      strokeColor = white;
    }else{
      pieceColor = white;
      strokeColor = black;
    }
  }
  
}

