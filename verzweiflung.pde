import gab.opencv.*;
import processing.video.*;
import java.awt.*;

float factor;

int pixellationFactor = 2;
Capture video;
OpenCV opencv;

void setup() {
  size(640, 480);
  video = new Capture(this, 640/2, 480/2);
  opencv = new OpenCV(this, 640/2, 480/2);
  opencv.loadCascade(OpenCV.CASCADE_FRONTALFACE);

  video.start();

  rectMode(CENTER);
}

void draw() {
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

void captureEvent(Capture c) {
  c.read();
}
