package com.wondersgroup.empi.util.common;
import java.util.*;

public class Test {
	
    public static void main(String []args) {
        List<String> list = Arrays.asList("ab,cd".split(","));
		List<String> query = Arrays.asList("a,c".split(","));
		System.out.println(fuzzyContainsAll(list, query));
    }
	
	// 判断S是不是Z的子集
	public static boolean fuzzyContainsAll(List<String> list, List<String> query) {
		boolean flag = true;
		for(String str : query) {
			flag = flag && fuzzyContains(list, str);
		}
		return flag;
	}
	
	// 对S中的每个字符串s，判断Z中是否有一个字符串z包含它
	public static boolean fuzzyContains(List<String> list, String s) {
		boolean flag = false;
		for(String z : list) {
			flag = flag || z.contains(s);
		}
		return flag;
	}
}
