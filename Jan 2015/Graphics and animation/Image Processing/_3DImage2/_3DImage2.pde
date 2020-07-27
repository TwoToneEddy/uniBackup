PImage Pic;
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
  translate(W/2, H/2);
}

void draw() {
  background(Yellow);
  translate(W/2, H/2 - PH/2, 20);
  rotateY(Theta);
  image(Pic, -PW/2, 0, PW, PH);
  Theta += dTheta;
} 
int i = 0;

void mousePressed() {
  save("_3dim2" + i + ".jpg");
  i++;
}

