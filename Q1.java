
package dsa;
import java.util.Scanner; // import the Scanner class 
import java.util.Arrays; 


public class DSA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in); //get input
        
        System.out.print("Number of Bottles & Number of barrels :"); 
        String[] x = myObj.nextLine().split(" ");   //call new input (.split=   input x array
        System.out.print("Volume of the barrels in bottles :"); 
        String[] y = myObj.nextLine().split(" "); //input y array   
        System.out.print("Price of each barrel :"); 
        String[] z = myObj.nextLine().split(" "); 
        int count = z.length;   //type of wines
        double bottles = Integer.parseInt(x[0]);   // get number of bottles .first we enter it as a string.so it must update as integer
        double[] p = new double[count];//p denote to each one bottle price
        for (int i=0 ; i<count ; i++){
            p[i] = Double.parseDouble(z[i])/Double.parseDouble(y[i]);     //should parse double. bcz first we get it string       
        }
        double price_v = 0;  //maxmimum value of the prize
        System.out.println();
        System.out.print("The value is ");
        System.out.println(price( x, y, z, p, bottles, price_v)); //call price function
        
        
        
    }
    static double price(String[] x, String[] y, String[] z, double[] p, double bottles, double price_v){
        
        double[] maxArray = max(p); //maximum value of among bottles
        
        if ( Double.parseDouble(y[(int)maxArray[0]]) > bottles ){  // feasibility function
            price_v = bottles*p[(int)maxArray[0]];
//            System.out.print("Last ");
//            System.out.println(p[(int)maxArray[0]]);


        }
        else{
            double max_value_bottles = Double.parseDouble(y[(int)maxArray[0]]);//maximum number of bottle can be filled by maximum unit price
            price_v = ((int)max_value_bottles)*p[(int)maxArray[0]];      //price of the fullfilled bottles from maximum price bottle   
            bottles = bottles - (int)Double.parseDouble(y[(int)maxArray[0]]); //number of remaining bottles (without fullfilled bottles)
            double half_bottle_price = (max_value_bottles%1)*p[(int)maxArray[0]];//price of the remaining wine from the maximum unit price wine
            if ( half_bottle_price%1 != 0 ){   //check if there any half filled bottles
                p[(int)maxArray[0]] = 0;  //set the maximum unit price equal to 0
                double[] array = max(p);     //then take next maxmimum unit price as array variable
                if (half_bottle_price > array[1]){      //check that remaining half filled bottle is greater than to the next maximum unit price
                    price_v = price_v + half_bottle_price;  //add the  half filled bottle price
                    bottles = bottles - 1;   //remaining bottles-1
                }
            }else{
                p[(int)maxArray[0]] = 0;       //maximum unit value assigned to 0
            }
            
            price_v = price_v + price( x, y, z, p, bottles, price_v);  //call the price method again
        }
        return price_v;  //solution function
    }
    
    static double[] max(double[] arr){  //to find max p
        double max = 0.0;
        double id = 0;    //position of max value in the p
        for ( int i=0; i< arr.length ; i++ ){
            if ( max < arr[i] ){
                
                id = i;
                max = arr[i];
            };
        };
        double[] array = {id,max}; //pass id and max as array
        return array;
    }
    
}




