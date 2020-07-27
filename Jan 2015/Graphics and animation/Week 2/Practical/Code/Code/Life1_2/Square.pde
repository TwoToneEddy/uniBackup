 class Square {
  int xC,
      yC;
  int Side;
  color FillCol,
        EdgeCol;

  public Square(int xC, int yC, int Side, color FillCol, color EdgeCol) {
    this.xC = xC;
    this.yC = yC;
    this.Side = Side;
    this.FillCol = FillCol;
    this.EdgeCol = EdgeCol;
  }
  
  public void Draw() {
    fill(FillCol);
    stroke(EdgeCol);
    rectMode(CENTER);
    rect(xC, yC, Side,Side);
  }
  
  public boolean Q_Contains(int x, int y) {
    return((x >= xC - Side/2) && (xC + Side/2 > x) &&
           (y >= yC - Side/2) && (yC + Side/2 > y));
  }  
}
