package misterhyacinth.psittacine.util;

import misterhyacinth.psittacine.Psittacine;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;

public class PsittacineUtils {

    private static final String MOD_ID = Psittacine.MOD_ID;

    public static void registerCompostables() {
        CompostingChanceRegistry registry = CompostingChanceRegistry.INSTANCE;
        registry.add(Psittacine.SEED_CAKE, 0.3F);
        registry.add(Psittacine.TOASTED_SEED_CAKE, 0.5F);
    }

    public static void registerFuels() {
        FuelRegistry registry = FuelRegistry.INSTANCE;
        registry.add(Psittacine.BLACK_PARROT_FEATHER_BLOCK, 200);
        registry.add(Psittacine.GRAY_PARROT_FEATHER_BLOCK, 200);
        registry.add(Psittacine.WHITE_PARROT_FEATHER_BLOCK, 200);
        registry.add(Psittacine.PINK_PARROT_FEATHER_BLOCK, 200);
        registry.add(Psittacine.RED_PARROT_FEATHER_BLOCK, 200);
        registry.add(Psittacine.ORANGE_PARROT_FEATHER_BLOCK, 200);
        registry.add(Psittacine.YELLOW_PARROT_FEATHER_BLOCK, 200);
        registry.add(Psittacine.GREEN_PARROT_FEATHER_BLOCK, 200);
        registry.add(Psittacine.BLUE_PARROT_FEATHER_BLOCK, 200);
        registry.add(Psittacine.VIOLET_PARROT_FEATHER_BLOCK, 200);

        registry.add(Psittacine.BLACK_PARROT_FEATHER_CARPET, 133);
        registry.add(Psittacine.GRAY_PARROT_FEATHER_CARPET, 133);
        registry.add(Psittacine.WHITE_PARROT_FEATHER_CARPET, 133);
        registry.add(Psittacine.PINK_PARROT_FEATHER_CARPET, 133);
        registry.add(Psittacine.RED_PARROT_FEATHER_CARPET, 133);
        registry.add(Psittacine.ORANGE_PARROT_FEATHER_CARPET, 133);
        registry.add(Psittacine.YELLOW_PARROT_FEATHER_CARPET, 133);
        registry.add(Psittacine.GREEN_PARROT_FEATHER_CARPET, 133);
        registry.add(Psittacine.BLUE_PARROT_FEATHER_CARPET, 133);
        registry.add(Psittacine.VIOLET_PARROT_FEATHER_CARPET, 133);
    }


    public static void registerFlammables() {
        FlammableBlockRegistry instance = FlammableBlockRegistry.getDefaultInstance();
        instance.add(Psittacine.BLACK_PARROT_FEATHER_BLOCK, 30, 60);
        instance.add(Psittacine.GRAY_PARROT_FEATHER_BLOCK, 30, 60);
        instance.add(Psittacine.WHITE_PARROT_FEATHER_BLOCK, 30, 60);
        instance.add(Psittacine.PINK_PARROT_FEATHER_BLOCK, 30, 60);
        instance.add(Psittacine.RED_PARROT_FEATHER_BLOCK, 30, 60);
        instance.add(Psittacine.ORANGE_PARROT_FEATHER_BLOCK, 30, 60);
        instance.add(Psittacine.YELLOW_PARROT_FEATHER_BLOCK, 30, 60);
        instance.add(Psittacine.GREEN_PARROT_FEATHER_BLOCK, 30, 60);
        instance.add(Psittacine.BLUE_PARROT_FEATHER_BLOCK, 30, 60);
        instance.add(Psittacine.VIOLET_PARROT_FEATHER_BLOCK, 30, 60);


        instance.add(Psittacine.BLACK_PARROT_FEATHER_CARPET, 60, 20);
        instance.add(Psittacine.GRAY_PARROT_FEATHER_CARPET, 60, 20);
        instance.add(Psittacine.WHITE_PARROT_FEATHER_CARPET, 60, 20);
        instance.add(Psittacine.PINK_PARROT_FEATHER_CARPET, 60, 20);
        instance.add(Psittacine.RED_PARROT_FEATHER_CARPET, 60, 20);
        instance.add(Psittacine.ORANGE_PARROT_FEATHER_CARPET, 60, 20);
        instance.add(Psittacine.YELLOW_PARROT_FEATHER_CARPET, 60, 20);
        instance.add(Psittacine.GREEN_PARROT_FEATHER_CARPET, 60, 20);
        instance.add(Psittacine.BLUE_PARROT_FEATHER_CARPET, 60, 20);
        instance.add(Psittacine.VIOLET_PARROT_FEATHER_CARPET, 60, 20);
    }



}
