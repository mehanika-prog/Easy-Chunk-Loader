package cc.mehanika.ecl;

import cc.mehanika.ecl.config.ModConfig;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.loader.api.FabricLoader;

public class ModMenuApiImpl implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {

        if (FabricLoader.getInstance().isModLoaded("cloth-config")) {

            return parent -> AutoConfig.getConfigScreen(ModConfig.class, parent).get();

        } else {

            return parent -> {
                return parent;
            };

        }

    }
}
