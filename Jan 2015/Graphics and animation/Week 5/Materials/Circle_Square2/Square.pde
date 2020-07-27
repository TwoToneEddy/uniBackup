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
  
  void Show() {
    rectMode(CENTER);
    translate(Cx, Cy);
    fill(Col);
    rect(0, 0, HS * 2, HS * 2);
  }
  
  void Move() {
    Cx += dx;
    Cy += dy;
  }
}
