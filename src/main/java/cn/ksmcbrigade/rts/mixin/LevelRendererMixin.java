package cn.ksmcbrigade.rts.mixin;

import cn.ksmcbrigade.vmr.uitls.ModuleUtils;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LevelRenderer.class)
public abstract class LevelRendererMixin {

    @Inject(method = {"renderSnowAndRain","tickRain"},at = @At("HEAD"),cancellable = true)
    public void weather(CallbackInfo ci){
        if(ModuleUtils.enabled("hack.name.nw")) ci.cancel();
    }

    @Inject(method = "renderEntity",at = @At("HEAD"),cancellable = true)
    public void player(Entity p_109518_, double p_109519_, double p_109520_, double p_109521_, float p_109522_, PoseStack p_109523_, MultiBufferSource p_109524_, CallbackInfo ci){
        if(!ModuleUtils.enabled("hack.name.fake_sp")) return;
        if(Minecraft.getInstance().player==null) return;
        if(!(p_109518_ instanceof Player)) return;
        if(p_109518_.getId()!=Minecraft.getInstance().player.getId()) ci.cancel();
    }
}
