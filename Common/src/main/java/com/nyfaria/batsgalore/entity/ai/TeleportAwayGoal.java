package com.nyfaria.batsgalore.entity.ai;

import com.nyfaria.batsgalore.entity.api.ModBat;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;

public class TeleportAwayGoal extends Goal {
	private final ModBat mob;

	public TeleportAwayGoal(ModBat mob) {
		this.mob = mob;
	}

	@Override
	public boolean canUse() {
		return mob != null && mob.getHealth() < mob.getMaxHealth() / 2 && mob.getLastHurtByMob() != null;
	}

	@Override
	public void start() {
		Vec3 batPos = mob.position();

		int randX = mob.getRandom().nextInt(7);
		int randY = mob.getRandom().nextInt(7);
		int randZ = mob.getRandom().nextInt(7);
		double destX = mob.getRandom().nextInt(2) == 0
			? batPos.x + randX
			: batPos.x - randX;
		double destY = mob.getRandom().nextInt(2) == 0
			? batPos.y + randY
			: batPos.y - randY;
		double destZ = mob.getRandom().nextInt(2) == 0
			? batPos.z + randZ
			: batPos.z - randZ;
		mob.randomTeleport(destX, destY, destZ, true);
	}
}