import processing.core.*; 
import processing.xml.*; 

import processing.opengl.*; 

import java.applet.*; 
import java.awt.Dimension; 
import java.awt.Frame; 
import java.awt.event.MouseEvent; 
import java.awt.event.KeyEvent; 
import java.awt.event.FocusEvent; 
import java.awt.Image; 
import java.io.*; 
import java.net.*; 
import java.text.*; 
import java.util.*; 
import java.util.zip.*; 
import java.util.regex.*; 

public class GearsApp extends PApplet {



//float theta;
//float theta2;
Gears first;
Gears scnd;

Gears thirdA;
Gears thirdB;
byte dAB;
float ax, ay;
Gears thirdC;
Gears thirdD;
byte dCD;
float cx, cy, dx, dy;
Gears thirdE;
Gears thirdF;

final float MAXTIMES = 8;
float maxDisplay;
float gearSpeed;



public void setup(){
  maxDisplay = 5;
  //size(screen.width, screen.height, OPENGL);
  gearSpeed = height/14;
  size(1200, 600, P2D);
  //theta = 0;
  //theta2 = 45;
  rectMode(CENTER);
  // x, y, h, w, dX, dY, theta, dTheta, times, rectIndex, gearIndex, rd, grn, bl, trans
  first = new Gears(0, 0, height/4, height/4, width/2, height/2, 0, 6.25f, 0, 0, 1, 0, 255, 12, 255);
  scnd = new Gears(0, 0, height/5, height/5, width/2, height/2, 90, -1.5f, 1, 0, 2, 0, 0, 255, 255);  
  
  thirdA = new Gears(0, 0, height/2, height/2, width/12, height/20, 0, 3, 0, 0, 3, 255, 25, 255, 255);
  thirdB = new Gears(0, 0, height/2.5f, height/2.5f, width/12, height/20, 90, -3, 1, 0, 3, 255, 255, 25, 255);  
  ax = width/11;
  ay = height/20;
  dAB = 0; //doesn't move
  
  thirdC = new Gears(0, 0, height/3, height/3, width/12, height/20, 0, -6.5f, 1, 0, 3, 21, 180, 180, 85);
  thirdD = new Gears(0, 0, height/5, height/5, width/12, height/20, 90, 3, 2, 0, 3, 255, 25, 25, 145); 
  cx = width/2;
  cy = height/2;
  dx = gearSpeed*random(-1,1);
  dy = gearSpeed*random(-1,1);
  while (dx == 0 || dy == 0){
    dx = gearSpeed*random(-1,1);
    dy = gearSpeed*random(-1,1);
  }
  dCD = 4; 
  
  thirdE = new Gears(0, 0, height/2.25f, height/2.25f, width/12, height/20, 45, -8.0f, 0, 0, 4, 110, 10, 180, 255);
  thirdF = new Gears(0, 0, height/5, height/5, width/12, height/20, 60, -6.0f, 0, 0, 4, 21, 18, 180, 125);
  
  frameRate(48);
  
  //noLoop();
}

public void draw(){
  background(0,0,8);
  
  pushMatrix();
  translate(width/2, height*.75f);
  thirdE.display();
  thirdF.display();
  popMatrix();

  pushMatrix();
  translate(width/2.2f, height/12);
  first.display();
  popMatrix();
  
  
  pushMatrix();
  thirdA.display();
  thirdB.display();
  popMatrix();
  
  
  pushMatrix();
    cx += dx;
    cy += dy;
    if (cy < height/100){
      //dx = width/20*random(-1,1);
      dy = gearSpeed*random(.1f,1);
    }
    
    if (cx > width*.8f){
      dx = gearSpeed*random(-1,-.1f);
      //dy = width/20*random(-1,1);
    }
  
    if (cy > height*.8f){
      //dx = width/20*random(-1,1);
      dy = gearSpeed*random(-1,-0.1f);
    }
    
    if (cx < width/100){
      dx = gearSpeed*random(0.1f,1);
      //dy = width/20*random(-1,1);
    }
  translate(cx, cy);   
  thirdC.display();
  thirdD.display();
  popMatrix();
  
  
  
  fill(255, 0, 0);
  pushMatrix();
  translate(width/2.2f, height/12);
  scnd.display();
  popMatrix();

  
  
}





class Gears{
  float x, y, w, h;
  float dX, dY;
  float theta; 
  float dTheta;
  float times; 
  float rd, grn, bl;
  float trans;
  Gears ul, ur, ll, lr;
  Gears um, lm;
  int rectIndex;
  int gearIndex;

