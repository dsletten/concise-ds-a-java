package ch07;

import containers.LinkedStack;
import containers.Stack;

public class Balance {
    public static boolean recursiveIsBalanced(String s) {
        StringEnumerator se = new StringEnumerator(s);
//        return checkBalanced(se)  &&  se.isEmpty();
        return checkSequential(se);
    }

    private static boolean checkSequential(StringEnumerator se) {
        if ( se.isEmpty() ) {
            return true;
        } else if ( se.current() == '[' ) {
            se.advance();

            if ( se.isEmpty() ) {
                return false;
            } else if ( checkNested(se) ) {
                if ( se.isEmpty() ) {
                    return false;
                } else if ( se.current() == ']' ) {
                    se.advance();
                    return checkSequential(se);
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private static boolean checkNested(StringEnumerator se) {
        if ( se.isEmpty() ) {
            return false;
        } else if ( se.current() == '[' ) {
            se.advance();

            if (se.isEmpty()) {
                return false;
            } else if (checkNested(se)) {
                if (se.isEmpty()) {
                    return false;
                } else if (se.current() == ']') {
                    se.advance();
                    return checkNested(se);
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else if ( se.current() == ']' ) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean checkBalanced(StringEnumerator se) {
        if ( se.isEmpty() ) {
            return true;
        } else if ( se.current() == '[' ) {
            se.advance();

            if ( se.isEmpty() ) {
                return false;
            } else if ( se.current() == '[' ) {
                return checkStart(se);
            } else if ( se.current() == ']' ) {
                return checkEnd(se);
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private static boolean checkStart(StringEnumerator se) {
        if ( checkBalanced(se) ) {
            if ( se.isEmpty() ) {
                return false;
            } else if ( se.current() == ']' ) {
                return checkEnd(se);
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private static boolean checkEnd(StringEnumerator se) {
        se.advance();

        if ( se.isEmpty() ) {
            return true;
        } else if ( se.current() == '[' ) {
            return checkBalanced(se);
        } else if ( se.current() == ']' ) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkBalancedIterativeStringEnumerator(String s) {
        StringEnumerator se = new StringEnumerator(s);
        int count = 0;

        while ( !se.isEmpty() ) {
            switch ( se.current() ) {
                case '[':
                    count++;
                    break;
                case ']':
                    count--;
                    break;
                default:
                    return false;
            }

            if ( count < 0 ) {
                return false;
            }

            se.advance();
        }

        return count == 0;
    }

    public static boolean checkBalancedIterativeString(String s) {
        int count = 0;

        for (int ch : s.codePoints().toArray()) {
            switch ( ch ) {
                case '[':
                    count++;
                    break;
                case ']':
                    count--;
                    break;
                default:
                    return false;
            }

            if ( count < 0 ) {
                return false;
            }
        }

        return count == 0;
    }

    public static boolean checkBalancedStackStringEnumerator(String s) {
        StringEnumerator se = new StringEnumerator(s);
        Stack<Character> stack = new LinkedStack<>();

        while ( !se.isEmpty() ) {
            switch ( se.current() ) {
                case '[':
                    stack.push(se.current());
                    break;
                case ']':
                    if ( stack.isEmpty() ) {
                        return false;
                    } else {
                        stack.pop();
                    }
                    break;
                default:
                    return false;
            }
            se.advance();
        }

        return stack.isEmpty();
    }

    public static boolean checkBalancedStackString(String s) {
        Stack<Integer> stack = new LinkedStack<>();

        for (int ch : s.codePoints().toArray()) {
            switch ( ch ) {
                case '[':
                    stack.push(ch);
                    break;
                case ']':
                    if ( stack.isEmpty() ) {
                        return false;
                    } else {
                        stack.pop();
                    }
                    break;
                default:
                    return false;
            }
        }

        return stack.isEmpty();
    }
}
