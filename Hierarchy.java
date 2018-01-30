/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphs;

import numbers.test.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author root
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
     
     //	Path/Connection	Finding.    //
     public List<T> FindPath(T src,T dst);
     public List<T> FindPath(T src,T dst,int depth);
     public double GetWeight(T src,T dst);
     public double GetWeight(List<T> list);
     public List<T> FindLithestPath(T src,T dst);
     public List<T> FindHeaviestPath(T src,T dst);
     //	Minimum	Spanning    Tree -> Used for graph.
     //public List<T> FindMST(T src,T dst);
     
     //	Debugging option	//
     public void PrintGraph();
}
