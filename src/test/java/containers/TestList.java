package containers;

import org.apache.commons.lang3.time.StopWatch;

import java.time.LocalDateTime;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class TestList {
    public static void testConstructor(Supplier<List<Integer>> f) {
        List<Integer> list = f.get();
        assertThat(list.isEmpty()).isTrue();
        assertThat(list.size()).isZero();
//        assertThat(list.get(0)).isNull();
        assertThatExceptionOfType(IllegalStateException.class).isThrownBy(() -> list.get(0));
    }

    public static void testIsEmpty(Supplier<List<Integer>> f) {
        List<Integer> list = f.get();
        assertThat(list.isEmpty()).isTrue();
        list.add(0);
        assertThat(list.isEmpty()).isFalse();
        list.delete(0);
        assertThat(list.isEmpty()).isTrue();
    }

    public static void testSize(Supplier<List<Integer>> f) {
        List<Integer> list = f.get();
        assertThat(list.size()).isZero();

        for (int i = 1; i < 1000; i++) {
            list.add(i);
            assertThat(list.size()).isEqualTo(i);
        }
    }

    public static void testClear(Supplier<List<Integer>> f) {
        List<Integer> list = f.get();
        fillList(list, 1000);
        assertThat(list.isEmpty()).isFalse();
        list.clear();
        assertThat(list.isEmpty()).isTrue();
    }

    public static void testContains(Supplier<List<Integer>> f) {
        int count = 1000;
        List<Integer> list = f.get();
        fillList(list, count);

        for (int i = 1; i <= count; i++) {
//            assertThat(list.contains(i)).isEqualTo(i);
            assertThat(list.contains(i, (obj, elt) -> obj.intValue() == elt.intValue())).isEqualTo(i);
        }
    }

    public static void testEach(Supplier<List<Character>> f) {
        List<Character> list = f.get();
        StringBuilder sb = new StringBuilder();
        for (char ch = 'a'; ch <= 'z'; ch++) {
            list.add(ch);
            sb.append(ch);
        }

        String expected = sb.toString();

        sb = new StringBuilder();
        list.each(sb::append);

        String actual = sb.toString();
        assertThat(actual).isEqualTo(expected);
    }

    private static <E> void testInsert(Supplier<List<E>> f, E fillElt, E foo, E bar) {
        List<E> list = f.get();
        int count = 6;

        list.insert(count-1, foo);
        assertThat(list.size()).isEqualTo(count);
        assertThat(list.get(0)).isEqualTo(fillElt);

        list.insert(0, bar);
        assertThat(list.size()).isEqualTo(count+1);
        assertThat(list.get(0)).isEqualTo(bar);

    }

    public static void testInsertFillNull(Supplier<List<String>> f) {
        testInsert(f, null, "foo", "bar");
    }

    public static void testInsertFillZero(Supplier<List<Integer>> f) {
        testInsert(f, 0, -1, -2);
    }

    public static void testInsertNegativeIndex(Supplier<List<Integer>> f) {
        List<Integer> list = f.get();
        list.add(0);

        int count = 10;
        for (int i = 1; i <= count; i++) {
            list.insert(-i, i);
        }

        int[] elts = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            elts[i] = list.get(i);
        }

//        int[] expected = new int[list.size()];
//        for (int i = list.size()-1, j = 0; i >= 0; i--, j++) {
//            expected[j] = i;
//        }
//
//        for (int i = 0; i < list.size(); i++) {
//            assertThat(elts[i]).isEqualTo(expected[i]);
//        }

        for (int i = 0, j = count; i < list.size(); i++, j--) {
            assertThat(elts[i]).isEqualTo(j);
        }
    }

    public static void testInsertEnd(Supplier<List<Integer>> f) {
        List<Integer> list = f.get();
        list.add(0, 1, 2);

        list.insert(3, 3);
        assertThat(list.get(3)).isEqualTo(3);
        assertThat(list.size()).isEqualTo(4);

        list.insert(5, 5);
        assertThat(list.get(5)).isEqualTo(5);
        assertThat(list.size()).isEqualTo(6);
    }

    public static void testDelete(Supplier<List<Integer>> f) {
        List<Integer> list = f.get();
        int count = 1000;
        fillList(list, count);

        for (int i = count; i >= 1; i--) {
            assertThat(list.size()).isEqualTo(i);
            list.delete(0);
        }

        assertThat(list.isEmpty()).isTrue();
    }

    public static void testDeleteNegativeIndex(Supplier<List<Integer>> f) {
        List<Integer> list = f.get();
        int count = 1000;
        fillList(list, count);

        for (int i = count; i >= 1; i--) {
            assertThat(list.delete(-1)).isEqualTo(i);
        }

        assertThat(list.isEmpty()).isTrue();
    }

    public static void testGet(Supplier<List<Integer>> f) {
        List<Integer> list = f.get();
        int count = 1000;
        fillList(list, count);

        for (int i = 0; i < count; i++) {
            assertThat(list.get(i)).isEqualTo(i+1);
        }
    }

    public static void testGetNegativeIndex(Supplier<List<Integer>> f) {
        List<Integer> list = f.get();
        int count = 1000;
        fillList(list, count);

        for (int i = -1; i >= -count; i--) {
            assertThat(list.get(i)).isEqualTo(count+i+1);
        }
    }

    public static void testSet(Supplier<List<Integer>> f) {
        List<Integer> list = f.get();
        int count = 1000;

        for (int i = 0; i <= count; i++) {
            list.set(i, i);
        }

        for (int i = 0; i <= count; i++) {
            assertThat(list.get(i)).isEqualTo(i);
        }
    }

    public static void testSetNegativeIndex(Supplier<List<Integer>> f) {
        List<Integer> list = f.get();
        int count = 1000;
        fillList(list, count);

        for (int i = -1; i >= -count; i--) {
            list.set(i, i);
        }

        for (int i = 0; i < count; i++) {
            assertThat(list.get(i)).isEqualTo(i-count);
        }
    }

    public static void testSetOutOfBounds(Supplier<List<String>> f) {
        List<String> list = f.get();
        int outOfBoundIndex = 10;
        list.set(outOfBoundIndex, "foo");
        assertThat(list.size()).isEqualTo(outOfBoundIndex+1);
    }

    public static void testIndex(Supplier<List<Integer>> f) {
        List<Integer> list = f.get();
        int count = 1000;
        fillList(list, count);

        for (int i = 1; i <= count; i++) {
//            assertThat(list.index(i)).isEqualTo(i-1);
            assertThat(list.index(i, (obj, elt) -> obj.equals(elt))).isEqualTo(i-1);
//            assertThat(list.index(i, (obj, elt) -> obj.intValue() == elt.intValue())).isEqualTo(i-1);
        }
    }

