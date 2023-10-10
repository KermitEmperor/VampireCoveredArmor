package com.kermitemperor.vampirismcoveredarmor.mixin;



import com.kermitemperor.vampirismcoveredarmor.config.VCACommonConfigs;
import de.teamlapen.vampirism.api.entity.player.vampire.IVampirePlayer;
import de.teamlapen.vampirism.player.FactionBasePlayer;
import de.teamlapen.vampirism.player.vampire.VampirePlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;



@SuppressWarnings({"unused", "AddedMixinMembersNamePattern"})
@Mixin(VampirePlayer.class)
public abstract class VampirePlayerMixin extends FactionBasePlayer<IVampirePlayer> {
    @Shadow(remap = false)
    private boolean sundamage_cache;

    public VampirePlayerMixin(Player player) {
        super(player);
    }


    @Inject(method = "isGettingSundamage", at = @At(value = "TAIL"), remap = false)
    public void noDamageIfInCoveredArmor(LevelAccessor iWorld, boolean forcerefresh, CallbackInfoReturnable<Boolean> cir) {
        if (forcerefresh) {
            this.sundamage_cache = this.sundamage_cache && !isInCoveredArmor(player);
        }
    }

    @Unique
    public boolean isInCoveredArmor(Player player) {
        for (ItemStack stack : player.getArmorSlots()) {
            String itemRegistryName = String.valueOf(stack.getItem().getRegistryName());
            if (VCACommonConfigs.SUNPROTECTED_ARMOR.get().contains(itemRegistryName) == VCACommonConfigs.INVERT.get()) return false;
        }
        return true;
    }
}
