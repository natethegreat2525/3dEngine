package com.nshirley.engine3d;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_NO_ERROR;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;

import java.util.ArrayList;

import org.lwjgl.opengl.GLContext;

import com.nshirley.engine3d.graphics.Shader;
import com.nshirley.engine3d.math.Matrix4f;

public class N3D {
	
	public static ArrayList<Matrix4f> matrixStack;

	public static void init() {
		GLContext.createFromCurrent();

		glClearColor(0, 0, 0, 1);
		glEnable(GL_DEPTH_TEST);
		
		glActiveTexture(GL_TEXTURE0);
		glEnable(GL_CULL_FACE);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		System.out.println("OpenGL: " + glGetString(GL_VERSION));
		
		loadShaders();
		matrixStack = new ArrayList<Matrix4f>();
		matrixStack.add(Matrix4f.identity());
	}
	
	private static void loadShaders() {
		Shader.StandardShader = new Shader("shaders/standard.vert", "shaders/standard.frag");
	}
	
	public static void pushMatrix() {
		matrixStack.add(matrixStack.get(matrixStack.size() - 1));
	}
	
	public static void multMatrix(Matrix4f m) {
		matrixStack.set(
				matrixStack.size() - 1,
				matrixStack.get(matrixStack.size() - 1).
				multiply(m)
				);
	}
	
	public static void popMatrix() {
		matrixStack.remove(matrixStack.size() - 1);
		if (matrixStack.size() == 0) {
			matrixStack.add(Matrix4f.identity());
		}
	}
	
	public static Matrix4f peekMatrix() {
		return matrixStack.get(matrixStack.size() - 1);
	}

	public static void printGlError() {
		int i = glGetError();
		if (i != GL_NO_ERROR) {
			System.out.println(i);
		}
	}
}
