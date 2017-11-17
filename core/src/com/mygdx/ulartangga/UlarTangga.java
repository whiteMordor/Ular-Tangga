package com.mygdx.ulartangga;

import com.badlogic.gdx.Game;

public class UlarTangga extends Game 
{
	
	@Override
	public void create()
	{
		// TODO Auto-generated method stub
		this.setScreen(new MainMenu(this));
	}
	
	
}
