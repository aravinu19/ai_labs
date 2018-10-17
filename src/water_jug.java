import support.node;

import static java.lang.System.exit;
import static java.lang.System.out;

import java.util.*;

public class water_jug {

    public static int MAX_CAPACITY_OF_LEFT_JUG, MAX_CAPACITY_OF_RIGHT_JUG, MAX_DEPTH_OF_TREE;
    public static Queue<node> queue;
    public static Stack<node> stack = new Stack<>();
    public static Set<node> alreadyEncountered;

    public static void main(String[] commandLineArgs){

        Scanner inputReader = new Scanner(System.in);

        out.println("The capacity of larger jug should be followed by the smaller jug and they should not be equal");
        out.println("Enter the capacity of two jugs : ");

        MAX_CAPACITY_OF_LEFT_JUG = inputReader.nextInt();
        MAX_CAPACITY_OF_RIGHT_JUG = inputReader.nextInt();
        MAX_DEPTH_OF_TREE = 15;

        out.println("Enter the required capacity : ");
        int capacity = inputReader.nextInt();

        while (true){

            out.println("\n\n\n\t\t\t\tWater Jug Problem \n\n");
            out.println("\t\t1. DFS Version");
            out.println("\t\t2. BFS Version");
            out.println("\t\tChoose the version you want to use [1/2]: ");

            switch (inputReader.nextInt()){

                case 1: DFS_Version(capacity); break;

                case 2: BFS_Version(capacity); break;

                default: out.println("Thanks for trying out :)"); exit(0);

            }

        }

    }

    private static void BFS_Version(int capacity) {

        node root = new node(0, 0);

        queue = new LinkedList<>();
        queue.add(root);

        alreadyEncountered = new HashSet<>();
        alreadyEncountered.add(root);

        int number_of_nodes_traversed = 0;
        int level = 1;
        boolean flag = false;

        while (!queue.isEmpty()){

            number_of_nodes_traversed++;

            int temp_variable_for_children = 0;
            node Node = queue.poll();

            if (Node.x == -1 && Node.y == -1){

                ++level;
                queue.add(new node(-1, -1));
                continue;

            }

            if (Node.x == capacity || Node.y == capacity){

                out.println("The required capacity can be achieved after " + (level + 1) + " move(s)");
                out.println("Number of nodes traversed: " + number_of_nodes_traversed);
                break;

            }

            if (level > MAX_DEPTH_OF_TREE){

                out.println("The desired capacity was not achieved within a tree depth of " + MAX_DEPTH_OF_TREE);
                out.println("Number of nodes traversed: " + number_of_nodes_traversed);
                break;

            }

            if (Node.x == MAX_CAPACITY_OF_LEFT_JUG && Node.y == 0){

                node new_node = new node(MAX_CAPACITY_OF_LEFT_JUG - MAX_CAPACITY_OF_RIGHT_JUG, MAX_CAPACITY_OF_RIGHT_JUG);

                if (check_if_encountered(new_node)) ++temp_variable_for_children;

            }else if (Node.y == MAX_CAPACITY_OF_RIGHT_JUG && Node.x == 0){

                node new_node = new node(MAX_CAPACITY_OF_RIGHT_JUG, 0);

                if (check_if_encountered(new_node)) ++temp_variable_for_children;

            }else if (Node.x == MAX_CAPACITY_OF_LEFT_JUG && Node.y == MAX_CAPACITY_OF_RIGHT_JUG){

                node new_node_1 = new node(0, MAX_CAPACITY_OF_RIGHT_JUG);

                if (check_if_encountered(new_node_1)) ++temp_variable_for_children;

                node new_node_2 = new node(MAX_CAPACITY_OF_LEFT_JUG, 0);

                if (check_if_encountered(new_node_2)) ++temp_variable_for_children;

            }else if (Node.x != 0 && Node.y != 0){

                node new_node_1 = new node(0, Node.y);
                if (check_if_encountered(new_node_1)) ++temp_variable_for_children;

                node new_node_2 = new node(Node.x, 0);
                if (check_if_encountered(new_node_2)) ++temp_variable_for_children;

                node new_node_3 = new node(Node.x - MAX_CAPACITY_OF_RIGHT_JUG + Node.y, MAX_CAPACITY_OF_RIGHT_JUG);
                if (check_if_encountered(new_node_3)) ++temp_variable_for_children;

                node new_node_4 = new node(MAX_CAPACITY_OF_LEFT_JUG, Node.y - MAX_CAPACITY_OF_LEFT_JUG + Node.x);
                if (check_if_encountered(new_node_4)) ++temp_variable_for_children;

            }else if (Node.x != 0){

                if (Node.x > MAX_CAPACITY_OF_RIGHT_JUG){

                    node new_node = new node(Node.x - MAX_CAPACITY_OF_RIGHT_JUG, MAX_CAPACITY_OF_RIGHT_JUG);
                    if (check_if_encountered(new_node)) ++temp_variable_for_children;

                }else {

                    node new_node = new node(0, Node.x);
                    if (check_if_encountered(new_node)) ++temp_variable_for_children;

                    node new_node_2 = new node(MAX_CAPACITY_OF_LEFT_JUG, Node.x);
                    if (check_if_encountered(new_node_2)) ++temp_variable_for_children;

                    node new_node_3 = new node(Node.x, MAX_CAPACITY_OF_RIGHT_JUG);
                    if (check_if_encountered(new_node_3)) ++temp_variable_for_children;

                }

            }else if (Node.y != 0){

                node new_node = new node(Node.y, 0);
                if (check_if_encountered(new_node)) ++temp_variable_for_children;

                node new_node_2 = new node(Node.y, MAX_CAPACITY_OF_RIGHT_JUG);
                if (check_if_encountered(new_node_2)) ++temp_variable_for_children;

                node new_node_3 = new node(MAX_CAPACITY_OF_LEFT_JUG, Node.y);
                if (check_if_encountered(new_node_3)) ++temp_variable_for_children;

            }else {

                node new_node_1 = new node(MAX_CAPACITY_OF_LEFT_JUG, 0);
                if (check_if_encountered(new_node_1)) ++temp_variable_for_children;

                node new_node_2 = new node(0, MAX_CAPACITY_OF_RIGHT_JUG);
                if (check_if_encountered(new_node_2)) ++temp_variable_for_children;

            }

            if (!flag)  queue.add(new node(-1, -1));

            flag = true;

        }

        for (node temp : queue){
            out.println("( " + temp.x + "," + " " + temp.y + ")");
        }

    }

