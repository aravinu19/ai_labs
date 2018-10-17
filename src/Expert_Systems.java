import java.util.Hashtable;
import java.util.Scanner;
import java.util.Stack;

import static java.lang.System.*;

public class Expert_Systems {

    public static void main(String[] commandLineArgs){

        String input;
        Stack<String> stk = new Stack<>();
        Hashtable<String, String> ht = new Hashtable<>();
        Scanner inputReader = new Scanner(in);

        out.println("Enter the string : ");
        input = inputReader.nextLine();

        ht.put("}","{");
        ht.put(")","(");
        ht.put(">","<");
        ht.put("\"","\"");

        try {
            String[] ch = input.split(" ");

            for (String temp : ch){

                if (ht.containsValue(temp)){

                    stk.push(temp);

                } else if (ht.containsKey(temp)){

                    String st = stk.peek();
                    String kv = ht.get(temp);

                    if (st.equals(kv)){

                        stk.pop();

                    } else {

                        out.println("Compilation Error");
                        return;

                    }

                }

            }

            if (stk.size() > 0) out.println("Compilation Error");
            else out.println("No Errors");

        } catch (Exception e){

            out.println("Compilation Errors");

        }

    }

}
