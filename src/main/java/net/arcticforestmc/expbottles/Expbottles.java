package net.arcticforestmc.expbottles;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public final class Expbottles extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getLogger().log(Level.INFO, "ExpBottles has been enabled");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
