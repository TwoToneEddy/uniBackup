int W = 300,
    H = W;
PImage Im;
void setup() {
  size(W, H);
  noStroke();
  Im = loadImage("King_Alfred_the_Great.jpg");
  image(Im, 0, 0, Im.width/5, Im.height/5);  
}

int i = 0;
int x = 40,
    y = 40;
void draw() {
  background(Yellow);
  fill(Red);
  //pushMatrix();
    translate(150 + i/20, 150 + i/20);
    rotate(i * PI/400);  
    image(Im, 0, 0, Im.width/5, Im.height/5);    
    i++; 
  //popMatrix();
 }
  



