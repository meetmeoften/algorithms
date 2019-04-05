package com.test.binaryTree.patel;

import java.io.DataInputStream;
import java.io.IOException;

public class ClassFit {
	public static void main(String args[])throws IOException
	{
		int n,i,it;
		DataInputStream in=new DataInputStream(System.in);
		System.out.println("Enter Number of Holes");
		n=Integer.parseInt(in.readLine());
		int sp[]=new int[n];
		for(i=0;i<n;i++)
		{
			System.out.println("Enter size of Hole"+(i+1));
			sp[i]=Integer.parseInt(in.readLine());
		}
		System.out.println("Before Insertion of new process");
		System.out.println("hole\t\tsize");
		for(i=0;i<n;i++)
		{
			System.out.println("hole"+(i+1)+"\t\t"+sp[i]);
		}
		System.out.println("enter new process size to insert");
		it=Integer.parseInt(in.readLine());
		for(i=0;i<n;i++)
		{
			if(it<sp[i])
			{
				sp[i]=sp[i]-it;
				break;
			}
		}
		System.out.println("After Insertion of new process");
		System.out.println("hole\t\tsize");
		for(i=0;i<n;i++)
		{
			System.out.println("hole"+(i+1)+"\t\t"+sp[i]);
		}
	}
}
