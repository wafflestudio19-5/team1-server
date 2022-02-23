#!/usr/bin/env bash

cp /home/ec2-user/app/deploy/*.jar /home/ec2-user/app/

sudo systemctl reload nginx
sudo systemctl reload initapp
