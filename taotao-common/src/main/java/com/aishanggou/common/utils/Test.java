package com.aishanggou.common.utils;

import java.math.BigDecimal;

public class Test {

	public static void main(String[] args) {
		
		double a1 = 4.00d;
		String str = String.valueOf(a1);
		BigDecimal bigDecimal = new BigDecimal(str);
		System.out.println(bigDecimal);
		//注意使用其 做除法运算时
		BigDecimal bigDecimal2 = new BigDecimal("2.00");
		//第3个参数是指定小数点的取舍方式，有：四舍五入制，进位制，退位制，等等，可也以不写，它有自己的默认方式。
		bigDecimal.divide(bigDecimal2);
		
	}
}
