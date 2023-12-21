package pgdp.oop;

public class PlayerPenguin extends Penguin {
    public PlayerPenguin(int x, int y) {
        super(x, y);
    }

    public boolean canEat(Animal animal) {

        if(animal == null){
            return false;
        }
        return animal.eatenBy(this);
    }
    public boolean hasMoved = false;
}
