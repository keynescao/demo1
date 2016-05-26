package com.demo.tst1;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class DemoMain extends C implements A,B{

	@Override
	public void echoC(String msg) {
		System.out.println("Echo : " + msg);
	}

	@Override
	public void echoB(String msg) {
		System.out.println("Echo : " + msg);
	}

	@Override
	public void echoA(String msg) {
		System.out.println("Echo : " + msg);
	}

	public static void main(String[] args) {
		/*A a = new DemoMain();
		B b = new DemoMain();
		C c = new DemoMain();
		
		a.echoA("A");
		b.echoB("B");
		b.sqrt(0.01D);
		c.echoC("C");
		c.say("over");
		String a1 = "abc",b1 = "bcd";
		String [] aa = {a1,b1};
		
		Convert<String,Integer> cont = (from) -> Integer.valueOf(from);
		
		Convert<String,Integer> cont1 = Integer :: valueOf;
		
		Function<String, Integer> func1 = Integer::valueOf;
		
		Comparator<String> comtr = (p1,p2) -> p1.compareTo(p2);
		
		List<String> strColl = new ArrayList<String>();
		strColl.add("abc1");
		strColl.add("babc2");
		strColl.add("cabc3");
		strColl.add("dabc4");
		strColl.add("eabc5");
		strColl.add("aabc6");
		strColl.add("ccabc7");
		strColl.add("fabc8");
		strColl.add("habc9");
		
		strColl.stream()
		.map(String::toUpperCase)
		//.filter((s)->s.startsWith("A"))
		//.reduce((s1,s2)->s1 +"#"+s2)
		.forEach(System.out::println);
		
		System.out.println("Hello GitHub");
		
		System.out.println("ABC123");
		
		int msg = (int)(System.currentTimeMillis()/1000L + 2208988800L);
		System.out.println(System.currentTimeMillis()/1000L);
		System.out.println(1462520809 + 2208988800L );
		System.out.println(Integer.MAX_VALUE);
		System.out.println(msg);
		*/
		
		/*DemoMain a = new DemoMain();
		a.getMusicList("E:\\other");
		for(String s : a.musicList){
			System.out.println(s);
		}*/
	}
	
	public List<String> musicList = new ArrayList<String>();
	
	public void getMusicList(String path){

		File file = new File(path);
		if(file.isDirectory()){
			File []list = file.listFiles(new FileFilter() {
				
				@Override
				public boolean accept(File pathname) {
					if(pathname.isDirectory()){
						return true;
					}else{
						String fileName = pathname.getName().toLowerCase();
						if(fileName.endsWith(".mp3")){
							return true;
						}
						return false;
					}
				}
			});
			for(File f : list){
				getMusicList(f.getAbsolutePath());
			}
		}else{
			musicList.add(file.getName());
		}

	}

}





interface Convert<F,T>{
	
	T convert(F from);
	
}

abstract class C{
	public abstract void echoC(String msg);
	public void say(String msg){
		System.out.println("Say : " + msg);
	}
}


interface A{
	public void echoA(String msg);
}

interface B{
	public void echoB(String msg);
	
	default double sqrt(double d){
		return Math.sqrt(d);
	}
}









