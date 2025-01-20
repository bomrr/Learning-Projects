/*
 * Defines what each entity should have
 */

public abstract class Entity {
    protected int entityID;
    protected int x;
    protected int y;
    protected int moveSpeed;

    public Entity(int x, int y, int entityID, int moveSpeed) {
        this.x = x;
        this.y = y;
        this.entityID = entityID;
        this.moveSpeed = moveSpeed;
    }

    public void moveRight() {
        x += moveSpeed;
    }

    public void moveLeft() {
        x -= moveSpeed;
    }

    public void moveUp() {
        y += moveSpeed;
    }

    public void moveDown() {
        y -= moveSpeed;
    }

    public int getEntityID() {
        return entityID;
    }

    public void setEntityID(int entityID) {
        this.entityID = entityID;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public abstract String toString();

    public void moveAround(World world) {
        int random = (int) (Math.random() * 5);
        switch (random) {
            case 0:
                moveRight();
                break;
            case 1:
                moveLeft();
                break;
            case 2:
                moveUp();
                break;
            case 3:
                moveDown();
                break;
            case 4:
                break;
        }

        checkBounds(world.getWidth(), world.getHeight());
    }

    public void checkBounds(int width, int height) {
        if (x < 0) {
            x = 0;
        } else if (x >= width) {
            x = width - moveSpeed;
        }

        if (y < 0) {
            y = 0;
        } else if (y >= height) {
            y = height - moveSpeed;
        }
    }
}