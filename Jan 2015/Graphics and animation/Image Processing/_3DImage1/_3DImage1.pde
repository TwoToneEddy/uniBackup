PImage Pic;
float Red,
      Green,
      Blue;
int W = 400,
    H = W;
int PW,
    PH;
int Div = 5;
float Theta = 0;
float dTheta = 0.01;
color Yellow = color(255, 255, 0);
void setup() {  
  size(W, H, P3D);
  Pic = loadImage("Small Mona Lisa.jpg");
  PW = Pic.width/Div;
  PH = Pic.height/Div;
}

void draw() {
  background(Yellow);
  rotateZ(Theta);
  image(Pic, W/2 - PW/2, H/2 - PH/2, PW, PH);
  Theta += dTheta;
} 


