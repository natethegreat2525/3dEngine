package com.nshirley.engine3d.math;

public class Vector3f {

	public float x, y, z;
	
	public Vector3f() {
		x = 0;
		y = 0;
		z = 0;
	}
	
	public Vector3f(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vector3f clone() {
		return new Vector3f(x, y, z);
	}

	public Vector3f mult(float i) {
		return new Vector3f(x * i, y * i, z * i);
	}
	
	public String toString() {
		return String.format("(%.2f, %.2f, %.2f)", x, y, z);
	}
	
}
