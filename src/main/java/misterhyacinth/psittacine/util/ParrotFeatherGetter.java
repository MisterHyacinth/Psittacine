package misterhyacinth.psittacine.util;

import misterhyacinth.psittacine.Psittacine;
import net.minecraft.item.Item;
import net.minecraft.util.math.random.Random;

import java.util.ArrayList;

public class ParrotFeatherGetter {

    private static final Item white = Psittacine.WHITE_PARROT_FEATHER;
    private static final Item gray = Psittacine.GRAY_PARROT_FEATHER;
    private static final Item black = Psittacine.BLACK_PARROT_FEATHER;
    private static final Item pink = Psittacine.PINK_PARROT_FEATHER;
    private static final Item red = Psittacine.RED_PARROT_FEATHER;
    private static final Item orange = Psittacine.ORANGE_PARROT_FEATHER;
    private static final Item yellow = Psittacine.YELLOW_PARROT_FEATHER;
    private static final Item green = Psittacine.GREEN_PARROT_FEATHER;
    private static final Item blue = Psittacine.BLUE_PARROT_FEATHER;
    private static final Item violet = Psittacine.VIOLET_PARROT_FEATHER;


    public static Item get(int variant, Random random) {
        ArrayList<Item> featherSelection  = new ArrayList<Item>();

        switch (variant) {
            //red blue - scarlet macaw
            default -> {
                featherSelection.add(red);
                featherSelection.add(yellow);
                featherSelection.add(blue);
            }
            //blue - hyacinth macaw
            case 1 -> featherSelection.add(blue);
            //green - hahn's macaw : lime - green ringneck
            case 2, 34 -> featherSelection.add(green);
            //yellow blue - blue and gold macaw
            case 3 -> {
                featherSelection.add(yellow);
                featherSelection.add(blue);
            }
            //grey red - african gray parrot
            case 4 -> {
                featherSelection.add(gray);
                featherSelection.add(red);
            }
            //black - greater vasa parrot : black red - palm cockatoo
            case 5, 28 -> featherSelection.add(black);
            //green grey - quaker parrot
            case 6 -> {
                featherSelection.add(gray);
                featherSelection.add(green);
            }
            //yellow green blue - blue-fronted amazon : green yellow - green budgie
            case 7, 31 -> {
                featherSelection.add(yellow);
                featherSelection.add(green);
                featherSelection.add(blue);
            }
            //yellow green - yellow-headed amazon : yellow - golden conure
            case 8, 20 -> {
                featherSelection.add(yellow);
                featherSelection.add(green);
            }
            //green grey black - greencheek conure
            case 9 -> {
                featherSelection.add(gray);
                featherSelection.add(black);
                featherSelection.add(red);
                featherSelection.add(green);
                featherSelection.add(blue);
            }
            //green red black - pineapple greencheek conure
            case 10 -> {
                featherSelection.add(black);
                featherSelection.add(red);
                featherSelection.add(orange);
                featherSelection.add(green);
                featherSelection.add(blue);
            }
            //green red blue - military macaw : red green - greenwing macaw : green red - male eclectus
            case 11, 16, 39 -> {
                featherSelection.add(red);
                featherSelection.add(green);
                featherSelection.add(blue);
            }
            //orange black - black-headed caique
            case 12 -> {
                featherSelection.add(white);
                featherSelection.add(black);
                featherSelection.add(orange);
                featherSelection.add(green);
            }
            //orange green - white-bellied caique
            case 13 -> {
                featherSelection.add(white);
                featherSelection.add(orange);
                featherSelection.add(green);
            }
            //green black - masked lovebird
            case 14 -> {
                featherSelection.add(black);
                featherSelection.add(yellow);
                featherSelection.add(green);
            }
            //green pink - peachface lovebird
            case 15 -> {
                featherSelection.add(pink);
                featherSelection.add(green);
                featherSelection.add(blue);
            }
            //blue grey - spix's macaw
            case 17 -> {
                featherSelection.add(gray);
                featherSelection.add(blue);
            }
            //blue green - blue-headed pionus
            case 18 -> {
                featherSelection.add(green);
                featherSelection.add(blue);
                featherSelection.add(violet);
            }
            //orange - sun conure
            case 19 -> {
                featherSelection.add(orange);
                featherSelection.add(green);
            }
            //grey - male cockatiel : yellow grey - pied cockatiel
            case 21, 22 -> {
                featherSelection.add(white);
                featherSelection.add(gray);
                featherSelection.add(yellow);
                featherSelection.add(orange);
            }
            //grey white - whiteface cockatiel : dark grey - female cockatiel
            case 23, 24 -> {
                featherSelection.add(white);
                featherSelection.add(gray);
            }
            //white yellow - sulfur-crested cockatoo
            case 25 -> {
                featherSelection.add(white);
                featherSelection.add(yellow);
            }
            //pink grey - galah
            case 26 -> {
                featherSelection.add(white);
                featherSelection.add(gray);
                featherSelection.add(pink);
            }
            //pink - leadbeater's cockatoo : white pink - salmon-crested cockatoo
            case 27, 35 -> {
                featherSelection.add(white);
                featherSelection.add(pink);
            }
            //white blue - white budgie : blue white - blue budgie
            case 29, 32 -> {
                featherSelection.add(white);
                featherSelection.add(gray);
                featherSelection.add(blue);
            }
            //yellow grey - yellow budgie
            case 30 -> {
                featherSelection.add(gray);
                featherSelection.add(yellow);
            }
            //light blue - blue ringneck
            case 33 -> {
                featherSelection.add(blue);
                featherSelection.add(violet);
            }
            //white - umbrella cockatoo
            case 36 -> featherSelection.add(white);
            //rainbow - rainbow lorikeet
            case 37 -> {
                featherSelection.add(red);
                featherSelection.add(orange);
                featherSelection.add(green);
                featherSelection.add(violet);
            }
            //red violet - female eclectus
            case 38 -> {
                featherSelection.add(red);
                featherSelection.add(orange);
                featherSelection.add(violet);
            }
        }

        int i = random.nextInt(featherSelection.size());
        return featherSelection.get(i);
    }





}
