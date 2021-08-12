// Implements Dictionary using Doubly Linked List (DLL)
// Implement the following functions using the specifications provided in the class List

public class A1List extends List {

    private A1List  next; // Next Node
    private A1List prev;  // Previous Node 

    public A1List(int address, int size, int key) { 
        super(address, size, key);
    }
    
    public A1List(){
        super(-1,-1,-1);
        // This acts as a head Sentinel

        A1List tailSentinel = new A1List(-1,-1,-1); // Intiate the tail sentinel
        
        this.next = tailSentinel;
        tailSentinel.prev = this;
    }

    public A1List Insert(int address, int size, int key)
    {
A1List current=new A1List(address,size,key);
A1List temp=this.next;
this.next=current;
current.prev=this;
current.next=temp;
temp.prev=current;
return current;
    }
private void del(A1List d){
    A1List previous=d.prev;
    A1List successor=d.next;
    previous.next=successor;
    successor.prev=previous;
    d=null;
 
 }
public boolean Delete(Dictionary d) 
    {


A1List cur=this;
while (cur.next!=null){
    if (cur.key==d.key && cur.address==d.address && cur.size==d.size){
        del(cur);
        return true;
    }
    cur=cur.next;
}

A1List cur2=this;
while (cur2.prev!=null){
    if (cur2.key==d.key && cur2.address==d.address && cur2.size==d.size){
        del(cur2);
        return true;
    }
    cur2=cur2.prev;
}
        return false;
    }

public A1List Find(int k, boolean exact)
    { 

if (exact){

A1List cur=this.getFirst();

if (cur==null){return null;}

if (cur.key==k){return cur;}

while (cur.next!=null){
    if (cur.key==k ){
        
        return cur;
    }
    cur=cur.next;
}
}




else{
A1List cur=this.getFirst();
if (cur==null){
//System.out.println("This block is null bro!");
return null;}
while (cur.next!=null ){
    if (cur.key>=k ){
        
        return cur;
    }
    cur=cur.next;
}
}
        return null;    }

   public A1List getFirst()
    {
        A1List cur=this;
//System.out.println(cur.key);
        if (cur.key==-1 && cur.next==null){
            return null;
        }
else  if (cur.key==-1 && cur.next.key==-1){
            return null;
        }
else  if (cur.key==-1){

            return cur.next;
        }
        else{
            while (cur.prev.key!=-1){cur=cur.prev;}
        return cur;
        }
    }
    
    public A1List getNext() 
    {
if (this.next.key!=-1){
        return this.next;}
else{return null;}
    }





private boolean iscyclic(){
//detecting loop using floyd's algorithm
A1List x=this.getFirst();
A1List y=this.getFirst();
while (x!=null &y!=null){
x=x.next;
if (y.next==null){return false;}
y=y.next.next

if (x==y){return true;}


}

return false;

}




    public boolean sanity()
    {

if (this.iscyclic()){return false;}
A1List cur1=this;
//Both while loops check for a loop as well as check other invariants metioned in the question. 
while (cur1.prev!=null){
if (cur1.key==-1){return false;}
if (cur1.prev.next!=cur1){return false;}
if (cur1.next.prev!=cur1){return false;}

cur1=cur1.prev;
}
if (cur1.key!=-1){return false;}


A1List cur2=this.next;
while(cur2.next!=null){
if (cur2.key==-1){return false;}

if (cur2.prev.next!=cur2){return false;}
if (cur2.next.prev!=cur2){return false;}
cur2=cur2.next;
}
if (cur2.key!=-1){return false;}










 return true;






    }

}


