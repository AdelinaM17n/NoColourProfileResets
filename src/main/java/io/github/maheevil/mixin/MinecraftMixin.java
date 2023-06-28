package io.github.maheevil.mixin;

import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MinecraftMixin {
	@Inject(
		method = "stop",
		at = @At(value = "INVOKE", target = "org/lwjgl/opengl/Display.destroy ()V", shift = At.Shift.BEFORE)
	)
	private void ee(CallbackInfo ci){
		Minecraft.shutdown();
		Runtime.getRuntime().halt(0);
	}
}
