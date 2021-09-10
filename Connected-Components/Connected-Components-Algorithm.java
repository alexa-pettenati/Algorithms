import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;
public class Graph {
    int nodes;
    LinkedList[] adjList;

    Graph(int nodes){
        this.nodes=nodes;
        adjList = new LinkedList[nodes];
        for(int i = 0; i < nodes ; i++){
            adjList[i] = new LinkedList<Integer>(); //list for every node
        }
    }

    public void dfs(int i, boolean[] discovered, boolean[] currComp){
        discovered[i]=true;
        currComp[i]=true;
        for(Object j : adjList[i]){
            if(discovered[(int) j]==false){
                dfs((Integer) j, discovered, currComp);
            }
        }
    }

    public int components(){
        int c=0;
        boolean[] discovered = new boolean[this.nodes];
        for(int i=0;i<discovered.length;i++){
            discovered[i]=false; //initialize to false
        }

        boolean[] currComp = new boolean[nodes];
        for(int i=0;i<currComp.length;i++){
            currComp[i]=false;
        }

        for(int i=0; i<this.nodes;i++){
            if(!discovered[i]){
                c++;
                dfs(i, discovered, currComp);
                System.out.println("Connected Component #"+c+": ");
                for(int j=0;j<currComp.length;j++){
                    if(currComp[j]==true)
                        System.out.print(j+1+" ");
                }
                System.out.println();
                for(int j=0;j<currComp.length;j++){
                    currComp[j]=false;
                }
            }
        }
        return c;
    }

    public void addEdge(int x, int y){
        adjList[y].add(x);
        adjList[x].add(y); //undirected
    }

    public static void main(String[]args) throws FileNotFoundException {
        System.out.println("Enter the file path: ");
        Scanner stdin = new Scanner(System.in);
        String pathname = stdin.nextLine();
        File file =
                new File(pathname);
        Scanner sc = new Scanner(file);
        int count =1;
        int nodes;
        Graph graph = null;
        while (sc.hasNextLine()) {
            if(count==2){
                nodes = Integer.parseInt(sc.nextLine().trim()); //1st is the number of nodes
                graph = new Graph(nodes);
            }else if(count==1){
                //second is the number of edges
                sc.nextLine();
            }else { //put edges into graph
                String[] split = sc.nextLine().split("\\s+");
                int x = Integer.parseInt(split[0]);
                int y = Integer.parseInt(split[1]);
                x=x-1;
                y=y-1;
                graph.addEdge(x, y);
            }
            count++;
        }
        int components = graph.components();
        System.out.println("Number of connected components is: "+components);
    }
}

