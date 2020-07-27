class Square {
  int Cx,
      Cy;
  int HS;
  color Col;
  int dx,
      dy;
  
  Square(int Cx, int Cy, int HS) {
    this.Cx = Cx;
    this.Cy = Cy;
    this.HS = HS;
  }
  
  void Draw() {
    rectMode(CENTER);
    fill(Col);
    rect(Cx, Cy, HS * 2, HS * 2);
  }
  
  void Move() {
    Cx += dx;
    Cy += dy;
  }
  
  boolean Q_MouseIn() {
    return(Cx - HS < mouseX && mouseX < Cx + HS &&
           Cy - HS < mouseY && mouseY < Cy + HS);
  }
}
