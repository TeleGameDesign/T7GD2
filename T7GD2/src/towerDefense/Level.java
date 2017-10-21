package towerDefense;

import java.util.Arrays;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

public class Level {
	
	int[][] map;
	float width, height;
	int lenX, lenY;
	
	public Level() {
		width = 32;
		height = 32;
		lenX = (int)(1280 / width);
		lenY = (int)(720 / height);
		genereMap();
		
	}
	
	public int[] genereLine(int element1, int element2, int debut, int fin) {
		//Genere une ligne avec element1 � partir de la case debut jusqu'� la case fin exclu, compl�te le reste avec element2
		int[] line = new int[lenX];
		for (int i = 0; i < lenX; i++)
		{
			line[i] = element2;
		}
		
		for (int i = debut; i < fin; i++) 
		{
			line[i] = element1;
		}
		return line;
	}
	
	public void genereMap() {
		// Generer la carte du jeu
		map = new int[lenY][lenX];
		
		int [] line1 = new int[lenX];
		Arrays.fill(line1, 1);
		map[0] = line1;
		
		for (int i=1; i < (int)(lenY - 4); i+=4)
		{
			map[i] = genereLine(0,1,1,2);
			map[i+1] = genereLine(0,1,1,lenX - 1);
			map[i+2] = genereLine(0,1,lenX - 2,lenX - 1);
			map[i+3] = genereLine(0,1,1,lenX - 1);
		}
		
		map[1][1] = 2;    //spawn
		map[lenY - 2][1] = 3;  //fin	
		map[lenY-1] = line1;	
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) {
		g.setColor(Color.green);
		double x,y;
		for ( int i = 0; i < lenY; i++)
		{
			for(int j = 0; j < lenX; j++)
			{
				x = 32*j;
				y = 32*i;						
				switch (map[i][j]) {
				case 1 : g.setColor(Color.green);g.fillRect((float)(x), (float)(y),(float) width, (float)height);
				break;
				case 2 :  g.setColor(Color.red);g.fillRect((float)(x), (float)(y),(float) width, (float)height);
				break;
				case 3 :  g.setColor(Color.blue);g.fillRect((float)(x), (float)(y),(float) width, (float)height);
				break;
				}
			}
		}

	}

	protected void update(GameContainer container, StateBasedGame game, int delta) {

	}
	
	public int getCase(int ligne, int colonne) {
		return map[ligne][colonne];
	}
	
	public double getX(int colonne) {
		//Donne le x du point en haut � gauche de la case � la colonne 'colonne'
		return 32*colonne;
	}
	
	public double getY(int ligne) {
		//Donne le y du point en haut � gauche de la case � la ligne 'ligne'
		return (32*ligne);
	}

}
