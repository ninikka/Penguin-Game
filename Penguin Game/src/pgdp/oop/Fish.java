package pgdp.oop;

import java.awt.Toolkit;
import java.io.File;

public class Fish extends Animal {
  static String filename = "fish.png";

  public Fish(int x, int y) {
    super(x, y);

    f = new File(filename);
    image = Toolkit.getDefaultToolkit().getImage(f.getAbsolutePath());
  }
  @Override
  public void move(){
    if(antarktis[x][inBound(y-1)] == null){
      if(safeSpace(x, inBound(y-1), this)){
        safeMove(this, x, inBound(y-1));
      }
    }
    else if(antarktis[inBound(x+1)][y] == null){
      if(safeSpace(inBound(x+1), y, this)){
        safeMove(this, inBound(x+1), y);
      }
    }
    else if(antarktis[x][inBound(y+1)] == null){
      if(safeSpace(x, inBound(y+1), this)){
        safeMove(this, x, inBound(y+1));
      }
    }
    else if(antarktis[inBound(x-1)][y] == null){
      if(safeSpace(inBound(x-1), y, this)){
        safeMove(this, inBound(x-1), y);
      }
    }
  }
  public boolean canEat(Animal animal) {
    return animal.eatenBy(this);
  }

  @Override
  protected boolean eatenBy(Penguin penguin) {
    return true;
  }

  @Override
  protected boolean eatenBy(PlayerPenguin playerPenguin) {
    return true;
  }

  @Override
  protected boolean eatenBy(Whale whale) {
    return false;
  }

  @Override
  protected boolean eatenBy(LeopardSeal leopardSeal) {
    return true;
  }

  @Override
  protected boolean eatenBy(Fish fish) {
    return false;
  }
}
