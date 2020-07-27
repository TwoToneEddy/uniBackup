int Side = 800;
color Yellow = color(255, 255, 0),
      Red = color(255, 0, 0),
      Green = color(0, 255, 0),
      Blue = color(0, 0, 255),
      Orange = color(255, 120, 30),
      White = color(255, 255, 255),
      Black = color(0),
      BG = White,
      Alive = Red;

//color[] Colours = {White, Black, Black, Black, Black, Black, Black, White};
int y = 1;
 
void setup() {
  int i,
      j;
  size(Side, Side);
  for(i = 0; i < Side; i++) {
    for(j = 0; j < Side; j++) {
      if (random(100) > 20) {
        set(i, j, Black);
      }
      else {
        set(i, j, Alive);
      }
    }
  }
}
 
void draw() {
  int i, j;
  int Code;
  
  loadPixels(); 
  for(i = 0; i < Side; i++) {
    for(j = 0; j < Side; j++) {
      Code = get_Code(i, j);
      if (pixels[j * Side + i] == Alive) {
        if (Code < 2) {
          pixels[j * Side + i] = Black;
        }
        else {
          if (Code > 3) {
            pixels[j * Side + i] = Black;
          }
        }
      }
      else {
        if (Code == 3) {
          pixels[j * Side + i] = Alive;
        }
      }
    }
  }
  updatePixels();
}

int get_Code(int x, int y){
  int i,
      j;
  int Sum = 0;      
  for (i = -1; i < 2; i++) {
    for(j = -1; j < 2; j++) {
      if (i!=0 || j!= 0) {
        if (get((i + x + Side) % Side, (j + y + Side) % Side) == Alive) {
          Sum++;
        }
      }
    }
  }  
  return(Sum);
}  

void mousePressed() {
  println(get_Code(mouseX, mouseY));
}
