import java.util.Arrays;
import java.util.ArrayList;
import java.lang.*;
public class Noeud {

  private Grille grille;
  private Noeud pere;
  private int g;

  // Constructor
  public Noeud(Grille grille,Noeud p,int g){
    this.grille=grille;
    this.pere=p;
    this.g=g;
  }

  public Grille getGrille(){
    return this.grille;
  }
  public Noeud getPere(){
    return this.pere;
  }

  // au depart h=h1 nb de case mal placées
  public int h1(){
    int totalMalPlace=0;
    int taille=grille.getTaille();

    for(int i = 0; i < this.grille.getTaille(); i++){    
            for(int j = 0; j < this.grille.getTaille(); j++){    
              if(this.grille.getGrille()[i][j]!=1+j+(i*taille) && this.grille.getGrille()[i][j]!=0){
                totalMalPlace+=1;
              }
            }    
    }
    return totalMalPlace;
  }


    // h2= la somme des distances des cases par rapport a leurs positions cibles.
  public int h2(){
    int somme=0;
    int taille=grille.getTaille();
    // creation de la grille réference pour calculer la distance manhattan
    int [][] grilleReference=new int[taille][taille];
    int cpt=0;
    for(int i=0;i<taille;i++){
      for(int j=0;j<taille;j++){
          cpt++;
          grilleReference[i][j]=cpt;
      }
    }
    grilleReference[taille-1][taille-1]=0;

    for(int i = 0; i < this.grille.getTaille(); i++){    
            for(int j = 0; j < this.grille.getTaille(); j++){    
              for(int k = 0; k < this.grille.getTaille(); k++){
                for(int l = 0; l < this.grille.getTaille(); l++){
                    if(this.grille.getGrille()[i][j]==grilleReference[k][l] && grilleReference[k][l]!=0){
                          somme+=Math.abs(j-l)+Math.abs(i-k);
                    }
                }
              }
            }    
    }
    return somme;
  }
  public int g(){
    return this.g;
  }
  public int h(){
    return h2();
  }
  public int f(){
    return this.g()+this.h();
  }

  public boolean estUnEtatFinal(){
    return h()==0;
  }

  public ArrayList<Noeud> successeurs(){
    ArrayList<Noeud> successeurs = new ArrayList<Noeud>();
    int row=this.grille.getLigne0();
    int col=this.grille.getColonne0();
    int size=this.grille.getTaille();
    if (col < size-1) {
        int[][] right = this.grille.copier();
        right[row][col] = right[row][col + 1];
        right[row][col+1] = 0;                  
        successeurs.add(new Noeud(new Grille(right),this,this.g+1)); 
    }
    if (col >0) {
        int[][] left = this.grille.copier();
        left[row][col] = left[row][col-1];
        left[row][col-1] = 0;
        successeurs.add(new Noeud(new Grille(left),this,this.g+1)); 
    }
     if (row < size-1) {
        int[][] down = this.grille.copier();
        down[row][col] = down[row + 1][col];
        down[row + 1][col] = 0;                 
        successeurs.add(new Noeud(new Grille(down),this,this.g+1)); 
    } 
     if (row > 0) {
        int[][] up = this.grille.copier();
        up[row][col] = up[row-1][col];
        up[row-1][col] = 0;
        successeurs.add(new Noeud(new Grille(up),this,this.g+1)); 
    }
    return successeurs;
  }

  @Override
  public boolean equals(Object object)
  {
      boolean isEqual= false;

      if (object != null && object instanceof Noeud)
      {
          isEqual = this.grille.equals(((Noeud) object).grille);
      }

      return isEqual;
  }

  // @Override
  // public int[][] hashCode() {
  //     return this.grille.getGrille();
  // }
}

// distance = abs(i - k) + abs(j - l);