# Hoover Demo

This is an assignment for RationalData.
<p>Solution was implemented with Quarkus 3.14.2 and Java 17.
<p>It was built on Windows and tested on Windows and Linux

## How to execute
- Download the executable folder
- From the command line go to the downloaded folder where hoover-demo-1.0.0.jar resides
- From the command line run **java -jar hoover-demo-1.0.0.jar**

In order to test the service send an http PUSH request to http://localhost:3267/api/v1/hoover/move with response body a json like:

```
{
    "roomSize": [5, 5],
    "coords": [1, 2],
    "patches": [
        [1, 0],
        [2, 2],
        [2, 3]
    ],
    "instructions": "NNESEESWNWW"
}
```

Using curl you can use the following from linux terminal

> curl --header "Content-Type: application/json"   --request POST   --data '{
> "roomSize": [5, 5],
> "coords": [1, 2],
> "patches": [
> [1, 0],
> [2, 2],
> [2, 3]
> ],
> "instructions": "NNESEESWNWW"
> }'   http://localhost:3267/api/v1/hoover/move

## How to run the tests

To run the tests download the project and from the root folder run the following 
command from the command line

> mvn test


## Assumptions

The following assumptions have been made
- All coordinates are in (X,Y) or (column,row) format. So, first number is the column and the second the row
- The room extends from (0, 0) to (X - 1, Y - 1) where (X,Y) are
the provided dimensions with the "roomSize" parameter
- Directions work like looking on a map, so 
  - North means from bottop to top (increasing Y),
  - South from top to bottom (decreasing Y), 
  - East from left to right and 
  - West from right to left (Increasing X) and

### Validation

The json input must follow the following requirements or an error will be returned
- all coordinates provided as an array of integers should consist of exactly 2 integers
- roomSize must be present and not null
- coords must be present, not null and inside the room
- patches must be present and not null. It can be an empty array though
- instructions must be present and not null. It can be an empty string though. All characters should
be one of N, S, E, W

If the requirements are not met or another error occurs a json containing an error 
code is returned. This is so that a possible frontend could map this error code to
a message supporting internationalization. 

For example this input 
```
{
    "roomSize": [5, 5],
    "coords": [1, 5],
    "patches": [
        [1, 0],
        [2, 2],
        [2, 3]
    ],
    "instructions": "NNESEESWNWW"
}
```

will produce this result
```
{
    "errorCode": "INVALID_INITIAL_POSITION"
}
```
