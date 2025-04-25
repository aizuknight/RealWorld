package me.aizuknight.realworld.commands;

import me.aizuknight.realworld.modules.AbstractLcdDisplay;
import me.aizuknight.realworld.modules.python.LcdDisplayImpl;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MessageCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        AbstractLcdDisplay lcd = new LcdDisplayImpl();
        lcd.display(commandSender.getName() + ": " + String.join(" ", strings));
        return true;
    }
}
