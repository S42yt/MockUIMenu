package tr.s42.mockuimenu.mixin.client;

import net.minecraft.client.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tr.s42.mockuimenu.client.util.ChatHelper;

@Mixin(Keyboard.class)
public class KeyboardMixin {

    @Inject(method = "processF3", at = @At("HEAD"))
    private void onProcessF3(int key, CallbackInfoReturnable<Boolean> cir) {
        if (key == 66) {
            ChatHelper.INSTANCE.sendMessage("hallo norisk client");
        }
    }
}