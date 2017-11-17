package com.mygdx.ulartangga;

import java.util.concurrent.ThreadLocalRandom;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class DaduScreen extends InputAdapter implements Screen
{
	//Dice Animation
	private float stateTime = 1.0f;
	private final int DICE_TEXTURE_WIDTH = 3;
	private final int DICE_TEXTURE_HEIGHT = 3;
	private Texture texturedice;
	private TextureRegion[][] diceFramesRaw;
	private TextureRegion[] diceFrames;
	private Animation[] diceWalkAnimation;
	private TextureRegion diceWalkFrame;
	
	//Batch
	private SpriteBatch batch;
	
	//Camera
	private OrthographicCamera camera;
	private final Vector2 CAMERA_SIZE = new Vector2(1366, 768);
	
	//Dice Roll
	private int count;
	private int lastIndex;
	private float rolling;
	private boolean start;
	private float diceCounter;

	@Override
	public void show()
	{
		// Initialize dice animation
		this.texturedice = new Texture(Gdx.files.internal("dice.png"));
		this.diceFramesRaw = TextureRegion.split(this.texturedice,
													this.texturedice.getWidth() / this.DICE_TEXTURE_WIDTH,
													this.texturedice.getHeight() / this.DICE_TEXTURE_HEIGHT);
		this.diceFrames = Methods.transformTo1D(this.diceFramesRaw,  
													this.DICE_TEXTURE_WIDTH,
													this.DICE_TEXTURE_WIDTH);
		this.diceWalkAnimation = new Animation[this.DICE_TEXTURE_WIDTH];
		//Separating the frames
		for (int i=0; i<this.diceWalkAnimation.length; i++)
		{
			this.diceWalkAnimation[i] = new Animation (1/60f, this.diceFramesRaw[i]);
		}
		
		//Initialize Camera
		this.camera = new OrthographicCamera();
		this.camera.setToOrtho(false,
								this.CAMERA_SIZE.x, 
								this.CAMERA_SIZE.y);
		
		//batch
		batch = new SpriteBatch();
		
		//Set touch screen sensor
		Gdx.input.setInputProcessor(this);
		
		//Dice Roll
		this.count = 0;
		this.lastIndex = 0;
		this.rolling = 20;
		this.start = false;
	}

	@Override
	public void render(float delta)
	{
		this.batch.setProjectionMatrix(this.camera.combined);
		Gdx.gl.glClearColor(1,  1,  1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		//Rendering Animation	
		if (Gdx.input.isButtonPressed(Buttons.LEFT))
		{
			this.start = true;
			this.diceCounter = 0f;
		}
		
		if (this.start)
		{
			this.diceCounter += Gdx.graphics.getDeltaTime() * 200;
					
			if (this.diceCounter >= 100f)
			{
				this.stateTime += Gdx.graphics.getDeltaTime() * 0.1f;
				this.lastIndex = ThreadLocalRandom.current().nextInt(1,  7);
				Methods.print(this.lastIndex);
				this.count++;
				this.diceCounter = 0f;
			}
			
			if (this.count >= this.rolling)
			{
				this.start = false;
			}
		}
		
//		this.diceWalkFrame = (TextureRegion)this.diceWalkAnimation[0].getKeyFrame(this.stateTime, true);
	
		batch.begin();
//		batch.draw(this.diceWalkFrame, this.CAMERA_SIZE.x/2, this.CAMERA_SIZE.y/2, 0, 0, this.diceWalkFrame.getRegionWidth(), this.diceWalkFrame.getRegionHeight(), 1f, 1f, 0f);
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	
}
