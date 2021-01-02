import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {

    public static void main(String args[]){

        int a = 1;

        System.out.println(getBit(4,1));
        System.out.println(countBits(32));
        int x = 100;
        System.out.println(Integer.toBinaryString(x));
        System.out.println("000000X00X1X10XX100101X00X00X1101110".length());
        System.out.println(intToString(5127835,4));



    }

    public static String intToString(int number, int groupSize) {
        StringBuilder result = new StringBuilder();

        for(int i = 35; i >= 0 ; i--) {
            int mask = 1 << i;
            result.append((number & mask) != 0 ? "1" : "0");

//            if (i % groupSize == 0)
//                result.append(" ");
        }
        //result.replace(result.length() - 1, result.length(), "");

        return result.toString();
    }

    static int getBit(int n, int k) {
        return (n >> k) & 1;
    }

    static int countBits(int number)
    {

        // log function in base 2
        // take only integer part
        return (int)(Math.log(number) /
                Math.log(2) + 1);
    }

    private static long gcd(long a, long b)
    {
        while (b > 0)
        {
            long temp = b;
            b = a % b; // % is remainder
            a = temp;
        }
        return a;
    }

    private static long gcd(long[] input)
    {
        long result = input[0];
        for(int i = 1; i < input.length; i++) result = gcd(result, input[i]);
        return result;
    }

    private static long lcm(long a, long b)
    {
        return a * (b / gcd(a, b));
    }

    private static long lcm(long[] input)
    {
        long result = input[0];
        for(int i = 1; i < input.length; i++) result = lcm(result, input[i]);
        return result;
    }

    void printSubArray(int arr[])
    {

        int n=arr.length;
        for (int i=0; i <n; i++) //This loop will select start element
        {
            for (int j=i; j<n; j++)   //This loop will select end element
            {
                for (int k=i; k<=j; k++) //This loop will print element from start to end

                {
                    System.out.print( arr[k]+" ");
                }
                System.out.println();
            }
        }
    }
}
