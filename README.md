Login, Solve, List, Shutdown, and Logout have all been implemented.
Multiple threads can also access the server at the same time and leave whenever.
Message function works from client to server, but not from server back to client.

Build and Run:
Run the server first, then how ever many clients. From there, you can either login or shutdown. If you
try to login with something other than the 4 given codes, the server will tell you to 
provide correct info. If you successfully login to the server, you are then able to use the 
solve, list, message, and logout functions. typing "SOLVE" + either -c or -r followed by a number 
will result in the solve function working. List and list -all also works as specified. MESSAGE followed
by a name or -all, then a message will result in that message being sent to the server (unfortunately
not back to the client). Any new clients that are ran will also work the exact same at anytime when
the server is running. Using the shutdown command in the client will result in that client leaving, but
the server will still run.

Bugs:
When entering values for rectangles and circles, only single digit integers work.
After sending a message to the server, the program will not send message back to client.
Also your assignment requirements on P3 doesn't list MESSAGE as a command you need to implement
(obviously we were supposed to and I tried but couldn't get it to work)- just a heads up to fix


run:
Send command to server:
LOGIN, SOLVE, LIST, MESSAGE, SHUTDOWN, LOGOUT
Some commands may only be used when logged in
list
Server: 300 Invalid Command
Send command to server:
LOGIN, SOLVE, LIST, MESSAGE, SHUTDOWN, LOGOUT
Some commands may only be used when logged in
login sally sally22
Server: SUCCESS
Send command to server:
LOGIN, SOLVE, LIST, MESSAGE, SHUTDOWN, LOGOUT
Some commands may only be used when logged in
SOLVE -r 5 3
Server: SUCCESS: Perimeter = 16.0 Area = 15.0
Send command to server:
LOGIN, SOLVE, LIST, MESSAGE, SHUTDOWN, LOGOUT
Some commands may only be used when logged in
SOLVE -c 9
Server: Success: Circumference = 56.548667764616276 Area = 34.55751918948772
Send command to server:
LOGIN, SOLVE, LIST, MESSAGE, SHUTDOWN, LOGOUT
Some commands may only be used when logged in
logout
Server: 200 Ok
Send command to server:
LOGIN, SOLVE, LIST, MESSAGE, SHUTDOWN, LOGOUT
Some commands may only be used when logged in
login john john2
Server: 300 Invalid Command
Send command to server:
LOGIN, SOLVE, LIST, MESSAGE, SHUTDOWN, LOGOUT
Some commands may only be used when logged in
login john john22
Server: SUCCESS
Send command to server:
LOGIN, SOLVE, LIST, MESSAGE, SHUTDOWN, LOGOUT
Some commands may only be used when logged in
SOLVE -r 8
Server: SUCCESS: Perimeter = 32.0 Area = 64.0
Send command to server:
LOGIN, SOLVE, LIST, MESSAGE, SHUTDOWN, LOGOUT
Some commands may only be used when logged in
LIST
Server: John:

Sides 8 8: Rectangle's perimeter is 32.0 and area is 64.0
Send command to server:
LOGIN, SOLVE, LIST, MESSAGE, SHUTDOWN, LOGOUT
Some commands may only be used when logged in
logout
Server: 200 Ok
Send command to server:
LOGIN, SOLVE, LIST, MESSAGE, SHUTDOWN, LOGOUT
Some commands may only be used when logged in
login sally sally22
Server: SUCCESS
Send command to server:
LOGIN, SOLVE, LIST, MESSAGE, SHUTDOWN, LOGOUT
Some commands may only be used when logged in
list
Server: Sally:

Sides 5 3: Rectangle's perimeter is 16.0 and area is 15.0
Radius 9: Circle's circumference is 56.548667764616276 and area is 34.55751918948772
Send command to server:
LOGIN, SOLVE, LIST, MESSAGE, SHUTDOWN, LOGOUT
Some commands may only be used when logged in
logout
Server: 200 Ok
Send command to server:
LOGIN, SOLVE, LIST, MESSAGE, SHUTDOWN, LOGOUT
Some commands may only be used when logged in
login root root22
Server: SUCCESS
Send command to server:
LOGIN, SOLVE, LIST, MESSAGE, SHUTDOWN, LOGOUT
Some commands may only be used when logged in
list -all
Server: Root:

John:

Sides 8 8: Rectangle's perimeter is 32.0 and area is 64.0
Sally:

Sides 5 3: Rectangle's perimeter is 16.0 and area is 15.0
Radius 9: Circle's circumference is 56.548667764616276 and area is 34.55751918948772
Qiang:

Send command to server:
LOGIN, SOLVE, LIST, MESSAGE, SHUTDOWN, LOGOUT
Some commands may only be used when logged in
MESSAGE -all Hi Everyone!
Server: Message Sent
Send command to server:
LOGIN, SOLVE, LIST, MESSAGE, SHUTDOWN, LOGOUT
Some commands may only be used when logged in
logout
Server: 200 Ok
Send command to server:
LOGIN, SOLVE, LIST, MESSAGE, SHUTDOWN, LOGOUT
Some commands may only be used when logged in
login qiang qiang22
Server: SUCCESS
Send command to server:
LOGIN, SOLVE, LIST, MESSAGE, SHUTDOWN, LOGOUT
Some commands may only be used when logged in
MESSAGE john sup dude
Server: Message Sent
Send command to server:
LOGIN, SOLVE, LIST, MESSAGE, SHUTDOWN, LOGOUT
Some commands may only be used when logged in
shutdown
BUILD SUCCESSFUL (total time: 5 minutes 53 seconds)
