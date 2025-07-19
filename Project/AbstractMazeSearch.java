import java.awt.Color;
import java.awt.Graphics;

/**
 * Author: Aayan Shah
 * Filename: AbstractMazeSearch.java
 * Date Last modified:17th Nov, 2024
 * File Purpose: Abstract Class for all search algorithms
 */

public abstract class AbstractMazeSearch {

    /**
     * a Maze object that will be the basis for the search
     */
    private Maze maze;
    /**
     * the Cell which the search starts from
     */
    private Cell start;
    /**
     * the Cell which the search is trying to reach
     */
    private Cell target;
    /**
     * the Cell where the search is currently at
     */
    private Cell cur;

    /**
     * saves the given Maze to the relevant field
     * and sets cur, start, and target to be null.
     * 
     * @param maze maze to search on
     */
    public AbstractMazeSearch(Maze maze) {
        this.maze = maze;
        cur = null;
        target = null;
        start = null;
    }

    /**
     * @return {@code Cell}the next Cell to explore
     */
    public abstract Cell findNextCell();

    /**
     * adds the given cell to whatever is storing
     * the future cells to explore
     * 
     * @param next adds cell to list of future cells to explore
     */
    public abstract void addCell(Cell next);

    /**
     * updates the cell in the structure
     * 
     * @param next the cell to update in the structure
     */
    public abstract void updateCell(Cell next);

    /**
     * returns number of future cells to explore
     * 
     * @return size of structure holding future cells
     */
    public abstract int numRemainingCells();

    /**
     * returns the underlying Maze
     * 
     * @return the underlying maze
     */
    public Maze getMaze() {
        return this.maze;
    }

    /**
     * returns the target of the search
     * 
     * @return the target cell
     */
    public Cell getTarget() {
        return target;
    }

    /**
     * sets target to given cell
     * @param target what target should be
     */
    public void setTarget(Cell target){
        this.target = target;
    }

    /**
     * sets start to given cell
     * @param start what the start should be
     */
    public void setStart(Cell start){
        this.start = start;
    }
    /**
     * sets the given cell to be current locatoin of search
     * 
     * @param cell the cell to be current location of search
     */
    public void setCur(Cell cell) {
        this.cur = cell;
    }

    /**
     * returns the current location of the search
     * 
     * @return the current location of search
     */
    public Cell getCur() {
        return this.cur;
    }

    /**
     * returns start of the search
     * 
     * @return start of the search
     */
    public Cell getStart() {
        return this.start;
    }

    /**
     * resets current, start and target cells to null
     */
    public void reset() {
        this.cur = null;
        this.start = null;
        this.target = null;
    }

    /**
     * finds a path from the start to specified cell
     * by repeatedly following the prev path
     * 
     * @param cell cell to find the path from start
     * @return {@code LinkedList<Cell>} the path, linkedlist of cells
     */
    public LinkedList<Cell> traceback(Cell cell) {
        LinkedList<Cell> path = new LinkedList<>();
        if(cell == null) return path;
        if (cell.getPrev() == null)
            return path;
            
        while (cell.getPrev() != cell && cell !=null) {
            path.add(cell.getPrev());
            cell = cell.getPrev();
        }
        if(cell != null) path.add(cell);
        if(cell.getPrev() == start){
            path.add(start);
        }
        if (path.size() == 0)
            return null;
        else
            return path;
    }

    /**
     * hmm
     * 
     * @param start  path to start from
     * @param target path to end
     * @return path found by algorithm, {@code null} if not found
     * 
     */
    public LinkedList<Cell> search(Cell start, Cell target, boolean display, int delay) throws InterruptedException {

        setStart(start);
        setCur(start);
        setTarget(target);
        start.setPrev(start);

        addCell(start);
        MazeSearchDisplay landscapeDisplay;
        if (display) {
            landscapeDisplay = new MazeSearchDisplay(this, 20);
        } else {
            landscapeDisplay = null;
        }
        while (numRemainingCells() > 0) {
            if (display) {
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                };
                
                landscapeDisplay.repaint();
            }
            cur = findNextCell();
            for (Cell neighbor : maze.getNeighbors(cur)) {
                if (neighbor.getPrev() == null) {
                    neighbor.setPrev(cur);
                    addCell(neighbor);
                }
                // TODO this migh be wong
                else if (traceback(neighbor).size() > traceback(cur).size() +1 ) {
                    neighbor.setPrev(cur);
                    updateCell(neighbor);
                }

                if (neighbor == target) {
                    return traceback(target);
                }
            }
        }

        return null;
    }

    public void draw(Graphics g, int scale) {
        // Draws the base version of the maze
        getMaze().draw(g, scale);
        // Draws the paths taken by the searcher
        getStart().drawAllPrevs(getMaze(), g, scale, Color.RED);
        // Draws the start cell
        getStart().draw(g, scale, Color.BLUE);
        // Draws the target cell
        getTarget().draw(g, scale, Color.RED);
        // Draws the current cell
        getCur().draw(g, scale, Color.MAGENTA);

        // If the target has been found, draws the path taken by the searcher to reach
        // the target sans backtracking.
        if (getTarget().getPrev() != null) {
            Cell traceBackCur = getTarget().getPrev();
            while (!traceBackCur.equals(getStart())) {
                traceBackCur.draw(g, scale, Color.GREEN);
                traceBackCur = traceBackCur.getPrev();
            }
            getTarget().drawPrevPath(g, scale, Color.BLUE);
        }
    }

}
