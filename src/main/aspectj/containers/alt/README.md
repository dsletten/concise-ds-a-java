
## Features of Inheritance
Following is a(n incomplete) list of features that Inheritance provides. In the following sections, I'll detail which ones were used
in this project and how we can migrate them to a Multiple Inheritance model.

* Type Inheritance
* Stateless Implementation Inheritance
* State Inheritance
* Stateful Implementation Inheritance
* Template Method Pattern Implementation


### Type Inheritance
When you inherit from a supertype, you inherit all of its type information. The type you are defining is an instance of any direct parent types... as well as all ancestor types.

In Java, if you want "Multiple Type Inheritance", the solution is easy. Use Interfaces to define Types. In other words, separate the Type hierarchy from the Class hierarchy.

Since Classes can implement multiple Interfaces, Types can be multiply-inherited.

I think this is where the "Twins" pattern falls down. Based on my reading of the pattern: upon implementation of the pattern, you have two Classes, each which have methods of the same signature. But any two such methods (of the same signature -- one for each class) appear each to belong to their own Type.

### Stateless Implementation Inheritance
I'll define "Stateless Implementation" as a method implementation that requires no knowledge of the instance variables of the ("this") instance on which it is executing. It relies ONLY on the Type information for the instance on which it is executing.

Implementations of this type can be defined as default methods on Interfaces.

Since Classes can implement multiple Interfaces, Stateless Implementations can be multiply-inherited.

### State Inheritance
By "State", I simply mean instance variables defined in Class specifications. State is inherited by extending a Class.

Since Classes can only extend one Class (and its ancestors), you cannot inherit State from multiple Classes (ancestries).

### Stateful Implementation Inheritance
I'll define "Stateful Implementation" as a method implementation that DOES require knowledge of instance variables of the instance on which it is executing.

Since Stateful Implementations depend on (inherited) State, and since Classes can only extend one Class (and its ancestors): you cannot inherit Stateful Implementations from multiple Classes (ancestries).

### Template Method Pattern Implementation
Inheritance provides a (convenient? Well, "convenient" in Java, at least) way to implement the Template Method pattern. As part of the public interface of a Class, you can define a final method that delegates to an abstract method -- thereby controlling the public interface and delegating only a portion of its implementation to subclasses.

The cost of doing so is that your final method has the signature that is "nice" (ie: its signature is the one you actually want to define), and the abstract method you force subclasses to override is slightly less meaningful (ie: "do"<Whatever>()). You also need to create names for things that don't really matter.


## Notes
I started by creating Interfaces to separate the Type Hierarchy from the Class Hierarchy.

Then, I noticed that MOST of the implementations were Stateless Implementations, so I defined them as default methods on their respective interfaces.

Next, I addressed the Stateful Implementations -- which were all methods on ArrayRingBuffer. I made ArrayRingBuffer a concrete class and implemented them.

Next, I implemented ArrayRingBufferDeque, which desires to share some implementations with ArrayRingBuffer. Instead of sharing the implementations via Inheritance, I did so via Composition. I just created an instance of ArrayRingBuffer to which ArrayRingBufferDeque intances could delegate. This was made easier by a feature(?) of Java that I always forget: in addition to being visible to subclasses, "protected" fields are visible to classes in the same package.

Note that since I extracted all Type information to Interfaces, however, ArrayRingBufferDeque extends NO Classes. I could probably have just had it extend ArrayRingBuffer, but that would have been less instructive.

Also note: once I got into this and realized that most of the shared implementations were stateless, the problem was much easier. In fact, the only Stateful Implementations were on leaf nodes of the inheritance hierarchy. The problem would have been much more interesting if "interior" Abstract Classes had had a Stateful Implementations that needed to be shared with a single subclass.

I gave some thought as to how this would be accomplished. If the abstract classes represented Types, those types could be extracted into two Interfaces, say "TypeA" and "TypeB". But, since the classes from which they were extracted were Abstract, we wouldn't expect every method of these Types to have implementations we want to share. If we wish to share implementations via Composition, we can't put those implementations in Abstract Classes -- because we can't create instances of Abstract Classes.

So, what is the solution? I think it is to create two new "implementation detail" interfaces: PartialTypeA and PartialTypeB, containing the implementations we want to share. Then create two "implementation detail" Classes, one for each of the interfaces -- which implement them. Then, instances of these Classes can be used inside of Classes which wish to implement the TypeA and TypeB interfaces with the shared Stateful Implementations.