package euler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 Maximum path sum I
 Problem 18
 By starting at the top of the triangle below and moving to adjacent numbers on the row below, the maximum
 total from top to bottom is 23.

 .    3
 .   7 4
 .  2 4 6
 . 8 5 9 3

 That is, 3 + 7 + 4 + 9 = 23.

 Find the maximum total from top to bottom of the triangle below:

 .                            75
 .                          95  64
 .                        17  47  82
 .                      18  35  87  10
 .                    20  04  82  47  65
 .                  19  01  23  75  03  34
 .                88  02  77  73  07  63  67
 .              99  65  04  28  06  16  70  92
 .            41  41  26  56  83  40  80  70  33
 .          41  48  72  33  47  32  37  16  94  29
 .        53  71  44  65  25  43  91  52  97  51  14
 .      70  11  33  28  77  73  17  78  39  68  17  57
 .    91  71  52  38  17  14  91  43  58  50  27  29  48
 .  63  66  04  68  89  53  67  30  73  16  69  87  40  31
 .04  62  98  27  23  09  70  98  73  93  38  53  60  04  23

 NOTE: As there are only 16384 routes, it is possible to solve this problem by trying every route. However, Problem
 67, is the same challenge with a triangle containing one-hundred rows; it cannot be solved by brute
 force, and requires a clever method! ;o)

75
95 64
17 47 82
18 35 87 10
20 04 82 47 65
19 01 23 75 03 34
88 02 77 73 07 63 67
99 65 04 28 06 16 70 92
41 41 26 56 83 40 80 70 33
41 48 72 33 47 32 37 16 94 29
53 71 44 65 25 43 91 52 97 51 14
70 11 33 28 77 73 17 78 39 68 17 57
91 71 52 38 17 14 91 43 58 50 27 29 48
63 66 04 68 89 53 67 30 73 16 69 87 40 31
04 62 98 27 23 09 70 98 73 93 38 53 60 04 23
 */

public class Problem0018 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<List<Integer>> list = new ArrayList<List<Integer>>();
		list = setup();

		int maxIdx = 0;
	//	int maxSum = 0;
	//	int maxVal = 0;
		int sum = 0;
		List<Integer> numbersChosen = new ArrayList<Integer>();

		for (int rowNum = list.size() - 1; rowNum > -1; rowNum--) {

			List<Integer> row = new ArrayList<Integer>(list.get(rowNum));

			if (rowNum == 0 && row.size() == 1) {
				System.out.println("Adding last row");
				numbersChosen.add(row.get(0));
				sum += row.get(0);
			}

			List<Integer> nextRow = new ArrayList<Integer>(list.get(rowNum + 1));
			// Check out the n, n+1 elements of the next row
			System.out.println("Current row = " + row);
			System.out.println("Next row = " + nextRow);
			int maxZ = 0;
			System.out.println("Position of max value in current row = "
					+ maxIdx);

			for (int z = maxIdx, uLimit = maxIdx + 2; z < uLimit; z++) {
				System.out.println("Checking element no " + z + " in next row");

				if (z > -1 && z < nextRow.size()) {
					if (nextRow.get(z) > maxZ) {
						maxZ = nextRow.get(z);
						maxIdx = z;

					}
				}

			}
			
			System.out.println("Next row maximum value = " + maxZ
					+ " at position " + maxIdx);
			System.out.println("Adding " + maxZ);
			numbersChosen.add(maxZ);
			sum += maxZ;
		}

		System.out.println("Sum is " + sum);
		System.out.println("Numbers chosen = " + numbersChosen);
		for (List<Integer> x : list) {
			System.out.println(x);
		}
		
		int lastSum = 0;
		for (Integer x : numbersChosen) {
			lastSum += x;
		}
		
		System.out.println("Last sum " + lastSum);
	}
private static Integer getIndexOfHighestNumber(Integer previousHighestIndex) {
	if (previousHighestIndex == null) {
		return 0;
	}
	
	//
}
	private static List<List<Integer>> setup() {
		List<List<Integer>> list = new ArrayList<List<Integer>>();
		list.add(new ArrayList<Integer>(Arrays.asList(75)));
		list.add(new ArrayList<Integer>(Arrays.asList(95, 64)));
		list.add(new ArrayList<Integer>(Arrays.asList(17, 47, 82)));
		list.add(new ArrayList<Integer>(Arrays.asList(18, 35, 87, 10)));
		list.add(new ArrayList<Integer>(Arrays.asList(20, 4, 82, 47, 65)));
		list.add(new ArrayList<Integer>(Arrays.asList(19, 1, 23, 75, 3, 34)));
		list.add(new ArrayList<Integer>(Arrays.asList(88, 2, 77, 73, 7, 63, 67)));
		list.add(new ArrayList<Integer>(Arrays.asList(99, 65, 4, 28, 6, 16, 70,
				92)));
		list.add(new ArrayList<Integer>(Arrays.asList(41, 41, 26, 56, 83, 40,
				80, 70, 33)));
		list.add(new ArrayList<Integer>(Arrays.asList(41, 48, 72, 33, 47, 32,
				37, 16, 94, 29)));
		list.add(new ArrayList<Integer>(Arrays.asList(53, 71, 44, 65, 25, 43,
				91, 52, 97, 51, 14)));
		list.add(new ArrayList<Integer>(Arrays.asList(70, 11, 33, 28, 77, 73,
				17, 78, 39, 68, 17, 57)));
		list.add(new ArrayList<Integer>(Arrays.asList(91, 71, 52, 38, 17, 14,
				91, 43, 58, 50, 27, 29, 48)));
		list.add(new ArrayList<Integer>(Arrays.asList(63, 66, 4, 68, 89, 53,
				67, 30, 73, 16, 69, 87, 40, 31)));
		list.add(new ArrayList<Integer>(Arrays.asList(04, 62, 98, 27, 23, 9,
				70, 98, 73, 93, 38, 53, 60, 4, 23)));
		return list;
	}
}
