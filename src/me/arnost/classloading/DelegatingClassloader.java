package me.arnost.classloading;


import java.io.InputStream;
import java.net.URL;

public class DelegatingClassloader extends ClassLoader {

    private ClassLoader delegateClassloader;

    public DelegatingClassloader(ClassLoader delegateClassloader) {
        this.delegateClassloader = delegateClassloader;
    }


    @Override
    public void setDefaultAssertionStatus(boolean enabled) {
        delegateClassloader.setDefaultAssertionStatus(enabled);    //To change body of overridden methods use File | Settings | File Templates.
    }


    @Override
    public void setPackageAssertionStatus(String packageName, boolean enabled) {
        delegateClassloader.setPackageAssertionStatus(packageName, enabled);    //To change body of overridden methods use File | Settings | File Templates.
    }


    @Override
    public void setClassAssertionStatus(String className, boolean enabled) {
        delegateClassloader.setClassAssertionStatus(className, enabled);    //To change body of overridden methods use File | Settings | File Templates.
    }


    @Override
    public void clearAssertionStatus() {
        delegateClassloader.clearAssertionStatus();
    }

    @Override
    public InputStream getResourceAsStream(String name) {
        return delegateClassloader.getResourceAsStream(name);
    }

    @Override
    public URL getResource(String name) {
        return delegateClassloader.getResource(name);
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        System.out.println("Loading class" + name + " using " + delegateClassloader);
        return delegateClassloader.loadClass(name);
    }


    @Override
    public String toString() {
        return "DelegatingClassloader to " + delegateClassloader;
    }
}
