package com.nshirley.engine3d.graphics;

public class Attribute {

	public int position;
	public int size;
	public boolean isFloat;
	
	public float[] dataf;
	public int[] datai;
			
	
	public boolean isIndexAttrib;
	public int[] indexData;
	
	public Attribute(int position, int size, float[] data) {
		this.position = position;
		this.size = size;
		this.dataf = data;
		this.isFloat = true;
	}
	
	public Attribute(int position, int size, int[] data) {
		this.position = position;
		this.size = size;
		this.datai = data;
		this.isFloat = false;
	}
	
	public Attribute(int[] data) {
		this.indexData = data;
		this.isIndexAttrib = true;
	}

	public int getCount() {
		if (isFloat) {
			return dataf.length / size;
		}
		return datai.length / size;
	}
}
