// Class: Implementation of BST in A2
// Implement the following functions according to the specifications provided in Tree.java

public class AVLTree extends BSTree {

    private AVLTree left, right;     // Children.
    private AVLTree parent;          // Parent pointer.
        private int height;     
    public AVLTree(){  
        super();
        // This acts as a sentinel root node
        // How to identify a sentinel node: A node with parent == null is SENTINEL NODE
        // The actual tree starts from one of the child of the sentinel node!.
        // CONVENTION: Assume right child of the sentinel node holds the actual root! and left child will always be null.
    }    

    public AVLTree(int address, int size, int key){
        super(address, size, key); 
    }
    public AVLTree Insert(int address, int size, int key) 
    { 
//this.inorder();
if (this.parent==null && this.right==null){
//System.out.println("Inserting at sentinel...");
this.right= new AVLTree(address,size,key);
this.right.parent=this;

        update_height(this);
return this.right;
}
if (this.parent==null){return this.right.Insert(address,size,key);}
else if (this.key>key){
if (this.left==null){this.left=new AVLTree(address,size,key);this.left.parent=this;
   balance(this);

        update_height(this);
return this.left;
}

return this.left.Insert(address,size,key);
}
else if (this.key<key){
    if (this.right==null){this.right=new AVLTree(address,size,key);this.right.parent=this;    
   balance(this);

        update_height(this);
return this.right;

    }
return this.right.Insert(address,size,key);


}
else if(this.address>address){
    if (this.left==null){this.left=new AVLTree(address,size,key);this.left.parent=this;    
        balance(this);

        update_height(this);
        return this.left;}
return this.left.Insert(address,size,key);
}
else{

    if (this.right==null){this.right=new AVLTree(address,size,key);this.right.parent=this;   
     balance(this);

 update_height(this);
     return this.right;}
return this.right.Insert(address,size,key);
}

    }

    public boolean Delete(Dictionary d)
    { 
if (d==null){return false;}
//this.inorder();
int address=d.address;
int key=d.key;
int size=d.size;
AVLTree temp1=this;
if (temp1.parent==null){temp1=temp1.right;}
if (temp1==null){return false;}
else if (temp1.key>key){

 if (temp1.left==null){return false;}    
return temp1.left.Delete(d);
}
else if (temp1.key<key){

 if (temp1.right==null){return false;}
return temp1.right.Delete(d);
}
else if(temp1.address>address){

 if (temp1.left==null){return false;}
return temp1.left.Delete(d);
}
else if (temp1.address<address){

 if (temp1.right==null){return false;}
return temp1.right.Delete(d);
}

else{
//Deletion of BST node
if (temp1.parent==null){return false;}
if (temp1.left==null && temp1.right==null){
if (temp1.parent.key==-1){//root deletion case
temp1.parent.right=null;
//temp1=null;
}
else{
if (temp1.parent.right==temp1){temp1.parent.right=null;}
else{temp1.parent.left=null;}
//temp1=null;
}

}

else if (temp1.left==null){
if (temp1.parent.key==-1){
temp1.parent.right=temp1.right;
temp1.right.parent=temp1.parent;
//temp1=null;

}
else{
if (temp1.parent.left==temp1){temp1.parent.left=temp1.right;
temp1.right.parent=temp1.parent;
}
else{
temp1.parent.right=temp1.right;
temp1.right.parent=temp1.parent;
}

}
}
else if (temp1.right==null){
if (temp1.parent.key==-1){

temp1.parent.right=temp1.left;
temp1.left.parent=temp1.parent;
//temp1=null;

}
else{
if (temp1.parent.left==temp1){temp1.parent.left=temp1.left;
temp1.left.parent=temp1.parent;
}
else{
temp1.parent.right=temp1.left;
temp1.left.parent=temp1.parent;
}
}

}


else{


//both non=null

AVLTree successor=temp1.getNext();
temp1.key=successor.key;
temp1.address=successor.address;
temp1.size=successor.size;

if (successor.right!=null){
if (successor.parent.key==-1){
successor.parent.right=successor.right;
successor.right.parent=successor.parent;
//temp1=null;

}
else{
if (successor.parent.left==successor){successor.parent.left=successor.right;
successor.right.parent=successor.parent;
}
else{
successor.parent.right=successor.right;
successor.right.parent=successor.parent;
}

}
}
else{

if (successor.parent.key==-1){//root deletion case
successor.parent.right=null;
//temp1=null;
}
else{
if (successor.parent.right==successor){successor.parent.right=null;}
else{successor.parent.left=null;}
//temp1=null;
}


}


}

}
temp1=balance(temp1);
update_height(temp1);
return true;
}
        
