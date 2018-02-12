# Elemental Naming
This was the fourth project for my Software Quality Assurance class at the University of Pittsburgh.  The project had a heavy emphasis on performance testing and analysis.  The program will take a string and determine whether it is possible to re-write the string in terms of the abbreviations of elements in the periodic table.  

If it is possible, the program will rewrite the string using the abbreviations as well as the full names of the elements used in the spelling.  The program accepts an input file from which it reads the target strings.

## Test Requirements/Dependencies  
  * `hamcrest-core-1.3.jar`
  * `junit-4.12.jar`  
  **These dependencies are included in the `jars/` directory of this repository.**

## Usage  
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

## Testing  
`./test.sh`  
  This will compile and execute the tests, assuming the dependencies listed above are correctly placed in the `jars/` directory.  
