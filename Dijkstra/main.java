

package Dijkstra;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import automorphism.Automorphism;

import CSP.ConstraintGraph;
import CSP.DFSSearch;

import pruning.SPpruner;

import search.Searcher;
import statistics.DistinctLabelStat;
import statistics.TimedOutSearchStats;

import utilities.CommandLineParser;
import utilities.DfscodesCache;
import utilities.MyPair;
import utilities.Settings;
import utilities.StopWatch;

import dataStructures.DFSCode;
import dataStructures.DFScodeSerializer;
import dataStructures.Graph;
import dataStructures.HPListGraph;
import dataStructures.Query;
import dataStructures.StaticData;
import decomposer.Decomposer;


public class main {
	
	static int APPROX=0;
	static int EXACT=1;
	
	static int FSM=0;
	
	public static void main(String[] args) 
	{
		int maxNumOfDistinctNodes=1;
				
		//default frequency
		int freq=1000;

//
//		List<Double> list = new ArrayList<>();
//		list.add(4.2);
//		list.add(3.7);
//		list.add(5.6);
//		list.add(2.5);
//		Collections.sort(list);

		//parse the command line arguments
		CommandLineParser.parse(args);
		
		if(utilities.Settings.frequency>-1)
			freq = utilities.Settings.frequency;
		
		Searcher<String, String> sr=null;

		StopWatch watch = new StopWatch();
		Runtime r = Runtime.getRuntime();
		r.gc();
		try
		{
			long start = System.currentTimeMillis();//开始时间
			long startMem = r.totalMemory()-r.freeMemory();//开始memory

			watch.start();
			
			if(Settings.fileName==null)
			{
				System.out.println("You have to specify a dataset filename");
				System.exit(1);
			}
			else
			{
				sr = new Searcher<String, String>(Settings.datasetsFolder+Settings.fileName, freq, 1);
			}
		
			//start mining
			sr.initialize();
			sr.search();

			long endMem = r.totalMemory() - r.freeMemory();//末尾Memory
			long end = System.currentTimeMillis();//末尾time

			System.out.println("The candidate number is :" + Settings.CanCount);
			System.out.println("The pattern number is :" + Settings.PatternCount);
			System.out.println("The time usage is :"+ String.valueOf((end-start)/1000)+"s");
			System.out.println("The memory usage is :"+ String.valueOf((endMem-startMem)/1024/1024)+"MB");
			System.out.println("不符合条件的数量 :"+ Settings.Count);
			watch.stop();
		
			//write output file for the following things:
			//1- time
			//2- number of resulted patterns
			//3- the list of frequent subgraphs
			FileWriter fw;
			try
			{
				String fName = "Output+"+"_"+Settings.fileName2 +"_"+Settings.utilityThre +"_" + Settings.MVC +".txt";
			
				fw = new FileWriter(fName);
//				fw.write(watch.getElapsedTime()/1000.0+"\n");
				fw.write(sr.result.size()+"\n");
			
				//write the frequent subgraphs
				for (int i = 0; i < sr.result.size(); i++) 
				{		
					String out=DFScodeSerializer.serialize(sr.result.get(i));
					fw.write(i+" :\n");
					fw.write(out);
				}
				fw.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
