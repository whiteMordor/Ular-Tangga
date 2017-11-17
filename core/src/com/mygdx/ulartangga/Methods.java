package com.mygdx.ulartangga;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Methods 
{
	public static TextureRegion[] transformTo1D(TextureRegion[][] tmp, int columns, int rows)
    {
        TextureRegion[] frames = new TextureRegion[columns * rows];

        int index = 0;
        for (int i=0; i<rows; i++)
        {
            for (int a=0; a<columns; a++)
            {
                frames[index++] = tmp[i][a];
            }
        }

        return frames;
    }
	
	public static final void print(Object obj)
	{
		System.out.println(obj);
	}
}
