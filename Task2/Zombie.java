public class Zombie extends Enemy{
    private final double probabilityOfResurrection = 0.7;
    public Zombie(int health) {
        super(health);
    }

    @Override
    public void takeDamage(Hero hero, int damage) {
        System.out.print(getClass().getName() + " имеет " + getHealth() + " здоровья. ");
        setHealth(getHealth() - damage);
        System.out.println(getClass().getName() + " получил " +
                damage + " единиц урона. Текущий уровень здоровья: " + getHealth());
        ultimateCheck();
        if(!isAlive()) {
            System.out.println(getClass().getName() + " погиб");
            return;
        }
        System.out.println(getClass().getName() + " атакует " + hero.getName() + "!");
        hero.takeDamage((int) Math.ceil(Math.random() * 5.0));
    }
    private void ultimateCheck() {
        if(!isAlive() && Math.random() < probabilityOfResurrection) {
            setHealth((int)(Math.ceil(Math.random() * 3) + 1));
            System.out.println(getClass().getName() + " воскрес с " + getHealth() + " здоровья. ");
        }
    }
}
