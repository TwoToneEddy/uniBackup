int PanelH = 40;
int W = 500,
    H = W + 2 * PanelH;
PFont Font;

int GSide = 20;
Grid RG = new Grid(GSide);
Grid BG = new Grid(GSide);
float X = 0,
      Y = 0;

void setup() {  
  size(W, H);
  RG.LineCol = Red;
  BG.LineCol = Blue;
  Font = loadFont("CourierNewPS-BoldItalicMT-16.vlw");
  textFont(Font);
}

int TmX,
    TmY;
    
void draw() {
  background(White);  
  translate(X, Y);
  TmX = mouseX;
  TmY = mouseY;
  BG.Draw();
  fill(Blue); 
  ellipse(mouseX, mouseY, 10, 10);  
  translate(-X, -Y);
  RG.Draw();
  fill(Red);
  ellipse(mouseX, mouseY, 10, 10);
  rectMode(CENTER);
  fill(Yellow);
  rect(width/2, height - PanelH/2, width, PanelH);
  fill(Green); 
  rect(W/4, H - 3 * PanelH/2, width/2, PanelH);
  rect(3 * W/4, H - 3 * PanelH/2, width/2, PanelH);
  textAlign(CENTER, CENTER);
  fill(Black);
  text("Move right - key r\nMove left - key l", width/4 , H - 3 * PanelH/2);
  text("Move up - key u\nMove down - key d", 3 * width/4, H - 3 * PanelH/2);
  fill(Blue);
  text("Translated mouse at " + "(" + TmX + ", " + TmY + ") WRT Blue grid", W/2, H - 3 * PanelH/4);   
  
  fill(Red);
  text("Untranslated mouse at " + "(" + mouseX + ", " + mouseY + ") WRT Red grid", W/2, H - PanelH/4);   
}

int i = 0;
void mousePressed() {//this is to get jpg files of the display
  save("TranslateDemo_" + i + ".jpg");//to put into PowerPoint
  i++;
}

void keyPressed() {
  switch(key) {
    case 'r':
      X += 1;
    break;
    case 'l':
      X -= 1;
    break;
    case 'u':
      Y -= 1;
    break;
    case 'd':
      Y += 1;
    break;
  }
}
