#!/bin/bash

echo "Building jar from files present in folder"

javac -d build src/*.java

jar --create --file Conjuga.jar --main-class Conjuga -C build .
