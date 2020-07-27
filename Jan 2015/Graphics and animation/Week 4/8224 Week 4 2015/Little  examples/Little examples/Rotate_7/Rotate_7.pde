void setup() {
  size(200, 200);
}

int i = 0;

void draw() {
  background(Yellow);
  fill(Red);
  pushMatrix();
    translate(100, 100);
    rotate(i * PI/400);
    i++;  
    ellipse(40, 40, 55, 55);
  popMatrix();
}
  



