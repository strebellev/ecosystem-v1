package objects;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;

public class Tree2 {
	PApplet parent;
	
	ArrayList<PVector> tree2;
	public PVector position;
	
	public Tree2(){
		tree2 = new ArrayList<PVector>();
	}
	
	
	@SuppressWarnings("deprecation")
	public void add(PVector l){
		tree2.add(l.get());
	}
	
	
	public void run(PApplet p){
		for(PVector t : tree2){
			p.stroke(1);
			p.fill(0,102,51);
			p.ellipse(t.x, t.y, 30, 30);
			p.fill(102,51,0);
			p.ellipse(t.x, t.y, 10, 10);
		}
	}
	
	public ArrayList<PVector> getTree2(){
		return tree2;
	}
}
