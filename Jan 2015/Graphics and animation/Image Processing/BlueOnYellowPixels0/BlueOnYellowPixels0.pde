color Blue = color(0, 0, 255);
color Yellow = color(255, 255, 0);

void setup() {
  size(200, 200);
  background(Yellow);
  loadPixels();
  for (int i = 0; i < width*height; i+= 4) {
    pixels[i] = Blue;
  }
}

void draw() {
}

void mousePressed() {
  updatePixels();
  save("P0.jpg");
}

