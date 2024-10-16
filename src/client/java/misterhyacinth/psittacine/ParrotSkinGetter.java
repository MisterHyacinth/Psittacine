package misterhyacinth.psittacine;

import net.minecraft.util.Identifier;

public class ParrotSkinGetter {

    private static final Identifier RED_BLUE_TEXTURE = Identifier.of("textures/entity/parrot/parrot_red_blue.png");
    private static final Identifier BLUE_TEXTURE = Identifier.of("textures/entity/parrot/parrot_blue.png");
    private static final Identifier GREY_TEXTURE = Identifier.of("textures/entity/parrot/parrot_grey.png");
    private static final Identifier GREEN_TEXTURE = Identifier.of("textures/entity/parrot/parrot_green.png");
    private static final Identifier BLACK_TEXTURE = Identifier.of("textures/entity/parrot/parrot_black.png");
    private static final Identifier BLACK_RED_TEXTURE = Identifier.of("textures/entity/parrot/parrot_black_red.png");
    private static final Identifier BLUE_GREEN_TEXTURE = Identifier.of("textures/entity/parrot/parrot_blue_green.png");
    private static final Identifier BLUE_GREY_TEXTURE = Identifier.of("textures/entity/parrot/parrot_blue_grey.png");
    private static final Identifier BLUE_WHITE_TEXTURE = Identifier.of("textures/entity/parrot/parrot_blue_white.png");
    private static final Identifier DARK_GREY_TEXTURE = Identifier.of("textures/entity/parrot/parrot_dark_grey.png");
    private static final Identifier GREEN_BLACK_TEXTURE = Identifier.of("textures/entity/parrot/parrot_green_black.png");
    private static final Identifier GREEN_GREY_TEXTURE = Identifier.of("textures/entity/parrot/parrot_green_grey.png");
    private static final Identifier GREEN_GREY_BLACK_TEXTURE = Identifier.of("textures/entity/parrot/parrot_green_grey_black.png");
    private static final Identifier GREEN_PINK_TEXTURE = Identifier.of("textures/entity/parrot/parrot_green_pink.png");
    private static final Identifier GREEN_RED_TEXTURE = Identifier.of("textures/entity/parrot/parrot_green_red.png");
    private static final Identifier GREEN_RED_BLACK_TEXTURE = Identifier.of("textures/entity/parrot/parrot_green_red_black.png");
    private static final Identifier GREEN_RED_BLUE_TEXTURE = Identifier.of("textures/entity/parrot/parrot_green_red_blue.png");
    private static final Identifier GREEN_YELLOW_TEXTURE = Identifier.of("textures/entity/parrot/parrot_green_yellow.png");
    private static final Identifier GREY_RED_TEXTURE = Identifier.of("textures/entity/parrot/parrot_grey_red.png");
    private static final Identifier GREY_WHITE_TEXTURE = Identifier.of("textures/entity/parrot/parrot_grey_white.png");
    private static final Identifier GREY_YELLOW_TEXTURE = Identifier.of("textures/entity/parrot/parrot_grey_yellow.png");
    private static final Identifier LIGHT_BLUE_TEXTURE = Identifier.of("textures/entity/parrot/parrot_light_blue.png");
    private static final Identifier LIME_TEXTURE = Identifier.of("textures/entity/parrot/parrot_lime.png");
    private static final Identifier ORANGE_TEXTURE = Identifier.of("textures/entity/parrot/parrot_orange.png");
    private static final Identifier ORANGE_BLACK_TEXTURE = Identifier.of("textures/entity/parrot/parrot_orange_black.png");
    private static final Identifier ORANGE_GREEN_TEXTURE = Identifier.of("textures/entity/parrot/parrot_orange_green.png");
    private static final Identifier PINK_TEXTURE = Identifier.of("textures/entity/parrot/parrot_pink.png");
    private static final Identifier PINK_GREY_TEXTURE = Identifier.of("textures/entity/parrot/parrot_pink_grey.png");
    private static final Identifier RAINBOW_TEXTURE = Identifier.of("textures/entity/parrot/parrot_rainbow.png");
    private static final Identifier RED_GREEN_TEXTURE = Identifier.of("textures/entity/parrot/parrot_red_green.png");
    private static final Identifier RED_VIOLET_TEXTURE = Identifier.of("textures/entity/parrot/parrot_red_violet.png");
    private static final Identifier WHITE_TEXTURE = Identifier.of("textures/entity/parrot/parrot_white.png");
    private static final Identifier WHITE_BLUE_TEXTURE = Identifier.of("textures/entity/parrot/parrot_white_blue.png");
    private static final Identifier WHITE_PINK_TEXTURE = Identifier.of("textures/entity/parrot/parrot_white_pink.png");
    private static final Identifier WHITE_YELLOW_TEXTURE = Identifier.of("textures/entity/parrot/parrot_white_yellow.png");
    private static final Identifier YELLOW_TEXTURE = Identifier.of("textures/entity/parrot/parrot_yellow.png");
    private static final Identifier YELLOW_BLUE_TEXTURE = Identifier.of("textures/entity/parrot/parrot_yellow_blue.png");
    private static final Identifier YELLOW_GREEN_TEXTURE = Identifier.of("textures/entity/parrot/parrot_yellow_green.png");
    private static final Identifier YELLOW_GREEN_BLUE_TEXTURE = Identifier.of("textures/entity/parrot/parrot_yellow_green_blue.png");
    private static final Identifier YELLOW_GREY_TEXTURE = Identifier.of("textures/entity/parrot/parrot_yellow_grey.png");

