package ar.edu.itba.sia;

import java.util.*;

import ar.edu.itba.sia.api.Heuristic;
import ar.edu.itba.sia.api.Problem;
import ar.edu.itba.sia.api.Rule;
import ar.edu.itba.sia.api.State;

import static ar.edu.itba.sia.SearchStrategy.IDDFS;

public class GPSEngine {

	private Queue<GPSNode> open;
	private Map<State, Integer> bestCosts;
	private Problem problem;
	private long explosionCounter;
	private boolean finished;
	private boolean failed;
	private GPSNode solutionNode;
	private Optional<Heuristic> heuristic;
	private long currentDepthLimit;

	// Use this variable in open set order.
	protected SearchStrategy strategy;

	public GPSEngine(Problem problem, SearchStrategy strategy, Heuristic heuristic) {
		open = new LinkedList<>();
		bestCosts = new HashMap<>();
		this.problem = problem;
		this.strategy = strategy;
		if(heuristic == null){
			this.heuristic = Optional.empty();
		}
		else{
			this.heuristic = Optional.of(heuristic);
		}
		explosionCounter = 0;
		finished = false;
		failed = false;
		currentDepthLimit = strategy == IDDFS ? 1 : Integer.MAX_VALUE;
	}

	public void findSolution() {
		long iddfsTotalExplosionCounter = 0;
		long lastExplosionCounter = explosionCounter;
		Heuristic myHeuristic;
		do {
			GPSNode rootNode = new GPSNode(problem.getInitState(), 0, null);
			//System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<current depth limit =" + currentDepthLimit);
			open.add(rootNode);
			explosionCounter = 0;
			while (open.size() > 0) {
				GPSNode currentNode = open.remove();
				// estos comentarios son importantes
//				//System.out.println(" depth = " +currentNode.getDepth());
				System.out.println("-----------SACO DE OPEN---------------");
				System.out.println(currentNode.getState().getRepresentation());
				System.out.println("--------------------------");
				System.out.println("cost = "+ currentNode.getCost());
				System.out.println("depth = "+ currentNode.getDepth());
				if(this.heuristic.isPresent())
					myHeuristic = this.heuristic.get();
				else
					throw new RuntimeException();
				System.out.println("h ="+ myHeuristic.getValue(currentNode.getState()));
				if (problem.isGoal(currentNode.getState())) {
//					System.out.println("Entro isGoal de engine");
					finished = true;
					solutionNode = currentNode;
					if(strategy == IDDFS)
						explosionCounter = iddfsTotalExplosionCounter;
					System.out.println("GANAMOS!");
					System.out.println("exploded nodes = "+explosionCounter);
					return;
				} else {
					explode(currentNode);
				}
			}
			if(strategy == IDDFS){
				if(lastExplosionCounter == explosionCounter){
					failed = true;
					finished = true;
					explosionCounter = iddfsTotalExplosionCounter;
					//System.out.println("exploded nodes = "+explosionCounter);
				}else{
					open.clear();
					currentDepthLimit++;
					lastExplosionCounter = explosionCounter;
					iddfsTotalExplosionCounter += explosionCounter;
				}
			}
			else{
				failed = true;
				finished = true;
				//System.out.println("exploded nodes = "+explosionCounter);
			}
		}while(strategy == IDDFS && !finished);
	}

