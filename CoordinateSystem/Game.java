import java.util.Iterator;
import java.util.Scanner;

public class Game {
    private boolean isRunning = true;
    private int currentTick = 0;
    private Player player;

    public void start() {
        Scanner input = new Scanner(System.in);
        
        System.out.println("Enter the width of the world: ");
        int width = input.nextInt();
        System.out.println("Enter the height of the world: ");
        int height = input.nextInt();

        World world = new World(width, height);
        player = new Player(0, 0, 1);
        world.addEntity(player);
        System.out.println("Player created at (0, 0)");

        startTickThread(world);

        startUserInputThread(input, world);

        createRandomEntities(world);
        
                while (isRunning) {
                    Iterator<Entity> iterator = world.getAllEntities().values().iterator();
                    while (iterator.hasNext()) {
                        Entity entity = iterator.next();
                        entity.moveAround(world);
                    }
                }
            }
            
    private void startUserInputThread(Scanner input, World world) {
        System.out.println("Listening for user input...");

        Thread userInputThread = new Thread(() -> {
            while (isRunning) {
                String command = input.nextLine();

                // Player commands:
                if (command.equals("move right")) {
                    player.moveRight();
                    System.out.println("Player moved right");
                } else if (command.toLowerCase().equals("move left")) {
                    player.moveLeft();
                    System.out.println("Player moved left");
                } else if (command.toLowerCase().equals("move up")) {
                    player.moveUp();
                    System.out.println("Player moved up");
                } else if (command.toLowerCase().equals("move down")) {
                    player.moveDown();
                    System.out.println("Player moved down");
                } else if (command.toLowerCase().equals("exit")) {
                    isRunning = false;
                }

                // Admin commands:
                if (command.toLowerCase().equals("print world")) {
                    System.out.println("\n" + "Printing world=====================");

                    System.out.println("Tick: " + currentTick);

                    for (Entity entity : world.getAllEntities().values()) {
                        System.out.println(entity.toString());
                    }
                }
                else if (command.toLowerCase().equals("create squirrel")) {
                    synchronized (world) {
                        int x = (int) (Math.random() * world.getWidth());
                        int y = (int) (Math.random() * world.getHeight());
                        Squirrel squirrel = new Squirrel(x, y, world.getAllEntities().size() + 1);
                        world.addEntity(squirrel);
                        System.out.println("Squirrel created at (" + x + ", " + y + ")");
                    }
                }
            }
            input.close();
        });
        userInputThread.start();
    }

    private void startTickThread(World world) {
        System.out.println("Starting tick thread...");
        Thread tickThread = new Thread(() -> {
            while (isRunning) {
                currentTick++;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        tickThread.start();
    }

    private void createRandomEntities(World world) {
        for (int i = 0; i < 5; i++) {
            int x = (int) (Math.random() * world.getWidth());
            int y = (int) (Math.random() * world.getHeight());
            Squirrel squirrel = new Squirrel(x, y, i + 2);
            world.addEntity(squirrel);
            System.out.println("Squirrel created at (" + x + ", " + y + ")");
        }
    }
}