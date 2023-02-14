package cc.mehanika.ecl.config;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = "ecl")
public class ModConfig implements ConfigData {

    public static void init() {

        AutoConfig.register(ModConfig.class, Toml4jConfigSerializer::new);

    }

    @Comment("If true, chunk loader will use ender pearls as a fuel.")
    public boolean burnEnderPearls = true;

    @Comment("Fuel consuming per hour.")
    public int pearlsPerHour = 8;

}
