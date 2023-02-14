# JEScript

Does this count as a transpiler?

Scripting "language" for JE2 that translates to Java that JE2 can run.

The Main goal of JEScript is to just make it faster to script certain functions that are often used in JE2.

Another goal of JEScript is being able to interchangeably use Java and JEScript in the same file.

## Usage:
`# - function call. ex: getAttachedObject() becomes #parent()`

`** - new`

`: - =`

`variable names like Vector3f become vec3, boolean become bool, etc.`

`Transform getters are simplified. ex: getTransform().position() becomes .position`

`Transform setters are simplified. ex: getTransform().setPosition() becomes .position->`

