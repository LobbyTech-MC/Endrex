package me.nahkd.spigot.sfaddons.endrex.items.liquid;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.skins.PlayerHead;
import me.nahkd.spigot.sfaddons.endrex.Endrex;
import me.nahkd.spigot.sfaddons.endrex.items.EndrexItems;
import me.nahkd.spigot.sfaddons.endrex.items.EndrexSkulls;

public class Liquids {

	public static CustomLiquid WATER;
	public static CustomLiquid LAVA;
	public static CustomLiquid RESONANT_ENDER;
	public static CustomLiquid MYSTHERIUM;
	
	public static void init(Endrex plugin) {
		WATER = new CustomLiquid(new NamespacedKey(plugin, "water"), new CustomItemStack(Material.WATER_BUCKET, "&f水"));
		LAVA = new CustomLiquid(new NamespacedKey(plugin, "lava"), new CustomItemStack(Material.LAVA_BUCKET, "&f岩浆"));
		RESONANT_ENDER = new CustomLiquid(new NamespacedKey(plugin, "resonant_ender"), new CustomItemStack(PlayerHead.getItemStack(EndrexSkulls.RESONANT_ENDER), "&3末影液"));
		MYSTHERIUM = new CustomLiquid(new NamespacedKey(plugin, "mystherium"), new CustomItemStack(PlayerHead.getItemStack(EndrexSkulls.MYSTHERIUM_LIQUID), "&5神秘液"));
	}
	public static void postInit() {
		WATER.bucket = new ItemStack(Material.WATER_BUCKET);
		WATER.crucibleSkull = EndrexSkulls.ENHANCED_CRUCIBLE_RESONANT_WATER;
		LAVA.bucket = new ItemStack(Material.LAVA_BUCKET);
		LAVA.crucibleSkull = EndrexSkulls.ENHANCED_CRUCIBLE_RESONANT_LAVA;
		RESONANT_ENDER.bucket = EndrexItems.RESONANT_ENDER_BUCKET.getItem();
		RESONANT_ENDER.crucibleSkull = EndrexSkulls.ENHANCED_CRUCIBLE_RESONANT_ENDER;
		MYSTHERIUM.bucket = EndrexItems.MYSTHERIUM_BUCKET.getItem();
		MYSTHERIUM.crucibleSkull = EndrexSkulls.ENHANCED_CRUCIBLE_MYSTHERIUM;
	}
	
}
