int W = 600,
    H = W;
Circle C;
Square S;
int Cx = 200,
    Cy = 300,
    D = 50;
int Sx = 200,
    Sy = 50,
    HS = 30;
    
void setup() {
  size(W, H);
  smooth();
  C = new Circle(Cx, Cy, D, Red);
  C.dx = 1;
  C.dy = -2;
  S = new Square(Sx, Sy, HS);
  S.Col = Green;
  S.dx = 1;
  S.dy = 0;
  background(Yellow);
  C.Show();
  S.Show();
}

boolean Q_Go = false;

void draw() {
  if(Q_Go) {
    background(Yellow);
    C.Show();
    C.Move();
    S.Show();
    S.Move();
  }
}
int i = 0;
void keyPressed() {
  save("CS1_" + i + ".jpg");
  i++;
}

void mousePressed() {
  Q_Go = !Q_Go;
}
  
  
