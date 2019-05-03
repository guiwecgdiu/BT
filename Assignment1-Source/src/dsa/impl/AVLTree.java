package dsa.impl;

import java.util.Iterator;

import dsa.iface.IIterator;
import dsa.iface.INode;

public class AVLTree<T extends Comparable<T>> extends BinarySearchTree<T> {
	
	
	//180 219 276 252

	
	public AVLTree() {
		this.root=new HBTNode(null,null,null,null);
	}
	
	
	
	
	private class HBTNode extends BTNode{
		private int height=0;

		public HBTNode(T e, AbstractBinaryTree<T>.BTNode p, AbstractBinaryTree<T>.BTNode l,
				AbstractBinaryTree<T>.BTNode r) {
			super(e, p, l, r);
		
		}

		public HBTNode(T e, AbstractBinaryTree<T>.BTNode p) {
			super(e, p);
			
		}

		public int getHeight() {
			return height;
		}

		public void setHeight(int height) {
			this.height = height;
		}
	}
	

	
	

	
	private void insertNode(T element) {
		HBTNode b=(AVLTree<T>.HBTNode) this.avlFind(element);
		if(this.isExternal(b)){
			this.HExpendEternalNode(b, element);				
			}else {
				return;
			}
	}
	
	
@Override
   public void insert( T element ) 
   {	
      // TODO: Implement the insert(...) method.
		
	//insert
		this.insertNode(element);
	   INode<T> i=this.avlFind(element);//Record the inserted node
	
	   //Whole process for restructure
	   if(!this.isAboveBlance(i)) 
	   {
		   INode<T> z=this.findUnbalanceNode(i);
		   INode<T> x=this.higherChil(this.higherChil(z));
		   restructure(x);
	   }
	   
	   this.update();
	   
   }
   
   @Override
   public boolean contains( T element ) 
   {
      // TODO: Implement the contains(...) method.
      return super.contains(element);
  
   
   }
   
   private void update() //Update height for each node
   {
	   IIterator i=this.nodes();
	   while(i.hasNext()) 
	   {
			BTNode b=(AbstractBinaryTree<T>.BTNode) i.next();
			HBTNode h=(AVLTree<T>.HBTNode) b;
			if(h.element!=null) {
					h.setHeight(this.height(b));
			}
	   }
	}
   

 
   
   @Override
   public void remove( T element ) 
   {
      // TODO: Implement the remove(...) method.
	  
	   if(this.contains(element) == false) {
		   return;
	   }
	   BTNode removal=(AbstractBinaryTree<T>.BTNode) this.findNode(element);
	   BTNode nextLarger=(AbstractBinaryTree<T>.BTNode) this.nextLarge(removal);
	   //Find and record the node which is next larger than removed one 
	   
	   super.remove(element);
	   
	   
	   if(this.height(root)>2)        //Protection
	   {
		   
		   BTNode b=nextLarger.parent;       //Start at the 'nextlarger' node
		   
		   while(!this.isRoot(b)) 			//Check and go upward till root 
		   {
			   if(!this.isBalance(b)) 		//Check balance for each layer,identify the position of z
			   {
				   this.restructure(this.higherChil(this.higherChil(b)));    //restructure by x
			   }
			   b=b.parent;					//go upward
		   }
		   
		   if(!this.isBalance(b)) 
		   {
			   this.restructure(this.higherChil(this.higherChil(b)));			//check and balance root
		   }
	   }
	   
	   this.update();
   } 
   
   
   
   private boolean isBalance(INode<T> n) 		//check balance
   {
	   BTNode b=(AbstractBinaryTree<T>.BTNode) n;
	   
	   if(Math.abs(height(b.left)-height(b.right)) >= 2) 
	   {
		   return false;
	   }else {
		   return true;
	   }
   }

