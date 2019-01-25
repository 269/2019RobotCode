#include <FastLED.h>

// How many leds in your strip?
#define NUM_LEDS 46
#define DATA_PIN 3

// Define the array of leds
CRGB leds[NUM_LEDS];

void setup() { 
      Serial.begin(9600);
      FastLED.addLeds<WS2811, DATA_PIN, GRB>(leds, NUM_LEDS);
      leds[0] = CRGB::Black;\
      FastLED.show();
}
void red(){
  leds[0] = CRGB::Red;
      FastLED.show();
}
void blue(){
  Serial.println("HEY THIS IS AN LED PROBLEM");
  leds[0] = CRGB::Blue;
      FastLED.show();
}
void off(){
  leds[0] = CRGB::Black;
      FastLED.show();
}
void green(){
  leds[0] = CRGB::Green;
      FastLED.show();
}
void pink(){
  leds[0] = CRGB::Pink;
      FastLED.show();
}
void handleComms(){
  if(Serial.available() > 0){
    String roboRio = Serial.readString();
    Serial.println(roboRio);
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
    else if (roboRio == "pink"){
      pink();
    }
    roboRio = "AHAAHAHHAHHA";
  }
}
void loop() { 
  // Turn the LED on, then pause
handleComms();
}
