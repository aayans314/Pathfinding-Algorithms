public class MazeBreadthFirstSearch extends AbstractMazeSearch{

    //queue to store all next cells
    private Queue<Cell> queue;

    /**
     * does bfs on given maze
     * @param maze maze to search on
     */
    public MazeBreadthFirstSearch(Maze maze){
        super(maze);
        queue = new LinkedList<>();
    }

    /**
     * finds next cell from queue to go to
     * @return {@code cell} next cell to go to
     */
    @Override
    public Cell findNextCell() {
        return queue.poll();
    }

     /**
     * adds given cell to queue
     * @param {@code next} next cell to add to queue
     */
    @Override
    public void addCell(Cell next) {
        queue.offer(next);
    }

    /**
     * updates the given cell
     *@param {@code cell} cell to update status of
     */
    @Override
    public void updateCell(Cell next) {
        //guess we dont need it here either
    }

    /**
     * returns number of cells left to explore
     * @return size of queue
     */
    @Override
    public int numRemainingCells() {
       return queue.size();
    }
    
}
