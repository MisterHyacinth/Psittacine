package misterhyacinth.psittacine.mixin;

import misterhyacinth.psittacine.util.PsittacineTags;
import net.minecraft.block.BlockState;
import net.minecraft.component.type.ToolComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShearsItem;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(ShearsItem.class)
public class ShearsItemMixin {

    // exempt from any license restrictions -
    // please re-use this freely (with your own registrations of the same common tags)
    // until fabric implements something similar again

    @Inject(at = @At("HEAD"), method = "createToolComponent", cancellable = true)
    private static void createToolComponent(CallbackInfoReturnable<ToolComponent> cir) {
        cir.setReturnValue(
                new ToolComponent(
                        List.of(
                                ToolComponent.Rule.ofAlwaysDropping(PsittacineTags.SHEARS_ALWAYS_DROP, 15.0F),
                                ToolComponent.Rule.of(PsittacineTags.SHEARS_SPEED_15, 15.0F),
                                ToolComponent.Rule.of(PsittacineTags.SHEARS_SPEED_5, 5.0F),
                                ToolComponent.Rule.of(PsittacineTags.SHEARS_SPEED_2, 2.0F)),
                        1.0F, 1)
        );
    }

    @Inject(at = @At("HEAD"), method = "postMine", cancellable = true)
    public void postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner,
                         CallbackInfoReturnable<Boolean> cir) {
        if (!world.isClient && !state.isIn(BlockTags.FIRE)) {
            stack.damage(1, miner, EquipmentSlot.MAINHAND);
        }
        cir.setReturnValue(
                state.isIn(PsittacineTags.SHEARS_POST_MINE)
        );
    }


}
