class Pawn extends Piece{
  Ellipse head,body,legs;
  Block base;
  
  Pawn(boolean team, float cX, float cY, float side,int velocity){
    super(team,cX,cY,side,velocity);
    head = new Ellipse(side/5,side/5);
    body = new Ellipse(side/3.5,side/3.5);
    legs = new Ellipse(side/2.5,side/2.5);
    base = new Block(side/1,side/3);
    type = PAWN;
    
    
  }
  
  Piece generateCopy(){
    Piece result = new Pawn(team,cX,cY,side,velocity);
    return result;
  }
  
  void refresh(){
    super.refreshPosition();
    pushMatrix();
      translate(0,-(side/12));//Fudge factor to center the piece
      pushMatrix();
        translate(cX,cY);
        body.Draw(pieceColor,strokeColor);
        pushMatrix();
          translate(0,-(side/4.5));
          head.Draw(pieceColor,strokeColor);
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
