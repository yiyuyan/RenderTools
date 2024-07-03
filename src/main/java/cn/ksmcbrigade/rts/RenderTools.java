package cn.ksmcbrigade.rts;

import cn.ksmcbrigade.vmr.uitls.ModuleUtils;
import cn.ksmcbrigade.vmr.module.Module;

import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

@Mod(RenderTools.MODID)
public class RenderTools {

    public static final String MODID = "rts";

    public static final Logger LOGGER = LogUtils.getLogger();

    public static Module[] modules = geModules();

    public RenderTools() throws ClassNotFoundException {
        MinecraftForge.EVENT_BUS.register(this);
        LOGGER.info("Adding modules.");

        for(Module module:modules){
            ModuleUtils.add(module);
            LOGGER.info("Added a module {}",module.getClass().getSimpleName());
        }

        LOGGER.info("Added done.");
    }

    public static Module[] geModules(){
        ArrayList<Module> modulesList = new ArrayList<>();
        try {
            ClassLoader classLoader = RenderTools.class.getClassLoader();
            Enumeration<URL> resources = classLoader.getResources("cn/ksmcbrigade/rts/modules");
            while (resources.hasMoreElements()) {
                URL url = resources.nextElement();
                LOGGER.info("Running Property {}",url.getProtocol());
                if (url.getProtocol().equals("jar")) {
                    String jarPath = URLDecoder.decode(url.getPath().substring(5, url.getPath().indexOf("!")), StandardCharsets.UTF_8);
                    JarFile jarFile = new JarFile(jarPath);
                    Enumeration<JarEntry> entries = jarFile.entries();
                    while (entries.hasMoreElements()) {
                        JarEntry entry = entries.nextElement();
                        if (entry.getName().startsWith("cn/ksmcbrigade/rts/modules") && entry.getName().endsWith(".class")) {
                            String className = entry.getName().replace("/", ".")
                                    .replace(".class", "");
                            Class<?> clazz = classLoader.loadClass(className);
                            if (Module.class.isAssignableFrom(clazz) && !clazz.equals(Module.class)) {
                                modulesList.add((Module) clazz.getDeclaredConstructor().newInstance());
                            }
                        }
                    }
                    jarFile.close();
                } else {
                    File file = new File(url.getFile());
                    if (file.isDirectory()) {
                        File[] classFiles = file.listFiles((dir, name) -> name.endsWith(".class"));
                        for (File classFile : classFiles) {
                            String className = classFile.getName().replace(".class", "");
                            Class<?> clazz = classLoader.loadClass("cn.ksmcbrigade.rts.modules." + className);
                            if (Module.class.isAssignableFrom(clazz) && !clazz.equals(Module.class)) {
                                modulesList.add((Module) clazz.getDeclaredConstructor().newInstance());
                            }
                        }
                    }
                }
            }
        } catch (IOException | ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException e) {
            LOGGER.error("error in get modules",e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        return modulesList.toArray(new Module[0]);
    }
}
