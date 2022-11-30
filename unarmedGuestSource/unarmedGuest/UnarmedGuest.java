package unarmedGuest;

import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.block.*;
import org.bukkit.permissions.*;
import org.bukkit.plugin.java.*;

public class UnarmedGuest extends JavaPlugin implements Listener, Runnable
{
	@Override
	public void onEnable()
	{
		Bukkit.getPluginManager().registerEvents(this, this);
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, this, 0, 600);
	}
	
	@Override
	public void run()
	{
		for (Player player : Bukkit.getOnlinePlayers())
		{
			if (isGuest(player))
				player.getInventory().clear();
		}
	}
	
	public static boolean isGuest(Permissible permissible)
	{
		return !permissible.hasPermission("guestmode.play");
	}
	
	@EventHandler
	public void onBlockDispenseArmor(BlockDispenseArmorEvent event)
	{
		if (isGuest(event.getTargetEntity()))
			event.setCancelled(true);
	}
}