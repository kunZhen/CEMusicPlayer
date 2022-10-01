int favoriteButton = 0;
int favoriteVal = 0;

int regresarButton = 1;
int regresarVal = 0;

int playButton = 2;
int playVal = 0;

int nextButton = 3;
int nextVal = 0;

int cycleButton = 4;
int cycleVal = 0;

void setup() {
  pinMode(favoriteButton, INPUT);
  Serial.begin(9600);
  pinMode(regresarButton, INPUT);
  Serial.begin(9600);
  pinMode(playButton, INPUT);
  Serial.begin(9600);
  pinMode(nextButton, INPUT);
  Serial.begin(9600);
  pinMode(nextButton, INPUT);
  Serial.begin(9600);
  pinMode(cycleButton, INPUT);
  Serial.begin(9600);
}

void loop() {
  favoriteVal = digitalRead(favoriteButton);
  if (favoriteVal == LOW){
    Serial.print("f");
    delay(1000);
    }  
  regresarVal = digitalRead(regresarButton);
  if (regresarVal == LOW){
    Serial.print("r");
    delay(1000);
    }  
  playVal = digitalRead(playButton);
  if (playVal == LOW){
    Serial.print("p");
    delay(1000);
    }
  nextVal = digitalRead(nextButton);
  if (nextVal == LOW){
    Serial.print("n");
    delay(1000);
    } 
  cycleVal = digitalRead(cycleButton);
  if (cycleVal == LOW){
    Serial.print("c");
    delay(1000);
    }
}
