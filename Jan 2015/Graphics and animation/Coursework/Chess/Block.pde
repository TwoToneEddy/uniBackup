class Block {
  float blockWidth,blockHeight; 
  color black = color(0,0,0);          
 
  Block(float blockWidth,float blockHeight) {
    this.blockWidth = blockWidth;
    this.blockHeight = blockHeight;
  }
  
  // PRE  TRUE
  // POST Circle drawn on canvas
  void Draw(color fillCol, color strokeCol) {
    stroke(strokeCol);
    fill(fillCol);
    rectMode(CENTER);
    rect(0, 0, blockWidth/2, blockHeight/2);    
  } 
  
}
