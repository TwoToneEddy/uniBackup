class UpDownControl {
  int ID;
  Square Up,
         Down;
  String Legend = null;        
  color ArrowCol = Black;
  color LCol = White;
  int Cx, 
      Cy;
  int HS;
  
  UpDownControl(int Cx, int Cy, int HS, color Col) {
    this.Cx = Cx;
    this.Cy = Cy;
    this.HS = HS;
    Up = new Square(Cx, Cy - HS, HS);
    Down = new Square(Cx, Cy + HS, HS);
    Up.Col = Col;
    Down.Col = Col;
  }    
  
  void Draw() {
    Up.Draw();
    Down.Draw();
    fill(ArrowCol);
    triangle(Cx - HS, Cy, Cx + HS, Cy, Cx, Cy - 2 * HS);
    triangle(Cx - HS, Cy, Cx + HS, Cy, Cx, Cy + 2 * HS);
    if(Legend != null) {
      fill(LCol);
      textAlign(CENTER, CENTER);
      text(Legend, Cx, Cy);
    }
  }
  
  boolean Q_Up() {
    return(Up.Q_MouseIn());
  }
  
  boolean Q_Down() {
    return(Down.Q_MouseIn());
  }
  
  boolean Q_MouseIn() {
    return(Up.Q_MouseIn() || Down.Q_MouseIn());
  }
}
