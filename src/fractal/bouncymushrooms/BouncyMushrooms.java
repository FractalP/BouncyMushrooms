package fractal.bouncymushrooms;

import java.util.HashMap;

import org.bukkit.World;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.util.Vector;

public class BouncyMushrooms extends JavaPlugin {
	
	public void onDisable() {
		this.getServer().getScheduler().cancelTasks(this); // cancel the async task scheduled below
        System.out.println("Bouncy Mushrooms de-bouncified");
    }
	
	public void onEnable() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvent(Event.Type.ENTITY_DAMAGE, new BouncyMushroomsEntityListener(), Priority.Normal, this);
		System.out.println("Mushrooms are now bouncy!");
		this.getServer().getScheduler().scheduleAsyncRepeatingTask(this, new Runnable () {
			public void run() {
				//System.out.println("Checking for bouncing!");
				for (World w : getServer().getWorlds()) {
					for (Player p : w.getPlayers()) {
						if (p.isSneaking()) {
							continue;
						}
						int blockId = p.getLocation().getBlock().getRelative(BlockFace.DOWN).getTypeId();
						if (blockId == 99 || blockId == 100) {
							Vector vel = p.getVelocity();
							vel.setY(2);
							p.setVelocity(vel);
						} 
					}
				}
			}
		}, 5, 10);
		
	}
	
	
}
