import RPi.GPIO as GPIO
import time
import sys

GPIO.setwarnings(False)
GPIO.setmode(GPIO.BOARD)
GPIO.cleanup()

led = int(sys.argv[1])

GPIO.setup(led, GPIO.OUT)

GPIO.output(led, 1)
time.sleep(2)
GPIO.output(led, 0)
GPIO.cleanup()