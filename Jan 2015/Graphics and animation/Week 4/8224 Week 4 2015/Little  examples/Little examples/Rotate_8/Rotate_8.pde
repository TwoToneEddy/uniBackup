int W = 300,
    H = W;

void setup() {
  size(W, H);
  noStroke();
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
  fill(Green);
  pushMatrix();
    translate(50, 100);
    rotate(3 * i * PI/400);
    i++;  
    ellipse(30, 30, 15, 15);
  popMatrix();
}
  



