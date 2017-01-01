package com.nshirley.engine3d.math;

import java.nio.FloatBuffer;

import com.nshirley.engine3d.utils.BufferUtils;


public class Matrix4f {

	public final int SIZE = 4 * 4;
	public float[] elements = new float[SIZE];
	
	public Matrix4f() {
		
		
	}
	
	public Matrix4f(float[] e) {
		elements = e;
	}
	
	public static Matrix4f identity() {
		Matrix4f result = new Matrix4f();
		
		result.elements[0 + 0*4] = 1;
		result.elements[1 + 1*4] = 1;
		result.elements[2 + 2*4] = 1;
		result.elements[3 + 3*4] = 1;
		
		return result;
	}
	
	public static Matrix4f orthographic(float left, float right, float bottom, float top, float near, float far) {
		Matrix4f result = identity();
		
		result.elements[0 + 0 * 4] = 2.0f / (right - left);
		result.elements[1 + 1 * 4] = 2.0f / (top - bottom);
		result.elements[2 + 2 * 4] = 2.0f / (far - near);
		
		result.elements[0 + 3 * 4] = (left + right) / (left - right);
		result.elements[1 + 3 * 4] = (bottom + top) / (bottom - top);
		result.elements[2 + 3 * 4] = (near + far) / (near - far);
		
		result.elements[3 + 3 * 4] = 1;
		
		return result;
	}
	
	public static Matrix4f perspective(float width, float height, float near, float far, float fov) {
		float r = (float) (Math.tan(fov/2) * near);
		float t = r * height / width;
		float v00 = near / r;
		float v11 = near/t;
		float v22 = (far + near)/(near - far);
		float v23 = 2 * far * near / (near - far);
		float v32 = -1;
		
		Matrix4f result = new Matrix4f();
		
		result.elements[0 + 0 * 4] = v00;
		result.elements[1 + 1 * 4] = v11;
		result.elements[2 + 2 * 4] = v22;
		result.elements[2 + 3 * 4] = v23;
		result.elements[3 + 2 * 4] = v32;
		
		return result;
	}
	
	public static Matrix4f translate(Vector3f vec) {
		Matrix4f result = identity();
		result.elements[0 + 3 * 4] = vec.x;
		result.elements[1 + 3 * 4] = vec.y;
		result.elements[2 + 3 * 4] = vec.z;
		
		return result;
	}
	
	public static Matrix4f rotateX(float angle) {
		Matrix4f result = identity();
		float r = (float) Math.toRadians(angle);
		float cos = (float) Math.cos(r);
		float sin = (float) Math.sin(r);
		
		result.elements[1 + 1 * 4] = cos;
		result.elements[2 + 1 * 4] = sin;

		result.elements[1 + 2 * 4] = -sin;
		result.elements[2 + 2 * 4] = cos;

		return result;
	}
	
	public static Matrix4f rotateY(float angle) {
		Matrix4f result = identity();
		float r = (float) Math.toRadians(angle);
		float cos = (float) Math.cos(r);
		float sin = (float) Math.sin(r);
		
		result.elements[0 + 0 * 4] = cos;
		result.elements[2 + 0 * 4] = -sin;

		result.elements[0 + 2 * 4] = sin;
		result.elements[2 + 2 * 4] = cos;

		return result;
	}
	
	public static Matrix4f rotateZ(float angle) {
		Matrix4f result = identity();
		float r = (float) Math.toRadians(angle);
		float cos = (float) Math.cos(r);
		float sin = (float) Math.sin(r);
		
		result.elements[0 + 0 * 4] = cos;
		result.elements[1 + 0 * 4] = sin;

		result.elements[0 + 1 * 4] = -sin;
		result.elements[1 + 1 * 4] = cos;

		return result;
	}
	
