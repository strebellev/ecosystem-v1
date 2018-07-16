

import mechanisme.World;
import processing.core.PApplet;

public class Game extends PApplet{

	/////////////////V3/////////////////////////
	
	public static void main(String[] args){
		PApplet.main("Game");
	}
	
	public void settings(){
		size(1200,900);
	}
	
	
	World world;
	
	public void setup(){
		world = new World(this);
		
	}
	
	
	public void draw(){
		background(255);/// 255 blanc 0 noir
		
		world.run();
		
		
	}
	
	
}
