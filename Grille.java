import java.util.Arrays;

public class Grille {

  private int[][] grille;
  private int taille;
  private int ligne0;// la ligne de la case vide
  private int colonne0;// la colonne de la case vide

  // Constructor
  public Grille(int[][] g){
    this.taille=g.length;
    this.grille=new int[this.taille][this.taille];
    for(int i = 0; i < this.taille; i++){    
            for(int j = 0; j < this.taille; j++){    
              this.grille[i][j]=g[i][j];
              if(this.grille[i][j]==0){
                this.colonne0=j;
                this.ligne0=i;
              }
            }    
    }
  }

  public int[][] getGrille(){
    return this.grille;
  }
  public int getTaille(){
    return this.taille;
  }
  public int getLigne0(){
    return this.ligne0;
  }
  public int getColonne0(){
    return this.colonne0;
  }

  public int getValeur(int i,int j){
    return this.grille[i][j];
  }

  public int[][] copier(){
    int [][] copy=new int[this.getTaille()][this.getTaille()];
    for(int i = 0; i < this.taille; i++){    
            for(int j = 0; j < this.taille; j++){    
              copy[i][j]=this.grille[i][j];
            }    
    }
    return copy;
  }

  // Surcharge de equals
  public boolean equals(Grille y){
    boolean flag=true;
    if(y.getTaille() != this.getTaille() ){ 
        flag=false;
    }    
    else {    
        for(int i = 0; i < y.getTaille(); i++){    
            for(int j = 0; j < y.getTaille(); j++){    
              if(y.getGrille()[i][j] != this.getGrille()[i][j]){ 
                  flag = false;    
                  break;    
              }    
            }    
        }
    }
    return flag;
  }

  //surcharge de toString 
  public String toString(){
    String s="";
    for(int i = 0; i < this.getTaille(); i++){    
            for(int j = 0; j < this.getTaille(); j++){    
              s+=""+this.grille[i][j]+"\t";    
            }    
            s+="\n";
    }
    return s;
  }

}