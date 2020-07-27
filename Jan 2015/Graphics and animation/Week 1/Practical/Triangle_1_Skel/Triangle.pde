class Triangle {
  int Mx,
      Ty,
      HB,
      Dx,
      Dy;
  color Col;
  
  
  Triangle(int Mx,int Ty, int HB){
    this.Mx=Mx;
    this.Ty=Ty;
    this.HB=HB;
    Dx=1;
    Dy=1;
    Col = color(255,255,0);
    
  }
  
  void Draw() {
    
    fill(Col);
    triangle(Mx-HB,Ty+(3*HB),Mx+HB,Ty+(3*HB),Mx,Ty);
  
  }
  
  void Move() {
    Mx+=Dx;
    Ty+=Dy;
  }
}
