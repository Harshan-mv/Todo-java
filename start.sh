#!/bin/bash
java -Dspring.profiles.active=prod -Dserver.port=$PORT -jar target/*.jar