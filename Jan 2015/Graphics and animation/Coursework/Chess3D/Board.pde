class Board {
  int side,tileW,tileH;
  color colorA,colorB;
  Tile[][] tiles;
  final static int rows = 8;
  
  Board(int side,color colorA, color colorB){
    this.side=side;
    this.colorA=colorA;
    this.colorB=colorB;
    
  }
  
  void initialiseBoard(){
    tiles = new Tile[rows][rows];
    
    tileW = 40;
    tileH = 40;
    
    boolean colourA = false;
    for(int i = 0; i < rows; i++){
      if(colourA)
        colourA = false;
      else
        colourA = true;
      for(int j = 0; j < rows; j++){
        if((j==0)&&(i==0))
          tiles[i][j] = new Tile(tileW,i,j,color(255,0,0));
        else{
          if(isEven(j,colourA))
            tiles[i][j] = new Tile(tileW,i,j,colorA);
          else
            tiles[i][j] = new Tile(tileW,i,j,colorB);
        }
      }
    }
  }
  
  void draw() {
    translate(100, 250);
    rotateY(radians(45));
    rotateX(radians(-35));
    rotateZ(radians(-25));
    for(int i = 0; i < rows; i++){
      for(int j = 0; j < rows; j++){
        tiles[i][j].draw();
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
  
}
