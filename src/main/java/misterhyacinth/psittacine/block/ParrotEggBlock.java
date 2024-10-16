package misterhyacinth.psittacine.block;


import misterhyacinth.psittacine.Psittacine;
import misterhyacinth.psittacine.util.ParrotVariantInterface;
import misterhyacinth.psittacine.util.PsittacineTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.BlockStateComponent;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.*;
import net.minecraft.entity.passive.BatEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.passive.ParrotEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

public class ParrotEggBlock extends Block {

    public static final IntProperty PARROT_TYPE = IntProperty.of("parrot_type", 0, 40);
    public static final IntProperty HATCH = Properties.HATCH;
    private static final VoxelShape SHAPE = Block.createCuboidShape(6.0, 0.0, 6.0, 10.0, 5.0, 10.0);


    public ParrotEggBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(HATCH, 0).with(PARROT_TYPE, 40));
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(HATCH, PARROT_TYPE);
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    public ItemStack getPickStack(WorldView world, BlockPos pos, BlockState state) {
        ItemStack itemStack = new ItemStack(this.asItem());
        int i = state.get(PARROT_TYPE);
        if (i < 40) {
            itemStack.set(DataComponentTypes.BLOCK_STATE,
                    BlockStateComponent.DEFAULT.with(ParrotEggBlock.PARROT_TYPE, i));
        }
        return itemStack;
    }

    public int getHatchStage(BlockState state) {
        return state.get(HATCH);
    }

    private boolean isReadyToHatch(BlockState state) {
        return this.getHatchStage(state) == 2;
    }


    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!this.isReadyToHatch(state)) {
            world.playSound(null, pos, Psittacine.EGG_CRACK_SOUND, SoundCategory.BLOCKS,
                    0.7F, 0.9F + random.nextFloat() * 0.2F);
            world.setBlockState(pos, state.with(HATCH, this.getHatchStage(state) + 1), 2);
        } else {
            world.playSound(null, pos, Psittacine.EGG_HATCH_SOUND, SoundCategory.BLOCKS,
                    0.7F, 0.9F + random.nextFloat() * 0.2F);
            world.breakBlock(pos, false);
            ParrotEntity parrotEntity = EntityType.PARROT.create(world);
            if (parrotEntity != null) {
                parrotEntity.setBreedingAge(-24000);
                Vec3d vec3d = pos.toCenterPos();
                parrotEntity.refreshPositionAndAngles(vec3d.getX(), vec3d.getY(), vec3d.getZ(),
                        MathHelper.wrapDegrees(world.random.nextFloat() * 360.0F), 0.0F);
                parrotEntity.initialize(world, world.getLocalDifficulty(pos), SpawnReason.BREEDING,null);
                if (state.get(PARROT_TYPE) < 40) {
                    ((ParrotVariantInterface)parrotEntity).setVariantByInt(state.get(PARROT_TYPE));
                }
                world.spawnEntity(parrotEntity);
            }
        }
    }


    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        boolean hatchBoost = isAboveHatchBooster(world, pos);
        boolean hatchable = isAboveHatchingBlock(world, pos);
        world.emitGameEvent(GameEvent.BLOCK_PLACE, pos, GameEvent.Emitter.of(state));

        if (hatchable) {
            int i = hatchBoost ? 6000 : 12000;
            int j = i / 3;
            world.scheduleBlockTick(pos, this, j + world.random.nextInt(300));
        }
    }



    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {

        boolean hatchBoost = isAboveHatchBooster(world, pos);
        boolean hatchable = isAboveHatchingBlock(world, pos);

        if (hatchable && !world.getBlockTickScheduler().isQueued(pos, this)) {
            int i = hatchBoost ? 6000 : 12000;
            int j = i / 3;
            world.scheduleBlockTick(pos, this, j + world.random.nextInt(300));
        }

        super.neighborUpdate(state, world, pos, sourceBlock, sourcePos, notify);
    }

    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state,
                           @Nullable BlockEntity blockEntity, ItemStack tool) {
        if (!world.isClient && EnchantmentHelper.hasAnyEnchantmentsIn(tool, PsittacineTags.DROPS_EGGS)) {
            ItemStack itemStack = new ItemStack(this.asItem());
            int i = state.get(PARROT_TYPE);
            if (i < 40) {
                itemStack.set(DataComponentTypes.BLOCK_STATE,
                        BlockStateComponent.DEFAULT.with(ParrotEggBlock.PARROT_TYPE, i));
            }
            ItemEntity itemEntity = new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(),
                    itemStack);
            itemEntity.setToDefaultPickupDelay();
            world.spawnEntity(itemEntity);
        }
        super.afterBreak(world, player, pos, state, blockEntity, tool);
        this.breakEgg(world, pos, state);
    }




    public static boolean isAboveHatchBooster(BlockView world, BlockPos pos) {
        return world.getBlockState(pos.down()).isIn(PsittacineTags.PARROT_EGG_HATCH_BOOST);
    }

    public static boolean isAboveHatchingBlock(BlockView world, BlockPos pos) {
        return world.getBlockState(pos.down()).isIn(PsittacineTags.PARROT_EGG_HATCH_BLOCKS) ;
    }


    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        if (!entity.bypassesSteppingEffects()) {
            this.tryBreakEgg(world, state, pos, entity, 100);
        }
        super.onSteppedOn(world, pos, state, entity);
    }

    public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        if (!entity.bypassesLandingEffects()) {
            this.tryBreakEgg(world, state, pos, entity, 3);
        }
        super.onLandedUpon(world, state, pos, entity, fallDistance);
    }

    private void tryBreakEgg(World world, BlockState state, BlockPos pos, Entity entity, int inverseChance) {
        if (this.breaksEgg(world, entity)) {
            if (!world.isClient && world.random.nextInt(inverseChance) == 0 && state.isOf(Psittacine.PARROT_EGG)) {
                this.breakEgg(world, pos, state);
            }

        }
    }


    private void breakEgg(World world, BlockPos pos, BlockState state) {
        world.playSound(null, pos, Psittacine.EGG_BREAK_SOUND, SoundCategory.BLOCKS,
                0.7F, 0.9F + world.random.nextFloat() * 0.2F);
        world.breakBlock(pos, false);
    }

    private boolean breaksEgg(World world, Entity entity) {
        if (!(entity instanceof ParrotEntity) && !(entity instanceof BatEntity) && !(entity instanceof ChickenEntity)) {
            if (!(entity instanceof LivingEntity)) {
                return false;
            } else {
                return entity instanceof PlayerEntity || world.getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING);
            }
        } else {
            return false;
        }
    }


}
