public class Player extends Entity {

    public Player(int x, int y, int entityNumber) {
        super(x, y, entityNumber, 1);
    }

    @Override
    public String toString() {
        return "Player" + entityID + " is at (" + x + ", " + y + ")";
    }

    @Override
    public void moveAround(World world) {}
}
