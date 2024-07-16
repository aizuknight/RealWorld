import RPi.GPIO as GPIO
import sys
import time

GPIO.setwarnings(False)
GPIO.setmode(GPIO.BOARD)
GPIO.cleanup()

pin = int(sys.argv[1])
GPIO.setup(pin, GPIO.OUT)

GPIO.output(pin, 1)
time.sleep(int(sys.argv[2]))

GPIO.cleanup()