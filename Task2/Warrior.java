public class Warrior extends Hero {
    private int armor;
    public Warrior(String name){
        super(name, 30);
        armor = 1;
    }
    @Override
    public void attackEnemy(Enemy enemy) {
        System.out.println(getClass().getName() + " " + getName() + " атакует врага!");
        enemy.takeDamage(this, 12);
    }
    @Override
    public void takeDamage(int damage) {
        System.out.print(getName() + " имеет " + getHealth() + " здоровья. ");
        damage = Math.max(0, damage - armor);
        System.out.print("Броня поглотила " + armor + " урона. ");
        setHealth(getHealth() - damage);
        System.out.println(getName() + " получил " +
                damage + " единиц урона. Текущий уровень здоровья: " + getHealth());
        if(!isAlive()) {
            System.out.println(getName() + " погиб");
        }
    }
}
