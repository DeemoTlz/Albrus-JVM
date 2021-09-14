package com.deemo;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class StringTest {
    static String s1 = "悠悠苍天何薄于我";
    final String s2 = "悠悠苍天何薄于我";

    @Test
    public void appetizer() {
        String s1 = "abc";
        String s2 = "abc";
        System.out.println(s1 == s2); // true

        String s3 = "abcdef";
        String s4 = "abc" + "def";
        System.out.println(s3 == s4); //

        String s = "def";
        String s5 = s1 + "def";
        String s6 = s1 + s;
        System.out.println(s3 == s5); //
        System.out.println(s3 == s6); //
        System.out.println(s5 == s6); //

        String s7 = s5.intern();
        System.out.println(s3 == s7); //
    }

    @Test
    public void appetizer2() {
        String s1 = "abcdef";
        String s2 = "abc";

        String s3 = s2 + "def";
        System.out.println(s1 == s3); //

        final String s4 = s2;
        String s5 = s4 + "def";
        System.out.println(s1 == s5); //

        final String s6 = "abc";
        String s7 = s6 + "def";
        System.out.println(s1 == s7); //
    }

    @Test
    public void inter() {
        // 字面量“于”、“禁”将在SCP中创建，此时SCP中并未出现“于禁”
        // String s1 = new String("于")  + new String("禁");
        String s1 = new StringBuilder("于").append("禁").toString();
        // 字面量，先在SCP中寻找是否有“于禁”，发现没有则创建“于禁”（或堆中创建“于禁”SCP中保存引用？）
        String s2 = "于禁";
        // intern() 方法想要将字符串“于禁”放置在SCP中，发现SCP中已经存在“于禁”，直接返回该引用
        // System.out.println(s1.intern() == s2);
        System.out.println(s1 == s2);

        // 字面量“许”、“褚”将在SCP中创建，此时SCP中并未出现“许褚”
        // String s3 = new String("许")  + new String("褚");
        String s3 = new StringBuilder("许").append("褚").toString();
        // String s3 = new String("许褚");
        // intern() 方法想要将字符串“许褚”放置在SCP中，发现SCP中还未出现过“于禁”，于是将s3的引用地址存放至SCP中
        s3.intern();
        // 字面量，先在SCP中寻找是否有“许褚”，发现已存在，则返回该引用
        String s4 = "许褚";
        System.out.println(s3 == s4);

        // String s = "典";
        // String ss = s + "韦";
        String s5 = new String("典") + new String("韦");
        // String s5 = new StringBuilder("典").append("韦").toString();
        System.out.println(s5.intern() == s5);
        // System.out.println(s5 == ss.intern());

        String s6 = "主公";
        String s7 = new StringBuilder("主").append("公").toString();
        System.out.println(s7.intern() == s7);
        System.out.println(s7.intern() == s6);

        String s8 = new String("阿斗");
        String s9 = new StringBuilder("阿").append("斗").toString();
        System.out.println(s9.intern() == s8.intern());
        System.out.println(s9.intern() == s9);

        String s10 = new String("左少");
        System.out.println(s10.intern() == s10);

        String s11 = "夏";
        String s12 = new StringBuilder(s11).append("侯").toString();
        System.out.println(s12.intern() == s12);

        // sun.misc.Version
        String s13 = new String("ja") + new String("va");
        System.out.println(s13.intern() == s13);
    }

    @Test
    public void literals() {
        String s = "悠悠苍天何薄于我";

        // CP RCP SCP
        System.out.println(s1 == this.s2);
        System.out.println(s1 == s);
        System.out.println(this.s2 == s);
        System.out.println(new A().s == s);
    }

    @Test
    public void string() {
        List<String> list = new LinkedList<>();

        int i = 0;
        while (true) {
            list.add(String.valueOf(i++).intern());
        }
    }

}

class A {
    String s = "悠悠苍天何薄于我";
}
