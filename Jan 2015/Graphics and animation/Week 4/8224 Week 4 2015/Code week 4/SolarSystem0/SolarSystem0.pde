int W = 500,
    H = W;
int GSide = 20;
float Unit = PI/1000;
void setup() {  
  size(W, H);
  stroke(Red); 
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
  i++;
}


