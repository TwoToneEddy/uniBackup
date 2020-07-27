

// get the inputs 

// ==========================================================

void mouseReleased () {
  // mouse evaluate. 
  // Check what's been 
  // pressed by the mouse.

  boolean done = false; 

  // Wenn linke Taste  
  if  (mouseButton == LEFT) {  
    // by Quark
    Shape3D picked = Shape3D.pickShapeB(this,mouseX, mouseY);
    if (picked != null) {
      done=true; 
      MakeHumanMove(picked.tagNo);
    }
  }
} // mouseClicked

void keyPressed() {
  if (key == CODED) {
    switch (keyCode) {
    case UP: 
      showFiguresAsImages = false; 
      InitBoxes () ;      
      break; 
    case DOWN: 
      showFiguresAsImages = true; 
      break; 
    default: 
      // nothing
      break; 
    } // switch 
  } // if
  else {
    // not coded 
    switch (key) {
    case '0': 
      NumberOfPlayer = 0; 
      statusPlayer=PlayerPC; 
      break; 
    case '1':     
      NumberOfPlayer = 1;     
      break; 
    case '2':     
      NumberOfPlayer = 2; 
      break; 
    case 'n':
      newGame=true; 
      break;  
    default: 
      // nothing
      break; 
    } // switch     
  } // else (not coded) 

} // keyPressed


