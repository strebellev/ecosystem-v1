package objects;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

public class Food2 {
	PApplet parent;
	ArrayList<PVector> food2;
	ArrayList<Tree2> tree2;
	float food2live;
	
	public Food2(int nombFood2, PApplet p){
		parent = p;
		food2 = new ArrayList<PVector>();
		food2live = 255;
	}
	
	@SuppressWarnings("deprecation")
	public void add(PVector l){
		food2.add(l.get());
	}
	
	public void grow(Tree2 t){
		ArrayList<PVector> tree2 = t.getTree2();
		for(int i = 0; i < tree2.size(); i++){
			if(parent.random(1) < 0.001){
				int nombfood2 = 1;
				for(int a = 0; a < nombfood2; a++){
					food2.add(new PVector(parent.random(tree2.get(i).x -50, tree2.get(i).x +50), parent.random(tree2.get(i).y-50, tree2.get(i).y +50)));
				}
			}
		}	
	}
	
	
	public void run(PApplet p){
		for(PVector f : food2){
			parent.rectMode(PConstants.CENTER);
			parent.stroke(0,food2live);
			parent.fill(153,153,0,food2live);
			parent.rect(f.x, f.y, 8, 8);
		}
	}
	
	public void growOld(Food2 f){
		ArrayList<PVector> food2 = f.getFood2();
		for(int i = food2.size()-1; i >= 0; i--){
			food2live -= 0.1;
		}
	}
	
	public ArrayList<PVector> getFood2(){
		return food2;
	}
	
	public boolean rotten(){
		if(food2live < 0.0){
			return true;
		}
		else{
			return false;
		}
	}
}
