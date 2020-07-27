int W = 500,
    H = W;
int GSide = 20;
Grid OG = new Grid(GSide);
Grid BG = new Grid(GSide);
float Unit = PI/1000;
void setup() {  
  size(W, H);
  stroke(Red);
  OG.LineCol = Orange;
  BG.LineCol = Black;  
}

int i = 0; 

void draw() {
  background(White);
  OG.Draw();
  fill(Black);
  stroke(Black);
  ellipse(300, 200, 10, 10);
  
  translate(300, 200);  
  rotate(i * Unit);
  BG.Draw();
  fill(Red);
  stroke(Red);
  ellipse(60, 60, 25, 25);
  
  i++;
}


