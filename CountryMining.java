//Benjamin S. Strait, 11/21/20
import java.io.*;
import java.util.Scanner;

public class CountryMining
{
   private int size;
   private String [] country;
   private String [] date;
   private String [] caseCount;
   private String [] US;
   private String endJan = "1/31/2020";
   private String endFeb = "2/29/2020";
   private String endMar = "3/31/2020";
   private String endApr = "4/30/2020";
   private String endMay = "5/15/2020";
   
   public CountryMining(String fileName) throws IOException
   {
      File file = new File(fileName);
      Scanner inputF = new Scanner(file);
      while(inputF.hasNext())
      {
         String str = inputF.nextLine();
         size++;
      }
      inputF.close();
      //2nd scan
      
      inputF = new Scanner(file);
      country = new String [size];
      date = new String [size];
      caseCount = new String [size];
      US = new String [size];
      
      int index = 0;
      while(inputF.hasNext())
      {
         int i = 0;
         String line = inputF.nextLine();
         String [] tokens = line.split(",");
         US [index] = tokens[1].trim();
         country [index] = tokens[2].trim();
         date [index] = tokens[3].trim();
         caseCount [index] = tokens[4].trim();
         for (i=0; i<country[index].length();i++)
         {
            char c = country[index].charAt(i);
            if (!Character.isLetter(c))
            {
               date [index] = tokens[4].trim();
               caseCount [index] = tokens[5].trim();
            }
         }
         index++;
      }
      inputF.close();
   }
   
   public int findTotalCountries(String c)
   {
      int total = 0;
      for(int i = 0; i < country.length; i++)
      {
         if(country[i].equals(c))
         {
            if(date[i].equals(endMay))
            {
               total+=Integer.parseInt(caseCount[i]);
            }
         }
      }
      return total;
   }
   
      public int findTotalCountriesOnDate(String c, int d)
   {
      int total = 0;
      String selected ="";
      if (d == 1)
      {
         selected = endJan;
      }
      else if (d == 2)
      {
         selected = endFeb;
      }
      else if (d == 3)
      {
         selected = endMar;
      }
      else if (d == 4)
      {
         selected = endApr;
      }
      else if (d == 5)
      {
         selected = endMay;
      }

      for(int i = 0; i < country.length; i++)
      {
         if(country[i].equals(c))
         {
            if(date[i].equals(selected))
            {
               total+=Integer.parseInt(caseCount[i]);
            }
         }
      }
      return total;
   }
   
   public int findTotalSatesOnDate(String s, int d)
   {
      int total = 0;
      String selected ="";
      if (d == 1)
      {
         selected = endJan;
      }
      else if (d == 2)
      {
         selected = endFeb;
      }
      else if (d == 3)
      {
         selected = endMar;
      }
      else if (d == 4)
      {
         selected = endApr;
      }
      else if (d == 5)
      {
         selected = endMay;
      }

      for(int i = 0; i < US.length; i++)
      {
         if(US[i].equals(s))
         {
            if(date[i].equals(selected))
            {
               total+=Integer.parseInt(caseCount[i]);
            }
         }
      }
      return total;
   }
   
   
   public int findTotalStates(String s)
   {
      int total = 0;
      for(int i = 0; i < US.length; i++)
      {
         if(US[i].equals(s))
         {
            if(date[i].equals(endMay))
            {
               total+=Integer.parseInt(caseCount[i]);
            }
         }
      }
      return total;
   }
   
   
   public double findAverage(String s)
   {
      double total = 0;
      double dTotal = 0;
      for(int i = 0; i < US.length; i++)
      {
         if(US[i].equals(s))
         {
            if(date[i].equals(endMay))
            {
               total+=Double.parseDouble(caseCount[i]);
            }
            dTotal++;
         }
      }
      double average = total/dTotal;
      return average;
   }


   
      public int findTotalDate(int d)
   {
      int total = 0;
      String selected ="";
      if (d == 1)
      {
         selected = endJan;
      }
      else if (d == 2)
      {
         selected = endFeb;
      }
      else if (d == 3)
      {
         selected = endMar;
      }
      else if (d == 4)
      {
         selected = endApr;
      }
      else if (d == 5)
      {
         selected = endMay;
      }

      for(int i = 0; i < date.length; i++)
      {
         if(date[i].equals(selected))
         {
            total+=Integer.parseInt(caseCount[i]);
         }
      }
      return total;
   }
   
   public int findTotalDateForStates(int d)
   {
      int total = 0;
      String selected ="";
      if (d == 1)
      {
         selected = endJan;
      }
      else if (d == 2)
      {
         selected = endFeb;
      }
      else if (d == 3)
      {
         selected = endMar;
      }
      else if (d == 4)
      {
         selected = endApr;
      }
      else if (d == 5)
      {
         selected = endMay;
      }

      for(int i = 0; i < date.length; i++)
      {
         if(date[i].equals(selected) && country[i].equals("US"))
         {
            total+=Integer.parseInt(caseCount[i]);
         }
      }
      return total;
   }
   
   
   public String highestCaseCount()
   {
      int highest = 0;
      int day = 0;
      int caseI = 0;
      for(int i = 1; i < US.length; i++)
      {
         if(country[i].equals("US"))
         {
            day = Integer.parseInt(caseCount[i]) - Integer.parseInt(caseCount[i-1]);
            if(day > highest)
            {
               highest = day;
               caseI = i;
            }
         }
      }
      
      String finalReturn = "Highest Number of Cases on a day: " + highest + "\nDate: " +date[caseI] + "\nState: " + US[caseI];
      return finalReturn;
   }
   
   
   public int getSize()
   {
      return size;
   }
   
   
   public String[] getListCountry()
   {
      String[] temp = new String[country.length];
      String currentCountry = "";
      int count = 0;
      int i = 0;
      for (i = 0; i< country.length; i++)
      {
         if (!country[i].equals(currentCountry))
         {
            temp[count] = country[i];
            currentCountry = country[i];
            count++;
         }
      }
      String[] list = new String[count];
      for (i = 0; i< count; i++)
      {
         list[i] = temp[i];
      }
      return list;
   }
   
   
   public String[] getListUS()
   {
      String[] temp = new String[US.length];
      String currentState = "";
      int count = 0;
      int i = 0;
      for (i = 0; i< US.length; i++)
      {
         if (!US[i].equals(currentState)&&country[i].equals("US"))
         {
            temp[count] = US[i];
            currentState = US[i];
            count++;
         }
      }
      String[] list = new String[count];
      for (i = 0; i< count; i++)
      {
         list[i] = temp[i];
      }
      return list;
   }
}