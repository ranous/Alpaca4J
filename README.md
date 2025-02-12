# Alpaca4J
Alpaca4J - An ASCOM Alpaca Framework for Java

Alpaca4J provides a framework for implementing ASCOM [Alpaca](https://ascom-standards.org/AlpacaDeveloper/Index.htm)
drivers and clients.  Alpaca4J does the heavy lifting developing drivers or calling them.  All the networking, 
Alpaca protocol processing, connection management, and error handling is taken care of. 

To develop an Alpaca device driver, the developer implements the appropriate device interface supplied by 
the framework.  The drivers are run within a Web Services framework.  The framework is responsible for 
discovering the driver and making it available on the network and the Alpaca device discovery protocol.

This project uses the [Quarkus](<https://quarkus.io/>.) web services framework.  Alpaca4J is implemented 
to only use the [microprofile](https://microprofile.io/) API standards.  There are several different 
implementations of web services that implement the microprofile APIs, so it should be possible to port 
Alpaca4J to another of these web services with minimal changes.
