class Car {
  Block Body;
  Circle Wheel1,
         Wheel2;
  
  Car(int Lx, int Ty, int W, int H, int WR, color BCol, color WCol) {
    Body = new Block(Lx, Ty, W, H);
    Wheel1 = new Circle(Lx, Ty + H, 2 * WR);
    Wheel2 = new Circle(Lx + W, Ty + H, 2 * WR); 
    Body.FillCol = BCol;
    Wheel1.FillCol = WCol;   
    Wheel2.FillCol = WCol;   
  }
  
  void Draw() {
    Body.Draw();
    Wheel1.Draw();
    Wheel2.Draw();
  }
  
  void Set_Velocity(int dx, int dy) {
    Body.dx = dx;
    Body.dy = dy;
    Wheel1.dx = dx;
    Wheel1.dy = dy; 
    Wheel2.dx = dx;
    Wheel2.dy = dy; 
  }
  
  void Incr_Velocity(int dx, int dy) {
    Body.dx += dx;
    Body.dy += dy;
    Wheel1.dx += dx;
    Wheel1.dy += dy; 
    Wheel2.dx += dx;
    Wheel2.dy += dy; 
  }
  
  void Move() {
    Body.Move();
    Wheel1.Move();
    Wheel2.Move();
  }

  void Explode(int v) {
    Body.dy = Body.dy -v;
    Wheel1.dx = Wheel1.dx - v;
    Wheel1.dy = Wheel1.dy + v;
    Wheel2.dx = Wheel2.dx + v;
    Wheel2.dy = Wheel2.dy + v;    
  }   
}    
