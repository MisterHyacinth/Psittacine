package misterhyacinth.psittacine.block;


import com.google.common.collect.Maps;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class PottedFeatherBlock extends Block {

    private static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    private static final Map<Item, Block> CONTENT_TO_POTTED = Maps.newHashMap();
    protected static final VoxelShape SHAPE = Block.createCuboidShape(5.0, 0.0, 5.0, 11.0, 6.0, 11.0);
    private final Item content;

    public PottedFeatherBlock(Item content, Settings settings) {
        super(settings);
        this.content = content;
        CONTENT_TO_POTTED.put(content, this);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH));
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }


    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        Direction dir = state.get(FACING).rotateClockwise(Direction.Axis.Y);
        world.setBlockState(pos, this.getDefaultState().with(FACING, dir));
        return ItemActionResult.success(world.isClient);
    }


    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        ItemStack itemStack = new ItemStack(this.content);
        if (!player.giveItemStack(itemStack)) {
            player.dropItem(itemStack, false);
        }
        world.setBlockState(pos, Blocks.FLOWER_POT.getDefaultState(), 3);
        world.emitGameEvent(player, GameEvent.BLOCK_CHANGE, pos);
        return ActionResult.success(world.isClient);
    }


    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING,rotation.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    public ItemStack getPickStack(WorldView world, BlockPos pos, BlockState state) {
        return new ItemStack(this.content);
    }

    public static Block getPotFromContent(Item content) {
        return CONTENT_TO_POTTED.getOrDefault(content, null);
    }



    public Item getContent() {
        return this.content;
    }


}
