import RPi.GPIO as GPIO
import dht11
import sys

GPIO.setwarnings(False)
GPIO.setmode(GPIO.BOARD)
# GPIO.cleanup()

instance = dht11.DHT11(pin=int(sys.argv[1]))
try:
	waitTime = 0
	while True:
		if(waitTime > 1000):
			GPIO.cleanup()
			exit(1)
		waitTime += 1
		result = instance.read()
		if result.is_valid():
			print(result.temperature)
			print(result.humidity)
			GPIO.cleanup()
			exit(0)
except Exception:
	GPIO.cleanup()
	exit(2)