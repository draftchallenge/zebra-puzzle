# Zebra Puzzle

Based on this [puzzle](https://en.wikipedia.org/wiki/Zebra_puzzle) this code provides all possible solutions within the given constraints.

Code is mostly covered with unit tests therefore it was possible to refactor very easily.

Setup comes with ant tasks for dependency resolution (via ivy) and runs unit-tests with code coverage (via jacoco).
To use full potential take a look at [my continuous integration setup on github](https://github.com/draftchallenge/continuous-integration-setup).

## About the code

The best documentation is the code itself. Start by looking at following files:

  * build.xml : Ant tasks, start with project's default task
  * ivy.xml: Dependency managed with ivy. Default ones needed for unit tests
  * src/Main.java: Entry point for reading the code.
  * test/MainTest.java: Dummy test with checks output and was most useful for working with legacy code and heavy refactoring
