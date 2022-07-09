package com.albrus.gc;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

public class AlbrusMetaspace {

    /**
     * -XX:MetaspaceSize=10M -XX:MaxMetaspaceSize=10M
     */
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Object.class);
        enhancer.setUseCache(false);
        enhancer.setCallback((MethodInterceptor) (o, method, objects, methodProxy) -> methodProxy.invokeSuper(o, objects));

        while (true) {
            enhancer.create();
        }
    }

}
