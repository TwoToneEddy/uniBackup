color Blue = color(0, 0, 255);
color Yellow = color(255, 255, 0);

void setup() {
  size(200, 200);
  background(Yellow);
  loadPixels();
  frameRate(1000);
}

int i = 0,
    j = 0;
    
void draw() {
  if(i < width) {
    if(j < height) {
      pixels[i * width + j] = Blue;
      updatePixels();
      j++;
    }
    else {
      if(i < width) {
        j = 0;
        i+= 4;
      }
    }
  }
}

int k = 0;

void mousePressed() {
  save("BY5" + k + ".jpg");
  k++;
}