  Gears(float x, float y, float h, float w, 
              float dX, float dY, float theta, float dTheta, float times, int rectIndex, int gearIndex, float rd, float grn, float bl, float trans){
     this.x = x;
     this.y = y;
     this.w = w;
     this.h = h;
     this.dX = dX;
     this.dY = dY;
     this.theta = theta;
     this.dTheta = dTheta;
     this.times = times;
     this.rectIndex = rectIndex;
     this.gearIndex = gearIndex;
     this.rd = rd;
     this.grn = grn;
     this.bl = bl;
     this.trans = trans;
     if (times <= MAXTIMES){
       //red becomes 80% it's value, bluebecomes 90% of it's value, and blue and grn are swapped
       // x, y, h, w, dX, dY, theta, dTheta, times, rectIndex, gearIndex, rd, grn, bl
        if (times != MAXTIMES && gearIndex==1){
          if (rectIndex == 20){
            ul = new Gears(0, 0, 6.5f*w, 6.5f*w, -1*w/2, -1*h/2, theta*2, -1.25f*dTheta, 0, 0, 4, 0, 205, 254, trans*.35f);
          }
          else{ 
            ul = new Gears(0, 0, h/2.4f, w/2, -1*w/2, -1*h/2, theta*2, dTheta*2, times+1, (rectIndex*4)+1, gearIndex, rd*.8f, grn*.8f, bl, trans);
          }

          ur = new Gears(0, 0, h/2.4f, w/2, w/2, -1*h/2, theta*2, dTheta*2, times+1, (rectIndex*4)+2, gearIndex,  rd*.8f, grn*.8f, bl, trans);
          lr = new Gears(0, 0, h/2.4f, w/2, w/2, h/2, theta*2, dTheta*2, times+1, (rectIndex*4)+3, gearIndex,  rd*.8f, grn*.8f, bl, trans);
          ll = new Gears(0, 0, h/2.4f, w/2, -1*w/2, h/2, theta*2, dTheta*2, times+1, (rectIndex*4)+4, gearIndex,  rd*.8f, grn*.8f, bl, trans);
          
        }
        else if (times != MAXTIMES && gearIndex==2){
          ul = new Gears(0, 0, h/2, w/2, -1*w/2, -1*h/2, theta*2, dTheta*2, times+1, (rectIndex*4)+1, gearIndex, rd*.8f, bl*.7f, grn, trans*.9f);
          ur = new Gears(0, 0, h/2, w/2, w/2, -1*h/2, theta*2, dTheta*2, times+1, (rectIndex*4)+2, gearIndex, rd*.8f, bl*.7f, grn, trans*.9f); 
          lr = new Gears(0, 0, h/2, w/2, w/2, h/2, theta*2, dTheta*2, times+1, (rectIndex*4)+3, gearIndex, rd*.8f, bl*.7f, grn, trans*.9f);
          ll = new Gears(0, 0, h/2, w/2, -1*w/2, h/2, theta*2, dTheta*2, times+1, (rectIndex*4)+4, gearIndex, rd*.8f, bl*.7f, grn, trans*.9f);
//          if (rectIndex == 16){
//            lm = new Gears(0, 0, h/2, w*8, w*-4, -1*h/2, theta*2, dTheta/5, 2, (rectIndex*4)+2, 5, rd/2, grn/4, bl*4, trans); 
//            um = new Gears(0, 0, h/2, w*8, w*-4, h/2, theta*2, dTheta/5, 2, (rectIndex*4)+3, 5, rd/2, grn/4, bl*4, trans);
//          }
          
        }

        else if (times != MAXTIMES && gearIndex == 4){
          
          ul = new Gears(0, 0, h/2, w/2, -1*w/2, -1*h/2, theta*2, dTheta*.6f, times+1, (rectIndex*4)+1, gearIndex, rd/2, grn/2, bl-40, trans*.85f);
          ur = new Gears(0, 0, h/2, w/2, w/2, -1*h/2, theta*2, dTheta*.6f, times+1, (rectIndex*4)+2, gearIndex, rd/2, grn/2, bl-40, trans*.85f); 
          lr = new Gears(0, 0, h/2, w/2, w/2, h/2, theta*2, dTheta*.6f, times+1, (rectIndex*4)+3, gearIndex, rd/2, grn/2, bl-40, trans*.85f);
          ll = new Gears(0, 0, h/2, w/2, -1*w/2, h/2, theta*2, dTheta*.6f, times+1, (rectIndex*4)+4, gearIndex, rd/2, grn/2, bl-40, trans*.85f);
          
          if (rectIndex == 16){
            //lm = new Gears(0, 0, h/2.5, w*6.5, w*-3.25, -1*h/2, theta*2, dTheta*1.2, 3, (rectIndex*4)+2, 5, rd/2, grn*4, bl/2, 255); 
            //um = new Gears(0, 0, h/2.5, w*6.5, w*-3.25, h/2, theta*2, dTheta*1.2, 3, (rectIndex*4)+3, 5, rd/2, grn*4, bl/2, 255);
          }
          
        }
        else if (times != MAXTIMES && gearIndex == 5){
            lm = new Gears(0, 0, h/2,  w, -1*w, -1*h/2, theta*2, dTheta*-1, times+1, (rectIndex*4)+2, 6, rd/2, grn*4, bl/2, trans*.6f); 
            um = new Gears(0, 0, h/2,  w, -w, h/2, theta*2, dTheta, times+1, (rectIndex*4)+3, 6, rd/2, grn*4, bl/2, trans*.677f);
        }
        else if (times != MAXTIMES && gearIndex == 6){
            lm = new Gears(0, 0, h/2,  w, -1*w/2, -1*h/2, theta*2, dTheta*-2, times+1, (rectIndex*4)+2, 6, rd/2, grn*4, bl/2, trans*.6f); 
            um = new Gears(0, 0, h/2,  w, -1*w/2, h/2, theta*2, dTheta*2, times+1, (rectIndex*4)+3, 6, rd/2, grn*4, bl/2, trans*.6f);
        }
        else if (times != MAXTIMES && gearIndex==3){
          ul = new Gears(0, 0, h/2, w/2, -1*w/2, -1*h/2, theta*2, dTheta*2, times+1, (rectIndex*4)+1, gearIndex, rd*.8f, bl*.7f, grn, trans*.9f);
          ur = new Gears(0, 0, h/2, w/2, w/2, -1*h/2, theta*2, dTheta*2, times+1, (rectIndex*4)+2, gearIndex, rd*.8f, bl*.7f, grn, trans*.9f); 
          lr = new Gears(0, 0, h/2, w/2, w/2, h/2, theta*2, dTheta*2, times+1, (rectIndex*4)+3, gearIndex, rd*.8f, bl*.7f, grn, trans*.9f);
          ll = new Gears(0, 0, h/2, w/2, -1*w/2, h/2, theta*2, dTheta*2, times+1, (rectIndex*4)+4, gearIndex, rd*.8f, bl*.7f, grn, trans*.9f);
          
        }
     }
     else{ //If no child exists, make null
        ll = null;
        ul = null;
        lr = null;
        ur = null; 
     }
  }
  
