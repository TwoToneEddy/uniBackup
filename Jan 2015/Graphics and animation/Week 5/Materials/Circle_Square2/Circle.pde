class Circle {
  int Cx,
      Cy;
  int dx,
      dy;  
  int D;
  color FillCol;            
 
  Circle(int Cx, int Cy, int D, color FillCol) {
    
    this.Cx = Cx;
    this.Cy = Cy;
    this.D = D;
    this.FillCol = FillCol;
  }
  
  // PRE  TRUE
  // POST Circle drawn on canvas
  void Show() {
    fill(FillCol);
    translate(Cx, Cy);
    ellipse(0, 0, D, D); 
    translate(-Cx, -Cy);   
  } 
  
  void Move() {
    Cx += dx;
    Cy += dy;
  }
}
