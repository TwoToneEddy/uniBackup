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

public class JustLife extends PApplet {

int Side = 800;
int Yellow = color(255, 255, 0),
      Red = color(255, 0, 0),
      Green = color(0, 255, 0),
      Blue = color(0, 0, 255),
      Orange = color(255, 120, 30),
      White = color(255, 255, 255),
      Black = color(0),
      BG = White,
      Alive = Red;

//color[] Colours = {White, Black, Black, Black, Black, Black, Black, White};
int y = 1;
 
public void setup() {
  int i,
      j;
  size(Side, Side);
  for(i = 0; i < Side; i++) {
    for(j = 0; j < Side; j++) {
      if (random(100) > 20) {
        set(i, j, Black);
      }
      else {
        set(i, j, Alive);
      }
    }
  }
}
 
public void draw() {
  int i, j;
  int Code;
  
  loadPixels(); 
  for(i = 0; i < Side; i++) {
    for(j = 0; j < Side; j++) {
      Code = get_Code(i, j);
      if (pixels[j * Side + i] == Alive) {
        if (Code < 2) {
          pixels[j * Side + i] = Black;
        }
        else {
          if (Code > 3) {
            pixels[j * Side + i] = Black;
          }
        }
      }
      else {
        if (Code == 3) {
          pixels[j * Side + i] = Alive;
        }
      }
    }
  }
  updatePixels();
}

public int get_Code(int x, int y){
  int i,
      j;
  int Sum = 0;      
  for (i = -1; i < 2; i++) {
    for(j = -1; j < 2; j++) {
      if (i!=0 || j!= 0) {
        if (get((i + x + Side) % Side, (j + y + Side) % Side) == Alive) {
          Sum++;
        }
      }
    }
  }  
  return(Sum);
}  

public void mousePressed() {
  println(get_Code(mouseX, mouseY));
}
  static public void main(String args[]) {
    PApplet.main(new String[] { "--bgcolor=#F0F0F0", "JustLife" });
  }
}
