package com.nshirley.engine3d.math;

public class Vector4f {

	public float x, y, z, w;
	
	public Vector4f() {
		x = 0;
		y = 0;
		z = 0;
		w = 0;
	}
	
	public Vector4f(float x, float y, float z, float w) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}
	
	public Vector4f clone() {
		return new Vector4f(x, y, z, w);
	}

	public Vector4f mult(float i) {
		return new Vector4f(x * i, y * i, z * i, w * i);
	}
	
	public String toString() {
		return String.format("(%.2f, %.2f, %.2f, %.2f)", x, y, z, w);
	}

	public Vector4f normalize() {
		float mag = (float) Math.sqrt(x * x + y * y + z * z + w * w);
		
		return new Vector4f(x / mag, y / mag, z / mag, w / mag);
	}
	
	public float mag() {
		return (float) Math.sqrt(x * x + y * y + z * z + w * w);
	}

	public float dot(Vector4f v) {
		return v.x*x + v.y*y + v.z*z + v.w*w;
	}

	public Vector4f add(Vector4f o) {
		return new Vector4f(x + o.x, y + o.y, z + o.z, w + o.w);
	}
	

	public static Vector4f random(double min, double max) {
		double range = max - min;
		return new Vector4f(
				(float) (Math.random() * range + min),
				(float) (Math.random() * range + min),
				(float) (Math.random() * range + min),
				(float) (Math.random() * range + min));
	}
	
}
