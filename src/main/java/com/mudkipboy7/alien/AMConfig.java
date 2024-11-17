package com.mudkipboy7.alien;

import com.mudkipboy7.alien.data.AMLanguage;

import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.Builder;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = AlienMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class AMConfig {
	private static String configString = AMLanguage.Affixes.configPrefix + AlienMod.MODID;

	public static class Server {
		// public static BooleanValue serverDebugMode;

		public Server(Builder builder) {
			// builder.push("server");
			// serverDebugMode = builder.comment("Turns off or on some serverside debug
			// functions.")
			// .translation(AMLang.serverDevModeCfg)
			// .define("serverdebugmode", false);
		}
	}

	public static class Client {
		public static BooleanValue renderFurCoatFirstPerson;

		public Client(Builder builder) {

			builder.push("client");

			renderFurCoatFirstPerson = builder
					.comment("Determines if the Fur Coat worn item is displayed over the arm in first person.")
					.translation(configString + "renderfurcoatfirstperson")
					.define("renderfurcoatfirstperson", true);

		}
	}

	public static class Common {

		public Common(Builder builder) {

		}
	}
}
