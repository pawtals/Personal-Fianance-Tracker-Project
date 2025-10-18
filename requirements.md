# Data Structures & Algorithms Projects

## Project 1: Personal Finance Tracker

### Description
A tool to log and categorize income and expenses, track financial habits, and generate reports.

### Core Requirements

- **Add/edit/delete** income and expense entries
- **Categorize transactions** (e.g., groceries, entertainment, salary)
- **View summaries** by category or time period
- **Save/load** transaction history (persistence)
- **Sort transactions** by date, category, or amount

### Data Structures

| Data Structure | Purpose |
|----------------|---------|
| **Linked List** | Store transactions in sequence |
| **HashMap** | Map categories to transactions |
| **Stack** | Implement undo functionality |
| **Tree** | Organize transactions by date hierarchy |

### Optional Features

- Budget limits and overspending alerts
- CSV import/export functionality
- GUI interface for better user experience

---

## Project 2: Social Network Simulator

### Description
Simulate a basic social network where users can connect with others, share posts, and explore connections.

### Core Requirements

- **Add/delete users**
- **Add/delete friendships** (edges in graph)
- **View a feed** of friend posts
- **Suggest friends** using graph traversal (BFS/DFS)

### Data Structures

| Data Structure | Purpose |
|----------------|---------|
| **Graph (Adjacency List)** | Store user connections |
| **HashMap** | Store user data and profiles |
| **Queue** | Display feed chronologically |
| **Set** | Friend suggestion logic (avoid duplicates) |

### Optional Features

- GUI with network visualization
- Messaging simulation between users
- Profile search functionality

### Implementation Notes

#### Friend Suggestion Algorithm
- Use **BFS (Breadth-First Search)** or **DFS (Depth-First Search)** for graph traversal
- Suggest friends of friends who are not already connected
- Use Set to prevent duplicate suggestions

#### Feed Display
- Use a queue to display posts in chronological order
- Show posts from direct friends only

#### Graph Representation
- Adjacency list for efficient friend lookups
- Undirected graph (friendship is mutual)

---

## Project 3: Inventory & Order Fulfillment System

### Description
Manage product inventory, accept and prioritize orders, and simulate warehouse processing.

### Core Requirements

- **Add/edit/remove products** from inventory
- **Track stock levels** for all products
- **Accept and process orders** in FIFO or priority order
- **Generate low stock alerts** when inventory is running low

### Data Structures

| Data Structure | Purpose |
|----------------|---------|
| **HashMap** | Fast product lookup by ID/SKU |
| **Queue** | Standard FIFO order processing |
| **Priority Queue/Heap** | Handle urgent/priority orders |
| **Linked List or TreeMap** | Maintain sorted product catalog |

### Optional Features

- Restocking system with supplier management
- Order history tracking and analytics
- Dashboard or GUI interface for warehouse management

### Implementation Notes

#### Order Processing Strategies
- **FIFO Queue**: Process orders in the order they were received
- **Priority Queue**: Process urgent orders first (e.g., express shipping, VIP customers)
- Consider using a Min-Heap for priority-based processing

#### Stock Management
- Trigger alerts when stock falls below a threshold
- Implement automatic reorder suggestions
- Track inventory turnover rates

---

## General Implementation Tips

### Data Structure Selection
- Choose appropriate data structures based on operation frequency
- Consider time complexity for common operations (insert, delete, search)
- Balance memory usage with performance requirements

### Best Practices
- Implement proper error handling and validation
- Write unit tests for core functionality
- Use design patterns (MVC, Observer, etc.) for scalability
- Document your code and API

### Persistence Options
- File-based storage (JSON, CSV, binary)
- Embedded databases (SQLite, H2)
- Serialization/deserialization mechanisms

---

## Project Comparison Matrix

| Feature | Project 1 (Finance) | Project 2 (Social Network) | Project 3 (Inventory) |
|---------|---------------------|----------------------------|------------------------|
| **Complexity** | Medium | Medium-High | Medium |
| **Graph Theory** | No | Yes | No |
| **CRUD Operations** | Yes | Yes | Yes |
| **Real-time Processing** | No | Moderate | Yes |
| **Data Persistence** | High | High | High |
| **Algorithms Focus** | Sorting, Undo | BFS/DFS, Traversal | Priority Queues, Heaps |

---

## Getting Started

1. **Choose a project** based on your interests and learning goals
2. **Design your classes** and interfaces first
3. **Implement core data structures** before adding features
4. **Test incrementally** as you build
5. **Add optional features** after core functionality is complete
