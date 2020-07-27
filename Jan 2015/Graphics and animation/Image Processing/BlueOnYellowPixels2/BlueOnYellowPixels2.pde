color Blue = color(0, 0, 255);
color Yellow = color(255, 255, 0);
int W = 200,
    H = W;
    
int N;

void setup() {
  size(W, H);
  N = W * H;
  background(Yellow);
  loadPixels();
  frameRate(1000);
}

int i = 0;

void draw() {
  if(i < N) {
    pixels[i] = Blue;
    i+= 4;
    updatePixels();
  }
}
int j = 0;

void mousePressed() {
  save("BY3" + j + ".jpg");
  j++;
}
  
