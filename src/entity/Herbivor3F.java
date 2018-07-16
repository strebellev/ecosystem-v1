package entity;

import java.util.ArrayList;
import java.util.List;

import mechanisme.DNA2;
import objects.Food2;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

public class Herbivor3F {
	PApplet parent;
	ArrayList<PVector> herb3f;
	
	
	public PVector position;
	DNA2 dna2;
	float xoff;
	float yoff;
	
	//////variable de reproduction
	float gestation;
	boolean pregnant = false;
	ArrayList<Herbivor3M> herb3m;
	
	////////////variables genetiques
	float health;
	float r; ///la taille
	float maxspeed;
	
	int lastChildTime = -1;
	
	@SuppressWarnings("deprecation")
	public Herbivor3F(PVector l, DNA2 dna2_, PApplet p){
		
		herb3f = new ArrayList<PVector>();
		
		parent = p;
		position = l.get();
		xoff = p.random(1000);
		yoff = p.random(1000);
		
		dna2 = dna2_;
		health = PApplet.map(dna2.genes[0], 0, 1, 50, 250);
		r = PApplet.map(dna2.genes[0], 0, 1, 10, 25);
		maxspeed = PApplet.map(dna2.genes[0], 0, 1, 10, 1);
	}
	
	void update(){
		float vx = PApplet.map(parent.noise(xoff), 0, 1, -maxspeed, maxspeed);
		float vy = PApplet.map(parent.noise(yoff), 0, 1, -maxspeed, maxspeed);
		PVector velocity = new PVector(vx,vy);
		xoff += 0.01;
		yoff += 0.01;
		position.add(velocity);
		health -= 0.01;
	}
	
	void borders(PApplet p){
		if (position.x < -r) position.x = p.width+r;
	    if (position.y < -r) position.y = p.height+r;
	    if (position.x > p.width+r) position.x = -r;
	    if (position.y > p.height+r) position.y = -r;
	}
	
	void display(){
		parent.ellipseMode(PConstants.CENTER);
		parent.strokeWeight(2);
		parent.stroke(0,128,255, health);
		parent.fill(0, health);
		parent.ellipse(position.x, position.y, r, r);
	}
	
	public DNA2 getDNA2(){
		return dna2;
	}
	
	public float getR(){
		return r;
	}
	
	public ArrayList<PVector> getHerbivor3F(){
		return herb3f;
	}
	
	public void run(PApplet p){
		update();
		borders(parent);
		display();
	}
	
	/*
	public void gestation(PApplet p){
		gestation = 500;
		
		if(gestation > 0){
			pregnant = true;
			gestation --;
			parent.fill(153,0,0);
			parent.ellipse(position.x, position.y, 5, 5);
		}
		else{
			pregnant = false;
		}
	}
	
	public boolean getPregnant(){
		return pregnant;
	}
	*/
	
	
	public boolean dead(){
		if(health < 0.0){
			return true;
		}
		else{
			return false;
		}
	}
	
	public void eat2(Food2 f){
		ArrayList<PVector> food2 = f.getFood2();
		for(int i = food2.size()-1; i >= 0; i--){
			PVector food2position = food2.get(i);
			float d = PVector.dist(position, food2position);
			if(d < r){
				health += 100;
				food2.remove(i);
			}
		}
	}
	
	public int lastChildTime(){
		return parent.millis() - lastChildTime;
	}
	
	public void setLastChildTime(int time){
		lastChildTime = time;
	}
	
	
	

}
