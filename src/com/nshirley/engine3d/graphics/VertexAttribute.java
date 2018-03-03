package com.nshirley.engine3d.graphics;

public class VertexAttribute {

	boolean isFloat;
	float[] dataf;
	int[] datai;
	
	public VertexAttribute(float[] data) {
		dataf = data;
		isFloat = true;
	}
	
	public VertexAttribute(int[] data) {
		datai = data;
		isFloat = false;
	}
}
