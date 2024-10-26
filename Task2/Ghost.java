public class Ghost extends Enemy{
    public Ghost(int health) {
        super(health);
    }

    @Override
    public void takeDamage(Hero hero, int damage) {
        System.out.print(getClass().getName() + " имеет " + getHealth() + " здоровья. ");
        if(Math.random() < 0.7) {
            System.out.print("По " + getClass().getName() + " сложно попасть. ");
        }
        else {
            setHealth(getHealth() - damage);
            System.out.println(getClass().getName() + " получил " +
                    damage + " единиц урона. Текущий уровень здоровья: " + getHealth());
        }
        if(!isAlive()) {
            System.out.println(getClass().getName() + " погиб");
            return;
        }
    }
}
