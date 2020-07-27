class RetButton {
  int Cx,
      Cy;
  int HW,
      HH;
  color Col;
  String Legend = null;
  PFont Font;
  color LegCol;
  
  RetButton(int Cx, int Cy, int HW, int HH) {
    this.Cx = Cx;
    this.Cy = Cy;
    this.HW = HW;
    this.HH = HH;
  }
  
  void Draw() {
    rectMode(CENTER);
    fill(Col);
    rect(Cx, Cy, 2 * HW, 2 * HH);
    if(Legend != null) {
      fill(LegCol);
      textAlign(CENTER, CENTER);
      textFont(Font);
      text(Legend, Cx, Cy);
    }
  }
  
  /* If the mouse is in this RectButton
       Returns true
     Else
       Returns false
  */  
  boolean Q_MouseIn() {
    return(Cx - HW < mouseX &&
           mouseX < Cx + HW &&
           Cy - HH < mouseY &&
           mouseY < Cy + HH);
  }
}
  
  
      