	public Matrix4f multiply(Matrix4f m) {
		Matrix4f result = new Matrix4f();
		
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				float sum = 0;
				for (int e = 0; e < 4; e++) {
					sum += this.elements[x + e * 4] * m.elements[e + y * 4];
				}
				result.elements[x + y * 4] = sum;
			}
		}
		return result;
	}
	
	public Matrix4f inverse() {
		float[] inv = new float[16], invOut = new float[16];
		float det = 0;
		int i = 0;
		float[] m = elements;
		
		inv[0] = m[5]  * m[10] * m[15] - 
		         m[5]  * m[11] * m[14] - 
		         m[9]  * m[6]  * m[15] + 
		         m[9]  * m[7]  * m[14] +
		         m[13] * m[6]  * m[11] - 
		         m[13] * m[7]  * m[10];
		
		inv[4] = -m[4]  * m[10] * m[15] + 
		          m[4]  * m[11] * m[14] + 
		          m[8]  * m[6]  * m[15] - 
		          m[8]  * m[7]  * m[14] - 
		          m[12] * m[6]  * m[11] + 
		          m[12] * m[7]  * m[10];
		
		inv[8] = m[4]  * m[9] * m[15] - 
		         m[4]  * m[11] * m[13] - 
		         m[8]  * m[5] * m[15] + 
		         m[8]  * m[7] * m[13] + 
		         m[12] * m[5] * m[11] - 
		         m[12] * m[7] * m[9];
		
		inv[12] = -m[4]  * m[9] * m[14] + 
		           m[4]  * m[10] * m[13] +
		           m[8]  * m[5] * m[14] - 
		           m[8]  * m[6] * m[13] - 
		           m[12] * m[5] * m[10] + 
		           m[12] * m[6] * m[9];
		
		inv[1] = -m[1]  * m[10] * m[15] + 
		          m[1]  * m[11] * m[14] + 
		          m[9]  * m[2] * m[15] - 
		          m[9]  * m[3] * m[14] - 
		          m[13] * m[2] * m[11] + 
		          m[13] * m[3] * m[10];
		
		inv[5] = m[0]  * m[10] * m[15] - 
		         m[0]  * m[11] * m[14] - 
		         m[8]  * m[2] * m[15] + 
		         m[8]  * m[3] * m[14] + 
		         m[12] * m[2] * m[11] - 
		         m[12] * m[3] * m[10];
		
		inv[9] = -m[0]  * m[9] * m[15] + 
		          m[0]  * m[11] * m[13] + 
		          m[8]  * m[1] * m[15] - 
		          m[8]  * m[3] * m[13] - 
		          m[12] * m[1] * m[11] + 
		          m[12] * m[3] * m[9];
		
		inv[13] = m[0]  * m[9] * m[14] - 
		          m[0]  * m[10] * m[13] - 
		          m[8]  * m[1] * m[14] + 
		          m[8]  * m[2] * m[13] + 
		          m[12] * m[1] * m[10] - 
		          m[12] * m[2] * m[9];
		
		inv[2] = m[1]  * m[6] * m[15] - 
		         m[1]  * m[7] * m[14] - 
		         m[5]  * m[2] * m[15] + 
		         m[5]  * m[3] * m[14] + 
		         m[13] * m[2] * m[7] - 
		         m[13] * m[3] * m[6];
		
		inv[6] = -m[0]  * m[6] * m[15] + 
		          m[0]  * m[7] * m[14] + 
		          m[4]  * m[2] * m[15] - 
		          m[4]  * m[3] * m[14] - 
		          m[12] * m[2] * m[7] + 
		          m[12] * m[3] * m[6];
		
		inv[10] = m[0]  * m[5] * m[15] - 
		          m[0]  * m[7] * m[13] - 
		          m[4]  * m[1] * m[15] + 
		          m[4]  * m[3] * m[13] + 
		          m[12] * m[1] * m[7] - 
		          m[12] * m[3] * m[5];
		
		inv[14] = -m[0]  * m[5] * m[14] + 
		           m[0]  * m[6] * m[13] + 
		           m[4]  * m[1] * m[14] - 
		           m[4]  * m[2] * m[13] - 
		           m[12] * m[1] * m[6] + 
		           m[12] * m[2] * m[5];
		
		inv[3] = -m[1] * m[6] * m[11] + 
		          m[1] * m[7] * m[10] + 
		          m[5] * m[2] * m[11] - 
		          m[5] * m[3] * m[10] - 
		          m[9] * m[2] * m[7] + 
		          m[9] * m[3] * m[6];
		
		inv[7] = m[0] * m[6] * m[11] - 
		         m[0] * m[7] * m[10] - 
		         m[4] * m[2] * m[11] + 
		         m[4] * m[3] * m[10] + 
		         m[8] * m[2] * m[7] - 
		         m[8] * m[3] * m[6];
		
		inv[11] = -m[0] * m[5] * m[11] + 
		           m[0] * m[7] * m[9] + 
		           m[4] * m[1] * m[11] - 
		           m[4] * m[3] * m[9] - 
		           m[8] * m[1] * m[7] + 
		           m[8] * m[3] * m[5];
		
		inv[15] = m[0] * m[5] * m[10] - 
		          m[0] * m[6] * m[9] - 
		          m[4] * m[1] * m[10] + 
		          m[4] * m[2] * m[9] + 
		          m[8] * m[1] * m[6] - 
		          m[8] * m[2] * m[5];
		
		det = m[0] * inv[0] + m[1] * inv[4] + m[2] * inv[8] + m[3] * inv[12];
		
		if (det == 0)
		    return null;
		
		det = 1.0f / det;
		
		for (i = 0; i < 16; i++)
		    invOut[i] = inv[i] * det;
		
		return new Matrix4f(invOut);
	}
	
	public Matrix4f transpose() {
		float[] o = new float[16];
		float[] m = elements;
		o[0] = m[0];
		o[1] = m[4];
		o[2] = m[8];
		o[3] = m[12];
		
		o[4] = m[1];
		o[5] = m[5];
		o[6] = m[9];
		o[7] = m[13];
		
		o[8] = m[2];
		o[9] = m[6];
		o[10] = m[10];
		o[11] = m[14];
		
		o[12] = m[3];
		o[13] = m[7];
		o[14] = m[11];
		o[15] = m[15];
		return new Matrix4f(o);
	}
	
	public FloatBuffer toFloatBuffer() {
		return BufferUtils.createFloatBuffer(elements);
	}
	
	public String toString() {
		String str = "";
		for (int i = 0; i < 16; i++) {
			str += elements[i] + "\t";
			if ((i + 1) % 4 == 0) {
				str += '\n';
			}
		}
		return str;
	}
}
