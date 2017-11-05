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

	public Vector3f normalize() {
		float mag = (float) Math.sqrt(x * x + y * y + z * z);
		
		return new Vector3f(x / mag, y / mag, z / mag);
	}
	
	public float mag() {
		return (float) Math.sqrt(x * x + y * y + z * z);
	}

	public float dot(Vector3f v) {
		return v.x*x + v.y*y + v.z*z;
	}

	public Vector3f add(Vector3f o) {
		return new Vector3f(x + o.x, y + o.y, z + o.z);
	}
	
	public Vector3i floor() {
		return new Vector3i((int) Math.floor(x), (int) Math.floor(y), (int) Math.floor(z));
	}

	public static Vector3f random(double min, double max) {
		double range = max - min;
		return new Vector3f(
				(float) (Math.random() * range + min),
				(float) (Math.random() * range + min),
				(float) (Math.random() * range + min));
	}
	
}
