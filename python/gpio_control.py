import RPi.GPIO as GPIO
import sys

GPIO.setwarnings(False)
GPIO.setmode(GPIO.BOARD)
GPIO.cleanup()

sys.argv.remove(sys.argv[0])
pins = []
for i in range(sys.argv.__len__):
    pins.append(int(sys.argv[i]))

