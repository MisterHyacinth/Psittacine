package misterhyacinth.psittacine.mixin;

import misterhyacinth.psittacine.Psittacine;
import misterhyacinth.psittacine.block.ParrotEggBlock;
import misterhyacinth.psittacine.util.Cageable;
import misterhyacinth.psittacine.util.ParrotFeatherGetter;
import misterhyacinth.psittacine.util.ParrotVariantInterface;
import misterhyacinth.psittacine.util.PsittacineTags;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.BlockStateComponent;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.AnimalMateGoal;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ParrotEntity.class)
public abstract class ParrotEntityMixin extends TameableShoulderEntity implements VariantHolder<ParrotEntity.Variant>, ParrotVariantInterface, Cageable {


    @Shadow
    @Final
    private static TrackedData<Integer> VARIANT;

    @Unique
    private static final Ingredient BREEDING_INGREDIENT =
            Ingredient.ofItems(Psittacine.SEED_CAKE, Psittacine.TOASTED_SEED_CAKE);

    @Unique
    public int shedTime;


    protected ParrotEntityMixin(EntityType<? extends TameableShoulderEntity> entityType, World world) {
        super(entityType, world);
        this.shedTime = this.random.nextInt(6000) + 6000;
    }




    //INITIALIZE WITH ALLOWED BABIES AND BIOME VARIANTS
    @Inject(at = @At("HEAD"), method = "initialize", cancellable = true)
    public void initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason,
                           @Nullable EntityData entityData,
                           CallbackInfoReturnable<EntityData> cir) {

        RegistryEntry<Biome> registryEntry = world.getBiome(this.getBlockPos());

        if (registryEntry.isIn(PsittacineTags.SPAWNS_BAMBOO_PARROTS) || registryEntry.toString().contains("bamboo")) {
            this.setVariantByInt(world.getRandom().nextBetween(33, 39));
        } else if (registryEntry.isIn(PsittacineTags.SPAWNS_JUNGLE_PARROTS)) {
            this.setVariantByInt(world.getRandom().nextInt(21));
        } else if (registryEntry.isIn(PsittacineTags.SPAWNS_OUTBACK_PARROTS)
                || registryEntry.isIn(BiomeTags.IS_BADLANDS)
                || registryEntry.isIn(BiomeTags.IS_SAVANNA)
                || registryEntry.isIn(BiomeTags.DESERT_PYRAMID_HAS_STRUCTURE)
                || registryEntry.toString().contains("eucalyptus") || registryEntry.toString().contains("outback")) {
            this.setVariantByInt(world.getRandom().nextBetween(21, 32));
        } else {
            this.setVariantByInt(world.getRandom().nextInt(40));
        }


        this.shedTime = this.random.nextInt(6000) + 6000;

        if (entityData == null) {
            entityData = new PassiveEntity.PassiveData(true);
        }

        cir.setReturnValue(super.initialize(world, difficulty, spawnReason, entityData));
    }


    //NO MORE COOKIE MURDER, USE SEED CAKES FOR HEALING & BREEDING, ALLOW CAGING
    @Inject(at = @At("HEAD"), method = "interactMob", cancellable = true)
    public void interactMob(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        ItemStack itemStack = player.getStackInHand(hand);
        Item item = itemStack.getItem();
        ActionResult actionResult;

        //NO!
        if (itemStack.isIn(ItemTags.PARROT_POISONOUS_FOOD)) {
            cir.setReturnValue(ActionResult.FAIL);
            cir.cancel();
        } else if (itemStack.isOf(Psittacine.BIRD_CAGE)) {
            ItemStack itemStackNew = new ItemStack(Psittacine.CAGED_PARROT);
            copyParrotDataToStack(itemStackNew);

            ItemStack itemStackSwapped = ItemUsage.exchangeStack(itemStack, player, itemStackNew, false);
            player.setStackInHand(hand, itemStackSwapped);
            World world = this.getWorld();
            this.discard();
            cir.setReturnValue(ActionResult.success(world.isClient));
        } else if (this.isTamed() && this.isOwner(player) && this.isBreedingItem(itemStack)) {
            if (this.getHealth() < this.getMaxHealth()) {
                if (!this.getWorld().isClient()) {
                    this.eat(player, hand, itemStack);
                    FoodComponent foodComponent = itemStack.get(DataComponentTypes.FOOD);
                    this.heal(foodComponent != null ? (float)foodComponent.nutrition() : 1.0F);
                }
                cir.setReturnValue(ActionResult.success(this.getWorld().isClient()));
            } else {
                actionResult = super.interactMob(player, hand);
                if (!actionResult.isAccepted()) {
                    this.setSitting(!this.isSitting());
                    cir.setReturnValue(ActionResult.success(this.getWorld().isClient()));
                }

                cir.setReturnValue(actionResult);
            }
        }
    }

    //BREEDING =========================================================================================================

    //BIRD CAN BE BABY
    @Inject(at = @At("HEAD"), method = "isBaby", cancellable = true)
    public void isBaby(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(super.isBaby());
    }

    //BREEDING GOAL
    @Inject(at = @At("TAIL"), method = "initGoals")
    protected void initGoals(CallbackInfo ci) {
        this.goalSelector.add(2,
                new AnimalMateGoal(this, 1.0));
    }

    //SEEDCAKE MAKES BABIES
    @Inject(at = @At("HEAD"), method = "isBreedingItem", cancellable = true)
    public void isBreedingItem(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(BREEDING_INGREDIENT.test(stack));
    }


    //TAMED PARROTS CAN BREED
    @Inject(at = @At("HEAD"), method = "canBreedWith", cancellable = true)
    public void canBreedWith(AnimalEntity other, CallbackInfoReturnable<Boolean> cir) {
        if (!this.isTamed()) {
            cir.setReturnValue(false);
        } else if (!(other instanceof ParrotEntity parrotEntity)) {
            cir.setReturnValue(false);
        } else {
            cir.setReturnValue(parrotEntity.isTamed() && super.canBreedWith(other));
        }
    }


    //BREED AND LAY EGG
    public void breed(ServerWorld world, AnimalEntity other) {
        ItemStack itemStack = new ItemStack(Psittacine.PARROT_EGG_ITEM);
        int skin;
        if (this.random.nextBoolean()) {
            skin = this.getVariantByInt();
        } else {
            skin = other.getDataTracker().get(VARIANT);
        }

        itemStack.set(DataComponentTypes.BLOCK_STATE,
                BlockStateComponent.DEFAULT.with(ParrotEggBlock.PARROT_TYPE, skin));

        ItemEntity itemEntity = new ItemEntity(world, this.getPos().getX(), this.getPos().getY(),
                this.getPos().getZ(), itemStack);
        itemEntity.setToDefaultPickupDelay();
        this.breed(world, other, null);
        this.playSound(Psittacine.EGG_PLOP_SOUND, 1.0F,
                (this.random.nextFloat() -
                        this.random.nextFloat()) * 0.2F + 0.5F);
        world.spawnEntity(itemEntity);
    }

    //FEATHERS =========================================================================================================

    //SHED FEATHERS
    @Inject(at = @At("TAIL"), method = "tickMovement")
    public void tickMovement(CallbackInfo ci) {

        if (!this.getWorld().isClient && this.isAlive() && !this.isBaby() && --this.shedTime <= 0) {
            Item feather = ParrotFeatherGetter.get(this.getVariantByInt(), this.random);
            this.dropItem(feather);
            this.emitGameEvent(GameEvent.ENTITY_PLACE);
            this.shedTime = this.random.nextInt(6000) + 6000;
        }
    }


    //DROP PARROT FEATHER IF THE UNSPEAKABLE AND UNFORGIVABLE HAPPENS
    public void onDeath(DamageSource damageSource) {
        Item feather = ParrotFeatherGetter.get(this.getVariantByInt(), this.random);
        this.dropItem(feather);
        super.onDeath(damageSource);
    }



    //SOUNDS ===========================================================================================================

    //NO MORE RANDOM MOB SOUNDS
    @Inject(at = @At("HEAD"), method = "getRandomSound", cancellable = true)
    private static void getRandomSound(World world, Random random, CallbackInfoReturnable<SoundEvent> cir) {
        cir.setReturnValue(SoundEvents.ENTITY_PARROT_AMBIENT);
    }


     //VARIANTS ========================================================================================================


    //BYPASS ENUM TO SET AND GET VARIANT
    @Override
    public void setVariantByInt(Integer id) {
        this.dataTracker.set(VARIANT, id);
    }
    @Override
    public Integer getVariantByInt() {
        return this.dataTracker.get(VARIANT);
    }


    //WRITE AND READ NBT, BYPASS ENUM

    @Inject(at = @At("TAIL"), method = "writeCustomDataToNbt")
    public void writeCustomDataToNbt(NbtCompound nbt, CallbackInfo ci) {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt("Variant", this.getVariantByInt());
        nbt.putInt("FeatherShedTime", this.shedTime);
    }

    @Inject(at = @At("TAIL"), method = "readCustomDataFromNbt")
    public void readCustomDataFromNbt(NbtCompound nbt, CallbackInfo ci) {
        super.readCustomDataFromNbt(nbt);
        this.setVariantByInt(nbt.getInt("Variant"));

        if (nbt.contains("FeatherShedTime")) {
            this.shedTime = nbt.getInt("FeatherShedTime");
        }
    }

    //CAGING ===========================================================================================================

    @Override
    public void copyParrotDataToStack(ItemStack stack) {
        Cageable.copyTameableDataToStack(this, stack);
        NbtComponent.set(DataComponentTypes.BUCKET_ENTITY_DATA, stack, (nbt) -> {
            nbt.putInt("ForcedAge", this.forcedAge);

            nbt.putInt("Variant", this.getVariantByInt());
            nbt.putInt("FeatherShedTime", this.shedTime);
        });
    }

    @Override
    public void copyParrotDataFromNbt(NbtCompound nbt) {
        Cageable.copyTameableDataFromNbt(this, nbt);
        this.forcedAge = nbt.getInt("ForcedAge");

        this.setVariantByInt(nbt.getInt("Variant"));

        if (nbt.contains("FeatherShedTime")) {
            this.shedTime = nbt.getInt("FeatherShedTime");
        }

    }






















}




