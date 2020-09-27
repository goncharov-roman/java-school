package org.roman.plugin_manager;

import org.roman.plugin_manager.plugin_a.Plugin;

public class PluginManager {

    private final String pluginRootDirectory;

    public PluginManager(String pluginRootDirectory) {
        this.pluginRootDirectory = pluginRootDirectory;
    }

    public Plugin load(String pluginName, String pluginClassName)
            throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        String pluginPath = getPluginPath(pluginName, pluginClassName);

        return (Plugin) PluginManager.class.getClassLoader().loadClass(pluginPath).newInstance();
    }

    public Plugin loadWithCL(String pluginName, String pluginClassName) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        String pluginPath = getPluginPath(pluginName, pluginClassName);
        ClassLoader classLoader = new PluginClassLoader();

        return (Plugin) classLoader.loadClass(pluginPath).newInstance();
    }

    private String getPluginPath(String pluginName, String pluginClassName) {
        return new StringBuilder()
                .append(pluginRootDirectory)
                .append(".")
                .append(pluginName)
                .append(".")
                .append(pluginClassName)
                .toString();
    }
}
