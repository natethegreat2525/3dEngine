package com.nshirley.engine3d.entities;

import com.nshirley.engine3d.math.Matrix4f;
import com.nshirley.engine3d.math.Vector3f;

public class Camera3d extends Camera {

	private Matrix4f pos;
	private Matrix4f rot;
	private Vector3f posVec, rotVec;
	
	public Camera3d(float fov, float width, float height, float near, float far) {
		pos = Matrix4f.identity();
		rot = Matrix4f.identity();
		prMatrix = Matrix4f.perspective(width, height, near, far, fov);
	}
	
	private void recalcVWMatrix() {
		super.vwMatrix = rot.multiply(pos);
	}
	
	public void setPosition(Vector3f vec) {
		pos = Matrix4f.translate(vec.mult(-1));
		recalcVWMatrix();
		posVec = vec.clone();
	}
	
	public void setRotation(Vector3f vec) {
		Matrix4f x, y, z;
		z = Matrix4f.rotateZ(vec.z);
		y = Matrix4f.rotateY(vec.y);
		x = Matrix4f.rotateX(vec.x);
		rot = z.multiply(x.multiply(y));
		recalcVWMatrix();
		rotVec = vec.clone();
	}
	
	public Vector3f getPosition() { return posVec; }
	public Vector3f getRotation() { return rotVec; }
}
