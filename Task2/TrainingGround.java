public class TrainingGround {
    public static void main(String[] args) {
        Enemy zombie = new Zombie(100);
        Hero hero = new Warrior("Hulk");
        hero.attackEnemy(zombie);
        System.out.println();

        hero = new Mage("Scarlet Witch");
        hero.attackEnemy(zombie);
        System.out.println();

        hero = new Archer("Robin");
        hero.attackEnemy(zombie);
        System.out.println();
    }
}