  public void display(){
    pushMatrix();
      if (this.gearIndex != 5){
        translate(dX, dY);
      }     
      rotate(radians(theta));
        //TODO change to check for null, then display, or don't display 
        if (ll != null && ll.times <= maxDisplay){
          ll.display();
        }
        if (ul != null && ul.times <= maxDisplay){
          ul.display();
        }
        if (lr != null && lr.times <= maxDisplay){
          lr.display();
        }
        if (ur != null && ur.times <= maxDisplay){
          ur.display();
        }
        if (um != null && um.times <= maxDisplay){
          um.display();
        }
        if (lm != null && lm.times <= maxDisplay){
          lm.display();
        }
      if (bl == 255 || (gearIndex == 3 && rectIndex == 0)){
       fill(rd, grn, bl, 130); 
      }
      else{
        fill(rd, grn, bl, trans);
      }
      //fill(0, random(8, 255), random(8, 255)); 
      if (this.gearIndex == 5){
        translate(dX, dY);
      } 
      rect(x, y, w, h);
      popMatrix();
      
      theta += dTheta;
      if (theta >= 360){
        theta -= 360;
      }
      else if (theta <= 0){
        theta += 360;
      }
      
      
    
  }   
  
}



  static public void main(String args[]) {
    PApplet.main(new String[] { "--bgcolor=#F0F0F0", "GearsApp" });
  }
}
