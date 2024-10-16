package misterhyacinth.psittacine.mixin.client;

import com.google.common.collect.ImmutableList;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.ParrotEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ParrotEntityModel.class)
@Environment(EnvType.CLIENT)
public abstract class ParrotEntityModelMixin <E extends Entity> extends EntityModel<E> {


    @Shadow
    @Final
    private ModelPart head;
    @Shadow @Final private ModelPart body;
    @Shadow @Final private ModelPart tail;
    @Shadow @Final private ModelPart rightWing;
    @Shadow @Final private ModelPart leftWing;
    @Shadow @Final private ModelPart leftLeg;
    @Shadow @Final private ModelPart rightLeg;

    @Shadow protected abstract void animateModel(ParrotEntityModel.Pose pose);

    @Shadow protected abstract void setAngles(ParrotEntityModel.Pose pose, int danceAngle, float limbAngle,
                                              float limbDistance, float age, float headYaw, float headPitch);




    @Inject(at = @At("HEAD"), method = "poseOnShoulder", cancellable = true)
    public void poseOnShoulder(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay,
                               float limbAngle, float limbDistance, float headYaw, float headPitch, int danceAngle,
                               CallbackInfo ci) {
        this.animateModel(ParrotEntityModel.Pose.ON_SHOULDER);
        this.setAngles(ParrotEntityModel.Pose.ON_SHOULDER, danceAngle, limbAngle, limbDistance, 0.0F, headYaw, headPitch);

        this.render(matrices, vertexConsumer, light, overlay, -1);

        ci.cancel();
    }


    @Unique
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, int color) {
        if (this.child) {
            matrices.push();
            float f;
            float invertedChildHeadScale = 2.1F;
            float invertedChildBodyScale = 2.0F;
            float childBodyYOffset = 24.0F;
            float childHeadYOffset = 11.5F;
            float childHeadZOffset = 1.0F;

            f = 1.5F / invertedChildHeadScale;
            matrices.scale(f, f, f);


            matrices.translate(0.0F, childHeadYOffset / 16.0F, childHeadZOffset / 16.0F);
            this.getHeadParts().forEach((headPart) -> {
                headPart.render(matrices, vertices, light, overlay, color);
            });
            matrices.pop();
            matrices.push();
            f = 1.0F / invertedChildBodyScale;
            matrices.scale(f, f, f);
            matrices.translate(0.0F, childBodyYOffset / 16.0F, 0.0F);
            getBodyParts().forEach((bodyPart) -> {
                bodyPart.render(matrices, vertices, light, overlay, color);
            });
            matrices.pop();
        } else {
            this.getHeadParts().forEach((headPart) -> {
                headPart.render(matrices, vertices, light, overlay, color);
            });
            this.getBodyParts().forEach((bodyPart) -> {
                bodyPart.render(matrices, vertices, light, overlay, color);
            });
        }

    }

    protected Iterable<ModelPart> getHeadParts() {
        return ImmutableList.of(this.head);
    }

    protected Iterable<ModelPart> getBodyParts() {
        return ImmutableList.of(this.body, this.tail, this.rightWing, this.leftWing, this.leftLeg, this.rightLeg);
    }


}
