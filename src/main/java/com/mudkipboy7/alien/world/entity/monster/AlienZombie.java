package com.mudkipboy7.alien.world.entity.monster;

import com.mudkipboy7.alien.world.WorldFuncs;
import com.mudkipboy7.alien.world.block.AMFluids;
import com.mudkipboy7.alien.world.entity.IAlienMob;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.fluids.FluidType;

public class AlienZombie extends Monster implements IAlienMob {

	public AlienZombie(EntityType<? extends AlienZombie> entityType, Level level) {
		super(entityType, level);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, Player.class, false));
		// this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this,
		// Villager.class, false));
		goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, true));
		this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(2, new RandomLookAroundGoal(this));

	}

	public static AttributeSupplier.Builder createAttributes() {
		return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 20.0D).add(Attributes.FOLLOW_RANGE, 40.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.25D).add(Attributes.ATTACK_DAMAGE, 3.0D);
	}

	@Override
	public void aiStep() {
		if (this.isAlive()) {
			if (!WorldFuncs.inUltracoldOrEndBiome(this.level(), this.blockPosition())
					&& !this.isInFluidType(AMFluids.AMMONIA_LIQUID_TYPE.get())) {
				this.setSecondsOnFire(8);
			} else {
				boolean flag = this.isSunBurnTick();
				if (flag) {
					ItemStack itemstack = this.getItemBySlot(EquipmentSlot.HEAD);
					if (!itemstack.isEmpty()) {
						if (itemstack.isDamageableItem()) {
							itemstack.setDamageValue(itemstack.getDamageValue() + this.random.nextInt(2));
							if (itemstack.getDamageValue() >= itemstack.getMaxDamage()) {
								this.broadcastBreakEvent(EquipmentSlot.HEAD);
								this.setItemSlot(EquipmentSlot.HEAD, ItemStack.EMPTY);
							}
						}

						flag = false;
					}

					if (flag) {
						this.setSecondsOnFire(8);
					}
				}

			}
		}
		super.aiStep();

	}

	@Override
	public boolean canFluidExtinguish(FluidType type) {
		return super.canFluidExtinguish(type);
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.ZOMBIE_AMBIENT;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource pDamageSource) {
		return SoundEvents.ZOMBIE_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.ZOMBIE_DEATH;
	}
}
