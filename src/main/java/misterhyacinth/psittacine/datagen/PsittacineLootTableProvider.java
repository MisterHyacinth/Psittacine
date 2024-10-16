package misterhyacinth.psittacine.datagen;

import misterhyacinth.psittacine.Psittacine;
import misterhyacinth.psittacine.block.PottedFeatherBlock;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class PsittacineLootTableProvider extends FabricBlockLootTableProvider {
    public PsittacineLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {

        addDrop(Psittacine.BLACK_PARROT_FEATHER_BLOCK);
        addDrop(Psittacine.GRAY_PARROT_FEATHER_BLOCK);
        addDrop(Psittacine.WHITE_PARROT_FEATHER_BLOCK);
        addDrop(Psittacine.PINK_PARROT_FEATHER_BLOCK);
        addDrop(Psittacine.RED_PARROT_FEATHER_BLOCK);
        addDrop(Psittacine.ORANGE_PARROT_FEATHER_BLOCK);
        addDrop(Psittacine.YELLOW_PARROT_FEATHER_BLOCK);
        addDrop(Psittacine.GREEN_PARROT_FEATHER_BLOCK);
        addDrop(Psittacine.BLUE_PARROT_FEATHER_BLOCK);
        addDrop(Psittacine.VIOLET_PARROT_FEATHER_BLOCK);

        addDrop(Psittacine.BLACK_PARROT_FEATHER_CARPET);
        addDrop(Psittacine.GRAY_PARROT_FEATHER_CARPET);
        addDrop(Psittacine.WHITE_PARROT_FEATHER_CARPET);
        addDrop(Psittacine.PINK_PARROT_FEATHER_CARPET);
        addDrop(Psittacine.RED_PARROT_FEATHER_CARPET);
        addDrop(Psittacine.ORANGE_PARROT_FEATHER_CARPET);
        addDrop(Psittacine.YELLOW_PARROT_FEATHER_CARPET);
        addDrop(Psittacine.GREEN_PARROT_FEATHER_CARPET);
        addDrop(Psittacine.BLUE_PARROT_FEATHER_CARPET);
        addDrop(Psittacine.VIOLET_PARROT_FEATHER_CARPET);

        addPottedFeatherDrops(Psittacine.POTTED_FEATHER);
        addPottedFeatherDrops(Psittacine.POTTED_BLACK_PARROT_FEATHER);
        addPottedFeatherDrops(Psittacine.POTTED_GRAY_PARROT_FEATHER);
        addPottedFeatherDrops(Psittacine.POTTED_WHITE_PARROT_FEATHER);
        addPottedFeatherDrops(Psittacine.POTTED_PINK_PARROT_FEATHER);
        addPottedFeatherDrops(Psittacine.POTTED_RED_PARROT_FEATHER);
        addPottedFeatherDrops(Psittacine.POTTED_ORANGE_PARROT_FEATHER);
        addPottedFeatherDrops(Psittacine.POTTED_YELLOW_PARROT_FEATHER);
        addPottedFeatherDrops(Psittacine.POTTED_GREEN_PARROT_FEATHER);
        addPottedFeatherDrops(Psittacine.POTTED_BLUE_PARROT_FEATHER);
        addPottedFeatherDrops(Psittacine.POTTED_VIOLET_PARROT_FEATHER);

    }

    public void addPottedFeatherDrops(Block block) {
        this.addDrop(block, (flowerPot) -> {
            return this.pottedPlantDrops(((PottedFeatherBlock)flowerPot).getContent());
        });
    }
}
