#!/bin/sh

java -XX:+UseZGC -cp target/IngKonkurs-1.0-SNAPSHOT.jar ingkonkurs.config.HttpServer

