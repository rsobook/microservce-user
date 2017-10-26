#!/bin/bash
cd /usr/local/bin/kumuluzee/


if [[ -z "${JAR}" ]];
    then
        echo 'You need to pass jar name.'
    else
        echo 'Starting KumuluzEE .. \n'
        java -jar ${JAR}
fi

