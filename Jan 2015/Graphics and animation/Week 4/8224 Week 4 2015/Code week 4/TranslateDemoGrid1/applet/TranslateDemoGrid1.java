import processing.core.*; 
import processing.xml.*; 

import java.applet.*; 
import java.awt.Dimension; 
import java.awt.Frame; 
import java.awt.event.MouseEvent; 
import java.awt.event.KeyEvent; 
import java.awt.event.FocusEvent; 
import java.awt.Image; 
import java.io.*; 
import java.net.*; 
import java.text.*; 
import java.util.*; 
import java.util.zip.*; 
import java.util.regex.*; 

public class TranslateDemoGrid1 extends PApplet {

int PanelH = 40;
int W = 500,
    H = W + 2 * PanelH;
PFont Font;

int GSide = 20;
Grid RG = new Grid(GSide);
Grid BG = new Grid(GSide);
float X = 0,
      Y = 0;

public void setup() {  
  size(W, H);
  RG.LineCol = Red;
  BG.LineCol = Blue;
  Font = loadFont("CourierNewPS-BoldItalicMT-16.vlw");
  textFont(Font);
}

int TmX,
    TmY;
    
public void draw() {
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
public void mousePressed() {//this is to get jpg files of the display
  save("TranslateDemo_" + i + ".jpg");//to put into PowerPoint
  i++;
}

public void keyPressed() {
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
class Grid {
  int Side;
  int LineCol;
  
  Grid(int Side) {
    this.Side = Side;
  }
  
  public void Draw() {
    int i;
    stroke(LineCol);
    for(i = 0; i <= width; i+=Side) {
      line(i, 0, i, height - 2 * PanelH);
      line(0, i, width, i);
    }
  }
}
int Yellow = color(255, 255, 0);
int Red = color(255, 0, 0);
int Green = color(0, 255, 0);
int Black = color(0, 0, 0);
int White = color(255, 255, 255);
int Blue = color(0, 0, 255);
int Orange = color(255, 127, 0);

public int RandCol() {
  int Col = color(random(255), random(255), random(255));
  return(Col);
}

  static public void main(String args[]) {
    PApplet.main(new String[] { "--bgcolor=#ece9d8", "TranslateDemoGrid1" });
  }
}
