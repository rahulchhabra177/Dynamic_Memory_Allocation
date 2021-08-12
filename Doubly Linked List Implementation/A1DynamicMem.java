// Class: A1DynamicMem
// Implements DynamicMem
// Does not implement defragment (which is for A2).

public class A1DynamicMem extends DynamicMem {
      
    public A1DynamicMem() {
        super();
    }

    public A1DynamicMem(int size) {
        super(size);
    }

    public A1DynamicMem(int size, int dict_type) {
        super(size, dict_type);
    }

//public int count(Dictionary d)
//{
//int cnt=0;
//Dictionary k=d.getFirst();
//for(Dictionary i=d.getFirst();i!=null;i=i.getNext()){cnt+=1;
//System.out.print(i.address);
//System.out.print(" : ");
//System.out.print(i.size);
//System.out.print(" : ");
//}
//return cnt;
//}

    public void Defragment() 
{
        return ;
    }

    // In A1, you need to implement the Allocate and Free functions for the class A1DynamicMem
    // Test your memory allocator thoroughly using Doubly Linked lists only (A1List.java).

    public int Allocate(int blockSize) {
//System.out.print("Initial count : ");
//System.out.println(this.count(allocBlk));
if (blockSize<1){return -1;}      
Dictionary d = freeBlk.Find(blockSize,false);
if (d==null){

//System.out.print("Final count : ");
//System.out.println(count(allocBlk));
//System.out.println("egh Couldn't found");
return -1;}

else{
if (d.size==blockSize){
 
allocBlk.Insert(d.address,d.size,d.address);
freeBlk.Delete(d);
//System.out.println(allocBlk.address);

}
else{
  
//System.out.println("egh f insertion");

  freeBlk.Insert(d.address+blockSize,d.size-blockSize,d.size-blockSize);
freeBlk.Delete(d);
//System.out.println("egh a insertion");
allocBlk.Insert(d.address,blockSize,d.address);
//System.out.println(allocBlk.address);
}

//System.out.print("Final count : ");

//System.out.println(count(allocBlk));

return d.address;
}    


    } 
    
    public int Free(int startAddr) {
       Dictionary cur=allocBlk.Find(startAddr,true);
if (cur==null){
//System.out.println("nulls nulls ");
return -1;}
else{
allocBlk.Delete(cur);
freeBlk.Insert(cur.address,cur.size,cur.size);


 return 0;
    }
}
}