package com.example.example_mod.mixin;

import net.minecraft.client.gui.screen.TitleScreen;
import org.lwjgl.opengl.Display;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;


@Mixin(TitleScreen.class)
public class TitleScreenMixin {

	@Inject(method = "init", at = @At("TAIL"))
	public void exampleMod$onInit(CallbackInfo ci) {
		try {
			Field shutdownHook = Display.class.getDeclaredField("shutdown_hook");
			shutdownHook.setAccessible(true);

			Field modifiersField = Field.class.getDeclaredField("modifiers");
			modifiersField.setAccessible(true);
			modifiersField.setInt(shutdownHook, shutdownHook.getModifiers() & ~Modifier.FINAL);
			modifiersField.setInt(shutdownHook, shutdownHook.getModifiers() & ~Modifier.PRIVATE);
			shutdownHook.set(Display.class, new Thread(() -> System.out.println("hi")));

			Method registerShutdownHook = Display.class.getDeclaredMethod("registerShutdownHook");
			registerShutdownHook.setAccessible(true);
			registerShutdownHook.invoke(null);
		} catch (NoSuchFieldException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			throw new RuntimeException(e);
		}
		System.out.println("e");
	}
}
