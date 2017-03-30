package strings;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toCollection;

// Дано две строки.
// Напишите алгоритм который выдаст символы присутствующие в обеих строках
public class IntersectSymbolsSetsTest extends Assert {
    public static void main(String[] args) throws Exception {
        new IntersectSymbolsSetsTest().bigString();
    }

    @Test
    public void intersectSets() throws Exception {
        Set<Character> set1 = charSet('A', 'B', 'D', 'Z');
        Set<Character> set2 = charSet('A', 'B', 'C', 'Z');
        // Делаем копию
        Set<Character> result = new HashSet<>(set1);
        result.retainAll(set2);
        assertEquals(charSet('A', 'B', 'Z'), result);
    }

    private HashSet<Character> charSet(Character... chars) {
        return Stream.of(chars).collect(toCollection(HashSet::new));
    }

    @Test
    public void testStringToCharSet() throws Exception {
        assertEquals(charSet('a', 'b'), strToCharSet("aabbab"));
        assertEquals(charSet('t', 'e', 's', 't', ' ', 'i', 's', 'h'),
                strToCharSet("this is test"));
    }

    private Set<Character> strToCharSet(String s) {
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            set.add(s.charAt(i));
        }
        return set;
    }

    @Test
    public void name() throws Exception {
        Character[] array = intersect("abcde", "abxdf");
        System.out.println(Arrays.toString(array));
    }

    @Test
    public void bigString() throws Exception {
        Random gen = new Random();
        StringBuilder s1 = new StringBuilder();
        StringBuilder s2 = new StringBuilder();
        for (int i = 0; i < 1000000; i++) {
            s1.append((char) gen.nextInt(1000));
            s2.append((char) gen.nextInt(1000));
        }
        intersect(s1.toString(), s2.toString());
    }

    private Character[] intersect(String s1, String s2) {
        Set<Character> result = strToCharSet(s1);
        result.retainAll(strToCharSet(s2));
        Character[] array = result.toArray(new Character[result.size()]);
        Arrays.sort(array);
        return array;
    }

}
