package misterhyacinth.psittacine.item;

import misterhyacinth.psittacine.Psittacine;
import misterhyacinth.psittacine.util.Cageable;
import net.minecraft.block.BlockState;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

import java.util.List;
import java.util.Objects;

public class CagedParrotItem extends Item {
    public CagedParrotItem(Settings settings) {
        super(settings);
    }

    public ActionResult useOnBlock(ItemUsageContext context) {

        World world = context.getWorld();
        if (world instanceof ServerWorld) {
            ItemStack itemStack = context.getStack();
            BlockPos blockPos = context.getBlockPos();
            Direction direction = context.getSide();
            BlockState blockState = world.getBlockState(blockPos);
            PlayerEntity player = context.getPlayer();
            Hand hand = context.getHand();

            BlockPos blockPos2;
            if (blockState.getCollisionShape(world, blockPos).isEmpty()) {
                blockPos2 = blockPos;
            } else {
                blockPos2 = blockPos.offset(direction);
            }



            EntityType entityType = EntityType.PARROT;
            Entity entity = entityType.spawnFromItemStack((ServerWorld)world, itemStack, context.getPlayer(), blockPos2,
                    SpawnReason.BUCKET, true,
                    !Objects.equals(blockPos, blockPos2) && direction == Direction.UP);
            if (entity != null) {
                NbtComponent nbtComponent = itemStack.getOrDefault(DataComponentTypes.BUCKET_ENTITY_DATA, NbtComponent.DEFAULT);
                if (!nbtComponent.isEmpty()) {
                    NbtCompound nbt = nbtComponent.copyNbt();
                    ((Cageable)entity).copyParrotDataFromNbt(nbt);
                }
                ItemStack itemStackNew = new ItemStack(Psittacine.BIRD_CAGE);
                ItemStack itemStackSwapped = ItemUsage.exchangeStack(itemStack, player, itemStackNew, false);
                player.setStackInHand(hand, itemStackSwapped);
                world.emitGameEvent(context.getPlayer(), GameEvent.ENTITY_PLACE, blockPos);
            }

        }

        return ActionResult.SUCCESS;

    }



    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type) {

        NbtComponent nbtComponent = stack.getOrDefault(DataComponentTypes.BUCKET_ENTITY_DATA, NbtComponent.DEFAULT);

        if (!nbtComponent.isEmpty()) {
            NbtCompound nbt = nbtComponent.copyNbt();
            if (nbt.contains("Variant")) {
                int i = nbt.getInt("Variant");
                if (i < 40) {
                    Formatting[] formattings = new Formatting[]{Formatting.ITALIC, Formatting.GRAY};
                    String string = "parrot.psittacine." + i;
                    tooltip.add(Text.translatable(string).formatted(formattings));
                }
            }
        }

    }




}
