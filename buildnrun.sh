#!/bin/sh

# Must be run from the main directory!
# Expects javac and java on the path.

CLASSPATH="Classes/:Classes/Integer Arithmatic/:Classes/Modular Arithmetic/"

# Build
javac -cp "$CLASSPATH" Classes/ProgramController.java

# Run
java -cp "$CLASSPATH" ProgramController

