#### Image EC2   

```sh
#!/bin/bash
sudo apt-get update
sudo apt-get upgrade -y
#sudo apt-get install nginx -y

apt install docker.io -y
systemctl status docker

curl -L https://github.com/docker/compose/releases/download/1.18.0/docker-compose-`uname -s`-`uname -m` -o /usr/local/bin/docker-compose

chmod +x /usr/local/bin/docker-compose

docker-compose --version
```

#### Install app instance 

