import RPi.GPIO as GPIO
import sys

GPIO.setwarnings(False)
GPIO.setmode(GPIO.BOARD)
GPIO.cleanup()

pin = int(sys.argv[1])
GPIO.setup(pin, GPIO.IN)

print(GPIO.input(pin))

GPIO.cleanup()