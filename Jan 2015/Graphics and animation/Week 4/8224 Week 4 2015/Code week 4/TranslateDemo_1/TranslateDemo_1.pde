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
  background(Yellow);
  line(x, 0, x, H);//Draws vertical line
  line(0, y, W, y);//Draws horizontalal line
  textAlign(CENTER);
  fill(Red);
  text("(" + x +", " + y + ")", x, y);//text draws (x, Y) of line intersectio
  noFill();
  stroke(Blue);
  ellipse(x, y, 10, 10);
  line(mouseX - 5, mouseY, mouseX + 5, mouseY);//draws vertical component of cross  
  line(mouseX, mouseY - 5, mouseX, mouseY + 5);//draws horizontal component of cross   
  fill(White);
  rect(0, H - PanelH, W, PanelH);
  Status = "NO TRANSLATION";
  fill(Blue);
  Status = Status + " - mouse at " + "(" + mouseX + ",";
  Status = Status + mouseY + ")";
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

void mousePressed() {
  save("TranslateDemo_1.jpg");
}
