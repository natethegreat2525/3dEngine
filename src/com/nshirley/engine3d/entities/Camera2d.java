package com.nshirley.engine3d.entities;

import com.nshirley.engine3d.math.Matrix4f;
import com.nshirley.engine3d.math.Vector2f;
import com.nshirley.engine3d.math.Vector3f;

public class Camera2d extends Camera {

	private Vector2f pos;
	private float rot;
	
	public Camera2d(float width, float height, float near, float far) {
		pos = new Vector2f(0, 0);
		rot = 0;
		prMatrix = Matrix4f.orthographic(-width/2, width/2, height/2, -height/2, near, far);
	}
	
	private void recalcVWMatrix() {
		Matrix4f posM = Matrix4f.translate(new Vector3f(pos.x, pos.y, 0));
		Matrix4f rotM = Matrix4f.rotateZ(rot);
		super.vwMatrix = posM.multiply(rotM);
	}
	
	public void setPosition(Vector2f vec) {
		pos = vec;
		recalcVWMatrix();
	}
	
	public void setRotation(float rot) {
		this.rot = rot;
		recalcVWMatrix();
	}
}
