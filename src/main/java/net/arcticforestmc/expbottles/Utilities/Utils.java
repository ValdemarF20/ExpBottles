package net.arcticforestmc.expbottles.Utilities;

import net.arcticforestmc.expbottles.DataManagers.DataContainer;
import net.arcticforestmc.expbottles.DataManagers.LegacyDataContainer;
import net.arcticforestmc.expbottles.Expbottles;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class Utils {
    private Utils(){}

    public static DataContainer getRightContainer() {
        Expbottles expbottles = JavaPlugin.getPlugin(Expbottles.class);

        if (ServerVersion.CURRENT_VERSION.isOlderThan(ServerVersion.V1_14_R1)) {return new LegacyDataContainer(expbottles);}
        return null;
    }

    public static Material getRightBottle() {
        Expbottles expbottles = JavaPlugin.getPlugin(Expbottles.class);

        if(ServerVersion.CURRENT_VERSION.isNewerThan(ServerVersion.V1_14_R1)) {return Material.EXPERIENCE_BOTTLE;}
        return Material.valueOf("EXP_BOTTLE");
    }

    //This method is required because the bukkit player.getTotalExperience() method, shows exp that has been 'spent'.
    //Without this people would be able to use exp and then still sell it.
    public static int getTotalExperience(final Player player) {
        int exp = Math.round(getExpAtLevel(player) * player.getExp());
        int currentLevel = player.getLevel();

        while (currentLevel > 0) {
            currentLevel--;
            exp += getExpAtLevel(currentLevel);
        }
        if (exp < 0) {
            exp = Integer.MAX_VALUE;
        }
        return exp;
    }

    private static int getExpAtLevel(final Player player) {
        return getExpAtLevel(player.getLevel());
    }

    public static int getExpAtLevel(final int level) {
        if (level <= 15) {
            return (2 * level) + 7;
        }
        if ((level >= 16) && (level <= 30)) {
            return (5 * level) - 38;
        }
        return (9 * level) - 158;
    }

    //This method is used to update both the recorded total experience and displayed total experience.
    //We reset both types to prevent issues.
    public static void setTotalExperience(final Player player, final int exp) {
        if (exp < 0) {
            throw new IllegalArgumentException("Experience is negative!");
        }
        player.setExp(0);
        player.setLevel(0);
        player.setTotalExperience(0);

        //This following code is technically redundant now, as bukkit now calulcates levels more or less correctly
        //At larger numbers however... player.getExp(3000), only seems to give 2999, putting the below calculations off.
        int amount = exp;
        while (amount > 0) {
            final int expToLevel = getExpAtLevel(player);
            amount -= expToLevel;
            if (amount >= 0) {
                // give until next level
                player.giveExp(expToLevel);
            } else {
                // give the rest
                amount += expToLevel;
                player.giveExp(amount);
                amount = 0;
            }
        }
    }
}
