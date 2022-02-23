#!/usr/bin/env bash

cp /home/ec2-user/app/deploy/*.jar /home/ec2-user/app/

sudo systemctl restart nginx
sudo systemctl restart initapp