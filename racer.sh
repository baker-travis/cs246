#!/bin/sh

# clean up on ^C to terminate script
trap 'pkill java; rm -f file.*; exit' 0 1 2 3 15

java -cp . Creator &

# give the Creator a two-second head start
sleep 2

java -cp . Destroyer &

while true
do
   sleep 1
   find . -name "file.*" | sort
   echo "----------------------"
done
