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
  fill(Black);
  stroke(Black);
  ellipse(300, 200, 10, 10);
  
  translate(300, 200);  
  rotate(i * Unit);
  fill(Red);
  stroke(Red);
  ellipse(60, 60, 25, 25);
  
  translate(60, 60);
  rotate(5 * i * Unit);
  fill(Blue);
  stroke(Blue);
  ellipse(30, 30, 15, 15); 
  
  translate(30, 30);
  rotate(10 * i * Unit);
  stroke(Green);
  fill(Green);
  ellipse(15, 15, 8, 8); 
  i++;
}


