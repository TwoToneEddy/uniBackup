color Yellow = color(255, 255, 0),
      Orange = color(255, 127, 0);
int W = 500,
    H = W;
Triangle T;

void setup() {  
  size(W, H); 
  T = new Triangle(250,250,50);
  T.Dx=5;
  T.Dy=5;
}


void draw() {
  background(Orange);
  T.Draw();
  T.Move();
  if(((T.Mx+T.HB)>=W)&&(T.Dx>0))
    T.Dx=T.Dx*-1;
  if((T.Ty <= 0)&&(T.Dy<0))
    T.Dy=T.Dy*-1;
  if((T.Ty+(3*T.HB)>=H)&&(T.Dy>0))
    T.Dy=T.Dy*-1;
  if(((T.Mx-T.HB)<=0)&&(T.Dx<0))
    T.Dx=T.Dx*-1;
   
}

void mousePressed() {
  

}

