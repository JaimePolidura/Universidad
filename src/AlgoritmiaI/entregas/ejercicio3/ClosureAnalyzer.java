package AlgoritmiaI.entregas.ejercicio3;

import AlgoritmiaI.datastructures.stack.MyStack;

import java.util.*;

public final class ClosureAnalyzer {
    private static final Map<Character, Character> closeToOpen;
    private static final Set<Character> openTokens;
    private static final Set<Character> closeTokens;

    static {
        closeTokens = new HashSet<Character>() {{
            add(')');
            add(']');
        }};

        openTokens = new HashSet<Character>(){{
            add('(');
            add('[');
        }};

        closeToOpen = new HashMap<Character, Character>(){{
            put(')', '(');
            put(']', '[');
        }};
    }

    public static SyntaxError getFirstError(String stringToCheck) {
        MyStack<TokenInfo> openTokens = new MyStack<>();
        MyStack<Integer> openTokenIndex = new MyStack<>();

        for(int i = 0; i < stringToCheck.length(); i++) {
            char actualChar = stringToCheck.charAt(i);

            if(isNotParenthesisToken(actualChar)) continue;

            if(isOpenToken(actualChar)){
                openTokens.push(TokenInfo.of(actualChar, i));

                openTokenIndex.push(i);
            }else{
                if(openTokens.isEmpty()){
                    MyStack<String> contextStack = buildContextStack(openTokenIndex, stringToCheck, i);

                    return new SyntaxError(contextStack, i);
                }

                char supossedOpenToken = openTokens.pop().getOpenToken();

                if(missMatchOpenCloseTokens(supossedOpenToken, actualChar)){
                    return new SyntaxError(buildContextStack(openTokenIndex, stringToCheck, i), i);
                }
            }
        }

        return !openTokens.isEmpty() ?
                new SyntaxError(buildContextStackWholeText(stringToCheck), openTokens.top().getIndex()):
                null;
    }

    private static MyStack<String> buildContextStackWholeText(String text) {
        MyStack<String> contextStack = new MyStack<>();
        contextStack.push(text);

        return  contextStack;
    }

    private static MyStack<String> buildContextStack(MyStack<Integer> indexes, String text, int error) {
        MyStack<String> contextStack = new MyStack<>();

        Object[] indexesObjectArray = indexes.listData();

        contextStack.push(text.substring(0, error + 1));

        for (int i = indexesObjectArray.length - 1; i >= 0 ; i--) {
            int indexesInteger = (int) indexesObjectArray[i];

            contextStack.push(text.substring(indexesInteger, error + 1));
        }

        return contextStack;
    }

    private static boolean missMatchOpenCloseTokens(char openToken, char closeToken) {
        return closeToOpen.get(closeToken) != openToken;
    }

    private static boolean isNotParenthesisToken(char token){
        return !isOpenToken(token) && !isCloseToken(token);
    }

    private static boolean isOpenToken(char token) {
        return openTokens.contains(token);
    }

    private static boolean isCloseToken(char token){
        return closeTokens.contains(token);
    }

    private static class TokenInfo {
        private final Character openToken;
        private final int index;

        public TokenInfo(Character openToken, int index) {
            this.openToken = openToken;
            this.index = index;
        }

        public Character getOpenToken() {
            return openToken;
        }

        public int getIndex() {
            return index;
        }

        public static TokenInfo of (Character openToken, int index) {
            return new TokenInfo(openToken, index);
        }
    }
}
