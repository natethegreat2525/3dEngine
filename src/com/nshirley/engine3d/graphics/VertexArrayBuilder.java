package com.nshirley.engine3d.graphics;

import java.util.ArrayList;
import static org.lwjgl.opengl.GL15.*;

public class VertexArrayBuilder {

	private ArrayList<Vertex> verts;
	private ArrayList<Triangle> tris;
	
	private int[] positions;
	private int[] sizes;
	
	public VertexArrayBuilder(int[] positions, int[] sizes) {
		this.positions = positions;
		this.sizes = sizes;
		if (positions.length != sizes.length) {
			throw new IllegalArgumentException("Number of positions and sizes does not match up");
		}
		verts = new ArrayList<Vertex>();
		tris = new ArrayList<Triangle>();
	}
	
	public void concatenate(VertexArrayBuilder vab) {
		int startIdx = verts.size();
		verts.addAll(vab.verts);
		for (Triangle t : vab.tris) {
			tris.add(new Triangle(t.a + startIdx, t.b + startIdx, t.c + startIdx));
		}
	}
	
	public void add(Vertex v) {
		verts.add(v);
	}
	
	public void addTriangle(int[] t) {
		tris.add(new Triangle(t));
	}
	
	public void addTriangle(int a, int b, int c) {
		tris.add(new Triangle(a, b, c));
	}
	
	public VertexArray build() {
		return build(GL_STATIC_DRAW);
	}
	
	public VertexArray build(int drawMode) {
		if (verts.size() == 0) {
			return new VertexArray(0);
		}
		Attribute[] a = new Attribute[sizes.length + 1];
		Vertex firstV = verts.get(0);
		for (int i = 0; i < sizes.length; i++) {
			if (firstV.attributes[i].isFloat) {
				int dataLen = firstV.attributes[i].dataf.length;
				int len = dataLen * verts.size();
				//float
				float[] attribArray = new float[len];
				int index = 0;
				for (Vertex v : verts) {
					for (float val : v.attributes[i].dataf) {
						attribArray[index] = val;
						index++;
					}
				}
				a[i] = new Attribute(positions[i], sizes[i], attribArray);
			} else {
				int dataLen = firstV.attributes[i].datai.length;
				int len = dataLen * verts.size();
				//int
				int[] attribArray = new int[len];
				int index = 0;
				for (Vertex v : verts) {
					for (int val : v.attributes[i].datai) {
						attribArray[index] = val;
						index++;
					}
				}
				a[i] = new Attribute(positions[i], sizes[i], attribArray);
			}
		}
		
		int[] indices = new int[tris.size() * 3];
		int index = 0;
		for (Triangle t : tris) {
			indices[index] = t.a;
			index++;
			indices[index] = t.b;
			index++;
			indices[index] = t.c;
			index++;
		}
		if (indices.length > 0)
			a[a.length - 1] = new Attribute(indices);
		return new VertexArray(drawMode, a);
	}

	public int getNumVerts() {
		return verts.size();
	}
}

class Triangle {
	int a, b, c;
	public Triangle(int a, int b, int c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}
	
	public Triangle(int[] abc) {
		this.a = abc[0];
		this.b = abc[1];
		this.c = abc[2];
	}
}