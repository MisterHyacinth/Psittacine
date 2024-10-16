package misterhyacinth.psittacine.item;

import misterhyacinth.psittacine.block.ParrotEggBlock;
import net.minecraft.block.Block;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.BlockStateComponent;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class ParrotEggItem extends BlockItem {
    public ParrotEggItem(Block block, Settings settings) {
        super(block, settings);
    }

    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type) {

        BlockStateComponent blockStateComponent =
                stack.getOrDefault(DataComponentTypes.BLOCK_STATE, BlockStateComponent.DEFAULT);
        if (!blockStateComponent.isEmpty()) {
            if (blockStateComponent.getValue(ParrotEggBlock.PARROT_TYPE) != null) {
                int i = blockStateComponent.getValue(ParrotEggBlock.PARROT_TYPE);
                if (i < 40) {
                    Formatting[] formattings = new Formatting[]{Formatting.ITALIC, Formatting.GRAY};
                    String string = "parrot.psittacine." + i;
                    tooltip.add(Text.translatable(string).formatted(formattings));
                }
            }
        }

    }

}
