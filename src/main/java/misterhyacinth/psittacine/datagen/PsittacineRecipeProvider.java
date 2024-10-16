package misterhyacinth.psittacine.datagen;

import misterhyacinth.psittacine.Psittacine;
import misterhyacinth.psittacine.util.PsittacineTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.CookingRecipeJsonBuilder;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.*;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class PsittacineRecipeProvider extends FabricRecipeProvider {
    public PsittacineRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, Psittacine.BIRD_CAGE, 4)
                .input('#', Items.IRON_BARS)
                .input('T', Items.IRON_TRAPDOOR)
                .pattern("#")
                .pattern("#")
                .pattern("T")
                .criterion("has_item", conditionsFromItem(Items.IRON_BARS))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, Items.ARROW, 4)
                .input('A', Items.FLINT)
                .input('I', Items.STICK)
                .input('#', Ingredient.fromTag(PsittacineTags.PARROT_FEATHERS))
                .pattern("A")
                .pattern("I")
                .pattern("#")
                .criterion("has_item", conditionsFromTag(PsittacineTags.PARROT_FEATHERS))
                .offerTo(exporter, "arrow_from_parrot_feathers");

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Items.FEATHER, 1).input(PsittacineTags.PARROT_FEATHERS)
                .criterion("has_item", conditionsFromTag(PsittacineTags.PARROT_FEATHERS))
                .offerTo(exporter, "feather_from_parrot_feathers");

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Psittacine.SEED_CAKE, 1)
                .input(Items.WHEAT_SEEDS)
                .input(Items.BEETROOT_SEEDS)
                .input(Items.PUMPKIN_SEEDS)
                .input(Items.MELON_SEEDS)
                .criterion("has_item", conditionsFromItem(Items.WHEAT_SEEDS))
                .criterion("has_item", conditionsFromItem(Items.BEETROOT_SEEDS))
                .criterion("has_item", conditionsFromItem(Items.PUMPKIN_SEEDS))
                .criterion("has_item", conditionsFromItem(Items.MELON_SEEDS))
                .offerTo(exporter);

        CookingRecipeJsonBuilder.create(Ingredient.ofItems(Psittacine.SEED_CAKE), RecipeCategory.FOOD,
                        Psittacine.TOASTED_SEED_CAKE, 0.1F, 200, RecipeSerializer.SMELTING,
                        SmeltingRecipe::new)
                .criterion("has_item", conditionsFromItem(Psittacine.SEED_CAKE))
                .offerTo(exporter, "toasted_seed_cake");

        CookingRecipeJsonBuilder.create(Ingredient.ofItems(Psittacine.SEED_CAKE), RecipeCategory.FOOD,
                        Psittacine.TOASTED_SEED_CAKE, 0.1F, 100, RecipeSerializer.SMOKING,
                        SmokingRecipe::new)
                .criterion("has_item", conditionsFromItem(Psittacine.SEED_CAKE))
                .offerTo(exporter, "toasted_seed_cake_from_smoking");

        CookingRecipeJsonBuilder.create(Ingredient.ofItems(Psittacine.SEED_CAKE), RecipeCategory.FOOD,
                        Psittacine.TOASTED_SEED_CAKE, 0.35F, 600, RecipeSerializer.CAMPFIRE_COOKING,
                        CampfireCookingRecipe::new)
                .criterion("has_item", conditionsFromItem(Psittacine.SEED_CAKE))
                .offerTo(exporter, "toasted_seed_cake_from_campfire_cooking");


        offerFeatherBlockRecipe(exporter, Psittacine.BLACK_PARROT_FEATHER, Psittacine.BLACK_PARROT_FEATHER_BLOCK,
                Psittacine.BLACK_PARROT_FEATHER_CARPET);
        offerFeatherBlockRecipe(exporter, Psittacine.GRAY_PARROT_FEATHER, Psittacine.GRAY_PARROT_FEATHER_BLOCK,
                Psittacine.GRAY_PARROT_FEATHER_CARPET);
        offerFeatherBlockRecipe(exporter, Psittacine.WHITE_PARROT_FEATHER, Psittacine.WHITE_PARROT_FEATHER_BLOCK,
                Psittacine.WHITE_PARROT_FEATHER_CARPET);
        offerFeatherBlockRecipe(exporter, Psittacine.PINK_PARROT_FEATHER, Psittacine.PINK_PARROT_FEATHER_BLOCK,
                Psittacine.PINK_PARROT_FEATHER_CARPET);
        offerFeatherBlockRecipe(exporter, Psittacine.RED_PARROT_FEATHER, Psittacine.RED_PARROT_FEATHER_BLOCK,
                Psittacine.RED_PARROT_FEATHER_CARPET);
        offerFeatherBlockRecipe(exporter, Psittacine.ORANGE_PARROT_FEATHER, Psittacine.ORANGE_PARROT_FEATHER_BLOCK,
                Psittacine.ORANGE_PARROT_FEATHER_CARPET);
        offerFeatherBlockRecipe(exporter, Psittacine.YELLOW_PARROT_FEATHER, Psittacine.YELLOW_PARROT_FEATHER_BLOCK,
                Psittacine.YELLOW_PARROT_FEATHER_CARPET);
        offerFeatherBlockRecipe(exporter, Psittacine.GREEN_PARROT_FEATHER, Psittacine.GREEN_PARROT_FEATHER_BLOCK,
                Psittacine.GREEN_PARROT_FEATHER_CARPET);
        offerFeatherBlockRecipe(exporter, Psittacine.BLUE_PARROT_FEATHER, Psittacine.BLUE_PARROT_FEATHER_BLOCK,
                Psittacine.BLUE_PARROT_FEATHER_CARPET);
        offerFeatherBlockRecipe(exporter, Psittacine.VIOLET_PARROT_FEATHER, Psittacine.VIOLET_PARROT_FEATHER_BLOCK,
                Psittacine.VIOLET_PARROT_FEATHER_CARPET);

    }

    public static void offerFeatherBlockRecipe(RecipeExporter exporter, ItemConvertible feather,
                                               ItemConvertible block, ItemConvertible carpet) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, block, 1)
                .input('#', feather)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .criterion("has_item", conditionsFromItem(feather))
                .offerTo(exporter);
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, feather, 9)
                .input(block)
                .criterion("has_item", conditionsFromItem(block))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, carpet, 1)
                .input('#', feather)
                .pattern("###")
                .criterion("has_item", conditionsFromItem(feather))
                .offerTo(exporter);
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, feather, 3)
                .input(carpet)
                .criterion("has_item", conditionsFromItem(carpet))
                .offerTo(exporter, getItemPath(feather) + "_from_" + getItemPath(carpet));

    }


}
