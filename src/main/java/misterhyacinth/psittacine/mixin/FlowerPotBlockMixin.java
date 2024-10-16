package misterhyacinth.psittacine.mixin;

import misterhyacinth.psittacine.block.PottedFeatherBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FlowerPotBlock.class)
public class FlowerPotBlockMixin {

    @Shadow
    @Final
    private Block content;

    @Inject(at = @At("HEAD"), method = "onUseWithItem", cancellable = true)
    public void onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player,
                              Hand hand, BlockHitResult hit, CallbackInfoReturnable<ItemActionResult> cir) {

        Item item = stack.getItem();
        ItemPlacementContext ctx = new ItemPlacementContext(player, hand, stack, hit);
        boolean potIsEmpty = this.content == Blocks.AIR;
        Block featherPot = PottedFeatherBlock.getPotFromContent(item);

        if(featherPot != null && potIsEmpty) {
            world.setBlockState(pos, featherPot.getPlacementState(ctx), 3);
            world.emitGameEvent(player, GameEvent.BLOCK_CHANGE, pos);
            player.incrementStat(Stats.POT_FLOWER);
            stack.decrementUnlessCreative(1, player);
            cir.setReturnValue(ItemActionResult.success(world.isClient));
        }

    }

}
