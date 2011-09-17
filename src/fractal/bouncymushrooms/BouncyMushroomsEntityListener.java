package fractal.bouncymushrooms;

import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityListener;

public class BouncyMushroomsEntityListener extends EntityListener {

	
	
	@Override
	public void onEntityDamage(EntityDamageEvent evt) {
		if (evt.isCancelled()) {
			return;
		} else if (evt.getCause() == EntityDamageEvent.DamageCause.FALL) {
			try {
				Player p = (Player)evt.getEntity();
				int blockId = p.getLocation().getBlock().getRelative(BlockFace.DOWN).getTypeId();
				if (blockId == 99 || blockId == 100 ) {
					evt.setCancelled(true);
				}
			} catch (ClassCastException cce) {
				
			}
		}
	}
	
}
