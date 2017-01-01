package com.nshirley.engine3d.utils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class BufferUtils {

	private BufferUtils() {
		
	}
	
	private static HashMap<Integer, ArrayList<FloatBuffer>> floatBufferQueue;
	
	static {
		floatBufferQueue = new HashMap<Integer, ArrayList<FloatBuffer>>();
	}
	
	private static FloatBuffer genFloatBuffer(int size) {
		ArrayList<FloatBuffer> bufferList = floatBufferQueue.get(size);
		if (bufferList != null && bufferList.size() > 0) {
			FloatBuffer buffer = bufferList.remove(bufferList.size() - 1);
			return buffer;
		}
		return ByteBuffer.allocateDirect(size).order(ByteOrder.nativeOrder()).asFloatBuffer();
	}
	
	public static void freeFloatBuffer(FloatBuffer buffer) {
		int size = buffer.capacity() * 4;
		buffer.rewind();
		ArrayList<FloatBuffer> bufferList = floatBufferQueue.get(size);
		if (bufferList == null) {
			bufferList = new ArrayList<FloatBuffer>();
			floatBufferQueue.put(size, bufferList);
		}
		bufferList.add(buffer);
	}
	
	
	public static ByteBuffer createByteBuffer(byte[] array) {
		ByteBuffer result = ByteBuffer.allocateDirect(array.length).order(ByteOrder.nativeOrder());
		result.put(array).flip();
		return result;
	}
	
	public static FloatBuffer createFloatBuffer(float[] array) {
		FloatBuffer result = genFloatBuffer(array.length << 2);
		result.put(array).flip();
		return result;
	}
	
	public static IntBuffer createIntBuffer(int[] array) {
		IntBuffer result = ByteBuffer.allocateDirect(array.length << 2).order(ByteOrder.nativeOrder()).asIntBuffer();
		result.put(array).flip();
		return result;
	}
}
