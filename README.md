# Bells_Java_Project


--- 
# ======= TODO =======
# Lab 2: Gathering the Requirements

Before we embark on a software project, we have to gather the requirements before we can begin.

# 1.0 Gather the Business Requirements

The business requirements of an application are the requirements given by the **project owner** (also known as the **project champion**). Those are the problems that the project owner wants the application be able to solve.

In this lab, we will look at a number of sample business requirements before writing our own.

# 2.0 Sample Business Requirements

Take a look at the two sample business requirements below.

## 2.1 Attendance Taking System

For many years, School XYZ has relied on logbooks where the students sign to take their attendance. The administration has decided to create an Attendance Taking System, with the goal to streamline the process of taking attendance and also to cut down on cases where students would sign for each other.

The system must allow administrators to create classes, update the particular of students attending classes, offer a way to mark attendance and generate the necessary reports.

### Business Objectives

1. Provide a streamlined method for teachers to take class attendance
2. Ensure that absentees cannot create fraudulent attendance records, such as by asking other students to sign for them
3. Reduce administration workload with regards of registering new students and creating classes for a new semester
4. Generate charts and summaries quickly for the purpose of reporting

## Stakeholders

1. Teachers: they must be able to take attendance quickly without hassle and be able to modify them in case of mistakes or updates
2. Administrator: they must be able to generate reports from the taken attendance (such as students with high rate of absenteeism) quickly and able to add new students and classes to the system efficiently.
3. Students: they must be able to take attendance with hassle.

## 2.2 Inventory Management System

ABC Bookshop, having sold books and music DVDs out from their brick and mortar stores, have decided to have an online ecommerce presence. They want to create an Inventory Management System to manage their physical products and digital assets. The system should allow the staff to maintain a catalog of products and search for a product by its name.

### Business Objectives

1.  Maintain a catalog of physical and digital products
2. Able to search for a product by its name
3. Adjust the stock quantity of physical products

### Stakeholders

1. Staff: they should be able to integrate the application into their daily operations
2. Supervisor: the application should cut down on the time needed to enter new products and to update the stock of existing products

# 3.0 Writing your own Business Requirements

As observed from above, a set of Business Requirements include the following three components:

1. A description of the project
2. A description of the business objectives — in other words, what problems should this application solve for the business? Take note that at this stage those are usually stated in a high-level executive summary. The details of those objectives will be fleshed out in the **functional requirements**.
3. A description of the Stakeholders: who are the people using the application, and what would they expect from it?

<aside>
💡 Think of a business requirement that you would like to work on for your practical project. Write out the **project description, business objectives** and **stakeholders**.

</aside>

# 4.0 Writing the Functional Requirements

The functional requirements are a set of detailed requirements that describe the detailed scope of each feature in the application. There are many ways to capture such requirements, however one of the most detailed way is via **use case**.

## 4.1 A Sample Use Case

Let’s examine a sample use case from the **Inventory Management System.** The following use case is for the feature to add a new product to the system. Take note that as the system supports both physical and digital products, we have to make sure the staff performing the task are able to state which kind of product they are adding.

## Use Case: Add a new product

**Actor:** Staff

**Description:** The staff adds a new product to the inventory management system. The product can be either a physical or a digital product.

**Preconditions:**

- The staff is logged into the system.
- The staff has the necessary permissions to add a new product.

**Steps:**

1. The staff navigates to the 'Add New Product' page.
2. The staff enters the product details, which includes:
    1. The name of the product
    2. The SKU of the product
    3. The price of the product
3. The staff selects the type of product: 'Physical' or 'Digital'.
4. If the product is 'Physical', the staff enters the following information:
    1. size
    2. weight
    3. colour
5. If the product is “Digital”, the staff enters the following information:
    1. file format
    2. download link
6. The staff submits the new product information.

**Postconditions:**

- The new product is added to the system.
- The staff can view the details of the new product in the system.

Let’s examine each of the different components of a use case. In a use case, the **Actor** is the person or role who is interacting with the system. The **Description** provides a brief overview of the action being performed. **Preconditions** are conditions that must be met before the action can take place, and **Steps** outline the process the actor goes through to complete the action. Finally, **Postconditions** describe the state of the system after the action is completed.

## 4.2 Writing Use Cases

To start writing use cases, start by identifying **each** feature in the application. You know that you have identified every possible feature when your list features can meet **all the business objectives and concerns for stakeholders.**

After which, for each feature, perform the following steps to write the use case:

1. Identify the actor who will be interacting with the feature.
2. Draft a brief description of the action being performed.
3. List out the preconditions necessary for the action to occur.
4. Outline the steps the actor takes to complete the action.
5. Describe the postconditions, or the state of the system after the action is completed.

Let’s go through each of the following in more detail.

### 4.2.1 Identify the Actor

The actor is the individual or role who will be interacting with the system or performing the action in the use case. This could be a user, a system administrator, or any other role that interacts with the system. The actor is not limited to humans; it could also be a system or software that interacts with the system you are developing.

Most of the time the actor will be one of the **identified** stakeholders. Make sure their concern about a particular feature is met, if any is stated.

<aside>
💡 For example, for the **Attendance Taking System**, the teachers using the system to take attendance wants the process to be a hassle-free, straightforward and time efficient.

</aside>

### 4.2.2 Describe the Action being Performed

The action being performed is the specific task that the actor is trying to accomplish with the system. This could be as simple as entering data or as complex as generating a report. It's important to be precise and clear in describing the action, as this will guide the development of the feature.

If you end up writing long paragraphs for this step, it might be the feature or action is too complex to be considered as **one** feature. See if you can break it down into smaller and more specific features.

### 4.2.3 List out the Preconditions

Preconditions are the conditions that must be met before the action can take place. This could include things like the actor being logged into the system, having the necessary permissions to perform the action, or any other requirements that must be met before the action can be performed. It's important to identify these preconditions as they can have a significant impact on how the feature is designed and implemented.

<aside>
💡 For instance, for the teacher to take attendance using the **Attendance Taking System**, the administrator must have created the class with its list of students beforehand.

</aside>

### 4.2.4 Outline the Steps to Complete the Action

The steps are a detailed, step-by-step process that the actor follows to complete the action. This should be as detailed as possible, outlining every click, every field that needs to be entered, and every decision that needs to be made. It's important to include all the steps, even if some of them seem obvious, as this will help ensure that the feature is designed and implemented correctly.

### 4.2.5 Describe the Post Conditions

Postconditions describe what happens after the actor has completed the action. This includes any changes to the system, such as new data being saved or a report being generated. It also includes any feedback given to the actor, such as a confirmation message or an error message. Defining postconditions ensures that the outcome of using the feature is clearly understood by all stakeholders.

<aside>
💡 Now write the use cases for the application which you want to do for your Practical Project.

</aside>