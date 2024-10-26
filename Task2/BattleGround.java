import java.util.ArrayList;
import java.util.List;

public class BattleGround {
    public static void main(String[] args) {
        Hero hero = new Warrior("Hulk");
        //Hero hero = new Mage("Scarlet Witch");
        //Hero hero = new Archer("Robin");

        List<Enemy> enemies = new ArrayList<Enemy>();
        enemies.add(new Zombie(15));
        enemies.add(new Zombie(22));
        enemies.add(new Ghost(17));
        while(hero.isAlive() && !enemies.isEmpty()) {
            hero.attackEnemy(enemies.getFirst());
            if(!enemies.getFirst().isAlive()) {
                enemies.removeFirst();
            }
            System.out.println();
        }
    }
}
