import java.util.Comparator;
import java.util.Random;

public class MazeAStarSearch extends AbstractMazeSearch {

    // Priority Queue to store future cells
    private PriorityQueue<Cell> priorityQueue;

    private Comparator<Cell> comparator;

    /**
     * constructor for a star search
     * 
     * @param maze maze to search on
     */
    public MazeAStarSearch(Maze maze) {
        super(maze);
        this.comparator = new Comparator<Cell>() {
            public int compare(Cell cell1, Cell cell2) {

                //During extensions, figured I
                //could improve my A* by doing
                //5*estimatedDistancex
                double numStepsCell1 = traceback(cell1).size();
                double estimatedDistance1 = Math.abs(getTarget().getRow() - cell1.getRow())
                        + Math.abs(getTarget().getCol() - cell1.getCol());
                double sumSteps1 = numStepsCell1 + 5*estimatedDistance1;
                double numStepsCell2 = traceback(cell2).size();
                double estimatedDistance2 = Math.abs(getTarget().getRow() - cell2.getRow())
                        + Math.abs(getTarget().getCol() - cell2.getCol());
                double sumSteps2 = numStepsCell2 + 5*estimatedDistance2;
                return Double.compare(sumSteps1, sumSteps2);

            }
        };
        priorityQueue = new Heap<>(comparator);
    }

    public static void main(String[] args) throws InterruptedException {
        Maze myMaze = new Maze(50, 50, 0.0);
        MazeBreadthFirstSearch as = new MazeBreadthFirstSearch(myMaze);
        //MazeDepthFirstSearch as = new MazeDepthFirstSearch(myMaze);
        //MazeAStarSearch as = new MazeAStarSearch(myMaze);
        Random random = new Random();
        Cell start = myMaze.get(random.nextInt(myMaze.getRows()), random.nextInt(myMaze.getCols()));
        Cell target = myMaze.get(random.nextInt(myMaze.getRows()), random.nextInt(myMaze.getCols()));
        start.setType(CellType.FREE);
        target.setType(CellType.FREE);
        as.search(start, target, true, 10);
        System.out.println(myMaze);

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