#!/bin/bash

cd ~/waffleoverflow

# update develop branch
git switch develop
git pull origin develop

# Delete application yaml
rm -rf src/main/resources/application.yml

# Update properties
cp ~/application.yml src/main/resources/application.yml

# gradle build
./gradlew bootJar

# Run application
sudo systemctl restart initapp
sudo systemctl restart nginx