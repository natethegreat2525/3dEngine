package com.nshirley.engine3d.entities.shapes;

import com.nshirley.engine3d.graphics.Vertex;
import com.nshirley.engine3d.graphics.VertexArray;
import com.nshirley.engine3d.graphics.VertexArrayBuilder;
import com.nshirley.engine3d.graphics.VertexAttribute;

public class Shape {

	public static VertexArray cube() {
		VertexArrayBuilder vab = new VertexArrayBuilder(
				new int[] { 0, 1, 2 },	//positions
				new int[] { 3, 2, 3 }	//sizes
				);
		
		vab.add(
				new Vertex(
						new VertexAttribute(new float[] {1, 1, 1}),
						new VertexAttribute(new float[] {1, 1}),
						new VertexAttribute(new float[] {1, 1, 1})
				));
		
		vab.add(
				new Vertex(
						new VertexAttribute(new float[] {-1, 1, 1}),
						new VertexAttribute(new float[] {0, 1}),
						new VertexAttribute(new float[] {-1, 1, 1})
				));
		
		vab.add(
				new Vertex(
						new VertexAttribute(new float[] {-1, -1, 1}),
						new VertexAttribute(new float[] {0, 0}),
						new VertexAttribute(new float[] {-1, -1, 1})
				));
		
		vab.add(
				new Vertex(
						new VertexAttribute(new float[] {1, -1, 1}),
						new VertexAttribute(new float[] {1, 0}),
						new VertexAttribute(new float[] {1, -1, 1})
				));
		
		vab.add(
				new Vertex(
						new VertexAttribute(new float[] {1, 1, -1}),
						new VertexAttribute(new float[] {1, 0}),
						new VertexAttribute(new float[] {1, 1, -1})
				));
		
		vab.add(
				new Vertex(
						new VertexAttribute(new float[] {-1, 1, -1}),
						new VertexAttribute(new float[] {0, 0}),
						new VertexAttribute(new float[] {-1, 1, -1})
				));
		
		vab.add(
				new Vertex(
						new VertexAttribute(new float[] {-1, -1, -1}),
						new VertexAttribute(new float[] {0, 1}),
						new VertexAttribute(new float[] {-1, -1, -1})
				));
		
		vab.add(
				new Vertex(
						new VertexAttribute(new float[] {1, -1, -1}),
						new VertexAttribute(new float[] {1, 1}),
						new VertexAttribute(new float[] {1, -1, -1})
				));
		
		//front
		vab.addTriangle(0, 1, 2);
		vab.addTriangle(0, 2, 3);
		
		//back
		vab.addTriangle(4, 6, 5);
		vab.addTriangle(4, 7, 6);
		
		//top
		vab.addTriangle(1, 0, 4);
		vab.addTriangle(4, 5, 1);
		
		//bottom
		vab.addTriangle(3, 2, 6);
		vab.addTriangle(6, 7, 3);
		
		//left1562
		vab.addTriangle(1, 5, 6);
		vab.addTriangle(1, 6, 2);
		
		//left1562
		vab.addTriangle(0, 7, 4);
		vab.addTriangle(0, 3, 7);
		
		return vab.build();
	}
}
