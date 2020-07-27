int W = 600,
    H = W;    
int N = 40;
boolean col = false;

void setup() {
  size(W, H, P3D); 
}

void draw() {
  background(Blue);
  translate(200, H-(100));
  //translate(frameCount, frameCount);
  rotateY(radians(45));
  rotateX(radians(-35));
  //rotateX(radians(frameCount));
  rotateZ(radians(-25));
  translate(-N,0,0);
  for(int i = 0; i < 8; i++){
    pushMatrix();
    col = !col;
    translate(0,0,i*(-N));
    for(int j = 0; j < 8; j++){
      translate(N,0,0);
      if(col==false){
        col = true;
        fill(Black);
      }else{
        fill(White);
        col = false;
      }
      box(N);
    }
    popMatrix();
  }
  /*
  fill(Red);
  box(N, N, N);
  translate(N,0,-N);
  fill(Green);
  box(N, N, N); 
  translate(N,0,-N);
  fill(Blue);
  box(N, N, N); */
}
  
int i = 0;    
void keyPressed() {
  save("Cube" + i + ".jpg");
  i++;
}    
