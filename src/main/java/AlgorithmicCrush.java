import java.io.*;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class AlgorithmicCrush {
    private static final boolean useFiles = true;

    private static InputReader scan;
    private static Segment[] tree;
    private static int a, b;
    private static long k;

    public static void main(String[] args) throws IOException {
        scan = new InputReader(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        if (useFiles) {
            scan = new InputReader(new FileInputStream("tests/algorithmic_crush/01"));
        }
        solve();
        pw.close();
    }

    private static void solve() {
        int N = scan.ni();
        // Calculate power of 2 >= N
        int SIZE = 1;
        while (SIZE < N) SIZE *= 2;

        Stream<Segment> segments = Stream.generate(Segment::new).limit(SIZE * 2);
        tree = segments.toArray(Segment[]::new);
        // Process Leaves
        for (int i = 0; i < SIZE; i++) {
            tree[SIZE + i].left = i + 1;
            tree[SIZE + i].right = i + 1;
        }
        //          1
        //    2           3
        //  4   5      6     7
        // 8 9 10 11 12 13 14 15
        //
        for (int i = SIZE - 1; i >= 1; i--) {
            int left = 2 * i;
            int right = left + 1;
            tree[i].left = tree[left].left;
            tree[i].right = tree[right].right;
        }

        int M = scan.ni();
        for (int i = 0; i < M; i++) {
            // Read query
            a = scan.ni();
            b = scan.ni();
            k = scan.nl();
            add(1);
        }
        for (int i = 1; i < SIZE; i++) {
            int left = 2 * i;
            int right = left + 1;
            tree[left].v += tree[i].v;
            tree[right].v += tree[i].v;
        }
        long max = tree[SIZE].v;
        for (int i = SIZE + 1; i < SIZE + N; i++)
            max = Math.max(max, tree[i].v);
        System.out.println(max);
    }

    // Segment
    //  |   left    right  |
    //  | left r1 l2 right |
    private static void add(int i) {
        int l = tree[i].left;
        int r = tree[i].right;
        if (a > r || b < l) return;
        if (a <= l && r <= b) {
            tree[i].v += k;
        } else {
            int left = 2 * i;
            add(left);
            add(left + 1);
        }
    }

    static class Segment {
        long v = 0;
        int left, right;
    }

    private static class InputReader {
        BufferedReader reader;
        StringTokenizer tokenizer;

        InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String nLine() throws IOException {
            return reader.readLine();
        }

        String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (Exception e) {
                    return null;
                }
            }
            return tokenizer.nextToken();
        }

        int ni() {
            return Integer.parseInt(next());
        }

        long nl() {
            return Long.parseLong(next());
        }

        public double nd() {
            return Double.parseDouble(next());
        }

    }

}