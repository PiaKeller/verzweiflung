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

Capture video;
OpenCV opencv;

public void setup() {
  
  video = new Capture(this, 640/2, 480/2);
  opencv = new OpenCV(this, 640/2, 480/2);
  opencv.loadCascade(OpenCV.CASCADE_FRONTALFACE);

  video.start();
}

public void draw() {

  background(255);
  scale(2);
  opencv.loadImage(video);

  //image(video, 0, 0 );

  noFill();
  stroke(255);
  strokeWeight(3);
  Rectangle[] faces = opencv.detect();
  println(faces.length);

  for (int i = 0; i < faces.length; i++) {
    //println(faces[i].x + "," + faces[i].y);
    factor = map(faces[i].width, 60, 140, 0, 1);
    rect(faces[i].x, faces[i].y, faces[i].width, faces[i].height);
  }

  float size = map(factor, 0, 1, 60, 300);
  fill(0);
  println(size);
  rect(0, 0, size, size);
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
