package misterhyacinth.psittacine.mixin.client;

import misterhyacinth.psittacine.ParrotSkinGetter;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.feature.ShoulderParrotFeatureRenderer;
import net.minecraft.client.render.entity.model.ParrotEntityModel;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ShoulderParrotFeatureRenderer.class)
public abstract class ShoulderParrotFeatureRendererMixin<T extends PlayerEntity> extends FeatureRenderer<T, PlayerEntityModel<T>> {

    @Shadow
    @Final
    private ParrotEntityModel model;

    public ShoulderParrotFeatureRendererMixin(FeatureRendererContext<T, PlayerEntityModel<T>> context) {
        super(context);
    }

    @Inject(at = @At("HEAD"), method = "renderShoulderParrot", cancellable = true)
    private void renderShoulderParrot(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, T player,
                                      float limbAngle, float limbDistance, float headYaw, float headPitch,
                                      boolean leftShoulder, CallbackInfo ci) {
        NbtCompound nbtCompound = leftShoulder ? player.getShoulderEntityLeft() : player.getShoulderEntityRight();
        EntityType.get(nbtCompound.getString("id")).filter((type) -> {
            return type == EntityType.PARROT;
        }).ifPresent((type) -> {
            matrices.push();
            matrices.translate(leftShoulder ? 0.4F : -0.4F, player.isInSneakingPose() ? -1.3F : -1.5F, 0.0F);

            int variant = nbtCompound.getInt("Variant");
            VertexConsumer vertexConsumer = vertexConsumers.getBuffer(
                    this.model.getLayer(ParrotSkinGetter.getParrotSkin(variant)));

            int age = nbtCompound.getInt("Age");
            this.model.child = age < 0;

            this.model.poseOnShoulder(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV,
                    limbAngle, limbDistance, headYaw, headPitch, player.age);


            matrices.pop();
        });
        ci.cancel();
    }



}
