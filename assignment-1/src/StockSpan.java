import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Stack;
import java.text.*;
import java.lang.System;
import java.io.IOException;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.Stack;

public class StockSpan {
    public static void main (String args[]){
	
	ArrayList <Float> prices = new ArrayList <Float>();
	ArrayList <Date> dates= new ArrayList <Date> ();
	float Naivetime;
	float Stacktime;
	int anoigma;
	int i;
	
	try{
	BufferedReader Reader = new BufferedReader(new FileReader(args[1]));
	String str;
    SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
     boolean firstline= true;
        while((str=Reader.readLine()) != null){
		    if(firstline){
			    str=Reader.readLine();
				firstline=false;
			}	
		
		String[] Fields = str.split(",");
		prices.add(Float.parseFloat(Fields[1]));
		try{

			dates.add(sdf.parse(Fields[0]));
		}catch(ParseException pe) {System.out.println(pe);}
        }
        Reader.close();
		}
		catch (IOException e) {
			System.out.println(e);
		}
    int counter=prices.size();
	
	if (args[0].equals("-n")) {
	    int [] span= new  int [counter];
	    for (i=0; i<counter; i++) {
		    boolean span_end= false;
			anoigma=1;
			
			while ((i-anoigma)>=0 && (!span_end)){
			    if (prices.get(i-anoigma)<=(prices.get(i))){
				    anoigma = anoigma +1;
				}	
				else {
				    span_end=true; 
			     }
			}
			span[i]=anoigma;
            System.out.println(dates.get(i)+","+span[i]);		
		} 
        		
	} 
    else if(args[0].equals("-s")){
	    int [] span = new int [counter];
	    Stack <Integer> S= new Stack <Integer> ();
        S.push(0);
		span[0]=1;
	    for(i=0; i<counter; i++) {
		    while ((!S.isEmpty()) && (prices.get(S.peek())<= prices.get(i))){
			    S.pop();
			}
			if (S.isEmpty()) {
			    span[i]=i +1;
			}
			else {
			    span[i] = i- S.peek();
			}
			S.push(i);
			System.out.println(dates.get(i)+","+span[i]);
		}	
    }	
	else if (args[0].equals("-b")){
	    long sTime = System.nanoTime();
	    int [] span= new  int [counter];

	    for (i=0; i<counter; i++) {
		    boolean span_end= false;
			anoigma=1;
			
			while ((i-anoigma)>=0 && (!span_end)){
			    if (prices.get(i-anoigma)<=(prices.get(i))){
				    anoigma = anoigma +1;
				}	
				else {
				    span_end = true; 
			     }
			}
        }
        long SimpleMethodTime = System.nanoTime() - sTime;
		Naivetime= SimpleMethodTime*100;
        System.out.println("Naivetime implementation took:"+Naivetime);
		
        sTime =System.nanoTime();
	    span = new int [counter];
	    Stack <Integer> S= new Stack <Integer> ();
        S.push(0);
		span[0]=1;
	    for(i=0; i<counter; i++) {
		    while ((!S.isEmpty()) && (prices.get(S.peek())<= prices.get(i))){
			    S.pop();
			}
			if (S.isEmpty()) {
			    span[i]=i +1;
			}
			else {
			    span[i] = i- S.peek();
			}
		}
        long StackMethodTime= System.nanoTime()-sTime;
        Stacktime=StackMethodTime*100;
		System.out.println("Stacktime implementation took:"+Stacktime);

    }	
 
	}
	    
} 
	