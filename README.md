# Maze Pathfinding Algorithm Analyzer

A comprehensive Java implementation comparing pathfinding algorithms (DFS, BFS, A*) on 2D mazes with statistical analysis of performance across varying obstacle densities.

## Overview

This project analyzes the performance characteristics of three fundamental pathfinding algorithms through extensive maze simulations. Using custom data structures and 10,000+ test cases per configuration, the study reveals how obstacle density affects algorithm efficiency and solution quality.

## Key Features

- **Multiple Pathfinding Algorithms**: DFS, BFS, A*, and custom Human-Inspired Search
- **Custom Data Structures**: Stack, Queue, Heap, Priority Queue, and LinkedList implementations
- **Statistical Analysis**: Large-scale simulations (10,000+ mazes) with performance metrics
- **Visual Interface**: Real-time maze generation and pathfinding visualization
- **Configurable Parameters**: Variable maze sizes (30x30, 50x50) and obstacle densities (0.00-0.99)
- **Advanced Heuristics**: Manhattan and Euclidean distance functions with weighted optimization

## Algorithms Implemented

### Core Pathfinding
1. **Depth-First Search (DFS)**: Stack-based exploration following single paths
2. **Breadth-First Search (BFS)**: Queue-based level-by-level exploration  
3. **A* Search**: Priority queue with Manhattan distance heuristic
4. **Human-Inspired Search**: Enhanced A* with neighbor quality assessment

### Data Structures
- **Stack**: LIFO structure for DFS backtracking
- **Queue**: FIFO structure for BFS level exploration
- **Heap**: Binary heap for priority queue operations
- **Priority Queue**: Optimized A* cell selection
- **LinkedList**: Custom implementation with iterator support

## Key Findings

### Maze Solvability Analysis
Statistical analysis of 10,000 mazes revealed a **sigmoid-like probability curve**:
- **Low density (0.0-0.3)**: High solvability (~90-100%)
- **Critical range (0.3-0.6)**: Rapid probability decline
- **High density (0.6+)**: Near-zero solvability (~0-5%)

### Path Length Comparison
| Algorithm | Path Quality | Performance Notes |
|-----------|--------------|------------------|
| **BFS** | Optimal (shortest path) | Guaranteed minimum distance |
| **A*** | Optimal (shortest path) | Matches BFS optimality |
| **DFS** | Suboptimal | Significantly longer paths |

### Exploration Efficiency
Cells explored before finding solution:
- **A* (optimized)**: Most efficient - minimal exploration
- **BFS**: Moderate efficiency - systematic exploration
- **DFS**: Least efficient - often explores unnecessary paths
- **Human Search**: Superior performance through intelligent neighbor selection

## Advanced Optimizations

### Weighted A* Implementation
```java
// Optimized heuristic function
priority = 5.0 * euclideanDistance + pathCost + 0.5 * neighborQuality
```

### Human-Inspired Enhancements
- **Good/Bad Neighbors**: Cells evaluated based on surrounding free space
- **Euclidean Distance**: More accurate distance estimation than Manhattan
- **Weighted Heuristics**: 5x weight for distance estimation, 0.5x for neighbor quality

## Performance Results

### Algorithm Efficiency Ranking
1. **Human-Inspired A***: Best performance across all densities
2. **Weighted A***: Nearly matches human search with proper weight factors
3. **Standard A***: Good performance but suboptimal weighting
4. **BFS**: Reliable but explores more cells than necessary
5. **DFS**: Poorest performance due to non-optimal exploration

### Critical Density Insights
- **Optimal performance range**: 0.1-0.3 obstacle density
- **Performance degradation**: Begins around 0.4 density
- **Algorithm convergence**: All algorithms show similar behavior at extreme densities

## Technical Implementation

### Core Classes
- **Maze.java**: Grid representation with obstacle generation
- **Cell.java**: Individual cell with type and pathfinding metadata
- **AbstractMazeSearch.java**: Base class for all pathfinding algorithms
- **MazeSearchDisplay.java**: Visualization interface

### Simulation Engine
- **Exploration.java**: Main analysis runner for standard algorithms
- **MazeHumanSearch.java**: Extended analysis with optimized implementations

## Running the Analysis

### Standard Algorithm Comparison
```bash
javac Exploration.java
java Exploration
```

### Advanced Analysis with Extensions
```bash
javac MazeHumanSearch.java
java MazeHumanSearch
```

## Research Contributions

1. **Heuristic Optimization**: Demonstrated significant improvement through weighted distance functions
2. **Density Analysis**: Quantified relationship between obstacle density and algorithm performance  
3. **Human-Inspired AI**: Successfully integrated intuitive pathfinding strategies into algorithmic approach
4. **Statistical Validation**: Large-scale empirical analysis with 10,000+ test cases per configuration

## Technical Highlights

- **Custom ADT Implementation**: All data structures built from scratch for educational completeness
- **Performance Profiling**: Comprehensive metrics including path length, cells explored, and success rates
- **Algorithm Hybridization**: Novel combination of A* with human-intuitive neighbor evaluation
- **Scalable Architecture**: Supports variable maze sizes and density configurations

## Author

**Aayan Shah**  
Computer Science & Physics Student  
[GitHub Profile](https://github.com/aayans314)
