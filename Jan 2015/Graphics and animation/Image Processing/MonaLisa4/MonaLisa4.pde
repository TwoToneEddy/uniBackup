PImage Pic; 
Pic = loadImage("Small Mona Lisa.jpg");
size(Pic.width, Pic.height);
image(Pic, 0, 0);
PImage Part = get(80, 80, 100, 100);
image(Part, 0, 0);
