package com.sollyw.biginv.mixin;

import net.minecraft.entity.player.PlayerInventory;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerInventory.class)
public abstract class PlayerInventoryMixin {
    @Shadow
    @Final
    @Mutable
    public static int MAIN_SIZE;

    @Shadow
    @Final
    @Mutable
    private static int HOTBAR_SIZE;

    @Shadow
    @Final
    @Mutable
    public static int OFF_HAND_SLOT;

    @Inject(method = "<clinit>",
            at = @At("TAIL"))
    private static void clinit(CallbackInfo ci) {
        MAIN_SIZE = 72;
        HOTBAR_SIZE = 12;
        OFF_HAND_SLOT = 76;
    }

    /**
     * @author SollyW
     * @reason Increase hotbar size
     */
    @Overwrite
    public static int getHotbarSize() {
        return 12;
    }

    @ModifyConstant(method = {"getSwappableHotbarSlot", "scrollInHotbar"},
            constant = @Constant(intValue = 9))
    private int hotbarSize(int value) {
        return 12;
    }

    @ModifyConstant(method = "isValidHotbarIndex",
            constant = @Constant(intValue = 9))
    private static int hotbarSize2(int value) {
        return 12;
    }

    @ModifyConstant(method = "getOccupiedSlotWithRoomForStack",
            constant = @Constant(intValue = 40))
    private int offhandSlot(int value) {
        return 76;
    }

    @ModifyConstant(method = {"readNbt", "writeNbt"},
            constant = @Constant(intValue = 100))
    private int armourSlotNbtOffset(int value) {
        return 74;
    }

    @ModifyArg(method = "<init>",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/util/collection/DefaultedList;ofSize(ILjava/lang/Object;)Lnet/minecraft/util/collection/DefaultedList;",
                    ordinal = 0),
            index = 0)
    private int size(int size) {
        return 72;
    }
}
