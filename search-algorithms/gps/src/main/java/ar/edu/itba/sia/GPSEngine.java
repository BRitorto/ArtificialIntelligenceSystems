package ar.edu.itba.sia;

import java.util.*;

import ar.edu.itba.sia.api.Heuristic;
import ar.edu.itba.sia.api.Problem;
import ar.edu.itba.sia.api.Rule;
import ar.edu.itba.sia.api.State;

import static ar.edu.itba.sia.SearchStrategy.IDDFS;

public class GPSEngine {

	Queue<GPSNode> open;
	Map<State, Integer> bestCosts;
	Problem problem;
	long explosionCounter;
	boolean finished;
	boolean failed;
	GPSNode solutionNode;
	Optional<Heuristic> heuristic;

	// Use this variable in open set order.
	protected SearchStrategy strategy;

	public GPSEngine(Problem problem, SearchStrategy strategy, Heuristic heuristic) {
		open = new LinkedList<>();
		bestCosts = new HashMap<>();
		this.problem = problem;
		this.strategy = strategy;
		this.heuristic = null;
		explosionCounter = 0;
		finished = false;
		failed = false;
	}

	public void findSolution() {
		GPSNode rootNode = new GPSNode(problem.getInitState(), 0, null);
		open.add(rootNode);
		// TODO: ¿Lógica de IDDFS?
		while (open.size() > 0) {
			GPSNode currentNode = open.remove();
			// estos comentarios son importantes
			//System.out.println("-----------SACO DE OPEN---------------");
			//System.out.println(currentNode.getState().getRepresentation());
			//System.out.println("--------------------------");
			if (problem.isGoal(currentNode.getState())) {
				finished = true;
				solutionNode = currentNode;
				System.out.println(solutionNode.getState().getRepresentation());
				System.out.println("GANAMOS!");
				return;
			} else {
				explode(currentNode);
			}
		}
		failed = true;
		finished = true;
//		GPSNode rootNode = new GPSNode(problem.getInitState(), 0, 0, null);
//		open.add(rootNode);
//		int currentDepthLimit = strategy == IDDFS ? 1 : Integer.MAX_VALUE;
//		do {
//			while (open.size() > 0 && open.peek().getDepth() <= currentDepthLimit) {
//				GPSNode currentNode = open.remove();
//				System.out.println(currentNode.getState().getRepresentation());
//				if (problem.isGoal(currentNode.getState())) {
//					finished = true;
//					solutionNode = currentNode;
//					return;
//				} else {
//					explode(currentNode);
//				}
//			}
//			if(strategy == IDDFS && open.size() > 0){
//				currentDepthLimit++;
//				open.clear();
//				open.add(rootNode);
//			}
//
//		} while(strategy == IDDFS && open.size() > 0);
//		failed = true;
//		finished = true;
	}

	private void explode(GPSNode node) {
		Collection<GPSNode> newCandidates;
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

			Stack<GPSNode> auxStack = new Stack<>(); // consultar si es grave que esto lo haga un poco mas ineficiente
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

			Stack<GPSNode> auxStack2 = new Stack<>(); // consultar si es grave que esto lo haga un poco mas ineficiente
			auxStack2.addAll(newCandidates);
			while(!auxStack2.isEmpty()){
				((LinkedList<GPSNode>)open).push(auxStack2.pop());
			}

			break;
		case GREEDY:
			newCandidates = new PriorityQueue<>(new Comparator<GPSNode>() {
				@Override
				public int compare(GPSNode node1, GPSNode node2) {
					return 1;//heuristic.getValue(node1.getState()).compareTo(heuristic.getValue(node2.getState()));
				}
			});
			addCandidates(node, newCandidates);
			open.addAll(newCandidates);
			break;
		case ASTAR:
			if (!isBest(node.getState(), node.getCost())) {
				return;
			}
			newCandidates = new PriorityQueue<>(new Comparator<GPSNode>() {
				@Override
				public int compare(GPSNode node1, GPSNode node2) {
					return 1;//(heuristic.getValue(node1.getState())+node1.getCost()).compareTo(heuristic.getValue(node2.getState())+node2.getCost());
				}
			});
			addCandidates(node, newCandidates);
			open.addAll(newCandidates);
			break;
		}
	}


	private void addCandidates(GPSNode node, Collection<GPSNode> candidates) {
		explosionCounter++;
		updateBest(node);
		for (Rule rule : (List<Rule>)problem.getRules()) {
			Optional<State> newState = rule.apply(node.getState());
			if (newState.isPresent()) {
				GPSNode newNode = new GPSNode(newState.get(), node.getCost() + rule.getCost(), rule);
				newNode.setParent(node);
				// estos comentarios son importantes
//				System.out.println("******NEW NODE CANDIDATE******");
//				System.out.println(newNode.getState().getRepresentation());
//				System.out.println("************");
				candidates.add(newNode);
			}
		}
//		explosionCounter++;
//		updateBest(node);
//		for (Object obj : problem.getRules()) {
//			Rule rule = (Rule) obj;
//			Optional<State> newState = rule.apply(node.getState());
//			if (newState.isPresent()) {
//				GPSNode newNode = new GPSNode(newState.get(), node.getCost() + rule.getCost(), node.getDepth() + 1, rule);
//				newNode.setParent(node);
//				candidates.add(newNode);
//			}
//		}
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
