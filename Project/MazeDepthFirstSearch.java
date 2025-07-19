public class MazeDepthFirstSearch extends AbstractMazeSearch {

    //Stack for depth first search
    private Stack<Cell> stack;


    /**
     * does dfs on given maze
     * @param maze maze to search on
     */
    public MazeDepthFirstSearch(Maze maze){
        super(maze);
        stack = new LinkedList<>();
    }

    @Override
    /**
     * finds next cell from stack to go to
     * @return {@code cell} next cell to go to
     */
    public Cell findNextCell() {
        return stack.pop();
    }

    /**
     * adds given cell to stack
     * @param {@code next} next cell to add to stack
     */
    @Override
    public void addCell(Cell next) {
        stack.push(next);
        
    }

    /**
     * updates the given cell
     *@param {@code cell} cell to update status of
     */
    @Override
    public void updateCell(Cell next) {
        //guess we wont need it for now
    }

    /**
     * returns number of cells left to explore
     * @return size of stack
     */
    @Override
    public int numRemainingCells() {
        return stack.size();
    }
    
}
