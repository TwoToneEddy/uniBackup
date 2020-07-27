int PanelH = 40;
int W = 500,
    H = W + PanelH;
int x = 0,
    y = 0;
PFont Font;

void setup() {  
  size(W, H);
  stroke(Red);
  Font = loadFont("CourierNewPS-BoldItalicMT-16.vlw");
  textFont(Font);
}

void draw() {
  String Status;
  background(Green);
  line(x, 0, x, H);
  line(0, y, W, y);
  textAlign(CENTER);
  fill(Red);
  text("(" + 0 +", " + 0 + ")", x, y);
  noFill();
  stroke(Blue);
  ellipse(x, y, 10, 10);
  line(mouseX - 5, mouseY, mouseX + 5, mouseY);  
  line(mouseX, mouseY - 5, mouseX, mouseY + 5);   
  fill(Yellow);
  rect(0, H - PanelH, W, PanelH);
  fill(Blue);
  Status = "translation (" + x + ", " + y + ")";
  Status = Status +  "- mouse at " + "(" + (mouseX - x) + ",";
  Status = Status + (mouseY - y) + ")";
  text(Status, W/2, H - 10);  
}

void keyPressed() {
  switch(key) {
    case 'x':
      x+= 1;
    break;
    case 'y':
      y+= 1;
    break;
    case 'u':
      x-= 1;
    break;
    case 'v':
      y-= 1;
    break;
  }
}
int i = 0;
void mousePressed() {
  save("TranslateDemo_2 " + i + ".jpg");
  i++;
}
