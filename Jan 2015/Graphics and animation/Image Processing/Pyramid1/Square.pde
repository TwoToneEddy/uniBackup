class Square {
  float Cx,
      Cy;
  float HS;
  color Col;
  color LCol;
  float dx,
      dy;
  String Legend = null;      
  
  Square(float Cx, float Cy, float HS) {
    this.Cx = Cx;
    this.Cy = Cy;
    this.HS = HS;
  }
  
  void Draw() {
    fill(Col);
    rectMode(CENTER);
    rect(Cx, Cy, 2 * HS, 2 * HS);
    if(Legend != null) {
      fill(LCol);
      textAlign(CENTER, CENTER);
      //text(Legend, Cx, Cy);
    }
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
