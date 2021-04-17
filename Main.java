import java.util.*;

class Main {
  public static void main(String[] args) {
    Solveur s=new Solveur();
    Grille g=Solveur.chargerFichier("puzzle04.txt");
    //System.out.println(g.toString());
    Noeud nfinale=s.algoAstar(g);
    if(nfinale!=null){
        ArrayList<Grille> li=s.cheminComplet(nfinale);
        if(li.size()==0){
          System.out.println("Le fichier contient déja la solution");
        }else{
          Collections.reverse(li);// Inverser le chemin pour avoir le vrai ordre
          int i=0;
          for(Grille gri: li){
              System.out.println("Etape "+i);
              System.out.println(gri.toString());
              i++;
          }
        }
    }
  }
}
