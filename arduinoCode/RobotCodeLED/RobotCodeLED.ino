#include <FastLED.h>

// How many leds in your strip?
#define NUM_LEDS 46
#define DATA_PIN 3

// Define the array of leds
CRGB leds[NUM_LEDS];

void setup() { 
      Serial.begin(9600);
      FastLED.addLeds<WS2812B, DATA_PIN, RGB>(leds, NUM_LEDS);
}
void red(){
  leds[0] = CRGB::Red;
}
void blue(){
  leds[0] = CRGB::Blue;
}
void off(){
  leds[0] = CRGB::Black;
}
void handleComms(){
  if(Serial.available() > 0){
    String roboRio = Serial.readString();
    if(roboRio == "red"){
      red();
    }
    else if (roboRio == "blue"){
      blue();
    }
    else if (roboRio == "green"){
      green();
    }
    else if(roboRio == "off"){
      off();
    }
  }
}
void loop() { 
  // Turn the LED on, then pause
handleComms();
}
