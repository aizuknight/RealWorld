package me.aizuknight.realworld.commands;

import me.aizuknight.realworld.utilities.BashUtilities;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class GpioCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(strings.length < 2) {
            commandSender.sendMessage(ChatColor.RED + "/gpio <operation> <pin>");
            return true;
        }
        String operation = strings[0];
        int pin;
        try {
            pin = Integer.parseInt(strings[1]);
        }
        catch (NumberFormatException ignored) {
            commandSender.sendMessage(ChatColor.RED + "illegal number format");
            return true;
        }
        switch(operation) {
            case "in":
                read(pin);
                break;
            case "blink":
                if(strings.length < 3) {
                    commandSender.sendMessage(ChatColor.RED + "/gpio blink <pin> <time>");
                    return true;
                }
                int time;
                try {
                    time = Integer.parseInt(strings[2]);
                } catch (NumberFormatException ignored) {
                    commandSender.sendMessage(ChatColor.RED + "illegal number format");
                    return true;
                }
                blink(pin, time);
                break;
            default:
                commandSender.sendMessage(ChatColor.RED + "/gpio <operation> <pin>");
        }
        return true;
    }

    private void read(int pin) {
        BashUtilities.execute("python3 /home/aizuknight/python/read.py " + pin);
    }

    private void blink(int pin, int time) {
        BashUtilities.execute("python3 /home/aizuknight/python/blink.py " + pin + " " + time);
    }
}
