import java.util.Comparator;
import java.util.Random;

public class MazeHumanSearch extends AbstractMazeSearch {

    private PriorityQueue<Cell> priorityQueue;

    private Comparator<Cell> comparator;

    public MazeHumanSearch(Maze maze) {
        super(maze);
        this.comparator = new Comparator<Cell>() {
            public int compare(Cell cell1, Cell cell2) {

                LinkedList<Cell> Neighbor1 = maze.getNeighbors(cell1);
                LinkedList<Cell> Neighbor2 = maze.getNeighbors(cell2);

                //Favouring going towards cells with more 
                //neighbors to go to
                int goodNeighbor1 = 0;
                int goodNeighbor2 = 0;
                for (Cell cell : Neighbor1) {
                    if (cell.getType() == CellType.FREE)
                        goodNeighbor1++;
                    else
                        goodNeighbor1--;
                }
                for (Cell cell : Neighbor2) {
                    if (cell.getType() == CellType.FREE)
                        goodNeighbor2++;
                    else
                        goodNeighbor2--;
                }

                //Also implementing euclidean distance
                //Also prioritizing distance from cell to target over 
                //factor to get to the cell by multiplying by 5
                //Lasting adding the neighbor factor as half as imp as distance
                //to target factor
                double numStepsCell1 = traceback(cell1).size();
                double estimatedDistance1 = Math.sqrt(Math.pow((Math.abs(getTarget().getRow() - cell1.getRow())), 2)
                        + Math.pow((Math.abs(getTarget().getCol() - cell1.getCol())), 2));
                double sumSteps1 = numStepsCell1 + 5 * estimatedDistance1 + estimatedDistance1 * 0.5 * goodNeighbor1;
                double numStepsCell2 = traceback(cell2).size();
                double estimatedDistance2 = Math.sqrt(Math.pow((Math.abs(getTarget().getRow() - cell2.getRow())), 2)
                        + Math.pow((Math.abs(getTarget().getCol() - cell2.getCol())), 2));
                double sumSteps2 = numStepsCell2 + 5 * estimatedDistance2 + estimatedDistance2 * 0.5 * goodNeighbor2;
                return Double.compare(sumSteps1, sumSteps2);

            }
        };
        priorityQueue = new Heap<>(comparator);

    }

    public static void main(String[] args) throws InterruptedException{
        Random random = new Random();
        Maze myMaze = new Maze(50, 50, 0.2);
        MazeHumanSearch hs = new MazeHumanSearch(myMaze);
        Cell start = myMaze.get(random.nextInt(myMaze.getRows()), random.nextInt(myMaze.getCols()));
                Cell target = myMaze.get(random.nextInt(myMaze.getRows()), random.nextInt(myMaze.getCols()));
                start.setType(CellType.FREE);
                target.setType(CellType.FREE);
        hs.search(start, target, true, 100);
    }
    public static void main2(String[] args) throws InterruptedException {

        //For extension I am only using maze size 50*50
        for (double density = 0; density < 100; density+=1) {
            int Hsize = 0;
            int Asize = 0;
            int Hpath=0;
            int offset=0;
            
            Random random = new Random();
            for (int i = 0; i < 10000; i++) {
                Maze myMaze = new Maze(50, 50, (double) density / 100);
                // MazeBreadthFirstSearch as = new MazeBreadthFirstSearch(myMaze);
                MazeHumanSearch hs = new MazeHumanSearch(myMaze);
                // MazeDepthFirstSearch as = new MazeDepthFirstSearch(myMaze);
                MazeAStarSearch as = new MazeAStarSearch(myMaze);
                Cell start = myMaze.get(random.nextInt(myMaze.getRows()), random.nextInt(myMaze.getCols()));
                Cell target = myMaze.get(random.nextInt(myMaze.getRows()), random.nextInt(myMaze.getCols()));
                start.setType(CellType.FREE);
                target.setType(CellType.FREE);
                LinkedList<Cell> hpath = hs.search(start, target, false, 0);
                //Finding total num cells visited
                //as for every cell traversed
                //it will have its prev fulfilled
                if(hpath!=null){
                for (Cell cell : myMaze) {
                    if (cell.getPrev() != null)
                        Hsize++;
                }
                myMaze.reset();
                as.search(start, target, false, 0);
                for (Cell cell : myMaze) {
                    if (cell.getPrev() != null)
                        Asize++;
                }

                //Calculating offset for path lenght incase not found
                
                Hpath += hpath.size();
                }else{
                    offset++;
                }
                
            }
            //offset only to path length because even when no solution, we need to see num of cells visited
            System.out.println((double) density / 100 + ", " + (double) Hsize / 10000 + ", " + (double) Asize / 10000+", " + (double) Hpath / (10000-offset));
        }
    }

    @Override
    public Cell findNextCell() {
        return priorityQueue.poll();
    }

    @Override
    public void addCell(Cell next) {
        priorityQueue.offer(next);
    }

    @Override
    public void updateCell(Cell next) {
        priorityQueue.updatePriority(next);
    }

    @Override
    public int numRemainingCells() {
        return priorityQueue.size();
    }

}
