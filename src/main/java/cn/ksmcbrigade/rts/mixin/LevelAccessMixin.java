package cn.ksmcbrigade.rts.mixin;

import cn.ksmcbrigade.vmr.uitls.ModuleUtils;
import net.minecraft.world.level.CommonLevelAccessor;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelTimeAccess;
import net.minecraft.world.level.storage.LevelData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(LevelAccessor.class)
public interface LevelAccessMixin extends CommonLevelAccessor, LevelTimeAccess {
    @Shadow LevelData getLevelData();

    /**
     * @author KSmc_brigade
     * @reason re
     */
    @Overwrite
    default long dayTime() {
        long ret = this.getLevelData().getDayTime();
        if(ModuleUtils.enabled("hack.name.no_moon")) ret = 0;
        return ret;
    }
}
