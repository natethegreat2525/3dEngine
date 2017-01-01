package com.nshirley.engine3d.window;

import org.lwjgl.glfw.GLFWMouseButtonCallback;
import org.lwjgl.glfw.GLFW;

public class MouseButton extends GLFWMouseButtonCallback {

	public static boolean[] mouseButtons = new boolean[10];
	@Override
	public void invoke(long window, int button, int action, int mods) {
		if (action == GLFW.GLFW_PRESS) {
			mouseButtons[button] = true;
		} else if (action == GLFW.GLFW_RELEASE) {
			mouseButtons[button] = false;
		}
	}

}
