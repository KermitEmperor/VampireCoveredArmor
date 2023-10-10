package com.kermitemperor.vampirismcoveredarmor.config;

import net.minecraftforge.common.ForgeConfigSpec;

import java.util.Collections;
import java.util.List;

public class VCACommonConfigs {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<List<? extends String>> SUNPROTECTED_ARMOR;
    public static final ForgeConfigSpec.ConfigValue<Boolean> INVERT;

    static {
        BUILDER.push("Configs for Vampirism Covered Armor");

        SUNPROTECTED_ARMOR = BUILDER.comment("Whitelist for armors to be considered protected against the Sun (ex.: [\"minecraft:diamond_boots\", \"modid:item_id\"])")
                .defineListAllowEmpty(List.of("sunprotected_armor"), Collections::emptyList, (element) -> element instanceof String || element == null);

        INVERT = BUILDER.comment("If sunprotected_armor should be inverted to blacklist list").define("sunprotected_armor_to_opposite", false);


        BUILDER.pop();
        SPEC = BUILDER.build();
    }


}