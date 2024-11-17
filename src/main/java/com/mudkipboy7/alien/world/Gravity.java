package com.mudkipboy7.alien.world;

import com.mudkipboy7.alien.data.tags.AMBiomeTags;

import net.minecraft.core.Holder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.phys.Vec3;

public class Gravity {

	/*
	 * Contains a bunch of stuff related to changing the gravity in dimensions.
	 */

	// These are used to modify an entity's fall
	public static double alienDimLivingFallMod = 0.045D;
	public static double alienDimItemFallMod = 0.02D;

	// These are used to modify an entity's jump
	public static double alienDimJumpMod = 1.15D;

	/**
	 * This function makes the entity fall slower. Should be called every tick. This
	 * function works together with with doHighJump() to make the AlienDim seem like
	 * it has low gravity.
	 * 
	 * @param entity  The entity that will be made to fall slower
	 * @param fallMod The modifier for the entity's fall
	 */
	public static void doSlowFall(Entity entity, double fallMod) {
		// Gets livingEntity's movement.
		Vec3 movement = entity.getDeltaMovement();
		
		// Checks if the entity is actually falling.
		if (movement.y < 0) {
			/*
			 * This checks for a list of set conditions. If any are true it doesn't change
			 * the fall rate.
			 */
			if (!(entity.isInFluidType() || (entity instanceof LivingEntity livingEntity && livingEntity.isFallFlying())
					|| (entity instanceof Player player && player.getAbilities().flying))) {

				/*
				 * The double fallyness is used below this to change the rate at which the
				 * entity falls. The higher the number being added to movement.y the slower the
				 * entity falls. This value will always be negative.
				 */
				double fallyness = -Math.abs(movement.y + fallMod);

				// This actually changes the falling
				entity.setDeltaMovement(movement.x, fallyness, movement.z);
			}

		}
	}

	/**
	 * Whenever this function is called the entity will be made to jump higher. This
	 * function works together with with doSlowFall() to make the AlienDim seem like
	 * it has low gravity.
	 * 
	 * @param livingEntity The Entity that is made to jump differently
	 * 
	 * @param jumpMod      The modifier for the entity's jump
	 */
	public static void doHighJump(LivingEntity livingEntity, double jumpMod) {
		// Gets livingEntity's movement.
		Vec3 movement = livingEntity.getDeltaMovement();
		/*
		 * This double is used to change the jump height. The higher jumpMod is, the
		 * higher the entity will jump. This works by multiplying the current y movement
		 * of the entity by a set amount. This value will always be negative.
		 */
		double jumpyness = Math.abs(movement.y * jumpMod);

		// This actually does the jumping. Uses jumpyness to change the jump height.
		livingEntity.setDeltaMovement(movement.x, jumpyness, movement.z);

	}

	public static boolean entityIsInLowGravityBiome(Entity entity) {
		Holder<Biome> biome = entity.level().getBiome(entity.blockPosition());
		return biome.is(AMBiomeTags.LOW_GRAVITY);

	}
}
