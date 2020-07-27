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

public class PictureCube2 extends PApplet {

int W = 800,
    H = W;
Cube PC;
float Rx = 0.0f,
      Ry = 0.0f,
      Rz = 0.0f;
float dRx = 0.0f,
      dRy = 0.0f,
      dRz = 0.0f;
      
PImage[] Pics = new PImage[6];


public void setup() {
  size(W, H, P3D);
  Pics[0] = loadImage("P1.jpg");
  Pics[1] = loadImage("P2.jpg");
  Pics[2] = loadImage("P3.jpg");
  Pics[3] = loadImage("P4.jpg");
  Pics[4] = loadImage("P5.jpg");
  Pics[5] = loadImage("P6.jpg");
  /*Pics[0] = loadImage("P1.jpg");
  Pics[1] = loadImage("P2.jpg");
  Pics[2] = loadImage("P3.jpg");
  Pics[3] = loadImage("P4.jpg");
  Pics[4] = loadImage("P5.jpg");
  Pics[5] = loadImage("P6.jpg");*/
  PC = new Cube(Pics);
}

public void draw() {
  background(White);
  pushMatrix();
    translate(W/2, H/2, 0);
    rotateX(Rx);
    rotateY(Ry);
    rotateZ(Rz);   
    PC.Draw();
  popMatrix();
  Rx += dRx;
  Ry += dRy;
  Rz += dRz;
}

public void keyPressed() {
  switch(key) {
    case 'x':    
      Rx += 0.1f;
    break;
    case 'y':    
      Ry += 0.1f;
    break;
    case 'z':    
      Rz += 0.1f;
    break;
    case 'u':    
      Rx -= 0.1f;
    break;
    case 'v':    
      Ry -= 0.1f;
    break;
    case 'w':    
      Rz -= 0.1f;
    break;
    case 'a':    
      Rx = 0;
    break;
    case 'b':    
      Ry = 0;
    break;
    case 'c':    
      Rz = 0;
    break;
    case 'i':    
      dRx += 0.01f;
    break;
    case 'j':    
      dRy += 0.01f;
    break;
    case 'k':    
      dRz += 0.01f;
    break;
    case 'l':    
      dRx -= 0.01f;
    break;
    case 'm':    
      dRy -= 0.01f;
    break;
    case 'n':    
      dRz -= 0.01f;
    break;
    case 'p':    
      dRx = 0;
    break;
    case 'q':    
      dRy = 0;
    break;
    case 'r':    
      dRz = 0;
    break;
  }
}
 int i = 0; 
public void mousePressed() {
  save("PC2" + i + ".jpg");
  i++;
} 

int Yellow = color(255, 255, 0),
      Green = color(0, 255, 0),
      Blue = color(0, 0, 255),
      Red = color(255, 0, 0),
      Orange = color(255, 127, 0),
      Col1 = color(127, 127, 0),
      Col2 = color(255, 255, 127);
int White = color(255, 255, 255);  
int Black = color(0, 0, 0);  

public int RandCol() {
  int Col;
  float R = random(256),
        G = random(256),
        B = random(256);
  Col = color(R, G, B);
  return(Col);
}
  
class Cube {  
  float Side;
  float[] Rots; 
  PImage[] Pics; 
  
  Cube(PImage[] Pics) {
    this.Pics = Pics;
    Side = Pics[0].width;
  }
  
  public void Draw() {    
    pushMatrix();
      translate(0, 0, Side/2);
      image(Pics[0], -Side/2, -Side/2);
    popMatrix();
    
    pushMatrix();
      rotateX(PI/2);
      translate(-Side/2, -Side/2, Side/2);
      image(Pics[1], 0, 0); 
    popMatrix(); 
  

    pushMatrix();
      rotateX(PI);
      translate(-Side/2, -Side/2, Side/2);
      image(Pics[2], 0,0); 
    popMatrix(); 


    pushMatrix();
      rotateX(3 * PI/2);
      translate(-Side/2, -Side/2, Side/2);
      image(Pics[3], 0,0); 
    popMatrix(); 
    
    pushMatrix();
      rotateY(PI/2);
      translate(-Side/2, -Side/2, Side/2);
      image(Pics[4], 0,0);
    popMatrix(); 
  
    pushMatrix();
      rotateY(3 * PI/2);
      translate(-Side/2, -Side/2, Side/2);
      image(Pics[5], 0,0);       
    popMatrix(); 
  }
}

class Square {
  float Cx,
      Cy;
  float HS;
  int Col;
  int LCol;
  float dx,
      dy;
  String Legend = null;      
  
  Square(float Cx, float Cy, float HS) {
    this.Cx = Cx;
    this.Cy = Cy;
    this.HS = HS;
  }
  
  public void Draw() {
    fill(Col);
    rectMode(CENTER);
    rect(Cx, Cy, 2 * HS, 2 * HS);
    if(Legend != null) {
      fill(LCol);
      textAlign(CENTER, CENTER);
      //text(Legend, Cx, Cy);
    }
  }
  
  public void Move() {
    Cx += dx;
    Cy += dy;
  }
  
  public boolean Q_MouseIn() {
    return(Cx - HS < mouseX && mouseX < Cx + HS &&
           Cy - HS < mouseY && mouseY < Cy + HS);
  }
}
class UpDownControl {
  int ID;
  Square Up,
         Down;
  String Legend = null;        
  int ArrowCol = Black;
  int LCol = White;
  int Cx, 
      Cy;
  int HS;
  
  UpDownControl(int Cx, int Cy, int HS, int Col) {
    this.Cx = Cx;
    this.Cy = Cy;
    this.HS = HS;
    Up = new Square(Cx, Cy - HS, HS);
    Down = new Square(Cx, Cy + HS, HS);
    Up.Col = Col;
    Down.Col = Col;
  }    
  
  public void Draw() {
    Up.Draw();
    Down.Draw();
    fill(ArrowCol);
    triangle(Cx - HS, Cy, Cx + HS, Cy, Cx, Cy - 2 * HS);
    triangle(Cx - HS, Cy, Cx + HS, Cy, Cx, Cy + 2 * HS);
    if(Legend != null) {
      fill(LCol);
      textAlign(CENTER, CENTER);
      text(Legend, Cx, Cy);
    }
  }
  
  public boolean Q_Up() {
    return(Up.Q_MouseIn());
  }
  
  public boolean Q_Down() {
    return(Down.Q_MouseIn());
  }
  
  public boolean Q_MouseIn() {
    return(Up.Q_MouseIn() || Down.Q_MouseIn());
  }
}
  static public void main(String args[]) {
    PApplet.main(new String[] { "--bgcolor=#ece9d8", "PictureCube2" });
  }
}
