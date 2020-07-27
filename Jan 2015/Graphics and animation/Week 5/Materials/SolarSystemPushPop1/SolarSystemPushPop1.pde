int W = 600,
    H = W;
int GSide = 20;
float Unit = PI/1000;
color C1 = RandCol(),
      C2 = RandCol(),
      C3 = RandCol();
void setup() {  
  size(W, H);
  stroke(Red);
}

int i = 0; 

void draw() {
  background(White);
  
  pushMatrix();
    fill(Black);
    stroke(Black);
    ellipse(400, 200, 10, 10);
    
    translate(400, 200);  
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
  popMatrix();
  pushMatrix();
    fill(Orange);
    stroke(Orange);
    ellipse(200, 400, 20, 20);

    translate(200, 400);  
    rotate(i * Unit);
    fill(C1);
    stroke(C1);
    ellipse(80, 80, 25, 25);
  
    translate(80, 80);
    rotate(5 * i * Unit);
    fill(C2);
    stroke(C2);
    ellipse(40, 40, 15, 15); 
  
    translate(40, 40);
    rotate(10 * i * Unit);
    stroke(C3);
    fill(C3);
    ellipse(15, 15, 8, 8); 
  popMatrix();
  i++;
}

void mousePressed() {
  save("SSPP1" + i + ".jpg");
  i++;
}