	private void explode(GPSNode node) {
		Collection<GPSNode> newCandidates;
		Heuristic myHeuristic;
		Stack<GPSNode> auxStack;
		switch (strategy) {
		case BFS:
			if (bestCosts.containsKey(node.getState())) {
				return;
			}
			newCandidates = new ArrayList<>();
			addCandidates(node, newCandidates);
			open.addAll(newCandidates);
			break;
		case DFS:
			if (bestCosts.containsKey(node.getState())) {
				return;
			}
			newCandidates = new ArrayList<>();
			addCandidates(node, newCandidates);

			auxStack = new Stack<>(); // consultar si es grave que esto lo haga un poco mas ineficiente
			auxStack.addAll(newCandidates);

			while(!auxStack.isEmpty()){
				((LinkedList<GPSNode>)open).push(auxStack.pop());
			}

			break;
		case IDDFS:
			if (bestCosts.containsKey(node.getState())) {
				return;
			}
			newCandidates = new ArrayList<>();
			addCandidates(node, newCandidates);
			if(!newCandidates.isEmpty() && ((ArrayList<GPSNode>) newCandidates).get(0).getDepth()<=currentDepthLimit) {
				Stack<GPSNode> auxStack2 = new Stack<>(); // consultar si es grave que esto lo haga un poco mas ineficiente
				auxStack2.addAll(newCandidates);
				while (!auxStack2.isEmpty()) {
					((LinkedList<GPSNode>) open).push(auxStack2.pop());
				}
			}
			break;
		case GREEDY:
			if(this.heuristic.isPresent())
				myHeuristic = this.heuristic.get();
			else
				throw new RuntimeException(); //nachito hace una exception para cuando falta la heuristica;
			newCandidates = new PriorityQueue<>(new Comparator<GPSNode>() {
				@Override
				public int compare(GPSNode node1, GPSNode node2) {
					return myHeuristic.getValue(node1.getState()).compareTo(myHeuristic.getValue(node2.getState()));
				}
			});
			addCandidates(node, newCandidates);
			auxStack = new Stack<>(); // consultar si es grave que esto lo haga un poco mas ineficiente
			auxStack.addAll(newCandidates);

			while(!auxStack.isEmpty()){
				((LinkedList<GPSNode>)open).push(auxStack.pop());
			}
			break;
		case ASTAR:
			if (!isBest(node.getState(), node.getCost())) {
				return;
			}
			if(this.heuristic.isPresent())
				myHeuristic = this.heuristic.get();
			else
				throw new RuntimeException(); //nachito hace una exception para cuando falta la heuristica;
			newCandidates = new PriorityQueue<>(new Comparator<GPSNode>() {
				@Override
				public int compare(GPSNode node1, GPSNode node2) {
					return (new Integer(myHeuristic.getValue(node1.getState())+node1.getCost())).compareTo(
							new Integer(myHeuristic.getValue(node2.getState())+node2.getCost()));
				}
			});
			addCandidates(node, newCandidates);
			auxStack = new Stack<>(); // consultar si es grave que esto lo haga un poco mas ineficiente
			auxStack.addAll(newCandidates);

			while(!auxStack.isEmpty()){
				((LinkedList<GPSNode>)open).push(auxStack.pop());
			}
			break;
		}
	}


	private void addCandidates(GPSNode node, Collection<GPSNode> candidates) {
		explosionCounter++;
		updateBest(node);
		Heuristic myHeuristic;
		for (Rule rule : (List<Rule>)problem.getRules()) {
			Optional<State> newState = rule.apply(node.getState());
			if (newState.isPresent()) {
				GPSNode newNode = new GPSNode(newState.get(), node.getCost() + rule.getCost(), node.getDepth()+1, rule);
				newNode.setParent(node);
				 //estos comentarios son importantes
				System.out.println("******NEW NODE CANDIDATE******");
				System.out.println(newNode.getState().getRepresentation());
				System.out.println( ">>>>>> depth = "+newNode.getDepth() );
				if(this.heuristic.isPresent())
					myHeuristic = this.heuristic.get();
				else
					throw new RuntimeException();
				System.out.println("h ="+ myHeuristic.getValue(newNode.getState()));
				System.out.println("************");
				candidates.add(newNode);
			}
		}
	}

	private boolean isBest(State state, Integer cost) {
		return !bestCosts.containsKey(state) || cost < bestCosts.get(state);
	}

	private void updateBest(GPSNode node) {
		bestCosts.put(node.getState(), node.getCost());
	}

	// GETTERS FOR THE PEOPLE!

	public Queue<GPSNode> getOpen() {
		return open;
	}

	public Map<State, Integer> getBestCosts() {
		return bestCosts;
	}

	public Problem getProblem() {
		return problem;
	}

	public long getExplosionCounter() {
		return explosionCounter;
	}

	public boolean isFinished() {
		return finished;
	}

	public boolean isFailed() {
		return failed;
	}

	public GPSNode getSolutionNode() {
		return solutionNode;
	}

	public SearchStrategy getStrategy() {
		return strategy;
	}

}
