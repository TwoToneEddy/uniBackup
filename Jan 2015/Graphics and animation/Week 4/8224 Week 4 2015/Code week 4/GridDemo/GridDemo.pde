int W = 500,
    H = W;

int GSide = 20;
Grid RG = new Grid(GSide);

void setup() {  
  size(W, H);
  RG.LineCol = Red;
}

void draw() {
  background(White);  
  RG.Draw();
}


