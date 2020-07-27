void setup() {
  size(200, 200);
  translate(100, 100);
  rotate(PI/4);
  rect(0, 0, 55, 55);
}
int i = 0;

void draw() {
  pushMatrix();
  translate(100, 100);
  rotate(i * PI/400);
  i++;  
  rect(0, 0, 55, 55);
  popMatrix();
}
  



