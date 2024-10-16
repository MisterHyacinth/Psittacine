package misterhyacinth.psittacine.mixin.client;

import misterhyacinth.psittacine.ParrotSkinGetter;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.ParrotEntityRenderer;
import net.minecraft.entity.passive.ParrotEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ParrotEntityRenderer.class)
@Environment(EnvType.CLIENT)
public class ParrotEntityRendererMixin {

    @Inject(at = @At("HEAD"),
            method = "getTexture(Lnet/minecraft/entity/passive/ParrotEntity;)Lnet/minecraft/util/Identifier;",
            cancellable = true)
    private void getTexture(ParrotEntity parrotEntity, CallbackInfoReturnable<Identifier> cir) {

        NbtCompound nbt = new NbtCompound();
        parrotEntity.writeNbt(nbt);
        int variant = 0;

        if (nbt.contains("Variant")) {
            variant = nbt.getInt("Variant");
        }

        cir.setReturnValue(ParrotSkinGetter.getParrotSkin(variant));
    }


}
