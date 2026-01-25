#!/bin/bash
sudo apt update -y
sudo apt update -y nginx
sudo systemctl start nginx
sudo ststemctl enable nginx
echo "Hello, world!" | sudo tee /var/html/index.html