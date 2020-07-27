int W = 600,
    Pane1H = 40,
    HS = Pane1H/2,
    H = W + Pane1H;
int BN = 6;    
int GSide = 20;
Grid OG = new Grid(GSide);
Grid BG = new Grid(GSide);
float Unit = PI/1000;
color Yellow = color(255, 255, 0);
color Red = color(255, 0, 0);
color Green = color(0, 255, 0);
color Black = color(0, 0, 0);
color White = color(255, 255, 255);
color Blue = color(0, 0, 255);
color Orange = color(255, 127, 0);

color C1 = RandCol(),
      C2 = RandCol(),
      C3 = RandCol(),
      C4 = RandCol();
color[] Cols = {Black, Red, Blue, C1, C2, C3, C4};     
Square[] Buttons = new Square[BN]; 
boolean[] Q_DrawB = new boolean[BN];

void setup() {  
  int i;
  
     
  size(W, H);
  stroke(Red);
  OG.LineCol = Orange;
  BG.LineCol = Black;
  for(i = 0; i < BN; i++) {
    Buttons[i] = new Square(HS + 2 * i * HS, H - HS, HS); 
    Buttons[i].Col = Cols[i];
    Q_DrawB[i] = false;
  }  
}

int i = 0; 

void draw() {
  background(White);  
  OG.Draw();
  pushMatrix();
    fill(Black);
    stroke(Black);
    ellipse(400, 200, 10, 10);
    
    translate(400, 200);  
    rotate(i * Unit);
    if(Q_DrawB[0]) {
      BG.Draw();
    }
    fill(Red);
    stroke(Red);
    ellipse(60, 60, 25, 25);
  
    translate(60, 60);
    rotate(5 * i * Unit);
    if(Q_DrawB[1]) {
      BG.Draw();
    }
    fill(Blue);
    stroke(Blue);
    ellipse(30, 30, 15, 15); 
  
    translate(30, 30);
    rotate(10 * i * Unit);
    if(Q_DrawB[2]) {
      BG.Draw();
    }
    stroke(Green);
    fill(Green);
    ellipse(15, 15, 8, 8); 
  popMatrix();
  pushMatrix();
    fill(C1);
    stroke(C1);
    ellipse(200, 400, 20, 20);

    translate(200, 400);  
    rotate(i * Unit);
    if(Q_DrawB[3]) {
      BG.Draw();
    }
    fill(C2);
    stroke(C2);
    ellipse(80, 80, 25, 25);
  
    translate(80, 80);
    rotate(5 * i * Unit);
    if(Q_DrawB[4]) {
      BG.Draw();
    }
    fill(C3);
    stroke(C3);
    ellipse(40, 40, 15, 15); 
  
    translate(40, 40);
    rotate(10 * i * Unit);
    if(Q_DrawB[5]) {
      BG.Draw();
    }
    stroke(C4);
    fill(C4);
    ellipse(15, 15, 8, 8); 
  popMatrix();
  fill(White);
  rect(W/2, H - Pane1H/2, W, Pane1H);
  int j;
  for(j = 0; j < BN; j++) {
    Buttons[j].Draw();
  }  
  i++;
}

void mousePressed() {
  int i;
  
  for(i = 0; i < BN; i++) {
    if(Buttons[i].Q_MouseIn()) {
      Q_DrawB[i] = !Q_DrawB[i];
    }
  }
}

void keyPressed() {
  save("SSPPG" + i + ".jpg");
}
