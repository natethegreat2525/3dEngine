package com.nshirley.engine3d.math;

public class Vector3i {

	public int x, y, z;
	
	public Vector3i() {
		x = 0;
		y = 0;
		z = 0;
	}
	
	public Vector3i(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vector3i add(Vector3i a, Vector3i b) {
		return new Vector3i(a.x + b.x, a.y + b.y, a.z + b.z);
	}
	
	public String toString() {
		return x + " " + y + " " + z;
	}
	
}
