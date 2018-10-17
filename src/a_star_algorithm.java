import java.util.Scanner;

import static java.lang.System.*;


public class a_star_algorithm {

    public static int INFINITY = 99, no_of_nodes;
    public static int[] estimate = new int[10];
    public static int[][] cost = new int[10][10];

    public static void main(String[] commandLineArgs){

        Scanner inputReader = new Scanner(in);

        out.println("Enter the number of nodes : ");
        no_of_nodes = inputReader.nextInt();

        out.println("Enter 99 for no edge");

        for (int index1 = 1; index1 <= no_of_nodes; index1++){

            cost[index1][index1] = 99;

            for (int index2 = index1 + 1; index2 <= no_of_nodes; index2++){

                out.println("Enter the edge " + index1 + " -> " + index2 + " value : ");
                cost[index1][index2] = inputReader.nextInt();
                cost[index2][index1] = cost[index1][index2];

            }

        }

        for (int index = 1; index <= no_of_nodes; index++){

            out.println("Enter the Estimate distance of " + index + ": ");
            estimate[index] = inputReader.nextInt();

        }

        Display();
        visit(1);

    }

    private static void visit(int visit_now) {

        out.print("\n" + visit_now + " -> ");
        for (int index = 1; index <= no_of_nodes; index++){

            visit_now = nearest(visit_now);
            out.print(visit_now);

            if (visit_now == no_of_nodes){

                out.println("\nReached");
                return;

            }

            out.print(" -> ");

        }

        out.println("No Path");

    }

    private static int nearest(int visit_now) {

        int node, weight;

        weight = INFINITY;
        node = visit_now;

        for (int index = 1; index <= no_of_nodes; index++){

            if (cost[visit_now][index] == INFINITY) continue;

            if (estimate[index] < weight){

                weight = estimate[index];
                node = index;

            }

        }

        return node;
    }

    private static void Display() {

        out.println("Cost Matrix \n");

        for (int index1 = 1; index1 <= no_of_nodes; index1++){

            for (int index2 = 1; index2 <= no_of_nodes; index2++){

                out.print("" + cost[index1][index2] + "\t");

            }

            out.println();

        }

    }

}
