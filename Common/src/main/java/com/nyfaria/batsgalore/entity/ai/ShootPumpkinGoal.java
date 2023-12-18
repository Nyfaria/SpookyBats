package com.nyfaria.batsgalore.entity.ai;

import com.nyfaria.batsgalore.entity.api.SpookyBat;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.entity.projectile.ThrownEgg;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class ShootPumpkinGoal extends Goal {
	private final SpookyBat bat;
	private int cooldown;

	public ShootPumpkinGoal(SpookyBat bat) {
		this.bat = bat;
	}

	@Override
	public boolean canUse() {
		return this.bat != null && this.bat.getTarget() != null;
	}

	@Override
	public void start() {
		this.cooldown = 0;
	}

	@Override
	public void tick() {
		Level world = bat.level();
		LivingEntity attackTarget = this.bat.getTarget();
		ItemStack pumpkin = new ItemStack(Items.PUMPKIN);

		ThrowableItemProjectile projectile = new ThrownEgg(world, bat);
		projectile.setItem(pumpkin);
		projectile.shootFromRotation(this.bat, this.bat.getXRot(), this.bat.getYRot(), 0.0F, 0.72F, 0F);

		// TODO: Is this logic okay?
		if (attackTarget == null || !canAttack(attackTarget)) return;
		++this.cooldown;

		if (this.cooldown == 15) {
			world.addFreshEntity(projectile);
			this.cooldown = 0;
		}
	}

	private boolean canAttack(LivingEntity target) {
		return target.distanceToSqr(this.bat) < 81 && this.bat.hasLineOfSight(target);
	}
}
