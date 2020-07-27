
PImage Pic;
float PointSize = 3;
int Top = 1;
void setup() {
  Pic = loadImage("Small Mona Lisa.jpg");
  size(Pic.width, Pic.height);
  image(Pic, 0, 0);
  noStroke();
  smooth();
  frameRate(1000);
}

void draw() {
  int i; 
  int x,
      y;
          
   x = int(random(Pic.width));
   y = int(random(Pic.height));
   color pix = Pic.get(x, y);
   fill(pix);
   ellipse(x + int(random(5)) - 2, y + int(random(5)) - 2, int(random(2 * PointSize)) + 1, int(random(2 * PointSize)) + 1);
}

int i = 0;

void mousePressed() {
  save("M2" + i + ".jpg");
  i++;
}
