package TaskManager;

public class TaskManager {

    Task[] heap;         // first place is a dummy in this array
    int[] availableData; // the reference array
    int size;             // the current number of tasks awaiting completion

    /**
     * Creates an empty data structure with the given capacity.
     * The capacity dictates how many different tasks may be put into the data structure.
     * Moreover, the capacity gives an upper bound to the serial number of tasks to be put in the data structure.
     */
    public TaskManager(int capacity) {
        this.heap = new Task[capacity + 1];            // first index is a dummy
        this.availableData = new int[capacity + 1];
        //sets all the array to -1, tasks that are not in the heap
        for (int i = 0; i < availableData.length; i++) {
            availableData[i] = -1;
        }
        this.size = 0;
    }

    /**
     * Inserts a given Task into the heap.
     * if capacity is full returns nothing
     *
     * @param t - the Task to be inserted.
     */
    public void insert(Task t) {
        if (this.size - 1 == heap.length) return;   //in case that capacity is full
        //maintain size
        size++;
        //maintain the structure property
        heap[this.size] = t;
        this.availableData[t.serial] = this.size;
        //percUp and update the reference array
        percUp(this.size);
    }


    /**
     * Returns the Task with the highest priority in the heap.
     * You may not use any loops (or recursion) in this function.
     *
     * @return the Task with the highest priority in the heap.
     */
    public Task findMax() {
        return heap[1];
    }

    /**
     * Removes and returns the Task with the highest priority from the heap.
     * Recall that you are not allowed to traverse all elements of the heap array.
     *
     * @return the Task with the highest priority in the heap.
     */

    public Task extractMax() {
        Task temp = heap[1];
        swap(1, this.size);
        //after swap free last element
        availableData[heap[this.size].serial] = -1;
        heap[this.size] = null;
        this.size--;
        //precDown and update the reference array
        percDown(1);
        return temp;
    }

    /**
     * percolate Up the heap, and updates the reference array.
     * uses the maintain the structure property
     * assumes that there is at least one task in the heap
     *
     * @param i - the index in which the task is entered
     */
    public void percUp(int i) {
        if (i == 1) return;
        if (this.heap[i].compareTo(this.heap[i / 2]) > 0) {          //heap is not in order
            swap(i / 2, i);
            percUp(i / 2);
        } else {
            return;
        }
    }

    /**
     * percolate Down the heap, and updates the reference array.
     * uses the maintain the structure property
     * assumes that there is at least one task in the heap
     *
     * @param i - the top of the heap (max)
     */
    public void percDown(int i) {
        if (2 * i > this.size) return;
        if (2 * i == this.size) {
            if (this.heap[i].compareTo(this.heap[2 * i]) < 0) {
                swap(i, i * 2);
                return;
            } else {
                return;
            }
        } else if (2 * i < this.size) {
            int j = ((this.heap[2 * i].compareTo(this.heap[(2 * i) + 1]) > 0) ? (2 * i) : ((2 * i) + 1));
            if (this.heap[i].compareTo(this.heap[j]) < 0) {
                swap(i, j);
                percDown(j);
            } else {
                return;
            }
        }
    }

    /**
     * a swap function to swap between places in the heap and
     * and in the reference array
     *
     * @param i - int the is being swaped
     * @param j - second int the is swaped
     **/
    public void swap(int i, int j) {
        // reference array update for the moved down tasks
        availableData[heap[i].serial] = j;
        availableData[heap[j].serial] = i;
        // heap update
        Task temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }


    /**
     * Updates the priority of a given task.
     * Does nothing if the task is not already in the heap.
     * Recall that you are not allowed to traverse all elements of the heap array.
     * Think about what can go wrong in the heap as you change the priority of a given task. How will you fix it?
     *
     * @param t           - the given task
     * @param newPriority - the new priority of the given task.
     */
    public void updatePriority(Task t, int newPriority) {
        if (t.priority < newPriority) {
            heap[availableData[t.serial]].priority = newPriority;
            percUp(availableData[t.serial]);
        } else {
            heap[availableData[t.serial]].priority = newPriority;
            percDown(availableData[t.serial]);
        }
    }


    /*
     * Test code; output should be:
     * task: abbreviate notes, priority: 10
     * task: download new version, priority: 20
     * task: bring food, priority: 11
     * task: abbreviate notes, priority: 10
     * task: clear histories, priority: 3
     * task: download new version, priority: 0
     */
    public static void main(String[] args) {



        /*
        TaskManager demo = new TaskManager(10);
        Task a = new Task(1, 10, "abbreviate notes");
        Task b = new Task(2, 2, "bring food");
        Task c = new Task(3, 3, "clear histories");
        Task d = new Task(4, 20, "download new version");

        demo.insert(a);
        System.out.println(demo.findMax());

        demo.insert(b);
        demo.insert(c);
        demo.insert(d);
        System.out.println(demo.findMax());
        demo.updatePriority(b, 11);
        demo.updatePriority(d, 0);
        System.out.println(demo.extractMax());
        System.out.println(demo.extractMax());
        System.out.println(demo.extractMax());
        System.out.println(demo.extractMax());
        */

        testInsertMultipleTasks();
    }


    public static void testInsertMultipleTasks() {
        int MAX_CAPACITY = 32;
        TaskManager tm = new TaskManager(MAX_CAPACITY);
        for (int i = 1; i <= MAX_CAPACITY / 2; i++) {
            Task t = new Task(i, i, Integer.toString(i));
            tm.insert(t);
        }


        int manager_size = tm.size;
        System.out.println(manager_size);
    }

}

