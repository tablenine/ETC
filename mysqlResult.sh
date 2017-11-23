#!/bin/sh
filePath="/tmp/test.txt"
mysql -uroot -psystem ssbr -N -s -e "select '', id, ', ', mail, '' from user where mail is not null and mail != '';" | sed "s/'/\'/;s/\t//g" > ${filePath}
