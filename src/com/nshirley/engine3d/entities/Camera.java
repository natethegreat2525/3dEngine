package com.nshirley.engine3d.entities;

import com.nshirley.engine3d.math.Matrix4f;

public abstract class Camera {

	protected Matrix4f prMatrix, vwMatrix;
	
	public Camera() {
		prMatrix = Matrix4f.identity();
		vwMatrix = Matrix4f.identity();
	}
	public Matrix4f getPRMatrix() {
		return prMatrix;
	}
	
	public Matrix4f getVWMatrix() {
		return vwMatrix;
	}
	
	public Matrix4f getTotalMatrix() {
		return prMatrix.multiply(vwMatrix);
	}
	
}
