package me.nahkd.spigot.sfaddons.endrex.items;

import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.MachineTier;
import io.github.thebusybiscuit.slimefun4.core.attributes.MachineType;
import io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem;
import io.github.thebusybiscuit.slimefun4.core.handlers.EntityKillHandler;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.skins.PlayerHead;
import io.github.thebusybiscuit.slimefun4.utils.LoreBuilder;
import me.nahkd.spigot.sfaddons.endrex.Endrex;
import me.nahkd.spigot.sfaddons.endrex.items.liquid.LiquidBucket;
import me.nahkd.spigot.sfaddons.endrex.items.liquid.Liquids;
import me.nahkd.spigot.sfaddons.endrex.items.machines.DustsFabricator;
import me.nahkd.spigot.sfaddons.endrex.items.machines.EnhancedElectricCrucible;
import me.nahkd.spigot.sfaddons.endrex.items.misc.EndRespawnAnchor;
import me.nahkd.spigot.sfaddons.endrex.items.misc.MysteriousTeleporter;
import me.nahkd.spigot.sfaddons.endrex.items.mysterious.MysteriousEquipment;
import me.nahkd.spigot.sfaddons.endrex.items.mysterious.RandomEnchantmentEntry;
import me.nahkd.spigot.sfaddons.endrex.items.resources.EndrexMineableResource;
import me.nahkd.spigot.sfaddons.endrex.items.resources.EndrexResourceItem;
import me.nahkd.spigot.sfaddons.endrex.recipes.EndrexRecipeType;
import me.nahkd.spigot.sfaddons.endrex.utils.EndrexLoreBuilder;
import me.nahkd.spigot.sfaddons.endrex.utils.InventoryUtils;
import me.nahkd.spigot.sfaddons.endrex.utils.ItemStackWrapper;

public class EndrexItems {
	
	public static ItemGroup CATEGORY_RESOURCES;
	public static EndrexItem ENDER_PEARL_ORE;
	public static EndrexItem CHORUS_ORE;
	public static EndrexItem END_STONE_COAL_ORE;
	public static EndrexItem END_STONE_IRON_ORE;
	public static EndrexItem END_STONE_LAPIS_ORE;
	public static EndrexItem END_STONE_REDSTONE_ORE;
	public static EndrexItem END_STONE_GOLD_ORE;
	public static EndrexItem RESONANT_ENDER_BUCKET;
	public static EndrexItem RESONANT_ENDER_DUST;
	public static EndrexItem ENDERIUM;
	public static EndrexItem ENDERIUM_BLOCK;
	public static EndrexItem DRAGON_SCALE;
	public static EndrexItem REINFORCED_DRAGON_SCALE;
	public static EndrexItem MYSTHERIUM_ORE;
	public static EndrexItem MYSTHERIUM;
	public static EndrexItem MYSTHERIUM_BUCKET;
	
	public static ItemGroup CATEGORY_MACHINES;
	public static EnhancedElectricCrucible ENHANCED_ELECTRIC_CRUCIBLE_1;
	public static EnhancedElectricCrucible ENHANCED_ELECTRIC_CRUCIBLE_2;
	public static DustsFabricator DUSTS_FABRICATOR_1;
	public static DustsFabricator DUSTS_FABRICATOR_2;
	
	public static ItemGroup CATEGORY_WEAPONS_AND_EQUIPMENTS;
	public static EndrexItem ENDERIUM_SWORD;
	public static EndrexItem EMPOWERED_ENDERIUM_SWORD;
	public static EndrexItem ELYTRA_OF_THE_LITTLE_DRAGON;
	public static EndrexItem REINFORCED_ELYTRA;
	public static EndrexItem MASK_OF_ENDER;
	public static EndrexItem MYSTERIOUS_SWORD;
	public static EndrexItem MYSTERIOUS_PICKAXE;
	public static EndrexItem MYSTERIOUS_AXE;
	
	public static ItemGroup CATEGORY_MISCELLANEOUS;
	public static EndRespawnAnchor END_RESPAWN_ANCHOR;
	public static MysteriousTeleporter MYSTERIOUS_TELEPORTER;
	public static EndrexItem MYSTERIOUS_TELEPORTER_LINKER;
	
