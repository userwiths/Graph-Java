package Graphs;

import numbers.test.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import Graphs.Hierarchy;

//Implementing Hierarchy is optional.
public class GraphTable<T> /*implements Hierarchy<T>*/{
    private double[][] Table;
    private List<T> Map;
    private int Elements;
    
    public GraphTable(){
	this.Map=new ArrayList<T>();
	this.Table=new double[1][1];
	this.Elements=0;
    }    
    public GraphTable(T[] arg){
	this.Map=new ArrayList<T>();
	for(T item:arg){
	    this.Map.add(item);
	}
	this.Table=new double[arg.length][arg.length];
	this.Elements=arg.length;
    }
    
      //	Used in function "FindMST" to check if a graph cell is available.
     private boolean CanGoAt(int x,int y,double[][] table){
	     if((x<table.length && y<table.length) && (x>=0 && y>=0)){
		 return table[y][x]!=0;
	     }
	     return false;
     	 }       
    
    //	Main functionality
     public void Add(T item){
	double[][] table=new double[this.Elements+1][this.Elements+1];
	int i=0,e=0;
	this.Map.add(item);
	
	for(i=0;i<this.Elements;i++){
	   for(e=0;e<this.Elements;e++){
	        table[i][e]=this.Table[i][e];
	   }
	}
	this.Table=table;
	this.Elements+=1;
    }    
     public void Remove(T item){
	double[][] table=new double[this.Elements-1][this.Elements-1];
	int i=0,e=0;
	int index=0;
	//Try to escape evil users
	if(!this.Map.contains(item)){
	    return;
	}
	
	index=this.Map.indexOf(item);
	this.Map.remove(item);
	for(i=0;i<this.Elements-1;i++){
	    if(i==index){
		continue;
	    }
	   for(e=0;e<this.Elements-1;e++){
	       if(e==index){
		   continue;
	       }
	        table[i][e]=this.Table[i][e];
	   }
	}
	this.Table=table;
	this.Elements-=1;
    }    
     public void Replace(T old,T nw){
	 this.Map.set(this.Map.indexOf(old), nw);
     }
     public int NumberOfElements(){
	 return this.Map.size();
     }     
     public double GetWeight(T src,T dst){
	 return this.Table[this.Map.indexOf(src)][this.Map.indexOf(dst)];
     }
     public double GetWeight(List<T> list){
	 double result=0;
	 for(int i=0;i<list.size()-1;i++){
	     result+=GetWeight(list.get(i),list.get(i+1));
	 }
	 return result;
  }
     //	Connecting Elements.	//
     public void Connect(T src,T dst){
	int y=this.Map.indexOf(dst);
	int x=this.Map.indexOf(src);
	this.Table[y][x]=1;
    }   
     public void Connect(T src,T dst,double value){
	int y=this.Map.indexOf(dst);
	int x=this.Map.indexOf(src);
	this.Table[y][x]=value;
    }   
     public void BiConnect(T src,T dst){
	int y=this.Map.indexOf(dst);
	int x=this.Map.indexOf(src);
	this.Table[y][x]=1;
	this.Table[x][y]=1;
    }   
     public void BiConnect(T src,T dst,double value){
	int y=this.Map.indexOf(dst);
	int x=this.Map.indexOf(src);
	this.Table[y][x]=value;
	this.Table[x][y]=value;
    }
     //	Dis-Connecting Elements. //
     public void DisConnect(T src,T dst){
	int y=this.Map.indexOf(dst);
	int x=this.Map.indexOf(src);
	this.Table[y][x]=0;
    }   
     public void BiDisConnect(T src,T dst){
	int y=this.Map.indexOf(dst);
	int x=this.Map.indexOf(src);
	this.Table[y][x]=0;
	this.Table[x][y]=0;
    }   
     // Get the elements that "src" is connected to-> GetParents(src)
     public List<T> RefferedBy(T src){
     	List<T> result=new ArrayList<T>();
	int y=this.Map.indexOf(src);
	int i=0;
	for(i=0;i<this.Elements;i++){
	    if(this.Table[y][i]!=0){
		result.add(this.Map.get(i));
	    }
	}
	return result;
     }    
    // To which elements is "src" connected to -> GetChilds(src)
      public List<T> ReffersTo(T src){
     	List<T> result=new ArrayList<T>();
	int x=this.Map.indexOf(src);
	int i=0;
	for(i=0;i<this.Elements;i++){
	    if(this.Table[i][x]!=0){
		result.add(this.Map.get(i));
	    }
	}
	return result;
     }	 
     // Get the first (top-to-bottom) child
     public T FirstChild(T src){
	     int i=0;
	     int e=this.Map.indexOf(src);
	     for(i=0;i<this.Elements;i++){
		 if(this.Table[i][e]!=0){
		     return this.Map.get(i);
		 }
	     }
	     return null;
     }
     // Check if the graph/map contains the given element.
     public boolean Contains(T item){
	     return this.Map.contains(item);
     }
     //Check if elements are connected.
     public boolean AreConnected(T src,T dst){
	 int x=this.Map.indexOf(src);
	 int y=this.Map.indexOf(dst);
	 return this.Table[y][x]!=0?FindPath(src,dst).isEmpty():true;
     }	 
     public boolean AreConnected(T src,T dst,int depth){
	     if(depth==1){
		 return AreConnected(src,dst);
	     }
	     return FindPath(src,dst,depth).isEmpty();
     } 
     // Works as it should
     public List<T> FindPath(T src,T dst){
	 List<T> result=new ArrayList<T>();
	 for(T curr:FindMST(src,dst)){
	     if(ReffersTo(curr).contains(dst) || RefferedBy(curr).contains(dst)){
		 result.add(curr);
		 return result;
	     }else{
		 result.add(curr);
	     }
	 }
	 return result;
}
      //	Needs More Optimization
     public List<T> FindPath(T src,T dst,int depth){
	      List<T> rusty=FindMST(src,dst);
	      //Dummy solution
	      return rusty.subList(0,depth);
}
      //	MST (Minimum Spanning Tree) members/elements
     public List<T> FindMST(T src,T dst){
	 List<T> result=new ArrayList<T>();
	 // Copy the table so its not changed
	 double[][] copy_table=new double[this.Elements][this.Elements];
	 for(int i=0;i<this.Elements;i++){
	     for(int e=0;e<this.Elements;e++){
		 copy_table[i][e]=this.Table[i][e];
	     }
	 } // End  of Copy
	 T last=src;	//Remember last element in order to detect (simple) cycles.
	int e=this.Map.indexOf(FirstChild(src));    //	The first child is the beggining element.
	int i=this.Map.indexOf(src);
	
	//  Add the beggining.
	result.add(src);
	
	//  Start loop checking each cell of the table, and remove each dead end.
	while (this.Map.get(e)!=dst && this.Map.get(i)!=dst){
		last=this.Map.get(e);
		copy_table[e][i]=0;
		if (CanGoAt(i,e+1,copy_table)){
			e++;
			result.add(this.Map.get(e));
		}else if (CanGoAt(i,e-1,copy_table)){
			e--;
			result.add(this.Map.get(e));
		}else if (CanGoAt(i+1,e,copy_table)){
			i++;
			result.add(this.Map.get(i));
		}else if (CanGoAt(i-1,e,copy_table)){
			i--;
			result.add(this.Map.get(i));
		}else{
		    copy_table[e][i]=0;
		    break;
		} 
		 if(this.Map.get(e)==last){
		    copy_table[e][i]=0;
		    break;
		}
	}
	 return result;
      }
     //	Get the path from src -> dst with Lithest connection.
     public List<T> FindLithestPath(T src,T dst){
	 List<T> result=new ArrayList<T>();
	 
	 return result;
     }
     //	Get the path from src -> dst which has the largest sum of connections.
     public List<T> FindHeaviestPath(T src,T dst){
	 List<T> result=new ArrayList<T>();
	 
	 return result;
     }	 
  
@Override
    public String toString(){
	String result="\t";
	for(T item:this.Map){
	    result+=item+"\t";
	}
	result+='\n';
	for(int i=0;i<this.Elements;i++){
	    result+=this.Map.get(i);
	    for(double val:this.Table[i]){
		result+="\t"+val;
	    }
	    result+='\n';
	}
	return result;
    }
    //	Just like using System.out with ".toString()".	//
    public void PrintGraph(){
	 System.out.print("\t");
	 for(T item:this.Map){
	     System.out.print(item+"\t");
	 }
	 System.out.println();
	 for(int i=0;i<this.Elements;i++){
	     System.out.print(this.Map.get(i));
	     for(double val:this.Table[i]){
		 System.out.print("\t"+val);
	     }
	     System.out.println();
	 }
     }
}
