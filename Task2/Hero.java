public abstract class Hero implements IMortal {
    private final String name;
    private int health;
    public Hero(String name, int health) {
        this.name = name;
        this.health = health;
    }
    public String getName() {
        return name;
    }
    public abstract void attackEnemy(Enemy enemy);
    @Override
    public boolean isAlive() {
        return getHealth() > 0;
    }
    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = Math.max(health, 0);
    }
    public abstract void takeDamage(int damage);
}
