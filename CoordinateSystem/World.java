import java.util.concurrent.ConcurrentHashMap;
/*
 * This class controls the world size and holds what is in the world.
 * Each entity is identified by their number.
 */

public class World {
    private int width;
    private int height;
    private ConcurrentHashMap<Integer, Entity> entities;

    public World(int width, int height) {
        this.width = width;
        this.height = height;
        entities = new ConcurrentHashMap<>();
    }

    public void addEntity(Entity entity) {
        entities.put(entity.getEntityID(), entity);
    }

    public void removeEntity(Entity entity) {
        entities.remove(entity.getEntityID());
    }

    public Entity getEntityById(int entityID) {
        return entities.get(entityID);
    }

    public Entity getEntityAt(int x, int y) {
        for (Entity entity : entities.values()) {
            if (entity.getX() == x && entity.getY() == y) {
                return entity;
            }
        }
        return null;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public ConcurrentHashMap<Integer, Entity> getAllEntities() {
        return entities;
    }
    
}
