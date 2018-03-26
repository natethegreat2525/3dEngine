package com.nshirley.engine3d.entities;

import com.nshirley.engine3d.N3D;
import com.nshirley.engine3d.graphics.Shader;
import com.nshirley.engine3d.graphics.Texture;
import com.nshirley.engine3d.math.Vector3f;

public class StandardRenderStrategy implements RenderStrategy {

	@Override
	public void render(Entity e) {
		Shader.StandardShader.enable();
		Texture tex = e.getTexture();
		if (tex != null) {
			tex.bind(0);
			Shader.StandardShader.setUniform1i("tex", 0);
		}
		Shader.StandardShader.setUniformMat4f("vw_matrix", N3D.peekMatrix());
		Shader.StandardShader.setUniformMat4f("ml_matrix", e.getModelMatrix());
		Shader.StandardShader.setUniformMat4f("norm_matrix", e.getModelMatrix().transpose().inverse());
		Shader.StandardShader.setUniform3f("sunLight.vColor", new Vector3f(1, 1, 1));
		Shader.StandardShader.setUniform3f("sunLight.vDirection", new Vector3f(-.3f, -1f, -.3f));
		Shader.StandardShader.setUniform1f("sunLight.fAmbientIntensity", .5f);
		Shader.StandardShader.setUniform4f("colorOverride", e.getColor());

		e.getVertexArray().render();
		//Debugging error print
		//N3D.printGlError();
	}

}
