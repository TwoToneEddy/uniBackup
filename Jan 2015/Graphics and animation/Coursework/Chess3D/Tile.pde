class Tile {
  int N,row,colomn;
  color col;
  
  
  Tile(int N, int row, int colomn,color col){
    this.N=N;
    this.row=row;
    this.colomn=colomn;
    this.col=col;
    
  }
  
  void draw() {
    fill(col);
    pushMatrix();
    translate(colomn*N,0,row*N);
    box(N,N/2,N);
    popMatrix();
  }
  /*
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
  }*/
  
}
