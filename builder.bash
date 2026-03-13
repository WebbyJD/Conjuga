#!/bin/bash

echo "Building jar from files present in folder"

javac -d build src/Conjuga.java

jar --create --file Conjuga.jar --main-class src.Conjuga -C build .
