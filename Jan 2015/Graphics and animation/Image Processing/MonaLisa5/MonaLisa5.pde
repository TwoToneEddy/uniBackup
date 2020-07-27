PImage Pic; 
Pic = loadImage("Small Mona Lisa.jpg");
size(Pic.width, Pic.height);
image(Pic, 0, 0);
int N = 16;
int i;
PImage Part = get(80, 80, 100, 100);

for(i = 0; i < N; i++) {
  image(Part, random(width), random(height));
}

save("ML4.jpg");
