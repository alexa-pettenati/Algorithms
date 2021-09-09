import java.util.Scanner;

class Cell{
    int cost;
    Cell parent;
    String attr;
}

public class StringMatching {
    static int min(int x, int y, int z) {
        if (x <= y && x <= z)
            return x;
        if (y <= x && y <= z)
            return y;
        else
            return z;
    }

    static int editDist(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();
        Cell table[][] = new Cell[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                table[i][j]= new Cell();
                table[i][j].cost=0;
                table[i][j].parent=null;
            }
        }

        for(int i=0;i<m;i++){
            try {
                table[i][0].cost = i;
                table[i][0].parent = table[i - 1][0];
                table[i][0].attr = "I";
            }catch(Exception x){
                table[i][0].cost = i;
                table[i][0].parent = null;
                table[i][0].attr = "I";
            }
        }

        for(int i=0;i<n;i++){
            try {
                table[0][i].cost = i;
                table[0][i].parent = table[0][i - 1];
                table[0][i].attr = "D";
            }catch(Exception x){
                table[0][i].cost = i;
                table[0][i].parent = null;
                table[0][i].attr = "D";
            }
        }

        if(str1.charAt(0)==str2.charAt(0)){
            table[0][0].attr = "M";
            table[0][0].cost = 0;
            table[0][0].parent = null;
        }else{
            table[0][0].attr = "S";
            table[0][0].cost = 1;
            table[0][0].parent = null;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {// 1st string empty, insert chars
                if (str1.charAt(i) == str2.charAt(j)) {//last chars are the same, match
                    table[i][j].cost = table[i - 1][j - 1].cost;
                    table[i][j].parent=table[i-1][j-1];
                    table[i][j].attr="M";
                } else {//last char is different, go through all possibilities to find the min
                    table[i][j].cost = 1 + min(table[i][j - 1].cost, table[i - 1][j].cost, table[i - 1][j - 1].cost);
                    //min(insert, remove, substitution)
                    if(table[i][j-1].cost==min(table[i][j - 1].cost, table[i - 1][j].cost, table[i - 1][j - 1].cost)){
                        table[i][j].parent=table[i][j-1];
                        table[i][j].attr="I";
                    }else if(table[i-1][j].cost==min(table[i][j - 1].cost, table[i - 1][j].cost, table[i - 1][j - 1].cost)){
                        table[i][j].parent=table[i-1][j];
                        table[i][j].attr="D";
                    }else{
                        table[i][j].parent=table[i-1][j-1];
                        table[i][j].attr="S";
                    }
                }
            }
        }

        Cell ans = table[m-1][n-1];
        String print="";
        while(ans.parent!=null){
            print+=ans.parent.attr;
            ans=ans.parent;
        }
        StringBuilder input1 = new StringBuilder();
        input1.append(print);
        input1 = input1.reverse();
        System.out.println("Operation order: "+input1);
        return table[m-1][n-1].cost; //ans
    }

    public static void main(String args[]) {
        Scanner stdin = new Scanner(System.in);
        System.out.println("Enter string one: ");
        String str1 = stdin.nextLine();
        System.out.println("Enter string two: ");
        String str2 = stdin.nextLine();
        int ans = editDist(str1, str2);
        System.out.println("Edit distance: "+ans);
    }
}


