class Grid {
  int Side;
  color LineCol;
  
  Grid(int Side) {
    this.Side = Side;
  }
  
  void Draw() {
    int i;
    stroke(LineCol);
    for(i = 0; i <= width; i+=Side) {
      line(i, 0, i, height - 2 * PanelH);
      line(0, i, width, i);
    }
  }
}
