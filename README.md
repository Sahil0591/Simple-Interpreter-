# SimpleLang

**SimpleLang** is a custom programming language built using ANTLR4. It features a complete grammar specification with support for typed variable declarations, expressions, functions, and control flow constructs.

## Features

- ANTLR4 grammar for parsing SimpleLang source code
- Support for:
  - Integer, Boolean, and Unit types
  - Variable declarations and initialization
  - Arithmetic and logical expressions
  - Conditional (`if-then-else`) and looping (`while`, `repeat-until`) constructs
  - Function declarations and calls
  - Basic I/O: `print`, `space`, and `newline`

## Grammar Overview

The grammar includes:

- `typed_idfr`: typed identifiers (e.g., `int x`)
- `init_expr`: variable declaration with assignment
- `exp`: arithmetic/logical expressions, blocks, and control statements
- `dec`: function definitions
- `body` and `block`: function and control flow bodies

## Getting Started

### Prerequisites

- [ANTLR4](https://www.antlr.org/)
- Java 8+

### Compile Grammar

```bash
antlr4 -Dlanguage=Java SimpleLang.g4
javac SimpleLang*.java
```
## Run the Interpreter

You should implement a visitor or listener to interpret the parse tree. Example usage:

```
ParseTree tree = parser.prog();
new MyLangVisitor().visit(tree);
```
```
/src           → Java source files for interpreter
/SimpleLang.g4 → ANTLR grammar definition
/test          → Test cases and example programs
```
