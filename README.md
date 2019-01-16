# CLI tools
CLI tools provide facilities for the command line interface development.

Command line interface can be defined as a command flow.
The commands in this flow are executed step by step.

The command implementation class should extend the ```AbstractCommand``` class or it's subclasses.
Each command performs some actions and then defines the next command based on this actions.
This is the command flow.

The ```CommandRunner``` class is used to run the command flow.
This class obtains the first command in the flow and executes all the commands in this flow.

Some commands require user interaction, some does not.
For example, the application first collects the required data from the user and then performs some actions based on this data.

Commands, that require the user interaction, should extend the ```AbstractUserActionCommand``` class or it's subclasses.
Some useful subclasses are ```AbstractMenuCommand``` to define the menu, ```AbstractInputCommand``` to obtain the data from the user like strings, numbers etc.

Commands, that does not require the user interaction, should extend the ```AbstractExecutionCommand``` class or it's subclasses.

All the commands in the flow share the same context.
One command in the flow can put the value to the context, another command can use this value to perform some actions.

The command flow can contain nested flows.
Nested flow has the nested context.
All the commands of the nested flow share the same nested context, but nested flow does not affect the parent context.

The nested flow is defined by the class, that extends the ```AbstractContainerCommand```.

# Latest release
CLI tools:
* **&lt;groupId&gt;**: ru.d-shap
* **&lt;artifactId&gt;**: cli
* **&lt;version&gt;**: 1.1

# Donation
If you find my code useful, you can [bye me a coffee](https://www.paypal.me/dshapovalov)
