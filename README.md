# Advent of Code — Java

A hands-on Java learning project built around [Advent of Code](https://adventofcode.com) — an annual series of programming puzzles released every December.

Each puzzle is solved in Java, and the whole thing is wrapped in a small **terminal (TUI)** application (built with [Lanterna](https://github.com/mabe02/lanterna)) so you can pick any year and day and run the solution interactively — right in your terminal.

---

## What it does

- Launches a full-screen terminal UI where you select an **Advent of Code year** and **day**
- Hitting **Solve** dynamically loads the matching solution class via reflection and runs both Part 1 and Part 2 on a background thread
- Results (including execution time) stream into an in-app scrolling "terminal" pane
- **Clear** empties the pane; **Quit** exits

```
┌ Advent of Code Solver ─────────────────────────────┐
│ Year: [2015▼]  Day: [1▼]  <Solve> <Clear> <Quit>   │
│┌ Terminal ─────────────────────────────────────────┐│
││ [09:14:01] Application started. Select a year...   ││
││ [09:14:05] The solution for year 2015, day 1 ...   ││
│└───────────────────────────────────────────────────┘│
└──────────────────────────────────────────────────────┘
```

## Project structure

```
src/
├── Main.java                          # Entry point — builds the Lanterna terminal UI
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
        └── day6/ ...
```

Solutions stay completely UI-agnostic: they only ever receive a `Consumer<String>` sink,
so the same solution runs unchanged regardless of the front-end.

To add a new puzzle, create `src/advents/year<YYYY>/day<D>/Solution.java` extending `BaseSolution` and drop an `input.txt` alongside it.

## Requirements

- **JDK 26** (this project compiles with `maven.compiler.release=26`)
- Maven — **not required globally**; use the bundled wrapper `./mvnw` (it downloads Maven on first run)

If you don't have JDK 26, install it without root via a tarball, e.g. Eclipse Temurin:

```bash
mkdir -p ~/.local/jdks
curl -sSL -o /tmp/jdk26.tar.gz \
  "https://api.adoptium.net/v3/binary/latest/26/ga/linux/x64/jdk/hotspot/normal/eclipse"
tar -xzf /tmp/jdk26.tar.gz -C ~/.local/jdks
export JAVA_HOME="$HOME/.local/jdks/jdk-26.0.1+8"   # adjust to the extracted folder name
```

> The build (and the `./mvnw` wrapper) uses `JAVA_HOME`. Make sure it points at JDK 26
> before building — otherwise an older default `java` on your `PATH` will fail on `release 26`.

## Build & run

```bash
# Compile
./mvnw compile

# Run the terminal UI directly (recommended during development)
./mvnw exec:java

# Package into a self-contained (fat) JAR, then run it
./mvnw package
java -jar target/advent-of-code-java-1.0-SNAPSHOT.jar
```

> Puzzle input files are read from relative paths (e.g. `src/advents/year2015/day1/input.txt`),
> so always run from the project root.

## Technologies

- **Java 26** — core language
- **[Lanterna](https://github.com/mabe02/lanterna)** — terminal (TUI) toolkit
- **Reflection** — dynamic solution dispatch (`Class.forName`)
- **Maven** (via the `./mvnw` wrapper) — build and dependency management
- **maven-shade-plugin** — bundles Lanterna into a runnable fat JAR
