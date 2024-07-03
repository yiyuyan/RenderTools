package cn.ksmcbrigade.rts.modules;

import cn.ksmcbrigade.vmr.module.Config;
import cn.ksmcbrigade.vmr.module.Module;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.client.Minecraft;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static cn.ksmcbrigade.vmr.module.Config.configDir;

public class HighCameraDistance extends Module {
    public HighCameraDistance() throws IOException {
        super("hack.name.high_cd",false,-1,new Config(new File(HighCameraDistance.class.getSimpleName()),get()),false);
    }

    @Override
    public void enabled(Minecraft MC) throws Exception {
        File pathFile = new File(configDir,getConfig().file.getPath()+".json");
        this.getConfig().setData(JsonParser.parseString(Files.readString(pathFile.toPath())).getAsJsonObject());
    }

    public static JsonObject get(){
        JsonObject object = new JsonObject();
        object.addProperty("disa",12.0d);
        return object;
    }
}