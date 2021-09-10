# Connected-Components
An algorithm to find the number of connected components within a given undirected graph.

Program takes in the path of test files. Test files form is as follows:
  - 1st number is number of edges in the graph
  - 2nd number is the number of vertices
  - each of the following lines represents an edge between a pair of numbered vertices

Algorithm utilizes an adjacency lsit data structure to hold the graph. Depth first search is used to keep track of discovered edges and number of components. 