    public static Identifier getParrotSkin(int variant) {

        return switch (variant) {

            //JUNGLE SKINS
            default -> RED_BLUE_TEXTURE;
            case 1 -> BLUE_TEXTURE;
            case 2 -> GREEN_TEXTURE;
            case 3 -> YELLOW_BLUE_TEXTURE;
            case 4 -> GREY_RED_TEXTURE;
            case 5 -> BLACK_TEXTURE;
            case 6 -> GREEN_GREY_TEXTURE;
            case 7 -> YELLOW_GREEN_BLUE_TEXTURE;
            case 8 -> YELLOW_GREEN_TEXTURE;
            case 9 -> GREEN_GREY_BLACK_TEXTURE;
            case 10 -> GREEN_RED_BLACK_TEXTURE;
            case 11 -> GREEN_RED_BLUE_TEXTURE;
            case 12 -> ORANGE_BLACK_TEXTURE;
            case 13 -> ORANGE_GREEN_TEXTURE;
            case 14 -> GREEN_BLACK_TEXTURE;
            case 15 -> GREEN_PINK_TEXTURE;
            case 16 -> RED_GREEN_TEXTURE;
            case 17 -> BLUE_GREY_TEXTURE;
            case 18 -> BLUE_GREEN_TEXTURE;
            case 19 -> ORANGE_TEXTURE;
            case 20 -> YELLOW_TEXTURE;

            //BADLANDS SKINS
            case 21 -> GREY_TEXTURE;
            case 22 -> YELLOW_GREY_TEXTURE;
            case 23 -> GREY_WHITE_TEXTURE;
            case 24 -> DARK_GREY_TEXTURE;
            case 25 -> WHITE_YELLOW_TEXTURE;
            case 26 -> PINK_GREY_TEXTURE;
            case 27 -> PINK_TEXTURE;
            case 28 -> BLACK_RED_TEXTURE;
            case 29 -> WHITE_BLUE_TEXTURE;
            case 30 -> GREY_YELLOW_TEXTURE;
            case 31 -> GREEN_YELLOW_TEXTURE;
            case 32 -> BLUE_WHITE_TEXTURE;

            //BAMBOO JUNGLE SKINS
            case 33 -> LIGHT_BLUE_TEXTURE;
            case 34 -> LIME_TEXTURE;
            case 35 -> WHITE_PINK_TEXTURE;
            case 36 -> WHITE_TEXTURE;
            case 37 -> RAINBOW_TEXTURE;
            case 38 -> RED_VIOLET_TEXTURE;
            case 39 -> GREEN_RED_TEXTURE;

        };

    }




}
