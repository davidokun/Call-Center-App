# Call-Center-App
A Sample application for a Call Center.

# Index
1. [Design](#1-design)  
   1.1 [Classes](#11-classes)  
   1.2 [Collaboration](#12-collaboration)  
2. [Extras](#2-extras)  
   2.1 [No Operator is available to a call](#21-no-operator-is-available-to-a-call)  
   2.2 [More than 10 call at the same time](#22-more-than-10-call-at-the-same-time)

---

# 1. Design
Base on the requirements of this application, a proposal desing has been formulated. Please find down below a desing diagrams for the appplication.

### 1.1 Classes

![Alt text](docs/Classes-Diagram.png?raw=true)

### 1.2 Collaboration

![Alt text](docs/Collaboration-Diagram.png?raw=true)

---

# 2. Extras

There are two questions about what happens with the calls. These are the solutions:

### 2.1 No Operator is available to a call.

`Q`: What happned in the application where is no operator free to attend the call?
`A`: The application uses an implemnetation of a Queue, ConcurrentLinkedQueue, so all calls that came in into the system are put in there waiting for an operator to be availabe to attend the call. Calls never get lost if there is no operator free.

### 2.2 More than 10 call at the same time.

`Q`: What happend to a call when there is more that 10 consurrent calls?
`A`: Same as before. Because of the implementation of a Queue, calls are just `queue` until an operator is available to attend the call.

---

# 3. How to run the app

### 3.1 Running locallly

1. Clone repository to a local folder in your machine. `git clone https://github.com/davidokun/Call-Center-App.git`


