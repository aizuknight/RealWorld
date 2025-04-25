package me.aizuknight.realworld.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.Map;

public class PiScoreboardManager {
    private final Scoreboard scoreboard;
    private final Team[] thiTeam = new Team[2];
    private final Team[] distanceTeam = new Team[2];
    // {ValueTeamName, ValuePrefix, MessageTeamName, MessagePrefix}
    private final String[] thi = {"thi", ChatColor.AQUA + "THI: ", "thiMessage", ChatColor.RESET.toString()};
    private final String[] lastThi = {ChatColor.GRAY + "?", ""};
    private final String[] distance = {"distance", ChatColor.AQUA + "Distance: ", "distanceMessage", ChatColor.RESET + "" + ChatColor.RESET};
    private final String[] lastDistance = {ChatColor.GRAY + "? cm", ""};

    public PiScoreboardManager() {
        scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        init();
    }

    private void init() {
        thiTeam[0] = scoreboard.registerNewTeam(thi[0]);
        thiTeam[0].addEntry(thi[1]);
        thiTeam[0].setSuffix(lastThi[0]);

        thiTeam[1] = scoreboard.registerNewTeam(thi[2]);
        thiTeam[1].addEntry(thi[3]);
        thiTeam[1].setSuffix(lastThi[1]);

        distanceTeam[0] = scoreboard.registerNewTeam(distance[0]);
        distanceTeam[0].addEntry(distance[1]);
        distanceTeam[0].setSuffix(lastDistance[0]);

        distanceTeam[1] = scoreboard.registerNewTeam(distance[2]);
        distanceTeam[1].addEntry(distance[3]);
        distanceTeam[1].setSuffix(lastDistance[1]);

        Objective sidebarObjective = scoreboard.registerNewObjective("sidebar", "dummy");
        sidebarObjective.setDisplaySlot(DisplaySlot.SIDEBAR);
        sidebarObjective.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + ChatColor.ITALIC + "RASPBERRY PI");
        sidebarObjective.getScore(ChatColor.GRAY + "==============================").setScore(11);
        sidebarObjective.getScore("   ").setScore(10);
        sidebarObjective.getScore(ChatColor.YELLOW + "Welcome, Anonymous!").setScore(9);
        sidebarObjective.getScore("  ").setScore(8);
        sidebarObjective.getScore(thi[1]).setScore(7);
        sidebarObjective.getScore(thi[3]).setScore(6);
        sidebarObjective.getScore(" ").setScore(5);
        sidebarObjective.getScore(distance[1]).setScore(4);
        sidebarObjective.getScore(distance[3]).setScore(3);
        sidebarObjective.getScore("").setScore(2);
        sidebarObjective.getScore(ChatColor.YELLOW + "localhost").setScore(1);
        sidebarObjective.getScore(ChatColor.GRAY + "------------------------------").setScore(0);
    }

    public void set(Player player) {
        player.setScoreboard(scoreboard);
    }

    public void updateThi(Map.Entry<String, String> thi) {
        for(Player player : Bukkit.getOnlinePlayers()) {
            Scoreboard scoreboard = player.getScoreboard();
            scoreboard.getTeam(this.thi[0]).setSuffix(thi.getKey());
            scoreboard.getTeam(this.thi[2]).setSuffix(thi.getValue());
        }
        lastThi[0] = thi.getKey();
        lastThi[1] = thi.getValue();
        thiTeam[0].setSuffix(thi.getKey());
        thiTeam[1].setSuffix(thi.getValue());
    }

    public void updateDistance(Map.Entry<String, String> distance) {
        for(Player player : Bukkit.getOnlinePlayers()) {
            Scoreboard scoreboard = player.getScoreboard();
            scoreboard.getTeam(this.distance[0]).setSuffix(distance.getKey());
            scoreboard.getTeam(this.distance[2]).setSuffix(distance.getValue());
        }
        lastDistance[0] = distance.getKey();
        lastDistance[1] = distance.getValue();
        distanceTeam[0].setSuffix(distance.getKey());
        distanceTeam[1].setSuffix(distance.getValue());
    }
}
