color Blue = color(0, 0, 255);
color Yellow = color(255, 255, 0);

void setup() {
  size(200, 200);
  background(Yellow);
  loadPixels();
}

void draw() {
  for (int i = 0; i < height; i+= 4) {
    for (int j = 0; j < width; j++) {
      pixels[i * width + j] = Blue;
    }
  }
}

void mousePressed() {
  updatePixels();
  save("P3.jpg");
}

