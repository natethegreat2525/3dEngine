package com.nshirley.engine3d.window;

import org.lwjgl.glfw.GLFWMouseButtonCallback;
import org.lwjgl.glfw.GLFW;

public class MouseButton extends GLFWMouseButtonCallback {

	public static boolean[] mouseButtons = new boolean[10];
	private static boolean[] mousePressArr = new boolean[10];
	private static boolean[] mouseReleaseArr = new boolean[10];
	
	@Override
	public void invoke(long window, int button, int action, int mods) {
		if (action == GLFW.GLFW_PRESS) {
			mouseButtons[button] = true;
			mousePressArr[button] = true;
		} else if (action == GLFW.GLFW_RELEASE) {
			mouseButtons[button] = false;
			mouseReleaseArr[button] = true;
		}
	}
	
	protected static void resetPressRelease() {
		for (int i = 0; i < 10; i++) {
			mousePressArr[i] = false;
			mouseReleaseArr[i] = false;
		}
	}
	
	public static boolean mousePress(int idx) {
		return mousePressArr[idx];
	}
	public static boolean mouseRelease(int idx) {
		return mouseReleaseArr[idx];
	}
	
	public static boolean mouseDown(int idx) {
		return mouseButtons[idx];
	}

}
