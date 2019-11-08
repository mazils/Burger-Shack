package Server;

public class BlockingQueue implements ListADT{

    private ArrayList arrayList;

    public BlockingQueue()
    {
        arrayList = new ArrayList();
    }

    @Override
    public void add(int index, Object element) {
        arrayList.add(index, element);
    }

    @Override
    public void add(Object element) {
        arrayList.add(element);
    }

    @Override
    public void set(int index, Object element) {
        arrayList.set(index, element);
    }

    @Override
    public Object get(int index) {
        return arrayList.get(index);
    }

    @Override
    public Object remove(int index) {
        return arrayList.remove(index);
    }

    @Override
    public Object remove(Object element) {
        return arrayList.remove(element);
    }

    @Override
    public int indexOf(Object element) {
        return arrayList.indexOf(element);
    }

    @Override
    public boolean contains(Object element) {
        return arrayList.contains(element);
    }

    @Override
    public boolean isEmpty() {
        return arrayList.isEmpty();
    }

    @Override
    public boolean isFull() {
        return arrayList.isFull();
    }

    @Override
    public int size() {
        return arrayList.size();
    }
}
