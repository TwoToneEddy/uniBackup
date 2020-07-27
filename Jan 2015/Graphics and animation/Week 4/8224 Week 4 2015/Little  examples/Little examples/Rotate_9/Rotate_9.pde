int W = 300,
    H = W;

void setup() {
  size(W, H);
  noStroke();
}

int i = 0;
int x = 40,
    y = 40;
void draw() {
  background(Yellow);
  fill(Red);
  //pushMatrix();
    translate(150, 150);
    rotate(i * PI/400);     
    ellipse(x, y, 25, 25);
    translate(x, y);
    ellipse(30, 30, 15, 15);
    translate(30, 30);
    rotate(i * PI/400);
    ellipse(15, 15, 8, 8);
    i++; 
  //popMatrix();
 }
  



