package com.nshirley.engine3d.graphics;

import java.io.InputStream;
import java.nio.FloatBuffer;
import java.util.HashMap;
import java.util.Map;

import org.lwjgl.opengl.GL40;

import com.nshirley.engine3d.math.Matrix4f;
import com.nshirley.engine3d.math.Vector3f;
import com.nshirley.engine3d.math.Vector4f;
import com.nshirley.engine3d.utils.BufferUtils;
import com.nshirley.engine3d.utils.ShaderUtils;

import static org.lwjgl.opengl.GL20.*;

public class Shader {
	
	public static final int VERTEX_ATTRIB = 0;
	public static final int TCOORD_ATTRIB = 1;
	public static final int COLOR_ATTRIB = 2;
	
	public static Shader StandardShader;
		
	private boolean enabled = false;
	
	private final int ID;
	private Map<String, Integer> locationCache = new HashMap<String, Integer>();

	public Shader(String vertex, String fragment) {
		ID = ShaderUtils.load(vertex, fragment);
	}
	
	public Shader(InputStream vert, InputStream frag) {
		ID = ShaderUtils.load(vert, frag);
	}
		
	public int getUniform(String name) {
		if (locationCache.containsKey(name))
			return locationCache.get(name);
		
		int result = glGetUniformLocation(ID, name);
		
		if (result == -1) 
			System.err.println("Could not find uniform var: " + name);
		else
			locationCache.put(name, result);
		
		return result;
	}
	
	public void setUniform1i(String name, int value) {
		if (!enabled) enable();
		glUniform1i(getUniform(name), value);
	}
	
	public void setUniform1f(String name, float value) {
		if (!enabled) enable();
		glUniform1f(getUniform(name), value);
	}
	
	public void setUniform2f(String name, float x, float y) {
		if (!enabled) enable();
		glUniform2f(getUniform(name), x, y);
	}
	
	public void setUniform2d(String name, double x, double y) {
		if (!enabled) enable();
		GL40.glUniform2d(getUniform(name), x, y);
	}
	
	public void setUniform1d(String name, double x) {
		if (!enabled) enable();
		GL40.glUniform1d(getUniform(name), x);
	}
	
	public void setUniform3f(String name, float x, float y, float z) {
		if (!enabled) enable();
		glUniform3f(getUniform(name), x, y, z);
	}
	
	public void setUniform3f(String name, Vector3f vec) {
		if (!enabled) enable();
		glUniform3f(getUniform(name), vec.x, vec.y, vec.z);
	}
	
	public void setUniform4f(String name, float x, float y, float z, float w) {
		if (!enabled) enable();
		glUniform4f(getUniform(name), x, y, z, w);
	}
	
	public void setUniform4f(String name, Vector4f vec) {
		if (!enabled) enable();
		glUniform4f(getUniform(name), vec.x, vec.y, vec.z, vec.w);
	}
	
	public void setUniformMat4f(String name, Matrix4f matrix) {
		if (!enabled) enable();
		FloatBuffer buf = matrix.toFloatBuffer();
		glUniformMatrix4fv(getUniform(name), false, buf);
		BufferUtils.freeFloatBuffer(buf);
	}
	
	public void enable() {
		glUseProgram(ID);
		enabled = true;
	}
	
	public void disable() {
		glUseProgram(0);
		enabled = false;
	}
}
