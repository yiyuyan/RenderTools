package cn.ksmcbrigade.rts.mixin;

import cn.ksmcbrigade.vmr.uitls.ModuleUtils;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Camera;
import net.minecraft.client.renderer.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public class GameRendererMixin {
    @Inject(method = "renderItemInHand",at = @At("HEAD"),cancellable = true)
    public void hand(PoseStack p_109121_, Camera p_109122_, float p_109123_, CallbackInfo ci){
        if(ModuleUtils.enabled("hack.name.no_hand")) ci.cancel();
    }
}
