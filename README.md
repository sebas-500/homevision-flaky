# HomeVision - Flaky API


## About

This repo contains a client for the HomeVision flaky API. The client was built using kotlin, in order to run the app you need to install sdkman, java and execute a bunch of simple commands.

The installation instructions support mac os.

## Installation

If you don't have java installed follow the commands listed on [sdkman install](https://sdkman.io/install) to install sdkman first.

After you succesfuly installed sdkman, install open jdk with the following command

```bash
sdk install java 19.0.2-open
```

You can run the following commands to check everything was installed properly

```bash
java --version
```

The output should be something similar to
openjdk 19.0.2 2023-01-17
OpenJDK Runtime Environment (build 19.0.2+7-44)
OpenJDK 64-Bit Server VM (build 19.0.2+7-44, mixed mode, sharing)

```bash
which java
```
The output should be something similar to
/Users/$USERNAME/.sdkman/candidates/java/current/bin/java

## Running the app

Execute the following commands

To clone the git repository

```bash
git clone git@github.com:sebas-500/homevision-flaky.git
```

Build the application

```java
./gradle build
```

Unzip and run the app

```bash
tar -xf ./build/distributions/homevision-flaky-1.0-SNAPSHOT.tar
./homevision-flaky-1.0-SNAPSHOT/bin/homevision-flaky
```

If instead you want to run the test suite
```java
./gradle clean test
```

To review test report use the following command
```
open ./build/reports/tests/test/index.html
```

To review coverage report use the following command
```
open ./build/reports/jacoco/test/html/index.html
```
