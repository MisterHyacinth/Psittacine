package misterhyacinth.psittacine;

import misterhyacinth.psittacine.block.ParrotEggBlock;
import misterhyacinth.psittacine.block.ParrotFeatherBlock;
import misterhyacinth.psittacine.block.PottedFeatherBlock;
import misterhyacinth.psittacine.item.CagedParrotItem;
import misterhyacinth.psittacine.item.ParrotEggItem;
import misterhyacinth.psittacine.util.PsittacineTags;
import misterhyacinth.psittacine.util.PsittacineUtils;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.CarpetBlock;
import net.minecraft.block.MapColor;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;





public class Psittacine implements ModInitializer {



	public static final String MOD_ID = "psittacine";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);




	//CAGES ============================================================================================================
	public static final Item BIRD_CAGE =
			registerItem("bird_cage", new Item(new Item.Settings()));
	public static final Item CAGED_PARROT =
			registerItem("caged_parrot", new CagedParrotItem(new Item.Settings()));

	//SEED CAKES =======================================================================================================

	public static final FoodComponent SEED_CAKE_FOOD =
			(new FoodComponent.Builder().nutrition(1).saturationModifier(0.3F).snack().build());
	public static final FoodComponent TOASTED_SEED_CAKE_FOOD =
			(new FoodComponent.Builder().nutrition(2).saturationModifier(0.6F).snack().build());

	public static final Item SEED_CAKE =
			registerItem("seed_cake", new Item(new Item.Settings().food(SEED_CAKE_FOOD)));
	public static final Item TOASTED_SEED_CAKE =
			registerItem("toasted_seed_cake", new Item(new Item.Settings().food(TOASTED_SEED_CAKE_FOOD)));

	//FEATHERS =========================================================================================================
	public static final Item BLACK_PARROT_FEATHER =
			registerItem("black_parrot_feather", new Item(new Item.Settings()));
	public static final Item GRAY_PARROT_FEATHER =
			registerItem("gray_parrot_feather", new Item(new Item.Settings()));
	public static final Item WHITE_PARROT_FEATHER =
			registerItem("white_parrot_feather", new Item(new Item.Settings()));
	public static final Item PINK_PARROT_FEATHER =
			registerItem("pink_parrot_feather", new Item(new Item.Settings()));
	public static final Item RED_PARROT_FEATHER =
			registerItem("red_parrot_feather", new Item(new Item.Settings()));
	public static final Item ORANGE_PARROT_FEATHER =
			registerItem("orange_parrot_feather", new Item(new Item.Settings()));
	public static final Item YELLOW_PARROT_FEATHER =
			registerItem("yellow_parrot_feather", new Item(new Item.Settings()));
	public static final Item GREEN_PARROT_FEATHER =
			registerItem("green_parrot_feather", new Item(new Item.Settings()));
	public static final Item BLUE_PARROT_FEATHER =
			registerItem("blue_parrot_feather", new Item(new Item.Settings()));
	public static final Item VIOLET_PARROT_FEATHER =
			registerItem("violet_parrot_feather", new Item(new Item.Settings()));

	//POTTED FEATHERS ==================================================================================================

	public static final Block POTTED_FEATHER = registerPottedFeather("potted_feather", Items.FEATHER);
	public static final Block POTTED_BLACK_PARROT_FEATHER =
			registerPottedFeather("potted_black_parrot_feather", BLACK_PARROT_FEATHER);
	public static final Block POTTED_GRAY_PARROT_FEATHER =
			registerPottedFeather("potted_gray_parrot_feather", GRAY_PARROT_FEATHER);
	public static final Block POTTED_WHITE_PARROT_FEATHER =
			registerPottedFeather("potted_white_parrot_feather", WHITE_PARROT_FEATHER);
	public static final Block POTTED_PINK_PARROT_FEATHER =
			registerPottedFeather("potted_pink_parrot_feather", PINK_PARROT_FEATHER);
	public static final Block POTTED_RED_PARROT_FEATHER =
			registerPottedFeather("potted_red_parrot_feather", RED_PARROT_FEATHER);
	public static final Block POTTED_ORANGE_PARROT_FEATHER =
			registerPottedFeather("potted_orange_parrot_feather", ORANGE_PARROT_FEATHER);
	public static final Block POTTED_YELLOW_PARROT_FEATHER =
			registerPottedFeather("potted_yellow_parrot_feather", YELLOW_PARROT_FEATHER);
	public static final Block POTTED_GREEN_PARROT_FEATHER =
			registerPottedFeather("potted_green_parrot_feather", GREEN_PARROT_FEATHER);
	public static final Block POTTED_BLUE_PARROT_FEATHER =
			registerPottedFeather("potted_blue_parrot_feather", BLUE_PARROT_FEATHER);
	public static final Block POTTED_VIOLET_PARROT_FEATHER =
			registerPottedFeather("potted_violet_parrot_feather", VIOLET_PARROT_FEATHER);

	//FEATHER BLOCKS ===================================================================================================

	public static final Block BLACK_PARROT_FEATHER_BLOCK =
			registerFeatherBlock("black_parrot_feather_block", MapColor.BLACK);
	public static final Block GRAY_PARROT_FEATHER_BLOCK =
			registerFeatherBlock("gray_parrot_feather_block", MapColor.GRAY);
	public static final Block WHITE_PARROT_FEATHER_BLOCK =
			registerFeatherBlock("white_parrot_feather_block", MapColor.WHITE);
	public static final Block PINK_PARROT_FEATHER_BLOCK =
			registerFeatherBlock("pink_parrot_feather_block", MapColor.PINK);
	public static final Block RED_PARROT_FEATHER_BLOCK =
			registerFeatherBlock("red_parrot_feather_block", MapColor.RED);
	public static final Block ORANGE_PARROT_FEATHER_BLOCK =
			registerFeatherBlock("orange_parrot_feather_block", MapColor.ORANGE);
	public static final Block YELLOW_PARROT_FEATHER_BLOCK =
			registerFeatherBlock("yellow_parrot_feather_block", MapColor.YELLOW);
	public static final Block GREEN_PARROT_FEATHER_BLOCK =
			registerFeatherBlock("green_parrot_feather_block", MapColor.GREEN);
	public static final Block BLUE_PARROT_FEATHER_BLOCK =
			registerFeatherBlock("blue_parrot_feather_block", MapColor.BLUE);
	public static final Block VIOLET_PARROT_FEATHER_BLOCK =
			registerFeatherBlock("violet_parrot_feather_block", MapColor.PURPLE);

	//FEATHER CARPETS ==================================================================================================

	public static final Block BLACK_PARROT_FEATHER_CARPET =
			registerFeatherCarpet("black_parrot_feather_carpet", MapColor.BLACK);
	public static final Block GRAY_PARROT_FEATHER_CARPET =
			registerFeatherCarpet("gray_parrot_feather_carpet", MapColor.GRAY);
	public static final Block WHITE_PARROT_FEATHER_CARPET =
			registerFeatherCarpet("white_parrot_feather_carpet", MapColor.WHITE);
	public static final Block PINK_PARROT_FEATHER_CARPET =
			registerFeatherCarpet("pink_parrot_feather_carpet", MapColor.PINK);
	public static final Block RED_PARROT_FEATHER_CARPET =
			registerFeatherCarpet("red_parrot_feather_carpet", MapColor.RED);
	public static final Block ORANGE_PARROT_FEATHER_CARPET =
			registerFeatherCarpet("orange_parrot_feather_carpet", MapColor.ORANGE);
	public static final Block YELLOW_PARROT_FEATHER_CARPET =
			registerFeatherCarpet("yellow_parrot_feather_carpet", MapColor.YELLOW);
	public static final Block GREEN_PARROT_FEATHER_CARPET =
			registerFeatherCarpet("green_parrot_feather_carpet", MapColor.GREEN);
	public static final Block BLUE_PARROT_FEATHER_CARPET =
			registerFeatherCarpet("blue_parrot_feather_carpet", MapColor.BLUE);
	public static final Block VIOLET_PARROT_FEATHER_CARPET =
			registerFeatherCarpet("violet_parrot_feather_carpet", MapColor.PURPLE);

	//EGG ==============================================================================================================

	public static final Block PARROT_EGG =
			registerBlockNoItem("parrot_egg", new ParrotEggBlock(AbstractBlock.Settings.create()
					.mapColor(MapColor.PALE_PURPLE).strength(0.5F).sounds(BlockSoundGroup.METAL)
					.nonOpaque().ticksRandomly()));

	public static final Item PARROT_EGG_ITEM =
			registerItem("parrot_egg", new ParrotEggItem(PARROT_EGG, new Item.Settings()));

	//SOUNDS ===========================================================================================================

	public static final Identifier EGG_PLOP_SOUND_ID = Identifier.of(MOD_ID, "parrot_egg_plop");
	public static SoundEvent EGG_PLOP_SOUND = SoundEvent.of(EGG_PLOP_SOUND_ID);

	public static final Identifier EGG_CRACK_SOUND_ID = Identifier.of(MOD_ID, "parrot_egg_crack");
	public static SoundEvent EGG_CRACK_SOUND = SoundEvent.of(EGG_CRACK_SOUND_ID);

	public static final Identifier EGG_BREAK_SOUND_ID = Identifier.of(MOD_ID, "parrot_egg_break");
	public static SoundEvent EGG_BREAK_SOUND = SoundEvent.of(EGG_BREAK_SOUND_ID);

	public static final Identifier EGG_HATCH_SOUND_ID = Identifier.of(MOD_ID, "parrot_egg_hatch");
	public static SoundEvent EGG_HATCH_SOUND = SoundEvent.of(EGG_HATCH_SOUND_ID);






	@Override
	public void onInitialize() {

		PsittacineUtils.registerCompostables();
		PsittacineUtils.registerFlammables();
		PsittacineUtils.registerFuels();

		BiomeModifications.addSpawn(BiomeSelectors.tag(PsittacineTags.SPAWNS_PARROTS), SpawnGroup.CREATURE,
				EntityType.PARROT, 50, 3, 6);

		Registry.register(Registries.SOUND_EVENT, EGG_PLOP_SOUND_ID, EGG_PLOP_SOUND);
		Registry.register(Registries.SOUND_EVENT, EGG_CRACK_SOUND_ID, EGG_CRACK_SOUND);
		Registry.register(Registries.SOUND_EVENT, EGG_BREAK_SOUND_ID, EGG_BREAK_SOUND);
		Registry.register(Registries.SOUND_EVENT, EGG_HATCH_SOUND_ID, EGG_HATCH_SOUND);

		ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(content -> {
			content.addAfter(Items.MILK_BUCKET, BIRD_CAGE, CAGED_PARROT);
		});

		ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(content -> {
			content.addAfter(Items.DRIED_KELP, SEED_CAKE, TOASTED_SEED_CAKE);
		});

		ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(content -> {
			content.addAfter(Items.SNIFFER_EGG, PARROT_EGG_ITEM);
			content.addAfter(Items.PEARLESCENT_FROGLIGHT, BLACK_PARROT_FEATHER_BLOCK, GRAY_PARROT_FEATHER_BLOCK,
					WHITE_PARROT_FEATHER_BLOCK, PINK_PARROT_FEATHER_BLOCK, RED_PARROT_FEATHER_BLOCK,
					ORANGE_PARROT_FEATHER_BLOCK, YELLOW_PARROT_FEATHER_BLOCK, GREEN_PARROT_FEATHER_BLOCK,
					BLUE_PARROT_FEATHER_BLOCK, VIOLET_PARROT_FEATHER_BLOCK);
			content.addAfter(VIOLET_PARROT_FEATHER_BLOCK, BLACK_PARROT_FEATHER_CARPET, GRAY_PARROT_FEATHER_CARPET,
					WHITE_PARROT_FEATHER_CARPET, PINK_PARROT_FEATHER_CARPET, RED_PARROT_FEATHER_CARPET,
					ORANGE_PARROT_FEATHER_CARPET, YELLOW_PARROT_FEATHER_CARPET, GREEN_PARROT_FEATHER_CARPET,
					BLUE_PARROT_FEATHER_CARPET, VIOLET_PARROT_FEATHER_CARPET);
		});

		ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(content -> {
			content.addAfter(Items.FEATHER, BLACK_PARROT_FEATHER, GRAY_PARROT_FEATHER, WHITE_PARROT_FEATHER,
					PINK_PARROT_FEATHER, RED_PARROT_FEATHER, ORANGE_PARROT_FEATHER, YELLOW_PARROT_FEATHER,
					GREEN_PARROT_FEATHER, BLUE_PARROT_FEATHER, VIOLET_PARROT_FEATHER);
		});

		PsittacineTags.register();

	}

	public static Block registerPottedFeather(String name, Item content) {
		return registerBlockNoItem(name,
				new PottedFeatherBlock(content,
						AbstractBlock.Settings.create()
								.breakInstantly().nonOpaque().pistonBehavior(PistonBehavior.DESTROY)));
	}



	public static Block registerFeatherCarpet(String name, MapColor mapColor) {
		return registerBlock(name,
				new CarpetBlock(AbstractBlock.Settings.create().mapColor(mapColor)
						.strength(0.1F).sounds(BlockSoundGroup.POWDER_SNOW).burnable()));
	}

	public static Block registerFeatherBlock(String name, MapColor mapColor) {
		return registerBlock(name,
				new ParrotFeatherBlock(AbstractBlock.Settings.create().mapColor(mapColor)
						.instrument(NoteBlockInstrument.DIDGERIDOO).strength(0.5F)
						.sounds(BlockSoundGroup.POWDER_SNOW).burnable()));
	}



	public static Block registerBlockNoItem(String name, Block block){
		return Registry.register(Registries.BLOCK, Identifier.of(MOD_ID, name), block);
	}
	public static Block registerBlock(String name, Block block){
		Item blockItem = registerBlockItem(name, block);
		return Registry.register(Registries.BLOCK, Identifier.of(MOD_ID, name), block);
	}
	private static Item registerBlockItem(String name, Block block) {
		return Registry.register(Registries.ITEM, Identifier.of(MOD_ID, name),
				new BlockItem(block, new Item.Settings()));
	}

	private static Item registerItem(String name, Item item) {
		return Registry.register(Registries.ITEM, Identifier.of(MOD_ID, name), item);
	}


}