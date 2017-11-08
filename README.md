# CS1632-Deliverable4  

## Test Requirements/Dependencies  
  * `hamcrest-core-1.3.jar`
  * `junit-4.12.jar`  
  **These dependencies are included in the `jars/` directory of this repository.**

## Instructions  
  Compile : `javac Element.java`  
  Execute : `java Element <input-file>`

#### Sample Output:
```
$ java Element sample_input.txt
Could not create name "John Jacob Jingleheimer Schmidt" out of elements.


La - La - La - La - La - La - La - La - La - La
Lanthanum - Lanthanum - Lanthanum - Lanthanum - Lanthanum - Lanthanum - Lanthanum - Lanthanum - Lanthanum - Lanthanum

La - B - O - O - N
Lanthanum - Boron - Oxygen - Oxygen - Nitrogen

Could not create name "Art Arterson" out of elements.


B - O - B
Boron - Oxygen - Boron
  ```

## Testing Instructions
`./test.sh`  
  This will compile and execute the tests, assuming the dependencies listed above are correctly placed in the `jars/` directory.  
