int W = 800,
    H = W;
Cube PC;
float Rx = 0.0,
      Ry = 0.0,
      Rz = 0.0;
float dRx = 0.0,
      dRy = 0.0,
      dRz = 0.0;
      
PImage[] Pics = new PImage[6];


void setup() {
  size(W, H, P3D);
  Pics[0] = loadImage("P1.jpg");
  Pics[1] = loadImage("P2.jpg");
  Pics[2] = loadImage("P3.jpg");
  Pics[3] = loadImage("P4.jpg");
  Pics[4] = loadImage("P5.jpg");
  Pics[5] = loadImage("P6.jpg");
  PC = new Cube(Pics);
}

void draw() {
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
    case 'i':    
      dRx += 0.01;
    break;
    case 'j':    
      dRy += 0.01;
    break;
    case 'k':    
      dRz += 0.01;
    break;
    case 'l':    
      dRx -= 0.01;
    break;
    case 'm':    
      dRy -= 0.01;
    break;
    case 'n':    
      dRz -= 0.01;
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
void mousePressed() {
  save("PC2" + i + ".jpg");
  i++;
} 

