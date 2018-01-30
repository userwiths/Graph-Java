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

import Graphs.GraphTable;
import numbers.test.Ellipse;

public class NumbersTest {
   
    public static void main(String[] args) {
	/*BigInteger num=new BigInteger("1163062543");   //10^6 (milion)->  19097003 -> 3:21
						//2*10^6 -> 36218899 -> 3:29
						//3*10^6 -> 53833913 -> 3:32
						//10^7 (10*milion)-> 183554971 -> 26:03
						//2*10^7 -> 377859481 -> 39:38
						//(4+?)*10^7 -> 749662411
						//(5+?)*10^7 -> 955273987
						//(6+?)*10^7 -> 1163062543
						
	
	for(int i=(int)Math.round(Math.pow(10, 7));i>0;i--){
	    num=num.nextProbablePrime();
	    System.out.println(i+" : "+num.toString());
	}
	*/
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
