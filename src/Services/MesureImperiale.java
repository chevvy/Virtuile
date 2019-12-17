package Services;

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

    public MesureImperiale(int huitiemes){
        this.pieds = huitiemes / 96;
        this.pouces = (huitiemes % 96) / 8;
        int num = huitiemes % 8;
        int denum = 8;
        while(num % 2 == 0 && num != 0){
            num /= 2;
            denum /= 2;
        }
        this.num = num;
        this.denum = denum;
    }

    public int getHuitiemes(){
        return pieds * 96 + pouces * 8 + num*8 / denum;
    }

    @Override
    public String toString(){
        return pieds + " pieds, " + pouces + " et " + num + "/" + denum;
    }
}
