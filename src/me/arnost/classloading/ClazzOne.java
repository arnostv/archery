package me.arnost.classloading;

public class ClazzOne {

    static {
        System.out.println("Static init in  ClazzOne, my classloader is " + ClazzOne.class.getClassLoader());
    }

    public void run() {
        System.out.println("Running ClazzOne, current classloader is  " + this.getClass().getClassLoader());

        new ClazzTwo();
    }
}
