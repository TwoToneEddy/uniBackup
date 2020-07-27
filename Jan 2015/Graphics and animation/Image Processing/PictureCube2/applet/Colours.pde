color Yellow = color(255, 255, 0),
      Green = color(0, 255, 0),
      Blue = color(0, 0, 255),
      Red = color(255, 0, 0),
      Orange = color(255, 127, 0),
      Col1 = color(127, 127, 0),
      Col2 = color(255, 255, 127);
color White = color(255, 255, 255);  
color Black = color(0, 0, 0);  

color RandCol() {
  color Col;
  float R = random(256),
        G = random(256),
        B = random(256);
  Col = color(R, G, B);
  return(Col);
}
