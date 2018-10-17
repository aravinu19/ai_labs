import java.util.Scanner;

public class graph_coloring_problem {

    private static int vector;
    private static int number_of_colors;
    private static int[] colors;
    private static int[][] graph;

    public static void main(String[] commadLineArgs){

        Scanner input = new Scanner(System.in);

        System.out.println("\t \t Graph Coloring Algorithm ");

        System.out.println("enter number of vertices : ");

        vector = input.nextInt();

        graph = new int[vector][vector];

        System.out.println("Enter Graph Matrix : \n");
        for (int index = 0; index < vector; index++){
            for (int index2 = 0; index2 < vector; index2++){
                graph[index][index2] = input.nextInt();
            }
        }

        System.out.print("\n Enter the number of colors : ");
        number_of_colors = input.nextInt();

        graph_color();

    }

    private static void graph_color() {

        colors = new int[vector];

        try {
            solve(0);
            System.out.println("No Solution");
        }catch (Exception e){

            System.out.println("\n Solution Exists");
            Display();

        }

    }

    private static void solve(int vector_check) throws Exception {

        if (vector_check == vector){
            throw new Exception("Solution Found");
        }

        for (int color = 1; color <= number_of_colors; color++){

            if (isPossible(vector_check, color)){

                colors[vector_check] = color;
                solve(vector_check + 1);
                colors[vector_check] = 0;

            }

        }

    }

    private static boolean isPossible(int vector_check, int color) {

        for (int index = 0; index < vector; index++){

            if (graph[vector_check][index] == 1 && color == colors[index]){

                return false;

            }

        }

        return true;
    }

    private static void Display(){

        System.out.println("Colors : ");
        for (int index = 0; index < vector; index++){
            System.out.print(colors[index] + " ");
        }

        System.out.println();

    }

}
