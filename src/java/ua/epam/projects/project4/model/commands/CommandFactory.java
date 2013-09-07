package ua.epam.projects.project4.model.commands;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Manage which command should be executed
 * @author Sergiy Tolok
 */
public class CommandFactory {
    
    private CommandFactory(){
        
    }
    
    private static CommandFactory commandFactory;
    /**
     * Singleton pattern using to create instanse of CommandFactory class
     */
    public synchronized static CommandFactory createCommandFactory(){
        if(commandFactory==null){
            commandFactory = new CommandFactory();
        }
        return commandFactory;
    }
    
    /**
     * Enum of all commands names
     */
    public enum CommandKey {
        LOGIN,
        REGISTRATION,
        CREATE_USER,
        ADD_PARK,
        CREATE_PARK,
        SHOW_PARK,
        OPEN_CREATE_PLANT,
        CREATE_PLANT,
        ADD_PLANT,
        SHOW_FORESTER_PARK,
        OPEN_CREATE_SERVICE_COMMAND,
        CREATE_SERVICE_COMMAND,
        ADD_ORDER_COMMAND,
        CONFIRM_EXECUTION_COMMAND,
        EXECUTE_ORDER_COMMAND,
        OPEN_MAIN_COMMAND,
        EXIT_COMMAND
    }
    
    protected Map commands = getCommands();
    
    /**
     * Create Command instance which will execute request
     */
    public Command createCommand(HttpServletRequest request, HttpServletResponse response) {
        CommandKey commandKey = getCommandKey(request);
        Class commandClass = (Class) commands.get(commandKey);
        if (commandClass == null) {
            throw new RuntimeException(getClass() + " was unable to find"
                    + "a command named '" + commandKey + "'.");
        }
        
        Command commandInstance = null;
        try {
            commandInstance = (Command) commandClass.newInstance();
            commandInstance.setReqRes(request, response);
        } catch (Exception e) {
        }
        return commandInstance;
    }

    /**
     * Parse request to CommandKey
     */
    protected CommandKey getCommandKey(HttpServletRequest request) {
        Enumeration parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String commandName = parameterNames.nextElement().toString();
            
            if (commandName.equals("login_command")) {
                return CommandKey.LOGIN;
            } else if (commandName.equals("registration_command")) {
                return CommandKey.REGISTRATION;
            } else if (commandName.equals("confirm_registration_command")) {
                return CommandKey.CREATE_USER;
            } else if (commandName.equals("add_park_command")) {
                return CommandKey.ADD_PARK;
            } else if (commandName.equals("create_park_command")) {
                return CommandKey.CREATE_PARK;
            } else if (commandName.equals("show_park_command")) {
                return CommandKey.SHOW_PARK;
            } else if (commandName.equals("open_create_plant_command")) {
                return CommandKey.OPEN_CREATE_PLANT;
            } else if (commandName.equals("create_plant_command")) {
                return CommandKey.CREATE_PLANT;
            }else if (commandName.equals("add_plant_command")) {
                return CommandKey.ADD_PLANT;
            }else if (commandName.equals("show_forester_park_command")) {
                return CommandKey.SHOW_FORESTER_PARK;
            }else if (commandName.equals("open_create_service_command")) {
                return CommandKey.OPEN_CREATE_SERVICE_COMMAND;
            }else if (commandName.equals("create_service_command")) {
                return CommandKey.CREATE_SERVICE_COMMAND;
            }else if (commandName.equals("add_order_command")) {
                return CommandKey.ADD_ORDER_COMMAND;
            }else if (commandName.equals("confirm_execution_command")) {
                return CommandKey.CONFIRM_EXECUTION_COMMAND;
            }else if (commandName.equals("execute_order_command")) {
                return CommandKey.EXECUTE_ORDER_COMMAND;
            }else if (commandName.equals("open_main_command")) {
                return CommandKey.OPEN_MAIN_COMMAND;
            }else if (commandName.equals("exit_command")) {
                return CommandKey.EXIT_COMMAND;
            }
        }
        return null;
    }
    
    /**
     * Fill map of commands
     */
    protected Map getCommands() {
        commands = new HashMap();
        
        commands.put(CommandKey.LOGIN, LoginCommand.class);
        commands.put(CommandKey.REGISTRATION, OpenRegistrationCommand.class);
        commands.put(CommandKey.CREATE_USER, ConfirmRegistrationCommand.class);
        commands.put(CommandKey.ADD_PARK, CreateParkCommand.class);
        commands.put(CommandKey.CREATE_PARK, OpenCreateParkCommand.class);
        commands.put(CommandKey.SHOW_PARK, ShowParkToOwnerCommand.class);
        commands.put(CommandKey.OPEN_CREATE_PLANT, OpenCreatePlantCommand.class);
        commands.put(CommandKey.CREATE_PLANT, CreatePlantInfoCommand.class);
        commands.put(CommandKey.ADD_PLANT, CreatePlantOrderCommand.class);
        commands.put(CommandKey.SHOW_FORESTER_PARK, ShowParkToForesterCommand.class);
        commands.put(CommandKey.OPEN_CREATE_SERVICE_COMMAND, OpenCreateServiceCommand.class);
        commands.put(CommandKey.CREATE_SERVICE_COMMAND, CreateServiceCommand.class);
        commands.put(CommandKey.ADD_ORDER_COMMAND, CreateOrderCommand.class);
        commands.put(CommandKey.CONFIRM_EXECUTION_COMMAND, ConfirmExecutionCommand.class);
        commands.put(CommandKey.EXECUTE_ORDER_COMMAND, ExecuteOrderCommand.class);
        commands.put(CommandKey.OPEN_MAIN_COMMAND, OpenMainCommand.class);  
        commands.put(CommandKey.EXIT_COMMAND, ExitCommand.class);
        return commands;
    }
}
