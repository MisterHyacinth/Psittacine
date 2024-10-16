package misterhyacinth.psittacine.util;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.ServerConfigHandler;
import net.minecraft.text.Text;

import java.util.UUID;

public interface Cageable {

    //COPY TO STACK ====================================================================================================

    void copyParrotDataToStack(ItemStack stack);

    static void copyTameableDataToStack(TameableEntity entity, ItemStack stack) {
        copyDataToStack(entity, stack);
        NbtComponent.set(DataComponentTypes.BUCKET_ENTITY_DATA, stack, (nbt) -> {
            if (entity.getOwnerUuid() != null) {
                nbt.putUuid("Owner", entity.getOwnerUuid());
            }
            nbt.putBoolean("Sitting", entity.isSitting());
            nbt.putInt("InLove", entity.getLoveTicks());
            nbt.putInt("Age", entity.getBreedingAge());
        });
    }


    static void copyDataToStack(MobEntity entity, ItemStack stack) {
        stack.set(DataComponentTypes.CUSTOM_NAME, entity.getCustomName());
        NbtComponent.set(DataComponentTypes.BUCKET_ENTITY_DATA, stack, (nbtCompound) -> {
            if (entity.isAiDisabled()) {
                nbtCompound.putBoolean("NoAI", entity.isAiDisabled());
            }

            if (entity.isSilent()) {
                nbtCompound.putBoolean("Silent", entity.isSilent());
            }

            if (entity.hasNoGravity()) {
                nbtCompound.putBoolean("NoGravity", entity.hasNoGravity());
            }

            if (entity.isGlowingLocal()) {
                nbtCompound.putBoolean("Glowing", entity.isGlowingLocal());
            }

            if (entity.isInvulnerable()) {
                nbtCompound.putBoolean("Invulnerable", entity.isInvulnerable());
            }

            nbtCompound.putFloat("Health", entity.getHealth());
        });
    }


    //COPY FROM NBT ====================================================================================================

    void copyParrotDataFromNbt(NbtCompound nbt);

    static void copyTameableDataFromNbt(TameableEntity entity, NbtCompound nbt) {
        copyDataFromNbt(entity, nbt);
        UUID uUID;
        if (nbt.containsUuid("Owner")) {
            uUID = nbt.getUuid("Owner");
        } else {
            String string = nbt.getString("Owner");
            uUID = ServerConfigHandler.getPlayerUuidByName(entity.getServer(), string);
        }

        if (uUID != null) {
            try {
                entity.setOwnerUuid(uUID);
                entity.setTamed(true, false);
            } catch (Throwable var4) {
                entity.setTamed(false, true);
            }
        }

        //sitting
        entity.setSitting(nbt.getBoolean("Sitting"));
        entity.setInSittingPose(entity.isSitting());

        entity.setLoveTicks(nbt.getInt("InLove"));
        entity.setBreedingAge(nbt.getInt("Age"));
    }

    static void copyDataFromNbt(MobEntity entity, NbtCompound nbt) {
        if (nbt.contains("NoAI")) {
            entity.setAiDisabled(nbt.getBoolean("NoAI"));
        }

        if (nbt.contains("Silent")) {
            entity.setSilent(nbt.getBoolean("Silent"));
        }

        if (nbt.contains("NoGravity")) {
            entity.setNoGravity(nbt.getBoolean("NoGravity"));
        }

        if (nbt.contains("Glowing")) {
            entity.setGlowing(nbt.getBoolean("Glowing"));
        }

        if (nbt.contains("Invulnerable")) {
            entity.setInvulnerable(nbt.getBoolean("Invulnerable"));
        }

        if (nbt.contains("Health", 99)) {
            entity.setHealth(nbt.getFloat("Health"));
        }

    }









}
