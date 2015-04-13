package AC2015;

/**
 * @author Alexandre & Clémence MÈGE
 *
 * This project
 * 
 * This project is used to solve Google Hash Code 2015 extended run.
 * It has been used to achieve 707237 points, achieving second place in the extended Run.
 * 
 * Apart from parsing input (in ReadInput), and writing output, it also has a mean to store and retrieve serialized solution to disk to restart from previous solution.
 * It uses the BestSolutionInProcess.ser file for that.
 * Each time a new imporved solution is found, it is saved to the disk with the zip of the program, for easy commit.
 * 
 * The algorithm used is mostly greedy, with some bactracking.
 * 
 * 
 * The algorithm has 3 main Parts:
 * 1) Finding the best possible path for one ballon, assuming all the other ballon are fixed. This is performed in OptimizeBallon class.
 * This is based on a dynamic program. For each time, I compute the maximum score for each position, using the scores ate the previous time. at the end, the best path is recontstructed
 * using standard, dijsktra like father list.
 * 
 * At the start of "optimize" function, the effect of the selected Ballon is removed, then "findBestPath" is called and returns the path with the best score. This path is then use to update the view.
 * 
 * Some optimizations are done over direct implementation:
 *   => Heavy use of Java parallel capability. Using optimisations and parallelization,the duration for one loop has been reduced from 40 seconds to 1.3s. More improvements may be aachievable by direct coding in C.
 *   => The score counts cibles that are covered by at least one Ballon. It also counts the cibles that are covered by only two ballons. The idea is that if a cible is covered by 2 Ballon, then at next update, one of the Ballon could move to improve score.
 *   
 * 
 * 2) When no improvements are possible, a backtracking is performed. During this backtracking, the score givent to cibles covered by more than one Ballon is increased (parameter PARAMAVOID). This allows exploring cases where more cells are covered by 2 Ballon, 
 * even if it somehow reduces the number of cells covered by at least one Ballon.
 *
 * 3) Final improvement is the optimization of pairs of paths. This is performed in OptimizePairBallon.
 * This class will take two Ballon as input, and will try to first improve a small part of the path of the Ballon.
 * taking a small time window (ex : 12 cycles), it will try to find the best paths for the two Ballon during this time window, keeping the same start point and end point for the two Ballon.
 * Finally, the final moves (for around 8 cycles) of the two Ballons are optimized toogether.
 * This final improvement step could be greatly optimized in term of efficiency and parallelization. However, it was able to increase the score by at least 1000 points.
 * The possible paths for the two Ballon are first enumerated, then a function computes the best pair of path.
 * The enumeration process has some optimisations : exploration is stopped as soon as possible, and similar paths (i.e. paths which cover the same uncovered cibles at the same time) are cleaned.
 * 
 * * Possible improvements : 
 * a) more pruning of candidate path is possible, first by removing cibles that are covered for all paths.
 * b) A non exhaustive search, based on simulated annealing or tabu search may allow improvement of longer segments while still getting closer to the optimal pair of paths.
 * c) Earlier pruning of poor paths may allow a deeper search.
 * 
 *
 *
 *
 *
 *
 *Other optimizations have been tried and are still in the code. 
 *	* for step 1, one optimization tried to take to Ballons, and consider a set of best paths for each Ballon, then trying to find the best pair. However it yielded no improvements.
 *	* For step two, trying to change the PARAMAVOID parameter in a simulated annealing way.
 *	
 *
 */
public class README {

}
