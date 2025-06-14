package net.yokohama_miyazawa.maidillager.mixin;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.PillagerRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Pillager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.yokohama_miyazawa.maidillager.MaidIllager;
import net.yokohama_miyazawa.maidillager.layers.IllagerFaceLayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@OnlyIn(Dist.CLIENT)
@Mixin(PillagerRenderer.class)
public class MixinPillagerRenderer {
    private static final ResourceLocation PILLAGER = ResourceLocation.parse(MaidIllager.MODID + ":textures/entity/maid_pillager.png");

    @Inject(method = "getTextureLocation(Lnet/minecraft/world/entity/monster/Pillager;)Lnet/minecraft/resources/ResourceLocation;", at = @At("RETURN"), cancellable = true)
    public void onGetTextureLocation(Pillager entity, CallbackInfoReturnable<ResourceLocation> cir){
        cir.setReturnValue((ResourceLocation) PILLAGER);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    private void init(EntityRendererProvider.Context context, CallbackInfo cir) {
        ((PillagerRenderer)(Object)this).addLayer(new IllagerFaceLayer<>(((PillagerRenderer)(Object)this)));
    }
}
