package Domaine;

public class MesureImperiale {
    public int pieds;
    public int pouces;
    public int num;
    public int denum;

    public MesureImperiale(int pieds, int pouces, int num, int denum){
        this.pieds = pieds;
        this.pouces = pouces;
        this.num = num;
        this.denum = denum;
    }

    public MesureImperiale(int trentedeuxiemes){
        this.pieds = trentedeuxiemes / 384;
        this.pouces = (trentedeuxiemes % 384) / 32;
        int num = trentedeuxiemes % 32;
        int denum = 32;
        while(num % 2 == 0){
            num /= 2;
            denum /= 2;
        }
        this.num = num;
        this.denum = denum;
    }

    public int getTrentedeuxiemes(){
        return pouces *32 + num*32/denum;
    }

    @Override
    public String toString(){
        return pieds + " pieds, " + pouces + " et " + num + "/" + denum;
    }
}
