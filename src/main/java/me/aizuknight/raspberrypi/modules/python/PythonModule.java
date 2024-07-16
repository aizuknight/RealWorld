package me.aizuknight.raspberrypi.modules.python;

import org.bukkit.Bukkit;

import java.util.logging.Logger;

public abstract class PythonModule {
    protected String command;
    protected ProcessBuilder processBuilder;
    protected final Logger logger = Bukkit.getLogger();
}