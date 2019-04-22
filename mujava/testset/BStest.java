import static org.junit.Assert.*;
import org.junit.*;

public class BStest {
	
	@Test
	public void test(){
		int array[] = new int[]{1,6,2,2,5};
		int result[] = new int[]{1,2,2,5,6};
		assertArrayEquals(result,BubbleSort.BubbleSort(array));
	}
	
}
