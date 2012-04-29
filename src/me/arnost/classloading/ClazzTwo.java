package me.arnost.classloading;

public class ClazzTwo {
    static {
        System.out.println("Initialize ClazzTwo, my classloader is " + ClazzOne.class.getClassLoader());
    }
}
