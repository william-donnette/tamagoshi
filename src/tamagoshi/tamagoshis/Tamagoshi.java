package tamagoshi.tamagoshis;

import java.util.Random;

public class Tamagoshi {
    private int age;
    private int maxEnergy;
    private int energy;
    private String name;
    private static int lifeTime = 10;
    private Random random;
    private int fun;
    private int maxFun;

    public Tamagoshi(String name) {
        this.name = name;
        age = 0;
        random = new Random();
        maxEnergy = random.nextInt(5)+5;
        energy = random.nextInt(3)+4;
        maxFun = random.nextInt(5)+5;
        fun = random.nextInt(3)+4;
    }

    /**
     * Affiche le nom du tamagoshi courant et son état
     * Retourne true si le tamagoshi est heureux ou false si il est affamé
     * @return boolean
     */
    public boolean parle() {
        String etat = "";
        etat += (energy <= 4) ? "J'ai faim, apporte moi a manger chacal !" : "";
        etat += (fun <= 4) ? " Starfoulah personne veut jouer avec moi :'(" : "";
        if(etat.isBlank()) etat = "Tout va bien !";
        System.out.println(">>>>> "+name + " : " + etat);
        return energy > 4;
    }

    /**
     * Affiche l'état de contentement du tamagoshi courant;
     * Retourne true si le tamagoshi courant avait faim ou false si il n'avait pas faim
     * @return boolean
     */
    public boolean mange() {
        Boolean affame = energy < maxEnergy;
        String faim = affame ? "Miam :p" : "Grrr  è_é";
        System.out.println(name + " : " + faim);
        energy += random.nextInt(2)+2;
        if (energy > maxEnergy) energy = maxEnergy;
        return affame;
    }

    /**
     * Affiche l'état de contentement du tamagoshi courant;
     * Retourne true si le tamagoshi courant avait envie de jouer ou false si il n'avait pas envie
     * @return boolean
     */
    public boolean joue() {
        Boolean manque = fun < maxFun;
        String joue = manque ? "Ouiiiiiii :D" : "Tu veux quoi toi ?";
        System.out.println(">>>>> "+name + " : " + joue);
        fun += random.nextInt(2)+2;
        if (fun > maxFun) fun = maxFun;
        return manque;
    }

    public boolean consommeEnergie(){
        energy--;
        if(energy <= 0) System.out.println(">>>>> "+name + " : " + "C'est trop, on crève la dalle ici\n");
        return energy > 0;
    }

    public boolean consommeFun(){
        fun--;
        if(fun <= 0) System.out.println(">>>>> "+name + " : " + "snif : je fais une dépression, ciao!!  O_o\n");
        return fun > 0;
    }

    public boolean vieillit(){
        age++;
        return age < lifeTime;
    }

    @Override
    public String toString() {
        return "Tamagoshi{" +
                "age=" + age +
                ", maxEnergy=" + maxEnergy +
                ", energy=" + energy +
                ", name='" + name + '\'' +
                ", random=" + random +
                '}';
    }

    public String getName(){
        return name;
    }

    public int getAge() {
        return age;
    }

    public static int getLifeTime() {
        return lifeTime;
    }

    public static void main(String[] args) {
        Tamagoshi t = new Tamagoshi("Ptit filou\n");
        System.out.println(t);
        t.parle();

        t.consommeEnergie();
        System.out.println(t);
        t.consommeEnergie();
        t.consommeEnergie();
        t.consommeEnergie();
        System.out.println(t);
        t.parle();
        t.mange();
        t.mange();
        t.mange();
        t.mange();
        t.mange();
        t.mange();
        t.mange();
        t.mange();
        t.mange();
        t.mange();
        t.mange();
        t.mange();
        t.mange();
        t.mange();
    }
}
