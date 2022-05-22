# Automation engineering challenge

## Pipelines

| Build                   | Status                                                                                                                                                                                                         |
|-------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Run UI Smoke Tests      | [![Run UI Smoke Tests](https://github.com/isandratskiy/ae-challenge/actions/workflows/smoke_test.yml/badge.svg)](https://github.com/isandratskiy/ae-challenge/actions/workflows/smoke_test.yml)                |
| Run UI Regression Tests | [![Run UI Regression Tests](https://github.com/isandratskiy/ae-challenge/actions/workflows/regression_test.yml/badge.svg)](https://github.com/isandratskiy/ae-challenge/actions/workflows/regression_test.yml) |

## Project Structure

```
└── src
    ├── main
    │   ├── java
    │   │   └── io
    │   │       └── sandratskyi
    │   │           └── challenge
    │   │               ├── config
    │   │               │   └── Configuration.java
    │   │               ├── fragments
    │   │               │   └── Carousel.java
    │   │               ├── pages
    │   │               │   ├── HomePage.java
    │   │               │   └── Page.java
    │   │               └── utils
    │   │                   └── SelenideElementUtils.java
    │   └── resources
    └── test
        ├── java
        │   ├── CarouselTest.java
        │   └── extension
        │       ├── BaseSetup.java
        │       ├── Regression.java
        │       ├── SetupExtension.java
        │       └── Smoke.java
        └── resources

```

### Dependencies

| Library name | Library version |
|--------------|-----------------|
| java         | 11              |
| gradle       | 7.4             |
| junit 5      | 5.8.2           |
| selenide     | 6.5.0           |

## How to run tests

**Run tests command:**

```sh
./gradlew clean test
```

**Run smoke tests only:**

```sh
./gradlew clean smoke
```

**Run regression test only:**

```sh
./gradlew clean regression
```

## Configuration

* All configuration params loading automatically from - Configuration class
* You can change site via LOCALIZATION environment variable

```sh
LOCALIZATION=es ./gradlew clean smoke
```