	public static void init(Endrex plugin) {
		// Resources
		CATEGORY_RESOURCES = new ItemGroup(new NamespacedKey(plugin, "resources"), new CustomItemStack(PlayerHead.getItemStack(EndrexSkulls.PEARL_ORE), "&eEndrex &7- &b资源"));
		initResources(plugin);
		
		// Machines
		CATEGORY_MACHINES = new ItemGroup(new NamespacedKey(plugin, "machines"), new CustomItemStack(PlayerHead.getItemStack(EndrexSkulls.ENHANCED_CRUCIBLE_EMPTY), "&eEndrex &7- &b基础机器"));
		initCrucibles(plugin);
		initDustsFarbicators(plugin);
		
		// Weapons
		CATEGORY_WEAPONS_AND_EQUIPMENTS = new ItemGroup(new NamespacedKey(plugin, "weapons_n_equipments"), new CustomItemStack(Material.DIAMOND_SWORD, "&eEndrex &7- &b武器和护甲"));
		initWeapons(plugin);
		initEquipments(plugin);
		
		CATEGORY_MISCELLANEOUS = new ItemGroup(new NamespacedKey(plugin, "miscellaneous"), new CustomItemStack(Material.ENDER_EYE, "&eEndrex &7- &b杂项"));
		initInterestingThings(plugin);
		
		// Other stuffs
		EnhancedElectricCrucible.addRecipe(ENDER_PEARL_ORE.getItem(), Liquids.RESONANT_ENDER, 1000);
		EnhancedElectricCrucible.addRecipe(new ItemStack(Material.ENDER_PEARL), Liquids.RESONANT_ENDER, 200); // Better to smelt ore!
		EnhancedElectricCrucible.addRecipe(new ItemStack(Material.CHORUS_FRUIT), Liquids.MYSTHERIUM, 150);
		EnhancedElectricCrucible.addRecipe(new ItemStack(Material.COBBLESTONE), Liquids.WATER, 100);
		EnhancedElectricCrucible.addRecipe(new ItemStack(Material.END_STONE), Liquids.LAVA, 75);
		EnhancedElectricCrucible.addRecipe(new ItemStack(Material.STONE), Liquids.LAVA, 75);
		EnhancedElectricCrucible.addRecipe(new ItemStack(Material.BLAZE_ROD), Liquids.LAVA, 100);
		EnhancedElectricCrucible.addRecipe(new ItemStack(Material.BLAZE_POWDER), Liquids.LAVA, 50);
		
		DustsFabricator.addRecipe(Liquids.RESONANT_ENDER, 500, RESONANT_ENDER_DUST.getItem());
		DustsFabricator.addRecipe(Liquids.MYSTHERIUM, 3000, MYSTHERIUM.getItem());
	}
	
