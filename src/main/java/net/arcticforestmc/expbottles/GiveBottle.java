package net.arcticforestmc.expbottles;

import net.arcticforestmc.expbottles.DataManagers.DataContainer;
import net.arcticforestmc.expbottles.Utilities.Utils;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class GiveBottle implements CommandExecutor {

    private DataContainer dataContainer = Utils.getRightContainer();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String Labels, String[] args){
        if(!(sender instanceof Player)){
            Bukkit.getLogger().log(Level.INFO, "Only players can execute this command");
            return true;
        }
        Player player = (Player) sender;

        if(args.length == 0){
            player.sendMessage(ChatColor.RED + "You have to provide an amount of experience to withdraw");
        }else{
            giveBottle(player, args[0]);
        }

        return true;
    }

    private ItemStack bottle = new ItemStack(Utils.getRightBottle());

    public void giveBottle(Player player, String arg) {
        int playerExp = Utils.getTotalExperience(player);
        int withdrawExp = 0;

        try{
            withdrawExp = Integer.parseInt(arg);
        }
        catch (NumberFormatException ex){
            ex.printStackTrace();
        }

        if(playerExp < withdrawExp){
            player.sendMessage(ChatColor.RED + "You do not have enough experience for that");
            return;
        }

        String updatedArg = String.valueOf(withdrawExp);
        if(updatedArg.equals("0")){
            player.sendMessage("You cannot withdraw 0 xp");
            return;
        }

        int newExp = playerExp - withdrawExp;

        //Set NBT Tag
        bottle = (ItemStack) dataContainer.set(bottle, "Custom-Bottle-Identifier", updatedArg);

        //Update displayname and lore
        List<String> lore = new ArrayList<>();
        ItemMeta meta = bottle.getItemMeta();

        meta.setDisplayName(ChatColor.GREEN + "Experience Bottle");
        lore.add("Amount of experience: " + withdrawExp);
        lore.add("");

        meta.setLore(lore);
        bottle.setItemMeta(meta);

        //Give the bottle
        player.getInventory().addItem(bottle);
        Utils.setTotalExperience(player, newExp);

        System.out.println(dataContainer.has(bottle, "Custom-Bottle-Identifier"));
    }
}