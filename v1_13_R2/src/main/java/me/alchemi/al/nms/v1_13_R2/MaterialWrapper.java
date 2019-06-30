package me.alchemi.al.nms.v1_13_R2;

import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Collectors;

import org.bukkit.Material;

import me.alchemi.al.api.MaterialWrapper.IMaterialWrapper;

public enum MaterialWrapper implements IMaterialWrapper{
	
    ACACIA_BOAT(Material.ACACIA_BOAT),
    /**
     * BlockData: {@link Switch}
     */
    ACACIA_BUTTON(Material.ACACIA_BUTTON),
    /**
     * BlockData: {@link Door}
     */
    ACACIA_DOOR(Material.ACACIA_DOOR),
    /**
     * BlockData: {@link Fence}
     */
    ACACIA_FENCE(Material.ACACIA_FENCE),
    /**
     * BlockData: {@link Gate}
     */
    ACACIA_FENCE_GATE(Material.ACACIA_FENCE_GATE),
    /**
     * BlockData: {@link Leaves}
     */
    ACACIA_LEAVES(Material.ACACIA_LEAVES),
    /**
     * BlockData: {@link Orientable}
     */
    ACACIA_LOG(Material.ACACIA_LOG),
    ACACIA_PLANKS(Material.ACACIA_PLANKS),
    /**
     * BlockData: {@link Powerable}
     */
    ACACIA_PRESSURE_PLATE(Material.ACACIA_PRESSURE_PLATE),
    /**
     * BlockData: {@link Sapling}
     */
    ACACIA_SAPLING(Material.ACACIA_SAPLING),
    /**
     * BlockData: {@link Sign}
     */
    ACACIA_SIGN(Material.SIGN),
    /**
     * BlockData: {@link Slab}
     */
    ACACIA_SLAB(Material.ACACIA_SLAB),
    /**
     * BlockData: {@link Stairs}
     */
    ACACIA_STAIRS(Material.ACACIA_STAIRS),
    /**
     * BlockData: {@link TrapDoor}
     */
    ACACIA_TRAPDOOR(Material.ACACIA_TRAPDOOR),
    /**
     * BlockData: {@link WallSign}
     */
    ACACIA_WALL_SIGN(Material.WALL_SIGN),
    /**
     * BlockData: {@link Orientable}
     */
    ACACIA_WOOD(Material.ACACIA_WOOD),
    /**
     * BlockData: {@link RedstoneRail}
     */
    ACTIVATOR_RAIL(Material.ACTIVATOR_RAIL),
    AIR(Material.AIR),
    ALLIUM(Material.ALLIUM),
    ANDESITE(Material.ANDESITE),
    /**
     * BlockData: {@link Slab}
     */
    ANDESITE_SLAB(Material.ANDESITE),
    /**
     * BlockData: {@link Stairs}
     */
    ANDESITE_STAIRS(Material.ANDESITE),
    /**
     * BlockData: {@link Fence}
     */
    ANDESITE_WALL(Material.ANDESITE),
    /**
     * BlockData: {@link Directional}
     */
    ANVIL(Material.ANVIL),
    APPLE(Material.APPLE),
    ARMOR_STAND(Material.ARMOR_STAND),
    ARROW(Material.ARROW),
    /**
     * BlockData: {@link Directional}
     */
    ATTACHED_MELON_STEM(Material.ATTACHED_MELON_STEM),
    /**
     * BlockData: {@link Directional}
     */
    ATTACHED_PUMPKIN_STEM(Material.ATTACHED_PUMPKIN_STEM),
    AZURE_BLUET(Material.AZURE_BLUET),
    BAKED_POTATO(Material.BAKED_POTATO),
    /**
     * BlockData: {@link Bamboo}
     */
    BAMBOO(Material.BARRIER),
    BAMBOO_SAPLING(Material.BARRIER),
    /**
     * BlockData: {@link Directional}
     */
    BARREL(Material.BARRIER),
    BARRIER(Material.BARRIER),
    BAT_SPAWN_EGG(Material.BAT_SPAWN_EGG),
    BEACON(Material.BEACON),
    BEDROCK(Material.BEDROCK),
    BEEF(Material.BEEF),
    BEETROOT(Material.BEETROOT),
    /**
     * BlockData: {@link Ageable}
     */
    BEETROOTS(Material.BEETROOTS),
    BEETROOT_SEEDS(Material.BEETROOT_SEEDS),
    BEETROOT_SOUP(Material.BEETROOT_SOUP),
    /**
     * BlockData: {@link Bell}
     */
    BELL(Material.BARRIER),
    BIRCH_BOAT(Material.BIRCH_BOAT),
    /**
     * BlockData: {@link Switch}
     */
    BIRCH_BUTTON(Material.BIRCH_BUTTON),
    /**
     * BlockData: {@link Door}
     */
    BIRCH_DOOR(Material.BIRCH_DOOR),
    /**
     * BlockData: {@link Fence}
     */
    BIRCH_FENCE(Material.BIRCH_FENCE),
    /**
     * BlockData: {@link Gate}
     */
    BIRCH_FENCE_GATE(Material.BIRCH_FENCE_GATE),
    /**
     * BlockData: {@link Leaves}
     */
    BIRCH_LEAVES(Material.BIRCH_LEAVES),
    /**
     * BlockData: {@link Orientable}
     */
    BIRCH_LOG(Material.BIRCH_LOG),
    BIRCH_PLANKS(Material.BIRCH_PLANKS),
    /**
     * BlockData: {@link Powerable}
     */
    BIRCH_PRESSURE_PLATE(Material.BIRCH_PRESSURE_PLATE),
    /**
     * BlockData: {@link Sapling}
     */
    BIRCH_SAPLING(Material.BIRCH_SAPLING),
    /**
     * BlockData: {@link Sign}
     */
    BIRCH_SIGN(Material.BIRCH_PLANKS),
    /**
     * BlockData: {@link Slab}
     */
    BIRCH_SLAB(Material.BIRCH_SLAB),
    /**
     * BlockData: {@link Stairs}
     */
    BIRCH_STAIRS(Material.BIRCH_STAIRS),
    /**
     * BlockData: {@link TrapDoor}
     */
    BIRCH_TRAPDOOR(Material.BIRCH_TRAPDOOR),
    /**
     * BlockData: {@link WallSign}
     */
    BIRCH_WALL_SIGN(Material.WALL_SIGN),
    /**
     * BlockData: {@link Orientable}
     */
    BIRCH_WOOD(Material.BIRCH_WOOD),
    /**
     * BlockData: {@link Rotatable}
     */
    BLACK_BANNER(Material.BLACK_BANNER),
    /**
     * BlockData: {@link Bed}
     */
    BLACK_BED(Material.BLACK_BED),
    BLACK_CARPET(Material.BLACK_CARPET),
    BLACK_CONCRETE(Material.BLACK_CONCRETE),
    BLACK_CONCRETE_POWDER(Material.BLACK_CONCRETE_POWDER),
    BLACK_DYE(Material.INK_SAC),
    /**
     * BlockData: {@link Directional}
     */
    BLACK_GLAZED_TERRACOTTA(Material.BLACK_GLAZED_TERRACOTTA),
    /**
     * BlockData: {@link Directional}
     */
    BLACK_SHULKER_BOX(Material.BLACK_SHULKER_BOX),
    BLACK_STAINED_GLASS(Material.BLACK_STAINED_GLASS),
    /**
     * BlockData: {@link GlassPane}
     */
    BLACK_STAINED_GLASS_PANE(Material.BLACK_STAINED_GLASS_PANE),
    BLACK_TERRACOTTA(Material.BLACK_TERRACOTTA),
    /**
     * BlockData: {@link Directional}
     */
    BLACK_WALL_BANNER(Material.BLACK_WALL_BANNER),
    BLACK_WOOL(Material.BLACK_WOOL),
    /**
     * BlockData: {@link Furnace}
     */
    BLAST_FURNACE(Material.FURNACE),
    BLAZE_POWDER(Material.BLAZE_POWDER),
    BLAZE_ROD(Material.BLAZE_ROD),
    BLAZE_SPAWN_EGG(Material.BLAZE_SPAWN_EGG),
    /**
     * BlockData: {@link Rotatable}
     */
    BLUE_BANNER(Material.BLUE_BANNER),
    /**
     * BlockData: {@link Bed}
     */
    BLUE_BED(Material.BLUE_BED),
    BLUE_CARPET(Material.BLUE_CARPET),
    BLUE_CONCRETE(Material.BLUE_CONCRETE),
    BLUE_CONCRETE_POWDER(Material.BLUE_CONCRETE_POWDER),
    BLUE_DYE(Material.LAPIS_LAZULI),
    /**
     * BlockData: {@link Directional}
     */
    BLUE_GLAZED_TERRACOTTA(Material.BLUE_GLAZED_TERRACOTTA),
    BLUE_ICE(Material.BLUE_ICE),
    BLUE_ORCHID(Material.BLUE_ORCHID),
    /**
     * BlockData: {@link Directional}
     */
    BLUE_SHULKER_BOX(Material.BLUE_SHULKER_BOX),
    BLUE_STAINED_GLASS(Material.BLUE_STAINED_GLASS),
    /**
     * BlockData: {@link GlassPane}
     */
    BLUE_STAINED_GLASS_PANE(Material.BLUE_STAINED_GLASS_PANE),
    BLUE_TERRACOTTA(Material.BLUE_TERRACOTTA),
    /**
     * BlockData: {@link Directional}
     */
    BLUE_WALL_BANNER(Material.BLUE_WALL_BANNER),
    BLUE_WOOL(Material.BLUE_WOOL),
    BONE(Material.BONE),
    /**
     * BlockData: {@link Orientable}
     */
    BONE_BLOCK(Material.BONE_BLOCK),
    BONE_MEAL(Material.BONE_MEAL),
    BOOK(Material.BOOK),
    BOOKSHELF(Material.BOOKSHELF),
    BOW(Material.BOW),
    BOWL(Material.BOWL),
    /**
     * BlockData: {@link Waterlogged}
     */
    BRAIN_CORAL(Material.BRAIN_CORAL),
    BRAIN_CORAL_BLOCK(Material.BRAIN_CORAL_BLOCK),
    /**
     * BlockData: {@link Waterlogged}
     */
    BRAIN_CORAL_FAN(Material.BRAIN_CORAL_FAN),
    /**
     * BlockData: {@link CoralWallFan}
     */
    BRAIN_CORAL_WALL_FAN(Material.BRAIN_CORAL_WALL_FAN),
    BREAD(Material.BREAD),
    /**
     * BlockData: {@link BrewingStand}
     */
    BREWING_STAND(Material.BREWING_STAND),
    BRICK(Material.BRICK),
    BRICKS(Material.BRICKS),
    /**
     * BlockData: {@link Slab}
     */
    BRICK_SLAB(Material.BRICK_SLAB),
    /**
     * BlockData: {@link Stairs}
     */
    BRICK_STAIRS(Material.BRICK_STAIRS),
    /**
     * BlockData: {@link Fence}
     */
    BRICK_WALL(Material.BRICK),
    /**
     * BlockData: {@link Rotatable}
     */
    BROWN_BANNER(Material.BROWN_BANNER),
    /**
     * BlockData: {@link Bed}
     */
    BROWN_BED(Material.BROWN_BED),
    BROWN_CARPET(Material.BROWN_CARPET),
    BROWN_CONCRETE(Material.BROWN_CONCRETE),
    BROWN_CONCRETE_POWDER(Material.BROWN_CONCRETE_POWDER),
    BROWN_DYE(Material.COCOA_BEANS),
    /**
     * BlockData: {@link Directional}
     */
    BROWN_GLAZED_TERRACOTTA(Material.BROWN_GLAZED_TERRACOTTA),
    BROWN_MUSHROOM(Material.BROWN_MUSHROOM),
    /**
     * BlockData: {@link MultipleFacing}
     */
    BROWN_MUSHROOM_BLOCK(Material.BROWN_MUSHROOM_BLOCK),
    /**
     * BlockData: {@link Directional}
     */
    BROWN_SHULKER_BOX(Material.BROWN_SHULKER_BOX),
    BROWN_STAINED_GLASS(Material.BROWN_STAINED_GLASS),
    /**
     * BlockData: {@link GlassPane}
     */
    BROWN_STAINED_GLASS_PANE(Material.BROWN_STAINED_GLASS_PANE),
    BROWN_TERRACOTTA(Material.BROWN_TERRACOTTA),
    /**
     * BlockData: {@link Directional}
     */
    BROWN_WALL_BANNER(Material.BROWN_WALL_BANNER),
    BROWN_WOOL(Material.BROWN_WOOL),
    /**
     * BlockData: {@link BubbleColumn}
     */
    BUBBLE_COLUMN(Material.BUBBLE_COLUMN),
    /**
     * BlockData: {@link Waterlogged}
     */
    BUBBLE_CORAL(Material.BUBBLE_CORAL),
    BUBBLE_CORAL_BLOCK(Material.BUBBLE_CORAL_BLOCK),
    /**
     * BlockData: {@link Waterlogged}
     */
    BUBBLE_CORAL_FAN(Material.BUBBLE_CORAL_FAN),
    /**
     * BlockData: {@link CoralWallFan}
     */
    BUBBLE_CORAL_WALL_FAN(Material.BUBBLE_CORAL_WALL_FAN),
    BUCKET(Material.BUCKET),
    /**
     * BlockData: {@link Ageable}
     */
    CACTUS(Material.CACTUS),
    CACTUS_GREEN(Material.CACTUS_GREEN),
    /**
     * BlockData: {@link Cake}
     */
    CAKE(Material.CAKE),
    /**
     * BlockData: {@link Campfire}
     */
    CAMPFIRE(Material.BARRIER),
    CARROT(Material.CARROT),
    /**
     * BlockData: {@link Ageable}
     */
    CARROTS(Material.CARROTS),
    CARROT_ON_A_STICK(Material.CARROT_ON_A_STICK),
    CARTOGRAPHY_TABLE(Material.BARRIER),
    /**
     * BlockData: {@link Directional}
     */
    CARVED_PUMPKIN(Material.CARVED_PUMPKIN),
    CAT_SPAWN_EGG(Material.BARRIER),
    /**
     * BlockData: {@link Levelled}
     */
    CAULDRON(Material.CAULDRON),
    CAVE_AIR(Material.CAVE_AIR),
    CAVE_SPIDER_SPAWN_EGG(Material.CAVE_SPIDER_SPAWN_EGG),
    CHAINMAIL_BOOTS(Material.CHAINMAIL_BOOTS),
    CHAINMAIL_CHESTPLATE(Material.CHAINMAIL_CHESTPLATE),
    CHAINMAIL_HELMET(Material.CHAINMAIL_HELMET),
    CHAINMAIL_LEGGINGS(Material.CHAINMAIL_LEGGINGS),
    /**
     * BlockData: {@link CommandBlock}
     */
    CHAIN_COMMAND_BLOCK(Material.CHAIN_COMMAND_BLOCK),
    CHARCOAL(Material.CHARCOAL),
    /**
     * BlockData: {@link Chest}
     */
    CHEST(Material.CHEST),
    CHEST_MINECART(Material.CHEST_MINECART),
    CHICKEN(Material.CHICKEN),
    CHICKEN_SPAWN_EGG(Material.CHICKEN_SPAWN_EGG),
    /**
     * BlockData: {@link Directional}
     */
    CHIPPED_ANVIL(Material.CHIPPED_ANVIL),
    CHISELED_QUARTZ_BLOCK(Material.CHISELED_QUARTZ_BLOCK),
    CHISELED_RED_SANDSTONE(Material.CHISELED_RED_SANDSTONE),
    CHISELED_SANDSTONE(Material.CHISELED_SANDSTONE),
    CHISELED_STONE_BRICKS(Material.CHISELED_STONE_BRICKS),
    /**
     * BlockData: {@link Ageable}
     */
    CHORUS_FLOWER(Material.CHORUS_FLOWER),
    CHORUS_FRUIT(Material.CHORUS_FRUIT),
    /**
     * BlockData: {@link MultipleFacing}
     */
    CHORUS_PLANT(Material.CHORUS_PLANT),
    CLAY(Material.CLAY),
    CLAY_BALL(Material.CLAY_BALL),
    CLOCK(Material.CLOCK),
    COAL(Material.COAL),
    COAL_BLOCK(Material.COAL_BLOCK),
    COAL_ORE(Material.COAL_ORE),
    COARSE_DIRT(Material.COARSE_DIRT),
    COBBLESTONE(Material.COBBLESTONE),
    /**
     * BlockData: {@link Slab}
     */
    COBBLESTONE_SLAB(Material.COBBLESTONE_SLAB),
    /**
     * BlockData: {@link Stairs}
     */
    COBBLESTONE_STAIRS(Material.COBBLESTONE_STAIRS),
    /**
     * BlockData: {@link Fence}
     */
    COBBLESTONE_WALL(Material.COBBLESTONE_WALL),
    COBWEB(Material.COBWEB),
    /**
     * BlockData: {@link Cocoa}
     */
    COCOA(Material.COCOA),
    COCOA_BEANS(Material.COCOA_BEANS),
    COD(Material.COD),
    COD_BUCKET(Material.COD_BUCKET),
    COD_SPAWN_EGG(Material.COD_SPAWN_EGG),
    /**
     * BlockData: {@link CommandBlock}
     */
    COMMAND_BLOCK(Material.COMMAND_BLOCK),
    COMMAND_BLOCK_MINECART(Material.COMMAND_BLOCK_MINECART),
    /**
     * BlockData: {@link Comparator}
     */
    COMPARATOR(Material.COMPARATOR),
    COMPASS(Material.COMPASS),
    /**
     * BlockData: {@link Levelled}
     */
    COMPOSTER(Material.BARRIER),
    /**
     * BlockData: {@link Waterlogged}
     */
    CONDUIT(Material.CONDUIT),
    COOKED_BEEF(Material.COOKED_BEEF),
    COOKED_CHICKEN(Material.COOKED_CHICKEN),
    COOKED_COD(Material.COOKED_COD),
    COOKED_MUTTON(Material.COOKED_MUTTON),
    COOKED_PORKCHOP(Material.COOKED_PORKCHOP),
    COOKED_RABBIT(Material.COOKED_RABBIT),
    COOKED_SALMON(Material.COOKED_SALMON),
    COOKIE(Material.COOKIE),
    CORNFLOWER(Material.SUNFLOWER),
    COW_SPAWN_EGG(Material.COW_SPAWN_EGG),
    CRACKED_STONE_BRICKS(Material.CRACKED_STONE_BRICKS),
    CRAFTING_TABLE(Material.CRAFTING_TABLE),
    CREEPER_BANNER_PATTERN(Material.BARRIER),
    /**
     * BlockData: {@link Rotatable}
     */
    CREEPER_HEAD(Material.CREEPER_HEAD),
    CREEPER_SPAWN_EGG(Material.CREEPER_SPAWN_EGG),
    /**
     * BlockData: {@link Directional}
     */
    CREEPER_WALL_HEAD(Material.CREEPER_WALL_HEAD),
    CROSSBOW(Material.BARRIER),
    CUT_RED_SANDSTONE(Material.CUT_RED_SANDSTONE),
    /**
     * BlockData: {@link Slab}
     */
    CUT_RED_SANDSTONE_SLAB(Material.BARRIER),
    CUT_SANDSTONE(Material.CUT_SANDSTONE),
    /**
     * BlockData: {@link Slab}
     */
    CUT_SANDSTONE_SLAB(Material.BARRIER),
    /**
     * BlockData: {@link Rotatable}
     */
    CYAN_BANNER(Material.CYAN_BANNER),
    /**
     * BlockData: {@link Bed}
     */
    CYAN_BED(Material.CYAN_BED),
    CYAN_CARPET(Material.CYAN_CARPET),
    CYAN_CONCRETE(Material.CYAN_CONCRETE),
    CYAN_CONCRETE_POWDER(Material.CYAN_CONCRETE_POWDER),
    CYAN_DYE(Material.CYAN_DYE),
    /**
     * BlockData: {@link Directional}
     */
    CYAN_GLAZED_TERRACOTTA(Material.CYAN_GLAZED_TERRACOTTA),
    /**
     * BlockData: {@link Directional}
     */
    CYAN_SHULKER_BOX(Material.CYAN_SHULKER_BOX),
    CYAN_STAINED_GLASS(Material.CYAN_STAINED_GLASS),
    /**
     * BlockData: {@link GlassPane}
     */
    CYAN_STAINED_GLASS_PANE(Material.CYAN_STAINED_GLASS_PANE),
    CYAN_TERRACOTTA(Material.CYAN_TERRACOTTA),
    /**
     * BlockData: {@link Directional}
     */
    CYAN_WALL_BANNER(Material.CYAN_WALL_BANNER),
    CYAN_WOOL(Material.CYAN_WOOL),
    /**
     * BlockData: {@link Directional}
     */
    DAMAGED_ANVIL(Material.DAMAGED_ANVIL),
    DANDELION(Material.DANDELION),
    DANDELION_YELLOW(Material.DANDELION_YELLOW),
    DARK_OAK_BOAT(Material.DARK_OAK_BOAT),
    /**
     * BlockData: {@link Switch}
     */
    DARK_OAK_BUTTON(Material.DARK_OAK_BUTTON),
    /**
     * BlockData: {@link Door}
     */
    DARK_OAK_DOOR(Material.DARK_OAK_DOOR),
    /**
     * BlockData: {@link Fence}
     */
    DARK_OAK_FENCE(Material.DARK_OAK_FENCE),
    /**
     * BlockData: {@link Gate}
     */
    DARK_OAK_FENCE_GATE(Material.DARK_OAK_FENCE_GATE),
    /**
     * BlockData: {@link Leaves}
     */
    DARK_OAK_LEAVES(Material.DARK_OAK_LEAVES),
    /**
     * BlockData: {@link Orientable}
     */
    DARK_OAK_LOG(Material.DARK_OAK_LOG),
    DARK_OAK_PLANKS(Material.DARK_OAK_PLANKS),
    /**
     * BlockData: {@link Powerable}
     */
    DARK_OAK_PRESSURE_PLATE(Material.DARK_OAK_PRESSURE_PLATE),
    /**
     * BlockData: {@link Sapling}
     */
    DARK_OAK_SAPLING(Material.DARK_OAK_SAPLING),
    /**
     * BlockData: {@link Slab}
     */
    DARK_OAK_SLAB(Material.DARK_OAK_SLAB),
    /**
     * BlockData: {@link Stairs}
     */
    DARK_OAK_STAIRS(Material.DARK_OAK_STAIRS),
    /**
     * BlockData: {@link TrapDoor}
     */
    DARK_OAK_TRAPDOOR(Material.DARK_OAK_TRAPDOOR),
    /**
     * BlockData: {@link Orientable}
     */
    DARK_OAK_WOOD(Material.DARK_OAK_WOOD),
    DARK_PRISMARINE(Material.DARK_PRISMARINE),
    /**
     * BlockData: {@link Slab}
     */
    DARK_PRISMARINE_SLAB(Material.DARK_PRISMARINE_SLAB),
    /**
     * BlockData: {@link Stairs}
     */
    DARK_PRISMARINE_STAIRS(Material.DARK_PRISMARINE_STAIRS),
    /**
     * BlockData: {@link DaylightDetector}
     */
    DAYLIGHT_DETECTOR(Material.DAYLIGHT_DETECTOR),
    /**
     * BlockData: {@link Waterlogged}
     */
    DEAD_BRAIN_CORAL(Material.DEAD_BRAIN_CORAL),
    DEAD_BRAIN_CORAL_BLOCK(Material.DEAD_BRAIN_CORAL_BLOCK),
    /**
     * BlockData: {@link Waterlogged}
     */
    DEAD_BRAIN_CORAL_FAN(Material.DEAD_BRAIN_CORAL_FAN),
    /**
     * BlockData: {@link CoralWallFan}
     */
    DEAD_BRAIN_CORAL_WALL_FAN(Material.DEAD_BRAIN_CORAL_WALL_FAN),
    /**
     * BlockData: {@link Waterlogged}
     */
    DEAD_BUBBLE_CORAL(Material.DEAD_BUBBLE_CORAL),
    DEAD_BUBBLE_CORAL_BLOCK(Material.DEAD_BUBBLE_CORAL_BLOCK),
    /**
     * BlockData: {@link Waterlogged}
     */
    DEAD_BUBBLE_CORAL_FAN(Material.DEAD_BUBBLE_CORAL_FAN),
    /**
     * BlockData: {@link CoralWallFan}
     */
    DEAD_BUBBLE_CORAL_WALL_FAN(Material.DEAD_BUBBLE_CORAL_WALL_FAN),
    DEAD_BUSH(Material.DEAD_BUSH),
    /**
     * BlockData: {@link Waterlogged}
     */
    DEAD_FIRE_CORAL(Material.DEAD_FIRE_CORAL),
    DEAD_FIRE_CORAL_BLOCK(Material.DEAD_FIRE_CORAL_BLOCK),
    /**
     * BlockData: {@link Waterlogged}
     */
    DEAD_FIRE_CORAL_FAN(Material.DEAD_FIRE_CORAL_FAN),
    /**
     * BlockData: {@link CoralWallFan}
     */
    DEAD_FIRE_CORAL_WALL_FAN(Material.DEAD_FIRE_CORAL_WALL_FAN),
    /**
     * BlockData: {@link Waterlogged}
     */
    DEAD_HORN_CORAL(Material.DEAD_HORN_CORAL),
    DEAD_HORN_CORAL_BLOCK(Material.DEAD_HORN_CORAL_BLOCK),
    /**
     * BlockData: {@link Waterlogged}
     */
    DEAD_HORN_CORAL_FAN(Material.DEAD_HORN_CORAL_FAN),
    /**
     * BlockData: {@link CoralWallFan}
     */
    DEAD_HORN_CORAL_WALL_FAN(Material.DEAD_HORN_CORAL_WALL_FAN),
    /**
     * BlockData: {@link Waterlogged}
     */
    DEAD_TUBE_CORAL(Material.DEAD_TUBE_CORAL),
    DEAD_TUBE_CORAL_BLOCK(Material.DEAD_TUBE_CORAL_BLOCK),
    /**
     * BlockData: {@link Waterlogged}
     */
    DEAD_TUBE_CORAL_FAN(Material.DEAD_TUBE_CORAL_FAN),
    /**
     * BlockData: {@link CoralWallFan}
     */
    DEAD_TUBE_CORAL_WALL_FAN(Material.DEAD_TUBE_CORAL_WALL_FAN),
    DEBUG_STICK(Material.DEBUG_STICK),
    /**
     * BlockData: {@link RedstoneRail}
     */
    DETECTOR_RAIL(Material.DETECTOR_RAIL),
    DIAMOND(Material.DIAMOND),
    DIAMOND_AXE(Material.DIAMOND_AXE),
    DIAMOND_BLOCK(Material.DIAMOND_BLOCK),
    DIAMOND_BOOTS(Material.DIAMOND_BOOTS),
    DIAMOND_CHESTPLATE(Material.DIAMOND_CHESTPLATE),
    DIAMOND_HELMET(Material.DIAMOND_HELMET),
    DIAMOND_HOE(Material.DIAMOND_HOE),
    DIAMOND_HORSE_ARMOR(Material.DIAMOND_HORSE_ARMOR),
    DIAMOND_LEGGINGS(Material.DIAMOND_LEGGINGS),
    DIAMOND_ORE(Material.DIAMOND_ORE),
    DIAMOND_PICKAXE(Material.DIAMOND_PICKAXE),
    DIAMOND_SHOVEL(Material.DIAMOND_SHOVEL),
    DIAMOND_SWORD(Material.DIAMOND_SWORD),
    DIORITE(Material.DIORITE),
    DIRT(Material.DIRT),
    /**
     * BlockData: {@link Dispenser}
     */
    DISPENSER(Material.DISPENSER),
    DOLPHIN_SPAWN_EGG(Material.DOLPHIN_SPAWN_EGG),
    DONKEY_SPAWN_EGG(Material.DONKEY_SPAWN_EGG),
    DRAGON_BREATH(Material.DRAGON_BREATH),
    DRAGON_EGG(Material.DRAGON_EGG),
    /**
     * BlockData: {@link Rotatable}
     */
    DRAGON_HEAD(Material.DRAGON_HEAD),
    /**
     * BlockData: {@link Directional}
     */
    DRAGON_WALL_HEAD(Material.DRAGON_WALL_HEAD),
    DRIED_KELP(Material.DRIED_KELP),
    DRIED_KELP_BLOCK(Material.DRIED_KELP_BLOCK),
    /**
     * BlockData: {@link Dispenser}
     */
    DROPPER(Material.DROPPER),
    DROWNED_SPAWN_EGG(Material.DROWNED_SPAWN_EGG),
    EGG(Material.EGG),
    ELDER_GUARDIAN_SPAWN_EGG(Material.ELDER_GUARDIAN_SPAWN_EGG),
    ELYTRA(Material.ELYTRA),
    EMERALD(Material.EMERALD),
    EMERALD_BLOCK(Material.EMERALD_BLOCK),
    EMERALD_ORE(Material.EMERALD_ORE),
    ENCHANTED_BOOK(Material.ENCHANTED_BOOK),
    ENCHANTED_GOLDEN_APPLE(Material.ENCHANTED_GOLDEN_APPLE),
    ENCHANTING_TABLE(Material.ENCHANTING_TABLE),
    ENDERMAN_SPAWN_EGG(Material.ENDERMAN_SPAWN_EGG),
    ENDERMITE_SPAWN_EGG(Material.ENDERMITE_SPAWN_EGG),
    /**
     * BlockData: {@link EnderChest}
     */
    ENDER_CHEST(Material.ENDER_CHEST),
    ENDER_EYE(Material.ENDER_EYE),
    ENDER_PEARL(Material.ENDER_PEARL),
    END_CRYSTAL(Material.END_CRYSTAL),
    END_GATEWAY(Material.END_GATEWAY),
    END_PORTAL(Material.END_PORTAL),
    /**
     * BlockData: {@link EndPortalFrame}
     */
    END_PORTAL_FRAME(Material.END_PORTAL_FRAME),
    /**
     * BlockData: {@link Directional}
     */
    END_ROD(Material.END_ROD),
    END_STONE(Material.END_STONE),
    END_STONE_BRICKS(Material.END_STONE_BRICKS),
    EVOKER_SPAWN_EGG(Material.EVOKER_SPAWN_EGG),
    EXPERIENCE_BOTTLE(Material.EXPERIENCE_BOTTLE),
    /**
     * BlockData: {@link Farmland}
     */
    FARMLAND(Material.FARMLAND),
    FEATHER(Material.FEATHER),
    FERMENTED_SPIDER_EYE(Material.FERMENTED_SPIDER_EYE),
    FERN(Material.FERN),
    FILLED_MAP(Material.FILLED_MAP),
    /**
     * BlockData: {@link Fire}
     */
    FIRE(Material.FIRE),
    FIREWORK_ROCKET(Material.FIREWORK_ROCKET),
    FIREWORK_STAR(Material.FIREWORK_STAR),
    FIRE_CHARGE(Material.FIRE_CHARGE),
    /**
     * BlockData: {@link Waterlogged}
     */
    FIRE_CORAL(Material.FIRE_CORAL),
    FIRE_CORAL_BLOCK(Material.FIRE_CORAL_BLOCK),
    /**
     * BlockData: {@link Waterlogged}
     */
    FIRE_CORAL_FAN(Material.FIRE_CORAL_FAN),
    /**
     * BlockData: {@link CoralWallFan}
     */
    FIRE_CORAL_WALL_FAN(Material.FIRE_CORAL_WALL_FAN),
    FISHING_ROD(Material.FISHING_ROD),
    FLINT(Material.FLINT),
    FLINT_AND_STEEL(Material.FLINT_AND_STEEL),
    FLOWER_POT(Material.FLOWER_POT),
    /**
     * BlockData: {@link Ageable}
     */
    FROSTED_ICE(Material.FROSTED_ICE),
    /**
     * BlockData: {@link Furnace}
     */
    FURNACE(Material.FURNACE),
    FURNACE_MINECART(Material.FURNACE_MINECART),
    GHAST_SPAWN_EGG(Material.GHAST_SPAWN_EGG),
    GHAST_TEAR(Material.GHAST_TEAR),
    GLASS(Material.GLASS),
    GLASS_BOTTLE(Material.GLASS_BOTTLE),
    /**
     * BlockData: {@link Fence}
     */
    GLASS_PANE(Material.GLASS_PANE),
    GLISTERING_MELON_SLICE(Material.GLISTERING_MELON_SLICE),
    GLOWSTONE(Material.GLOWSTONE),
    GLOWSTONE_DUST(Material.GLOWSTONE_DUST),
    GOLDEN_APPLE(Material.GOLDEN_APPLE),
    GOLDEN_AXE(Material.GOLDEN_AXE),
    GOLDEN_BOOTS(Material.GOLDEN_BOOTS),
    GOLDEN_CARROT(Material.GOLDEN_CARROT),
    GOLDEN_CHESTPLATE(Material.GOLDEN_CHESTPLATE),
    GOLDEN_HELMET(Material.GOLDEN_HELMET),
    GOLDEN_HOE(Material.GOLDEN_HOE),
    GOLDEN_HORSE_ARMOR(Material.GOLDEN_HORSE_ARMOR),
    GOLDEN_LEGGINGS(Material.GOLDEN_LEGGINGS),
    GOLDEN_PICKAXE(Material.GOLDEN_PICKAXE),
    GOLDEN_SHOVEL(Material.GOLDEN_SHOVEL),
    GOLDEN_SWORD(Material.GOLDEN_SWORD),
    GOLD_BLOCK(Material.GOLD_BLOCK),
    GOLD_INGOT(Material.GOLD_INGOT),
    GOLD_NUGGET(Material.GOLD_NUGGET),
    GOLD_ORE(Material.GOLD_ORE),
    GRANITE(Material.GRANITE),
    GRASS(Material.GRASS),
    /**
     * BlockData: {@link Snowable}
     */
    GRASS_BLOCK(Material.GRASS_BLOCK),
    GRASS_PATH(Material.GRASS_PATH),
    GRAVEL(Material.GRAVEL),
    /**
     * BlockData: {@link Rotatable}
     */
    GRAY_BANNER(Material.GRAY_BANNER),
    /**
     * BlockData: {@link Bed}
     */
    GRAY_BED(Material.GRAY_BED),
    GRAY_CARPET(Material.GRAY_CARPET),
    GRAY_CONCRETE(Material.GRAY_CONCRETE),
    GRAY_CONCRETE_POWDER(Material.GRAY_CONCRETE_POWDER),
    GRAY_DYE(Material.GRAY_DYE),
    /**
     * BlockData: {@link Directional}
     */
    GRAY_GLAZED_TERRACOTTA(Material.GRAY_GLAZED_TERRACOTTA),
    /**
     * BlockData: {@link Directional}
     */
    GRAY_SHULKER_BOX(Material.GRAY_SHULKER_BOX),
    GRAY_STAINED_GLASS(Material.GRAY_STAINED_GLASS),
    /**
     * BlockData: {@link GlassPane}
     */
    GRAY_STAINED_GLASS_PANE(Material.GRAY_STAINED_GLASS_PANE),
    GRAY_TERRACOTTA(Material.GRAY_TERRACOTTA),
    /**
     * BlockData: {@link Directional}
     */
    GRAY_WALL_BANNER(Material.GRAY_WALL_BANNER),
    GRAY_WOOL(Material.GRAY_WOOL),
    /**
     * BlockData: {@link Rotatable}
     */
    GREEN_BANNER(Material.GREEN_BANNER),
    /**
     * BlockData: {@link Bed}
     */
    GREEN_BED(Material.GREEN_BED),
    GREEN_CARPET(Material.GREEN_CARPET),
    GREEN_CONCRETE(Material.GREEN_CONCRETE),
    GREEN_CONCRETE_POWDER(Material.GREEN_CONCRETE_POWDER),
    /**
     * BlockData: {@link Directional}
     */
    GREEN_GLAZED_TERRACOTTA(Material.GREEN_GLAZED_TERRACOTTA),
    /**
     * BlockData: {@link Directional}
     */
    GREEN_SHULKER_BOX(Material.GREEN_SHULKER_BOX),
    GREEN_STAINED_GLASS(Material.GREEN_STAINED_GLASS),
    /**
     * BlockData: {@link GlassPane}
     */
    GREEN_STAINED_GLASS_PANE(Material.GREEN_STAINED_GLASS_PANE),
    GREEN_TERRACOTTA(Material.GREEN_TERRACOTTA),
    /**
     * BlockData: {@link Directional}
     */
    GREEN_WALL_BANNER(Material.GREEN_WALL_BANNER),
    GREEN_WOOL(Material.GREEN_WOOL),
    GUARDIAN_SPAWN_EGG(Material.GUARDIAN_SPAWN_EGG),
    GUNPOWDER(Material.GUNPOWDER),
    /**
     * BlockData: {@link Orientable}
     */
    HAY_BLOCK(Material.HAY_BLOCK),
    HEART_OF_THE_SEA(Material.HEART_OF_THE_SEA),
    /**
     * BlockData: {@link AnaloguePowerable}
     */
    HEAVY_WEIGHTED_PRESSURE_PLATE(Material.HEAVY_WEIGHTED_PRESSURE_PLATE),
    /**
     * BlockData: {@link Hopper}
     */
    HOPPER(Material.HOPPER),
    HOPPER_MINECART(Material.HOPPER_MINECART),
    /**
     * BlockData: {@link Waterlogged}
     */
    HORN_CORAL(Material.HORN_CORAL),
    HORN_CORAL_BLOCK(Material.HORN_CORAL_BLOCK),
    /**
     * BlockData: {@link Waterlogged}
     */
    HORN_CORAL_FAN(Material.HORN_CORAL_FAN),
    /**
     * BlockData: {@link CoralWallFan}
     */
    HORN_CORAL_WALL_FAN(Material.HORN_CORAL_WALL_FAN),
    HORSE_SPAWN_EGG(Material.HORSE_SPAWN_EGG),
    HUSK_SPAWN_EGG(Material.HUSK_SPAWN_EGG),
    ICE(Material.ICE),
    INFESTED_CHISELED_STONE_BRICKS(Material.INFESTED_CHISELED_STONE_BRICKS),
    INFESTED_COBBLESTONE(Material.INFESTED_COBBLESTONE),
    INFESTED_CRACKED_STONE_BRICKS(Material.INFESTED_CRACKED_STONE_BRICKS),
    INFESTED_MOSSY_STONE_BRICKS(Material.INFESTED_MOSSY_STONE_BRICKS),
    INFESTED_STONE(Material.INFESTED_STONE),
    INFESTED_STONE_BRICKS(Material.INFESTED_STONE_BRICKS),
    INK_SAC(Material.INK_SAC),
    IRON_AXE(Material.IRON_AXE),
    /**
     * BlockData: {@link Fence}
     */
    IRON_BARS(Material.IRON_BARS),
    IRON_BLOCK(Material.IRON_BLOCK),
    IRON_BOOTS(Material.IRON_BOOTS),
    IRON_CHESTPLATE(Material.IRON_CHESTPLATE),
    /**
     * BlockData: {@link Door}
     */
    IRON_DOOR(Material.IRON_DOOR),
    IRON_HELMET(Material.IRON_HELMET),
    IRON_HOE(Material.IRON_HOE),
    IRON_HORSE_ARMOR(Material.IRON_HORSE_ARMOR),
    IRON_INGOT(Material.IRON_INGOT),
    IRON_LEGGINGS(Material.IRON_LEGGINGS),
    IRON_NUGGET(Material.IRON_NUGGET),
    IRON_ORE(Material.IRON_ORE),
    IRON_PICKAXE(Material.IRON_PICKAXE),
    IRON_SHOVEL(Material.IRON_SHOVEL),
    IRON_SWORD(Material.IRON_SWORD),
    /**
     * BlockData: {@link TrapDoor}
     */
    IRON_TRAPDOOR(Material.IRON_TRAPDOOR),
    ITEM_FRAME(Material.ITEM_FRAME),
    /**
     * BlockData: {@link Directional}
     */
    JACK_O_LANTERN(Material.JACK_O_LANTERN),
    /**
     * BlockData: {@link Jukebox}
     */
    JUKEBOX(Material.JUKEBOX),
    JUNGLE_BOAT(Material.JUNGLE_BOAT),
    /**
     * BlockData: {@link Switch}
     */
    JUNGLE_BUTTON(Material.JUNGLE_BUTTON),
    /**
     * BlockData: {@link Door}
     */
    JUNGLE_DOOR(Material.JUNGLE_DOOR),
    /**
     * BlockData: {@link Fence}
     */
    JUNGLE_FENCE(Material.JUNGLE_FENCE),
    /**
     * BlockData: {@link Gate}
     */
    JUNGLE_FENCE_GATE(Material.JUNGLE_FENCE_GATE),
    /**
     * BlockData: {@link Leaves}
     */
    JUNGLE_LEAVES(Material.JUNGLE_LEAVES),
    /**
     * BlockData: {@link Orientable}
     */
    JUNGLE_LOG(Material.JUNGLE_LOG),
    JUNGLE_PLANKS(Material.JUNGLE_PLANKS),
    /**
     * BlockData: {@link Powerable}
     */
    JUNGLE_PRESSURE_PLATE(Material.JUNGLE_PRESSURE_PLATE),
    /**
     * BlockData: {@link Sapling}
     */
    JUNGLE_SAPLING(Material.JUNGLE_SAPLING),
    /**
     * BlockData: {@link Slab}
     */
    JUNGLE_SLAB(Material.JUNGLE_SLAB),
    /**
     * BlockData: {@link Stairs}
     */
    JUNGLE_STAIRS(Material.JUNGLE_STAIRS),
    /**
     * BlockData: {@link TrapDoor}
     */
    JUNGLE_TRAPDOOR(Material.JUNGLE_TRAPDOOR),
    /**
     * BlockData: {@link Orientable}
     */
    JUNGLE_WOOD(Material.JUNGLE_WOOD),
    /**
     * BlockData: {@link Ageable}
     */
    KELP(Material.KELP),
    KELP_PLANT(Material.KELP_PLANT),
    KNOWLEDGE_BOOK(Material.KNOWLEDGE_BOOK),
    /**
     * BlockData: {@link Ladder}
     */
    LADDER(Material.LADDER),
    LAPIS_BLOCK(Material.LAPIS_BLOCK),
    LAPIS_LAZULI(Material.LAPIS_LAZULI),
    LAPIS_ORE(Material.LAPIS_ORE),
    /**
     * BlockData: {@link Bisected}
     */
    LARGE_FERN(Material.LARGE_FERN),
    /**
     * BlockData: {@link Levelled}
     */
    LAVA(Material.LAVA),
    LAVA_BUCKET(Material.LAVA_BUCKET),
    LEAD(Material.LEAD),
    LEATHER(Material.LEATHER),
    LEATHER_BOOTS(Material.LEATHER_BOOTS),
    LEATHER_CHESTPLATE(Material.LEATHER_CHESTPLATE),
    LEATHER_HELMET(Material.LEATHER_HELMET),
    LEATHER_LEGGINGS(Material.LEATHER_LEGGINGS),
    /**
     * BlockData: {@link Switch}
     */
    LEVER(Material.LEVER),
    /**
     * BlockData: {@link Rotatable}
     */
    LIGHT_BLUE_BANNER(Material.LIGHT_BLUE_BANNER),
    /**
     * BlockData: {@link Bed}
     */
    LIGHT_BLUE_BED(Material.LIGHT_BLUE_BED),
    LIGHT_BLUE_CARPET(Material.LIGHT_BLUE_CARPET),
    LIGHT_BLUE_CONCRETE(Material.LIGHT_BLUE_CONCRETE),
    LIGHT_BLUE_CONCRETE_POWDER(Material.LIGHT_BLUE_CONCRETE_POWDER),
    LIGHT_BLUE_DYE(Material.LIGHT_BLUE_DYE),
    /**
     * BlockData: {@link Directional}
     */
    LIGHT_BLUE_GLAZED_TERRACOTTA(Material.LIGHT_BLUE_GLAZED_TERRACOTTA),
    /**
     * BlockData: {@link Directional}
     */
    LIGHT_BLUE_SHULKER_BOX(Material.LIGHT_BLUE_SHULKER_BOX),
    LIGHT_BLUE_STAINED_GLASS(Material.LIGHT_BLUE_STAINED_GLASS),
    /**
     * BlockData: {@link GlassPane}
     */
    LIGHT_BLUE_STAINED_GLASS_PANE(Material.LIGHT_BLUE_STAINED_GLASS_PANE),
    LIGHT_BLUE_TERRACOTTA(Material.LIGHT_BLUE_TERRACOTTA),
    /**
     * BlockData: {@link Directional}
     */
    LIGHT_BLUE_WALL_BANNER(Material.LIGHT_BLUE_WALL_BANNER),
    LIGHT_BLUE_WOOL(Material.LIGHT_BLUE_WOOL),
    /**
     * BlockData: {@link Rotatable}
     */
    LIGHT_GRAY_BANNER(Material.LIGHT_GRAY_BANNER),
    /**
     * BlockData: {@link Bed}
     */
    LIGHT_GRAY_BED(Material.LIGHT_GRAY_BED),
    LIGHT_GRAY_CARPET(Material.LIGHT_GRAY_CARPET),
    LIGHT_GRAY_CONCRETE(Material.LIGHT_GRAY_CONCRETE),
    LIGHT_GRAY_CONCRETE_POWDER(Material.LIGHT_GRAY_CONCRETE_POWDER),
    LIGHT_GRAY_DYE(Material.LIGHT_GRAY_DYE),
    /**
     * BlockData: {@link Directional}
     */
    LIGHT_GRAY_GLAZED_TERRACOTTA(Material.LIGHT_GRAY_GLAZED_TERRACOTTA),
    /**
     * BlockData: {@link Directional}
     */
    LIGHT_GRAY_SHULKER_BOX(Material.LIGHT_GRAY_SHULKER_BOX),
    LIGHT_GRAY_STAINED_GLASS(Material.LIGHT_GRAY_STAINED_GLASS),
    /**
     * BlockData: {@link GlassPane}
     */
    LIGHT_GRAY_STAINED_GLASS_PANE(Material.LIGHT_GRAY_STAINED_GLASS_PANE),
    LIGHT_GRAY_TERRACOTTA(Material.LIGHT_GRAY_TERRACOTTA),
    /**
     * BlockData: {@link Directional}
     */
    LIGHT_GRAY_WALL_BANNER(Material.LIGHT_GRAY_WALL_BANNER),
    LIGHT_GRAY_WOOL(Material.LIGHT_GRAY_WOOL),
    /**
     * BlockData: {@link AnaloguePowerable}
     */
    LIGHT_WEIGHTED_PRESSURE_PLATE(Material.LIGHT_WEIGHTED_PRESSURE_PLATE),
    /**
     * BlockData: {@link Bisected}
     */
    LILAC(Material.LILAC),
    LILY_PAD(Material.LILY_PAD),
    /**
     * BlockData: {@link Rotatable}
     */
    LIME_BANNER(Material.LIME_BANNER),
    /**
     * BlockData: {@link Bed}
     */
    LIME_BED(Material.LIME_BED),
    LIME_CARPET(Material.LIME_CARPET),
    LIME_CONCRETE(Material.LIME_CONCRETE),
    LIME_CONCRETE_POWDER(Material.LIME_CONCRETE_POWDER),
    LIME_DYE(Material.LIME_DYE),
    /**
     * BlockData: {@link Directional}
     */
    LIME_GLAZED_TERRACOTTA(Material.LIME_GLAZED_TERRACOTTA),
    /**
     * BlockData: {@link Directional}
     */
    LIME_SHULKER_BOX(Material.LIME_SHULKER_BOX),
    LIME_STAINED_GLASS(Material.LIME_STAINED_GLASS),
    /**
     * BlockData: {@link GlassPane}
     */
    LIME_STAINED_GLASS_PANE(Material.LIME_STAINED_GLASS_PANE),
    LIME_TERRACOTTA(Material.LIME_TERRACOTTA),
    /**
     * BlockData: {@link Directional}
     */
    LIME_WALL_BANNER(Material.LIME_WALL_BANNER),
    LIME_WOOL(Material.LIME_WOOL),
    LINGERING_POTION(Material.LINGERING_POTION),
    LLAMA_SPAWN_EGG(Material.LLAMA_SPAWN_EGG),
    /**
     * BlockData: {@link Rotatable}
     */
    MAGENTA_BANNER(Material.MAGENTA_BANNER),
    /**
     * BlockData: {@link Bed}
     */
    MAGENTA_BED(Material.MAGENTA_BED),
    MAGENTA_CARPET(Material.MAGENTA_CARPET),
    MAGENTA_CONCRETE(Material.MAGENTA_CONCRETE),
    MAGENTA_CONCRETE_POWDER(Material.MAGENTA_CONCRETE_POWDER),
    MAGENTA_DYE(Material.MAGENTA_DYE),
    /**
     * BlockData: {@link Directional}
     */
    MAGENTA_GLAZED_TERRACOTTA(Material.MAGENTA_GLAZED_TERRACOTTA),
    /**
     * BlockData: {@link Directional}
     */
    MAGENTA_SHULKER_BOX(Material.MAGENTA_SHULKER_BOX),
    MAGENTA_STAINED_GLASS(Material.MAGENTA_STAINED_GLASS),
    /**
     * BlockData: {@link GlassPane}
     */
    MAGENTA_STAINED_GLASS_PANE(Material.MAGENTA_STAINED_GLASS_PANE),
    MAGENTA_TERRACOTTA(Material.MAGENTA_TERRACOTTA),
    /**
     * BlockData: {@link Directional}
     */
    MAGENTA_WALL_BANNER(Material.MAGENTA_WALL_BANNER),
    MAGENTA_WOOL(Material.MAGENTA_WOOL),
    MAGMA_BLOCK(Material.MAGMA_BLOCK),
    MAGMA_CREAM(Material.MAGMA_CREAM),
    MAGMA_CUBE_SPAWN_EGG(Material.MAGMA_CUBE_SPAWN_EGG),
    MAP(Material.MAP),
    MELON(Material.MELON),
    MELON_SEEDS(Material.MELON_SEEDS),
    MELON_SLICE(Material.MELON_SLICE),
    /**
     * BlockData: {@link Ageable}
     */
    MELON_STEM(Material.MELON_STEM),
    MILK_BUCKET(Material.MILK_BUCKET),
    MINECART(Material.MINECART),
    MOOSHROOM_SPAWN_EGG(Material.MOOSHROOM_SPAWN_EGG),
    /**
     * BlockData: {@link MultipleFacing}
     */
    MOSSY_COBBLESTONE(Material.MOSSY_COBBLESTONE),
    /**
     * BlockData: {@link Fence}
     */
    MOSSY_COBBLESTONE_WALL(Material.MOSSY_COBBLESTONE_WALL),
    MOSSY_STONE_BRICKS(Material.MOSSY_STONE_BRICKS),
    /**
     * BlockData: {@link TechnicalPiston}
     */
    MOVING_PISTON(Material.MOVING_PISTON),
    MULE_SPAWN_EGG(Material.MULE_SPAWN_EGG),
    /**
     * BlockData: {@link MultipleFacing}
     */
    MUSHROOM_STEM(Material.MUSHROOM_STEM),
    MUSHROOM_STEW(Material.MUSHROOM_STEW),
    MUSIC_DISC_11(Material.MUSIC_DISC_11),
    MUSIC_DISC_13(Material.MUSIC_DISC_13),
    MUSIC_DISC_BLOCKS(Material.MUSIC_DISC_BLOCKS),
    MUSIC_DISC_CAT(Material.MUSIC_DISC_CAT),
    MUSIC_DISC_CHIRP(Material.MUSIC_DISC_CHIRP),
    MUSIC_DISC_FAR(Material.MUSIC_DISC_FAR),
    MUSIC_DISC_MALL(Material.MUSIC_DISC_MALL),
    MUSIC_DISC_MELLOHI(Material.MUSIC_DISC_MELLOHI),
    MUSIC_DISC_STAL(Material.MUSIC_DISC_STAL),
    MUSIC_DISC_STRAD(Material.MUSIC_DISC_STRAD),
    MUSIC_DISC_WAIT(Material.MUSIC_DISC_WAIT),
    MUSIC_DISC_WARD(Material.MUSIC_DISC_WARD),
    MUTTON(Material.MUTTON),
    /**
     * BlockData: {@link Snowable}
     */
    MYCELIUM(Material.MYCELIUM),
    NAME_TAG(Material.NAME_TAG),
    NAUTILUS_SHELL(Material.NAUTILUS_SHELL),
    NETHERRACK(Material.NETHERRACK),
    NETHER_BRICK(Material.NETHER_BRICK),
    NETHER_BRICKS(Material.NETHER_BRICKS),
    /**
     * BlockData: {@link Fence}
     */
    NETHER_BRICK_FENCE(Material.NETHER_BRICK_FENCE),
    /**
     * BlockData: {@link Slab}
     */
    NETHER_BRICK_SLAB(Material.NETHER_BRICK_SLAB),
    /**
     * BlockData: {@link Stairs}
     */
    NETHER_BRICK_STAIRS(Material.NETHER_BRICK_STAIRS),
    /**
     * BlockData: {@link Orientable}
     */
    NETHER_PORTAL(Material.NETHER_PORTAL),
    NETHER_QUARTZ_ORE(Material.NETHER_QUARTZ_ORE),
    NETHER_STAR(Material.NETHER_STAR),
    /**
     * BlockData: {@link Ageable}
     */
    NETHER_WART(Material.NETHER_WART),
    NETHER_WART_BLOCK(Material.NETHER_WART_BLOCK),
    /**
     * BlockData: {@link NoteBlock}
     */
    NOTE_BLOCK(Material.NOTE_BLOCK),
    OAK_BOAT(Material.OAK_BOAT),
    /**
     * BlockData: {@link Switch}
     */
    OAK_BUTTON(Material.OAK_BUTTON),
    /**
     * BlockData: {@link Door}
     */
    OAK_DOOR(Material.OAK_DOOR),
    /**
     * BlockData: {@link Fence}
     */
    OAK_FENCE(Material.OAK_FENCE),
    /**
     * BlockData: {@link Gate}
     */
    OAK_FENCE_GATE(Material.OAK_FENCE_GATE),
    /**
     * BlockData: {@link Leaves}
     */
    OAK_LEAVES(Material.OAK_LEAVES),
    /**
     * BlockData: {@link Orientable}
     */
    OAK_LOG(Material.OAK_LOG),
    OAK_PLANKS(Material.OAK_PLANKS),
    /**
     * BlockData: {@link Powerable}
     */
    OAK_PRESSURE_PLATE(Material.OAK_PRESSURE_PLATE),
    /**
     * BlockData: {@link Sapling}
     */
    OAK_SAPLING(Material.OAK_SAPLING),
    /**
     * BlockData: {@link Slab}
     */
    OAK_SLAB(Material.OAK_SLAB),
    /**
     * BlockData: {@link Stairs}
     */
    OAK_STAIRS(Material.OAK_STAIRS),
    /**
     * BlockData: {@link TrapDoor}
     */
    OAK_TRAPDOOR(Material.OAK_TRAPDOOR),
    /**
     * BlockData: {@link Orientable}
     */
    OAK_WOOD(Material.OAK_WOOD),
    /**
     * BlockData: {@link Observer}
     */
    OBSERVER(Material.OBSERVER),
    OBSIDIAN(Material.OBSIDIAN),
    OCELOT_SPAWN_EGG(Material.OCELOT_SPAWN_EGG),
    /**
     * BlockData: {@link Rotatable}
     */
    ORANGE_BANNER(Material.ORANGE_BANNER),
    /**
     * BlockData: {@link Bed}
     */
    ORANGE_BED(Material.ORANGE_BED),
    ORANGE_CARPET(Material.ORANGE_CARPET),
    ORANGE_CONCRETE(Material.ORANGE_CONCRETE),
    ORANGE_CONCRETE_POWDER(Material.ORANGE_CONCRETE_POWDER),
    ORANGE_DYE(Material.ORANGE_DYE),
    /**
     * BlockData: {@link Directional}
     */
    ORANGE_GLAZED_TERRACOTTA(Material.ORANGE_GLAZED_TERRACOTTA),
    /**
     * BlockData: {@link Directional}
     */
    ORANGE_SHULKER_BOX(Material.ORANGE_SHULKER_BOX),
    ORANGE_STAINED_GLASS(Material.ORANGE_STAINED_GLASS),
    /**
     * BlockData: {@link GlassPane}
     */
    ORANGE_STAINED_GLASS_PANE(Material.ORANGE_STAINED_GLASS_PANE),
    ORANGE_TERRACOTTA(Material.ORANGE_TERRACOTTA),
    ORANGE_TULIP(Material.ORANGE_TULIP),
    /**
     * BlockData: {@link Directional}
     */
    ORANGE_WALL_BANNER(Material.ORANGE_WALL_BANNER),
    ORANGE_WOOL(Material.ORANGE_WOOL),
    OXEYE_DAISY(Material.OXEYE_DAISY),
    PACKED_ICE(Material.PACKED_ICE),
    PAINTING(Material.PAINTING),
    PAPER(Material.PAPER),
    PARROT_SPAWN_EGG(Material.PARROT_SPAWN_EGG),
    /**
     * BlockData: {@link Bisected}
     */
    PEONY(Material.PEONY),
    /**
     * BlockData: {@link Slab}
     */
    PETRIFIED_OAK_SLAB(Material.PETRIFIED_OAK_SLAB),
    PHANTOM_MEMBRANE(Material.PHANTOM_MEMBRANE),
    PHANTOM_SPAWN_EGG(Material.PHANTOM_SPAWN_EGG),
    PIG_SPAWN_EGG(Material.PIG_SPAWN_EGG),
    /**
     * BlockData: {@link Rotatable}
     */
    PINK_BANNER(Material.PINK_BANNER),
    /**
     * BlockData: {@link Bed}
     */
    PINK_BED(Material.PINK_BED),
    PINK_CARPET(Material.PINK_CARPET),
    PINK_CONCRETE(Material.PINK_CONCRETE),
    PINK_CONCRETE_POWDER(Material.PINK_CONCRETE_POWDER),
    PINK_DYE(Material.PINK_DYE),
    /**
     * BlockData: {@link Directional}
     */
    PINK_GLAZED_TERRACOTTA(Material.PINK_GLAZED_TERRACOTTA),
    /**
     * BlockData: {@link Directional}
     */
    PINK_SHULKER_BOX(Material.PINK_SHULKER_BOX),
    PINK_STAINED_GLASS(Material.PINK_STAINED_GLASS),
    /**
     * BlockData: {@link GlassPane}
     */
    PINK_STAINED_GLASS_PANE(Material.PINK_STAINED_GLASS_PANE),
    PINK_TERRACOTTA(Material.PINK_TERRACOTTA),
    PINK_TULIP(Material.PINK_TULIP),
    /**
     * BlockData: {@link Directional}
     */
    PINK_WALL_BANNER(Material.PINK_WALL_BANNER),
    PINK_WOOL(Material.PINK_WOOL),
    /**
     * BlockData: {@link Piston}
     */
    PISTON(Material.PISTON),
    /**
     * BlockData: {@link PistonHead}
     */
    PISTON_HEAD(Material.PISTON_HEAD),
    /**
     * BlockData: {@link Rotatable}
     */
    PLAYER_HEAD(Material.PLAYER_HEAD),
    /**
     * BlockData: {@link Directional}
     */
    PLAYER_WALL_HEAD(Material.PLAYER_WALL_HEAD),
    /**
     * BlockData: {@link Snowable}
     */
    PODZOL(Material.PODZOL),
    POISONOUS_POTATO(Material.POISONOUS_POTATO),
    POLAR_BEAR_SPAWN_EGG(Material.POLAR_BEAR_SPAWN_EGG),
    POLISHED_ANDESITE(Material.POLISHED_ANDESITE),
    POLISHED_DIORITE(Material.POLISHED_DIORITE),
    POLISHED_GRANITE(Material.POLISHED_GRANITE),
    POPPED_CHORUS_FRUIT(Material.POPPED_CHORUS_FRUIT),
    POPPY(Material.POPPY),
    PORKCHOP(Material.PORKCHOP),
    POTATO(Material.POTATO),
    /**
     * BlockData: {@link Ageable}
     */
    POTATOES(Material.POTATOES),
    POTION(Material.POTION),
    POTTED_ACACIA_SAPLING(Material.POTTED_ACACIA_SAPLING),
    POTTED_ALLIUM(Material.POTTED_ALLIUM),
    POTTED_AZURE_BLUET(Material.POTTED_AZURE_BLUET),
    POTTED_BIRCH_SAPLING(Material.POTTED_BIRCH_SAPLING),
    POTTED_BLUE_ORCHID(Material.POTTED_BLUE_ORCHID),
    POTTED_BROWN_MUSHROOM(Material.POTTED_BROWN_MUSHROOM),
    POTTED_CACTUS(Material.POTTED_CACTUS),
    POTTED_DANDELION(Material.POTTED_DANDELION),
    POTTED_DARK_OAK_SAPLING(Material.POTTED_DARK_OAK_SAPLING),
    POTTED_DEAD_BUSH(Material.POTTED_DEAD_BUSH),
    POTTED_FERN(Material.POTTED_FERN),
    POTTED_JUNGLE_SAPLING(Material.POTTED_JUNGLE_SAPLING),
    POTTED_OAK_SAPLING(Material.POTTED_OAK_SAPLING),
    POTTED_ORANGE_TULIP(Material.POTTED_ORANGE_TULIP),
    POTTED_OXEYE_DAISY(Material.POTTED_OXEYE_DAISY),
    POTTED_PINK_TULIP(Material.POTTED_PINK_TULIP),
    POTTED_POPPY(Material.POTTED_POPPY),
    POTTED_RED_MUSHROOM(Material.POTTED_RED_MUSHROOM),
    POTTED_RED_TULIP(Material.POTTED_RED_TULIP),
    POTTED_SPRUCE_SAPLING(Material.POTTED_SPRUCE_SAPLING),
    POTTED_WHITE_TULIP(Material.POTTED_WHITE_TULIP),
    /**
     * BlockData: {@link RedstoneRail}
     */
    POWERED_RAIL(Material.POWERED_RAIL),
    PRISMARINE(Material.PRISMARINE),
    PRISMARINE_BRICKS(Material.PRISMARINE_BRICKS),
    /**
     * BlockData: {@link Slab}
     */
    PRISMARINE_BRICK_SLAB(Material.PRISMARINE_BRICK_SLAB),
    /**
     * BlockData: {@link Stairs}
     */
    PRISMARINE_BRICK_STAIRS(Material.PRISMARINE_BRICK_STAIRS),
    PRISMARINE_CRYSTALS(Material.PRISMARINE_CRYSTALS),
    PRISMARINE_SHARD(Material.PRISMARINE_SHARD),
    /**
     * BlockData: {@link Slab}
     */
    PRISMARINE_SLAB(Material.PRISMARINE_SLAB),
    /**
     * BlockData: {@link Stairs}
     */
    PRISMARINE_STAIRS(Material.PRISMARINE_STAIRS),
    PUFFERFISH(Material.PUFFERFISH),
    PUFFERFISH_BUCKET(Material.PUFFERFISH_BUCKET),
    PUFFERFISH_SPAWN_EGG(Material.PUFFERFISH_SPAWN_EGG),
    PUMPKIN(Material.PUMPKIN),
    PUMPKIN_PIE(Material.PUMPKIN_PIE),
    PUMPKIN_SEEDS(Material.PUMPKIN_SEEDS),
    /**
     * BlockData: {@link Ageable}
     */
    PUMPKIN_STEM(Material.PUMPKIN_STEM),
    /**
     * BlockData: {@link Rotatable}
     */
    PURPLE_BANNER(Material.PURPLE_BANNER),
    /**
     * BlockData: {@link Bed}
     */
    PURPLE_BED(Material.PURPLE_BED),
    PURPLE_CARPET(Material.PURPLE_CARPET),
    PURPLE_CONCRETE(Material.PURPLE_CONCRETE),
    PURPLE_CONCRETE_POWDER(Material.PURPLE_CONCRETE_POWDER),
    PURPLE_DYE(Material.PURPLE_DYE),
    /**
     * BlockData: {@link Directional}
     */
    PURPLE_GLAZED_TERRACOTTA(Material.PURPLE_GLAZED_TERRACOTTA),
    /**
     * BlockData: {@link Directional}
     */
    PURPLE_SHULKER_BOX(Material.PURPLE_SHULKER_BOX),
    PURPLE_STAINED_GLASS(Material.PURPLE_STAINED_GLASS),
    /**
     * BlockData: {@link GlassPane}
     */
    PURPLE_STAINED_GLASS_PANE(Material.PURPLE_STAINED_GLASS_PANE),
    PURPLE_TERRACOTTA(Material.PURPLE_TERRACOTTA),
    /**
     * BlockData: {@link Directional}
     */
    PURPLE_WALL_BANNER(Material.PURPLE_WALL_BANNER),
    PURPLE_WOOL(Material.PURPLE_WOOL),
    PURPUR_BLOCK(Material.PURPUR_BLOCK),
    /**
     * BlockData: {@link Orientable}
     */
    PURPUR_PILLAR(Material.PURPUR_PILLAR),
    /**
     * BlockData: {@link Slab}
     */
    PURPUR_SLAB(Material.PURPUR_SLAB),
    /**
     * BlockData: {@link Stairs}
     */
    PURPUR_STAIRS(Material.PURPUR_STAIRS),
    QUARTZ(Material.QUARTZ),
    QUARTZ_BLOCK(Material.QUARTZ_BLOCK),
    /**
     * BlockData: {@link Orientable}
     */
    QUARTZ_PILLAR(Material.QUARTZ_PILLAR),
    /**
     * BlockData: {@link Slab}
     */
    QUARTZ_SLAB(Material.QUARTZ_SLAB),
    /**
     * BlockData: {@link Stairs}
     */
    QUARTZ_STAIRS(Material.QUARTZ_STAIRS),
    RABBIT(Material.RABBIT),
    RABBIT_FOOT(Material.RABBIT_FOOT),
    RABBIT_HIDE(Material.RABBIT_HIDE),
    RABBIT_SPAWN_EGG(Material.RABBIT_SPAWN_EGG),
    RABBIT_STEW(Material.RABBIT_STEW),
    /**
     * BlockData: {@link Rail}
     */
    RAIL(Material.RAIL),
    REDSTONE(Material.REDSTONE),
    REDSTONE_BLOCK(Material.REDSTONE_BLOCK),
    /**
     * BlockData: {@link Lightable}
     */
    REDSTONE_LAMP(Material.REDSTONE_LAMP),
    /**
     * BlockData: {@link Lightable}
     */
    REDSTONE_ORE(Material.REDSTONE_ORE),
    /**
     * BlockData: {@link Lightable}
     */
    REDSTONE_TORCH(Material.REDSTONE_TORCH),
    /**
     * BlockData: {@link RedstoneWallTorch}
     */
    REDSTONE_WALL_TORCH(Material.REDSTONE_WALL_TORCH),
    /**
     * BlockData: {@link RedstoneWire}
     */
    REDSTONE_WIRE(Material.REDSTONE_WIRE),
    /**
     * BlockData: {@link Rotatable}
     */
    RED_BANNER(Material.RED_BANNER),
    /**
     * BlockData: {@link Bed}
     */
    RED_BED(Material.RED_BED),
    RED_CARPET(Material.RED_CARPET),
    RED_CONCRETE(Material.RED_CONCRETE),
    RED_CONCRETE_POWDER(Material.RED_CONCRETE_POWDER),
    /**
     * BlockData: {@link Directional}
     */
    RED_GLAZED_TERRACOTTA(Material.RED_GLAZED_TERRACOTTA),
    RED_MUSHROOM(Material.RED_MUSHROOM),
    /**
     * BlockData: {@link MultipleFacing}
     */
    RED_MUSHROOM_BLOCK(Material.RED_MUSHROOM_BLOCK),
    RED_NETHER_BRICKS(Material.RED_NETHER_BRICKS),
    RED_SAND(Material.RED_SAND),
    RED_SANDSTONE(Material.RED_SANDSTONE),
    /**
     * BlockData: {@link Slab}
     */
    RED_SANDSTONE_SLAB(Material.RED_SANDSTONE_SLAB),
    /**
     * BlockData: {@link Stairs}
     */
    RED_SANDSTONE_STAIRS(Material.RED_SANDSTONE_STAIRS),
    /**
     * BlockData: {@link Directional}
     */
    RED_SHULKER_BOX(Material.RED_SHULKER_BOX),
    RED_STAINED_GLASS(Material.RED_STAINED_GLASS),
    /**
     * BlockData: {@link GlassPane}
     */
    RED_STAINED_GLASS_PANE(Material.RED_STAINED_GLASS_PANE),
    RED_TERRACOTTA(Material.RED_TERRACOTTA),
    RED_TULIP(Material.RED_TULIP),
    /**
     * BlockData: {@link Directional}
     */
    RED_WALL_BANNER(Material.RED_WALL_BANNER),
    RED_WOOL(Material.RED_WOOL),
    /**
     * BlockData: {@link Repeater}
     */
    REPEATER(Material.REPEATER),
    /**
     * BlockData: {@link CommandBlock}
     */
    REPEATING_COMMAND_BLOCK(Material.REPEATING_COMMAND_BLOCK),
    /**
     * BlockData: {@link Bisected}
     */
    ROSE_BUSH(Material.ROSE_BUSH),
    ROTTEN_FLESH(Material.ROTTEN_FLESH),
    SADDLE(Material.SADDLE),
    SALMON(Material.SALMON),
    SALMON_BUCKET(Material.SALMON_BUCKET),
    SALMON_SPAWN_EGG(Material.SALMON_SPAWN_EGG),
    SAND(Material.SAND),
    SANDSTONE(Material.SANDSTONE),
    /**
     * BlockData: {@link Slab}
     */
    SANDSTONE_SLAB(Material.SANDSTONE_SLAB),
    /**
     * BlockData: {@link Stairs}
     */
    SANDSTONE_STAIRS(Material.SANDSTONE_STAIRS),
    SCUTE(Material.SCUTE),
    SEAGRASS(Material.SEAGRASS),
    SEA_LANTERN(Material.SEA_LANTERN),
    /**
     * BlockData: {@link SeaPickle}
     */
    SEA_PICKLE(Material.SEA_PICKLE),
    SHEARS(Material.SHEARS),
    SHEEP_SPAWN_EGG(Material.SHEEP_SPAWN_EGG),
    SHIELD(Material.SHIELD),
    /**
     * BlockData: {@link Directional}
     */
    SHULKER_BOX(Material.SHULKER_BOX),
    SHULKER_SHELL(Material.SHULKER_SHELL),
    SHULKER_SPAWN_EGG(Material.SHULKER_SPAWN_EGG),
    SILVERFISH_SPAWN_EGG(Material.SILVERFISH_SPAWN_EGG),
    SKELETON_HORSE_SPAWN_EGG(Material.SKELETON_HORSE_SPAWN_EGG),
    /**
     * BlockData: {@link Rotatable}
     */
    SKELETON_SKULL(Material.SKELETON_SKULL),
    SKELETON_SPAWN_EGG(Material.SKELETON_SPAWN_EGG),
    /**
     * BlockData: {@link Directional}
     */
    SKELETON_WALL_SKULL(Material.SKELETON_WALL_SKULL),
    SLIME_BALL(Material.SLIME_BALL),
    SLIME_BLOCK(Material.SLIME_BLOCK),
    SLIME_SPAWN_EGG(Material.SLIME_SPAWN_EGG),
    SMOOTH_QUARTZ(Material.SMOOTH_QUARTZ),
    SMOOTH_RED_SANDSTONE(Material.SMOOTH_RED_SANDSTONE),
    SMOOTH_SANDSTONE(Material.SMOOTH_SANDSTONE),
    SMOOTH_STONE(Material.SMOOTH_STONE),
    /**
     * BlockData: {@link Snow}
     */
    SNOW(Material.SNOW),
    SNOWBALL(Material.SNOWBALL),
    SNOW_BLOCK(Material.SNOW_BLOCK),
    SOUL_SAND(Material.SOUL_SAND),
    SPAWNER(Material.SPAWNER),
    SPECTRAL_ARROW(Material.SPECTRAL_ARROW),
    SPIDER_EYE(Material.SPIDER_EYE),
    SPIDER_SPAWN_EGG(Material.SPIDER_SPAWN_EGG),
    SPLASH_POTION(Material.SPLASH_POTION),
    SPONGE(Material.SPONGE),
    SPRUCE_BOAT(Material.SPRUCE_BOAT),
    /**
     * BlockData: {@link Switch}
     */
    SPRUCE_BUTTON(Material.SPRUCE_BUTTON),
    /**
     * BlockData: {@link Door}
     */
    SPRUCE_DOOR(Material.SPRUCE_DOOR),
    /**
     * BlockData: {@link Fence}
     */
    SPRUCE_FENCE(Material.SPRUCE_FENCE),
    /**
     * BlockData: {@link Gate}
     */
    SPRUCE_FENCE_GATE(Material.SPRUCE_FENCE_GATE),
    /**
     * BlockData: {@link Leaves}
     */
    SPRUCE_LEAVES(Material.SPRUCE_LEAVES),
    /**
     * BlockData: {@link Orientable}
     */
    SPRUCE_LOG(Material.SPRUCE_LOG),
    SPRUCE_PLANKS(Material.SPRUCE_PLANKS),
    /**
     * BlockData: {@link Powerable}
     */
    SPRUCE_PRESSURE_PLATE(Material.SPRUCE_PRESSURE_PLATE),
    /**
     * BlockData: {@link Sapling}
     */
    SPRUCE_SAPLING(Material.SPRUCE_SAPLING),
    /**
     * BlockData: {@link Slab}
     */
    SPRUCE_SLAB(Material.SPRUCE_SLAB),
    /**
     * BlockData: {@link Stairs}
     */
    SPRUCE_STAIRS(Material.SPRUCE_STAIRS),
    /**
     * BlockData: {@link TrapDoor}
     */
    SPRUCE_TRAPDOOR(Material.SPRUCE_TRAPDOOR),
    /**
     * BlockData: {@link Orientable}
     */
    SPRUCE_WOOD(Material.SPRUCE_WOOD),
    SQUID_SPAWN_EGG(Material.SQUID_SPAWN_EGG),
    STICK(Material.STICK),
    /**
     * BlockData: {@link Piston}
     */
    STICKY_PISTON(Material.STICKY_PISTON),
    STONE(Material.STONE),
    STONE_AXE(Material.STONE_AXE),
    STONE_BRICKS(Material.STONE_BRICKS),
    /**
     * BlockData: {@link Slab}
     */
    STONE_BRICK_SLAB(Material.STONE_BRICK_SLAB),
    /**
     * BlockData: {@link Stairs}
     */
    STONE_BRICK_STAIRS(Material.STONE_BRICK_STAIRS),
    /**
     * BlockData: {@link Switch}
     */
    STONE_BUTTON(Material.STONE_BUTTON),
    STONE_HOE(Material.STONE_HOE),
    STONE_PICKAXE(Material.STONE_PICKAXE),
    /**
     * BlockData: {@link Powerable}
     */
    STONE_PRESSURE_PLATE(Material.STONE_PRESSURE_PLATE),
    STONE_SHOVEL(Material.STONE_SHOVEL),
    /**
     * BlockData: {@link Slab}
     */
    STONE_SLAB(Material.STONE_SLAB),
    STONE_SWORD(Material.STONE_SWORD),
    STRAY_SPAWN_EGG(Material.STRAY_SPAWN_EGG),
    STRING(Material.STRING),
    /**
     * BlockData: {@link Orientable}
     */
    STRIPPED_ACACIA_LOG(Material.STRIPPED_ACACIA_LOG),
    /**
     * BlockData: {@link Orientable}
     */
    STRIPPED_ACACIA_WOOD(Material.STRIPPED_ACACIA_WOOD),
    /**
     * BlockData: {@link Orientable}
     */
    STRIPPED_BIRCH_LOG(Material.STRIPPED_BIRCH_LOG),
    /**
     * BlockData: {@link Orientable}
     */
    STRIPPED_BIRCH_WOOD(Material.STRIPPED_BIRCH_WOOD),
    /**
     * BlockData: {@link Orientable}
     */
    STRIPPED_DARK_OAK_LOG(Material.STRIPPED_DARK_OAK_LOG),
    /**
     * BlockData: {@link Orientable}
     */
    STRIPPED_DARK_OAK_WOOD(Material.STRIPPED_DARK_OAK_WOOD),
    /**
     * BlockData: {@link Orientable}
     */
    STRIPPED_JUNGLE_LOG(Material.STRIPPED_JUNGLE_LOG),
    /**
     * BlockData: {@link Orientable}
     */
    STRIPPED_JUNGLE_WOOD(Material.STRIPPED_JUNGLE_WOOD),
    /**
     * BlockData: {@link Orientable}
     */
    STRIPPED_OAK_LOG(Material.STRIPPED_OAK_LOG),
    /**
     * BlockData: {@link Orientable}
     */
    STRIPPED_OAK_WOOD(Material.STRIPPED_OAK_WOOD),
    /**
     * BlockData: {@link Orientable}
     */
    STRIPPED_SPRUCE_LOG(Material.STRIPPED_SPRUCE_LOG),
    /**
     * BlockData: {@link Orientable}
     */
    STRIPPED_SPRUCE_WOOD(Material.STRIPPED_SPRUCE_WOOD),
    /**
     * BlockData: {@link StructureBlock}
     */
    STRUCTURE_BLOCK(Material.STRUCTURE_BLOCK),
    STRUCTURE_VOID(Material.STRUCTURE_VOID),
    SUGAR(Material.SUGAR),
    /**
     * BlockData: {@link Ageable}
     */
    SUGAR_CANE(Material.SUGAR_CANE),
    /**
     * BlockData: {@link Bisected}
     */
    SUNFLOWER(Material.SUNFLOWER),
    /**
     * BlockData: {@link Bisected}
     */
    TALL_GRASS(Material.TALL_GRASS),
    /**
     * BlockData: {@link Bisected}
     */
    TALL_SEAGRASS(Material.TALL_SEAGRASS),
    TERRACOTTA(Material.TERRACOTTA),
    TIPPED_ARROW(Material.TIPPED_ARROW),
    /**
     * BlockData: {@link TNT}
     */
    TNT(Material.TNT),
    TNT_MINECART(Material.TNT_MINECART),
    TORCH(Material.TORCH),
    TOTEM_OF_UNDYING(Material.TOTEM_OF_UNDYING),
    /**
     * BlockData: {@link Chest}
     */
    TRAPPED_CHEST(Material.TRAPPED_CHEST),
    TRIDENT(Material.TRIDENT),
    /**
     * BlockData: {@link Tripwire}
     */
    TRIPWIRE(Material.TRIPWIRE),
    /**
     * BlockData: {@link TripwireHook}
     */
    TRIPWIRE_HOOK(Material.TRIPWIRE_HOOK),
    TROPICAL_FISH(Material.TROPICAL_FISH),
    TROPICAL_FISH_BUCKET(Material.TROPICAL_FISH_BUCKET),
    TROPICAL_FISH_SPAWN_EGG(Material.TROPICAL_FISH_SPAWN_EGG),
    /**
     * BlockData: {@link Waterlogged}
     */
    TUBE_CORAL(Material.TUBE_CORAL),
    TUBE_CORAL_BLOCK(Material.TUBE_CORAL_BLOCK),
    /**
     * BlockData: {@link Waterlogged}
     */
    TUBE_CORAL_FAN(Material.TUBE_CORAL_FAN),
    /**
     * BlockData: {@link CoralWallFan}
     */
    TUBE_CORAL_WALL_FAN(Material.TUBE_CORAL_WALL_FAN),
    /**
     * BlockData: {@link TurtleEgg}
     */
    TURTLE_EGG(Material.TURTLE_EGG),
    TURTLE_HELMET(Material.TURTLE_HELMET),
    TURTLE_SPAWN_EGG(Material.TURTLE_SPAWN_EGG),
    VEX_SPAWN_EGG(Material.VEX_SPAWN_EGG),
    VILLAGER_SPAWN_EGG(Material.VILLAGER_SPAWN_EGG),
    VINDICATOR_SPAWN_EGG(Material.VINDICATOR_SPAWN_EGG),
    /**
     * BlockData: {@link MultipleFacing}
     */
    VINE(Material.VINE),
    VOID_AIR(Material.VOID_AIR),
    /**
     * BlockData: {@link Directional}
     */
    WALL_TORCH(Material.WALL_TORCH),
    /**
     * BlockData: {@link Levelled}
     */
    WATER(Material.WATER),
    WATER_BUCKET(Material.WATER_BUCKET),
    WET_SPONGE(Material.WET_SPONGE),
    /**
     * BlockData: {@link Ageable}
     */
    WHEAT(Material.WHEAT),
    WHEAT_SEEDS(Material.WHEAT_SEEDS),
    /**
     * BlockData: {@link Rotatable}
     */
    WHITE_BANNER(Material.WHITE_BANNER),
    /**
     * BlockData: {@link Bed}
     */
    WHITE_BED(Material.WHITE_BED),
    WHITE_CARPET(Material.WHITE_CARPET),
    WHITE_CONCRETE(Material.WHITE_CONCRETE),
    WHITE_CONCRETE_POWDER(Material.WHITE_CONCRETE_POWDER),
    /**
     * BlockData: {@link Directional}
     */
    WHITE_GLAZED_TERRACOTTA(Material.WHITE_GLAZED_TERRACOTTA),
    /**
     * BlockData: {@link Directional}
     */
    WHITE_SHULKER_BOX(Material.WHITE_SHULKER_BOX),
    WHITE_STAINED_GLASS(Material.WHITE_STAINED_GLASS),
    /**
     * BlockData: {@link GlassPane}
     */
    WHITE_STAINED_GLASS_PANE(Material.WHITE_STAINED_GLASS_PANE),
    WHITE_TERRACOTTA(Material.WHITE_TERRACOTTA),
    WHITE_TULIP(Material.WHITE_TULIP),
    /**
     * BlockData: {@link Directional}
     */
    WHITE_WALL_BANNER(Material.WHITE_WALL_BANNER),
    WHITE_WOOL(Material.WHITE_WOOL),
    WITCH_SPAWN_EGG(Material.WITCH_SPAWN_EGG),
    /**
     * BlockData: {@link Rotatable}
     */
    WITHER_SKELETON_SKULL(Material.WITHER_SKELETON_SKULL),
    WITHER_SKELETON_SPAWN_EGG(Material.WITHER_SKELETON_SPAWN_EGG),
    /**
     * BlockData: {@link Directional}
     */
    WITHER_SKELETON_WALL_SKULL(Material.WITHER_SKELETON_WALL_SKULL),
    WOLF_SPAWN_EGG(Material.WOLF_SPAWN_EGG),
    WOODEN_AXE(Material.WOODEN_AXE),
    WOODEN_HOE(Material.WOODEN_HOE),
    WOODEN_PICKAXE(Material.WOODEN_PICKAXE),
    WOODEN_SHOVEL(Material.WOODEN_SHOVEL),
    WOODEN_SWORD(Material.WOODEN_SWORD),
    WRITABLE_BOOK(Material.WRITABLE_BOOK),
    WRITTEN_BOOK(Material.WRITTEN_BOOK),
    /**
     * BlockData: {@link Rotatable}
     */
    YELLOW_BANNER(Material.YELLOW_BANNER),
    /**
     * BlockData: {@link Bed}
     */
    YELLOW_BED(Material.YELLOW_BED),
    YELLOW_CARPET(Material.YELLOW_CARPET),
    YELLOW_CONCRETE(Material.YELLOW_CONCRETE),
    YELLOW_CONCRETE_POWDER(Material.YELLOW_CONCRETE_POWDER),
    /**
     * BlockData: {@link Directional}
     */
    YELLOW_GLAZED_TERRACOTTA(Material.YELLOW_GLAZED_TERRACOTTA),
    /**
     * BlockData: {@link Directional}
     */
    YELLOW_SHULKER_BOX(Material.YELLOW_SHULKER_BOX),
    YELLOW_STAINED_GLASS(Material.YELLOW_STAINED_GLASS),
    /**
     * BlockData: {@link GlassPane}
     */
    YELLOW_STAINED_GLASS_PANE(Material.YELLOW_STAINED_GLASS_PANE),
    YELLOW_TERRACOTTA(Material.YELLOW_TERRACOTTA),
    /**
     * BlockData: {@link Directional}
     */
    YELLOW_WALL_BANNER(Material.YELLOW_WALL_BANNER),
    YELLOW_WOOL(Material.YELLOW_WOOL),
    /**
     * BlockData: {@link Rotatable}
     */
    ZOMBIE_HEAD(Material.ZOMBIE_HEAD),
    ZOMBIE_HORSE_SPAWN_EGG(Material.ZOMBIE_HORSE_SPAWN_EGG),
    ZOMBIE_PIGMAN_SPAWN_EGG(Material.ZOMBIE_PIGMAN_SPAWN_EGG),
    ZOMBIE_SPAWN_EGG(Material.ZOMBIE_SPAWN_EGG),
    ZOMBIE_VILLAGER_SPAWN_EGG(Material.ZOMBIE_VILLAGER_SPAWN_EGG),
    /**
     * BlockData: {@link Directional}
     */
    ZOMBIE_WALL_HEAD(Material.ZOMBIE_WALL_HEAD),
    // ----- Legacy Separator -----
    @Deprecated
    LEGACY_AIR(Material.LEGACY_AIR),
    @Deprecated
    LEGACY_STONE(Material.LEGACY_STONE),
    @Deprecated
    LEGACY_GRASS(Material.LEGACY_GRASS),
    @Deprecated
    LEGACY_DIRT(Material.LEGACY_DIRT),
    @Deprecated
    LEGACY_COBBLESTONE(Material.LEGACY_COBBLESTONE),
    @Deprecated
    LEGACY_WOOD(Material.LEGACY_WOOD),
    @Deprecated
    LEGACY_SAPLING(Material.LEGACY_SAPLING),
    @Deprecated
    LEGACY_BEDROCK(Material.LEGACY_BEDROCK),
    @Deprecated
    LEGACY_WATER(Material.LEGACY_WATER),
    @Deprecated
    LEGACY_STATIONARY_WATER(Material.LEGACY_STATIONARY_WATER),
    @Deprecated
    LEGACY_LAVA(Material.LEGACY_LAVA),
    @Deprecated
    LEGACY_STATIONARY_LAVA(Material.LEGACY_STATIONARY_LAVA),
    @Deprecated
    LEGACY_SAND(Material.LEGACY_SAND),
    @Deprecated
    LEGACY_GRAVEL(Material.LEGACY_GRAVEL),
    @Deprecated
    LEGACY_GOLD_ORE(Material.LEGACY_GOLD_ORE),
    @Deprecated
    LEGACY_IRON_ORE(Material.LEGACY_IRON_ORE),
    @Deprecated
    LEGACY_COAL_ORE(Material.LEGACY_COAL_ORE),
    @Deprecated
    LEGACY_LOG(Material.LEGACY_LOG),
    @Deprecated
    LEGACY_LEAVES(Material.LEGACY_LEAVES),
    @Deprecated
    LEGACY_SPONGE(Material.LEGACY_SPONGE),
    @Deprecated
    LEGACY_GLASS(Material.LEGACY_GLASS),
    @Deprecated
    LEGACY_LAPIS_ORE(Material.LEGACY_LAPIS_ORE),
    @Deprecated
    LEGACY_LAPIS_BLOCK(Material.LEGACY_LAPIS_BLOCK),
    @Deprecated
    LEGACY_DISPENSER(Material.LEGACY_DISPENSER),
    @Deprecated
    LEGACY_SANDSTONE(Material.LEGACY_SANDSTONE),
    @Deprecated
    LEGACY_NOTE_BLOCK(Material.LEGACY_NOTE_BLOCK),
    @Deprecated
    LEGACY_BED_BLOCK(Material.LEGACY_BED_BLOCK),
    @Deprecated
    LEGACY_POWERED_RAIL(Material.LEGACY_POWERED_RAIL),
    @Deprecated
    LEGACY_DETECTOR_RAIL(Material.LEGACY_DETECTOR_RAIL),
    @Deprecated
    LEGACY_PISTON_STICKY_BASE(Material.LEGACY_PISTON_STICKY_BASE),
    @Deprecated
    LEGACY_WEB(Material.LEGACY_WEB),
    @Deprecated
    LEGACY_LONG_GRASS(Material.LEGACY_LONG_GRASS),
    @Deprecated
    LEGACY_DEAD_BUSH(Material.LEGACY_DEAD_BUSH),
    @Deprecated
    LEGACY_PISTON_BASE(Material.LEGACY_PISTON_BASE),
    @Deprecated
    LEGACY_PISTON_EXTENSION(Material.LEGACY_PISTON_EXTENSION),
    @Deprecated
    LEGACY_WOOL(Material.LEGACY_WOOL),
    @Deprecated
    LEGACY_PISTON_MOVING_PIECE(Material.LEGACY_PISTON_MOVING_PIECE),
    @Deprecated
    LEGACY_YELLOW_FLOWER(Material.LEGACY_YELLOW_FLOWER),
    @Deprecated
    LEGACY_RED_ROSE(Material.LEGACY_RED_ROSE),
    @Deprecated
    LEGACY_BROWN_MUSHROOM(Material.LEGACY_BROWN_MUSHROOM),
    @Deprecated
    LEGACY_RED_MUSHROOM(Material.LEGACY_RED_MUSHROOM),
    @Deprecated
    LEGACY_GOLD_BLOCK(Material.LEGACY_GOLD_BLOCK),
    @Deprecated
    LEGACY_IRON_BLOCK(Material.LEGACY_IRON_BLOCK),
    @Deprecated
    LEGACY_DOUBLE_STEP(Material.LEGACY_DOUBLE_STEP),
    @Deprecated
    LEGACY_STEP(Material.LEGACY_STEP),
    @Deprecated
    LEGACY_BRICK(Material.LEGACY_BRICK),
    @Deprecated
    LEGACY_TNT(Material.LEGACY_TNT),
    @Deprecated
    LEGACY_BOOKSHELF(Material.LEGACY_BOOKSHELF),
    @Deprecated
    LEGACY_MOSSY_COBBLESTONE(Material.LEGACY_MOSSY_COBBLESTONE),
    @Deprecated
    LEGACY_OBSIDIAN(Material.LEGACY_OBSIDIAN),
    @Deprecated
    LEGACY_TORCH(Material.LEGACY_TORCH),
    @Deprecated
    LEGACY_FIRE(Material.LEGACY_FIRE),
    @Deprecated
    LEGACY_MOB_SPAWNER(Material.LEGACY_MOB_SPAWNER),
    @Deprecated
    LEGACY_WOOD_STAIRS(Material.LEGACY_WOOD_STAIRS),
    @Deprecated
    LEGACY_CHEST(Material.LEGACY_CHEST),
    @Deprecated
    LEGACY_REDSTONE_WIRE(Material.LEGACY_REDSTONE_WIRE),
    @Deprecated
    LEGACY_DIAMOND_ORE(Material.LEGACY_DIAMOND_ORE),
    @Deprecated
    LEGACY_DIAMOND_BLOCK(Material.LEGACY_DIAMOND_BLOCK),
    @Deprecated
    LEGACY_WORKBENCH(Material.LEGACY_WORKBENCH),
    @Deprecated
    LEGACY_CROPS(Material.LEGACY_CROPS),
    @Deprecated
    LEGACY_SOIL(Material.LEGACY_SOIL),
    @Deprecated
    LEGACY_FURNACE(Material.LEGACY_FURNACE),
    @Deprecated
    LEGACY_BURNING_FURNACE(Material.LEGACY_BURNING_FURNACE),
    @Deprecated
    LEGACY_SIGN_POST(Material.LEGACY_SIGN_POST),
    @Deprecated
    LEGACY_WOODEN_DOOR(Material.LEGACY_WOODEN_DOOR),
    @Deprecated
    LEGACY_LADDER(Material.LEGACY_LADDER),
    @Deprecated
    LEGACY_RAILS(Material.LEGACY_RAILS),
    @Deprecated
    LEGACY_COBBLESTONE_STAIRS(Material.LEGACY_COBBLESTONE_STAIRS),
    @Deprecated
    LEGACY_WALL_SIGN(Material.LEGACY_WALL_SIGN),
    @Deprecated
    LEGACY_LEVER(Material.LEGACY_LEVER),
    @Deprecated
    LEGACY_STONE_PLATE(Material.LEGACY_STONE_PLATE),
    @Deprecated
    LEGACY_IRON_DOOR_BLOCK(Material.LEGACY_IRON_DOOR_BLOCK),
    @Deprecated
    LEGACY_WOOD_PLATE(Material.LEGACY_WOOD_PLATE),
    @Deprecated
    LEGACY_REDSTONE_ORE(Material.LEGACY_REDSTONE_ORE),
    @Deprecated
    LEGACY_GLOWING_REDSTONE_ORE(Material.LEGACY_GLOWING_REDSTONE_ORE),
    @Deprecated
    LEGACY_REDSTONE_TORCH_OFF(Material.LEGACY_REDSTONE_TORCH_OFF),
    @Deprecated
    LEGACY_REDSTONE_TORCH_ON(Material.LEGACY_REDSTONE_TORCH_ON),
    @Deprecated
    LEGACY_STONE_BUTTON(Material.LEGACY_STONE_BUTTON),
    @Deprecated
    LEGACY_SNOW(Material.LEGACY_SNOW),
    @Deprecated
    LEGACY_ICE(Material.LEGACY_ICE),
    @Deprecated
    LEGACY_SNOW_BLOCK(Material.LEGACY_SNOW_BLOCK),
    @Deprecated
    LEGACY_CACTUS(Material.LEGACY_CACTUS),
    @Deprecated
    LEGACY_CLAY(Material.LEGACY_CLAY),
    @Deprecated
    LEGACY_SUGAR_CANE_BLOCK(Material.LEGACY_SUGAR_CANE_BLOCK),
    @Deprecated
    LEGACY_JUKEBOX(Material.LEGACY_JUKEBOX),
    @Deprecated
    LEGACY_FENCE(Material.LEGACY_FENCE),
    @Deprecated
    LEGACY_PUMPKIN(Material.LEGACY_PUMPKIN),
    @Deprecated
    LEGACY_NETHERRACK(Material.LEGACY_NETHERRACK),
    @Deprecated
    LEGACY_SOUL_SAND(Material.LEGACY_SOUL_SAND),
    @Deprecated
    LEGACY_GLOWSTONE(Material.LEGACY_GLOWSTONE),
    @Deprecated
    LEGACY_PORTAL(Material.LEGACY_PORTAL),
    @Deprecated
    LEGACY_JACK_O_LANTERN(Material.LEGACY_JACK_O_LANTERN),
    @Deprecated
    LEGACY_CAKE_BLOCK(Material.LEGACY_CAKE_BLOCK),
    @Deprecated
    LEGACY_DIODE_BLOCK_OFF(Material.LEGACY_DIODE_BLOCK_OFF),
    @Deprecated
    LEGACY_DIODE_BLOCK_ON(Material.LEGACY_DIODE_BLOCK_ON),
    @Deprecated
    LEGACY_STAINED_GLASS(Material.LEGACY_STAINED_GLASS),
    @Deprecated
    LEGACY_TRAP_DOOR(Material.LEGACY_TRAP_DOOR),
    @Deprecated
    LEGACY_MONSTER_EGGS(Material.LEGACY_MONSTER_EGGS),
    @Deprecated
    LEGACY_SMOOTH_BRICK(Material.LEGACY_SMOOTH_BRICK),
    @Deprecated
    LEGACY_HUGE_MUSHROOM_1(Material.LEGACY_HUGE_MUSHROOM_1),
    @Deprecated
    LEGACY_HUGE_MUSHROOM_2(Material.LEGACY_HUGE_MUSHROOM_2),
    @Deprecated
    LEGACY_IRON_FENCE(Material.LEGACY_IRON_FENCE),
    @Deprecated
    LEGACY_THIN_GLASS(Material.LEGACY_THIN_GLASS),
    @Deprecated
    LEGACY_MELON_BLOCK(Material.LEGACY_MELON_BLOCK),
    @Deprecated
    LEGACY_PUMPKIN_STEM(Material.LEGACY_PUMPKIN_STEM),
    @Deprecated
    LEGACY_MELON_STEM(Material.LEGACY_MELON_STEM),
    @Deprecated
    LEGACY_VINE(Material.LEGACY_VINE),
    @Deprecated
    LEGACY_FENCE_GATE(Material.LEGACY_FENCE_GATE),
    @Deprecated
    LEGACY_BRICK_STAIRS(Material.LEGACY_BRICK_STAIRS),
    @Deprecated
    LEGACY_SMOOTH_STAIRS(Material.LEGACY_SMOOTH_STAIRS),
    @Deprecated
    LEGACY_MYCEL(Material.LEGACY_MYCEL),
    @Deprecated
    LEGACY_WATER_LILY(Material.LEGACY_WATER_LILY),
    @Deprecated
    LEGACY_NETHER_BRICK(Material.LEGACY_NETHER_BRICK),
    @Deprecated
    LEGACY_NETHER_FENCE(Material.LEGACY_NETHER_FENCE),
    @Deprecated
    LEGACY_NETHER_BRICK_STAIRS(Material.LEGACY_NETHER_BRICK_STAIRS),
    @Deprecated
    LEGACY_NETHER_WARTS(Material.LEGACY_NETHER_WARTS),
    @Deprecated
    LEGACY_ENCHANTMENT_TABLE(Material.LEGACY_ENCHANTMENT_TABLE),
    @Deprecated
    LEGACY_BREWING_STAND(Material.LEGACY_BREWING_STAND),
    @Deprecated
    LEGACY_CAULDRON(Material.LEGACY_CAULDRON),
    @Deprecated
    LEGACY_ENDER_PORTAL(Material.LEGACY_ENDER_PORTAL),
    @Deprecated
    LEGACY_ENDER_PORTAL_FRAME(Material.LEGACY_ENDER_PORTAL_FRAME),
    @Deprecated
    LEGACY_ENDER_STONE(Material.LEGACY_ENDER_STONE),
    @Deprecated
    LEGACY_DRAGON_EGG(Material.LEGACY_DRAGON_EGG),
    @Deprecated
    LEGACY_REDSTONE_LAMP_OFF(Material.LEGACY_REDSTONE_LAMP_OFF),
    @Deprecated
    LEGACY_REDSTONE_LAMP_ON(Material.LEGACY_REDSTONE_LAMP_ON),
    @Deprecated
    LEGACY_WOOD_DOUBLE_STEP(Material.LEGACY_WOOD_DOUBLE_STEP),
    @Deprecated
    LEGACY_WOOD_STEP(Material.LEGACY_WOOD_STEP),
    @Deprecated
    LEGACY_COCOA(Material.LEGACY_COCOA),
    @Deprecated
    LEGACY_SANDSTONE_STAIRS(Material.LEGACY_SANDSTONE_STAIRS),
    @Deprecated
    LEGACY_EMERALD_ORE(Material.LEGACY_EMERALD_ORE),
    @Deprecated
    LEGACY_ENDER_CHEST(Material.LEGACY_ENDER_CHEST),
    @Deprecated
    LEGACY_TRIPWIRE_HOOK(Material.LEGACY_TRIPWIRE_HOOK),
    @Deprecated
    LEGACY_TRIPWIRE(Material.LEGACY_TRIPWIRE),
    @Deprecated
    LEGACY_EMERALD_BLOCK(Material.LEGACY_EMERALD_BLOCK),
    @Deprecated
    LEGACY_SPRUCE_WOOD_STAIRS(Material.LEGACY_SPRUCE_WOOD_STAIRS),
    @Deprecated
    LEGACY_BIRCH_WOOD_STAIRS(Material.LEGACY_BIRCH_WOOD_STAIRS),
    @Deprecated
    LEGACY_JUNGLE_WOOD_STAIRS(Material.LEGACY_JUNGLE_WOOD_STAIRS),
    @Deprecated
    LEGACY_COMMAND(Material.LEGACY_COMMAND),
    @Deprecated
    LEGACY_BEACON(Material.LEGACY_BEACON),
    @Deprecated
    LEGACY_COBBLE_WALL(Material.LEGACY_COBBLE_WALL),
    @Deprecated
    LEGACY_FLOWER_POT(Material.LEGACY_FLOWER_POT),
    @Deprecated
    LEGACY_CARROT(Material.LEGACY_CARROT),
    @Deprecated
    LEGACY_POTATO(Material.LEGACY_POTATO),
    @Deprecated
    LEGACY_WOOD_BUTTON(Material.LEGACY_WOOD_BUTTON),
    @Deprecated
    LEGACY_SKULL(Material.LEGACY_SKULL),
    @Deprecated
    LEGACY_ANVIL(Material.LEGACY_ANVIL),
    @Deprecated
    LEGACY_TRAPPED_CHEST(Material.LEGACY_TRAPPED_CHEST),
    @Deprecated
    LEGACY_GOLD_PLATE(Material.LEGACY_GOLD_PLATE),
    @Deprecated
    LEGACY_IRON_PLATE(Material.LEGACY_IRON_PLATE),
    @Deprecated
    LEGACY_REDSTONE_COMPARATOR_OFF(Material.LEGACY_REDSTONE_COMPARATOR_OFF),
    @Deprecated
    LEGACY_REDSTONE_COMPARATOR_ON(Material.LEGACY_REDSTONE_COMPARATOR_ON),
    @Deprecated
    LEGACY_DAYLIGHT_DETECTOR(Material.LEGACY_DAYLIGHT_DETECTOR),
    @Deprecated
    LEGACY_REDSTONE_BLOCK(Material.LEGACY_REDSTONE_BLOCK),
    @Deprecated
    LEGACY_QUARTZ_ORE(Material.LEGACY_QUARTZ_ORE),
    @Deprecated
    LEGACY_HOPPER(Material.LEGACY_HOPPER),
    @Deprecated
    LEGACY_QUARTZ_BLOCK(Material.LEGACY_QUARTZ_BLOCK),
    @Deprecated
    LEGACY_QUARTZ_STAIRS(Material.LEGACY_QUARTZ_STAIRS),
    @Deprecated
    LEGACY_ACTIVATOR_RAIL(Material.LEGACY_ACTIVATOR_RAIL),
    @Deprecated
    LEGACY_DROPPER(Material.LEGACY_DROPPER),
    @Deprecated
    LEGACY_STAINED_CLAY(Material.LEGACY_STAINED_CLAY),
    @Deprecated
    LEGACY_STAINED_GLASS_PANE(Material.LEGACY_STAINED_GLASS_PANE),
    @Deprecated
    LEGACY_LEAVES_2(Material.LEGACY_LEAVES_2),
    @Deprecated
    LEGACY_LOG_2(Material.LEGACY_LOG_2),
    @Deprecated
    LEGACY_ACACIA_STAIRS(Material.LEGACY_ACACIA_STAIRS),
    @Deprecated
    LEGACY_DARK_OAK_STAIRS(Material.LEGACY_DARK_OAK_STAIRS),
    @Deprecated
    LEGACY_SLIME_BLOCK(Material.LEGACY_SLIME_BLOCK),
    @Deprecated
    LEGACY_BARRIER(Material.LEGACY_BARRIER),
    @Deprecated
    LEGACY_IRON_TRAPDOOR(Material.LEGACY_IRON_TRAPDOOR),
    @Deprecated
    LEGACY_PRISMARINE(Material.LEGACY_PRISMARINE),
    @Deprecated
    LEGACY_SEA_LANTERN(Material.LEGACY_SEA_LANTERN),
    @Deprecated
    LEGACY_HAY_BLOCK(Material.LEGACY_HAY_BLOCK),
    @Deprecated
    LEGACY_CARPET(Material.LEGACY_CARPET),
    @Deprecated
    LEGACY_HARD_CLAY(Material.LEGACY_HARD_CLAY),
    @Deprecated
    LEGACY_COAL_BLOCK(Material.LEGACY_COAL_BLOCK),
    @Deprecated
    LEGACY_PACKED_ICE(Material.LEGACY_PACKED_ICE),
    @Deprecated
    LEGACY_DOUBLE_PLANT(Material.LEGACY_DOUBLE_PLANT),
    @Deprecated
    LEGACY_STANDING_BANNER(Material.LEGACY_STANDING_BANNER),
    @Deprecated
    LEGACY_WALL_BANNER(Material.LEGACY_WALL_BANNER),
    @Deprecated
    LEGACY_DAYLIGHT_DETECTOR_INVERTED(Material.LEGACY_DAYLIGHT_DETECTOR_INVERTED),
    @Deprecated
    LEGACY_RED_SANDSTONE(Material.LEGACY_RED_SANDSTONE),
    @Deprecated
    LEGACY_RED_SANDSTONE_STAIRS(Material.LEGACY_RED_SANDSTONE_STAIRS),
    @Deprecated
    LEGACY_DOUBLE_STONE_SLAB2(Material.LEGACY_DOUBLE_STONE_SLAB2),
    @Deprecated
    LEGACY_STONE_SLAB2(Material.LEGACY_STONE_SLAB2),
    @Deprecated
    LEGACY_SPRUCE_FENCE_GATE(Material.LEGACY_SPRUCE_FENCE_GATE),
    @Deprecated
    LEGACY_BIRCH_FENCE_GATE(Material.LEGACY_BIRCH_FENCE_GATE),
    @Deprecated
    LEGACY_JUNGLE_FENCE_GATE(Material.LEGACY_JUNGLE_FENCE_GATE),
    @Deprecated
    LEGACY_DARK_OAK_FENCE_GATE(Material.LEGACY_DARK_OAK_FENCE_GATE),
    @Deprecated
    LEGACY_ACACIA_FENCE_GATE(Material.LEGACY_ACACIA_FENCE_GATE),
    @Deprecated
    LEGACY_SPRUCE_FENCE(Material.LEGACY_SPRUCE_FENCE),
    @Deprecated
    LEGACY_BIRCH_FENCE(Material.LEGACY_BIRCH_FENCE),
    @Deprecated
    LEGACY_JUNGLE_FENCE(Material.LEGACY_JUNGLE_FENCE),
    @Deprecated
    LEGACY_DARK_OAK_FENCE(Material.LEGACY_DARK_OAK_FENCE),
    @Deprecated
    LEGACY_ACACIA_FENCE(Material.LEGACY_ACACIA_FENCE),
    @Deprecated
    LEGACY_SPRUCE_DOOR(Material.LEGACY_SPRUCE_DOOR),
    @Deprecated
    LEGACY_BIRCH_DOOR(Material.LEGACY_BIRCH_DOOR),
    @Deprecated
    LEGACY_JUNGLE_DOOR(Material.LEGACY_JUNGLE_DOOR),
    @Deprecated
    LEGACY_ACACIA_DOOR(Material.LEGACY_ACACIA_DOOR),
    @Deprecated
    LEGACY_DARK_OAK_DOOR(Material.LEGACY_DARK_OAK_DOOR),
    @Deprecated
    LEGACY_END_ROD(Material.LEGACY_END_ROD),
    @Deprecated
    LEGACY_CHORUS_PLANT(Material.LEGACY_CHORUS_PLANT),
    @Deprecated
    LEGACY_CHORUS_FLOWER(Material.LEGACY_CHORUS_FLOWER),
    @Deprecated
    LEGACY_PURPUR_BLOCK(Material.LEGACY_PURPUR_BLOCK),
    @Deprecated
    LEGACY_PURPUR_PILLAR(Material.LEGACY_PURPUR_PILLAR),
    @Deprecated
    LEGACY_PURPUR_STAIRS(Material.LEGACY_PURPUR_STAIRS),
    @Deprecated
    LEGACY_PURPUR_DOUBLE_SLAB(Material.LEGACY_PURPUR_DOUBLE_SLAB),
    @Deprecated
    LEGACY_PURPUR_SLAB(Material.LEGACY_PURPUR_SLAB),
    @Deprecated
    LEGACY_END_BRICKS(Material.LEGACY_END_BRICKS),
    @Deprecated
    LEGACY_BEETROOT_BLOCK(Material.LEGACY_BEETROOT_BLOCK),
    @Deprecated
    LEGACY_GRASS_PATH(Material.LEGACY_GRASS_PATH),
    @Deprecated
    LEGACY_END_GATEWAY(Material.LEGACY_END_GATEWAY),
    @Deprecated
    LEGACY_COMMAND_REPEATING(Material.LEGACY_COMMAND_REPEATING),
    @Deprecated
    LEGACY_COMMAND_CHAIN(Material.LEGACY_COMMAND_CHAIN),
    @Deprecated
    LEGACY_FROSTED_ICE(Material.LEGACY_FROSTED_ICE),
    @Deprecated
    LEGACY_MAGMA(Material.LEGACY_MAGMA),
    @Deprecated
    LEGACY_NETHER_WART_BLOCK(Material.LEGACY_NETHER_WART_BLOCK),
    @Deprecated
    LEGACY_RED_NETHER_BRICK(Material.LEGACY_RED_NETHER_BRICK),
    @Deprecated
    LEGACY_BONE_BLOCK(Material.LEGACY_BONE_BLOCK),
    @Deprecated
    LEGACY_STRUCTURE_VOID(Material.LEGACY_STRUCTURE_VOID),
    @Deprecated
    LEGACY_OBSERVER(Material.LEGACY_OBSERVER),
    @Deprecated
    LEGACY_WHITE_SHULKER_BOX(Material.LEGACY_WHITE_SHULKER_BOX),
    @Deprecated
    LEGACY_ORANGE_SHULKER_BOX(Material.LEGACY_ORANGE_SHULKER_BOX),
    @Deprecated
    LEGACY_MAGENTA_SHULKER_BOX(Material.LEGACY_MAGENTA_SHULKER_BOX),
    @Deprecated
    LEGACY_LIGHT_BLUE_SHULKER_BOX(Material.LEGACY_LIGHT_BLUE_SHULKER_BOX),
    @Deprecated
    LEGACY_YELLOW_SHULKER_BOX(Material.LEGACY_YELLOW_SHULKER_BOX),
    @Deprecated
    LEGACY_LIME_SHULKER_BOX(Material.LEGACY_LIME_SHULKER_BOX),
    @Deprecated
    LEGACY_PINK_SHULKER_BOX(Material.LEGACY_PINK_SHULKER_BOX),
    @Deprecated
    LEGACY_GRAY_SHULKER_BOX(Material.LEGACY_GRAY_SHULKER_BOX),
    @Deprecated
    LEGACY_SILVER_SHULKER_BOX(Material.LEGACY_SILVER_SHULKER_BOX),
    @Deprecated
    LEGACY_CYAN_SHULKER_BOX(Material.LEGACY_CYAN_SHULKER_BOX),
    @Deprecated
    LEGACY_PURPLE_SHULKER_BOX(Material.LEGACY_PURPLE_SHULKER_BOX),
    @Deprecated
    LEGACY_BLUE_SHULKER_BOX(Material.LEGACY_BLUE_SHULKER_BOX),
    @Deprecated
    LEGACY_BROWN_SHULKER_BOX(Material.LEGACY_BROWN_SHULKER_BOX),
    @Deprecated
    LEGACY_GREEN_SHULKER_BOX(Material.LEGACY_GREEN_SHULKER_BOX),
    @Deprecated
    LEGACY_RED_SHULKER_BOX(Material.LEGACY_RED_SHULKER_BOX),
    @Deprecated
    LEGACY_BLACK_SHULKER_BOX(Material.LEGACY_BLACK_SHULKER_BOX),
    @Deprecated
    LEGACY_WHITE_GLAZED_TERRACOTTA(Material.LEGACY_WHITE_GLAZED_TERRACOTTA),
    @Deprecated
    LEGACY_ORANGE_GLAZED_TERRACOTTA(Material.LEGACY_ORANGE_GLAZED_TERRACOTTA),
    @Deprecated
    LEGACY_MAGENTA_GLAZED_TERRACOTTA(Material.LEGACY_MAGENTA_GLAZED_TERRACOTTA),
    @Deprecated
    LEGACY_LIGHT_BLUE_GLAZED_TERRACOTTA(Material.LEGACY_LIGHT_BLUE_GLAZED_TERRACOTTA),
    @Deprecated
    LEGACY_YELLOW_GLAZED_TERRACOTTA(Material.LEGACY_YELLOW_GLAZED_TERRACOTTA),
    @Deprecated
    LEGACY_LIME_GLAZED_TERRACOTTA(Material.LEGACY_LIME_GLAZED_TERRACOTTA),
    @Deprecated
    LEGACY_PINK_GLAZED_TERRACOTTA(Material.LEGACY_PINK_GLAZED_TERRACOTTA),
    @Deprecated
    LEGACY_GRAY_GLAZED_TERRACOTTA(Material.LEGACY_GRAY_GLAZED_TERRACOTTA),
    @Deprecated
    LEGACY_SILVER_GLAZED_TERRACOTTA(Material.LEGACY_SILVER_GLAZED_TERRACOTTA),
    @Deprecated
    LEGACY_CYAN_GLAZED_TERRACOTTA(Material.LEGACY_CYAN_GLAZED_TERRACOTTA),
    @Deprecated
    LEGACY_PURPLE_GLAZED_TERRACOTTA(Material.LEGACY_PURPLE_GLAZED_TERRACOTTA),
    @Deprecated
    LEGACY_BLUE_GLAZED_TERRACOTTA(Material.LEGACY_BLUE_GLAZED_TERRACOTTA),
    @Deprecated
    LEGACY_BROWN_GLAZED_TERRACOTTA(Material.LEGACY_BROWN_GLAZED_TERRACOTTA),
    @Deprecated
    LEGACY_GREEN_GLAZED_TERRACOTTA(Material.LEGACY_GREEN_GLAZED_TERRACOTTA),
    @Deprecated
    LEGACY_RED_GLAZED_TERRACOTTA(Material.LEGACY_RED_GLAZED_TERRACOTTA),
    @Deprecated
    LEGACY_BLACK_GLAZED_TERRACOTTA(Material.LEGACY_BLACK_GLAZED_TERRACOTTA),
    @Deprecated
    LEGACY_CONCRETE(Material.LEGACY_CONCRETE),
    @Deprecated
    LEGACY_CONCRETE_POWDER(Material.LEGACY_CONCRETE_POWDER),
    @Deprecated
    LEGACY_STRUCTURE_BLOCK(Material.LEGACY_STRUCTURE_BLOCK),
    // ----- Item Separator -----
    @Deprecated
    LEGACY_IRON_SPADE(Material.LEGACY_IRON_SPADE),
    @Deprecated
    LEGACY_IRON_PICKAXE(Material.LEGACY_IRON_PICKAXE),
    @Deprecated
    LEGACY_IRON_AXE(Material.LEGACY_IRON_AXE),
    @Deprecated
    LEGACY_FLINT_AND_STEEL(Material.LEGACY_FLINT_AND_STEEL),
    @Deprecated
    LEGACY_APPLE(Material.LEGACY_APPLE),
    @Deprecated
    LEGACY_BOW(Material.LEGACY_BOW),
    @Deprecated
    LEGACY_ARROW(Material.LEGACY_ARROW),
    @Deprecated
    LEGACY_COAL(Material.LEGACY_COAL),
    @Deprecated
    LEGACY_DIAMOND(Material.LEGACY_DIAMOND),
    @Deprecated
    LEGACY_IRON_INGOT(Material.LEGACY_IRON_INGOT),
    @Deprecated
    LEGACY_GOLD_INGOT(Material.LEGACY_GOLD_INGOT),
    @Deprecated
    LEGACY_IRON_SWORD(Material.LEGACY_IRON_SWORD),
    @Deprecated
    LEGACY_WOOD_SWORD(Material.LEGACY_WOOD_SWORD),
    @Deprecated
    LEGACY_WOOD_SPADE(Material.LEGACY_WOOD_SPADE),
    @Deprecated
    LEGACY_WOOD_PICKAXE(Material.LEGACY_WOOD_PICKAXE),
    @Deprecated
    LEGACY_WOOD_AXE(Material.LEGACY_WOOD_AXE),
    @Deprecated
    LEGACY_STONE_SWORD(Material.LEGACY_STONE_SWORD),
    @Deprecated
    LEGACY_STONE_SPADE(Material.LEGACY_STONE_SPADE),
    @Deprecated
    LEGACY_STONE_PICKAXE(Material.LEGACY_STONE_PICKAXE),
    @Deprecated
    LEGACY_STONE_AXE(Material.LEGACY_STONE_AXE),
    @Deprecated
    LEGACY_DIAMOND_SWORD(Material.LEGACY_DIAMOND_SWORD),
    @Deprecated
    LEGACY_DIAMOND_SPADE(Material.LEGACY_DIAMOND_SPADE),
    @Deprecated
    LEGACY_DIAMOND_PICKAXE(Material.LEGACY_DIAMOND_PICKAXE),
    @Deprecated
    LEGACY_DIAMOND_AXE(Material.LEGACY_DIAMOND_AXE),
    @Deprecated
    LEGACY_STICK(Material.LEGACY_STICK),
    @Deprecated
    LEGACY_BOWL(Material.LEGACY_BOWL),
    @Deprecated
    LEGACY_MUSHROOM_SOUP(Material.LEGACY_MUSHROOM_SOUP),
    @Deprecated
    LEGACY_GOLD_SWORD(Material.LEGACY_GOLD_SWORD),
    @Deprecated
    LEGACY_GOLD_SPADE(Material.LEGACY_GOLD_SPADE),
    @Deprecated
    LEGACY_GOLD_PICKAXE(Material.LEGACY_GOLD_PICKAXE),
    @Deprecated
    LEGACY_GOLD_AXE(Material.LEGACY_GOLD_AXE),
    @Deprecated
    LEGACY_STRING(Material.LEGACY_STRING),
    @Deprecated
    LEGACY_FEATHER(Material.LEGACY_FEATHER),
    @Deprecated
    LEGACY_SULPHUR(Material.LEGACY_SULPHUR),
    @Deprecated
    LEGACY_WOOD_HOE(Material.LEGACY_WOOD_HOE),
    @Deprecated
    LEGACY_STONE_HOE(Material.LEGACY_STONE_HOE),
    @Deprecated
    LEGACY_IRON_HOE(Material.LEGACY_IRON_HOE),
    @Deprecated
    LEGACY_DIAMOND_HOE(Material.LEGACY_DIAMOND_HOE),
    @Deprecated
    LEGACY_GOLD_HOE(Material.LEGACY_GOLD_HOE),
    @Deprecated
    LEGACY_SEEDS(Material.LEGACY_SEEDS),
    @Deprecated
    LEGACY_WHEAT(Material.LEGACY_WHEAT),
    @Deprecated
    LEGACY_BREAD(Material.LEGACY_BREAD),
    @Deprecated
    LEGACY_LEATHER_HELMET(Material.LEGACY_LEATHER_HELMET),
    @Deprecated
    LEGACY_LEATHER_CHESTPLATE(Material.LEGACY_LEATHER_CHESTPLATE),
    @Deprecated
    LEGACY_LEATHER_LEGGINGS(Material.LEGACY_LEATHER_LEGGINGS),
    @Deprecated
    LEGACY_LEATHER_BOOTS(Material.LEGACY_LEATHER_BOOTS),
    @Deprecated
    LEGACY_CHAINMAIL_HELMET(Material.LEGACY_CHAINMAIL_HELMET),
    @Deprecated
    LEGACY_CHAINMAIL_CHESTPLATE(Material.LEGACY_CHAINMAIL_CHESTPLATE),
    @Deprecated
    LEGACY_CHAINMAIL_LEGGINGS(Material.LEGACY_CHAINMAIL_LEGGINGS),
    @Deprecated
    LEGACY_CHAINMAIL_BOOTS(Material.LEGACY_CHAINMAIL_BOOTS),
    @Deprecated
    LEGACY_IRON_HELMET(Material.LEGACY_IRON_HELMET),
    @Deprecated
    LEGACY_IRON_CHESTPLATE(Material.LEGACY_IRON_CHESTPLATE),
    @Deprecated
    LEGACY_IRON_LEGGINGS(Material.LEGACY_IRON_LEGGINGS),
    @Deprecated
    LEGACY_IRON_BOOTS(Material.LEGACY_IRON_BOOTS),
    @Deprecated
    LEGACY_DIAMOND_HELMET(Material.LEGACY_DIAMOND_HELMET),
    @Deprecated
    LEGACY_DIAMOND_CHESTPLATE(Material.LEGACY_DIAMOND_CHESTPLATE),
    @Deprecated
    LEGACY_DIAMOND_LEGGINGS(Material.LEGACY_DIAMOND_LEGGINGS),
    @Deprecated
    LEGACY_DIAMOND_BOOTS(Material.LEGACY_DIAMOND_BOOTS),
    @Deprecated
    LEGACY_GOLD_HELMET(Material.LEGACY_GOLD_HELMET),
    @Deprecated
    LEGACY_GOLD_CHESTPLATE(Material.LEGACY_GOLD_CHESTPLATE),
    @Deprecated
    LEGACY_GOLD_LEGGINGS(Material.LEGACY_GOLD_LEGGINGS),
    @Deprecated
    LEGACY_GOLD_BOOTS(Material.LEGACY_GOLD_BOOTS),
    @Deprecated
    LEGACY_FLINT(Material.LEGACY_FLINT),
    @Deprecated
    LEGACY_PORK(Material.LEGACY_PORK),
    @Deprecated
    LEGACY_GRILLED_PORK(Material.LEGACY_GRILLED_PORK),
    @Deprecated
    LEGACY_PAINTING(Material.LEGACY_PAINTING),
    @Deprecated
    LEGACY_GOLDEN_APPLE(Material.LEGACY_GOLDEN_APPLE),
    @Deprecated
    LEGACY_SIGN(Material.LEGACY_SIGN),
    @Deprecated
    LEGACY_WOOD_DOOR(Material.LEGACY_WOOD_DOOR),
    @Deprecated
    LEGACY_BUCKET(Material.LEGACY_BUCKET),
    @Deprecated
    LEGACY_WATER_BUCKET(Material.LEGACY_WATER_BUCKET),
    @Deprecated
    LEGACY_LAVA_BUCKET(Material.LEGACY_LAVA_BUCKET),
    @Deprecated
    LEGACY_MINECART(Material.LEGACY_MINECART),
    @Deprecated
    LEGACY_SADDLE(Material.LEGACY_SADDLE),
    @Deprecated
    LEGACY_IRON_DOOR(Material.LEGACY_IRON_DOOR),
    @Deprecated
    LEGACY_REDSTONE(Material.LEGACY_REDSTONE),
    @Deprecated
    LEGACY_SNOW_BALL(Material.LEGACY_SNOW_BALL),
    @Deprecated
    LEGACY_BOAT(Material.LEGACY_BOAT),
    @Deprecated
    LEGACY_LEATHER(Material.LEGACY_LEATHER),
    @Deprecated
    LEGACY_MILK_BUCKET(Material.LEGACY_MILK_BUCKET),
    @Deprecated
    LEGACY_CLAY_BRICK(Material.LEGACY_CLAY_BRICK),
    @Deprecated
    LEGACY_CLAY_BALL(Material.LEGACY_CLAY_BALL),
    @Deprecated
    LEGACY_SUGAR_CANE(Material.LEGACY_SUGAR_CANE),
    @Deprecated
    LEGACY_PAPER(Material.LEGACY_PAPER),
    @Deprecated
    LEGACY_BOOK(Material.LEGACY_BOOK),
    @Deprecated
    LEGACY_SLIME_BALL(Material.LEGACY_SLIME_BALL),
    @Deprecated
    LEGACY_STORAGE_MINECART(Material.LEGACY_STORAGE_MINECART),
    @Deprecated
    LEGACY_POWERED_MINECART(Material.LEGACY_POWERED_MINECART),
    @Deprecated
    LEGACY_EGG(Material.LEGACY_EGG),
    @Deprecated
    LEGACY_COMPASS(Material.LEGACY_COMPASS),
    @Deprecated
    LEGACY_FISHING_ROD(Material.LEGACY_FISHING_ROD),
    @Deprecated
    LEGACY_WATCH(Material.LEGACY_WATCH),
    @Deprecated
    LEGACY_GLOWSTONE_DUST(Material.LEGACY_GLOWSTONE_DUST),
    @Deprecated
    LEGACY_RAW_FISH(Material.LEGACY_RAW_FISH),
    @Deprecated
    LEGACY_COOKED_FISH(Material.LEGACY_COOKED_FISH),
    @Deprecated
    LEGACY_INK_SACK(Material.LEGACY_INK_SACK),
    @Deprecated
    LEGACY_BONE(Material.LEGACY_BONE),
    @Deprecated
    LEGACY_SUGAR(Material.LEGACY_SUGAR),
    @Deprecated
    LEGACY_CAKE(Material.LEGACY_CAKE),
    @Deprecated
    LEGACY_BED(Material.LEGACY_BED),
    @Deprecated
    LEGACY_DIODE(Material.LEGACY_DIODE),
    @Deprecated
    LEGACY_COOKIE(Material.LEGACY_COOKIE),
    /**
     * @see org.bukkit.map.MapView
     */
    @Deprecated
    LEGACY_MAP(Material.LEGACY_MAP),
    @Deprecated
    LEGACY_SHEARS(Material.LEGACY_SHEARS),
    @Deprecated
    LEGACY_MELON(Material.LEGACY_MELON),
    @Deprecated
    LEGACY_PUMPKIN_SEEDS(Material.LEGACY_PUMPKIN_SEEDS),
    @Deprecated
    LEGACY_MELON_SEEDS(Material.LEGACY_MELON_SEEDS),
    @Deprecated
    LEGACY_RAW_BEEF(Material.LEGACY_RAW_BEEF),
    @Deprecated
    LEGACY_COOKED_BEEF(Material.LEGACY_COOKED_BEEF),
    @Deprecated
    LEGACY_RAW_CHICKEN(Material.LEGACY_RAW_CHICKEN),
    @Deprecated
    LEGACY_COOKED_CHICKEN(Material.LEGACY_COOKED_CHICKEN),
    @Deprecated
    LEGACY_ROTTEN_FLESH(Material.LEGACY_ROTTEN_FLESH),
    @Deprecated
    LEGACY_ENDER_PEARL(Material.LEGACY_ENDER_PEARL),
    @Deprecated
    LEGACY_BLAZE_ROD(Material.LEGACY_BLAZE_ROD),
    @Deprecated
    LEGACY_GHAST_TEAR(Material.LEGACY_GHAST_TEAR),
    @Deprecated
    LEGACY_GOLD_NUGGET(Material.LEGACY_GOLD_NUGGET),
    @Deprecated
    LEGACY_NETHER_STALK(Material.LEGACY_NETHER_STALK),
    @Deprecated
    LEGACY_POTION(Material.LEGACY_POTION),
    @Deprecated
    LEGACY_GLASS_BOTTLE(Material.LEGACY_GLASS_BOTTLE),
    @Deprecated
    LEGACY_SPIDER_EYE(Material.LEGACY_SPIDER_EYE),
    @Deprecated
    LEGACY_FERMENTED_SPIDER_EYE(Material.LEGACY_FERMENTED_SPIDER_EYE),
    @Deprecated
    LEGACY_BLAZE_POWDER(Material.LEGACY_BLAZE_POWDER),
    @Deprecated
    LEGACY_MAGMA_CREAM(Material.LEGACY_MAGMA_CREAM),
    @Deprecated
    LEGACY_BREWING_STAND_ITEM(Material.LEGACY_BREWING_STAND_ITEM),
    @Deprecated
    LEGACY_CAULDRON_ITEM(Material.LEGACY_CAULDRON_ITEM),
    @Deprecated
    LEGACY_EYE_OF_ENDER(Material.LEGACY_EYE_OF_ENDER),
    @Deprecated
    LEGACY_SPECKLED_MELON(Material.LEGACY_SPECKLED_MELON),
    @Deprecated
    LEGACY_MONSTER_EGG(Material.LEGACY_MONSTER_EGG),
    @Deprecated
    LEGACY_EXP_BOTTLE(Material.LEGACY_EXP_BOTTLE),
    @Deprecated
    LEGACY_FIREBALL(Material.LEGACY_FIREBALL),
    @Deprecated
    LEGACY_BOOK_AND_QUILL(Material.LEGACY_BOOK_AND_QUILL),
    @Deprecated
    LEGACY_WRITTEN_BOOK(Material.LEGACY_WRITTEN_BOOK),
    @Deprecated
    LEGACY_EMERALD(Material.LEGACY_EMERALD),
    @Deprecated
    LEGACY_ITEM_FRAME(Material.LEGACY_ITEM_FRAME),
    @Deprecated
    LEGACY_FLOWER_POT_ITEM(Material.LEGACY_FLOWER_POT_ITEM),
    @Deprecated
    LEGACY_CARROT_ITEM(Material.LEGACY_CARROT_ITEM),
    @Deprecated
    LEGACY_POTATO_ITEM(Material.LEGACY_POTATO_ITEM),
    @Deprecated
    LEGACY_BAKED_POTATO(Material.LEGACY_BAKED_POTATO),
    @Deprecated
    LEGACY_POISONOUS_POTATO(Material.LEGACY_POISONOUS_POTATO),
    @Deprecated
    LEGACY_EMPTY_MAP(Material.LEGACY_EMPTY_MAP),
    @Deprecated
    LEGACY_GOLDEN_CARROT(Material.LEGACY_GOLDEN_CARROT),
    @Deprecated
    LEGACY_SKULL_ITEM(Material.LEGACY_SKULL_ITEM),
    @Deprecated
    LEGACY_CARROT_STICK(Material.LEGACY_CARROT_STICK),
    @Deprecated
    LEGACY_NETHER_STAR(Material.LEGACY_NETHER_STAR),
    @Deprecated
    LEGACY_PUMPKIN_PIE(Material.LEGACY_PUMPKIN_PIE),
    @Deprecated
    LEGACY_FIREWORK(Material.LEGACY_FIREWORK),
    @Deprecated
    LEGACY_FIREWORK_CHARGE(Material.LEGACY_FIREWORK_CHARGE),
    @Deprecated
    LEGACY_ENCHANTED_BOOK(Material.LEGACY_ENCHANTED_BOOK),
    @Deprecated
    LEGACY_REDSTONE_COMPARATOR(Material.LEGACY_REDSTONE_COMPARATOR),
    @Deprecated
    LEGACY_NETHER_BRICK_ITEM(Material.LEGACY_NETHER_BRICK_ITEM),
    @Deprecated
    LEGACY_QUARTZ(Material.LEGACY_QUARTZ),
    @Deprecated
    LEGACY_EXPLOSIVE_MINECART(Material.LEGACY_EXPLOSIVE_MINECART),
    @Deprecated
    LEGACY_HOPPER_MINECART(Material.LEGACY_HOPPER_MINECART),
    @Deprecated
    LEGACY_PRISMARINE_SHARD(Material.LEGACY_PRISMARINE_SHARD),
    @Deprecated
    LEGACY_PRISMARINE_CRYSTALS(Material.LEGACY_PRISMARINE_CRYSTALS),
    @Deprecated
    LEGACY_RABBIT(Material.LEGACY_RABBIT),
    @Deprecated
    LEGACY_COOKED_RABBIT(Material.LEGACY_COOKED_RABBIT),
    @Deprecated
    LEGACY_RABBIT_STEW(Material.LEGACY_RABBIT_STEW),
    @Deprecated
    LEGACY_RABBIT_FOOT(Material.LEGACY_RABBIT_FOOT),
    @Deprecated
    LEGACY_RABBIT_HIDE(Material.LEGACY_RABBIT_HIDE),
    @Deprecated
    LEGACY_ARMOR_STAND(Material.LEGACY_ARMOR_STAND),
    @Deprecated
    LEGACY_IRON_BARDING(Material.LEGACY_IRON_BARDING),
    @Deprecated
    LEGACY_GOLD_BARDING(Material.LEGACY_GOLD_BARDING),
    @Deprecated
    LEGACY_DIAMOND_BARDING(Material.LEGACY_DIAMOND_BARDING),
    @Deprecated
    LEGACY_LEASH(Material.LEGACY_LEASH),
    @Deprecated
    LEGACY_NAME_TAG(Material.LEGACY_NAME_TAG),
    @Deprecated
    LEGACY_COMMAND_MINECART(Material.LEGACY_COMMAND_MINECART),
    @Deprecated
    LEGACY_MUTTON(Material.LEGACY_MUTTON),
    @Deprecated
    LEGACY_COOKED_MUTTON(Material.LEGACY_COOKED_MUTTON),
    @Deprecated
    LEGACY_BANNER(Material.LEGACY_BANNER),
    @Deprecated
    LEGACY_END_CRYSTAL(Material.LEGACY_END_CRYSTAL),
    @Deprecated
    LEGACY_SPRUCE_DOOR_ITEM(Material.LEGACY_SPRUCE_DOOR_ITEM),
    @Deprecated
    LEGACY_BIRCH_DOOR_ITEM(Material.LEGACY_BIRCH_DOOR_ITEM),
    @Deprecated
    LEGACY_JUNGLE_DOOR_ITEM(Material.LEGACY_JUNGLE_DOOR_ITEM),
    @Deprecated
    LEGACY_ACACIA_DOOR_ITEM(Material.LEGACY_ACACIA_DOOR_ITEM),
    @Deprecated
    LEGACY_DARK_OAK_DOOR_ITEM(Material.LEGACY_DARK_OAK_DOOR_ITEM),
    @Deprecated
    LEGACY_CHORUS_FRUIT(Material.LEGACY_CHORUS_FRUIT),
    @Deprecated
    LEGACY_CHORUS_FRUIT_POPPED(Material.LEGACY_CHORUS_FRUIT_POPPED),
    @Deprecated
    LEGACY_BEETROOT(Material.LEGACY_BEETROOT),
    @Deprecated
    LEGACY_BEETROOT_SEEDS(Material.LEGACY_BEETROOT_SEEDS),
    @Deprecated
    LEGACY_BEETROOT_SOUP(Material.LEGACY_BEETROOT_SOUP),
    @Deprecated
    LEGACY_DRAGONS_BREATH(Material.LEGACY_DRAGONS_BREATH),
    @Deprecated
    LEGACY_SPLASH_POTION(Material.LEGACY_SPLASH_POTION),
    @Deprecated
    LEGACY_SPECTRAL_ARROW(Material.LEGACY_SPECTRAL_ARROW),
    @Deprecated
    LEGACY_TIPPED_ARROW(Material.LEGACY_TIPPED_ARROW),
    @Deprecated
    LEGACY_LINGERING_POTION(Material.LEGACY_LINGERING_POTION),
    @Deprecated
    LEGACY_SHIELD(Material.LEGACY_SHIELD),
    @Deprecated
    LEGACY_ELYTRA(Material.LEGACY_ELYTRA),
    @Deprecated
    LEGACY_BOAT_SPRUCE(Material.LEGACY_BOAT_SPRUCE),
    @Deprecated
    LEGACY_BOAT_BIRCH(Material.LEGACY_BOAT_BIRCH),
    @Deprecated
    LEGACY_BOAT_JUNGLE(Material.LEGACY_BOAT_JUNGLE),
    @Deprecated
    LEGACY_BOAT_ACACIA(Material.LEGACY_BOAT_ACACIA),
    @Deprecated
    LEGACY_BOAT_DARK_OAK(Material.LEGACY_BOAT_DARK_OAK),
    @Deprecated
    LEGACY_TOTEM(Material.LEGACY_TOTEM),
    @Deprecated
    LEGACY_SHULKER_SHELL(Material.LEGACY_SHULKER_SHELL),
    @Deprecated
    LEGACY_IRON_NUGGET(Material.LEGACY_IRON_NUGGET),
    @Deprecated
    LEGACY_KNOWLEDGE_BOOK(Material.LEGACY_KNOWLEDGE_BOOK),
    @Deprecated
    LEGACY_GOLD_RECORD(Material.LEGACY_GOLD_RECORD),
    @Deprecated
    LEGACY_GREEN_RECORD(Material.LEGACY_GREEN_RECORD),
    @Deprecated
    LEGACY_RECORD_3(Material.LEGACY_RECORD_3),
    @Deprecated
    LEGACY_RECORD_4(Material.LEGACY_RECORD_4),
    @Deprecated
    LEGACY_RECORD_5(Material.LEGACY_RECORD_5),
    @Deprecated
    LEGACY_RECORD_6(Material.LEGACY_RECORD_6),
    @Deprecated
    LEGACY_RECORD_7(Material.LEGACY_RECORD_7),
    @Deprecated
    LEGACY_RECORD_8(Material.LEGACY_RECORD_8),
    @Deprecated
    LEGACY_RECORD_9(Material.LEGACY_RECORD_9),
    @Deprecated
    LEGACY_RECORD_10(Material.LEGACY_RECORD_10),
    @Deprecated
    LEGACY_RECORD_11(Material.LEGACY_RECORD_11),
    @Deprecated
    LEGACY_RECORD_12(Material.LEGACY_RECORD_12);

	private final Material material;
	
	private MaterialWrapper(Material material) {
		this.material = material;
	}
	
	@Override
	public Material getMaterial() {
		return material;
	}
	
	static Iterator<Material> getMaterials() {
		return Arrays.asList(values()).stream()
				.map(MaterialWrapper -> MaterialWrapper.getMaterial())
				.collect(Collectors.toList()).iterator();
	}

}