	private static void initCrucibles(Endrex plugin) {
		// The main reason why I created crucible is because I want to find a way to use
		// ender pearl ore.
		
		ENHANCED_ELECTRIC_CRUCIBLE_1 = new EnhancedElectricCrucible(CATEGORY_MACHINES, new SlimefunItemStack(
				"ENHANCED_CRUCIBLE_1",
				PlayerHead.getItemStack(EndrexSkulls.ENHANCED_CRUCIBLE_EMPTY),
				"&e增强型末影电坩埚",
				"",
				LoreBuilder.machine(MachineTier.BASIC, MachineType.MACHINE),
				LoreBuilder.speed(1),
				LoreBuilder.powerPerSecond(32),
				EndrexLoreBuilder.liquidCapacity(2500),
				EndrexLoreBuilder.liquidPerTick(50)
				), RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
						SlimefunItems.DAMASCUS_STEEL_INGOT, new ItemStack(Material.IRON_INGOT), SlimefunItems.DAMASCUS_STEEL_INGOT,
						SlimefunItems.DAMASCUS_STEEL_INGOT, SlimefunItems.CRUCIBLE, SlimefunItems.DAMASCUS_STEEL_INGOT,
						SlimefunItems.HEATING_COIL, SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.HEATING_COIL
				}, 50, 16, 2500)
				.registerChain(plugin);
		ENHANCED_ELECTRIC_CRUCIBLE_2 = new EnhancedElectricCrucible(CATEGORY_MACHINES, new SlimefunItemStack(
				"ENHANCED_CRUCIBLE_2",
				PlayerHead.getItemStack(EndrexSkulls.ENHANCED_CRUCIBLE_EMPTY),
				"&e增强型末影电坩埚 &7- &eII",
				"",
				LoreBuilder.machine(MachineTier.BASIC, MachineType.MACHINE),
				LoreBuilder.speed(2),
				LoreBuilder.powerPerSecond(48),
				EndrexLoreBuilder.liquidCapacity(5000),
				EndrexLoreBuilder.liquidPerTick(100)
				), RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
						new ItemStack(Material.IRON_INGOT), null, new ItemStack(Material.IRON_INGOT),
						SlimefunItems.DAMASCUS_STEEL_INGOT, ENHANCED_ELECTRIC_CRUCIBLE_1.getItem(), SlimefunItems.DAMASCUS_STEEL_INGOT,
						SlimefunItems.HEATING_COIL, null, SlimefunItems.HEATING_COIL
				}, 100, 24, 5000)
				.registerChain(plugin);
	}
	private static void initDustsFarbicators(Endrex plugin) {
		DUSTS_FABRICATOR_1 = new DustsFabricator(CATEGORY_MACHINES, new SlimefunItemStack(
				"DUSTS_FABRICATOR_1",
				PlayerHead.getItemStack(EndrexSkulls.DUSTS_FABRICATOR),
				"&b粉尘塑形机",
				"",
				LoreBuilder.machine(MachineTier.BASIC, MachineType.MACHINE),
				LoreBuilder.speed(1),
				LoreBuilder.powerPerSecond(32),
				EndrexLoreBuilder.liquidCapacity(3000),
				EndrexLoreBuilder.liquidPerTick(50)
				), RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
				new ItemStack(Material.COBBLESTONE), new ItemStack(Material.COBBLESTONE), new ItemStack(Material.COBBLESTONE),
				new ItemStack(Material.COBBLESTONE), SlimefunItems.ELECTRIC_FURNACE, new ItemStack(Material.COBBLESTONE),
				SlimefunItems.HEATING_COIL, new ItemStack(Material.OBSIDIAN), SlimefunItems.HEATING_COIL
				}, 50, 16, 3000)
				.registerChain(plugin);
		DUSTS_FABRICATOR_2 = new DustsFabricator(CATEGORY_MACHINES, new SlimefunItemStack(
				"DUSTS_FABRICATOR_2",
				PlayerHead.getItemStack(EndrexSkulls.DUSTS_FABRICATOR),
				"&b粉尘塑形机 &7- &eII",
				"",
				LoreBuilder.machine(MachineTier.BASIC, MachineType.MACHINE),
				LoreBuilder.speed(1),
				LoreBuilder.powerPerSecond(32),
				EndrexLoreBuilder.liquidCapacity(3000),
				EndrexLoreBuilder.liquidPerTick(100)
				), RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
				new ItemStack(Material.COBBLESTONE), new ItemStack(Material.COBBLESTONE), new ItemStack(Material.COBBLESTONE),
				new ItemStack(Material.COBBLESTONE), DUSTS_FABRICATOR_1.getItem(), new ItemStack(Material.COBBLESTONE),
				SlimefunItems.HEATING_COIL, new ItemStack(Material.OBSIDIAN), SlimefunItems.HEATING_COIL
				}, 100, 16, 3000)
				.registerChain(plugin);
	}
	private static void initResources(Endrex plugin) {
		ENDER_PEARL_ORE = new EndrexResourceItem(plugin, new SlimefunItemStack("ENDER_PEARL_ORE", PlayerHead.getItemStack(EndrexSkulls.PEARL_ORE), "&f末影矿", "", "&7把它丢出去如何?"), 4, 19)
				.addMachineRecipe((RecipeDisplayItem) RecipeType.ORE_CRUSHER.getMachine(), new ItemStack(Material.ENDER_PEARL, 4))
				.itemUseHandler((e) -> {
					e.getPlayer().launchProjectile(EnderPearl.class);
					e.cancel();
					InventoryUtils.consumeHand(e.getPlayer().getInventory(), e.getInteractEvent().getHand(), 1);
				})
				.registerChain(plugin);
		CHORUS_ORE = new EndrexResourceItem(plugin, new SlimefunItemStack("CHORUS_ORE", PlayerHead.getItemStack(EndrexSkulls.CHORUS_ORE), "&f紫颂矿", "", "&7它的价值是否仅在于瞬移?", "&7或者只是为了好吃?"), 7, 23)
				.addMachineRecipe((RecipeDisplayItem) RecipeType.ORE_CRUSHER.getMachine(), new ItemStack(Material.CHORUS_FRUIT, 4))
				.registerChain(plugin);
		
		// Overworld resources
		END_STONE_COAL_ORE = new EndrexResourceItem(plugin, new SlimefunItemStack("END_STONE_COAL_ORE", PlayerHead.getItemStack(EndrexSkulls.COAL_ORE), "&f末地煤矿", "", "&7为什么会这样呢?", "&7它是否真的存在?"), 9, 27)
				.addMachineRecipe((RecipeDisplayItem) RecipeType.ORE_CRUSHER.getMachine(), new ItemStack(Material.COAL, 3))
				.registerChain(plugin);
		END_STONE_IRON_ORE = new EndrexResourceItem(plugin, new SlimefunItemStack("END_STONE_IRON_ORE", PlayerHead.getItemStack(EndrexSkulls.IRON_ORE), "&f末地铁矿", "", "&7为什么会这样呢?", "&7它是否真的存在?"), 8, 25)
				.addMachineRecipe((RecipeDisplayItem) RecipeType.ORE_CRUSHER.getMachine(), new CustomItemStack(SlimefunItems.IRON_DUST, 3))
				.registerChain(plugin);
		END_STONE_LAPIS_ORE = new EndrexResourceItem(plugin, new SlimefunItemStack("END_STONE_LAPIS_ORE", PlayerHead.getItemStack(EndrexSkulls.LAPIS_ORE), "&f末地青金石矿", "", "&7为什么会这样呢?", "&7它是否真的存在?"), 5, 12)
				.addMachineRecipe((RecipeDisplayItem) RecipeType.ORE_CRUSHER.getMachine(), new ItemStack(Material.LAPIS_LAZULI, 14))
				.registerChain(plugin);
		END_STONE_REDSTONE_ORE = new EndrexResourceItem(plugin, new SlimefunItemStack("END_STONE_REDSTONE_ORE", PlayerHead.getItemStack(EndrexSkulls.REDSTONE_ORE), "&f末地红石矿", "", "&7为什么会这样呢?", "&7它是否真的存在?"), 5, 12)
				.addMachineRecipe((RecipeDisplayItem) RecipeType.ORE_CRUSHER.getMachine(), new ItemStack(Material.REDSTONE, 8))
				.registerChain(plugin);
		END_STONE_GOLD_ORE = new EndrexResourceItem(plugin, new SlimefunItemStack("END_STONE_GOLD_ORE", PlayerHead.getItemStack(EndrexSkulls.GOLD_ORE), "&f末地金矿", "", "&7为什么会这样呢?", "&7它是否真的存在?"), 0, 5)
				.addMachineRecipe((RecipeDisplayItem) RecipeType.ORE_CRUSHER.getMachine(), new CustomItemStack(SlimefunItems.GOLD_DUST, 2))
				.registerChain(plugin);
		
		RESONANT_ENDER_BUCKET = new LiquidBucket(CATEGORY_RESOURCES, new SlimefunItemStack("RESONANT_ENDER_BUCKET", PlayerHead.getItemStack(EndrexSkulls.RESONANT_ENDER_BUCKET), "&b末影液桶", "", "&7不，你不能倾倒该液体", "&:(")).registerChain(plugin);
		RESONANT_ENDER_DUST = new EndrexItem(CATEGORY_RESOURCES, new SlimefunItemStack("RESONANT_ENDER_DUST", Material.CYAN_DYE, "&b末影粉"), EndrexRecipeType.DUSTS_FABRICATOR, new ItemStack[0]).registerChain(plugin);
		ENDERIUM = new EndrexItem(CATEGORY_RESOURCES, new SlimefunItemStack("ENDERIUM", Material.LIGHT_BLUE_DYE, "&b末影合金"), RecipeType.SMELTERY, new ItemStack[] {
				SlimefunItems.ALUMINUM_DUST, SlimefunItems.ALUMINUM_DUST, SlimefunItems.ALUMINUM_DUST, SlimefunItems.SILVER_DUST, SlimefunItems.SILVER_DUST, SlimefunItems.COPPER_DUST, RESONANT_ENDER_DUST.getItem(), RESONANT_ENDER_DUST.getItem(), null
		}).registerChain(plugin);
		ENDERIUM_BLOCK = new EndrexItem(CATEGORY_RESOURCES, new SlimefunItemStack("ENDERIUM_BLOCK", Material.LIGHT_BLUE_CONCRETE, "&b末影合金块"), RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
				ENDERIUM.getItem(), ENDERIUM.getItem(), ENDERIUM.getItem(),
				ENDERIUM.getItem(), ENDERIUM.getItem(), ENDERIUM.getItem(),
				ENDERIUM.getItem(), ENDERIUM.getItem(), ENDERIUM.getItem()
		}).registerChain(plugin);
		DRAGON_SCALE = new EndrexItem(CATEGORY_RESOURCES, new SlimefunItemStack("DRAGON_SCALE", Material.BLACK_DYE, "&e龙鳞", "", "&7按F键骑龙:)"), EndrexRecipeType.KILL_ENDER_DRAGON, new ItemStack[0]).registerChain(plugin);
		REINFORCED_DRAGON_SCALE = new EndrexItem(CATEGORY_RESOURCES, (SlimefunItemStack) new ItemStackWrapper(new SlimefunItemStack("REINFORCED_DRAGON_SCALE", Material.BLACK_DYE, "&e强化龙鳞", "", "&7更难获取了...")).addEnchant(Enchantment.DURABILITY, 0).addFlag(ItemFlag.HIDE_ENCHANTS).getItem(), RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
				ENDERIUM_BLOCK.getItem(), ENDERIUM_BLOCK.getItem(), ENDERIUM_BLOCK.getItem(),
				DRAGON_SCALE.getItem(), DRAGON_SCALE.getItem(), DRAGON_SCALE.getItem(),
				SlimefunItems.REINFORCED_PLATE, SlimefunItems.REINFORCED_PLATE, SlimefunItems.REINFORCED_PLATE
		}).registerChain(plugin);
		{
			SlimefunItemStack oreblock = new SlimefunItemStack("MYSTHERIUM_ORE", Material.PURPLE_CONCRETE, "&5神秘矿", "", "&7它蕴含在无边的末地之中");
			MYSTHERIUM = new EndrexItem(CATEGORY_RESOURCES, new SlimefunItemStack("MYSTHERIUM", Material.PURPLE_DYE, "&5神秘凝胶", "", "&7它实在是太稀有了"), RecipeType.ORE_CRUSHER, new ItemStack[] {oreblock, null, null, null, null, null, null, null, null}).registerChain(plugin);
			MYSTHERIUM_ORE = new EndrexMineableResource(CATEGORY_RESOURCES, oreblock, 0.09, MYSTHERIUM.getItem()).registerChain(plugin);
		}
		MYSTHERIUM_BUCKET = new LiquidBucket(CATEGORY_RESOURCES, new SlimefunItemStack("MYSTHERIUM_BUCKET", PlayerHead.getItemStack(EndrexSkulls.MYSTHERIUM_BUCKET), "&b神秘液桶", "", "&7不，你不能倾倒该液体", "&7:(")).registerChain(plugin);
	}
	private static void initWeapons(Endrex plugin) {
		ENDERIUM_SWORD = new EndrexEquipment(CATEGORY_WEAPONS_AND_EQUIPMENTS, (SlimefunItemStack) new ItemStackWrapper(new SlimefunItemStack(
				"ENDERIUM_SWORD",
				Material.IRON_SWORD,
				"&b末影合金剑",
				"&7珍珠掠夺者 I",
				"",
				"&f你将有更高的机率",
				"&f从末影人身上获取末影珍珠"
				))
				.addEnchant(Enchantment.DAMAGE_ALL, 1)
				.getItem(), RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
						null, ENDERIUM.getItem(), null,
						null, ENDERIUM.getItem(), null,
						null, new ItemStack(Material.STICK), null
		}).addHandlerChain(EndrexItems.pearlStealer(0.40)).registerChain(plugin);
		EMPOWERED_ENDERIUM_SWORD = new EndrexEquipment(CATEGORY_WEAPONS_AND_EQUIPMENTS, (SlimefunItemStack) new ItemStackWrapper(new SlimefunItemStack(
				"EMPOWERED_ENDERIUM_SWORD",
				Material.IRON_SWORD,
				"&b赋能末影合金剑",
				"&7珍珠抢夺者 II",
				"",
				"&f你将有更高的机率",
				"&f从末影人身上获取末影珍珠"
				))
				.addEnchant(Enchantment.DAMAGE_ALL, 3)
				.addEnchant(Enchantment.DURABILITY, 1)
				.getItem(), RecipeType.ANCIENT_ALTAR, new ItemStack[] {
						SlimefunItems.ENDER_LUMP_2, ENDERIUM.getItem(), SlimefunItems.ENDER_LUMP_2,
						ENDERIUM.getItem(), ENDERIUM_SWORD.getItem(), ENDERIUM.getItem(),
						SlimefunItems.ENDER_LUMP_2, ENDERIUM.getItem(), SlimefunItems.ENDER_LUMP_2
		}).addHandlerChain(EndrexItems.pearlStealer(0.75)).registerChain(plugin);
		
		MYSTERIOUS_SWORD = new MysteriousEquipment(CATEGORY_WEAPONS_AND_EQUIPMENTS, new SlimefunItemStack(
				"MYSTERIOUS_SWORD",
				Material.IRON_SWORD,
				"&d神秘剑",
				EndrexLoreBuilder.CLICK_TO_UNLOCK,
				"",
				"&f右击获得随机附魔"
				), RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
				null, MYSTHERIUM.getItem(), null,
				null, MYSTHERIUM.getItem(), null,
				null, new ItemStack(Material.STICK), null
		}).addEnchantment(
				// Spagetti code
				new RandomEnchantmentEntry(Enchantment.DURABILITY, 0.20, 1, 1),
				new RandomEnchantmentEntry(Enchantment.DURABILITY, 0.12, 2, 4),
				new RandomEnchantmentEntry(Enchantment.DURABILITY, 0.05, 5, 6),
				new RandomEnchantmentEntry(Enchantment.DAMAGE_ALL, 0.08, 1, 2),
				new RandomEnchantmentEntry(Enchantment.DAMAGE_ALL, 0.05, 3, 3),
				new RandomEnchantmentEntry(Enchantment.DAMAGE_ALL, 0.02, 4, 6),
				new RandomEnchantmentEntry(Enchantment.DAMAGE_UNDEAD, 0.03, 1, 2),
				new RandomEnchantmentEntry(Enchantment.DAMAGE_ARTHROPODS, 0.03, 1, 2),
				new RandomEnchantmentEntry(Enchantment.FIRE_ASPECT, 0.01, 1, 2),
				
				new RandomEnchantmentEntry(Enchantment.MENDING, 0.01, 1, 1)
				).registerChain(plugin);
		MYSTERIOUS_PICKAXE = new MysteriousEquipment(CATEGORY_WEAPONS_AND_EQUIPMENTS, new SlimefunItemStack(
				"MYSTERIOUS_PICKAXE",
				Material.IRON_PICKAXE,
				"&d神秘镐",
				EndrexLoreBuilder.CLICK_TO_UNLOCK,
				"",
				"&f右击获得随机附魔"
				), RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
				MYSTHERIUM.getItem(), MYSTHERIUM.getItem(), MYSTHERIUM.getItem(),
				null, new ItemStack(Material.STICK), null,
				null, new ItemStack(Material.STICK), null
		}).addEnchantment(
				new RandomEnchantmentEntry(Enchantment.DURABILITY, 0.20, 1, 1),
				new RandomEnchantmentEntry(Enchantment.DURABILITY, 0.12, 2, 4),
				new RandomEnchantmentEntry(Enchantment.DURABILITY, 0.05, 5, 6),
				new RandomEnchantmentEntry(Enchantment.DIG_SPEED, 0.15, 1, 2),
				new RandomEnchantmentEntry(Enchantment.DIG_SPEED, 0.09, 3, 5),
				new RandomEnchantmentEntry(Enchantment.DIG_SPEED, 0.06, 6, 8),
				new RandomEnchantmentEntry(Enchantment.LOOT_BONUS_BLOCKS, 0.10, 1, 1),
				new RandomEnchantmentEntry(Enchantment.LOOT_BONUS_BLOCKS, 0.07, 2, 4),
				new RandomEnchantmentEntry(Enchantment.LOOT_BONUS_BLOCKS, 0.03, 5, 6),

				new RandomEnchantmentEntry(Enchantment.MENDING, 0.01, 1, 1),
				new RandomEnchantmentEntry(Enchantment.SILK_TOUCH, 0.01, 1, 2) // Silk Touch II confirmed by Notch
				).registerChain(plugin);
		MYSTERIOUS_AXE = new MysteriousEquipment(CATEGORY_WEAPONS_AND_EQUIPMENTS, new SlimefunItemStack(
				"MYSTERIOUS_AXE",
				Material.IRON_AXE,
				"&d神秘斧",
				EndrexLoreBuilder.CLICK_TO_UNLOCK,
				"",
				"&f右击获得随机附魔"
				), RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
				MYSTHERIUM.getItem(), MYSTHERIUM.getItem(), null,
				MYSTHERIUM.getItem(), new ItemStack(Material.STICK), null,
				null, new ItemStack(Material.STICK), null
		}).addEnchantment(
				new RandomEnchantmentEntry(Enchantment.DURABILITY, 0.20, 1, 1),
				new RandomEnchantmentEntry(Enchantment.DURABILITY, 0.12, 2, 4),
				new RandomEnchantmentEntry(Enchantment.DURABILITY, 0.05, 5, 6),
				new RandomEnchantmentEntry(Enchantment.DIG_SPEED, 0.15, 1, 2),
				new RandomEnchantmentEntry(Enchantment.DIG_SPEED, 0.09, 3, 5),
				new RandomEnchantmentEntry(Enchantment.DIG_SPEED, 0.06, 6, 8),
				new RandomEnchantmentEntry(Enchantment.DAMAGE_ALL, 0.08, 1, 2),
				new RandomEnchantmentEntry(Enchantment.DAMAGE_ALL, 0.05, 3, 3),

				new RandomEnchantmentEntry(Enchantment.MENDING, 0.01, 1, 1),
				new RandomEnchantmentEntry(Enchantment.SILK_TOUCH, 0.01, 1, 2) // Silk Touch II confirmed by Jeb
				).registerChain(plugin);
	}
	private static void initEquipments(Endrex plugin) {
		ELYTRA_OF_THE_LITTLE_DRAGON = new EndrexEquipment(CATEGORY_WEAPONS_AND_EQUIPMENTS, (SlimefunItemStack) new ItemStackWrapper(new SlimefunItemStack("ELYTRA_OF_THE_LITTLE_DRAGON", Material.ELYTRA, "&e小龙的鞘翅", "", "&7它真的还没长大...")).addEnchant(Enchantment.DURABILITY, 2).getItem(), RecipeType.ANCIENT_ALTAR, new ItemStack[] {
				SlimefunItems.AIR_RUNE, SlimefunItems.ELYTRA_SCALE, SlimefunItems.AIR_RUNE,
				SlimefunItems.AIR_RUNE, new ItemStack(Material.LEATHER_CHESTPLATE), SlimefunItems.AIR_RUNE,
				DRAGON_SCALE.getItem(), SlimefunItems.ELYTRA_SCALE, DRAGON_SCALE.getItem()
		}).registerChain(plugin);
		REINFORCED_ELYTRA = new EndrexEquipment(CATEGORY_WEAPONS_AND_EQUIPMENTS, (SlimefunItemStack) new ItemStackWrapper(new SlimefunItemStack("REINFORCED_ELYTRA", Material.ELYTRA, "&e增强型鞘翅")).addEnchant(Enchantment.DURABILITY, 5).getItem(), RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
				null, SlimefunItems.ELYTRA_SCALE, null,
				REINFORCED_DRAGON_SCALE.getItem(), new ItemStack(Material.ELYTRA), REINFORCED_DRAGON_SCALE.getItem(),
				SlimefunItems.REINFORCED_PLATE, SlimefunItems.ELYTRA_SCALE, SlimefunItems.REINFORCED_PLATE
		}).registerChain(plugin);
		MASK_OF_ENDER = new EndrexEquipment(CATEGORY_WEAPONS_AND_EQUIPMENTS, new SlimefunItemStack("MASK_OF_ENDER", Material.LEATHER_HELMET, Color.AQUA, "&b末影面具", "", "&7这一定有什么", "&7特殊的功能..."), RecipeType.ARMOR_FORGE, new ItemStack[] {
				new ItemStack(Material.ENDER_PEARL), new ItemStack(Material.ENDER_PEARL), new ItemStack(Material.ENDER_PEARL),
				new ItemStack(Material.ENDER_EYE), new ItemStack(Material.LEATHER_HELMET), new ItemStack(Material.ENDER_EYE),
				null, null, null
		}).registerChain(plugin);
	}
	private static void initInterestingThings(Endrex plugin) {
		END_RESPAWN_ANCHOR = new EndRespawnAnchor(CATEGORY_MISCELLANEOUS, new SlimefunItemStack("END_RESPAWN_ANCHOR", PlayerHead.getItemStack(EndrexSkulls.RESPAWN_ANCHOR), "&e末地重生锚", "", "&7允许您在末地中记录重生点", "&7需用末影合金块来激活它"), RecipeType.ANCIENT_ALTAR, new ItemStack[] {
				SlimefunItems.ENDER_LUMP_3, new ItemStack(Material.END_STONE), SlimefunItems.ENDER_RUNE,
				new ItemStack(Material.END_STONE), new ItemStack(Material.ENCHANTING_TABLE), new ItemStack(Material.END_STONE),
				SlimefunItems.ENDER_RUNE, new ItemStack(Material.END_STONE), SlimefunItems.ENDER_LUMP_3
		}).registerChain(plugin);
		MYSTERIOUS_TELEPORTER = new MysteriousTeleporter(CATEGORY_MISCELLANEOUS, new SlimefunItemStack("MYSTERIOUS_TELEPORTER", PlayerHead.getItemStack(EndrexSkulls.MYSTERIOUS_TELEPORTER), "&d神秘传送器", "", "&7你想传送到哪呢?", "&7仅需耗费1个神秘凝胶", "&7即可传送"), RecipeType.MAGIC_WORKBENCH, new ItemStack[] {
				MYSTHERIUM.getItem(), new ItemStack(Material.CHORUS_FLOWER), MYSTHERIUM.getItem(),
				SlimefunItems.ENDER_LUMP_2, SlimefunItems.MAGIC_EYE_OF_ENDER, SlimefunItems.ENDER_LUMP_2,
				new ItemStack(Material.END_STONE), MYSTHERIUM.getItem(), new ItemStack(Material.END_STONE)
		}).registerChain(plugin);
		MYSTERIOUS_TELEPORTER_LINKER = new EndrexItem(CATEGORY_MISCELLANEOUS, new SlimefunItemStack("MYSTERIOUS_TELEPORTER_LINKER", Material.BLAZE_ROD, "&d神秘传送链接器", "", "&7用于链接2个神秘传送器", "&7一次性用品", "", "&e右击 &7第一个神秘传送器", "&7绑定当前位置.", "&e右击 &7第二个神秘传送器", "&7建立链接"), RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
				null, new ItemStack(Material.ENDER_EYE), null,
				null, new ItemStack(Material.BLAZE_ROD), null,
				null, SlimefunItems.ENDER_LUMP_2, null
		}).registerChain(plugin);
	}
	
	private static EntityKillHandler pearlStealer(double chance) {
		return (e, entity, killer, item) -> {
			ThreadLocalRandom random = ThreadLocalRandom.current();
			if (e.getEntityType() == EntityType.ENDERMAN && random.nextDouble() <= 0.6D) {
				e.getDrops().add(new ItemStack(Material.ENDER_PEARL));
			}
		};
	}
}
