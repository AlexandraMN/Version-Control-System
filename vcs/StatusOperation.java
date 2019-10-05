package vcs;

import utils.ErrorCodeManager;
import utils.OperationType;

import java.util.ArrayList;

// Am creat aceasta clasa pentru a afisa ce avem in staging

public class StatusOperation extends VcsOperation {
    public StatusOperation(OperationType type, ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }

    @Override
    public final int execute(Vcs vcs) {
        vcs.getOutputWriter().write("On branch: " + vcs.getCurrentBranch().getName() + "\n");
        vcs.getOutputWriter().write("Staged changes:" + "\n");
        if (vcs.getStaging() != null) {
            for (String string : vcs.getStaging()) {
                vcs.getOutputWriter().write("\t" + string + "\n");
            }
        }
        return ErrorCodeManager.OK;
    }
}
