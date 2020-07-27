PImage Pic;

void setup() {  
  Pic = loadImage("Small Mona Lisa.jpg");
  size(Pic.width, Pic.height);
  println(width + " " + height);
  set(0, 0, Pic);
  save("MLP7.jpg");
} 


void draw() {
}

void mousePressed() {
  float Red,
        Green,
        Blue;

  loadPixels();
  background(0);
  for (int i = 0; i < width*height; i++) {
    Red = red(pixels[i]);
    Green = green(pixels[i]);
    Blue = blue(pixels[i]);
    pixels[i] = color(Green, Red, Blue);
  }
  updatePixels();
  save("ML7.jpg");
}
