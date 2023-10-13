package com.kermitemperor.vampirismcoveredarmor;

import com.kermitemperor.vampirismcoveredarmor.config.VCACommonConfigs;
import com.mojang.logging.LogUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import java.util.Objects;


@Mod(VampirismCoveredArmor.MOD_ID)
public class VampirismCoveredArmor {

    public static final String MOD_ID = "vampirismcoveredarmor";
    private static final Logger LOGGER = LogUtils.getLogger();

    public VampirismCoveredArmor() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        eventBus.addListener(this::setup);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, VCACommonConfigs.SPEC, "vampirismcoveredarmor-common.toml");

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        // Some preinit code
        LOGGER.info("[VampirismCoveredArmor]: Pre-init phase started");
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public void addCoveredArmorTooltip(final ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        if (VCACommonConfigs.SUNPROTECTED_ARMOR.get().contains(Objects.requireNonNull(stack.getItem().getRegistryName()).toString())) {
            event.getToolTip().add(new TranslatableComponent("tooltip.vampirismcoveredarmor.sunprotected").withStyle(ChatFormatting.DARK_GRAY).withStyle(ChatFormatting.UNDERLINE));
        }
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)

}
