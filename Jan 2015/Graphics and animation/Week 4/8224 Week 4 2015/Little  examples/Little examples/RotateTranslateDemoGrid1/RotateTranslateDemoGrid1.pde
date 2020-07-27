int PanelH = 40;
int W = 500,
    H = W + 2 * PanelH;
PFont Font;
RectButton Turn_CW,
           Turn_ACW;
PImage CWArrowIm,
       ACWArrowIm;
int GSide = 20;
Grid RG = new Grid(GSide);
Grid BG = new Grid(GSide);
float Theta = 0;

void setup() {  
  size(W, H);
  RG.LineCol = Red;
  BG.LineCol = Blue;
  Font = loadFont("CourierNewPS-BoldItalicMT-16.vlw");
  textFont(Font);
  Turn_CW = new RectButton(W/4, H - PanelH * 3/2, W/4, PanelH/2);
  Turn_CW.Font = Font;
  Turn_CW.Legend = "Turn Clockwise \n - key c";
  Turn_CW.Col = Green;
  Turn_ACW = new RectButton(3 * W/4, H - PanelH * 3/2, W/4, PanelH/2);
  Turn_ACW.Font = Font;
  Turn_ACW.Legend = "Turn Anti Clockwise \n - key a";
  Turn_ACW.Col = Green;
  CWArrowIm = loadImage("CWArrow.jpg");
  ACWArrowIm = loadImage("ACWArrow.jpg");
  
}


int RmX,
    RmY;
int UmX,
    UmY;
    
void draw() {
  background(White);  
  rotate(Theta);
  RmX = mouseX;
  RmY = mouseY;
  BG.Draw();
  textAlign(CENTER);
  fill(Blue); 
  ellipse(mouseX, mouseY, 10, 10);  
  rotate(-Theta);
  RG.Draw();
  UmX = mouseX;
  UmY = mouseY;
  Turn_CW.Draw();  
  Turn_ACW.Draw();
  fill(Red);
  ellipse(mouseX, mouseY, 10, 10); 
  image(CWArrowIm, 0, H - PanelH - CWArrowIm.height/5, CWArrowIm.width/5, CWArrowIm.height/5);  
  image(ACWArrowIm, W - ACWArrowIm.width/5, H - PanelH - ACWArrowIm.height/5, ACWArrowIm.width/5, CWArrowIm.height/5);
  fill(Yellow);
  rect(width/2, height - PanelH/2, width, PanelH);
  fill(Red);
  text("Unrotated mouse at " + "(" + mouseX + ", " + mouseY + ")", W/2, H - PanelH/4);   
  fill(Blue);
  text("Rotated mouse at " + "(" + RmX + ", " + RmY + ")", W/2, H - 3 * PanelH/4);   
}

void mousePressed() {
}


void keyPressed() {
  switch(key) {
    case 'c':
      Theta += 0.01;
    break;
    case 'a':
      Theta -= 0.01;
    break;
    case 'd':
      Theta += 0.1;
    break;
    case 'e':
      Theta -= 0.1;
    break;
    case 's':
      Theta += 0.1;
    break;
  }
}
