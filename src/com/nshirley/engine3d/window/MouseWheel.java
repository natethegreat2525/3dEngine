package com.nshirley.engine3d.window;

import org.lwjgl.glfw.GLFWScrollCallback;

public class MouseWheel extends GLFWScrollCallback {

	public static double VX, VY;
	public static double X, Y;
	@Override
	public void invoke(long window, double xpos, double ypos) {	
		VX = xpos;
		VY = ypos;
		X += xpos;
		Y += ypos;
	}

}
