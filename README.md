# Chatebote

Simple AI chatbot client working inside Terminal and GUI.

## About
Chatebote is a small, focused Java-based chatbot implementation intended to run both in a terminal and as a lightweight GUI application.
It aims to be easy to build, inspect, and extend — suitable for learning, experimentation, and simple local use.

## Features
- Terminal-based interactive chat experience
- Minimal GUI front-end for desktop use
- Written in Java (100% of the repository)
- Simple, easy-to-read code suitable for modification

## Requirements
- Java 25 or later
- Gradle

## Build & Run
Unfortunately, `./gradlew run` doesn't work because of gradlew's logging system.
The below command works:
```
./gradlew assemble
java -jar build/libs/Chatebote.jar
```
And then you follow error messages to make the app work.

## License
This project is licensed under the GNU General Public License v3.0 — see the LICENSE file for details: [LICENSE](https://github.com/iasonasTan/Chatebote/blob/master/LICENSE)

## Contact
Author: Jason Tantaros (iasonasTan)  
For questions or collaboration, open an issue in this repository or make a pull request.