        public AVLTree Find(int key,boolean exact){
//this.inorder();
if (this.parent==null && this.right==null){

            //System.out.println("I myself is null");

return null;}
else{

return this.getRoot().Findauxs(key,exact);
}

}
  
private void update_height(AVLTree x){
while (x.parent!=null || x.parent.key!=-1){
    x.height=height(x);
    x=x.parent;
}




}
public AVLTree Findauxs(int key,boolean exact){

if (this.parent==null && this.right==null){return null;}

else if (exact){
AVLTree temp=this;
while (temp!=null){
if (temp.key==key){
if (temp.left==null){return temp;}
AVLTree result=temp.left.Findauxs(key,exact);
if (result==null){return temp;}
return result;
}

while (temp!=null && temp.key<key){
temp=temp.right;}
while (temp!=null && temp.key>key){
temp=temp.left;}


}


}

else{
AVLTree temp=this;
while (temp!=null){
if (temp.key>=key){if (temp.left==null){return temp;}
AVLTree result=temp.left.Findauxs(key,exact);
if (result==null){return temp;}
return result;}
while (temp!=null && temp.key<key){
temp=temp.right;}


}
}
return null;

}









private AVLTree getRoot(){
AVLTree cur=this;
if (cur==null){return null;}
if(cur.parent==null){return cur.right;}
else{return cur.parent.getRoot();}
}


public AVLTree getFirst()
    {
 if(this==null){return null;}
 AVLTree root=this.getRoot();
if (root==null){return null;}
while (root.left!=null){
root=root.left;}
return root;       

    }

