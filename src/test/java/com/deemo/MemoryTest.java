package com.deemo;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.*;

public class MemoryTest {

    /**
     * -Xms20m -Xmx20m -XX:-UseGCOverheadLimit -XX:+HeapDumpOnOutOfMemoryError
     */
    @Test
    public void heap() {
        List<MemoryTest> list = new LinkedList<>();

        while (true) {
            list.add(new MemoryTest());
        }
    }

    private int stackLength = 1;
    public void stackLeak1() {
        stackLength++;
        stackLeak1();
    }

    /**
     * -Xss128k
     */
    @Test
    public void stack1() {
        try {
            stackLeak1();
        } catch (Throwable e) {
            System.out.println("stack length is: " + stackLength);
            throw e;
        }
    }

    public void stackLeak2() {
        long no0, no10, no20, no30, no40, no50, no60, no70, no80, no90,
                no1, no11, no21, no31, no41, no51, no61, no71, no81, no91,
                no2, no12, no22, no32, no42, no52, no62, no72, no82, no92,
                no3, no13, no23, no33, no43, no53, no63, no73, no83, no93,
                no4, no14, no24, no34, no44, no54, no64, no74, no84, no94,
                no5, no15, no25, no35, no45, no55, no65, no75, no85, no95,
                no6, no16, no26, no36, no46, no56, no66, no76, no86, no96,
                no7, no17, no27, no37, no47, no57, no67, no77, no87, no97,
                no8, no18, no28, no38, no48, no58, no68, no78, no88, no98,
                no9, no19, no29, no39, no49, no59, no69, no79, no89, no99;

        stackLength++;
        stackLeak2();

        no0 = no10 = no20 = no30 = no40 = no50 = no60 = no70 = no80 = no90 =
        no1 = no11 = no21 = no31 = no41 = no51 = no61 = no71 = no81 = no91 =
        no2 = no12 = no22 = no32 = no42 = no52 = no62 = no72 = no82 = no92 =
        no3 = no13 = no23 = no33 = no43 = no53 = no63 = no73 = no83 = no93 =
        no4 = no14 = no24 = no34 = no44 = no54 = no64 = no74 = no84 = no94 =
        no5 = no15 = no25 = no35 = no45 = no55 = no65 = no75 = no85 = no95 =
        no6 = no16 = no26 = no36 = no46 = no56 = no66 = no76 = no86 = no96 =
        no7 = no17 = no27 = no37 = no47 = no57 = no67 = no77 = no87 = no97 =
        no8 = no18 = no28 = no38 = no48 = no58 = no68 = no78 = no88 = no98 =
        no9 = no19 = no29 = no39 = no49 = no59 = no69 = no79 = no89 = no99 = 0;
    }

    /**
     * -Xss128k
     */
    @Test
    public void stack2() {
        try {
            stackLeak2();
        } catch (Throwable e) {
            System.out.println("stack length is: " + stackLength);
            throw e;
        }

        // 运行结果：
        /**
         * stack length is: 50
         *
         * java.lang.StackOverflowError
         * 	at com.deemo.MemoryTest.stackLeak2(MemoryTest.java:53)
         * 	at com.deemo.MemoryTest.stackLeak2(MemoryTest.java:54)
         * 	at com.deemo.MemoryTest.stackLeak2(MemoryTest.java:54)
         * 	...
         */
    }

    /**
     * -XX:PermSize=6M -XX:MaxPermSize=6M
     * -Xms6M -Xmx6M
     */
    @Test
    public void rcpTest() {
        Set<String> set = new HashSet<>();
        int i = 0;

        while (true) {
            // set.add(String.valueOf(i++));
            set.add(String.valueOf(i++).intern());
        }

        // 运行结果：
        /**
         * java.lang.OutOfMemoryError: Java heap space
         *
         * 	at java.lang.Integer.toString(Integer.java:403)
         * 	at java.lang.String.valueOf(String.java:3099)
         * 	at com.deemo.MemoryTest.rcpTest(MemoryTest.java:102)
         */
        /**
         * java.lang.OutOfMemoryError: Java heap space
         *
         * 	at java.util.HashMap.newNode(HashMap.java:1747)
         * 	at java.util.HashMap.putVal(HashMap.java:642)
         * 	at java.util.HashMap.put(HashMap.java:612)
         * 	at java.util.HashSet.add(HashSet.java:220)
         * 	at com.deemo.MemoryTest.rcpTest(MemoryTest.java:102)
         */
    }

    /**
     * -XX:PermSize=6M -XX:MaxPermSize=6M
     * -XX:MetaspaceSize=6M -XX:MaxMetaspaceSize=6M
     */
    @Test
    public void methodAreaTest() {
        try {
            while (true) {
                Enhancer enhancer = new Enhancer();
                enhancer.setSuperclass(Object.class);
                enhancer.setUseCache(false);
                enhancer.setCallback(new MethodInterceptor() {
                    @Override
                    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                        return methodProxy.invokeSuper(o, objects);
                    }
                });

                enhancer.create();
            }
        } catch (Exception e) {
            System.err.println(e.getCause().getCause());
        }

        // 运行结果：JDK 7
        /**
         * java.lang.OutOfMemoryError: PermGen space
         */
        // 运行结果：JDK 8
        /**
         * java.lang.OutOfMemoryError: Metaspace
         */
    }

}
