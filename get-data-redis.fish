#!/usr/bin/env fish
docker exec -it my_redis redis-cli -h localhost get "hello::SimpleKey []"