    public AVLTree getNext()
    { 
// return the succesor of given node in binary search tree

if (this.right!=null){
AVLTree temp=this.right;
while (temp.left!=null){temp=temp.left;}
return temp;
}


else{
    AVLTree temp=this;
while (temp.parent!=null){

if (temp.parent.left==temp){return temp.parent;}
temp=temp.parent;
}

}



        return null;
    }


private AVLTree rrrotate(AVLTree x){
//Assuming this node x will have a left and a left.left child
// this.inorder();
// System.out.println("youji");

if (x==null){return x;}
if (x.left==null){return x;}
AVLTree temp;
if (x.left.right==null){
    AVLTree par=x.parent;
     temp=x.left;
     x.left=null;
if (par==null){return this;}
if (par!=null){
temp.parent=par;
if (par.left==x){par.left=temp;}
else{par.right=temp;}
//temp.parent=par;
}
   
    temp.right=x;
    x.parent=temp;

}

else{

AVLTree lr=x.left.right; 
temp=x.left;

 AVLTree par=x.parent;
    // AVLTree temp=x.left;
 if (par==null){return this;}
if (par!=null){
temp.parent=par;
if (par.left==x){par.left=temp;}
else{par.right=temp;}
//temp.parent=par;
}
    temp.right=x;
    x.parent=temp;
    x.left=lr;
    lr.parent=x;
}
// this.inorder();

return temp;

}
 
private AVLTree llrotate(AVLTree x){
    // this.inorder();
// System.out.println("namaste/welcome ji");

//Assuming this node x will have a left and a left.left child
    if (x==null){return x;}
    if (x.right==null){return x;}

AVLTree temp;
if (x.right.left==null){
 temp=x.right;
    x.right=null;
 AVLTree par=x.parent;
     //AVLTree temp=x.left;
if (par!=null){
temp.parent=par;
if (par.left==x){par.left=temp;}
else{par.right=temp;}
//temp.parent=par;
}


    temp.left=x;
    x.parent=temp;

}

else{

AVLTree lr=x.right.left;
temp=x.right;
 AVLTree par=x.parent;
  //x.right=null;


     //AVLTree temp=x.left;
if (par!=null){
temp.parent=par;
if (par.left==x){par.left=temp;}
else{par.right=temp;}
//temp.parent=par;
}
    temp.left=x;
    x.parent=temp;
    x.right=lr;
    lr.parent=x;

}
// this.inorder();

return temp;

}


private AVLTree lrrotate(AVLTree x){
// this.inorder();
// System.out.println("namaste/welcome");
 AVLTree temp= llrotate(x.left);
 temp.parent=x;
 x.left=temp;
 // this.inorder();

 return rrrotate(x);  
}


private AVLTree rlrotate(AVLTree x){
// this.inorder();
// System.out.println("helloji");

    AVLTree temp=rrrotate(x.right);
    x.right=temp;
    temp.parent=x;
// this.inorder();

    return llrotate(x);
}

private int max(int a,int b){
    if (a>=b){return a;}
    return b;
}
private int height(AVLTree x){

    if (x==null){return -1;}
    if (x.parent==null){return 0;}
    else if (x.left==null && x.right==null){return 0;}
    
else if (x.left==null){return 1+height(x.right);}
else if (x.right==null){return 1+height(x.left);}
    else{return 1+max(height(x.left),height(x.right));}

}
private int abs(int x){
if (x>=0){return x;}
return -x;

}
private boolean isBalanced(AVLTree x){
    if(x.parent==null){return true;}
    if (x==null){return true;}

else if (abs(height(x.left)-height(x.right))>1){
//     System.out.println("I came ");
//       System.out.println("my key ");
//       System.out.println(x.key);
//         System.out.println(x.size);
//         System.out.println(x.address);
// System.out.println(height(x.left));
// System.out.println(height(x.right));

// if (x.left==null){System.out.println("left null");}
// else{System.out.println(x.left.size);}

// if (x.right==null){System.out.println("right null");}
// else{System.out.println(x.right.size);}


    // System.out.println("false");

 return false;}
return true;
}


private AVLTree balance(AVLTree x){
    if (x.parent==null){return x;}
    if (x.key==-1){return x;}
if (x==null){return x;}
AVLTree parent=x.parent;
if (isBalanced(x)){
// System.out.println("Chet");
   return balance(x.parent)
    ;}
    // System.out.println("ke.0y");
    // System.out.println(x.key);
// System.out.println("Checkpoint");
int hleft=height(x.left);
// System.out.println("left:h: ");
// System.out.println(hleft);
int hright=height(x.right);
// System.out.println("right:h: ");
// System.out.println(hright);
if (hleft>=hright){
    // System.out.println("Checkpoint");
int hll=height(x.left.left);
// System.out.println("Checkpoint");
int hlr=height(x.left.right);
// System.out.println("Checkpoint");

if (hll>hlr){
// System.out.println("Crr");
    rrrotate(x);}
else{AVLTree don= lrrotate(x);

}
// System.out.println("Checkpoint");
// doubtfull step
// this.inorder();
}

else{

int hrl=height(x.right.left);
int hrr=height(x.right.right);
if (hrl>hrr){
// this.inorder();

    rlrotate(x);
// this.inorder();
}
else{
    // this.inorder();
llrotate(x);
// this.inorder();
}



}
// System.out.println("Checkpoint");
// this.inorder();

return balance(x.parent);
// System.out.println("Checkpoint");

}

private boolean treebalance(AVLTree x){
if (x==null){return true;}
if (!isBalanced(x)){return false;}
else{
return (treebalance(x.left)&&treebalance(x.right));

}

}


    private boolean isBST(){
    if (this==null){return true;}
if (this.left==null && this.right==null){return true;}
else if (this.left==null){
if (this.right.key<this.key){return false;}
if (this.key==this.right.key && this.address>this.right.address){return false;}
return this.right.isBST();


}
else if (this.right==null){
if (this.left.key>this.key){return false;}
if (this.key==this.left.key && this.address<this.left.address){return false;}
return this.left.isBST();


}
else{


if (this.right.key<this.key){return false;}
if (this.left.key>this.key){return false;}


if (this.key==this.left.key && this.address<this.left.address){return false;}
if (this.key==this.right.key && this.address>this.right.address){return false;}
return (this.left.isBST()) &&(this.right.isBST());}
}

private boolean isLinked(){
if (this.left==null && this.right==null){return true;}
else if (this.left==null){
    if (this.right.parent==this){return this.right.isLinked();}
    else{return false;}

}

else if (this.right==null){
    if (this.left.parent==this){return this.left.isLinked();}
    else{return false;}

}


else{
return (this.left.isLinked())&&(this.right.isLinked());

}

}

private boolean check(){

    if (this.getRoot().parent!=null && this.getRoot().parent.key==-1 && this.getRoot().parent.parent==null){return true;}
    return false;
}


    public boolean sanity()
    {


        AVLTree root=this.getRoot();
//isBST also checks whether there is a cycle 

if (root==null){return true;}
if (!treebalance(root)){return false;}

if (!root.isBST()){return false;}
if (!root.isLinked()){return false;}
if (!root.check()){return false;}




        return true;
    }

    
    
}




