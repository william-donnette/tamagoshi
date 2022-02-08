package tamagoshi.jeu;

import tamagoshi.tamagoshis.Tamagoshi;
import tamagoshi.util.Utilisateur;

import java.io.IOException;
import java.util.ArrayList;

public class TamaGame {
    private ArrayList<Tamagoshi> depart;
    private ArrayList<Tamagoshi> course;

    public TamaGame() {
        this.depart = new ArrayList<>();
        this.course = new ArrayList<>();
    }

    public void initialisation(){
        System.out.println("Saisir le nombre de tamagoshis : \n");
        int nombre_tamagoshis = Integer.parseInt(Utilisateur.saisieClavier());
        for(int i=0; i<nombre_tamagoshis; i++){
            System.out.println("Saisir le nom du tamagoshi n°"+(i+1)+" : \n");
            String name = Utilisateur.saisieClavier();
            Tamagoshi t = new Tamagoshi(name);
            depart.add(t);
            course.add(t);
        }
    }

    public void play(){
        initialisation();
        Boolean fin = false;
        int cycle = 1;
        while(!fin){

            System.out.println("------- Cycle " + cycle + " -------\n");

            // Présentation des Tamagoshis
            for (Tamagoshi tamagoshi : course) {
                tamagoshi.parle();
            }

            // Heure du repas
            int nourrir = 0;
            if (course.size() == 1){
                course.get(0).mange();
                course.get(0).joue();
            }
            else {
                do {
                    System.out.println("Nourrir quel tamagoshi ?\n");
                    String listeTama = "";
                    for (Tamagoshi tamagoshi : course) {
                        listeTama += "(" + (course.indexOf(tamagoshi) + 1) + ") " + tamagoshi.getName();
                        listeTama += "         ";
                    }
                    System.out.println(listeTama);
                    System.out.println("Choississez un numéro\n");
                    nourrir = Integer.parseInt(Utilisateur.saisieClavier()) - 1;
                }while(nourrir >= course.size() || nourrir < 0);
                course.get(nourrir).mange();
                int jouer = 0;
                do{
                    System.out.println("Jouer avec quel tamagoshi ?\n");
                    String listeTamaJouer = "";
                    for(Tamagoshi tamagoshi : course){
                        listeTamaJouer +="("+(course.indexOf(tamagoshi)+1)+") "+tamagoshi.getName();
                        listeTamaJouer += "         ";
                    }
                    System.out.println(listeTamaJouer);
                    System.out.println("Choississez un numéro\n");
                    jouer = Integer.parseInt(Utilisateur.saisieClavier())-1;
                }while(jouer >= course.size() || jouer < 0);
                course.get(jouer).joue();
            }


            // Moment de vieillir et perdre de l'energie
            course.removeIf(tamagoshi -> !(tamagoshi.vieillit() && tamagoshi.consommeEnergie() && tamagoshi.consommeFun()));

            // Verification fin du jeu
            if (course.isEmpty()) fin = true;
            cycle++;
        }

        System.out.println("------- Fin de Partie -------\n");
        resultat();
    }

    public float score(){
        float maxScore = Tamagoshi.getLifeTime() * depart.size();
        float score = 0;
        for (Tamagoshi tamagoshi : depart) {
            score += tamagoshi.getAge();
        }
        return ( score / maxScore ) * 100;
    }

    public void resultat(){
        System.out.println("----------- Bilan -------------\n");
        for (Tamagoshi tamagoshi : depart) {
            if(tamagoshi.getAge() == Tamagoshi.getLifeTime()){
                System.out.println(tamagoshi.getName()+" a survécu et vous remercie :)\n");
            }else{
                System.out.println(tamagoshi.getName()+" n'est pas arrivé au bout et ne vous félicite pas :(   ---->  Il est mort à "+tamagoshi.getAge()+" ans\n");
            }
        }
        System.out.println("score obtenue : "+score()+"%\n");
    }

    public static void main(String[] args) {
        TamaGame jeu = new TamaGame();
        jeu.play();
    }
}
