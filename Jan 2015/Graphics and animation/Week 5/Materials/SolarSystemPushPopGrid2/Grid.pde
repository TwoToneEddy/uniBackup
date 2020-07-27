class Grid {
  int Side;
  color LineCol;
  
  Grid(int Side) {
    this.Side = Side;
  }
  
  void Show() {
    int i;
    stroke(LineCol);
    for(i = 0; i <= width; i+=Side) {
      line(i, 0, i, height);
      line(0, i, width, i);
    }
  }
}
