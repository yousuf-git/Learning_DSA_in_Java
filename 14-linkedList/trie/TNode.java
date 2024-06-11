package trie;

class TNode {
    TNode[] children;
    boolean isEOW;

    TNode() {
        children = new TNode[26]; // considering small alphabets only
        // for (TNode tNode : children) {
        //     tNode = null;
        // }
        isEOW = false;
    }
}