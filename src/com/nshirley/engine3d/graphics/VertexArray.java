package com.nshirley.engine3d.graphics;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import com.nshirley.engine3d.utils.BufferUtils;

public class VertexArray {

	private int count;
	private Attribute[] attributes;
	private int ibo;
	private boolean freed;
	
	private int[] buffers;
	
	public VertexArray(int count) {
		this.count = count;
		attributes = new Attribute[0];
		build(GL_STATIC_DRAW);
	}
	
	public VertexArray(Attribute ...attributes) {
		this.attributes = attributes;
		this.build(GL_STATIC_DRAW);
	}
	
	public VertexArray(int drawMethod, Attribute ...attributes) {
		this.attributes = attributes;
		this.build(drawMethod);
	}
	
	public void build(int drawMethod) {
		buffers = new int[attributes.length + 1];
		int vao = glGenVertexArrays();
		buffers[attributes.length] = vao;
		
		glBindVertexArray(vao);
		
		for (int i = 0; i < attributes.length; i++) {
			Attribute a = attributes[i];
			if (a == null)
				continue;
			if (a.isIndexAttrib) {
				ibo = glGenBuffers();
				glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
				glBufferData(GL_ELEMENT_ARRAY_BUFFER, BufferUtils.createIntBuffer(a.indexData), drawMethod);
				count = a.indexData.length;
				buffers[i] = ibo;
			} else {
				if (a.isFloat) {
					int bo = glGenBuffers();
					glBindBuffer(GL_ARRAY_BUFFER, bo);
					glBufferData(GL_ARRAY_BUFFER, BufferUtils.createFloatBuffer(a.dataf), drawMethod);
					glVertexAttribPointer(a.position, a.size, GL_FLOAT, false, 0, 0);
					glEnableVertexAttribArray(a.position);
					buffers[i] = bo;
				} else {
					int bo = glGenBuffers();
					glBindBuffer(GL_ARRAY_BUFFER, bo);
					glBufferData(GL_ARRAY_BUFFER, BufferUtils.createIntBuffer(a.datai), drawMethod);
					glVertexAttribPointer(a.position, a.size, GL_INT, false, 0, 0);
					glEnableVertexAttribArray(a.position);
					buffers[i] = bo;
				}
			}
		}
		
		if (ibo == 0 && attributes.length > 0) {
			count = attributes[0].getCount();
		}
		glBindVertexArray(0);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
	}
	
	public void bind() {
		glBindVertexArray(buffers[buffers.length - 1]);
		if (ibo > 0) {
			glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
		}
	}
	
	public void unbind() {
		glBindVertexArray(0);
		if (ibo > 0)
			glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
	}
	
	public void draw() {
		if (ibo > 0)
			glDrawElements(GL_TRIANGLES, count, GL_UNSIGNED_INT, 0);
		else
			glDrawArrays(GL_TRIANGLES, 0, count);
	}
	
	public void drawPoints() {
		glDrawArrays(GL_POINTS, 0, count);
	}
	
	public void render() {
		bind();
		draw();
	}
	
	public void renderPoints() {
		bind();
		drawPoints();
	}
	
	public void free() {
		if (freed == true) {
			System.out.println("Double free!");
		}
		freed = true;
		glBindVertexArray(buffers[buffers.length - 1]);
		for (int i = 0; i < buffers.length - 1; i++) {
			glDeleteBuffers(buffers[i]);
		}
		glDeleteVertexArrays(buffers[buffers.length - 1]);
	}
}
