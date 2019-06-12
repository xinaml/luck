#!bin/bash
service docker start
docker start mysql
docker start redis
docker start elasticsearch
docker start kibana
docker start logstash
