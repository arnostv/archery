package me.arnost.classloading;

import java.io.IOException;
import java.io.InputStream;

import static java.io.File.separatorChar;

public class LocalClassloader extends ClassLoader {

    LocalClassloader(ClassLoader parrent) {
        super(parrent);
    }


    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {

        //loading siblings - from same package as this classloader, otherwise use parent classloader
        if (name.startsWith(getClass().getPackage().getName())) {
            String classPath = name.replace('.', separatorChar) + ".class";
            InputStream is = super.getResourceAsStream(classPath);
            try {
                byte[] buffer = new byte[is.available()];
                is.read(buffer, 0, buffer.length);

                Class clazz = defineClass(name, buffer, 0, buffer.length);
                resolveClass(clazz);
                return clazz;
            } catch (IOException e) {
                throw new ClassNotFoundException("Can't load class file " + e.getLocalizedMessage(),e);
            } finally {
                try {
                    is.close();
                } catch (IOException e) {
                    throw new ClassNotFoundException("Error closing input stream " + e.getLocalizedMessage(),e);
                }
            }
        } else {
            return super.loadClass(name);
        }
    }
}
