# Binary Search Tree Visualizer (Java implementation)

This project was created to test your own implementation of a Map implemented with a Binary Search Tree.
Here you will be able to add or remove entries from the Map and see how the tree was built. 
Everything was thought for Integer--Character entries.

----

## Add or remove actions

> ADD: For adding an entry it's needed to input in the text field an integer, a comma and a character, i.e., 10,a.
> REMOVE: For removing an entry it's needed to input in the text field an integer, i.e, 10. 

The remove function will show advice if the map is empty. And, if the integer wasn't in the map, nothing will happen.

----

## Changing implementation

If someone wants to test his own implementation it's needed to change the class BSTMap and adapt BSTMapExtended, BSTNode and check Program class where BSTMapExtended is associated. Maybe the best option is to check everything ;) 

Also, the dummy nodes are represented, it is simple to change, just stop calling the function `drawNode()` when 
`node.getLeft().getKey == null` (and right). Right now it stops when `node.getLeft() == null` (and right).

----

## Limitations

- When the tree is too big (more than five entries inline) it's complicated to see the keys and values.
- The zoom in and out is static.

----

### Contributions

If you do any change or upgrade and it's better, send it sanpas03@gmail.com
**And of course feel free to use it.** 