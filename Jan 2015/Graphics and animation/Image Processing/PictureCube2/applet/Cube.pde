  
class Cube {  
  float Side;
  float[] Rots; 
  PImage[] Pics; 
  
  Cube(PImage[] Pics) {
    this.Pics = Pics;
    Side = Pics[0].width;
  }
  
  void Draw() {    
    pushMatrix();
      translate(0, 0, Side/2);
      image(Pics[0], -Side/2, -Side/2);
    popMatrix();
    
    pushMatrix();
      rotateX(PI/2);
      translate(-Side/2, -Side/2, Side/2);
      image(Pics[1], 0, 0); 
    popMatrix(); 
  

    pushMatrix();
      rotateX(PI);
      translate(-Side/2, -Side/2, Side/2);
      image(Pics[2], 0,0); 
    popMatrix(); 


    pushMatrix();
      rotateX(3 * PI/2);
      translate(-Side/2, -Side/2, Side/2);
      image(Pics[3], 0,0); 
    popMatrix(); 
    
    pushMatrix();
      rotateY(PI/2);
      translate(-Side/2, -Side/2, Side/2);
      image(Pics[4], 0,0);
    popMatrix(); 
  
    pushMatrix();
      rotateY(3 * PI/2);
      translate(-Side/2, -Side/2, Side/2);
      image(Pics[5], 0,0);       
    popMatrix(); 
  }
}

