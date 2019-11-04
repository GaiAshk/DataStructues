package TaskManager;


import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import java.lang.*;
import java.util.*;
/**
 * This class only tests simple examples (to give a sanity-check).
 * Careful: Correct code will always pass the tests, but not all code that
 * passes these tests is correct.
 *
 */

public class TaskManagerTest {
    public final static int MAX_CAPACITY = 32;

    @Test(timeout = 5000)
    public void testEmptyManager() {
        TaskManager t = new TaskManager(MAX_CAPACITY);
        int manager_size = t.size();
        assertEquals("Failed creating empty manager, checked manager size\n", 0, manager_size);
    }

    @Test(timeout = 5000)
    public void testInsertOneTask() {
        TaskManager tm = new TaskManager(MAX_CAPACITY);
        Task t = new Task(1,1,"1");
        tm.insert(t);
        int manager_size = tm.size();
        assertEquals("Failed adding one item to task manager, checked manager size\n", 1, manager_size);
    }

    @Test(timeout = 5000)
    public void testFindMaxOneTaskReturnValue() {
        TaskManager tm = new TaskManager(MAX_CAPACITY);
        Task t = new Task(1,1,"1");
        tm.insert(t);
        Task t1 = tm.findMax();
        assertEquals("Added one item to a heap, then run findMax, returned value have wrong serial\n", 1, t1.serial);
        assertEquals("Added one item to a heap, then run findMax, returned value have wrong priority\n", 1, t1.priority);
    }

    @Test(timeout = 5000)
    public void testFindMaxOneTaskHeapSize() {
        TaskManager tm = new TaskManager(MAX_CAPACITY);
        Task t = new Task(1,1,"1");
        tm.insert(t);
        int manager_size = tm.size();
        Task t1 = tm.findMax();
        int new_manager_size = tm.size();
        assertEquals("Added one item to a heap, then run findMax, checked that findMax did not changed manager size\n", manager_size, new_manager_size);
    }

    @Test(timeout = 5000)
    public void testMultipleFindMax() {
        TaskManager tm = new TaskManager(MAX_CAPACITY);
        Task t = new Task(1,1,"1");
        tm.insert(t);
        Task t1 = tm.findMax();
        Task t2 = tm.findMax();
        assertEquals("Added one item to a heap, then run findMax twice, the two tasks returned are different tasks\n", t1, t2);
    }


    @Test(timeout = 5000)
    public void testExtractMaxOneTaskReturnValue() {
        TaskManager tm = new TaskManager(MAX_CAPACITY);
        Task t = new Task(1,1,"1");
        tm.insert(t);
        int manager_size = tm.size();

        Task t1 = tm.extractMax();
        assertEquals("Added one item to a heap, then run extractMax, returned value have wrong serial\n", 1, t1.serial);
        assertEquals("Added one item to a heap, then run extractMax, returned value have wrong priority\n", 1, t1.priority);
    }

    @Test(timeout = 5000)
    public void testExtractMaxOneTaskHeapSize() {
        TaskManager tm = new TaskManager(MAX_CAPACITY);
        Task t = new Task(1,1,"1");
        tm.insert(t);
        int manager_size = tm.size();

        Task t1 = tm.extractMax();

        int new_manager_size = tm.size();
        assertEquals("Added one item to a heap, then run extractMax, current manager size should be zero\n", manager_size-1, new_manager_size);
    }

    @Test(timeout = 5000)
    public void testUpdatePriorityOneTask() {
        TaskManager tm = new TaskManager(MAX_CAPACITY);
        Task t = new Task(1,1,"1");
        tm.insert(t);
        tm.updatePriority(t, 2);

        Task t1 = tm.findMax();

        assertEquals("Added one item to a heap, then run updatePriority with new priority, then run findMax, returned value have wrong serial\n", 1, t1.serial);
        assertEquals("Added one item to a heap, then run updatePriority with new priority, then run findMax, returned value have wrong priority\n", 2, t1.priority);
    }

    @Test(timeout = 5000)
    public void testMultipleUpdatePriorityOneTask() {
        TaskManager tm = new TaskManager(MAX_CAPACITY);
        Task t = new Task(1,1,"1");
        tm.insert(t);
        tm.updatePriority(t, 2);
        tm.updatePriority(t, 3);

        Task t1 = tm.findMax();

        assertEquals("Added one item to a heap, then run updatePriority with new priority multiple times, then run findMax, returned value have wrong serial priority\n", 3, t1.priority);
    }


