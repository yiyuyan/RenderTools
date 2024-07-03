package cn.ksmcbrigade.rts.modules;

import cn.ksmcbrigade.vmr.BuiltInModules.RainbowGui;
import cn.ksmcbrigade.vmr.module.Module;
import cn.ksmcbrigade.vmr.uitls.ModuleUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.level.Level;

import java.awt.*;

public class OverWorldXYZ extends Module {

    public OverWorldXYZ() {
        super("hack.over_xyz");
    }

    @Override
    public void renderGame(GuiGraphics pose) {
        Minecraft MC = Minecraft.getInstance();
        if(MC.player==null) return;
        if(MC.level==null) return;
        if(MC.level.dimension().equals(Level.NETHER)){
            int color;
            if(!ModuleUtils.enabled("RainbowGui")){
                color = Color.WHITE.getRGB();
            }
            else{
                color = RainbowGui.getColor().get("c").getAsInt();
            }

            String xyz = "OverWorld XYZ: " + Math.round(MC.player.getX()*100.0)/100.0D*8.00d + ", " + Math.round(MC.player.getY()*100.0)/100.0D + ", " + Math.round(MC.player.getZ()*8*100.0)/100.0D*8.00d;
            pose.drawString(MC.font,xyz,0,MC.font.lineHeight-2,color);
        }
    }
}
