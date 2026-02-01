# Alpaca4J
Alpaca4J - An ASCOM Alpaca Framework for Java

Alpaca4J provides a framework for implementing 
ASCOM [Alpaca](https://ascom-standards.org/AlpacaDeveloper/Index.htm) drivers and clients. 
Alpaca4J does the heavy lifting in developing drivers or calling them. All the networking,
Alpaca protocol processing, connection management, and error handling is taken care of.

To develop an Alpaca device driver, the developer implements the appropriate device interface 
supplied by the framework. The drivers are run within a Web Services framework. The framework 
is responsible for discovering the driver and making it available on the network through the 
Alpaca device discovery protocol.

This project uses the [Quarkus](https://quarkus.io/) web services framework. Alpaca4J is 
implemented to only use the [MicroProfile](https://microprofile.io/) API standards. There 
are several different implementations of web services that support MicroProfile APIs, so it 
should be possible to port Alpaca4J to another web services framework with minimal changes.

## Sub-Modules

Alpaca4J includes the following submodules to help developers get started with building and 
testing applications:

1. **`alpaca-client-test`**:  
   This module provides a simple implementation of an Alpaca client application. It 
demonstrates how to interact with devices using Alpaca protocols. The `alpaca-client-test` 
module is an excellent starting point for developers who want to build their own Alpaca client 
applications.

2. **`alpaca-server-test`**:  
   This module includes an example of implementing devices using the Alpaca protocol. 
It is designed to help developers get started with building their own Alpaca-compatible device 
drivers. The `alpaca-server-test` module provides a foundational implementation of test 
devices within a server framework.

These submodules are great tools for experimentation and learning. Developers can use them 
as references or templates to accelerate their implementation process.

## Features
- Support for ASCOM Alpaca protocols for device drivers and clients.
- Automatic network service registration and Alpaca device discovery.
- A common `BaseDevice` implementation for shared Alpaca device functionality.
- Framework-neutral design based on [MicroProfile](https://microprofile.io/) standards.
- Optimized for [Quarkus](https://quarkus.io/), with portability to other platforms.
- Example modules (`alpaca-client-test` and `alpaca-server-test`) for getting started with 
client and server development.

## Getting Started
### Prerequisites
Make sure you have the following tools installed:
- Java Development Kit (JDK 17 or higher)
- Maven or Gradle as your build tool
- A MicroProfile-compliant web service framework (e.g., Quarkus for rapid development). This
  is not necessary if only using the client library to interact with Alpaca devices.

---

This section provides an overview of how the `alpaca-client-test` and `alpaca-server-test` 
modules serve as starting points for developers, either for creating clients or implementing 
test devices.