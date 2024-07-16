import RPi.GPIO as GPIO
import time
import sys

GPIO.setwarnings(False)
GPIO.setmode(GPIO.BOARD)
GPIO.cleanup()

trigPin = int(sys.argv[1])
echoPin = int(sys.argv[2])

GPIO.setup(trigPin, GPIO.OUT)
GPIO.setup(echoPin, GPIO.IN)

try:
    GPIO.output(trigPin, 0)
    time.sleep(2E-6)
    GPIO.output(trigPin, 1)
    time.sleep(10E-6)
    GPIO.output(trigPin, 0)
    waitTime = 0
    while GPIO.input(echoPin) == 0:
        if(waitTime > 10000):
            GPIO.cleanup()
            exit(1)
        waitTime += 1
        pass
    echoStartTime = time.time()
    waitTime = 0
    while GPIO.input(echoPin) == 1:
        if(waitTime > 100000000):
            GPIO.cleanup()
            exit(2)
        waitTime += 1
        pass
    echoStopTime = time.time()
    pingTravelTime = echoStopTime - echoStartTime
    dist_cm = (pingTravelTime*34444)/2
    print(dist_cm)
except Exception:
    GPIO.cleanup()
    exit(3)
GPIO.cleanup()