color Blue = color(0, 0, 255);
color Yellow = color(255, 255, 0);

void setup() {
  size(200, 200);
  background(Yellow);
  loadPixels();
  for (int i = 0; i < height; i+= 4) {
    for (int j = 0; j < width; j++) {
      Wait(100);
      pixels[i * width + j] = Blue;
      updatePixels();
      draw();
    }
  }
  
  save("P2.jpg");
}

void Wait(int T) {
  int Now = millis(),
      Then = millis() + T;
  
  while(millis() < Then){
  }
}

