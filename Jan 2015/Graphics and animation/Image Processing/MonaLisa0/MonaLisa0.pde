PImage Pic;

void setup() {
  Pic = loadImage("Small Mona Lisa.jpg");
  size(Pic.width, Pic.height);  
  background(0);
  frameRate(1000);
}

void draw() {
  int i; 
  int x,
      y;
      
  x = int(random(Pic.width));
  y = int(random(Pic.height));
  color pix = Pic.get(x, y);
  set(x, y, pix);
}

int i = 0;

void mousePressed() {
  save("M0" + i + ".jpg");
  i++;
}
