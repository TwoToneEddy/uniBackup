color Yellow = color(255, 255, 0),
      Green = color(0, 255, 0),
      Blue = color(0, 0, 255),
      Black = color(0,0,0),
      Red = color(255, 0, 0);
color White = color(255, 255, 255);  

color RandCol() {
  color Col;
  float R = random(256),
        G = random(256),
        B = random(256);
  Col = color(R, G, B);
  return(Col);
}
