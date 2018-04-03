package com.nshirley.engine3d.window;

import static org.lwjgl.glfw.GLFW.GLFW_CURSOR;
import static org.lwjgl.glfw.GLFW.GLFW_RESIZABLE;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwDestroyWindow;
import static org.lwjgl.glfw.GLFW.glfwGetPrimaryMonitor;
import static org.lwjgl.glfw.GLFW.glfwGetVideoMode;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSetCursorPosCallback;
import static org.lwjgl.glfw.GLFW.glfwSetInputMode;
import static org.lwjgl.glfw.GLFW.glfwSetKeyCallback;
import static org.lwjgl.glfw.GLFW.glfwSetMouseButtonCallback;
import static org.lwjgl.glfw.GLFW.glfwSetScrollCallback;
import static org.lwjgl.glfw.GLFW.glfwSetWindowPos;
import static org.lwjgl.glfw.GLFW.glfwShowWindow;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwSwapInterval;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.system.MemoryUtil.NULL;

import org.lwjgl.glfw.GLFWVidMode;

public class Window {

	private int width, height;
	private long window;
	private String title;
	
	private Input input;
	private Mouse mouse;
	private MouseButton mouseButton;
	private MouseWheel mouseWheel;
	
	public Window(int w, int h, String title) {
		glfwInit();
		width = w;
		height = h;
		this.title = title;
		glfwWindowHint(GLFW_RESIZABLE, GL_FALSE);

		window = glfwCreateWindow(width, height, this.title, NULL, NULL);
		GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		glfwSetWindowPos(window, (vidmode.width() - width) / 2, (vidmode.height() - height) / 2);
		if (window == NULL) {
			System.err.println("NULL Window");
		}
		input = new Input();
		mouse = new Mouse();
		mouseButton = new MouseButton();
		mouseWheel = new MouseWheel();
		
		glfwSetKeyCallback(window, input);
		glfwSetCursorPosCallback(window, mouse);
		glfwSetMouseButtonCallback(window, mouseButton);
		glfwSetScrollCallback(window, mouseWheel);

		glfwMakeContextCurrent(window);
		glfwShowWindow(window);
		glfwSwapInterval(1);
	}
	
	public void flip() {
		glfwSwapBuffers(window);
	}
	
	public void clear() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}
	
	public boolean shouldClose() {
		return glfwWindowShouldClose(window);
	}
	
	public void pollEvents() {
		MouseButton.resetPressRelease();
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
