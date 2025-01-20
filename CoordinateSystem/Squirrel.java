public class Squirrel extends Entity {

    public Squirrel(int x, int y, int entityNumber) {
        super(x, y, entityNumber, 1);
    }

    @Override
    public String toString() {
        return "Squirrel" + entityID + " is at (" + x + ", " + y + ")";
    }

}
