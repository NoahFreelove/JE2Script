# JEScript

Does this count as a transpiler?

Scripting "language" for JE2 that translates to Java that JE2 can run.

# Goals:
1. To just make it faster to script certain functions that are often used in JE2.

2. Being able to interchangeably use Java and JEScript in the same file. The transpiler will only 
change recognized JEScript code.


## What's Different?
`#` - special JEScript function call. ex: `getAttachedObject()` becomes `#parent()`

`**` can be used interchangeably with `new`

`:` can be used interchangeable with `=`

Variable names like `Vector3f` become `vec3`, `boolean` become `bool`, etc.

Transform getters are simplified. ex: `getTransform().position()` becomes `.position`

Transform setters are simplified. ex: `getTransform().setPosition()` becomes `.position->`

New features like Friend methods: `:) methodName AllowedClass (Var1 var1, Var2 var2){( some code here )};`

or `Friend methodName AllowedClass (Var1 var1, Var2 var2){( some code here )};`