   private void restructure( INode<T> xn ) {
      // Implement the restructure(...) method.
	   
	   if(this.depth(xn)<2)			//protection 
	   {
		   return;
	   }
	   
	   
	   BTNode x=(AbstractBinaryTree<T>.BTNode) xn;
	   BTNode y=x.parent;
	   BTNode z=y.parent;
	   								//Record the references of x,y,z	
	   
		  if(z.element().compareTo(y.element())>y.element().compareTo(x.element())) {      //first 1 second -1 {left--->right} z>y y<x
			 
			  	BTNode leftx=x.left;
			  	BTNode rightx=x.right;
			
			  	if(this.isRoot(z)) {				//Deal with the z's upward node
			  		
			  		x.parent=null;						//pay attention,its absent might cause endless recursion
			  		this.root=x;     
			  	
			  	}else {
			  	
			  		BTNode zp=z.parent;				//zp record the parent of z
			  		if(zp.right == z) {
			  			zp.right=x;
			  			x.parent=zp;
			  		}else if(zp.left == z) {
					zp.left=x;
			  			x.parent=zp;
			 		}
			  	}
				
				  
				  x.right=z;
				  z.parent=x;
				  
				  x.left=y;
				  y.parent=x;
				  
				  y.right=leftx;
				  leftx.parent=y;
				  
				  z.left=rightx;
				  rightx.parent=z;
				  
			  
				  
		  }else if(z.element().compareTo(y.element()) < y.element().compareTo(x.element())){//first -1 second1 {right---->left}
			  
			  BTNode leftx=x.left;
			  BTNode rightx=x.right;
			  
			  if(this.isRoot(z)) {
				  
					x.parent=null;//pay attention,its absent might cause endless recursion	
				  	this.root=x;
				  	
				 }else {
					  BTNode zp=z.parent;
					  if(zp.right == z) {
						  zp.right=x;
						  x.parent=zp;
					  }else if(zp.left == z) {
						  zp.left=x;
						  x.parent=zp;
					  }
				 }
			  
			  	x.left=z;
			  	z.parent=x;
			  	
			  	x.right=y;
			  	y.parent=x;
			  	
			  	z.right=leftx;
			  	leftx.parent=z;
			  	
			  	y.left=rightx;
			  	rightx.parent=y;
			  	
			  	
			  
			  
			  
		  }else if(z.element().compareTo(y.element())==1&&y.element().compareTo(x.element())==1) { // first 1 second 1 {left--->left}
			  BTNode righty=y.right;
			  if(this.isRoot(z)) {
				  y.parent=null;
				  this.root=y;
			  }else  {
				  BTNode zp=z.parent;
				  if(zp.right == z) {
					  zp.right=y;
					  y.parent=zp;
				  }else if(zp.left == z) {
					  zp.left=y;
					  y.parent=zp;
				  }
			  }
				  
				  y.right=z;
				  z.parent=y;
				  
				  z.left=righty;
				  righty.parent=z;
				  
				  
			  
		  }else if(z.element().compareTo(y.element())==-1&&y.element().compareTo(x.element())==-1) {// first -1 second -1 {right--->right}
			  BTNode lefty=y.left;
			  if(this.isRoot(z)) {
				  y.parent=null;
				  this.root=y;
			  }else  {
				  BTNode zp=z.parent;
				  if(zp.right == z) {
					  zp.right=y;
					  y.parent=zp;
				  }else if(zp.left == z) {
					  zp.left=y;
					  y.parent=zp;
				  }
			  }  
				  y.left=z;
				  z.parent=y;
				  
				  z.right=lefty;
				  lefty.parent=z;
		  }
   }
   
   
   
   
   
   
   //find higher child
   public INode<T> higherChil(INode<T> parent){				//Return the higher child,used for find x y z
	   if(parent.element() == null) {
		   return null;
	   }
	   BTNode x=(AbstractBinaryTree<T>.BTNode) parent;
	   INode<T> higherChil;
	   if(height(x.left)>height(x.right)) {
		   higherChil=x.left;
	   }else {
		   higherChil=x.right;
	   }
	   	return higherChil;
   }
   
   
   
   public INode<T> nextLarge(INode<T> parent){					//Find the next larger node
	   
	   BTNode p=(AbstractBinaryTree<T>.BTNode) parent;
	   
	   if(p.right.element!=null) {
		   BTNode next=p.right;
		   while(next.left.element!=null) {
			   next=next.left;
		   }
		   return next;
	   }else {
		   return parent;
   			}
   }
   
   
  
   
   public int height(INode<T> x) {						   //Calculate height
	   BTNode n = (AbstractBinaryTree<T>.BTNode) x;
	  if(this.isExternal(x)) 
	  {
		  return 0;
	  }
	  else {
			 return 1+Math.max(height(n.left),height(n.right));
	  }
   }
   
   
   
   //return is the tree balanced
   public boolean isAboveBlance(INode<T> a)
   {
		  if(findUnbalanceNode(a) == null) 
		  {
			  return true;
		  }else {
			  return false;
		  }
   }
   
   	
	  //return unbalanced node z, the checking scope involve a itself and above nodes.Return null when all nodes are balance
	  public INode<T> findUnbalanceNode(INode<T> a)
	  {
		  BTNode x=(AbstractBinaryTree<T>.BTNode) a;
		  BTNode parent;
		  
		  
		  if(this.isRoot(x)) 
		  {
			  if(Math.abs(height(x.left)-height(x.right)) >= 2) {
				  return x;
			  }else {
				  return null;
			  }
			  
		  }
		  
		  
		  while(!this.isRoot(x))
		  {
			  parent=x.parent;
			  if(Math.abs(height(parent.left)-height(parent.right)) >= 2) {
				  return parent;
			  }
			  x=x.parent;
		  }
		  
		  return null;
	  }
	  
	  
	  
	  public int depth(INode<T> x) {
		 INode<T> i=x;
		 int n=0;
		 while(i != this.root) {
			 i=this.parent(i);
			 n++;
		 }
		 return n;
	  }
	  
	  
	  
	  public INode<T> findNode(T element)
	  {
		  return super.find(root, element);
	  }

		private void HExpendEternalNode(INode<T> i,T element) {
			HBTNode h=(AVLTree<T>.HBTNode) i;
			h.element=element;
			h.left=new HBTNode(null,h,null,null);
			h.right=new HBTNode(null,h,null,null);
			h.setHeight(1);
		}
		
		
		//Return t.element ==null when search reached to the external node
		//Return t.element != null when search reached to the exist node
		//Search starts with root
		
		
		private INode<T> avlFind(T element) {
			HBTNode t=(AVLTree<T>.HBTNode) this.root; 
			while(t.element != null) {
				if(t.element.compareTo(element)== 0) {
					return t;
				}else if(t.element.compareTo(element) == 1) {
					t=(AVLTree<T>.HBTNode) t.left;
				}else if(this.root.element.compareTo(element) == -1){
					t=(AVLTree<T>.HBTNode) t.right;
				}
			}
			
			return t;
		}
		
		public void infoPrint() {
			System.out.println("--------------------------------------------------");
			IIterator<INode<T>> i=this.nodes();
			while(i.hasNext()) {
				BTNode b=(AbstractBinaryTree<T>.BTNode) i.next();
				if(b.element!=null) {
					if(b.parent == null || b.left == null || b.right == null) {
						System.out.println(b.element+"  "+b.left+" "+b.right+" "+b.parent);
					}else {
						System.out.println(b.element+"  "+b.left.element+" "+b.right.element+" "+b.parent.element);
					}
					}
			}
			System.out.println("--------------------------------------------------");
			}

}
