class Bishop extends Piece{
  Ellipse head,body,legs;
  Block base,crossVert,crossHoriz;
  
  Bishop(boolean team, float cX, float cY, float side,int velocity){
    super(team,cX,cY,side,velocity);
    head = new Ellipse(side/6,side/5);
    body = new Ellipse(side/2.5,side/3.5);
    legs = new Ellipse(side/3.5,side/2.5);
    base = new Block(side/1,side/3);
    crossVert = new Block(side/8,side/2);
    crossHoriz = new Block(side/2.5,side/8);
    type = BISHOP;
    
    
  }
  
  Piece generateCopy(){
    Piece result = new Bishop(team,cX,cY,side,velocity);
    return result;
  }
  
  void refresh(){
    super.refreshPosition();
    pushMatrix();
      translate(0,-(side/12));//Fudge factor to center the piece
      pushMatrix();
        translate(cX,cY);
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
        body.Draw(pieceColor,strokeColor);
        crossHoriz.Draw(strokeColor,pieceColor);
        pushMatrix();
          translate(0,side/30);
          crossVert.Draw(strokeColor,pieceColor);
        popMatrix();
      popMatrix();
    popMatrix();
  }
  
}
