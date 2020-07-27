PImage Pic; 
Pic = loadImage("Small Mona Lisa.jpg");
size(Pic.width, Pic.height);
image(Pic, 0, 0);
int N = 100;
int i;
PImage Part;

for(i = 0; i < N; i++) {
  Part = get(80 + int(random(320)), 80 + int(random(400)), 100, 100);
  image(Part, random(width), random(height));
}

save("ML4.jpg");