    private static boolean check_if_encountered(node new_node) {

        if (!alreadyEncountered.contains(new_node)){

            queue.add(new_node);
            alreadyEncountered.add(new_node);
            return true;

        }

        return false;
    }

    private static void DFS_Version(int capacity) {

        node root = new node(0, 0);

        int level = 0;

        stack.push(root);

        alreadyEncountered = new HashSet<>();
        alreadyEncountered.add(root);

        Stack<Integer> keeps_track_of_current_level = new Stack<>();
        keeps_track_of_current_level.push(0);

        boolean solved_flag = false;
        int number_of_nodes_traversed = 0;

        while (!stack.isEmpty()){

            number_of_nodes_traversed++;

            node Node = stack.pop();

            int current_level = keeps_track_of_current_level.pop();

            if (Node.x == capacity || Node.y == capacity){

                out.println("The Required capacity can be acheived after " + current_level + "move(s)");
                out.println("Number of nodes traversed : " + number_of_nodes_traversed);
                solved_flag = true;
                break;

            }

            if (current_level > MAX_DEPTH_OF_TREE){
                continue;
            }

            if (Node.x == MAX_CAPACITY_OF_LEFT_JUG && Node.y == 0){

                node new_node = new node(MAX_CAPACITY_OF_LEFT_JUG - MAX_CAPACITY_OF_RIGHT_JUG, MAX_CAPACITY_OF_RIGHT_JUG);
                if (check_if_encountered_dfs(new_node)) keeps_track_of_current_level.push(current_level+1);

            } else if (Node.y == MAX_CAPACITY_OF_RIGHT_JUG && Node.x == 0){

                node new_node = new node(MAX_CAPACITY_OF_RIGHT_JUG, 0);
                if (check_if_encountered_dfs(new_node)) keeps_track_of_current_level.push(current_level+1);

            } else if (Node.x == MAX_CAPACITY_OF_LEFT_JUG && Node.y == MAX_CAPACITY_OF_RIGHT_JUG){

                node new_node_1 = new node(0, MAX_CAPACITY_OF_RIGHT_JUG);
                if (check_if_encountered_dfs(new_node_1)) keeps_track_of_current_level.push(current_level+1);

                node new_node_2 = new node(MAX_CAPACITY_OF_LEFT_JUG, 0);
                if (check_if_encountered_dfs(new_node_2)) keeps_track_of_current_level.push(current_level+1);

            } else if (Node.x != 0 && Node.y != 0){

                node new_node_1 = new node(0, Node.y);
                if (check_if_encountered_dfs(new_node_1)) keeps_track_of_current_level.push(current_level+1);

                node new_node_2 = new node(Node.x, 0);
                if (check_if_encountered_dfs(new_node_2)) keeps_track_of_current_level.push(current_level+1);

                node new_node_3 = new node(Node.x - MAX_CAPACITY_OF_RIGHT_JUG + Node.y, MAX_CAPACITY_OF_RIGHT_JUG);
                if (check_if_encountered_dfs(new_node_3)) keeps_track_of_current_level.push(current_level+1);

                node new_node_4 = new node(MAX_CAPACITY_OF_LEFT_JUG, Node.y - MAX_CAPACITY_OF_LEFT_JUG + Node.x);
                if (check_if_encountered_dfs(new_node_4)) keeps_track_of_current_level.push(current_level+1);

            } else if (Node.x != 0){

                if (Node.x > MAX_CAPACITY_OF_RIGHT_JUG){

                    node new_node = new node(Node.x - MAX_CAPACITY_OF_RIGHT_JUG, MAX_CAPACITY_OF_RIGHT_JUG);
                    if (check_if_encountered_dfs(new_node)) keeps_track_of_current_level.push(current_level+1);

                } else {

                    node new_node = new node(0, Node.x);
                    if (check_if_encountered_dfs(new_node)) keeps_track_of_current_level.push(current_level+1);

                    node new_node_2 = new node(MAX_CAPACITY_OF_LEFT_JUG, Node.x);
                    if (check_if_encountered_dfs(new_node_2)) keeps_track_of_current_level.push(current_level+1);

                    node new_node_3 = new node(Node.x, MAX_CAPACITY_OF_RIGHT_JUG);
                    if (check_if_encountered_dfs(new_node_3)) keeps_track_of_current_level.push(current_level+1);

                }

            } else if (Node.y != 0){

                node new_node = new node(Node.y, 0);
                if (check_if_encountered_dfs(new_node)) keeps_track_of_current_level.push(current_level+1);

                node new_node_1 = new node(Node.y, MAX_CAPACITY_OF_RIGHT_JUG);
                if (check_if_encountered_dfs(new_node_1)) keeps_track_of_current_level.push(current_level+1);

                node new_node_2 = new node(MAX_CAPACITY_OF_LEFT_JUG, Node.y);
                if (check_if_encountered_dfs(new_node_2)) keeps_track_of_current_level.push(current_level+1);

            } else {

                node new_node_1 = new node(MAX_CAPACITY_OF_LEFT_JUG, 0);
                if (check_if_encountered_dfs(new_node_1)) keeps_track_of_current_level.push(current_level+1);

                node new_node_2 = new node(0, MAX_CAPACITY_OF_RIGHT_JUG);
                if (check_if_encountered_dfs(new_node_2)) keeps_track_of_current_level.push(current_level+1);

            }

        }

        if (!solved_flag){

            out.println("The desired capacity was not achieved within a tree depth of 5");
            out.println("Number of nodes traversed : " + number_of_nodes_traversed);

        }

        for (node temp : stack){
            out.println("( " + temp.x + "," + " " + temp.y + ")");
        }

    }

    private static boolean check_if_encountered_dfs(node new_node) {

        if (!alreadyEncountered.contains(new_node)){

            stack.add(new_node);
            alreadyEncountered.add(new_node);
            return true;

        }

        return false;
    }

}
