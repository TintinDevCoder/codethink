package bishi.xinye;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main1 {
    public static class StateMachine<TState, TEvent> {
        private TState currentState;
        private Map<TState, Map<TEvent, TState>> transitions = new HashMap<>();
        public StateMachine(TState initialState) {
            this.currentState = initialState;
        }
        public void addTransition(TState from, TEvent event, TState to) {
            transitions.putIfAbsent(from, new HashMap<>());
            transitions.get(from).put(event, to);
        }
        public boolean fireEvent(TEvent event) {
            Map<TEvent, TState> eventMap = transitions.get(currentState);
            if (eventMap != null && eventMap.containsKey(event)) {
                currentState = eventMap.get(event);
                return true;
            }
            return false;
        }
        public TState getCurrentState() {
            return currentState;
        }
    }

    public static void main(String[] args) {
        StateMachine<String, String> stateMachine = new StateMachine<>("DRAFT");
        stateMachine.addTransition("DRAFT", "SUBMIT","REVIEW");
        stateMachine.addTransition("REVIEW", "REJECT","DRAFT");
        stateMachine.addTransition("REVIEW", "PUBLISH","PUBLISHED");
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String event = sc.next();
            stateMachine.fireEvent(event);
            System.out.print(stateMachine.getCurrentState() + ",");
        }
        sc.close();
    }
}
