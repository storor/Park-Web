package ua.tolok.projects.project4.model.commands;


/**
 * Reset Session command
 * @author Sergiy Tolok
 */
public class ExitCommand extends Command {

    public String perform() {
        req.getSession().invalidate();
        return INDEX_PAGE;
    }
}
