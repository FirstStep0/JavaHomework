public class Mage extends Hero {
    private int mana = 0;
    public Mage(String name){
        super(name, 15);
    }
    @Override
    public void attackEnemy(Enemy enemy) {
        System.out.println(getClass().getName() + " " + getName() + " атакует врага!");
        int extraDamage = ultimateCheck();
        if(extraDamage > 0) {
            System.out.println(getClass().getName() + " " + getName() + " наносит дополнительные " + extraDamage + " единиц урона магией");
        }
        enemy.takeDamage(this, 14 + extraDamage);
    }
    private int ultimateCheck() {
        ++mana;
        if(mana >= 2) {
            mana -= 2;
            return (int)Math.ceil(Math.random() * 3) + 1;
        }
        return 0;
    }
    @Override
    public void takeDamage(int damage) {
        System.out.print(getName() + " имеет " + getHealth() + " здоровья. ");
        setHealth(getHealth() - damage);
        System.out.println(getName() + " получил " +
                damage + " единиц урона. Текущий уровень здоровья: " + getHealth());
        if(!isAlive()) {
            System.out.println(getName() + " погиб");
        }
    }
}
