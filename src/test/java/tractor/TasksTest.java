package tractor;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TasksTest {

    @Test
    public void testTask1() throws Exception {
        Task1 task1 = new Task1();
        assertEquals(" pleh em ", task1.solution(" help me "));
        assertEquals("ew tset sredoc", task1.solution("we test coders"));
    }

    @Test
    public void testTask2() throws Exception {
        Task2 task2 = new Task2();
        assertEquals("NOTHING", task2.solution("nice", "nice"));
        assertEquals("INSERT e", task2.solution("nice", "neice"));
        assertEquals("DELETE e", task2.solution("neice", "nice"));
        assertEquals("IMPOSSIBLE", task2.solution("xxxx", "yy"));
    }

    @Test
    public void testTask3() {
        Task3 task3 = new Task3();
        int[] A = new int[12];
        A[0] = 5;
        A[1] = 4;
        A[2] = -3;
        A[3] = 2;
        A[4] = 0;
        A[5] = 1;
        A[6] = -1;
        A[7] = 0;
        A[8] = 2;
        A[9] = -3;
        A[10] = 4;
        A[11] = -5;
        assertEquals(7, task3.solution(A));

        int[] A1 = new int[3];
        A1[0] = 1;
        A1[1] = 2;
        A1[2] = 3;
        assertEquals(1, task3.solution(A1));

        int[] A2 = new int[3];
        A2[0] = 1;
        A2[1] = 2;
        A2[2] = -1;
        assertEquals(2, task3.solution(A2));
    }
}
