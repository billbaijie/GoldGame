package com.baijie.GoldGame;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Test {
	public static void main(String args[]){
		List<String> list = new ArrayList<String>();
		list.add("a");
		list.add("b");
		list.add("c");
		Iterator<String> i = list.iterator();
		System.out.println(i.next());
		System.out.println(i.next());
		list.clear();
//		list.add("d");
//		list.add("e");
//		list.add("f");
//		i = list.iterator();
		System.out.println(i.next());
		System.out.println(i.next());
		
	}
}
