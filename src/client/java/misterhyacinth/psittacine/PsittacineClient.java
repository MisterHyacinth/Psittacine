package misterhyacinth.psittacine;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;

public class PsittacineClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		makeCutout(Psittacine.BLACK_PARROT_FEATHER_CARPET);
		makeCutout(Psittacine.GRAY_PARROT_FEATHER_CARPET);
		makeCutout(Psittacine.WHITE_PARROT_FEATHER_CARPET);
		makeCutout(Psittacine.PINK_PARROT_FEATHER_CARPET);
		makeCutout(Psittacine.RED_PARROT_FEATHER_CARPET);
		makeCutout(Psittacine.ORANGE_PARROT_FEATHER_CARPET);
		makeCutout(Psittacine.YELLOW_PARROT_FEATHER_CARPET);
		makeCutout(Psittacine.GREEN_PARROT_FEATHER_CARPET);
		makeCutout(Psittacine.BLUE_PARROT_FEATHER_CARPET);
		makeCutout(Psittacine.VIOLET_PARROT_FEATHER_CARPET);

		makeCutout(Psittacine.POTTED_FEATHER);

		makeCutout(Psittacine.POTTED_BLACK_PARROT_FEATHER);
		makeCutout(Psittacine.POTTED_GRAY_PARROT_FEATHER);
		makeCutout(Psittacine.POTTED_WHITE_PARROT_FEATHER);
		makeCutout(Psittacine.POTTED_PINK_PARROT_FEATHER);
		makeCutout(Psittacine.POTTED_RED_PARROT_FEATHER);
		makeCutout(Psittacine.POTTED_ORANGE_PARROT_FEATHER);
		makeCutout(Psittacine.POTTED_YELLOW_PARROT_FEATHER);
		makeCutout(Psittacine.POTTED_GREEN_PARROT_FEATHER);
		makeCutout(Psittacine.POTTED_BLUE_PARROT_FEATHER);
		makeCutout(Psittacine.POTTED_VIOLET_PARROT_FEATHER);
	}

	private static void makeCutout(Block block) {
		BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getCutout());
	}

}