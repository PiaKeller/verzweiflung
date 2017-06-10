import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import gab.opencv.*; 
import processing.video.*; 
import java.awt.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class verzweiflung extends PApplet {





float factor;

int pixellationFactor = 2;
Capture video;
OpenCV opencv;

public void setup() {
  
  video = new Capture(this, 640/2, 480/2);
  opencv = new OpenCV(this, 640/2, 480/2);
  opencv.loadCascade(OpenCV.CASCADE_FRONTALFACE);

  video.start();

  rectMode(CENTER);
}

public void draw() {
  background(255);
  scale(2);
  opencv.loadImage(video);

  image(video, 0, 0);
  loadPixels();
  background(255);

  noFill();
  noStroke();
  Rectangle[] faces = opencv.detect();

  if (faces.length > 0){
    pixellationFactor = faces[0].width/5;
    if(pixellationFactor % 2 > 0){
      pixellationFactor += 1;
    }
    println("Face detected, setting pixellationFactor to: " + pixellationFactor);
  }

  for (int i = 0; i < (width/pixellationFactor); i++){
    for(int j = 0; j < (height/pixellationFactor); j++){
      fill(pixels[j*video.width*pixellationFactor + i*pixellationFactor]);
      rect(i*pixellationFactor, j*pixellationFactor, pixellationFactor, pixellationFactor);
    }

  }
}

public void captureEvent(Capture c) {
  c.read();
}
  public void settings() {  size(640, 480); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "verzweiflung" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
