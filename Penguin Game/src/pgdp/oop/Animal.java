package pgdp.oop;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.io.File;

public abstract class Animal {
  protected int x, y;
  protected boolean isAlive = true;
  static String filename;
  protected File f;
  protected Image image;

  protected static Animal[][] antarktis;

  public Animal(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public boolean safeSpace(int x, int y, Animal animal){
    if(x == 0){
      if(antarktis[antarktis.length-1][y] != null && antarktis[antarktis.length-1][y].canEat(animal)) {
        return false;
      }
    }
    else{
      if(antarktis[x-1][y] != null && antarktis[x-1][y].canEat(animal)){
        return false;
      }
    }
    if( x == antarktis.length - 1){
      if(antarktis[0][y] != null && antarktis[0][y].canEat(animal)){
        return false;
      }
    }
    else{
      if(antarktis[x+1][y] != null && antarktis[x+1][y].canEat(animal)){
        return false;
      }
    }
    if(y == 0){
      if(antarktis[x][antarktis[0].length - 1] != null && antarktis[x][antarktis[0].length - 1].canEat(animal)){
        return false;
      }
    }
    else{
      if(antarktis[x][y-1] != null && antarktis[x][y-1].canEat(animal)){
        return false;
      }
    }
    if( y == antarktis[0].length - 1){
      if(antarktis[x][0] != null && antarktis[x][0].canEat(animal)){
        return false;
      }
    }
    else{
      if(antarktis[x][y+1] != null && antarktis[x][y+1].canEat(animal)){
        return false;
      }
    }
    return true;
  }
  public static int inBound(int coordinate){
    if (coordinate == -1){
      return antarktis.length-1;
    }
    else if(coordinate == antarktis.length){
      return 0;
    }
    return coordinate;
  }
  public static void safeMove(Animal animal, int x, int y){
    antarktis[animal.x][animal.y] = null;
    antarktis[x][y] = animal;
    animal.x = x;
    animal.y = y;
  }
  public void move() {
    if(this.canEat(antarktis[inBound(x-1)][y])){
      if(safeSpace(inBound(x-1), y, this)){
        antarktis[inBound(x-1)][y].isAlive = false;
        safeMove(this, inBound(x-1), y);
      }
    }
    else if(this.canEat(antarktis[x][inBound(y-1)])){
      if(safeSpace(x, inBound(y-1), this)){
        antarktis[x][inBound(y-1)].isAlive = false;
        safeMove(this, x, inBound(y-1));
      }
    }
    else if(this.canEat(antarktis[inBound(x+1)][y])){
      if(safeSpace(inBound(x+1), y, this)){
        antarktis[inBound(x+1)][y].isAlive = false;
        safeMove(this, inBound(x+1), y);
      }
    }
    else if(this.canEat(antarktis[x][inBound(y+1)])){
      if(safeSpace(x, inBound(y+1), this)){
        antarktis[x][inBound(y+1)].isAlive = false;
        safeMove(this, x, inBound(y+1));
      }
    }
    else if(antarktis[inBound(x-1)][y] == null){
      if(safeSpace(inBound(x-1), y, this)){
        safeMove(this, inBound(x-1), y);
      }
    }
    else if(antarktis[x][inBound(y-1)] == null){
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
  }
  public abstract boolean canEat(Animal animal);

  protected abstract boolean eatenBy(Penguin penguin);
  protected abstract boolean eatenBy(PlayerPenguin playerPenguin);
  protected abstract boolean eatenBy(Whale whale);
  protected abstract boolean eatenBy(LeopardSeal leopardSeal);
  protected abstract boolean eatenBy(Fish fish);

  public static void setAntarktis(Animal[][] antarktis) {
    Animal.antarktis = antarktis;
  }
  // Graphics Stuff - You don't have to do anything here

  private void paintSymbol(Graphics g, Color c, int height, int width) {
    GradientPaint gradient = new GradientPaint(15, 0, c, width, 0, Color.LIGHT_GRAY);
    ((Graphics2D) g).setPaint(gradient);
    ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g.fillOval((int) (width * 0.3), (int) (height * 0.3), (int) (width * 0.5),
            (int) (height * 0.5));
  }

  public void draw(Graphics g, int height, int width) {
    if (image == null) {
      paintSymbol(g, Color.YELLOW, height, width);
      return;
    }
    ((Graphics2D) g).drawImage(image, 0, 0, width, height, 0, 0, image.getWidth(null),
            image.getHeight(null), null);
  }
}
