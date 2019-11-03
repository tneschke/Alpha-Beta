import java.util.List;

public class AlphaBetaPruning {
	private int bestMove;   
	private int nodesVisited;    
	private int nodesEvaluated;     
	private int minDepth;           
	private double numberBranches;
	private double numberBranched;
	private double moveValue;
	private int maxDepth;
     
    public AlphaBetaPruning() {
    }

    public void printStats() {	
    	System.out.println("Move: " + this.bestMove);
    	System.out.println("Value: " + (this.moveValue == 0 ? 1: -1)*this.moveValue);
    	System.out.println("Number of Nodes Visited: " + this.nodesVisited);
    	System.out.println("Number of Nodes Evaluated: " + this.nodesEvaluated);
    	System.out.println("Max Depth Reached: " + this.maxDepth);
    	System.out.println("Avg Effective Branching Factor: " + Math.round(this.numberBranches/this.numberBranched*10.0)/10.0);
    }

    /**
     * This function will start the alpha-beta search
     * @param state This is the current game state
     * @param depth This is the specified search depth
     */
    public void run(GameState state, int depth) {
        this.minDepth = depth;
        int count = 0;
        for(int i = 1; i <= state.getSize(); i++) {
        	if(!state.getStone(i)) count++;
        }
        
        this.moveValue = this.alphabeta(state, depth, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, true);
        this.maxDepth = depth - this.minDepth;
        if (count%2 == 0) this.moveValue = this.moveValue *(-1);
        
    }

    /**
     * This method is used to implement alpha-beta pruning for both 2 players
     * @param state This is the current game state
     * @param depth Current depth of search
     * @param alpha Current Alpha value
     * @param beta Current Beta value
     * @param maxPlayer True if player is Max Player; Otherwise, false
     * @return int This is the number indicating score of the best next move
     */
    private double alphabeta(GameState state, int depth, double alpha, double beta, boolean maxPlayer) {
    	if(depth < this.minDepth) this.minDepth = depth;
        List<GameState> successors = state.getSuccessors();
        this.nodesVisited++;
        if(successors.size() == 0) {
            this.nodesEvaluated++;
        	if (maxPlayer) return -1.0;
        	else return 1.0;
        }
        if(depth == 0) {
            this.nodesEvaluated++;
        	if(maxPlayer) return state.evaluate();
        	else return -(state.evaluate());
        }
        this.numberBranched++;
        if(maxPlayer) {
        	double best = Double.NEGATIVE_INFINITY;
        	for(GameState successor : successors) {
                this.numberBranches++;
        		double value = alphabeta(successor, depth-1, alpha, beta, false);
        		if(value > best) this.bestMove = successor.getLastMove();
        		best = Math.max(best, value);
        		alpha = Math.max(alpha, best);
        		if (beta <= alpha ) break;
        	}
        	return best;
        }
        else {
        	double best = Double.POSITIVE_INFINITY;
        	for(GameState successor : successors) {
                this.numberBranches++;
        		double value = alphabeta(successor, depth-1, alpha, beta, true);
        		best = Math.min(best, value);
        		beta = Math.min(beta, best);
        		if (beta <= alpha ) break;
        	}
        	return best;
        }
    }
}
