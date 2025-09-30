package us.mudkip989.plugins.bge;

import org.bukkit.plugin.java.JavaPlugin;

public final class BGE extends JavaPlugin {

    public static BGE instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
