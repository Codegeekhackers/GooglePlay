package com.lan.googleplay.uitls;

import java.util.Random;

import android.graphics.Color;

public class ColorUtil {
	public static int getRandomColor() {
		Random random = new Random();
		int red = random.nextInt(180) + 50;
		int green = random.nextInt(180) + 50;
		int blue = random.nextInt(180) + 50;
		return Color.rgb(red, green, blue);
	}
}
