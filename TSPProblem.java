import java.io.*;
import java.util.*;
 public class TSPProblem{
     
     
public static String Permutation_string;
public static String minimumTour;
 public static int[] permutationArray;
 public static int[][]inputCostArray;
public  static int mincost;
 public static int maxcost;
 
 
    public static void main(String[] args) {
      int count =0;
      int size=0;
      int cursor=0;
      String sizesarray[]=null;
      String dataarray[]=null;

      try
      {
            LineNumberReader ln=new LineNumberReader(new FileReader(args[0]));
             FileOutputStream BW=new FileOutputStream(new File(args[1]));
            size=Integer.parseInt(ln.readLine());
            String data="";
           ln.setLineNumber(cursor+1);
            String Line=null;
            String sizes=size+"";
            while((Line=ln.readLine())!=null)
             {
                    if(count==((size*size)+cursor))
                    {
                        data=data+"@";
                        cursor=count;
                        ln.setLineNumber((size*size)+1);
                        size=Integer.parseInt(Line);
                        sizes=sizes+","+size;
                        ln.setLineNumber(cursor);

                    }
                    else
                    {
                        data=data+Line+"\n";
                        count++;
                }
           }
           
            sizesarray=sizes.split(",");
            dataarray=data.split("@");
                       
           for(int i=0;i<sizesarray.length;i++)
           {
               int inputSize=Integer.parseInt(sizesarray[i].toString().trim());
               inputCostArray=new int[inputSize][inputSize];
             
            String inputDataArray[]=dataarray[i].split("[\\r\\n]+");
               
             for(int l=0;l<inputSize;l++)
             {                
                 for(int m=0;m<inputSize;m++)
                 {
                
                     inputCostArray[l][m]=Integer.parseInt(inputDataArray[(m%inputSize)+(l*inputSize)].split(" ")[2]);
                 }
             }
             permutationArray=new int[inputSize];            
             for(int itr=0;itr<inputSize;itr++)
             {
                 permutationArray[itr]=itr;
                               
             }
                mincost=100000;
                maxcost=-100000;
                minimumTour="";
                int n=permutationArray[0];
                long time1 = System.currentTimeMillis();//.nanoTime();
                perm(n);
                long time2 = System.currentTimeMillis();
                long timeTaken = time2 - time1;
  
                 String temp="";
                           
                 String Content=(i+1)+" "+inputSize+" "+mincost+" "+maxcost+" "+timeTaken+" ms \nminimumTour"+minimumTour;
                 byte[]con=Content.getBytes();   
                 BW.write(con);
    
           }
        }     
            catch(Exception ex)
        {
            ex.printStackTrace();
        }

    }
    
    
    public static void perm(int start)
    {  
      
        //System.out.println("First "+start); 
        if(start<permutationArray.length-1)
        {
            for(int i=start;i<permutationArray.length;i++)
            {                 
                 swap(permutationArray,i,start);
                 perm(start+1);
                 swap(permutationArray,i,start);
                          
            }
            
        } 
        else
        {           
            
               permutation_Cost(permutationArray, permutationArray.length);
                    
        }
        
    }
    public static void swap(int array[],int a,int b)
    {
         int temp=array[a];
         array[a]=array[b];
         array[b]=temp;
    }
    
    
public static void permutation_Cost(int permutationArray[], int n)
{
    int i, sum = 0;
    Permutation_string="";
   
    for(i = 0; i < n; i++)
    {
        sum += inputCostArray[permutationArray[i%n]][permutationArray[(i + 1) % n]];
       Permutation_string+="\n"+permutationArray[i];
    }
	if(permutationArray[0]==0)
	{
		//System.out.println("if wala "+Permutation_string);
    if (mincost > sum)
    {
		
        mincost = sum;
        minimumTour=Permutation_string;
		//System.out.print(minimumTour+" cost is  "+mincost);
    }
    if(maxcost<sum)
    {
        maxcost=sum;
    }
	}
	else
	{
		//System.out.println("else  wala "+Permutation_string);
	}
}      
}
