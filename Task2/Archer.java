public class Archer extends Hero {
    public Archer(String name){
        super(name, 20);
    }
    @Override
    public void attackEnemy(Enemy enemy) {
        if(!isAlive()) {
            System.out.println("Лучник мертв и не может атаковать");
            return;
        }
        System.out.println(getClass().getName() + " " + getName() + " атакует врага!");
        enemy.takeDamage(this, 10);
    }

    @Override
    public void takeDamage(int damage) {
        System.out.print(getName() + " имеет " + getHealth() + " здоровья. ");
        if(dodgeCheck()) {
            System.out.print(getName() + " уклоняется и получает меньше урона. ");
            damage = (damage + 1) / 2;
        }
        setHealth(getHealth() - damage);
        System.out.println(getName() + " получил " +
                damage + " единиц урона. Текущий уровень здоровья: " + getHealth());
        if(!isAlive()) {
            System.out.println(getName() + " погиб");
        }
    }
    private boolean dodgeCheck() {
        return Math.random() < 0.5;
    }
}
