package me.arnost.classloading;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ClassLoading {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        //new ClassLoading().run();


//        DelegatingClassloader classloader = new DelegatingClassloader(Thread.currentThread().getContextClassLoader());
//        System.out.println("Setting delegating classloader to " + classloader);
//        Thread.currentThread().setContextClassLoader(classloader);

        LocalClassloader classloader = new LocalClassloader(Thread.currentThread().getContextClassLoader());
        System.out.println("Setting delegating classloader to " + classloader);
        Thread.currentThread().setContextClassLoader(classloader);

//        ClazzOne cone = new ClazzOne();
//        cone.run();


        Class clazz = Thread.currentThread().getContextClassLoader().loadClass("me.arnost.classloading.ClazzOne");
        //Class clazz = Thread.currentThread().getContextClassLoader().loadClass(ClazzOne.class.getName());
        //ClassLoading loading = (ClassLoading) clazz.newInstance();
        Object o = clazz.newInstance();
        final Method method = clazz.getMethod("run");
        method.invoke(o);
        //loading.run();

    }

    public void run() {
        System.out.println("Instantiating ClassLoading");
        //new ClazzOne().run();
    }
}
