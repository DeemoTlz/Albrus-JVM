package com.deemo;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

public class MemoryTest6 {

    @Test
    public void rpcTest() {
        Set<String> set = new HashSet<String>();
        int i = 0;

        while (true) {
            // set.add(String.valueOf(i++));
            set.add(String.valueOf(i++).intern());
        }

        // 运行结果：
        /**
         * Exception in thread "main" java.lang.OutOfMemoryError: PermGen space
         * 	at java.lang.String.intern(Native Method)
         * 	at com.deemo.MemoryTest6.main(MemoryTest6.java from InputFileObject:14)
         */
    }

    /**
     * -XX:PermSize=6M -XX:MaxPermSize=6M
     * -Xms6M -Xmx6M
     */
    @Test
    public void methodAreaTest() {
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

        // 运行结果：
        /**
         * Exception in thread "main" java.lang.OutOfMemoryError: PermGen space
         */
    }

}
