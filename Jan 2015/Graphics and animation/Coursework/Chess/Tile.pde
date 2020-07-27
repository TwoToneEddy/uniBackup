class Tile {
  float x,y,h,w;
  int row,col;
  color fCol;
  Piece occupyingPiece;
  boolean isValidIndicator,checkIndicator,checkMateIndicator;
  color green = color(0,255,0);
  color red = color(255,0,0);
  
  
  Tile(float x,float y, float h, float w, color fCol, int row, int col){
    this.x=x;
    this.y=y;
    this.h=h;
    this.w=w;
    this.fCol=fCol;
    this.row=row;
    this.col=col;
    occupyingPiece = null;
    isValidIndicator=false;
    checkIndicator=false;
    checkMateIndicator=false;
    
  }
  
  Tile generateCopy(){
    Tile copy = new Tile(x,y,h,w,fCol,row,col);
    copy.isValidIndicator = isValidIndicator;
    copy.checkIndicator = checkIndicator;
    if(occupyingPiece!=null)
      copy.setPiece(occupyingPiece.generateCopy());
    else
      copy.setPiece(null);
    return copy;
  }
  
  void refresh() {
    stroke(fCol);
    if(isValidIndicator && isOccupied())
      fill(red);
    else
      fill(fCol);
      rectMode(CORNER);
      rect(x,y,h,w);
    
    if(isValidIndicator && !isOccupied()){
      fill(green);
      ellipse(x+(w/2), y+(h/2), w/4, h/4);
    }
  }
  
  boolean contains(int qX, int qY){
    if((qX>x)&&(qX<(x+w))){
      if((qY>y)&&(qY<(y+h))){
        return true;
      }else{
        return false;
      }
    }else{
      return false;
    }
  }
  
  void setPiece(Piece piece){
    occupyingPiece = piece;
  }
  
  Piece getPiece(){
    return occupyingPiece;
  }
  
  void setIsValidIndicator(boolean valid){
    isValidIndicator = valid;
  }
  
  void setCheckIndicator(boolean check){
    checkIndicator = check;
  }
  void setCheckMateIndicator(boolean checkMate){
    checkMateIndicator = checkMate;
  }
  boolean isOccupied(){
    if(occupyingPiece != null)
      return true;
    else
      return false;
  }
  
  boolean isEmpty(){
    if(occupyingPiece==null)
      return true;
    else
      return false;
  }
}
