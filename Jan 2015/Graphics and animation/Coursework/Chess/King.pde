class King extends Piece{
  Ellipse peakA,peakB,crownBaseA;
  Block crownBaseB,base,crossVert,crossHoriz;
  float peakCenteringTrans;
  
  King(boolean team, float cX, float cY, float side,int velocity){
    super(team,cX,cY,side,velocity);
    peakA = new Ellipse(side/5,side/2.5);
    peakB = new Ellipse(side/5,side/2.5);
    crossVert = new Block(side/8,side/1.5);
    crossHoriz = new Block(side/2.5,side/8);
    crownBaseA = new Ellipse(side/8,side/2.5);
    crownBaseB = new Block(side/1.25,side/2);
    base = new Block(side/1,side/3);
    peakCenteringTrans = side/8;//(side/2.5)/2;
    type = KING;
    
    
  }
  
  Piece generateCopy(){
    Piece result = new King(team,cX,cY,side,velocity);
    return result;
  }
  
  void refresh(){
    super.refreshPosition();
    pushMatrix();
      translate(0,-(side/12));
      pushMatrix();
        translate(cX,cY);
        
        pushMatrix();
          translate(side/8,0);
          pushMatrix();
            rotate(radians(30));
            translate(0,-(peakCenteringTrans));
            peakA.Draw(pieceColor,strokeColor);
          popMatrix();
        popMatrix();
        
        pushMatrix();
          translate(-(side/8),0);
          pushMatrix();
            rotate(radians(-30));
            translate(0,-(peakCenteringTrans));
            peakB.Draw(pieceColor,strokeColor);
          popMatrix();
        popMatrix();
        
        pushMatrix();
          translate(0,-(peakCenteringTrans*1.5));
          crossHoriz.Draw(pieceColor,strokeColor);
        popMatrix();
        
        pushMatrix();
          translate(0,-(peakCenteringTrans));
          crossVert.Draw(pieceColor,strokeColor);
        popMatrix();
        
        
        
        pushMatrix();
          translate(0,side/8);
          crownBaseB.Draw(pieceColor,strokeColor);
        popMatrix();
        
        pushMatrix();
          rotate(radians(90));
          crownBaseA.Draw(pieceColor,strokeColor);
        popMatrix();
        
        pushMatrix();
          translate(0,side/3);
          base.Draw(pieceColor,strokeColor);
        popMatrix();
        
      popMatrix();
    popMatrix();
  }
  
}
