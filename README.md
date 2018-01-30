Implementation of a graph in java.

Take a look at GraphTest.java for a ready example.
Take a look at GraphTable.java if you want the real programming thing.

Creating a Graph with nodes of type [Type] and passes the refference to [name].
GraphTable<[Type]> [name] = new GraphTable<[Type]>();

Alternative constructor which takes an array of type [Type] as elements in the graph.
Each element in the given array beccomes a graph element.
GraphTable<[Type]> [name] = new GraphTable<[Type]>([Array of type [Type]]);

Connectiong elements.
	[value] -> optional, default [1];
	Connects [src] to [dst]
[name].Connect([src],[dst],[value])

The same as executing
	[name].Connect([src],[dst],[value]);[name].Connect([dst],[src],[value]);
[name].BiConnect([src],[dst],[value])

Printing graph:
System.out.println([name].tosString())

