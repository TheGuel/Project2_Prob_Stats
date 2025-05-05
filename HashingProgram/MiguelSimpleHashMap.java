import java.util.LinkedList;

/**
 * A simple hash map implementation using generic types, a dumb hash function,
 * and an array of linked lists.
 */
public class MiguelSimpleHashMap<T> {
    private LinkedList<T>[] data;
    private int size;             
    private int capacity;
    private int collisionCount;
    private int resizeCount;

    // Initializing the class for my hashmap
    public MiguelSimpleHashMap(int initialCapacity) {
        this.capacity = initialCapacity;
        this.data = new LinkedList[capacity];
        this.size = 0;
        this.collisionCount = 0;
        this.resizeCount = 0;
    }
    
    // Hash function that returns the string length mod array size
    public int dumbHash(String text) {
        return text.length() % capacity;
    }
    
    // Adds an item to a hashmap and makes use of dumbHash in order to compute its index
    public void add(T item) {
        int index = dumbHash(item.toString());

        if (data[index] == null) {
            data[index] = new LinkedList<>();
        } else if (!data[index].isEmpty()) {
            collisionCount++;
        }

        data[index].add(item);
        size++;

        if ((double) size / capacity > 0.75) {
            resize();
        }
    }
    
    // Checks whether the specified item exists in the map
    public boolean contains(T item) {
        int index = dumbHash(item.toString());
        if (data[index] != null) {
            return data[index].contains(item);
        }
        
        return false;
    }

    // Doubles the capacity of the array and rehashes current elements
    public void resize() {
        System.out.println("Resizing the Array");
        System.out.println("------------------");
        
        resizeCount++;
        int newCapacity = capacity * 2;
        LinkedList<T>[] oldData = data;
        
        data = new LinkedList[newCapacity];
        capacity = newCapacity;
        size = 0;
        
        for (LinkedList<T> bucket : oldData) {
            if (bucket != null) {
                for (T item : bucket) {
                    add(item);
                }
            }
        }
    }
    
    // Getter method for getting the collision count
    public int getCollisionCount() {
        return collisionCount;
    }

    // Getter method for getting the resize count
    public int getResizeCount() {
        return resizeCount;
    }

    // Getter method for getting the size amount
    public int getSize() {
        return size;
    }

    // Getter method for getting the capacity amount
    public int getCapacity() {
        return capacity;
    }
    
    public double getLoadFactor() {
        return (double) size / capacity;
    }
}

