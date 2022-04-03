package fr.alasdiablo.diolib.mixin;

import fr.alasdiablo.diolib.tag.DioTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.PowderSnowBlock;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = PowderSnowBlock.class, priority = 0)
public class PowderSnowBlockMixin {

    @Inject(method = "canEntityWalkOnPowderSnow", at = @At("RETURN"), cancellable = true)
    private static void canEntityWalkOnPowderSnow(Entity entity, @NotNull CallbackInfoReturnable<Boolean> callback) {
        if (!callback.getReturnValue()) {
            if (entity instanceof LivingEntity livingEntity) {
                var boots = livingEntity.getItemBySlot(EquipmentSlot.FEET);
                if (boots.is(DioTags.BOOTS_WALK_ON_POWDER_SNOW)) {
                    callback.setReturnValue(true);
                }
            }
        }
    }
}
