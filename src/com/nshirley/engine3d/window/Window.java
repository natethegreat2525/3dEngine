package com.nshirley.engine3d.window;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

import java.nio.ByteBuffer;

import org.lwjgl.glfw.GLFWvidmode;

public class Window {

	private int width, height;
	private long window;
	private String title;
	
	private Input input;
	private Mouse mouse;
	private MouseButton mouseButton;
	
	public Window(int w, int h, String title) {
		glfwInit();
		width = w;
		height = h;
		this.title = title;
		glfwWindowHint(GLFW_RESIZABLE, GL_FALSE);

		window = glfwCreateWindow(width, height, this.title, NULL, NULL);
		
		ByteBuffer vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		glfwSetWindowPos(window, (GLFWvidmode.width(vidmode) - width) / 2, (GLFWvidmode.height(vidmode) - height) / 2);
		if (window == NULL) {
			System.err.println("NULL Window");
		}
		input = new Input();
		mouse = new Mouse();
		mouseButton = new MouseButton();
		
		glfwSetKeyCallback(window, input);
		glfwSetCursorPosCallback(window, mouse);
		glfwSetMouseButtonCallback(window, mouseButton);

		glfwMakeContextCurrent(window);
		glfwShowWindow(window);
	}
	
	public void flip() {
		glfwSwapBuffers(window);
	}
	
	public void clear() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}
	
	public boolean shouldClose() {
		return glfwWindowShouldClose(window) == GL_TRUE;
	}
	
	public void pollEvents() {
		glfwPollEvents();
	}
	
	public void destroy() {
		glfwDestroyWindow(window);
		glfwTerminate();
	}

	public void setCursorMode(int mode) {
		glfwSetInputMode(window, GLFW_CURSOR, mode);
	}
}
