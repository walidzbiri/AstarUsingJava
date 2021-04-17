import java.util.Arrays;
import java.util.ArrayList;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class Solveur {

   private ArrayList<Noeud> liste_noeuds_ouverts;
   private ArrayList<Noeud> liste_noeuds_fermes;

  public Solveur(){
    liste_noeuds_fermes=new ArrayList<Noeud>();
    liste_noeuds_ouverts=new ArrayList<Noeud>();
  }

  public Noeud findMin(ArrayList<Noeud> list_noeuds){
    Noeud min = list_noeuds.get(0); 
    for(Noeud n : list_noeuds){
      if(n.f()  < min.f()){
        min = n; 
      }
    }
    return min;
  }
  public Noeud algoAstar(Grille initiale){
    System.out.println("wait please !");
    Noeud noeudCourant=new Noeud(initiale, null, 0);
    this.liste_noeuds_ouverts.add(noeudCourant);
    
    while(!noeudCourant.estUnEtatFinal()){
      if(liste_noeuds_ouverts.size()==0){
        noeudCourant=null;
        System.out.println("Pas de solution pour ce puzzle");
        break;
      }
      noeudCourant=findMin(this.liste_noeuds_ouverts);
        // for(Noeud n: this.liste_noeuds_ouverts){
        //     System.out.println(n.getGrille().toString());
        // }
      //System.out.println(noeudCourant.getGrille().toString());
      this.liste_noeuds_ouverts.remove(noeudCourant);
      this.liste_noeuds_fermes.add(noeudCourant);

      ArrayList<Noeud> successeurs=noeudCourant.successeurs();
      for(Noeud s: successeurs){
        if(notInList(s, liste_noeuds_fermes)){
          if(notInList(s, liste_noeuds_ouverts)){
            liste_noeuds_ouverts.add(s);
          }else{
            Noeud n=getIdentiqueInList(s, liste_noeuds_ouverts);
            if(s.f()<n.f()){
              //n=s;
              liste_noeuds_ouverts.set(getNoeudIndex(s,liste_noeuds_ouverts),n);
            }
          }
        }
      }
    }
    return noeudCourant;
  }

  public int getNoeudIndex(Noeud n,ArrayList<Noeud> li){
    for(int i=0;i<li.size();i++){
      if(li.get(i).getGrille().equals(n.getGrille())){
        return i;
      }
    }
    return -1;
  }

  public boolean notInList(Noeud n,ArrayList<Noeud> li){
    boolean flag=true;
    for(Noeud no: li){
      if(n.equals(no)){
        flag=false;
        break;
      }
    }
    return flag;
  }

  public Noeud getIdentiqueInList(Noeud n,ArrayList<Noeud> li){
    for(Noeud no: li){
      if(n.equals(no)){
        return no;
      }
    }
    return new Noeud(null,null,0);
  }

  public ArrayList<Grille> cheminComplet(Noeud noeudFinale){
    ArrayList<Grille> cheminTotal=new ArrayList<Grille>();
    if(liste_noeuds_fermes.indexOf(noeudFinale)!=-1){
            Noeud n=liste_noeuds_fermes.get(liste_noeuds_fermes.indexOf(noeudFinale));
            while(n!=null){
              cheminTotal.add(n.getGrille());
              n=n.getPere();
            }
    }
    return cheminTotal;
  }
  /* recharge un fichier puzzle*/
  public static Grille chargerFichier(String nomFichier) {
        int[][] grille = null;
        try {
            File f = new File(nomFichier);
            Scanner scanner = new Scanner(f);
            // la première ligne : taille du puzzle
            Scanner sc = new Scanner(scanner.nextLine());
            int N = sc.nextInt();
            grille = new int[N][N];
            int i = 0;
            while (scanner.hasNext()) {
                sc = new Scanner(scanner.nextLine());
                int j = 0;
                while (sc.hasNext()) {
                    grille[i][j] = sc.nextInt();
                    j++;
                }
                i++;
            }
            // les autres lignes la grille du puzzle
            sc.close();
        } catch (FileNotFoundException exception) {
            System.out.println("File not found " + exception.getMessage());
        }
        assert grille != null;
        return new Grille(grille);
  }


}