class Rook extends Piece{
  Ellipse crownBaseA;
  Block peakA,peakB,peakC,crownBaseB,base;
  float peakCenteringTrans;
  
  Rook(boolean team, float cX, float cY, float side,int velocity){
    super(team,cX,cY,side,velocity);
    peakA = new Block(side/7,side/3);
    peakB = new Block(side/7,side/3);
    peakC = new Block(side/7,side/3);
    crownBaseA = new Ellipse(side/8,side/2.5);
    crownBaseB = new Block(side/1.25,side/2);
    base = new Block(side/1,side/3);
    peakCenteringTrans = side/8;//(side/2.5)/2;
    type = ROOK;
    
    
  }
  
  Piece generateCopy(){
    Piece result = new Rook(team,cX,cY,side,velocity);
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
            translate(0,-(peakCenteringTrans));
            peakA.Draw(pieceColor,strokeColor);
          popMatrix();
        popMatrix();
        
        pushMatrix();
          translate(-(side/8),0);
          pushMatrix();
            translate(0,-(peakCenteringTrans));
            peakB.Draw(pieceColor,strokeColor);
          popMatrix();
        popMatrix();
        
        pushMatrix();
          translate(0,-(peakCenteringTrans));
          peakC.Draw(pieceColor,strokeColor);
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
