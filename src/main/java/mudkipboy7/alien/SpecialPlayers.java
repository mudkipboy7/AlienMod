package mudkipboy7.alien;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import com.electronwill.nightconfig.core.CommentedConfig;
import com.google.gson.JsonObject;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

public class SpecialPlayers {
	// Filenames
	private static final String specialPlayersDir = "mudkipboy7.alien.specialplayers.toml";
	private static final String specialPlayerTypesDir = "mudkipboy7.alien.specialplayertypes.json";

	/*
	 * Determines if the player has a custom survival gear texture or not as well as
	 * what the texturepath is.
	 */
	private static final HashMap<String, SpecialPlayerType> specialPlayerTypes = new HashMap<String, SpecialPlayerType>();
	private static final HashMap<String, String> specialPlayerSettingsUsernames = new HashMap<String, String>();
	private static final HashMap<String, String> specialPlayerSettingsUUIDS = new HashMap<String, String>();

	/**
	 * This reads the alien.specialplayers.json and adds all of the items in all of
	 * the lists to their respective lists in this file
	 */
	public static void loadSpecialPlayersStuff() {
		SpecialPlayers.specialPlayerSettingsUsernames.clear();

		/*
		 * Gets the special player types
		 */
		JsonObject jsonFile = AMMethods.getJson(specialPlayerTypesDir).getAsJsonObject();
		jsonFile.keySet().forEach(entry -> {
			if (jsonFile.get(entry) instanceof JsonObject playerType) {
				String name = entry;
				SpecialPlayerType p = new SpecialPlayerType(playerType);
				if (name != null && p != null)
					specialPlayerTypes.put(name, p);
			}
		});
		/*
		 * Gets the special players
		 */
		CommentedConfig specialPlayersTOML = AMMethods.getTOML(specialPlayersDir);
		Map<String, Object> specialPlayersMap = specialPlayersTOML.valueMap();
		if (specialPlayersMap.getOrDefault("USERNAMES", null) instanceof CommentedConfig cfg)
			cfg.valueMap().forEach((username, setting) -> {
				if (specialPlayerTypes.containsKey(setting) && setting instanceof String s)
					specialPlayerSettingsUsernames.put(username, s);
			});
		if (specialPlayersMap.getOrDefault("UUIDS", null) instanceof CommentedConfig cfg) {
			cfg.valueMap().forEach((uuid, setting) -> {
				if (specialPlayerTypes.containsKey(setting) && setting instanceof String s)
					specialPlayerSettingsUUIDS.put(uuid, s);
			});
		}
	}

	/**
	 * 
	 * @param player
	 * @return The player's special player settings if they have any or null
	 *         elsewise
	 */
	public static SpecialPlayerType getSpecialPlayersType(Player player) {
		String playerName = player.getName().getString();
		String playerUUID = player.getStringUUID();
		String typeID = specialPlayerSettingsUsernames.containsKey(playerName)
				? specialPlayerSettingsUsernames.get(playerName)
				: (specialPlayerSettingsUUIDS.containsKey(playerUUID) ? specialPlayerSettingsUUIDS.get(playerUUID)
						: null);
		if (typeID != null && specialPlayerTypes.containsKey(typeID))
			return specialPlayerTypes.get(typeID);
		return null;
	}

	/**
	 * Defines a specific setting set for special players
	 */
	public static final class SpecialPlayerType {
		private static final ResourceLocation defaultSurvivalGearTexture = AlienMod
				.location("textures/models/survivalgear/survival_gear.png");
		private ResourceLocation survivalGearTexture = defaultSurvivalGearTexture;
		private ArrayList<String> flags = new ArrayList<String>();

		public SpecialPlayerType(CommentedConfig player) {
			/*
			 * Does the stuff with survival gear
			 */

			if (player.get("survival_gear_texture") instanceof String path && path != null) {
				survivalGearTexture = new ResourceLocation(path);
			}
			if (player.get("flags") instanceof Collection<?> list && list != null) {
				list.forEach(c -> flags.add(((String) c)));
			}
		}

		public SpecialPlayerType(JsonObject player) {
			/*
			 * Does the stuff with survival gear
			 */
			if (player.has("survival_gear_texture"))
				this.survivalGearTexture = new ResourceLocation(player.get("survival_gear_texture").getAsString());
			if (player.has("flags") && player.get("flags").isJsonArray()) {
				player.getAsJsonArray("flags").forEach(c -> flags.add(c.getAsString()));
			}
		}

		public SpecialPlayerType(Yaml player) {

		}

		public ResourceLocation getSurvivalGearTexture() {
			return survivalGearTexture;
		}

		public ArrayList<String> getFlags() {
			return flags;
		}

		public boolean hasFlag(String specialThing) {
			return flags.contains(specialThing);
		}
	}

}
