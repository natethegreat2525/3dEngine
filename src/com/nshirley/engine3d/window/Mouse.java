package com.nshirley.engine3d.window;

import org.lwjgl.glfw.GLFWCursorPosCallback;

public class Mouse extends GLFWCursorPosCallback {

	public static double VX, VY;
	public static double X, Y;
	@Override
	public void invoke(long window, double xpos, double ypos) {	
		VX = xpos - X;
		VY = ypos - Y;
		X = xpos;
		Y = ypos;
	}

}
