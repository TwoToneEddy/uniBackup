int W = 400,
    H = W;
Circle C;
int Cx = 200,
    Cy = 300,
    D = 50;
    
void setup() {
  size(W, H);
  smooth();
  C = new Circle(Cx, Cy, D, Red);
  C.Show();
  C.dx = 1;
  C.dy = -1;
}

void draw() {
  background(Yellow);
  C.Show();
  C.Move();
}
  
  
