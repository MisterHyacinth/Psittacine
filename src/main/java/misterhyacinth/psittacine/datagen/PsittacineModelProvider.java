package misterhyacinth.psittacine.datagen;

import misterhyacinth.psittacine.Psittacine;
import misterhyacinth.psittacine.block.ParrotEggBlock;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.data.client.*;
import net.minecraft.util.Identifier;

import java.util.Optional;
import java.util.function.Function;

import static net.minecraft.data.client.BlockStateModelGenerator.createNorthDefaultHorizontalRotationStates;

public class PsittacineModelProvider extends FabricModelProvider {
    public PsittacineModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator generator) {

        registerParrotEgg(generator);

        registerFeatherBlock(Psittacine.BLACK_PARROT_FEATHER_BLOCK, Psittacine.BLACK_PARROT_FEATHER_CARPET, generator);
        registerFeatherBlock(Psittacine.GRAY_PARROT_FEATHER_BLOCK, Psittacine.GRAY_PARROT_FEATHER_CARPET, generator);
        registerFeatherBlock(Psittacine.WHITE_PARROT_FEATHER_BLOCK, Psittacine.WHITE_PARROT_FEATHER_CARPET, generator);
        registerFeatherBlock(Psittacine.PINK_PARROT_FEATHER_BLOCK, Psittacine.PINK_PARROT_FEATHER_CARPET, generator);
        registerFeatherBlock(Psittacine.RED_PARROT_FEATHER_BLOCK, Psittacine.RED_PARROT_FEATHER_CARPET, generator);
        registerFeatherBlock(Psittacine.ORANGE_PARROT_FEATHER_BLOCK, Psittacine.ORANGE_PARROT_FEATHER_CARPET, generator);
        registerFeatherBlock(Psittacine.YELLOW_PARROT_FEATHER_BLOCK, Psittacine.YELLOW_PARROT_FEATHER_CARPET, generator);
        registerFeatherBlock(Psittacine.GREEN_PARROT_FEATHER_BLOCK, Psittacine.GREEN_PARROT_FEATHER_CARPET, generator);
        registerFeatherBlock(Psittacine.BLUE_PARROT_FEATHER_BLOCK, Psittacine.BLUE_PARROT_FEATHER_CARPET, generator);
        registerFeatherBlock(Psittacine.VIOLET_PARROT_FEATHER_BLOCK, Psittacine.VIOLET_PARROT_FEATHER_CARPET, generator);

        registerHorizontalRotatedState(Psittacine.POTTED_FEATHER, generator);
        registerPottedFeather(Psittacine.POTTED_BLACK_PARROT_FEATHER, generator);
        registerPottedFeather(Psittacine.POTTED_GRAY_PARROT_FEATHER, generator);
        registerPottedFeather(Psittacine.POTTED_WHITE_PARROT_FEATHER, generator);
        registerPottedFeather(Psittacine.POTTED_PINK_PARROT_FEATHER, generator);
        registerPottedFeather(Psittacine.POTTED_RED_PARROT_FEATHER, generator);
        registerPottedFeather(Psittacine.POTTED_ORANGE_PARROT_FEATHER, generator);
        registerPottedFeather(Psittacine.POTTED_YELLOW_PARROT_FEATHER, generator);
        registerPottedFeather(Psittacine.POTTED_GREEN_PARROT_FEATHER, generator);
        registerPottedFeather(Psittacine.POTTED_BLUE_PARROT_FEATHER, generator);
        registerPottedFeather(Psittacine.POTTED_VIOLET_PARROT_FEATHER, generator);

    }

    //ITEM MODELS ======================================================================================================

    @Override
    public void generateItemModels(ItemModelGenerator generator) {

        generator.register(Psittacine.BIRD_CAGE, Models.GENERATED);
        generator.register(Psittacine.CAGED_PARROT, Models.GENERATED);

        generator.register(Psittacine.SEED_CAKE, Models.GENERATED);
        generator.register(Psittacine.TOASTED_SEED_CAKE, Models.GENERATED);

        generator.register(Psittacine.BLACK_PARROT_FEATHER, Models.GENERATED);
        generator.register(Psittacine.GRAY_PARROT_FEATHER, Models.GENERATED);
        generator.register(Psittacine.WHITE_PARROT_FEATHER, Models.GENERATED);
        generator.register(Psittacine.PINK_PARROT_FEATHER, Models.GENERATED);
        generator.register(Psittacine.RED_PARROT_FEATHER, Models.GENERATED);
        generator.register(Psittacine.ORANGE_PARROT_FEATHER, Models.GENERATED);
        generator.register(Psittacine.YELLOW_PARROT_FEATHER, Models.GENERATED);
        generator.register(Psittacine.GREEN_PARROT_FEATHER, Models.GENERATED);
        generator.register(Psittacine.BLUE_PARROT_FEATHER, Models.GENERATED);
        generator.register(Psittacine.VIOLET_PARROT_FEATHER, Models.GENERATED);

    }


    //MODELS ===========================================================================================================

    public static final Model POTTED_FEATHER = block("potted_feather", TextureKey.TEXTURE);
    public static final Model PARROT_EGG = block("parrot_egg", TextureKey.TEXTURE);
    public static final Model PARROT_FEATHER_CARPET = block("parrot_feather_carpet",
            TextureKey.SIDE, TextureKey.TOP, TextureKey.BOTTOM, TextureKey.CROSS);

    private static Model block(String parent, TextureKey... requiredTextureKeys) {
        return new Model(Optional.of(Identifier.of(Psittacine.MOD_ID, "block/" + parent)),
                Optional.empty(), requiredTextureKeys);
    }

    //TEXTURE MAPS ====================================================================================================

    public static TextureMap featherCarpetMap(Block block) {
        return new TextureMap()
                .put(TextureKey.SIDE, TextureMap.getSubId(block, "_side"))
                .put(TextureKey.BOTTOM, TextureMap.getSubId(block, "_bottom"))
                .put(TextureKey.TOP, TextureMap.getSubId(block, "_top"))
                .put(TextureKey.CROSS, TextureMap.getSubId(block, "_cross"));
    }

    //METHODS ==========================================================================================================

    private void registerFeatherBlock(Block block, Block carpet, BlockStateModelGenerator generator) {
        generator.registerSingleton(block, TexturedModel.CUBE_BOTTOM_TOP);
        TextureMap carpetMap = featherCarpetMap(block);
        generator.registerSingleton(carpet, carpetMap, PARROT_FEATHER_CARPET);

    }
    private void registerParrotEgg(BlockStateModelGenerator generator) {
        generator.registerItemModel(Psittacine.PARROT_EGG.asItem());
        Function<Integer, Identifier> function = (hatch) -> {

            String string = switch (hatch) {
                case 1 -> "_slightly_cracked";
                case 2 -> "_very_cracked";
                default -> "_not_cracked";
            };
            Identifier id = ModelIds.getBlockSubModelId(Psittacine.PARROT_EGG, string);
            TextureMap textureMap = TextureMap.texture(id);
            return PARROT_EGG.upload(Psittacine.PARROT_EGG, string, textureMap, generator.modelCollector);
        };
        generator.blockStateCollector.accept(VariantsBlockStateSupplier.create(Psittacine.PARROT_EGG)
                .coordinate(BlockStateVariantMap.create(ParrotEggBlock.HATCH).register((hatch) -> {
                    return BlockStateVariant.create().put(VariantSettings.MODEL, function.apply(hatch));
                })));
    }

    public final void registerPottedFeather(Block block, BlockStateModelGenerator generator) {
        TextureMap textureMap = TextureMap.texture(block);
        POTTED_FEATHER.upload(block, textureMap, generator.modelCollector);
        registerHorizontalRotatedState(block, generator);
    }

    public final void registerHorizontalRotatedState(Block block, BlockStateModelGenerator generator) {
        Identifier identifier = ModelIds.getBlockModelId(block);
        generator.blockStateCollector.accept(
                VariantsBlockStateSupplier.create(block,
                                BlockStateVariant.create().put(VariantSettings.MODEL, identifier))
                        .coordinate(createNorthDefaultHorizontalRotationStates()));
    }

}
