int W = 600,
    H = W;    
//Vector V;
int N = 40;
color[] Cols = new color[8];

void setup() {
  int i;
  
  size(W, H, P3D); 
  for(i = 0; i < 8; i++) {
    Cols[i] = RandCol();
  }
}

color BBB(int i) {
  if (random(100) < 1) {
    Cols[i] = RandCol();
  }
  return(Cols[i]);
}

void draw() {
  background(0);
  translate(W/2, H/2);
  rotateY(frameCount*PI/mouseX);
  rotateX(frameCount*PI/mouseY);
  rotateZ(frameCount*PI/600);
  
  //Box 0
  pushMatrix();
    translate(0, 0, 0);
    fill(BBB(0));
    box(N, N, N);
  popMatrix();
  
  //Box 1 
  pushMatrix();
    translate(N, 0);
    fill(BBB(1));
    box(N, N, N);
  popMatrix(); 
  
  //Box 2
  pushMatrix();
    translate(-N, 0);
    fill(BBB(2));
    box(N, N, N);
  popMatrix();
  
  //Box 3
  pushMatrix();
    translate(0, -N);
    fill(BBB(3));
    box(N, N, N);
  popMatrix();
  
  //Box 4 
  pushMatrix();
    translate(0, N);
    fill(BBB(4));
    box(N, N, N);
  popMatrix(); 
  
  //Box 5
  pushMatrix();
    translate(0, 0, -N);
    fill(BBB(5));
    box(N, N, N);
  popMatrix(); 
  
  //Box 6
  pushMatrix();
    translate(0, 0, N);
    fill(BBB(6));
    box(N, N, N);
  popMatrix(); 
  
  //Box 7
  pushMatrix();
    translate(0, 0, 2*N);
    fill(BBB(7));
    box(N, N, N);
  popMatrix(); 
}
    
