package containers;

public class ArrayListX<E> extends ArrayList<E> {
    private int offset = 0;

    public ArrayListX() {
        super();
    }

    public ArrayListX(E fillElt) {
        super(fillElt);
    }

    @SuppressWarnings("unused")
    protected ArrayListX<E> makeEmptylist() {
        return new ArrayListX<>(getFillElt());
    }

    @Override
    public int size() {
        return super.size() - offset;
    }

    @Override
    protected void doClear() {
        super.doClear();
        offset = 0;
    }

    @Override
    protected void shiftUp(int low, int high) {
        super.shiftUp(low + offset, high +offset);
    }

    @Override
    protected void shiftDown(int low, int high) {
        super.shiftDown(low + offset, high + offset);
    }

    @Override
    protected E doGet(int i) {
        return super.doGet(i + offset);
    }

    @Override
    protected void doSet(int i, E obj) {
        super.doSet(i + offset, obj);
    }

    @Override
    protected void doDoInsert(int i, E obj) {
        if ( offset == 0  ||  i > size() / 2) {
            super.doDoInsert(i, obj);
        } else {
            if ( i != 0 ) {
                shiftDown(0, i);
            }

            offset--;
            set(i, obj);
        }
    }

    @Override
    protected E doDoDelete(int i) {
        E doomed = get(i);

        if ( i > size() / 2 ) {
            super.doDoDelete(i);
        } else {
            if ( i != 0 ) {
                shiftUp(0, i);
            }

            set(0, getFillElt());
            offset++;
        }

        return doomed;
    }

    public static void main(String[] args) {
        List<Integer> al = new ArrayListX<>();
        al.add(2);
        al.add(4);
        al.add(6);
        al.add(8);

        System.out.println(al.delete(0));
        System.out.println(al.size());
        System.out.println(al);

        System.out.println(al.get(0));
        al.set(3, 10);
        al.insert(3, 8);

        System.out.println(al);
        System.out.println(al.get(-2));
        System.out.println(al.get(-1));

        System.out.println(al.slice(0, 5));
        System.out.println(al.slice(1, 5));
        System.out.println(al.slice(2, 5));
        System.out.println(al.slice(-4, 2));
        System.out.println(al.slice(-8, 2));

        al.insert(12, 999);
        System.out.println(al);

        al = new ArrayListX<>(0);
        al.add(2, 4, 6, 8);
        al.insert(12, 999);
        System.out.println(al);

//        al.each(integer -> System.out.println(integer));
        al.each(System.out::println);

        System.out.println(al.contains(2));
        System.out.println(al.contains(2, (item, elt) -> elt == Math.pow(item, 3)));

        ListIterator<Integer> li = al.listIterator();
        System.out.println(li.current());
        System.out.println(li.currentIndex());
        li.next();
        System.out.println(li.current());
        li.addBefore(-99);
        li.addAfter(-98);
        System.out.println(al);

        li = al.listIterator(al.size()-1);
        System.out.println(li.current());
        li.previous();
        System.out.println(li.current());


        List<Integer> list = new ArrayListX<>();

        System.out.println(list.isEmpty());
        list.add(0);
        System.out.println(list.isEmpty());
        list.delete(0);
        System.out.println(list.isEmpty());

    }
}
