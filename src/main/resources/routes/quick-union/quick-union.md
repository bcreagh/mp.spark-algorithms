# Quick Union

Quick Union is an approach to solving the dynamic connectivity problem. The dynamic connectivity problem describes a situation where we have many nodes. Some of them are connected, some of them are not. The problem is, how do we find out if two nodes are connected?

I've used a vague term like "node" because the nodes could be anything:

* In a social network, the nodes would be people. If two people are connected, it means they are friends.
* In a computer network, nodes could be computers. If they are connected, then they can communicate with each other.
* The nodes could be streets, and we might want to know if it's possible to drive from one street to another.

In this Quick Union implementation, nodes are simply represented by integers. This is done for my own convenience. It has two functions:

* ```union()```
* ```connected()```

The ```union()``` function connects two nodes. The ```connected()``` function checks to see if two nodes are connected

The nodes are stored in an array, but they are organised in tree structures. The index of each item in the array represents the node
itself. The value stored in the array at each index represents the parent of the node in its tree.
Every time we use ```union()``` to connect two nodes, we join the two trees that the nodes belong to.
When we use ```connected()``` to check if two nodes are connected, we simply find the root node of each tree. If the two root nodes
are the same, then we know that the two nodes are in the same tree. If the two nodes are in the same tree, they must be connected!

To try it out, click the "Try it" button. The input must be in JSON format, and should look something like this:

```
{
    "totalNodes": 10,
    "union": [
        [2, 5],
        [3, 6],
        [5, 6]
    ],
    "connected": [
        [2, 6],
        [3, 9]
    ]
}
```

```totalNodes``` represents the total number of nodes that you want to create.
Each item in ```union``` represents two nodes that you wish to connect.
Each item in ```connected``` represents two nodes that should be checked to see if they are connected.


As always, I don't have much time to do error checking on these so don't try too hard to break it :)

Here is an analysis an the different functions (n is the number of nodes):

* QuickUnion constructor: O(n)

* ```union()```: O(log(n)

* ```connected()```: O(log(n))

The log(n) performance for ```union()``` and ```connected()``` are achieved because this is a Weighted Quick Union. When we ```union()``` two nodes together, the algorithm checks which node belongs to the bigger tree. The root node of the smaller tree becomes the child of the root node of the bigger tree.

The key to the log(n) performance is that any given node will always be less than lg(n) away from its root node. This is because ```union()``` can only make a node move 1 extra node away from its root node because it has been added to a new tree. But if it has been added to a new tree, then it must be in the smaller tree. Therefore, its tree size must at lease double as a result of the ```union()```. If there are n nodes, then it is only possible to double lg(n) times. Therefore, a node can never be more than lg(n) away from its root node.