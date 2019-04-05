import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class CollectionsBenchmarkMain {
    private static long s;
    private static int b;
    private static long e ;

    private static final int index = 5555555;

    public static void main(String[] args) {
        Double[] array = new Double [10000000];
        for (int i = 0; i < 10000000; i++) {
            array[i] = (double) i;
        }

        System.out.println("Array List");
        ArrayList<Double> arrayList = new ArrayList<>(Arrays.asList(array));
        System.out.printf("Время получения элемента по индексу %d: %n", index);
        System.out.println(timeGet_Del( arrayList::get));
        System.out.printf("Время удаления элемента %d: %n", index);
        System.out.println(timeGet_Del( arrayList::remove));
        System.out.printf("Время добавления элемента по индексу %d: %n", index);
        System.out.println(timeAddToIndex( arrayList::add));
        System.out.println();

        System.out.println("Linked List");
        LinkedList<Double> linkedList = new LinkedList<>(arrayList);
        System.out.printf("Время получения элемента по индексу %d: %n", index);
        System.out.println(timeGet_Del(linkedList::get));
        System.out.printf("Время удаления элемента %d: %n", index);
        System.out.println(timeGet_Del(linkedList::remove));
        System.out.printf("Время добавления элемента по индексу %d: %n", index);
        System.out.println(timeAddToIndex(linkedList::add));
        System.out.println();

        System.out.println("Hash Set");
        HashSet<Double> hashSet = new HashSet<>(arrayList);
        System.out.printf("Время проверки, есть ли элемент %d: %n", index);
        System.out.println(timeGet_Del(hashSet::contains));
        System.out.printf("Время удаления элемента %d: %n", index);
        System.out.println(timeGet_Del(hashSet::remove));
        System.out.printf("Время добавления элемента %d: %n", index);
        System.out.println(timeAdd(hashSet::add));
        System.out.println();

        System.out.println("Tree Set");
        TreeSet<Double> treeSet = new TreeSet<>(arrayList);
        System.out.printf("Время проверки, есть ли элемент %d: %n", index);
        s = System.currentTimeMillis();
        treeSet.contains((double)index);
        e = System.currentTimeMillis();
        System.out.println(e-s);
        System.out.printf("Время удаления элемента %d: %n", index);
        s = System.currentTimeMillis();
        treeSet.remove((double)index);
        e = System.currentTimeMillis();
        System.out.println(e-s);
        System.out.printf("Время добавления элемента %d: %n", index);
        System.out.println(timeAdd(treeSet::add));
        System.out.println();

        System.out.println("Linked Hash Set");
        LinkedHashSet<Double> linkedHashSet = new LinkedHashSet<>(arrayList);
        System.out.printf("Время проверки, есть ли элемент %d: %n", index);
        System.out.println(timeGet_Del(linkedHashSet::contains));
        System.out.printf("Время удаления элемента %d: %n", index);
        System.out.println(timeGet_Del(linkedHashSet::remove));
        System.out.printf("Время добавления элемента %d: %n", index);
        System.out.println(timeAdd(linkedHashSet::add));
        System.out.println();

        System.out.println("Tree Map");
        TreeMap<Integer, Double> treeMap = new TreeMap<>();
        for (int i = 0; i < array.length; i++) {
            treeMap.put(i, array[i]);
        }
        System.out.printf("Время проверки по ключу %d: %n", index);
        s = System.currentTimeMillis();
        treeMap.containsKey(index);
        e = System.currentTimeMillis();
        System.out.println(e-s);
        System.out.printf("Время проверки, есть ли значение %d: %n", index);
        System.out.println(timeGet_Del(treeMap::containsValue));
        System.out.printf("Время удаления элемента %d: %n", index);
        System.out.println(timeGet_Del(treeMap::remove));
        System.out.printf("Время добавления элемента %d: %n", index);
        System.out.println(timeAddToIndex(treeMap::put));
        System.out.println();

        System.out.println("Hash Map");
        HashMap<Integer, Double> hashMap = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            treeMap.put(i, array[i]);
        }
        System.out.printf("Время проверки по ключу %d: %n", index);
        s = System.currentTimeMillis();
        hashMap.containsKey(index);
        e = System.currentTimeMillis();
        System.out.println(e-s);
        System.out.printf("Время проверки, есть ли значение %d: %n", index);
        System.out.println(timeGet_Del(hashMap::containsValue));
        System.out.printf("Время удаления элемента %d: %n", index);
        System.out.println(timeGet_Del(hashMap::remove));
        System.out.printf("Время добавления элемента %d: %n", index);
        System.out.println(timeAddToIndex(hashMap::put));
        System.out.println();

        System.out.println("Итог: \nArrayList подходит хранения большого чилса элементов, без потребности их частой вставки и удаления. \n" +
                "LinkedList выгоден при хранении большой коллекции, которую часто требуется модифицировать. \n" +
                "LinkedHash, Hash и Tree Set нужны для работы с множеством неповторяющихся элементов, время выполнения методов всегда константное. \n" +
                "HashMap аналогичен множествам Set с той разницей, что значения элементов могут повторяться. \n" +
                "TreeMap не выгоден при необходимости проверять наличие какого-либо конкретного значения.");
    }

    private static long timeGet_Del(Consumer<Integer> c) {
        s = System.currentTimeMillis();
        c.accept(index);
        e = System.currentTimeMillis();
        return e - s;
    }

    private static long timeAdd(Consumer<Double> c) {
        s = System.currentTimeMillis();
        c.accept((double)index);
        e = System.currentTimeMillis();
        return e - s;
    }

    private static long timeAddToIndex(BiConsumer<Integer, Double> c) {
        s = System.currentTimeMillis();
        c.accept(index, -11.0);
        e = System.currentTimeMillis();
        return e - s;
    }
}
