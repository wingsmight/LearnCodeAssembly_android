

package shared.turboeditor.home.texteditor;

import java.util.LinkedList;

public class SearchResult {
    // list of index
    public LinkedList<Integer> foundIndex;
    public int textLength;
    public boolean isReplace;
    public String textToReplace;
    public int index;
    public String whatToSearch;
    public boolean isRegex;


    public SearchResult(LinkedList<Integer> foundIndex, int textLength, boolean isReplace, String whatToSearch, String textToReplace, boolean isRegex) {
        this.foundIndex = foundIndex;
        this.textLength = textLength;
        this.isReplace = isReplace;
        this.whatToSearch = whatToSearch;
        this.textToReplace = textToReplace;
        this.isRegex = isRegex;
    }

    public void doneReplace() {
        foundIndex.remove(index);
        int i;
        for (i = index; i < foundIndex.size(); i++) {
            foundIndex.set(i, foundIndex.get(i) + textToReplace.length() - textLength);
        }
        index--; // an element was removed so we decrease the index
    }

    public int numberOfResults() {
        return foundIndex.size();
    }

    public boolean hasNext() {
        return index < foundIndex.size() - 1;
    }

    public boolean hasPrevious() {
        return index > 0;
    }

    public boolean canReplaceSomething() {
        return isReplace && foundIndex.size() > 0;
    }
}
