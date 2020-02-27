# Java Agent Test

A Java agent implements with maven and ByteBuddy. That's prototype was influenced for Koichi Sakata presentation, pass URL here:

> Un Java Agente implementado con Maven y Byte Buddy. Este prototipo ha sido influenciado por la presentación de Koichi Sakata, paso la URL aquí:

https://static.rainfocus.com/oracle/oow18/sess/1525944617498001QSYo/PF/oraclecodeone2018_1540364035637001JNJf.pdf 

That project was a basic prototype for work with Agents. The Agent will be executed on compilation time, modifying java's ByteCodes putting its a Function Hook for know when methods call it, execute it or throw it an exception.

> Este proyecto es un prototipo basíco para trabajar con Agente, este programa se ejecuta en tiempo de compilación, modificando el ByteCode del proyecto Applicacion y añadiendole una función hook para saber cuando se ha ejecutado, finalizado o lanzado una excepción. 

For run this proyect, you must created a agent jar using maven package, and then intregate on java execution using this comand

> Para arrancar este proyecto deberar crear un agente jar usando maven package y luego integrarlo con java usando el siguiente comando:

Default:
`-javaagent:[YOUR_WORKSPACE_PATH]\JavaAgent\agent\target\agent-0.0.1-SNAPSHOT-jar-with-dependencies.jar`

With AgentConfig:
`-javaagent:[YOUR_WORKSPACE_PATH]\JavaAgent\agent\target\agent-0.0.1-SNAPSHOT-jar-with-dependencies.jar=[YOUR_WORKSPACE_PATH]\JavaAgent\Application\src\main\resources\agentConfig.yaml`

Without an agentConfig, the agent will catch all functions of the program and inject the hook. With agentConfig, you can determine the package you like to scan. Following the next structure:

> Sin el agentConfig el agente capturará todas las funciones del programa y les injectará el hook. Con el agentConfig podrás determinar el paquete que tu quieres escanear, siguiente la siguiente estructura:



```
packages:
   - es.raulgf92.application
   - es.raulgf92.agent
```
