package com.tobalsa.starfighter;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;

public class SFBackground {

	private FloatBuffer vertexBuffer;
	private FloatBuffer textureBuffer;
	private ByteBuffer indexBuffer;

	private int[] textures = new int[1];
	private float vertices[] = {
			//X,Y,Z
			0.0f, 0.0f, 0.0f,
			1.0f, 0.0f, 0.0f,
			1.0f, 1.0f, 0.0f,
			0.0f, 1.0f, 0.0f,
	};
	private float texture[] = {
			0.0f, 0.0f,
			1.0f, 0.0f,
			1.0f, 1.0f,
			0.0f, 1.0f,
	};
	private byte indices[] = {
			0,1,2,
			0,2,3,
	};

	public SFBackground() {
		ByteBuffer byteBuf = ByteBuffer.allocateDirect(vertices.length * 4);//*4 xk se necesita 4 veces el espacio de un byte por ser floats
		byteBuf.order(ByteOrder.nativeOrder());
		vertexBuffer = byteBuf.asFloatBuffer();
		vertexBuffer.put(vertices);
		vertexBuffer.position(0);

		byteBuf = ByteBuffer.allocateDirect(texture.length * 4);
		byteBuf.order(ByteOrder.nativeOrder());
		textureBuffer = byteBuf.asFloatBuffer();
		textureBuffer.put(texture);
		textureBuffer.position(0);

		indexBuffer = ByteBuffer.allocateDirect(indices.length);
		indexBuffer.put(indices);
		indexBuffer.position(0);

	}
	
	//Se ejecutara 1vez para iniciar la textura
	public void loadTexture(GL10 gl, int texture, Context context) {
		InputStream imageStream = context.getResources().openRawResource(texture);
		Bitmap bitmap = null;

		try {
			bitmap = BitmapFactory.decodeStream(imageStream);	
		} catch (Exception e) {

		}finally {
			//Siempre limpiar el stream y cerrarlo
			try {
				imageStream.close();
				imageStream = null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		/*Puntero de texturas, trabaja como un diccionario, 1ºparam es el numero de texturas que tienes
		y el 3º param es 0 porque el array empieza en 0*/
		gl.glGenTextures(1, textures, 0);
		//Si se cargan 2 texturas juntas deberia haber dos lineas cada una cargando una posicion del array
		gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[0]);
		
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);
		//Permite que el juego tenga scroll en S y T
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S, GL10.GL_REPEAT);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T, GL10.GL_REPEAT);
		
		GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
		
		bitmap.recycle();
	}
	
	//Se ejecutara siempre que queramos pintar el bg
	public void draw(GL10 gl) {
		gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[0]);//Se carga la textura
		
		/*Decimos que no renderice los vertices que no se vean, puesto que al ser
		un juego en 2D todos los vertices estaran visible en todo momento*/
		gl.glFrontFace(GL10.GL_CCW);
		gl.glEnable(GL10.GL_CULL_FACE);
		gl.glCullFace(GL10.GL_BACK);
		
		//Habilita los estados de vertices y texturas, y carga los buffers en OpenGL
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
		gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, textureBuffer);
		
		//Se dibujan los elementos y se deshabilitado todo.
		gl.glDrawElements(GL10.GL_TRIANGLES, indices.length, GL10.GL_UNSIGNED_BYTE, indexBuffer);
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		gl.glDisable(GL10.GL_CULL_FACE);
	}


}
