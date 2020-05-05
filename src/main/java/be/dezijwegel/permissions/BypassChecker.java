package be.dezijwegel.permissions;

import be.dezijwegel.hooks.EssentialsHook;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BypassChecker {

    private final EssentialsHook essentialsHook;
    private final boolean isEnabled;
    private final Set<GameMode> bypassedGamemodes;

    /**
     * Creates an object that can check whether or not a player has got bypass permissions
     * @param bypassedGamemodes the list of gamemodes that are bypassed
     */
    public BypassChecker(boolean isEnabled, EssentialsHook essentialsHook, List<GameMode> bypassedGamemodes)
    {
        this.isEnabled = isEnabled;

        // Add all bypassed gamemodes
        this.bypassedGamemodes = new HashSet<>();
        this.bypassedGamemodes.addAll(bypassedGamemodes);

        this.essentialsHook = essentialsHook;
    }


    /**
     * Returns whether a player has bypass permissions or not
     * Can be based on a permission node or gamemode
     * @param player the player to be checked
     * @return whether this player should be bypassed or not
     */
    public boolean isPlayerBypassed(Player player)
    {
        boolean hasPermission       = player.hasPermission("bettersleeping.bypass") && isEnabled;
        boolean essentialsBypass    = player.hasPermission("essentials.sleepingignored") && essentialsHook.isHooked() && isEnabled;
        boolean gamemodeBypass      = bypassedGamemodes.contains( player.getGameMode() );

        return hasPermission || essentialsBypass || gamemodeBypass;
    }

}