//            (defun test-index-test (list-constructor)
//  (let ((list (funcall list-constructor)))
//            (loop for ch in #[#\a #\z]
//            do (add list ch))
//            (notany #'(lambda (ch) (index list ch)) #[#\A #\Z])
//            (every #'(lambda (ch) (index list ch :test #'char-equal)) #[#\A #\Z]))
//    t)
//

    public static void testSlice(Supplier<List<Integer>> f) {
        List<Integer> list = f.get();
        int count = 1000;
        fillList(list, count);

        int n = count / 2;
        int j = count / 10;

        List<Integer> slice = list.slice(j, n);
        assertThat(slice.size()).isEqualTo(n);

        for (int i = 0; i < n; i++) {
            assertThat(slice.get(i)).isEqualTo(i + j + 1);
        }
    }

    public static void testSliceCornerCases(Supplier<List<Integer>> f) {
        List<Integer> list = f.get();
        int count = 1000;
        fillList(list, count);

        int n = 10;
        List<Integer> slice = list.slice(list.size(), n);
        assertThat(slice.isEmpty()).isTrue();

        slice = list.slice(-n, n);
        assertThat(slice.size()).isEqualTo(n);

        slice = list.slice(-(count + 1), n);
        assertThat(slice.isEmpty()).isTrue();
    }

    public static void testTime(Supplier<List<Integer>> f) {
        List<Integer> list = f.get();
        int count = 10000;

        StopWatch watch = new StopWatch();
        watch.start();
        for (int i = 1; i <= 10; i++) {
            fillList(list, count);
            while (!list.isEmpty()) {
                list.delete(0);
            }
        }
        watch.stop();

        System.out.println("Elapsed time: " + watch.getTime());

        watch.reset();
        watch.start();
        for (int i = 1; i <= 10; i++) {
            fillList(list, count);
            while (!list.isEmpty()) {
                list.delete(-1);
            }
        }
        watch.stop();

        System.out.println("Elapsed time: " + watch.getTime());
    }

    private static void fillList(List<Integer> List, int count) {
        for (int i = 1; i <= count; i++) {
            List.add(i);
        }
    }
}
