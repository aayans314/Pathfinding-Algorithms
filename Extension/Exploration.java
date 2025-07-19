import java.util.Random;

public class Exploration {
    public static void explorationOne() throws InterruptedException {

        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int found = 0;
            for (int j = 0; j < 10000; j++) {

                Maze myMaze = new Maze(30, 30, (double) i / 100);
                MazeAStarSearch as = new MazeAStarSearch(myMaze);
                // MazeDepthFirstSearch dfs = new MazeDepthFirstSearch(myMaze);
                // MazeBreadthFirstSearch bfs = new MazeBreadthFirstSearch(myMaze);
                Cell start = myMaze.get(random.nextInt(myMaze.getRows()), random.nextInt(myMaze.getCols()));
                Cell target = myMaze.get(random.nextInt(myMaze.getRows()), random.nextInt(myMaze.getCols()));
                start.setType(CellType.FREE);
                target.setType(CellType.FREE);

                LinkedList<Cell> path = as.search(start, target, false, 0);
                if (path != null)
                    found++;

            }
            System.out.println("Average finding case for 10000 iterations for density " + (double) i / 100 + " is "
                  + (double) found / 100);
           // System.out.println((double) i/100 + " "+ (double) found/100); 
        }
    }

    public static void explorationTwo() throws InterruptedException {
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            int Asize = 0;
            int Bsize = 0;
            int Dsize = 0;
            for (int j = 0; j < 1000; j++) {

                Maze myMaze = new Maze(30, 30, (double) i / 10);
                MazeAStarSearch as = new MazeAStarSearch(myMaze);
                MazeDepthFirstSearch dfs = new MazeDepthFirstSearch(myMaze);
                MazeBreadthFirstSearch bfs = new MazeBreadthFirstSearch(myMaze);
                Cell start = myMaze.get(random.nextInt(myMaze.getRows()), random.nextInt(myMaze.getCols()));
                Cell target = myMaze.get(random.nextInt(myMaze.getRows()), random.nextInt(myMaze.getCols()));
                start.setType(CellType.FREE);
                target.setType(CellType.FREE);

                LinkedList<Cell> pathA = as.search(start, target, false, 0);
                if (pathA != null) {
                    myMaze.reset();
                    LinkedList<Cell> pathB = bfs.search(start, target, false, 0);
                    myMaze.reset();
                    LinkedList<Cell> pathD = dfs.search(start, target, false, 0);
                    myMaze.reset();

                    Asize += pathA.size();
                    Bsize += pathB.size();
                    Dsize += pathD.size();

                } 
                    
            }

            System.out.println("Average size from A Star (for 30*30 and density " + (double) i / 10 + ") is "
                    + (double) Asize / 1000);
            System.out.println("Average size from BFS (for 30*30 and density " + (double) i / 10 + ") is "
                    + (double) Bsize / 1000);
            System.out.println("Average size from DFS (for 30*30 and density " + (double) i / 10 + ") is "
                    + (double) Dsize / 1000);
                System.out.println();
            //System.out.println((double) i/10 + " "+(double) Asize/1000 + " "+(double) Bsize/1000 + " "+(double) Dsize/1000 );
        }
    }

    public static void explorationThree() throws InterruptedException {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int Asize = 0;
            int Bsize = 0;
            int Dsize = 0;
            for (int j = 0; j < 1000; j++) {

                Maze myMaze = new Maze(30, 30, (double) i / 10);
                MazeAStarSearch as = new MazeAStarSearch(myMaze);
                MazeDepthFirstSearch dfs = new MazeDepthFirstSearch(myMaze);
                MazeBreadthFirstSearch bfs = new MazeBreadthFirstSearch(myMaze);
                Cell start = myMaze.get(random.nextInt(myMaze.getRows()), random.nextInt(myMaze.getCols()));
                Cell target = myMaze.get(random.nextInt(myMaze.getRows()), random.nextInt(myMaze.getCols()));
                start.setType(CellType.FREE);
                target.setType(CellType.FREE);

                LinkedList<Cell> pathA = as.search(start, target, false, 0);
                if (pathA != null) {
                    for(Cell cell : myMaze){
                        if (cell.getPrev()!=null) Asize++;
                    }
                    myMaze.reset();
                    bfs.search(start, target, false, 0);
                    for(Cell cell : myMaze){
                        if (cell.getPrev()!=null) Bsize++;
                    }
                    myMaze.reset();
                    dfs.search(start, target, false, 0);
                    for(Cell cell : myMaze){
                        if (cell.getPrev()!=null) Dsize++;
                    }
                    myMaze.reset();                  

                } 
                    
            }

           System.out.println("Average num of cells explored from A Star (for 30*30 and density " + (double) i / 10 + ") is "
                   + (double) Asize / 1000);
           System.out.println("Average num of cells explored from BFS (for 30*30 and density " + (double) i / 10 + ") is "
                   + (double) Bsize / 1000);
           System.out.println("Average num of cells explored from DFS (for 30*30 and density " + (double) i / 10 + ") is "
                   + (double) Dsize / 1000);
               System.out.println();
           //System.out.println((double) i/10 + " "+(double) Asize/1000 + " "+(double) Bsize/1000 + " "+(double) Dsize/1000 );
        }
    }

    

    public static void main(String[] args) throws InterruptedException {
        explorationOne();
        System.out.println();
        explorationTwo();
        System.out.println();
        explorationThree();
        System.out.println();
    }
}
