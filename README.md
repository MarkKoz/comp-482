# COMP 333 - Project 1: PreProcessing for ProposeDispose

**Course Title:** Algorithm Design and Analysis<br/>
**Semester:** Fall 2021<br/>

## Overview

The _StableMarriage_ problem receives its input as ranked lists. Every man ranks the women from most to least desired and every woman ranks the men from most to least desirable. Recall that running the _ProposeDispose_ algorithm in the most obvious way using these ranked lists results in an _O(N³)_ algorithm where `N` is the number of men (or women). The reason this occurs is that when an engaged woman receives a proposal, she must linearly scan her list to determine which of the men she prefers. Inclass we showed a preprocessing step that could be done to reduce the time to _O(N²)_. In this project you'll be given the matrix (2-d array) containing the women's rankings and need to perform this preprocessing step.

## Details

The input will come from a file called `input.txt` which will be placed in the same directory as your _java_ file. The first line of the file will have a single value which will be the value of `N`. The next `N` lines will be the preference lists for the `N` women.

Your program will read in this file and perform the steps necessary to create the array where row `i` column `j` tells you how woman `i` ranks man `j`. For example if a woman ranks the men `5 1 3 8 2 7 6 4` then you should modify it to:  man 1 is ranked 2nd, man 2 is ranked 5th, man 3 is ranked 3rd, man 4 is ranked 8th, man 5 is ranked 1st, man 6 is ranked 7th, man 7 is ranked 6th, and man 8 is ranked 4th. So you should create the line `2 5 3 8 1 7 6 4`.

Then you will print this array out to the screen using tabs to seperate items in the same row. See the sample output below.

You can discuss the algorithm to be used with anyone and consult any source (books, internet, etc). However, for projects, you are expected to write the code on your own with limited or no assistance from the professor (using _Project0.java_ is permitted), no assistance from others, and limited or no assistance from other sources (books, internet, etc).

## Required Specifications

Your project must:

* consist of 1 or more dot-java files (no _class_ files, _zip_ files, input files or other files should be submitted). Each file must have your name and which project you are submitting as comments on the first 2 lines.
* not be placed into any package (for the Java pedants, it must be in the default package).
* be designed and formatted reasonably (correct indentation, no excessively long lines, no excessivelylong methods, has useful method/variable names, etc)
* have one file called _Project1.java_.
* compile using the command `javac Project1.java`.
* run using the command `java Project1`.
* accept input from a file called _input.txt_ in the same directory as the _java_ file(s) formatted precisely asdescribed above.
* accomplishes the goal of the project. In other words, the output should be the correct answer, computed in the correct way, formatted correctly.
* be submitted on time (early and multiple times is fine).

For each listed item that you fail to follow, expect to lose at least 5 points

## Sample Execution

<details>
<summary>Sample 1</summary>

If <i>input.txt</i> contains

```
4
1 2 3 4
4 3 2 1
1 3 2 4
3 1 4 2
```

then your program should output

```
1     2     3     4
4     3     2     1
1     3     2     4
2     4     1     3
```
</details>

<details>
<summary>Sample 2</summary>

If <i>input.txt</i> contains

```
6
3 2 5 4 1 6
4 3 5 2 6 1
1 6 2 4 3 5
5 1 4 6 2 3
5 1 4 6 3 2
3 2 1 6 5 4
```

then your program should output

```
5     2     1     4     3     6
6     4     2     1     3     5
1     3     5     4     6     2
2     5     6     3     1     4
2     6     5     3     1     4
3     2     1     6     5     4
```
</details>

<details>
<summary>Sample 3</summary>

If <i>input.txt</i> contains

```
8
6 1 3 2 4 5 8 7
4 3 2 1 8 7 6 5
1 3 5 7 2 4 6 8
1 8 2 7 3 6 4 5
4 5 3 6 2 7 1 8
2 4 6 8 1 3 5 7
8 7 6 5 4 3 2 1
5 1 3 8 2 7 6 4
```

then the output should be

```
2     4     3     5     6     1     8     7
4     3     2     1     8     7     6     5
1     5     2     6     3     7     4     8
1     3     5     7     8     6     4     2
7     5     3     1     2     4     6     8
5     1     6     2     7     3     8     4
8     7     6     5     4     3     2     1
2     5     3     8     1     7     6     4
```
</details>

## Stray Thoughts

I will be using a recent version of Java (likely the current version Java SE 17, but if a new version is released I may upgrade).

You  are  generally  allowed  to  use  the  standard  features,  classes,  methods  in  Java.  For  example,  I  expect nearly all students will sometimes want to use either an array or  `java.util.ArrayList` and the built in sort routine (either for arrays or `ArrayLists`). This is allowed as long as it doesn't violate a project requirement.

You can use as many or as few files as you feel appropriate, but the main method should be located in a file called _Project1.java_. Otherwise the project won't compile/run with the required commands.

Some IDEs default to placing _java_ files into packages. This will likely cause the commands `javac Project1.java` and/or `java Project1` to fail. Either use an IDE that does not place _java_ files into packages OR learn your preferred IDE well enough to avoid this issue OR delete any package lines before submission.

Students often decide to change or modify the format of the input or ouput. Sometimes it makes the project easier for them. Other times a student thinks it is an improved design. You may or may not be right, but don't change the input or output format.

Your project should be written and understood by you. Helping or receiving help from others to figure out what is allowed/required is fine, but copying code is not. Significant shared source code indicates that you either did not write/understand what you submitted or you assisted another in submitting code they did not write or understand.
