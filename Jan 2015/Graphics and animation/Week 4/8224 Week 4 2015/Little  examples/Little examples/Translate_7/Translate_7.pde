int W = 400,
    H = W;
int S = 20;
int xMove = 20,
    yMove = 20;
void setup() {
  size(W, H);
  //rect(0, 0, S, S);
}  

void draw() {
  fill(White);
  pushMatrix();
    translate(xMove, yMove);
    rect(0, 0, S, S);
    translate(xMove, yMove);
    rect(0, 0, S, S);
    fill(Red);
    rect(0, 0, S, S);
  popMatrix();
}
  
  
  
  
