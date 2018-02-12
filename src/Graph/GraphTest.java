package Graphs;

import numbers.test.*;
import java.io.Console;
import java.io.PrintStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

//Not needet if they are in one package
//import Graphs.GraphTable;
public class GtaphTest {
   
    public static void main(String[] args) {
	String[] map={"one","two","three","four","five","six"};
	GraphTable<String> grp=new GraphTable(map);
	
	grp.Connect("one","two",12);
	grp.Connect("two", "three",23);
	grp.Connect("three", "four",34);
	grp.Connect("four", "five",45);
	grp.Connect("five", "six");
    
	grp.Connect("two", "four",209);
	grp.Connect("two", "six",4);
	grp.Connect("three","six",3);
	
	System.out.println(grp.toString());
	
	grp.Add("eight");
	grp.Connect("two", "eight",6);
	grp.Connect("four", "eight",4);
	grp.Connect("five","eight",3);
	
	grp.Remove("eight");
	grp.Add("seven");
	grp.Connect("six", "seven",78);
	grp.Connect("two", "seven",5);
	grp.Connect("five", "seven",123);
	grp.Connect("one", "three",200);
	grp.Connect("one", "four",13);
	grp.Connect("six", "seven",98);
	grp.Connect("four", "six",666);
	grp.Connect("two", "one",999);
	grp.PrintGraph();
	System.out.println(grp.FindPath("one", "seven").toString());
	grp.PrintGraph();  

    }
}
