// Class: Implementation of BST in A2
// Implement the following functions according to the specifications provided in Tree.java

public class BSTree extends Tree {

    private BSTree left, right;     // Children.
    private BSTree parent;          // Parent pointer.
        
    public BSTree(){  
        super();
        // This acts as a sentinel root node
        // How to identify a sentinel node: A node with parent == null is SENTINEL NODE
        // The actual tree starts from one of the child of the sentinel node!.
        // CONVENTION: Assume right child of the sentinel node holds the actual root! and left child will always be null.
    }    

    public BSTree(int address, int size, int key){
        super(address, size, key); 
    }
    public BSTree Insert(int address, int size, int key) 
    { 
//this.inorder();
if (this.parent==null && this.right==null){
//System.out.println("Inserting at sentinel...");
this.right= new BSTree(address,size,key);
this.right.parent=this;

return this.right;
}
if (this.parent==null){return this.right.Insert(address,size,key);}
else if (this.key>key){
if (this.left==null){this.left=new BSTree(address,size,key);this.left.parent=this;
return this.left;
}

return this.left.Insert(address,size,key);
}
else if (this.key<key){
    if (this.right==null){this.right=new BSTree(address,size,key);this.right.parent=this;
return this.right;
    }
return this.right.Insert(address,size,key);


}
else if(this.address>address){
    if (this.left==null){this.left=new BSTree(address,size,key);this.left.parent=this;return this.left;}
return this.left.Insert(address,size,key);
}
else{

    if (this.right==null){this.right=new BSTree(address,size,key);this.right.parent=this;return this.right;}
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
BSTree temp1=this;
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

BSTree successor=temp1.getNext();
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

return true;
}
        
        public BSTree Find(int key,boolean exact){
//this.inorder();
if (this.parent==null && this.right==null){

            //System.out.println("I myself is null");

return null;}
else{

return this.getRoot().Findauxs(key,exact);
}

}
  

public BSTree Findauxs(int key,boolean exact){

if (this.parent==null && this.right==null){return null;}

else if (exact){
BSTree temp=this;
while (temp!=null){
if (temp.key==key){
if (temp.left==null){return temp;}
BSTree result=temp.left.Findauxs(key,exact);
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
BSTree temp=this;
while (temp!=null){
if (temp.key>=key){if (temp.left==null){return temp;}
BSTree result=temp.left.Findauxs(key,exact);
if (result==null){return temp;}
return result;}
while (temp!=null && temp.key<key){
temp=temp.right;}


}
}
return null;

}









private BSTree getRoot(){
BSTree cur=this;
if (cur==null){return null;}
if(cur.parent==null){return cur.right;}
else{return cur.parent.getRoot();}
}


public BSTree getFirst()
    {
 if(this==null){return null;}
 BSTree root=this.getRoot();
if (root==null){return null;}
while (root.left!=null){
root=root.left;}
return root;       

    }

    public BSTree getNext()
    { 
// return the succesor of given node in binary search tree

if (this.right!=null){
BSTree temp=this.right;
while (temp.left!=null){temp=temp.left;}
return temp;
}


else{
    BSTree temp=this;
while (temp.parent!=null){

if (temp.parent.left==temp){return temp.parent;}
temp=temp.parent;
}

}



        return null;
    }

private void inorder(){

System.out.print("Inorder: ");
for (BSTree i=this.getFirst();i!=null;i=i.getNext()){

System.out.print("(");

System.out.print(i.address);
System.out.print(",");

System.out.print(i.size);
System.out.print(",");

System.out.print(i.key);
System.out.print(",");

System.out.print(") -> ");
}


System.out.println(" ");

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


        BSTree root=this.getRoot();
//isBST also checks whether there is a cycle 
if (root==null){return true;}
if (!root.isBST()){return false;}
if (!root.isLinked()){return false;}
if (!root.check()){return false;}




        return true;
    }
}




