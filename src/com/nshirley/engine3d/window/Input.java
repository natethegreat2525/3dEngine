package com.nshirley.engine3d.window;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallback;

public class Input extends GLFWKeyCallback {

	public static boolean[] keys = new boolean[1024];
	public static boolean[] keysHit = new boolean[1024];
	
	@Override
	public void invoke(long window, int key, int scancode, int action, int mods) {
		if (key < 0) {
			return;
		}
		keys[key] = action != GLFW.GLFW_RELEASE;
		if (action == GLFW.GLFW_PRESS) {
			keysHit[key] = true;
		}
	}
	
	public static boolean isKeyDown(int keycode) {
		return keys[keycode];
	}
	
	public static boolean isKeyHit(int keycode) {
		if (keysHit[keycode]) {
			keysHit[keycode] = false;
			return true;
		}
		return false;
	}

}
