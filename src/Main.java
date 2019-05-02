import dsa.impl.*;
import dsa.util.TreePrinter;

public class Main {
   public static void main(String[] args ) {
      // Replace this with your code to test your implementations.
      // This just an example of one simple test for a AVL Tree.
      AVLTree<Integer> st = new AVLTree<Integer>();
      
      st.insert( 10 );
      st.insert( 20 );
      st.insert( 40 );
     st.insert(15);
     st.remove( 20 );
      st.contains( 10 );
      st.contains( 30 );
      st.insert( 30 );
    st.insert( 80 );
     st.insert(90);
     st.remove(20);
     st.insert( 20 );
      System.out.println("insert 30");
      TreePrinter.printTree( st );
   
   }
}