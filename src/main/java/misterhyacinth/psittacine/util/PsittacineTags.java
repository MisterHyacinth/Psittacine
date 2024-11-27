package misterhyacinth.psittacine.util;

import misterhyacinth.psittacine.Psittacine;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;

public class PsittacineTags {

    private static final String MOD_ID = Psittacine.MOD_ID;

    public static final TagKey<Biome> SPAWNS_PARROTS =
            TagKey.of(RegistryKeys.BIOME, Identifier.of(MOD_ID, "spawns_parrots"));
    public static final TagKey<Biome> SPAWNS_BAMBOO_PARROTS =
            TagKey.of(RegistryKeys.BIOME, Identifier.of(MOD_ID, "spawns_bamboo_parrots"));
    public static final TagKey<Biome> SPAWNS_JUNGLE_PARROTS =
            TagKey.of(RegistryKeys.BIOME, Identifier.of(MOD_ID, "spawns_jungle_parrots"));
    public static final TagKey<Biome> SPAWNS_OUTBACK_PARROTS =
            TagKey.of(RegistryKeys.BIOME, Identifier.of(MOD_ID, "spawns_outback_parrots"));



    public static final TagKey<Block> PARROT_EGG_HATCH_BOOST =
            TagKey.of(RegistryKeys.BLOCK, Identifier.of(MOD_ID, "parrot_egg_hatch_boost"));
    public static final TagKey<Block> PARROT_EGG_HATCH_BLOCKS =
            TagKey.of(RegistryKeys.BLOCK, Identifier.of(MOD_ID, "parrot_egg_hatch_blocks"));
    public static final TagKey<Block> PARROT_FEATHER_BLOCKS =
            TagKey.of(RegistryKeys.BLOCK, Identifier.of(MOD_ID, "parrot_feather_blocks"));
    public static final TagKey<Item> PARROT_FEATHERS =
            TagKey.of(RegistryKeys.ITEM, Identifier.of(MOD_ID, "parrot_feathers"));
    public static final TagKey<Enchantment> DROPS_EGGS =
            TagKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(MOD_ID, "drops_eggs"));


    public static final TagKey<Block> SHEARS_ALWAYS_DROP =
            TagKey.of(RegistryKeys.BLOCK, Identifier.of("c", "shears_always_drop"));
    public static final TagKey<Block> SHEARS_SPEED_15 =
            TagKey.of(RegistryKeys.BLOCK, Identifier.of("c", "shears_speed_15"));
    public static final TagKey<Block> SHEARS_SPEED_5 =
            TagKey.of(RegistryKeys.BLOCK, Identifier.of("c", "shears_speed_5"));
    public static final TagKey<Block> SHEARS_SPEED_2 =
            TagKey.of(RegistryKeys.BLOCK, Identifier.of("c", "shears_speed_2"));
    public static final TagKey<Block> SHEARS_POST_MINE =
            TagKey.of(RegistryKeys.BLOCK, Identifier.of("c", "shears_post_mine"));

    public static void register() {}
}
