from RPi import GPIO
from firebase import firebase
import time
GPIO.setmode(GPIO.BCM)
GPIO.setwarnings(False)
trigger=14
echo=15
binleft="100"
x=15            #reference dust bin height
st=time.time()
GPIO.setup(trigger,GPIO.OUT)
GPIO.setup(echo,GPIO.IN)
firebase=firebase.FirebaseApplication(' ')      #Enter the firebase link
while(1):
    GPIO.output(trigger,1)
    time.sleep(0.01)
    GPIO.output(trigger,0)
    stime=time.time()
    etime=time.time()
    while GPIO.input(echo)==0:
        stime=time.time()
    while GPIO.input(echo)==1:
        etime=time.time()
    duration=etime-stime
    distance=(duration*34300)/2
    dis=int(distance)
    et=time.time()
    if(dis<x+1):
        per=(dis/x)*100
        per=int(per)
        rem=100-per
        t=et-st
        rate=rem/t
        binleft=per/rate
        binleft=int(binleft)
        print(rem)
        print(binleft)
        firebase.put('/','/bin',str(rem)+"%")
        firebase.put('/','/binleft',str(binleft)+"min")
        time.sleep(0.09)
    