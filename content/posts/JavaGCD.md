---
layout: post
title: java几种方式实现斐波那契数列
date: 2015-08-27
meta_description:
categories:
 - java
 - data structure
comments: true
browser_title: java几种方式实现斐波那契数列
---

    关于斐波那契数列:
    指的是这样一个数列 0, 1, 1, 2, 3, 5, 8 ...
    - 第0项是0，第1项是第一个1
    - 从第2项开始，每一项都等于前两项之和

数学与生活关于[斐波那契数列](http://baike.baidu.com/view/816.htm)的且不谈，在程序语言学习中学习递归时，总会以此为经典示例

#### java实现几种方式

```java
/**
 * @author nonefly
 * 2015年8月27日
 */
public class Gcd {
    /**
     * 解法零
     * 暴力尝试
     * 选一个较小的数开始尝试，直到试到2，两者都能除尽时终止
     */
    public static int gcd0(int a, int b) {
        if(a < 0 || b < 0)
            return -1;
        for (int i = a > b ? b : a; i > 1; i--) {
            if(a % i == 0 && b % i == 0)
                return i;
        }
        return 1;
    }
    /** 解法一 
     * 欧几里得算法（辗转相除法）
     * 求两个数(a b,假设a > b)最大公约数,
     * 每次用较大数a除b取余,
     * 余数c不为0则让较大数等于较小数，较小数等于余数,即:
     * a = b; b= c;
     */
    public static int gcd1(int a, int b) {
        if(a < 0 || b < 0)
            return -1;
        if(a == b || a == 0)
            return b;
        if(b == 0)
            return a;
        if(a < b)
            return gcd1(a, b % a);
        else
            return gcd1(b, a % b);
    }
    /**
     * 解法二
     * 更相减损术 来自《九章算术》
     * ps:我也不知道这算法来头，只是知道算法思想，查后才知它的出处和名字~
     * 
     * ①两个数都为偶数，提出一个2
     * ②一个为偶数，偶数除以二
     * ③两个为奇数，大者减去小的
     * ④重复以上三个步骤，直到两个数相等
     */
    public static int gcd2(int a, int b) {
        if(a < 0 || b < 0)
            return -1;
        if(a == b)
            return a;
        if((a & 1) > (b & 1))
            return gcd2(a, b >> 1);
        if((a & 1) < (b & 1))
            return gcd2(a >> 1, b);
        if((a & 1) == 0 && (b & 1) == 0)
            return gcd2(a >> 1, b >> 1) << 1;
        return gcd2(Math.abs(a - b), b > a ? a : b);
    }
    
    public static void main(String[] args) {
        System.out.println(gcd0(99, 66));
    }
}
```
