#!/usr/bin/env bash

echo "> FE 배포"
sudo cp -rf /home/ubuntu/fe/dist/* /var/www/html
