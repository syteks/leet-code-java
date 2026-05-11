# Advent of Code — Java

A hands-on Java learning project built around [Advent of Code](https://adventofcode.com) — an annual series of programming puzzles released every December.

Each puzzle is solved in Java, and the whole thing is wrapped in a small Java Swing desktop application so you can pick any year and day and run the solution interactively.

---

## What it does

- Launches a Swing UI where you select an **Advent of Code year** and **day**
- Hitting **Solve** dynamically loads the matching solution class via reflection and runs both Part 1 and Part 2
- Results (including execution time) are printed to an in-app terminal

## Project structure

```
src/
├── Main.java                          # Entry point — builds the Swing window
├── SolutionFactory.java               # Loads the correct Solution class via reflection
└── advents/
    ├── interfaces/
    │   └── Solvable.java              # Interface every solution must implement
    ├── utils/
    │   ├── BaseSolution.java          # Abstract base class (calls solvePart1 + solvePart2)
    │   └── FileReader.java            # Utility for reading puzzle input files
    └── year2015/
        ├── day1/
        │   ├── Solution.java
        │   └── input.txt
        ├── day2/ ...
        └── day5/ ...
```

To add a new puzzle, create `src/advents/year<YYYY>/day<D>/Solution.java` extending `BaseSolution` and drop an `input.txt` alongside it.

## Requirements

- Java 17+
- Maven 3.6+

## Build & run

```bash
# Compile
mvn compile

# Run the Swing UI directly (recommended during development)
mvn exec:java

# Package into a runnable JAR, then run it
mvn package
java -jar target/advent-of-code-java-1.0-SNAPSHOT.jar
```

> The puzzle input files are read from relative paths (e.g. `src/advents/year2015/day1/input.txt`), so always run the commands from the project root.

## Technologies

- **Java 17** — core language
- **Java Swing** — desktop UI
- **Reflection** — dynamic solution dispatch (`Class.forName`)
- **Maven** — build and dependency management
