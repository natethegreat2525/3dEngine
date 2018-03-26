package com.nshirley.engine3d.entities;

import java.nio.FloatBuffer;

import com.nshirley.engine3d.graphics.Texture;
import com.nshirley.engine3d.graphics.VertexArray;
import com.nshirley.engine3d.math.Matrix4f;
import com.nshirley.engine3d.math.Vector4f;

public class Entity {

	protected Matrix4f mlMatrix;
	protected boolean mlMatrixDirty;
	protected FloatBuffer mlMatrixBuffer;
	
	protected VertexArray va;
	protected RenderStrategy rs;
	protected Texture tex;
	
	protected Vector4f color;
	
	public Entity(VertexArray va, Texture tex) {
		this(va, tex, null);
	}
	
	public Entity(VertexArray va, Texture tex, RenderStrategy rs) {
		if (rs == null) {
			rs = new StandardRenderStrategy();
		}
		this.va = va;
		this.rs = rs;
		this.tex = tex;
		this.color = new Vector4f(1, 1, 1, 1);
	}
	
	public void setColor(Vector4f col) {
		this.color = col;
	}
	
	public Vector4f getColor() {
		return color;
	}
	
	public void setModelMatrix(Matrix4f m) {
		this.mlMatrix = m;
	}
	
	public Matrix4f getModelMatrix() {
		return mlMatrix;
	}
	
	public VertexArray getVertexArray() {
		return va;
	}
	
	public void setVertexArray(VertexArray va) {
		this.va = va;
	}
	
	public void setRenderStrategy(RenderStrategy rs) {
		this.rs = rs;
	}

	public void render() {
		rs.render(this);
	}

	public Texture getTexture() {
		return tex;
	}
	
	public void free() {
		va.free();
	}
}
