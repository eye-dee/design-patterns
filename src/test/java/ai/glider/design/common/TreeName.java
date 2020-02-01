package ai.glider.design.common;

public enum TreeName {
    RED_BLACK("red_black"),
    SEARCH_BINARY("search_binary"),
    AVL("avl");

    public String getName() {
        return name;
    }

    private final String name;

    TreeName(String name) {
        this.name = name;
    }
}
