# Firewall
A simple Firewall , which accepts or blocks the traffic using a simple set of rules.

##Implementation I have used Java for the Implementing the FireWall. As a simplified model of a firewall, programmed with a 
set of predetermined security rules. As network traffic enters and leaves the machine,the firewall rules determine whether the traffic 
should be allowed or blocked. An input from the CSV file has four columns- Direction,protocol, ports, and IP address. 
Using a CSV read class , We read the file and parse the output in the form of packet class. we use getter method to get the direction, 
protocol, port and IP. Have created a FireWall class which implements a firewall interface , the class has a function accept_packet
which returns boolean output. we have an enumeration- ruletype , which has 4 variables of the combination Direction and protocol. 
we createa hashmap where the key is the ruletype and value is List of List of strings. I have used this Data Structure for performance 
efficency. As we store everything from the csv once and check it for sanity.
In the method addPacketsTomap we create initialize a array list for all four variables in ruletype enum. IP and port number is mapped 
to the key(ruletype). We use the accept packet where we get the port number , we use another method to check if the IP is present in the 
haspmap. we use another isvalid to check if the port range is present.

##Testing

I have tested the firewall by passing various input combination and tested all edge cases.
It returns true for all valid inputs and False for all other inputs.

##I am looking forward to work in

Data team - I have prior experience in Data Integration and have worked on various Database DB2, Teradata, Mysql and written several python and Unix scrpits to improve the perfomance. I developed an acute interest on the way data works and handling large scale data.This job profile aligns with my interest and I am eagerly looking forward to be a part of the team.
Platform team.
