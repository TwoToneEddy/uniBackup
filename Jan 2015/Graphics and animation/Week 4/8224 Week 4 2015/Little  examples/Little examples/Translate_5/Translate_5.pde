color White = color(255, 255, 255),
      Red = color(255, 0, 0),
      Green = color(0, 255, 0);

int W = 400,
    H = W;
int S = 20;
int x1Move = 1,
    y1Move = 2;
int x2Move = 3,
    y2Move = -2;
    
void setup() {
  size(W, H);
  rect(0, 0, S, S);
}  

void draw() {
  background(White);
  pushMatrix();
    fill(Red);
    translate(x1Move, y1Move);
    rect(0, 0, S, S);
    x1Move++;
    y1Move++;
  popMatrix();
  fill(Green);
  rect(W/2 + x2Move, H/2 - y2Move, S, S); 
  x2Move++;
  y2Move++;  
}
  
  
  
  
