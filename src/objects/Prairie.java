package objects;

import processing.core.PApplet;
import processing.core.PConstants;

public class Prairie {
	PApplet parent;
	
	public Prairie(PApplet p){
		parent = p;
	}
	
	public void run(PApplet p){
		parent.rectMode(PConstants.CENTER);
		parent.noStroke();
		parent.fill(0,153,0,25);
		parent.rect(600, 450, 900, 600);
	}
}
