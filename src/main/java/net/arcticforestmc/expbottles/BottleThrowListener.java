package net.arcticforestmc.expbottles;

import net.arcticforestmc.expbottles.DataManagers.DataContainer;
import net.arcticforestmc.expbottles.Utilities.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ExpBottleEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.logging.Level;

public class BottleThrowListener implements Listener {

    private DataContainer dataContainer = Utils.getRightContainer();

    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        if(e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {return;}

        Player player = e.getPlayer();
        ItemStack bottle = player.getInventory().getItemInMainHand();

        if(!(bottle.getType().equals(Utils.getRightBottle()))){
            return;
        }
        dataContainer = Utils.getRightContainer();

        if(dataContainer == null){
            Bukkit.getLogger().log(Level.SEVERE, "DataContainer is null because server version is not 1.12.2");
        }

        if(dataContainer.has(bottle, "Custom-Bottle-Identifier")){
            int totalExp = Utils.getTotalExperience(player);
            String giveExp = dataContainer.get(bottle, "Custom-Bottle-Identifier");

            int giveExpInt = 0;
            try{
                giveExpInt = Integer.parseInt(giveExp);
            }
            catch (NumberFormatException ex){
                ex.printStackTrace();
            }

            player.giveExp(giveExpInt);

            for(ItemStack is : player.getInventory()){
                if(is == null){continue;}
                if(is.equals(bottle)){
                    is.setAmount(is.getAmount() - 1);
                    e.setCancelled(true);
                }
            }
        }
    }
}
