package pgdp.oop;

import java.awt.event.WindowEvent;

public class Antarktis extends Maze {
    private static int width, height;
    private static Penguin lostPenguin;
    private static Whale[] whales = new Whale[5];
    private static LeopardSeal[] leopardSeals = new LeopardSeal[20];
    private static Fish[] fishes = new Fish[500];
    private static PlayerPenguin playerPenguin;

    public static void main(String[] args) {
        width = 41;
        height = 41;
        antarktis = generateMaze(width, height);
        Animal.setAntarktis(antarktis);
        setupMaze();
        gameLoop();
        closeFrame();
    }

    private static void gameLoop() {
        while (true) {
            draw();
            if(currentEvent == LEFT){
                if(antarktis[Animal.inBound(playerPenguin.x-1)][playerPenguin.y] != null) {
                    if (antarktis[Animal.inBound(playerPenguin.x - 1)][playerPenguin.y].canEat(playerPenguin)) {
                        System.out.println("The Player penguin died. You lost(not slay)");
                        closeFrame();
                        System.exit(0);
                    } else if ((!playerPenguin.canEat(antarktis[Animal.inBound(playerPenguin.x - 1)][playerPenguin.y]) && lostPenguin != antarktis[Animal.inBound(playerPenguin.x - 1)][playerPenguin.y])) {
                        System.out.println("Invalid move. You lost(not slay)");
                        closeFrame();
                        System.exit(0);
                    } else if (lostPenguin == antarktis[Animal.inBound(playerPenguin.x - 1)][playerPenguin.y]) {
                        System.out.println("You won (slayed QUEEEEEN)");
                        closeFrame();
                        System.exit(0);
                    }
                }
                Animal.safeMove(playerPenguin, Animal.inBound(playerPenguin.x-1), playerPenguin.y);
                playerPenguin.hasMoved = true;
            } else if (currentEvent == RIGHT) {
                if(antarktis[Animal.inBound(playerPenguin.x+1)][playerPenguin.y] != null) {
                    if (antarktis[Animal.inBound(playerPenguin.x + 1)][playerPenguin.y].canEat(playerPenguin)) {
                        System.out.println("The Player penguin died. You lost(not slay)");
                        closeFrame();
                        System.exit(0);
                    } else if ((!playerPenguin.canEat(antarktis[Animal.inBound(playerPenguin.x + 1)][playerPenguin.y]) && lostPenguin != antarktis[Animal.inBound(playerPenguin.x + 1)][playerPenguin.y])) {
                        System.out.println("Invalid move. You lost(not slay)");
                        closeFrame();
                        System.exit(0);
                    } else if (lostPenguin == antarktis[Animal.inBound(playerPenguin.x + 1)][playerPenguin.y]) {
                        System.out.println("You won (slayed QUEEEEEN)");
                        closeFrame();
                        System.exit(0);
                    }
                }
                Animal.safeMove(playerPenguin, Animal.inBound(playerPenguin.x+1), playerPenguin.y);
                playerPenguin.hasMoved = true;
            } else if (currentEvent == UP){
                if(antarktis[playerPenguin.x][Animal.inBound(playerPenguin.y-1)] != null) {
                    if (antarktis[playerPenguin.x][Animal.inBound(playerPenguin.y - 1)].canEat(playerPenguin)) {
                        System.out.println("The Player penguin died. You lost(not slay)");
                        closeFrame();
                        System.exit(0);
                    } else if ((!playerPenguin.canEat(antarktis[playerPenguin.x][Animal.inBound(playerPenguin.y - 1)]) && lostPenguin != antarktis[playerPenguin.x][Animal.inBound(playerPenguin.y - 1)])) {
                        System.out.println("Invalid move. You lost(not slay)");
                        closeFrame();
                        System.exit(0);
                    } else if (lostPenguin == antarktis[playerPenguin.x][Animal.inBound(playerPenguin.y - 1)]) {
                        System.out.println("You won (slayed QUEEEEEN)");
                        closeFrame();
                        System.exit(0);
                    }
                }
                Animal.safeMove(playerPenguin, playerPenguin.x, Animal.inBound(playerPenguin.y-1));
                playerPenguin.hasMoved = true;
            } else if (currentEvent == DOWN) {
                if(antarktis[playerPenguin.x][Animal.inBound(playerPenguin.y+1)] != null) {
                    if (antarktis[playerPenguin.x][Animal.inBound(playerPenguin.y + 1)].canEat(playerPenguin)) {
                        System.out.println("The Player penguin died. You lost(not slay)");
                        closeFrame();
                        System.exit(0);
                    } else if ((!playerPenguin.canEat(antarktis[playerPenguin.x][Animal.inBound(playerPenguin.y + 1)]) && lostPenguin != antarktis[playerPenguin.x][Animal.inBound(playerPenguin.y + 1)])) {
                        System.out.println("Invalid move. You lost(not slay)");
                        closeFrame();
                        System.exit(0);
                    } else if (lostPenguin == antarktis[playerPenguin.x][Animal.inBound(playerPenguin.y + 1)]) {
                        System.out.println("You won (slayed QUEEEEEN)");
                        closeFrame();
                        System.exit(0);
                    }
                }
                Animal.safeMove(playerPenguin, playerPenguin.x, Animal.inBound(playerPenguin.y+1));
                playerPenguin.hasMoved = true;
            }
            if(playerPenguin.hasMoved) {
                moveAll();
                playerPenguin.hasMoved = false;
            }
            currentEvent = NOTHING;

        }
    }

    private static void moveAll() {
        for(int i =0; i < whales.length; i++){
            whales[i].move();
        }
        if(!lostPenguin.isAlive){
            System.out.println("The lost penguin died. You lost(not slay)");
            closeFrame();
            System.exit(0);
        }
        for(int i =0; i < leopardSeals.length; i++){
            if(leopardSeals[i].isAlive) {
                leopardSeals[i].move();
            }
        }
        for(int i =0; i < fishes.length; i++){
            if(fishes[i].isAlive) {
                fishes[i].move();
            }
        }

    }

    /**
     * Example Setup for easier Testing during development
     */
    private static void setupMaze() {
        int[] pos;
        playerPenguin = new PlayerPenguin(3, 3);
        antarktis[3][3] = playerPenguin;

        for (int i = 0; i < whales.length; i++) {
            pos = getRandomEmptyField();
            whales[i] = new Whale(pos[0], pos[1]);
            antarktis[pos[0]][pos[1]] = whales[i];
        }

        for (int i = 0; i < leopardSeals.length; i++) {
            pos = getRandomEmptyField();
            leopardSeals[i] = new LeopardSeal(pos[0], pos[1]);
            antarktis[pos[0]][pos[1]] = leopardSeals[i];
        }

        for (int i = 0; i < fishes.length; i++) {
            pos = getRandomEmptyField();
            fishes[i] = new Fish(pos[0], pos[1]);
            antarktis[pos[0]][pos[1]] = fishes[i];
        }

        antarktis[20][20] = new Penguin(20, 20);
        lostPenguin = (Penguin) antarktis[20][20];
    }
}
