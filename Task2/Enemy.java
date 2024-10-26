public abstract class Enemy implements IMortal {
    private int health;
    public Enemy(int health) {
        setHealth(health);
    }
    int getHealth() {
        return health;
    }
    void setHealth(int health) {
        this.health = Math.max(health, 0);
    }
    public abstract void takeDamage(Hero hero, int damage);
    @Override
    public boolean isAlive() {
        return getHealth() > 0;
    }
}
