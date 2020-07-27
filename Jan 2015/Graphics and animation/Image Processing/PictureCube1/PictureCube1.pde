int W = 400,
    H = W;
Square S;
int Cx = 0,
    Cy = 0;
int HalfSide = 40;
float Rx = 0.0,
      Ry = 0.0,
      Rz = 0.0;
PFont Font;
void setup() {
  smooth();
  size(W, H, P3D);
  Font = loadFont("Calibri-Bold-20.vlw");
  textFont(Font); 
  S = new Square(Cx, Cy, HalfSide);
}


void draw() {
  background(White);
  translate(W/2, H/2, 0);
  rotateX(Rx);
  rotateY(Ry);
  rotateZ(Rz);
  S.Col = Green;
  S.LCol = Black;
  S.Legend = "1"; 
  translate(0, 0, HalfSide);
  S.Draw();
  
  S.Col = Red;
  S.LCol = Black;
  S.Legend = "2";
  rotateX(PI/2);
  translate(0, HalfSide, HalfSide);  
  S.Draw();
  
  S.Col = Blue;
  S.LCol = Black;
  S.Legend = "3";
  rotateX(PI/2);
  translate(0,-HalfSide, -HalfSide);  
  S.Draw();

  S.Col = Orange;
  S.LCol = Black;
  S.Legend = "4";
  rotateX(PI/2);
  translate(0,HalfSide, HalfSide);  
  S.Draw();
  
  S.Col = C1;
  S.LCol = Black;
  S.Legend = "5";
  rotateY(PI/2);
  translate(HalfSide, 0, HalfSide);  
  S.Draw();
  
  S.Col = Yellow;
  S.LCol = Black;
  S.Legend = "5";
  //rotateY(PI/2);
  translate(0, 0, - 2 * HalfSide);  
  S.Draw();
}

void keyPressed() {
  switch(key) {
    case 'x':    
      Rx += 0.1;
    break;
    case 'y':    
      Ry += 0.1;
    break;
    case 'z':    
      Rz += 0.1;
    break;
    case 'u':    
      Rx -= 0.1;
    break;
    case 'v':    
      Ry -= 0.1;
    break;
    case 'w':    
      Rz -= 0.1;
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
  }
}
    

