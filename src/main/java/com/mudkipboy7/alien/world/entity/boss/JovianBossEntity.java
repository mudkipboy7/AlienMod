package com.mudkipboy7.alien.world.entity.boss;

import com.mudkipboy7.alien.world.block.AMBlocks;
import com.mudkipboy7.alien.world.entity.AMEntities;
import com.mudkipboy7.alien.world.entity.IAlienMob;
import com.mudkipboy7.alien.world.entity.AIgoals.JovianBossFindNearestTargetGoal;
import com.mudkipboy7.alien.world.entity.monster.JovianBossMinion;
import com.mudkipboy7.alien.world.worldgen.dimension.AMDimensions;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class JovianBossEntity extends PathfinderMob implements IAlienMob, RangedAttackMob {
	ServerBossEvent bossEvent = (ServerBossEvent) (new ServerBossEvent(getDisplayName(), BossEvent.BossBarColor.WHITE,
			BossEvent.BossBarOverlay.PROGRESS));
	// Stores permananant changes to the bosses behavior.
	int phase = 0;
	// How many golden apples it has left
	int applesLeft = 64;
	// How many ticks until it heals
	int ticksLeftToFinishEating = 0;
	// How many arrows it has left to shoot
	int arrowsLeft = 64;
	boolean alreadyDoneCreativeTaunt = false;
	JovianBossStrategy strategy = JovianBossStrategy.CHASING;

	private static final int PLATFORM_RADIUS = 23;

	public JovianBossEntity(EntityType<? extends JovianBossEntity> entityType, Level level) {
		super(entityType, level);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.targetSelector.addGoal(0, new JovianBossFindNearestTargetGoal(this));
		goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, true));
		this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 8.0F));

	}

	@Override
	public void tick() {

		if (!level().isClientSide) {
			this.extinguishFire();
			this.resetFallDistance();
			if (this.blockPosition().getY() < 40) {
				this.playSound(SoundEvents.ENDERMAN_TELEPORT);
				this.teleportTo(0D, 68D, 0D);
				sendChatMessage(JovianBossLines.WHEN_KNOCKED_OFF_SIDE);
				this.level().setBlockAndUpdate(new BlockPos(0, 67, 0),
						AMBlocks.HARDENED_CLOUD.get().defaultBlockState());
			}
			// if(this.attack() != null) {

			// }
			// else {
			this.setSprinting(true);
			// }

			if (this.getHealth() < (this.getMaxHealth() / 2.0F) && phase == 0) {
				this.phase = 1;
			}
			if (this.getHealth() < (this.getMaxHealth() / 3.0F) && random.nextInt(50) == 0) {
				this.spawnZombies(this.position(), 3);
				this.playSound(SoundEvents.ENDERMAN_TELEPORT);
				this.teleportTo(random.nextInt(PLATFORM_RADIUS - 4), 67, random.nextInt(PLATFORM_RADIUS - 4));
				this.tryStartHealWithApple();
			} else if (random.nextInt(40) == 0 && phase != 0) {
				this.spawnZombies(this.position(), 1);
				this.tryStartHealWithApple();
			}
			// this.tryStartHealWithApple();
			this.tickAppleHealing();
			bossEvent.setProgress(1 * (this.getHealth() / this.getMaxHealth()));
		}
		super.tick();
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Monster.createMobAttributes().add(Attributes.MAX_HEALTH, 250.0D).add(Attributes.MOVEMENT_SPEED, 0.29D)
				.add(Attributes.ATTACK_DAMAGE, 1.0D).add(Attributes.FOLLOW_RANGE, 100.0D);
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return null;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource pDamageSource) {
		return SoundEvents.PLAYER_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.PLAYER_DEATH;
	}

	@Override
	public void sendSystemMessage(Component pComponent) {
		Minecraft.getInstance().gui.getChat().addMessage(pComponent);
	}

	@Override
	public void onAddedToWorld() {

		if (!this.level().isClientSide()) {
			// level().getServer().close();
			// int applesLeft = 64;
			this.setItemSlot(EquipmentSlot.MAINHAND, Items.DIAMOND_SWORD.getDefaultInstance());
			simulateJoinGame();
			// this.heal(getMaxHealth());
		}

		super.onAddedToWorld();
	}

	@Override
	public void die(DamageSource pDamageSource) {
		if (!this.level().isClientSide()) {
			this.readAdditionalSaveData(getPersistentData());
			sendChatMessage(JovianBossLines.DEATH);
			sendSystemMessage(pDamageSource.getLocalizedDeathMessage(this));
			sendLeaveGameMessage();
			level().setBlockAndUpdate(new BlockPos(0, 67, 0), AMBlocks.JOVIAN_RETURN_PORTAL.get().defaultBlockState());
		}
		super.die(pDamageSource);

	}

	/**
	 * Add the given player to the list of players tracking this entity. For
	 * instance, a player may track a boss in order to view its associated boss bar.
	 */
	@Override
	public void startSeenByPlayer(ServerPlayer pPlayer) {
		super.startSeenByPlayer(pPlayer);
		bossEvent.addPlayer(pPlayer);
	}

	/**
	 * Removes the given player from the list of players tracking this entity. See
	 * {@link Entity#addTrackingPlayer} for more information on tracking.
	 */
	@Override
	public void stopSeenByPlayer(ServerPlayer pPlayer) {
		super.stopSeenByPlayer(pPlayer);
		bossEvent.removePlayer(pPlayer);
	}

	private boolean sendLeaveGameMessage() {
		if (!this.level().isClientSide()) {
			sendSystemMessage(
					Component.translatable("multiplayer.player.left", this.getName()).withStyle(ChatFormatting.YELLOW));
			return true;
		}
		return false;
	}

	private boolean simulateJoinGame() {
		if (!this.level().isClientSide()) {
			sendSystemMessage(Component.translatable("multiplayer.player.joined", this.getName())
					.withStyle(ChatFormatting.YELLOW));
			if (this.level().dimension() != AMDimensions.JOVIANDIM_LEVEL) {
				sendChatMessage(JovianBossLines.WHEN_SUMMONED_IN_WRONG_DIMENSION);
				this.remove(RemovalReason.DISCARDED);
				this.sendLeaveGameMessage();
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean hurt(DamageSource pDamageSource, float pDamageAmount) {
		if (pDamageSource.getEntity() != null && pDamageSource.getEntity() instanceof Player player
				&& player.isCreative()) {
			if (!alreadyDoneCreativeTaunt)
				sendChatMessage(JovianBossLines.WHEN_ATTACKED_IN_CREATIVE_MODE);
			this.alreadyDoneCreativeTaunt = true;
			return false;
		}
		return super.hurt(pDamageSource, pDamageAmount);
	}

	@Override
	protected void actuallyHurt(DamageSource pDamageSource, float pDamageAmount) {
		super.actuallyHurt(pDamageSource, pDamageAmount);
	}

	private void sendChatMessage(String message) {
		sendSystemMessage(Component.translatable(message, this.getName()));
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putInt("phase", this.phase);
		compound.putInt("apples_left", this.applesLeft);
		compound.putString("apples_left", this.strategy.getName());

	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		this.phase = compound.getInt("phase");
		/*
		 * This is a fix so I can check if its already been spawned or not. If I don't
		 * do this it'll overwrite applesLeft with 0 when first spawned in because 0 is
		 * the default value and I can't specify a default one. If I don't do this it'll
		 * always be set to 0 apples left when spawned in for the first time.
		 */
		if (compound.contains("apples_left")) {
			this.applesLeft = compound.getInt("apples_left");
		}
		if (compound.contains("strategy")) {
			this.strategy = JovianBossStrategy.valueOf(compound.getString("strategy"));
		}
		// this.ticksLeftToFinishEating = compound.getInt("ticks_left_to_eat_apple");
	}

	private boolean tryStartHealWithApple() {

		if (this.getHealth() < this.getMaxHealth() && this.applesLeft > 0 && this.ticksLeftToFinishEating <= 0) {
			this.setItemSlot(EquipmentSlot.MAINHAND, Items.GOLDEN_APPLE.getDefaultInstance());
			this.playSound(getEatingSound(Items.GOLDEN_APPLE.getDefaultInstance()));
			// System.out.println("fewfwefwefewfwef");
			this.ticksLeftToFinishEating = 30;
			this.setSilent(false);

			return true;
		}
		return false;
	}

	private boolean tickAppleHealing() {
		if (this.ticksLeftToFinishEating > 1) {
			this.playSound(getEatingSound(Items.GOLDEN_APPLE.getDefaultInstance()));
			this.setItemSlot(EquipmentSlot.MAINHAND, Items.GOLDEN_APPLE.getDefaultInstance());
			ticksLeftToFinishEating--;

		} else if (this.ticksLeftToFinishEating == 1) {
			this.playSound(SoundEvents.PLAYER_BURP);
			ticksLeftToFinishEating = 0;
			this.applesLeft--;
			this.setItemSlot(EquipmentSlot.MAINHAND, Items.DIAMOND_SWORD.getDefaultInstance());
			this.heal(10);
			return true;
		}
		return false;
	}

	public int getTicksLeftToFinishEating() {
		return ticksLeftToFinishEating;
	}

	public void spawnZombies(Vec3 pos, int ammount) {

		for (int i = 0; i < ammount; i++) {
			double x = pos.x + random.nextInt(2);
			double y = pos.y;
			double z = pos.z + random.nextInt(2);
			JovianBossMinion zombie = new JovianBossMinion(AMEntities.JOVIAN_BOSS_MINION.get(), this.level());
			zombie.setPos(new Vec3(x, y, z));
			this.level().addFreshEntity(zombie);
		}
	}

	@Override
	public void performRangedAttack(LivingEntity pTarget, float pVelocity) {

	}
}
