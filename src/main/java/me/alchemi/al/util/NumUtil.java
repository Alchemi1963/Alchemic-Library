package me.alchemi.al.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumUtil {
	private static final Map<Character, Integer> dictionary = new HashMap<Character, Integer>() {
		{
			put('a', 1);
			put('b', 2);
			put('c', 3);
			put('d', 4);
			put('e', 5);
			put('f', 6);
			put('g', 7);
			put('h', 8);
			put('i', 9);
			put('j', 10);
			put('k', 11);
			put('l', 12);
			put('m', 13);
			put('n', 14);
			put('o', 15);
			put('p', 16);
			put('q', 17);
			put('r', 18);
			put('s', 19);
			put('t', 20);
			put('u', 21);
			put('v', 22);
			put('w', 23);
			put('x', 24);
			put('y', 25);
			put('z', 26);
			put(' ', 27);
			put('-', 28);
			put('_', 29);
			put('?', 30);
			put('*', 31);
		}
	};
	
	private static final TreeMap<Integer, String> romanMap = new TreeMap<Integer, String>();

	static {

        romanMap.put(1000, "M");
        romanMap.put(900, "CM");
        romanMap.put(500, "D");
        romanMap.put(400, "CD");
        romanMap.put(100, "C");
        romanMap.put(90, "XC");
        romanMap.put(50, "L");
        romanMap.put(40, "XL");
        romanMap.put(10, "X");
        romanMap.put(9, "IX");
        romanMap.put(5, "V");
        romanMap.put(4, "IV");
        romanMap.put(1, "I");

    }
	
	/**
	 * Test if a string is a number.
	 * 
	 * @param input the string to test.
	 * @return Wether the string is a number or not.
	 */
	public static boolean testIfNumber(String input) {
		Matcher m = Pattern.compile("\\d+(,|.)*").matcher(input);
		
		return m.matches();
	}
	
	public static boolean isPrimitive(String input, Class<? extends Number> primitive){
		try {
			Method pMethod = primitive.getMethod("valueOf", String.class);
			pMethod.invoke(null, input);
			return true;
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			return false;
		}
		
	}

	public static String toBinary(String from) {
		String output = "";
		from = from.toLowerCase();
		
		for (int ch = 0; ch < from.length(); ch++) {
			output += NumUtil.numToBinary(NumUtil.letterToNumber(from.charAt(ch)));
		}
		return output;
	}

	public static int letterToNumber(char input) {
		return NumUtil.dictionary.containsKey(input) ? NumUtil.dictionary.get(input) : -1;
	}

	public static String numToBinary(int number) {
		String bin = "";
		
		while (number > 0) {
			for (int entry : Arrays.asList(4, 3, 2, 1, 0)) {
				if (number - Math.pow(2, entry) >= 0) {
					number -= Math.pow(2, entry);
					bin += "1";
					continue;
				}
				bin += "0";
			}
		}
		
		return bin;
	}	

	public final static String toRoman(int number) {
	    int l =  romanMap.floorKey(number);
	    if ( number == l ) {
	        return romanMap.get(number);
	    }
	    return romanMap.get(l) + toRoman(number-l);
	}
	
}
