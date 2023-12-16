package me.nahkd.spigot.sfaddons.endrex.recipes;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.skins.PlayerHead;
import me.nahkd.spigot.sfaddons.endrex.Endrex;
import me.nahkd.spigot.sfaddons.endrex.items.EndrexSkulls;

/**
 * Some sort of "enum". You can access to this class after Endrex is enabled.
 * @author nahkd123
 *
 */
public class EndrexRecipeType extends RecipeType {

	public static EndrexRecipeType LIQUID_STORAGE;
	public static EndrexRecipeType DUSTS_FABRICATOR;
	public static EndrexRecipeType KILL_ENDER_DRAGON;
	public static EndrexRecipeType RANDOMLY_GENERATED;
	
	public static void init(Endrex plugin) {
		LIQUID_STORAGE = new EndrexRecipeType(new NamespacedKey(plugin, "liquid_storage"), new CustomItemStack(PlayerHead.getItemStack(EndrexSkulls.RESONANT_ENDER_BUCKET), "&e液体储存", "&7从增强型末影电坩埚中获取"));
		DUSTS_FABRICATOR = new EndrexRecipeType(new NamespacedKey(plugin, "dusts_fabricator"), new CustomItemStack(PlayerHead.getItemStack(EndrexSkulls.DUSTS_FABRICATOR), "&b粉尘塑形机", "&7在粉尘塑形机中制取"));
		KILL_ENDER_DRAGON = new EndrexRecipeType(new NamespacedKey(plugin, "kill_ender_dragon"), new CustomItemStack(Material.DRAGON_EGG, "&e击杀末影龙", "&7干就完事了"));
		RANDOMLY_GENERATED = new EndrexRecipeType(new NamespacedKey(plugin, "randomly_generated"), new CustomItemStack(Material.IRON_ORE, "&b随机生成", "&7该方块在末地中随机生成"));
	}
	
	public EndrexRecipeType(NamespacedKey key, ItemStack item) {
		super(key, item);
	}

}
