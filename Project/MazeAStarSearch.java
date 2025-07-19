import java.util.Comparator;

public class MazeAStarSearch extends AbstractMazeSearch{

    //Priority Queue to store future cells
    private PriorityQueue<Cell> priorityQueue;

    private Comparator<Cell> comparator;

    /**
     * constructor for a star search
     * @param maze maze to search on
     */
    public MazeAStarSearch(Maze maze){
        super(maze);
        this.comparator = new Comparator<Cell>() {
            public int compare (Cell cell1, Cell cell2){
                
                int numStepsCell1 = traceback(cell1).size();
                int estimatedDistance1 = Math.abs(getTarget().getRow() - cell1.getRow())+ Math.abs(getTarget().getCol() - cell1.getCol());
                int sumSteps1 = numStepsCell1 + estimatedDistance1;
                int numStepsCell2 = traceback(cell2).size();
                int estimatedDistance2 = Math.abs(getTarget().getRow() - cell2.getRow())+ Math.abs(getTarget().getCol() - cell2.getCol());
                int sumSteps2 = numStepsCell2 + estimatedDistance2;
                return Integer.compare(sumSteps1, sumSteps2);

                
            }
        };
        priorityQueue = new Heap<>(comparator);
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