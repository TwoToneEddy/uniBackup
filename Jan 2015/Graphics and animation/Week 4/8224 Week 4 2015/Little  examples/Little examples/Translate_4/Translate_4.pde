color White = color(255, 255, 255),
      Red = color(255, 0, 0);

int W = 400,
    H = W;
int S = 20;
int xMove = 1,
    yMove = 2;
    
void setup() {
  size(W, H);
  fill(Red);
  rect(0, 0, S, S);
}  

void draw() {
  background(White);
  translate(xMove, yMove);
  rect(0, 0, S, S);
  xMove++;
  yMove++;
}
  
  
  
  
