package classes.Methods;

import java.util.*;

public class DateTreeNode {

    private String label;
    private Map<String, DateTreeNode> children;
    private ArrayList<Integer> expenseIndices;
    private NodeType type;

    public enum NodeType {
        YEAR,
        MONTH,
        DAY,
    } // aparently java supports enums?

    public DateTreeNode(String label, NodeType type) {
        this.label = label;
        this.type = type;
        this.children = new TreeMap<>();
        this.expenseIndices = new ArrayList<>();
    }

    public String getLabel() {
        return label;
    }

    public NodeType getType() {
        return type;
    }

    public Map<String, DateTreeNode> getChildren() {
        return children;
    }

    public ArrayList<Integer> getExpenseIndices() {
        return expenseIndices;
    }

    public void addExpenseIndex(int index) {
        this.expenseIndices.add(index);
    }

    public DateTreeNode getOrCreateChild(String key, NodeType childType) {
        return children.computeIfAbsent(key, k ->
            new DateTreeNode(k, childType)
        );
    }

    /**
     * Get all expense indices in this subtree (recursive)
     */
    public ArrayList<Integer> getAllExpenseIndices() {
        ArrayList<Integer> allIndices = new ArrayList<>(expenseIndices);
        for (DateTreeNode child : children.values()) {
            allIndices.addAll(child.getAllExpenseIndices());
        }
        return allIndices;
    }
}
