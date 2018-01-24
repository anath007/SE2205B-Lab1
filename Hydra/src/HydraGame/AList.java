/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HydraGame;

/**
 *
 * @author Abdelkader
 */
import java.util.Arrays;
public class AList<T> implements ListInterface<T> {
    private T[] list;   // Array of list entries; ignore list[0]
private int numberOfEntries;
private boolean initialized = false;
private static final int DEFAULT_CAPACITY = 25;
private static final int MAX_CAPACITY = 10000;
public AList() {
   this(DEFAULT_CAPACITY);
} // end default constructor
public AList(int initialCapacity) {
   if (initialCapacity < DEFAULT_CAPACITY) // Is initialCapacity too small?
initialCapacity = DEFAULT_CAPACITY;
else // Is initialCapacity too big?
  checkCapacity(initialCapacity);
T[] tempList = (T[])new Object[initialCapacity + 1];
list = tempList;
numberOfEntries = 0;
initialized = true;

    
}

private void checkCapacity(int capacity) {
  if (capacity > MAX_CAPACITY) 
      throw new IllegalStateException ("Attempt to create a list whose capacity exceeds allowd maximum");
     
   }

public void add (T newEntry)
{
    checkInitialization ();
    list[numberOfEntries+1]=newEntry;
    numberOfEntries++;
    ensureCapacity();
}

private void checkInitialization()
{
    if(!initialized)
        throw new SecurityException ("AList object is not initialized properly.");
}

private void ensureCapacity() {
  int capacity = list.length - 1;
  if (numberOfEntries == capacity) {
     int newCapacity = 2 * capacity;
     //Is capacity too big?
     checkCapacity(newCapacity);
     list = Arrays.copyOf(list, newCapacity + 1);
  } // end if
}

public void add(int newPosition, T newEntry) 
{
   checkInitialization();
   if ((newPosition >= 1) && (newPosition <= numberOfEntries + 1)) {
if (newPosition <= numberOfEntries)
   makeRoom(newPosition);
list[newPosition] = newEntry;
numberOfEntries++;
 ensureCapacity();
   }
   else 
       throw new IndexOutOfBoundsException("Given position of add's new entry is out of bounds.");

}

private void makeRoom (int newPosition)
{
    assert (newPosition >=1) && (newPosition <=numberOfEntries+1);
    
    int newIndex= newPosition;
    int lastIndex= numberOfEntries;
    
    for (int index= lastIndex; index>=newIndex; index--)
        list[index+1]=list[index];
}

public T remove (int givenPosition)
{
    checkInitialization();
    if ((givenPosition>=1)&&(givenPosition<=numberOfEntries))
    {
        assert !isEmpty();
        T result =list[givenPosition];
        
        if (givenPosition<numberOfEntries)
            removeGap(givenPosition);
        
        numberOfEntries--;
        return result;
    }
    else
        throw new IndexOutOfBoundsException("Illegal position given to remove operation.");
}

private void removeGap(int givenPosition)
{
    assert (givenPosition>=1)&&(givenPosition<numberOfEntries);
    int removeIndex = givenPosition;
    int lastIndex= numberOfEntries;
    
    for (int index=removeIndex;index<lastIndex; index++)
        list[index]=list[index+1];
}

public void clear()
{
    checkInitialization();;
    for (int index=1;index<=numberOfEntries;index++)
        list[index]=null;
    numberOfEntries=0;
}

public T replace(int givenPosition, T newEntry)
{
    checkInitialization();
    if ((givenPosition>=1)&&(givenPosition<=numberOfEntries))
    {
        assert !isEmpty();
        T orginalEntry=list[givenPosition];
        list[givenPosition]=newEntry;
        return orginalEntry;
    }
    else
        throw new IndexOutOfBoundsException("Illegal position given to replace operation.");
}

public T getEntry (int givenPosition)
{
    checkInitialization();
    if ((givenPosition>=1)&&(givenPosition<=numberOfEntries))
    {
        assert !isEmpty();
        return list[givenPosition];
    }
    else
        throw new IndexOutOfBoundsException("Illegal position given to getEntry operation.");
}

public int getLength()
{
    return numberOfEntries;
}

public boolean isEmpty()
{
    return numberOfEntries==0;
}

public T[] toArray()
{
    checkInitialization();
    @SuppressWarnings ("unchecked")
    T[] result=(T[])new Object[numberOfEntries];
    
    for (int index=0; index<numberOfEntries;index++)
    {
        result[index]=list[index+1];
    }
    
    return result;
}

public boolean contains (T anEntry)
{
    checkInitialization();
    boolean found =false;
    int index=1;
    while (!found &&(index<=numberOfEntries))
    {
        if (anEntry.equals(list[index]))
            found=true;
        index++;
    }
    
    return found;
}

@Override
public int getPosition(T anEntry){
    checkInitialization();
    boolean found =false;
    int index=1;
    while (!found &&(index<=numberOfEntries))
    {
        if (anEntry.equals(list[index]))
            found=true;
        else
        index++;
    }
    
    return index;
    
}

}


    

