package com.geez;
public class Geez{
   public String[][] geez={
            {"1","፩"},
            {"2","፪"},
            {"3","፫"},
            {"4","፬"},
            {"5","፭"},
            {"6","፮"},
            {"7","፯"},
            {"8","፰"},
            {"9","፱"},
            {"10","፲"},
            {"20","፳"},
            {"30","፴"},
            {"40","፵"},
            {"50","፶"},
            {"60","፷"},
            {"70","፸"},
            {"80","፹"},
            {"90","፺"},
           {"100","፻"},
           {"10000","፼"},
    };

    private String geezNum="";
    private boolean last= false;
    private String reverse(String geez){
        char point;
        String reverse="";
        for (int i=geez.length()-1; i>=0; i--)
        {
            point= geez.charAt(i);
            reverse+=point;
        }
        return reverse;
    }

    private void tenth(int oriMod){
        String check = Integer.toString(oriMod);
        for (String[] i : geez) {
            for (int k = 0; k < i.length; k++) {
                if (i[k].equals(check)) geezNum+=i[1];
            }
        }
    }

    private void hundred(int oriMod){
        int originalNum = oriMod;
        int lastModules=0;
        while(originalNum>0){
            lastModules=originalNum%10;
            originalNum/=10;
        }
        String check = Integer.toString(lastModules);
        if(oriMod == 100 && last) geezNum+="፻";
        else if(oriMod/100 == lastModules){
            geezNum+="፻";
            for (String[] i : geez) {
                for (int k = 0; k < i.length; k++) {
                    if (i[k].equals(check)) geezNum+=i[1];
                }
            }
        }
    }

    private void thousand(int oriMod){
        boolean check = true;
        char ch ='፻';
        for(int i=0; i < geezNum.length();i++){
            if(ch == (geezNum.charAt(i))) {
                check=false;
                break;
            }
        }
        if(check) geezNum+="፻";
        tenth(oriMod/100);
    }

    private void tenThousand(int n){
        if(n == 10000 && last) geezNum+="፼";
        else{
            int k = n;
            int count=0;
            boolean check = true;
            char ch ='፼';
            for(int i=0; i < geezNum.length();i++){
                if(ch == (geezNum.charAt(i))) {
                    check=false;
                }
            }
            while(k > 0){
                k/=10;
                count++;
            }
            //  System.out.println(count);
            if(check) geezNum+="፼";
            if(count == 5){
                thousand(n/1000);
                tenth(n/10000);
            }else{
                tenth(n/10_000);
            }

        }

    }


    public String numToGeez(int n){
        int count=1;

        while(n>0) {
            if(n/10 == 0)last=true;
            int modules = n % 10;
            modules *= count;
            n /= 10;
            int modulesCount = 0;
            int oriMod = modules;
            while (modules > 0) {
                modulesCount++;
                modules/= 10;
            }
            //      System.out.println(modulesCount);
            if (modulesCount < 3) tenth(oriMod);
            else if (modulesCount < 4) hundred(oriMod);
            else if (modulesCount < 5 )thousand(oriMod);
            else tenThousand(oriMod);

            count *= 10;
        }
        return reverse(geezNum);
    }
   private int geezCheck(String a){
   int num=0;
       for(int i=0; i < geez.length; i++){
           for(int j=1; j < geez[i].length; j++){
               if(geez[i][j].equals(a)){
                   num=Integer.parseInt(geez[i][0]);
               }
           }
       }
       return num;
   }
   public int geezToNum(String geezWord){
       char point;
      int finalResult=0;
      int tempResult=0;
      int mul=1;
       for (int i=0; i<geezWord.length(); i++) {
           point= geezWord.charAt(i);
         int num=geezCheck(Character.toString(point));
         if(i+1 < geezWord.length() &&'፻' != geezWord.charAt(i+1) &&
                 '፼' != (geezWord.charAt(i))){
             tempResult+=num;
         }else if(i+1 < geezWord.length() &&'፻' == (geezWord.charAt(i+1))){
             tempResult+=num;
             mul=tempResult*100;
             finalResult+=mul;
             mul=1;
             i++;
             tempResult=0;
         }else if(i+1 < geezWord.length() &&'፼' == (geezWord.charAt(i+1))){
             tempResult+=num;
             mul=tempResult*1000;
             finalResult+=mul;
             mul=1;
             i++;
             tempResult=0;
         }else if('፼' == (geezWord.charAt(i))){
             if(i!=0) {
                 finalResult=num*tempResult;
                 tempResult=0;
             }
             else{
                 finalResult=num;
             }
         }
         else{
             finalResult=finalResult+num+tempResult;
         }
       }

       return finalResult;
   }
}
