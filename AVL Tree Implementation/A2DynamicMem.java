    // Class: A2DynamicMem
// Implements Degragment in A2. No other changes should be needed for other functions.

public class A2DynamicMem extends A1DynamicMem {
      
    public A2DynamicMem() {  super(); }

    public A2DynamicMem(int size) { super(size); }

    public A2DynamicMem(int size, int dict_type) { super(size, dict_type); }

    // In A2, you need to test your implementation using BSTrees and AVLTrees. 
    // No changes should be required in the A1DynamicMem functions. 
    // They should work seamlessly with the newly supplied implementation of BSTrees and AVLTrees
    // For A2, implement the Defragment function for the class A2DynamicMem and test using BSTrees and AVLTrees. 

    public void Defragment() {
if (type==2){
BSTree aux=new BSTree();
for (Dictionary i=freeBlk.getFirst();i!=null;i=i.getNext()){
aux.Insert(i.address,i.size,i.address);
}

BSTree x=aux.getFirst();
BSTree suc;
if (x==null){suc=null;}
else{suc=x.getNext();
}
while (suc!=null){


if (suc==null){break;}
if (x.address+x.size==suc.address){

int sz=x.size;
int ads=x.address;
Dictionary a=new BSTree(x.address,x.size,x.size);

Dictionary b=new BSTree(suc.address,suc.size,suc.size);

x=suc;

suc.size+=sz;
suc.address=ads;
suc.key=suc.address;


freeBlk.Delete(a);
freeBlk.Delete(b);

freeBlk.Insert(ads,suc.size,suc.size);
suc=suc.getNext();
continue;
}
x=suc;
suc=suc.getNext();
}
for (BSTree i=aux.getFirst();i!=null;i=i.getNext()){
//addrsss,size , key
aux.Delete(freeBlk.Find(i.size,true));
}

        return ;
    }

else if (type==3){
    AVLTree aux=new AVLTree();
for (Dictionary i=freeBlk.getFirst();i!=null;i=i.getNext()){
aux.Insert(i.address,i.size,i.address);
}

AVLTree x=aux.getFirst();
AVLTree suc;
if (x==null){suc=null;}
else{suc=x.getNext();
}
while (suc!=null){


if (suc==null){break;}
if (x.address+x.size==suc.address){

int sz=x.size;
int ads=x.address;
Dictionary a=new AVLTree(x.address,x.size,x.size);

Dictionary b=new AVLTree(suc.address,suc.size,suc.size);

x=suc;

suc.size+=sz;
suc.address=ads;
suc.key=suc.address;


freeBlk.Delete(a);
freeBlk.Delete(b);

freeBlk.Insert(ads,suc.size,suc.size);
suc=suc.getNext();
continue;
}
x=suc;
suc=suc.getNext();
}
for (AVLTree i=aux.getFirst();i!=null;i=i.getNext()){
//addrsss,size , key
aux.Delete(freeBlk.Find(i.size,true));
}

        return ;}

}
public int Allocate(int blockSize) {
// System.out.print("Free ");
// freeBlk.inorder();
// System.out.print("Allocate ");
// allocBlk.inorder();

if (blockSize<1){return -1;}      
Dictionary d = freeBlk.Find(blockSize,false);


if (d==null){

//System.out.print("Final count : ");
//System.out.println(count(allocBlk));
//System.out.println("egh Couldn't found");
return -1;}

else{
//System.out.print("Find : ");
//System.out.print(d.address);
//System.out.print(" ");
//System.out.print(d.size);
//System.out.print(" ");
//System.out.println(d.key);
//System.out.print("Final count : ");

if (d.size==blockSize){
 int ads=d.address;
//System.out.print("ALLocate ");
allocBlk.Insert(d.address,d.size,d.address);

//System.out.print("Free ");
freeBlk.Delete(d);
//System.out.println(allocBlk.address);
//System.out.print("Free after ");
//freeBlk.inorder();
//System.out.print("Allocate after ");
//allocBlk.inorder();
return ads;
}
else{
  
//System.out.println("egh f insertion");

//System.out.print("Free ");
freeBlk.Insert(d.address+blockSize,d.size-blockSize,d.size-blockSize);
int ads=d.address;
//System.out.print("d.address ");
//System.out.println(d.address);

//System.out.print("Free ");
freeBlk.Delete(d);
//System.out.print("d.address ");
//System.out.println(d.address);

//System.out.println("egh a insertion");

//System.out.print("ALLocate ");
allocBlk.Insert(ads,blockSize,ads);
//System.out.println(allocBlk.address);
//System.out.println("sending address ");
//System.out.print("Free after ");
//freeBlk.inorder();
//System.out.print("Allocate after ");
//allocBlk.inorder();
return ads;
}



//System.out.println(count(allocBlk));


}    


    } 
    
    public int Free(int startAddr) {
//System.out.print("Free  ");
//freeBlk.inorder();
//System.out.print("Allocate ");
//allocBlk.inorder();

       Dictionary cur=allocBlk.Find(startAddr,true);
if (cur==null || startAddr<0){
//System.out.println("nulls nulls ");
  //  System.out.print("Free after ");
//freeBlk.inorder();
//System.out.print("Allocate after ");
//allocBlk.inorder();
return -1;}
else{



freeBlk.Insert(cur.address,cur.size,cur.size);
allocBlk.Delete(cur);

//System.out.print("Free after ");
//freeBlk.inorder();
//System.out.print("Allocate after ");
//allocBlk.inorder();
 return 0;
    }
}
 
}