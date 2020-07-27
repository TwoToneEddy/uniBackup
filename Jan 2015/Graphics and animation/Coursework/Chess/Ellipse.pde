class Ellipse {
  float w,h;    
  color black = color(0,0,0);       
 
  Ellipse(float w, float h) {
    
    this.w = w;
    this.h = h;
  }
  
  // PRE  TRUE
  // POST Circle drawn on canvas
  void Draw(color fillCol, color strokeCol) {
    stroke(strokeCol);
    fill(fillCol);
    ellipse(0, 0, w, h);    
  } 
  
}

