import i2clcdb
import sys

i2clcdb.lcd_init()

message = ""
for i in range(1, len(sys.argv)):
    message += sys.argv[i] + " "

i2clcdb.lcd_string(message, i2clcdb.LCD_LINE_1)
print(message)
