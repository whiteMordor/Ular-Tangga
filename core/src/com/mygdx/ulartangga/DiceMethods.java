package com.mygdx.ulartangga;

import java.util.concurrent.ThreadLocalRandom;

public class DiceMethods
{
	public static int randomizeDiceValue(int min, int max)
	{
		return ThreadLocalRandom.current().nextInt(min, max+1);
	}
}
