package Customers;

// Based on Observer Design Pattern example from www.tutorialspoint.com
public abstract class Observer {
   protected Subject subject;
   public abstract void update();
}