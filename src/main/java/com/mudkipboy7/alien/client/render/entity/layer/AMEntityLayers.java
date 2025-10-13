package com.mudkipboy7.alien.client.render.entity.layer;

import com.mudkipboy7.alien.AlienMod;
import com.mudkipboy7.alien.client.render.entity.layer.survivalgear.SurvivalGearLayer;
import com.mudkipboy7.alien.client.render.entity.layer.survivalgear.SurvivalGearMouthpeiceLayer;
import com.mudkipboy7.alien.client.render.entity.model.AlienZombieModel;
import com.mudkipboy7.alien.client.render.entity.model.JovianBossModel;
import com.mudkipboy7.alien.client.render.entity.model.TestEntityModel;
import com.mudkipboy7.alien.client.render.entity.model.survivalgear.SurvivalGearFirstPersonModel;
import com.mudkipboy7.alien.client.render.entity.model.survivalgear.SurvivalGearModel;
import com.mudkipboy7.alien.client.render.entity.model.survivalgear.SurvivalGearMouthpeiceModel;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.resources.PlayerSkin;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = AlienMod.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class AMEntityLayers {
	/*
	 * Layer Locations
	 */
	public static final ModelLayerLocation SURVIVAL_GEAR_MOUTHPEICE = new ModelLayerLocation(
			AlienMod.location("survival_gear_mouthpeice"), "main");
	public static final ModelLayerLocation INNER_SURVIVAL_CLOTHES = new ModelLayerLocation(
			AlienMod.location("inner_survival_gear"), "main");
	public static final ModelLayerLocation OUTER_SURVIVAL_CLOTHES = new ModelLayerLocation(
			AlienMod.location("outer_survival_gear"), "main");
	public static final ModelLayerLocation SLIM_INNER_SURVIVAL_CLOTHES = new ModelLayerLocation(
			AlienMod.location("slim_inner_survival_gear"), "main");
	public static final ModelLayerLocation SLIM_OUTER_SURVIVAL_CLOTHES = new ModelLayerLocation(
			AlienMod.location("slim_outer_survival_gear"), "main");
	public static final ModelLayerLocation FIRST_PERSON_SURVIVAL_CLOTHES = new ModelLayerLocation(
			AlienMod.location("first_person_survival_gear"), "main");
	public static final ModelLayerLocation SLIM_FIRST_PERSON_SURVIVAL_CLOTHES = new ModelLayerLocation(
			AlienMod.location("slim_first_person_survival_gear"), "main");

	public static final ModelLayerLocation TEST_ENTITY = new ModelLayerLocation(AlienMod.location("test_entity"),
			"main");
	public static final ModelLayerLocation ALIEN_ZOMBIE = new ModelLayerLocation(AlienMod.location("alien_zombie"),
			"main");
	public static final ModelLayerLocation JOVIAN_BOSS = new ModelLayerLocation(AlienMod.location("jovian_boss"),
			"main");

	@SubscribeEvent
	public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(SURVIVAL_GEAR_MOUTHPEICE, () -> SurvivalGearMouthpeiceModel.createLayer());

		/*
		 * Wearable
		 */

		// Steve player model
		event.registerLayerDefinition(INNER_SURVIVAL_CLOTHES,
				() -> SurvivalGearModel.InnerModel.createBodyLayer(false));
		event.registerLayerDefinition(OUTER_SURVIVAL_CLOTHES,
				() -> SurvivalGearModel.OuterModel.createBodyLayer(false));
		event.registerLayerDefinition(FIRST_PERSON_SURVIVAL_CLOTHES,
				() -> SurvivalGearFirstPersonModel.createLayer(false));
		// Alex player model
		event.registerLayerDefinition(SLIM_INNER_SURVIVAL_CLOTHES,
				() -> SurvivalGearModel.InnerModel.createBodyLayer(true));
		event.registerLayerDefinition(SLIM_OUTER_SURVIVAL_CLOTHES,
				() -> SurvivalGearModel.OuterModel.createBodyLayer(true));
		event.registerLayerDefinition(SLIM_FIRST_PERSON_SURVIVAL_CLOTHES,
				() -> SurvivalGearFirstPersonModel.createLayer(true));

		/*
		 * Entity
		 */
		event.registerLayerDefinition(TEST_ENTITY, () -> TestEntityModel.createBodyLayer());
		event.registerLayerDefinition(ALIEN_ZOMBIE, () -> AlienZombieModel.createBodyLayer());
		event.registerLayerDefinition(JOVIAN_BOSS, () -> JovianBossModel.createBodyLayer());

	}

	@SuppressWarnings({ "rawtypes", "unused" })
	@SubscribeEvent
	public static void addEntityLayers(EntityRenderersEvent.AddLayers event) {
		/*
		 * Whenever you add a new one make sure to check your adding it to the right one
		 * on the left
		 */
		EntityRenderDispatcher renderDispatcher = Minecraft.getInstance().getEntityRenderDispatcher();
		EntityModelSet modelSet = Minecraft.getInstance().getEntityModels();
		EntityRendererProvider.Context context = event.getContext();
		ModelManager modelManager = context.getModelManager();

		PlayerRenderer playerRendererWide = event.getPlayerSkin(PlayerSkin.Model.WIDE);
		PlayerRenderer playerRendererSlim = event.getPlayerSkin(PlayerSkin.Model.SLIM);
		playerRendererWide.addLayer(new SurvivalGearLayer<>(playerRendererWide,
				new SurvivalGearModel.InnerModel(context.bakeLayer(INNER_SURVIVAL_CLOTHES)),
				new SurvivalGearModel.OuterModel(context.bakeLayer(OUTER_SURVIVAL_CLOTHES)), modelManager));
		playerRendererSlim.addLayer(new SurvivalGearLayer<>(playerRendererSlim,
				new SurvivalGearModel.InnerModel(context.bakeLayer(SLIM_INNER_SURVIVAL_CLOTHES)),
				new SurvivalGearModel.OuterModel(context.bakeLayer(SLIM_OUTER_SURVIVAL_CLOTHES)), modelManager));

		/*
		 * Make sure to load these last, I'm pretty sure if you don't it'll screw with
		 * the other ones.
		 */
		playerRendererWide.addLayer(new SurvivalGearMouthpeiceLayer<>(playerRendererWide, modelSet));
		playerRendererSlim.addLayer(new SurvivalGearMouthpeiceLayer<>(playerRendererSlim, modelSet));

	}

}