    @Test(timeout = 5000)
    public void testUpdatePriorityOneTaskHeapSize() {
        TaskManager tm = new TaskManager(MAX_CAPACITY);
        Task t = new Task(1,1,"1");
        tm.insert(t);

        int manager_size = tm.size();
        tm.updatePriority(t, 2);

        int new_manager_size = tm.size();

        assertEquals("Added one item to a heap, then run updatePriority with new priority multiple times, checked manager size did not changed\n", manager_size, new_manager_size);
    }


    @Test(timeout = 5000)
    public void testInsertMultipleTasks() {
        TaskManager tm = new TaskManager(MAX_CAPACITY);
        for(int i =1; i<= MAX_CAPACITY/2;i++)
        {
            Task t = new Task(i,i,Integer.toString(i));
            tm.insert(t);
        }


        int manager_size = tm.size();
        assertEquals("Added Multiple items to the task manager, then checked if its size is as the number of items added\n", MAX_CAPACITY/2, manager_size);
    }

    @Test(timeout = 5000)
    public void testInsertMaximalTasks() {
        TaskManager tm = new TaskManager(MAX_CAPACITY);
        for(int i =1; i<= MAX_CAPACITY;i++)
        {
            Task t = new Task(i,i,Integer.toString(i));
            tm.insert(t);
        }


        int manager_size = tm.size();
        assertEquals("Added the maximal number of items to the task manager, then checked if its size is as the number of items added\n", MAX_CAPACITY, manager_size);
    }

    @Test(timeout = 5000)
    public void testFindMaxMultipleTasks() {
        TaskManager tm = new TaskManager(MAX_CAPACITY);
        for(int i =1; i<= MAX_CAPACITY;i++)
        {
            Task t = new Task(i,i,Integer.toString(i));
            tm.insert(t);
        }

        Task t1 = tm.findMax();
        assertEquals("Added the maximal number of items to the task manager (item i with priority i), then findMax returned wrong serial\n", MAX_CAPACITY, t1.serial);
        assertEquals("Added the maximal number of items to the task manager (item i with priority i), then findMax returned wrong priority\n", MAX_CAPACITY, t1.priority);
    }

    @Test(timeout = 5000)
    public void testExtractMaxMultipleTasks() {
        TaskManager tm = new TaskManager(MAX_CAPACITY);
        for(int i =1; i<= MAX_CAPACITY;i++)
        {
            Task t = new Task(i,i,Integer.toString(i));
            tm.insert(t);
        }

        int manager_size = tm.size();
        Task t1 = tm.extractMax();
        assertEquals("Added the maximal number of items to the task manager (item i with priority i), then extractMax returned wrong serial\n", MAX_CAPACITY, t1.serial);
        assertEquals("Added the maximal number of items to the task manager (item i with priority i), then extractMax returned wrong priority\n", MAX_CAPACITY, t1.priority);

        int new_manager_size = tm.size();
        assertEquals("Added the maximal number of items to the task manager (item i with priority i), then run extractMax and checked the size of the manager\n", manager_size-1, new_manager_size);
    }

    @Test(timeout = 5000)
    public void testMultipleExtractMax() {
        TaskManager tm = new TaskManager(MAX_CAPACITY);
        for(int i =1; i<= MAX_CAPACITY;i++)
        {
            Task t = new Task(i,i,Integer.toString(i));
            tm.insert(t);
        }

        for(int i =1; i<= MAX_CAPACITY;i++)
        {
            Task t1 = tm.extractMax();
            assertEquals("Added the maximal number of items to the task manager (item i with priority i), then run extractMax the maximal number of time possible, returned wrong serial\n", MAX_CAPACITY+1-i, t1.serial);
            assertEquals("Added the maximal number of items to the task manager (item i with priority i), then run extractMax the maximal number of time possible, returned wrong priority\n", MAX_CAPACITY+1-i, t1.priority);
        }
    }

    @Test(timeout = 5000)
    public void testFindMaxAfterExtractMax() {
        TaskManager tm = new TaskManager(MAX_CAPACITY);
        Task t1 = new Task(1,1,Integer.toString(1));
        tm.insert(t1);
        Task t2 = new Task(2,2,Integer.toString(2));
        tm.insert(t2);

        tm.extractMax();

        Task currentMax = tm.findMax();
        assertEquals("Added multiple items to the maanger, cheked that findMax after extractMax works correctly, task returned by findMax have wrong serial\n", 1, currentMax.serial);
        assertEquals("Added multiple items to the maanger, cheked that findMax after extractMax works correctly, task returned by findMax have wrong priority\n", 1, currentMax.priority);
    }

