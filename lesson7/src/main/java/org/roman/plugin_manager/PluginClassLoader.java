package org.roman.plugin_manager;

import static java.util.Objects.isNull;

public class PluginClassLoader extends ClassLoader {

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        ClassLoader parent = getParent();
        synchronized (getClassLoadingLock(name)) {
            Class<?> c = findLoadedClass(name);
            if (isNull(c)) {
                try {
                    c = findClass(name);
                } catch (ClassNotFoundException ignored) {}

                if (isNull(c)) {
                    c = parent.loadClass(name);
                }
            }
            return c;
        }
    }
}
