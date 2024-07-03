package cn.ksmcbrigade.rts.modules;

import cn.ksmcbrigade.vmr.module.Module;
import cn.ksmcbrigade.vmr.uitls.ModuleUtils;
import net.minecraft.client.Minecraft;

public class CloseAll extends Module {
    public CloseAll() {
        super("hack.name.close_all");
    }

    @Override
    public void enabled(Minecraft MC) throws Exception {
        ModuleUtils.getAll(true).stream()
                .filter(m -> m!=this)
                .toList()
                .forEach(m -> {
                    try {
                        m.setEnabled(false);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
        ModuleUtils.set(this.name,false);
    }
}
