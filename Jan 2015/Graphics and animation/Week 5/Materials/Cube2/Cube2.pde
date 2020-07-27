int W = 600,
    H = W;    
int N = 40;
int i = 0;

void setup() {
  
  size(W, H, P3D);
 }

void draw() {
  background(0);
  translate(W/2, H/2);
  rotateY(frameCount*PI/mouseX);
  rotateX(frameCount*PI/mouseY);
  rotateZ(frameCount*PI/600);
  fill(Green);
  box(N, N, N);
  pushMatrix();
  translate(0, N);
  rotateY(PI/4);
  fill(Red);
  box(N, N, N); 
  popMatrix();
  fill(Blue);
  translate(0, 2 * N  + i);
  box(N, N, N); 
  i++;
}
 
void mouseClicked() {
  i = -i;
}  

int j = 0;    
void keyPressed() {
  save("Cube" + j + ".jpg");
  j++;
}
