/**
 * Class that finds the shortest path from the start to the exit of a dungeon and prints the path length
 * @author Abdullah Elganainy
 */
public class FindShortestPath {
    // private instance variables that are used to store values for the shortest path
    private static DLPriorityQueue<Hexagon> queue;
    private static Dungeon dung;
    private static int D;

    /**
     * using helper methods the shortest path is determined by the number of hexagons from the start to the exit
     * @param args
     */
    public static void main (String[] args) {
        try {
            if(args.length < 1) { // if no file is specified then throws an exception that indicates that there is no file specified
                throw new Exception("No input file specified");
            }
            queue = new DLPriorityQueue<Hexagon>(); // creates an empty queue
            String dungeonFileNames = args[0];
            dung = new Dungeon(dungeonFileNames); // sets dung to the dungeon created by the file
            Hexagon chamber = dung.getStart();
            queue.add(chamber, 0);
            chamber.markEnqueued();
            while(!queue.isEmpty() && !chamber.isExit()) { // loop through until the exit chamber is found or the queue is empty
                chamber = queue.removeMin();
                chamber.markDequeued();
                if(!hasDragons(chamber)) { // if there are no dragons in the chamber or its neighbours then call the checkNeighbours method
                    checkNeighbours(chamber);
                }
            }
            if(chamber.isExit()) { // if the exit was found then print the length of the path
                System.out.println(String.format("Path of length %d found", D));
            } else { // otherwise print that there were no paths found
                System.out.println("No path found");
            }
        } catch (InvalidDungeonCharacterException e) { // if there is a wrong character in the dungeon.txt files then prints the error message
            System.out.println("There is a non existent character in the dungeon file");
        } catch (Exception e) { // if there is an exception thrown that is not specified then print the error message
            System.out.println(e.getMessage());
        }
    }

    /**
     * finds if the chamber or any of its neighbouring chambers is a dragon
     * @param chamber
     * @return boolean true or false
     */
    private static boolean hasDragons(Hexagon chamber) {
        for(int i = 0; i <= 5; i++) { // loop through all of the possible neighbouring chambers
            try {
                Hexagon neighbour = chamber.getNeighbour(i);
                if(neighbour != null) { // if the neighbouring chamber is not null, then checks if the chamber or its neighbouring chambers is a dragon
                    if(neighbour.isDragon() || chamber.isDragon()) {
                        return true;
                    }
                }
            } catch (InvalidNeighbourIndexException e) { // if the neighbour index is invalid then prints the error message
                System.out.println("The neighbour index being called does not exist");
            }
        }
        return false;
    }
    
    /**
     * checks if the neighbouring chambers are valid paths for the dungeon
     * @param chamber
     */
    private static void checkNeighbours(Hexagon chamber) {
        for(int i = 0; i <= 5; i++) { // loop through all of the possible neighbouring chambers
            try {
                Hexagon neighbour = chamber.getNeighbour(i);
                if(neighbour != null && !neighbour.isWall() && !neighbour.isMarkedDequeued()) { // if the neighbouring chamber is not null, a wall, or marked as dequed. then continue
                    D = 1 + chamber.getDistanceToStart(); // sets D as the distance from the chamber to the start of the dungeon including the starting chamber
                    if(neighbour.getDistanceToStart() > D) {
                        neighbour.setDistanceToStart(D);
                        neighbour.setPredecessor(chamber);
                        if(neighbour.isMarkedEnqueued()) { // if the neighbour is marked as enqueued then update its priority to the distance to the start of the dungeon plus the distance to the exit of the dungeon
                            queue.updatePriority(neighbour, neighbour.getDistanceToStart() + neighbour.getDistanceToExit(dung));
                        } else { // otherwise add the neighbour to the queue and mark it as enqueued
                            queue.add(neighbour, neighbour.getDistanceToStart() + neighbour.getDistanceToExit(dung));
                            neighbour.markEnqueued();
                        }
                    }
                }
            } catch (InvalidNeighbourIndexException e) { // if the neighbour index is invalid then prints the error message
                System.out.println("The neighbour index being called does not exist");
            }
        }
    }
}