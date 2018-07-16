package mechanisme;

import java.util.ArrayList;
import java.util.Iterator;

import entity.Charognard1M;
import entity.Herbivor1;
import entity.Herbivor3F;
import entity.Herbivor3M;
import objects.Food;
import objects.Food2;
import objects.Prairie;
import objects.Tree1;
import objects.Tree2;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;


/////////////////V3/////////////////////////


public class World {
	PApplet parent;
	
	Tree1 tree1;
	Tree2 tree2;
	Food food1;
	
	Prairie prairie;
	
	ArrayList<Herbivor1> herb1;
	
	ArrayList<Food2> food2;
	
	
	ArrayList<Herbivor3M> herb3m;
	ArrayList<Herbivor3F> herb3f;
	
	///test////
	ArrayList<Charognard1M> cha1m;
	

	
	public World(PApplet p){
		parent = p;
		
		//int nombprairie = 1;
		prairie = new Prairie(parent);
		
		int nombtree1 = 3;
		tree1 = new Tree1(nombtree1, p);
		for(int i = 0; i < nombtree1; i++){
			tree1.add(new PVector(p.random(p.width-10), p.random(p.height-10)));
		}
		
		int nombtree2 = 20;
		tree2 = new Tree2();
		for(int i = 0; i < nombtree2; i++){
			tree2.add(new PVector(p.random(p.width-1000, p.width-150), p.random(p.height-700, p.height-150)));
		}
		
		int nombHerb1 = 10;
		herb1 = new ArrayList<Herbivor1>();
		for(int i = 0; i < nombHerb1; i++){
			PVector l = new PVector(p.random(p.width),p.random(p.height));  //l = random position
			DNA dna = new DNA(p);
			herb1.add(new Herbivor1(l,dna,p));
		}
		
		int nombFood = 1;
		food1 = new Food(nombFood, p);
		for(int i = 0; i < nombFood; i++){
			food1.add(new PVector(p.random(p.width), p.random(p.height)));
		}
		
		int nombfood2 = 1;
		food2 = new ArrayList<Food2>();
		for(int i = 0; i < nombfood2; i++){
			food2.add(new Food2(nombfood2, p));
		}
		
		
		int nombHerb3m = 10;
		herb3m = new ArrayList<Herbivor3M>();
		for(int i = 0; i < nombHerb3m; i++){
			PVector l = new PVector(p.random(p.width),p.random(p.height));
			DNA2 dna2 = new DNA2(p);
			herb3m.add(new Herbivor3M(l, dna2, p));
		}
		
		int nombHerb3f = 10;
		herb3f = new ArrayList<Herbivor3F>();
		for(int i = 0; i < nombHerb3f; i++){
			PVector l = new PVector(p.random(p.width),p.random(p.height));
			DNA2 dna2 = new DNA2(p);
			herb3f.add(new Herbivor3F(l,dna2,p));
		}
		
		int nomcha1m = 1;
		cha1m = new ArrayList<Charognard1M>();
		for(int i = 0; i < nomcha1m; i++){
			PVector l = new PVector(p.random(p.width),p.random(p.height));
			cha1m.add(new Charognard1M(l,p));
		}
		
	}
	
	
	
	public void run(){
		
		
		parent.fill(0);
		parent.text("herbivor male " + herb3m.size(),10,20);
		parent.text("herbivor female " + herb3f.size(),10,32);
		
		
		
		
		
		
		////// fonctionne parfaitement !!!!!!
		
		////arbres et fruits
		tree2.run(parent);
		food1.run(parent);
		
		prairie.run(parent);
		
		if(parent.random(1) < 0.01){
			food2.add(new Food2(0, parent));
		}
		
		Iterator<Food2> F2 = food2.iterator();
		
		while(F2.hasNext()){
			Food2 f2 = F2.next();
			f2.run(parent);
			f2.grow(tree2);
			f2.growOld(f2);
			if(f2.rotten()){
				F2.remove();
			}
		}
		
		
		//////test/////////
		
		for(int i = cha1m.size()-1; i >=0; i--){
			Charognard1M c1m = cha1m.get(i);
			float[] x = new float[20];
			float[] y = new float[20];
			
			
			for(int a =0; a < x.length-1; a++){
				c1m.dragSegment(0, parent.mouseX, parent.mouseY);
				c1m.dragSegment(i+1, x[i], y[i]);
				c1m.run();
				
			}
		}
		
		///////////////////
		
		
		
		//////attention il faut mettre food2 avant pour qu'il puisse savoir de quoi il parle, si mis apres ca bug !!!!!
		for(int a = herb3f.size()-1; a >= 0; a--){
			Herbivor3F h3f = herb3f.get(a);
			//h3f.run(parent);
			if(h3f.dead()){
				//herb3f.remove(a);
			}
			
			for(int i = food2.size()-1; i >= 0; i--){
				Food2 f2 = food2.get(i);
				h3f.eat2(f2);
			}
		}
		
		Iterator<Herbivor3F> H3F = herb3f.iterator();
		while(H3F.hasNext()){
			Herbivor3F h3f = H3F.next();
			h3f.run(parent);
			if(h3f.dead()){
				H3F.remove();
			}
		}
		
		for(int b = herb3m.size()-1; b >= 0; b--){
			Herbivor3M h3m = herb3m.get(b);
			//h3m.run(parent);
			if(h3m.dead()){
				//herb3m.remove(b);
			}
			
			for(int i = food2.size()-1; i >= 0; i--){
				Food2 f2 = food2.get(i);
				h3m.eat2(f2);
			}
		}
		
		Iterator<Herbivor3M> H3M = herb3m.iterator();
		while(H3M.hasNext()){
			Herbivor3M h3m = H3M.next();
			h3m.run(parent);
			if(h3m.dead()){
				H3M.remove();
			}
		}
		
		/////reproduction herbivor 3 fct parfaitement
			for(int x = herb3m.size()-1; x >= 0; x--){
				for(int y = herb3f.size()-1; y >= 0; y--){
					Herbivor3M h3m = herb3m.get(x);
					Herbivor3F h3f = herb3f.get(y);
					PVector h3mpos = h3m.position;
					PVector h3fpos = h3f.position;
					float d = PVector.dist(h3mpos, h3fpos);
					if(d < h3f.getR()){
						DNA2 momgenes = h3f.getDNA2();
						DNA2 dadgenes = h3m.getDNA2();
						DNA2 childDna2 = momgenes.crossover(dadgenes, parent);
						if(parent.random(1) < 0.05 && h3m.lastChildTime() > 10000 && h3f.lastChildTime() > 10000){
							if(parent.random(10) > 5 && herb3f.size() < 20){
								herb3f.add(new Herbivor3F(h3fpos, childDna2, parent));
								h3f.setLastChildTime(parent.millis());
							}
							if(parent.random(10) < 5 && herb3m.size() < 20){
								herb3m.add(new Herbivor3M(h3fpos, childDna2, parent));
								h3m.setLastChildTime(parent.millis());
							}
						}
					}
				}
			}
		
	}
}
