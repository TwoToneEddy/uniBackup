int W = 600,
    H = 600;
int dx = 0,
    dy = 0;
PFont font;
color Yellow = color(255, 255, 0),      
      Green = color(0, 255, 0),
      Red = color(255, 0, 0),
      BG_C = Yellow;
Car C = new Car(40, 150, 100, 40, 20, Green, Red); 
 
void setup() {
  size(W, H);
  smooth();
  fill(Green);
}

void draw() {
  background(BG_C);
  C.Draw();
  C.Move();
}

void keyPressed() {
  switch(key) {
    case 'l':
      C.Incr_Velocity(1, 0);
    break;
    case 'u':
      C.Incr_Velocity(0, -1);
    break;
    case 'r':
      C.Incr_Velocity(-1, 0);
    break;
    case 'd':
      C.Incr_Velocity(0, 1);
    break;
    case 'm':
      C.Incr_Velocity(1, 1);
    break;
    case 'z':
      C.Incr_Velocity(-1, 1);
    break;
    case 'q':
      C.Incr_Velocity(-1, -1);
    break;
    case 'p':
      C.Incr_Velocity(1, -1);
    break;

    case 's':
      C.Set_Velocity(0, 0);
    break;
  }
}  
       



