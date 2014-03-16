package com.tobalsa.aprendiendoopengl;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Square {
	// Nuestros vertices
	private float vertices[] = {//Puntos del cuadrado
			-1.0f,  1.0f, 0.0f,  // 0, Noroeste
			-1.0f, -1.0f, 0.0f,  // 1, Suroeste
			1.0f, -1.0f, 0.0f,  // 2, Sureste
			1.0f,  1.0f, 0.0f,  // 3, Noreste
			};

	// Como conectamos los vertices
	private byte indices[] = { 
			0, 1, 2,
			0, 2, 3,
			};

	private FloatBuffer vertexBuffer;

	private ByteBuffer indexBuffer;

	public Square() {
		// Un float son 4bytes, por lo que multiplicamos el numero de vertices * 4.
		ByteBuffer byteBuffer = ByteBuffer.allocateDirect(vertices.length * 4);
		byteBuffer.order(ByteOrder.nativeOrder());
		vertexBuffer = byteBuffer.asFloatBuffer();
		vertexBuffer.put(vertices);
		vertexBuffer.position(0);
		
		//No funciona porque en el ejemplo usaba un array de shorts e intentando 
		//usar un array de bytes no funciona
		indexBuffer = ByteBuffer.allocateDirect(indices.length);
		indexBuffer.put(indices);
		indexBuffer.position(0);
	}

	public void draw(GL10 gl) {
		// Se dibuja en el sentido contrario de las ajugas del reloj.
		gl.glFrontFace(GL10.GL_CCW);
		/* Habilita el culling, eliminando la parte no visible de los poligonos
		y ahorrando por lo tanto recursos del procesador*/
		gl.glEnable(GL10.GL_CULL_FACE); 
		// Que caras se deben eliminar con el culling.
		gl.glCullFace(GL10.GL_BACK); 

		// Habilitamos el buffer de vertices para escritura que sera usado para renderizar.
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		// Se especifica la localizacion y el formato de la informacion del array de vertices.
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);

		gl.glDrawElements(GL10.GL_TRIANGLES, indices.length, GL10.GL_UNSIGNED_SHORT, indexBuffer);

		// Deshabilitamos todo aquello que hayamos habilitado.
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY); 
		gl.glDisable(GL10.GL_CULL_FACE);
	}

}
