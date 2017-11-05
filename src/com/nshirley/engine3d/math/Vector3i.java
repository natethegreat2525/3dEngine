package com.nshirley.engine3d.math;

public class Vector3i {

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		result = prime * result + z;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vector3i other = (Vector3i) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		if (z != other.z)
			return false;
		return true;
	}

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
	
	public static Vector3i add(Vector3i a, Vector3i b) {
		return new Vector3i(a.x + b.x, a.y + b.y, a.z + b.z);
	}
	
	public String toString() {
		return x + " " + y + " " + z;
	}

	public Vector3i mult(int val) {
		return new Vector3i(x*val, y*val, z*val);
	}
	
	public Vector3i clone() {
		return new Vector3i(x, y, z);
	}
	
}
