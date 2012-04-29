package me.arnost.classloading;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ClassLoading {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {



        LocalClassloader classloader = new LocalClassloader(Thread.currentThread().getContextClassLoader());
        System.out.println("Setting delegating classloader to " + classloader);
        Thread.currentThread().setContextClassLoader(classloader);


        System.out.println("*** Directly initializing - new ClazzOne");
        ClazzOne cone = new ClazzOne();
        cone.run();


        System.out.println("*** Initializing by classloader, invoking by reflection");
        Class clazz = Thread.currentThread().getContextClassLoader().loadClass(ClazzOne.class.getName());
        Object o = clazz.newInstance();
        final Method method = clazz.getMethod("run");
        method.invoke(o);

        System.out.println("Assing object to class - this may fail if classes are from different classloaders");
        System.out.println("Instanceof check : " + (o instanceof ClazzOne));
        ClazzOne assigned = (ClazzOne) o;
        assigned.run();

        System.out.println("DONE!");

    }

    public void run() {
        System.out.println("Instantiating ClassLoading");
        //new ClazzOne().run();
    }
}
