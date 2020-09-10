import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;

public class GenerateArrayTest {
    @Test
    public void shouldReturn2(){
        assertArrayEquals(new int []{2}, GenerateArray.generateArray("2"));
    }
    @Test
    public void shouldReturnArray(){
        assertArrayEquals(new int[] {2, 5, 8}, GenerateArray.generateArray("2,5,8"));
    }

    @Test
    public void shouldReturnARange(){
        assertArrayEquals(new int[] {5, 6, 7, 8, 9, 10}, GenerateArray.generateArray("5-10"));
    }

    @Test
    public void shouldReturnARangeWithSkips(){
        assertArrayEquals(new int[]{5, 7, 9}, GenerateArray.generateArray("5-10:2"));
    }

    @Test
    public void shouldReturnArrayAndARange(){
        assertArrayEquals(new int [] {1, 3, 5, 6, 7, 8}, GenerateArray.generateArray("1,3,5-8"));
    }

    @Test
    public void shouldReturnArrayRangeAndSkips(){
        assertArrayEquals(new int [] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 14, 20, 22, 24}, GenerateArray.generateArray("1-10,14,20-25:2"));
    }

}