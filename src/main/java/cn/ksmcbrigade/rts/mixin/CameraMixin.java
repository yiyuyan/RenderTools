package cn.ksmcbrigade.rts.mixin;

import cn.ksmcbrigade.vmr.uitls.ModuleUtils;
import net.minecraft.client.Camera;
import net.minecraft.world.level.material.FogType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Camera.class)
public class CameraMixin {
    @Inject(method = "getFluidInCamera",at = @At("RETURN"),cancellable = true)
    public void fog(CallbackInfoReturnable<FogType> cir){
        if(ModuleUtils.enabled("hack.name.no_under_overlay")) cir.setReturnValue(FogType.NONE);
    }

    @ModifyVariable(method = "getMaxZoom",at = @At("HEAD"),argsOnly = true)
    public double disa(double d){
        return ModuleUtils.enabled("hack.name.high_cd")?ModuleUtils.get("hack.name.high_cd").getConfig().get("disa").getAsDouble():d;
    }

    @Inject(method = "getMaxZoom",at = @At(value = "HEAD"),cancellable = true)
    public void maxZoom(double p_90567_, CallbackInfoReturnable<Double> cir){
        if(ModuleUtils.enabled("hack.name.no_cc")) cir.setReturnValue(p_90567_);
    }
}
