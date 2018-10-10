fun main(args: Array<String>) {
    Class1().callClass2()
}

interface ValueInterface {
    fun print()
    fun printMessage()
    fun printMessageLine()
}

/**
 * In delegation we can pass context of interface and implement only methods which are required . In class 2 only print() is over ridden
 * so, calling print() from class2 obj will print2 from class2 and other interface methods will be called from class1
 * */
open class Class1 : ValueInterface {

    open fun callClass2() {
        Class2(this).printClass1()

    }

    override fun print() {
        println("override print() Class1")
    }

    override fun printMessage() {
        println("override printMessage() Class1")
    }

    override fun printMessageLine() {
        println("override printMessageLine() Class1")
    }
}

class Class2(private var interfaceVal: ValueInterface) : ValueInterface by interfaceVal {
    fun printClass1() {
        print()
        printMessage()
        printMessageLine()
    }

    override fun print() {
        println("override print() Class2 ")
    }

}


/**
 * If there’s a class B which implements interface A, you can delegate all methods of b to class C by interface A. It’s called Class Delegation.
 * Internally, b will be stored as the private field in C, and all implemented methods of B will be generated as static methods which refer to that private field b in C
 * */

/*Class delegation under the hood
How this work? Here’s a simple code:

interface Base {
    fun printX()
}

class BaseImpl(val x: Int) : Base {
    override fun printX() { print(x) }
}
val baseImpl = BaseImpl(10)
class Derived(baseImpl: Base) : Base by baseImpl
Above code will be generated like the following code in Java:

public interface Base {
   void printX();
}
public final class BaseImpl implements Base {
   private final int x;
   public void printX() {
      int var1 = this.x;
      System.out.print(var1);
   }
   // ...
}
public final class Derived implements Base {
   // $FF: synthetic field
   private final Base $$delegate_0;
   public Derived(@NotNull Base baseImpl) {
      Intrinsics.checkParameterIsNotNull(baseImpl, "baseImpl");
      super();
      this.$$delegate_0 = baseImpl;
   }
   public void printX() {
      this.$$delegate_0.printX();
   }
}
As you can see, $$delegate_0 is generated to refer to the origin instance of Base, and printX() is also generated to call printX() of $$delegate_0. So, you can call printX() method without the reference by wrapped method in class Derived.
*/