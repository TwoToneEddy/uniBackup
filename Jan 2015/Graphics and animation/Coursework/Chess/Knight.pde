class Knight extends Piece{
  Ellipse ears,body,legs;
  Block base;
  
  Knight(boolean team, float cX, float cY, float side,int velocity){
    super(team,cX,cY,side,velocity);
    ears = new Ellipse(side/10,side/5);
    body = new Ellipse(side/2,side/5);
    legs = new Ellipse(side/5,side/2);
    base = new Block(side/1,side/3);
    type = KNIGHT;
    
    
  }
  
  Piece generateCopy(){
    Piece result = new Knight(team,cX,cY,side,velocity);
    return result;
  }
  
  void refresh(){
    super.refreshPosition();
    pushMatrix();
      translate(0,-(side/12));//Fudge factor to center the piece
      pushMatrix();
        translate(cX,cY);
        
        pushMatrix();
          translate(0,-side/6.5);
          rotate(radians(20));
          ears.Draw(pieceColor,strokeColor);
        popMatrix();
        pushMatrix();
          translate(-side/7,0);
          rotate(radians(-30));
          body.Draw(pieceColor,strokeColor);
        popMatrix();
        pushMatrix();
          translate(0,(side/4));
          legs.Draw(pieceColor,strokeColor);
          pushMatrix();
            translate(0,(side/6.5));
            base.Draw(pieceColor,strokeColor);
          popMatrix();
        popMatrix();
      popMatrix();
    popMatrix();
  }
  
}