    @Test(timeout = 5000)
    public void testInsertMultipleTasksSamePrio() {
        TaskManager tm = new TaskManager(MAX_CAPACITY);
        Task t1 = new Task(1,2,Integer.toString(1));
        tm.insert(t1);
        Task t2 = new Task(2,2,Integer.toString(2));
        tm.insert(t2);

        Task currentMax = tm.findMax();
        assertEquals("Added two tasks, same priority (serial 1,2. priority 2), findMax returned wrong serial\n", 2, currentMax.serial);
        assertEquals("Added two tasks, same priority (serial 1,2. priority 2), findMax returned wrong priority\n", 2, currentMax.priority);
    }

    @Test(timeout = 5000)
    public void testUpdatePriorityToMaxMultipleTasks() {
        TaskManager tm = new TaskManager(MAX_CAPACITY);
        Task t = new Task(1,1,Integer.toString(1));
        tm.insert(t);
        for(int i =2; i<= MAX_CAPACITY;i++)
        {
            Task t1 = new Task(i,i,Integer.toString(i));
            tm.insert(t1);
        }
        tm.updatePriority(t, MAX_CAPACITY+1);

        Task t1 = tm.findMax();
        assertEquals("Added the maximal number of items to the task manager (item i with priority i), use updatePriority(task with serial 1, MAX_PRIORITY+1),findMax returned wrong serial\n", 1, t1.serial);
        assertEquals("Added the maximal number of items to the task manager (item i with priority i), use updatePriority(task with serial 1, MAX_PRIORITY+1),findMax returned wrong  priority\n", MAX_CAPACITY+1, t1.priority);
    }

    @Test(timeout = 5000)
    public void testUpdatePriorityNotToMaxMultipleTasks() {
        TaskManager tm = new TaskManager(MAX_CAPACITY);
        Task t = new Task(1,1,Integer.toString(1));
        tm.insert(t);
        for(int i =2; i<= MAX_CAPACITY;i++)
        {
            Task t1 = new Task(i,i,Integer.toString(i));
            tm.insert(t1);
        }
        tm.updatePriority(t, MAX_CAPACITY/2);

        Task t1 = tm.findMax();
        assertEquals("Added the maximal number of items to the task manager (item i with priority i), use updatePriority(task with serial 1, MAX_PRIORITY/2),findMax returned wrong serial\n", MAX_CAPACITY, t1.serial);
        assertEquals("Added the maximal number of items to the task manager (item i with priority i), use updatePriority(task with serial 1, MAX_PRIORITY/2),findMax returnedwrong priority\n", MAX_CAPACITY, t1.priority);
    }

    @Test(timeout = 5000)
    public void testMultipleUpdatePriorityMultipleTasks() {

        Task[] tasks = new Task[MAX_CAPACITY + 1];
        TaskManager tm = new TaskManager(MAX_CAPACITY);
        for(int i =1; i<= MAX_CAPACITY;i++)
        {
            Task t1 = new Task(i,i,Integer.toString(i));
            tm.insert(t1);
            tasks[i] = t1;
        }
        for(int i =1; i<= MAX_CAPACITY;i++)
        {

            tm.updatePriority(tasks[i], MAX_CAPACITY+1-i);
        }

        Task t1 = tm.extractMax();
        assertEquals("Added the maximal number of items to the task manager (item i with priority i), then updated all priorities (for serial i new prio is MAX_CAPACITY+1-i), extractMax returned wrong serial after replacing the priorities\n", 1, t1.serial);
        assertEquals("Added the maximal number of items to the task manager (item i with priority i), then updated all priorities (for serial i new prio is MAX_CAPACITY+1-i), extractMax returned wrong priority after replacing the priorities\n", MAX_CAPACITY, t1.priority);

        Task t2 = tm.findMax();
        assertEquals("Added the maximal number of items to the task manager (item i with priority i), then updated all priorities (for serial i new prio is MAX_CAPACITY+1-i), run extractMax to remove the maximal value, then run findMax that returned wrong serial.\n", 2, t2.serial);
        assertEquals("Added the maximal number of items to the task manager (item i with priority i), then updated all priorities (for serial i new prio is MAX_CAPACITY+1-i), run extractMax to remove the maximal value, then run findMax that returned wrong priority.\n", MAX_CAPACITY-1, t2.priority);

    }

}
