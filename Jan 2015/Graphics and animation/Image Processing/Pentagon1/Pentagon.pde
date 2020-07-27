class Pentagon {
  float Cx,
        Cy;
  float Side;
  color Col;
  float V;
  
  
  Pentagon(float Side, color Col) {
    this.Side = Side;
    this.Col = Col;
    V = Side/(2 * Side/cos(0.3));
  }
  
  void Draw() {println("DRAW");
    stroke(Col);
    translate(width/2, height/2);
    beginShape(LINES);      
      vertex(- Side/2, V);
      rotate(2*PI/5);
      vertex(- Side/2, V);
      rotate(2*PI/5);
      vertex(- Side/2, V);
      rotate(2*PI/5);
      vertex(- Side/2, V);
      rotate(2*PI/5);
      vertex(- Side/2, V);
      rotate(2*PI/5); 
    endShape();
  }
}
