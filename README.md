# Shortest Path Algorithm with Dijkstra and Bellman-Ford

This is a Java program that implements two shortest path algorithms: **Dijkstra** and **Bellman-Ford**. The program allows you to calculate the shortest paths and costs between network nodes, taking user input for the costs between the nodes.

## Features

- Allows the user to input the costs between the network links.
- Calculates the shortest path and minimum cost between nodes using:
  - **Dijkstra's Algorithm**: for non-negative edge weights.
  - **Bellman-Ford Algorithm**: supports negative edge weights and detects negative cycles.
- Provides a comparison of the costs between nodes using both algorithms.

## How the Program Works

1. **Cost Input**: The user is prompted to input the cost between connected nodes in a network.
2. **Shortest Path Calculation**: The program computes the shortest path from the user-selected start node to all other nodes using both Dijkstra and Bellman-Ford algorithms.
3. **Display Results**: The shortest path and costs are displayed for each node.

## Program Structure

- The program initializes an empty graph and prompts the user to input the costs for each link between the nodes.
- Two algorithms are used:
  - **Dijkstra**: Finds the shortest path by maintaining a priority queue for nodes.
  - **Bellman-Ford**: Iteratively relaxes the edges of the graph to find the shortest path.
- Results are displayed in a formatted table showing the calculated costs for each node and the best route.

## How to Use

### Prerequisites

- **Java Development Kit (JDK)** installed.
- Basic knowledge of how to run a Java program.

### Running the Program

1. **Clone the repository** (or download the code):

   ```bash
   git clone https://github.com/yourusername/Shortest-Path-Algorithm.git
   ```

2. **Compile the Java file**:

   ```bash
   javac Source_Code.java
   ```

3. **Run the program**:

   ```bash
   java Source_Code
   ```

4. **Input costs**:

   The program will ask you to input the cost for each pair of connected nodes. You must enter a non-negative integer value.

5. **Select the starting node**:

   The program will ask you to select a starting node from which to calculate the shortest path to all other nodes.

### Example Input and Output

```
Enter cost from 1 link to 2: 10
Enter cost from 1 link to 4: 15
Enter cost from 2 link to 3: 12
Enter cost from 2 link to 4: 10
Enter cost from 3 link to 5: 5
Enter cost from 3 link to 6: 10
Enter cost from 4 link to 5: 10
Enter cost from 5 link to 6: 5
Enter start link: 1

| From link to link | Cost (Bellman-Ford) | Cost (Dijkstra) | Best Route          |
|-------------------|---------------------|-----------------|---------------------|
| 1 -> 1            | 0                   | 0               | 1 -> 1              |
| 1 -> 2            | 10                  | 10              | 1 -> 2              |
| 1 -> 3            | 22                  | 22              | 1 -> 2 -> 3         |
| 1 -> 4            | 15                  | 15              | 1 -> 4              |
| 1 -> 5            | 25                  | 25              | 1 -> 4 -> 5         |
| 1 -> 6            | 30                  | 30              | 1 -> 4 -> 5 -> 6    |
```

### Explanation of Results

- **From link to link**: Displays the source node to the destination node.
- **Cost (Bellman-Ford)**: Shows the cost calculated using the Bellman-Ford algorithm.
- **Cost (Dijkstra)**: Shows the cost calculated using Dijkstra’s algorithm.
- **Best Route**: Displays the path from the source node to the destination node using the shortest route.
