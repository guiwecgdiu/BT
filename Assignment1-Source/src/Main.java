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
      st.insert(9);
      st.insert(6);
      st.insert(4);
      st.insert(15);
    //  st.infoPrint();   //this method can check the p l an f of all node
      st.remove( 20 );
      st.contains( 10 );
      st.contains( 30 );
      	st.insert( 30 );
      st.insert( 80 );
      st.insert(90);
      st.remove(20);
      st.insert( 20 );
      TreePrinter.printTree( st );
   
   }
}