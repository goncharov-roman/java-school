package org.roman.plugin_manager;

import org.roman.plugin_manager.plugin_a.Plugin;

public class Main {

    public static void main(String[] args) {
        PluginManager pluginManager = new PluginManager("org.roman.plugin_manager");
        Plugin plugin = null;
        try {
            plugin = pluginManager.load("plugin_a", "PluginA");
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }

        System.out.println(plugin);

        Plugin pluginCL = null;
        try {
            pluginCL = pluginManager.loadWithCL("plugin_a", "PluginA");
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }

        System.out.println(pluginCL);
    }
}
