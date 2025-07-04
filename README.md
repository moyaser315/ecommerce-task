# ecommerce-task
Full Stack Development Internship Challenge for Fawry Rise program:

# **Important**

`main` branch is sticking to the general principles of OOP.

`composition` branch contains the design I prefer using composition over inhreitance. Reason why I prefer it is in the end of the readme file.

# Features
- Different types of products:
    - simple
    - shippable
    - expirable
    - shippable and expirable
- Add/remove items, manage quantities, Track customer information and balance.
- Calculate shipping fees based on weight, Process orders with shipping calculations and receipt generation
- Check product expiration dates during checkout
- Real-time stock tracking and validation for race conditions

# Design Patterns

showcased factory and adapters design patterns

# OOP

In main branch:
- **Inheritance and Abstraction** :
    - Used abstract BaseProduct
    - all children products Inherited the base and overridden its functionalities.
- **Polymorphism** :
    - Products handled through BaseProduct reference
    - Runtime method resolution based on actual object type
    - Used Interfaces with Shippable and Expirable
- **Encapsulation** :
    - Private fields with public getter/setter methods
    - Protected access for inheritance hierarchy
    - Validation in constructors and methods

# Test Cases

- Invalid data: when creating the products
- Successful Checkout: Normal purchase flow
- Insufficient Stock: Handling out-of-stock scenarios
- Abandoned Cart: Inventory restoration when cart is cleared
- Multiple Users: Concurrent cart operations
- Expired Product: Validation of expiration dates

# Error Handling

The system includes robust error handling for:
- Invalid product parameters
- Insufficient inventory
- Expired products
- Insufficient customer balance
- Invalid cart operations

To run the test cases:
```bash
javac *.java
java main
```

**Note**
I prefer composition for flexibility, maintanability and avoiding complexity.
Also, coming from a .NET background I stick to the principle of `composition over inheritance`.