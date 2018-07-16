package mechanisme;

import processing.core.PApplet;

public class DNA2 {
	PApplet parent;
	public float[] genes;
	int diffgenes = 2;
	DNA2 dna2;
	
	public DNA2(PApplet p){
		parent = p;
		genes = new float[diffgenes];
		for(int i = 0; i < genes.length; i++){
			genes[i] = p.random(0, 1);
		}
	}
	
	DNA2(float[] newgenes){
		genes = newgenes;
	}
	
	public DNA2 copy(){
		float[] newgenes = new float[genes.length];
		for(int i =0; i < newgenes.length; i++){
			newgenes[i] = genes[i];
		}
		return new DNA2(newgenes);
	}
	
	DNA2 crossover(DNA2 partner,PApplet p){
		float[] child = new float[genes.length];
		//float crossover =  (parent.random(genes.length));
		int crossover = (int) p.random(5);
		for(int i = 0; i < genes.length; i++){
			if(i > crossover){
				child[i] = genes[i];
			}
			else{
				child[i] = partner.genes[i];
			}
		}
		DNA2 newgenes = new DNA2(child);
		return newgenes;
	}
	
	public DNA2 getDNA2(){
		return dna2;
	}
	
	public void mutate(float m){
		for(int i = 0; i < genes.length; i++){
			if(parent.random(1) < m){
				genes[i] = parent.random(0, 1);
			}
		}
	}
}
