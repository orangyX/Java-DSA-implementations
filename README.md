# Data-structures-and-algorithms-implementations
## Overview of repo
Concerned with implementing data structures, aiming to:
  - Study properties of varying data structures
  - Assess the time cimplexities for data structures and their operations, along with space complexities
  - Error conditions associated with the data structures
And more, which is likely yet to be listed.

## Contents
<pre>
1. Stack ADT
2. Map ADT
    2.1. Sets
    2.2. Hash tables
3. Queue ADT
    3.1. Circular queue
    3.2. Priority queue
4. Linked list ADT
    4.1. Singly linked list
    4.2. Doubly linked list
5. Tree ADT
    5.1. Binary trees
    5.2. AVL trees
6. Graph ADT
</pre>

## Properties of data structures
We consider the following in conjunction to specific data structures:
  - Hash tables:
      - hash functions, compression functions, and variations of comrpression functions
  - Queues:
      - front and rear points, acting to identify indices of enqueue and dequeue
  - Linked lists:
      - pointer assignments to add/delete nodes; consider the memory trade-off of DLL compared to SLL to yield predecessor nodes in O(1)
  - Binary trees:
      - height able to degenerate to O(n); how the AVL implementation solves this, yielding a particular height bound
  - Graph ADT:
      - Primm's and Kruskal's to yield MST's of a graph; BFS and DFS traversal to yield spanning trees, and Djikstra's, yielding the shortest path
