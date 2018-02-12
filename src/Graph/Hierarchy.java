package Graphs;

import java.util.ArrayList;
import java.util.List;

/*
 * This is an optional interface that
 * I use to write different data structures with hierarchycal structure.
 * Graph, Tree, HashTable ....
 * It has the methods that for me are must have for each such structure.
 */
public interface Hierarchy<T> {
    
    //	Ability to Add/Remove/Replace Elements
    public void Add(T item);
    public void Remove(T item);
    public void Replace(T old,T nw);
    public boolean Contains(T src);
    public int NumberOfElements();

    //	Connecting Elements.	//
     public void Connect(T src,T dst);
     public void Connect(T src,T dst,double value);
     public void BiConnect(T src,T dst);
     public void BiConnect(T src,T dst,double value);
     
     //	Dis-Connecting Elements. //
     public void DisConnect(T src,T dst);
     public void BiDisConnect(T src,T dst);
     
     //	Get some simple information (GetChilds/GetParents) //
     public List<T> RefferedBy(T src);
     public List<T> ReffersTo(T src);
     public T FirstChild(T src);
     
     //	Check if those items are connected. //
     public boolean AreConnected(T src,T dst);
     public boolean AreConnected(T src,T dst,int depth);
     
     /*
      * Path/Connection	Finding.
      * Mostly used in graphs.
      * Can be omited for other data structures.    
     public List<T> FindPath(T src,T dst);
     public List<T> FindPath(T src,T dst,int depth);
     public double GetWeight(T src,T dst);
     public double GetWeight(List<T> list);
     public List<T> FindLithestPath(T src,T dst);
     public List<T> FindHeaviestPath(T src,T dst);
     //	Minimum	Spanning    Tree -> Used for graph.
     public List<T> FindMST(T src,T dst);
     */
     //	Debugging option	//
     public void PrintGraph();
}
