package cn.ksmcbrigade.rts.mixin;

import cn.ksmcbrigade.vmr.uitls.ModuleUtils;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ScreenEffectRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ScreenEffectRenderer.class)
public class ScreenEffectRendererMixin {
    @Inject(method = "renderFire",at = @At("HEAD"),cancellable = true)
    private static void fire(Minecraft p_110729_, PoseStack p_110730_, CallbackInfo ci){
        if(ModuleUtils.enabled("hack.name.no_fire")) ci.cancel();
    }
}
