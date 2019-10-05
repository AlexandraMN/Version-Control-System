package vcs;

import utils.ErrorCodeManager;
import utils.OperationType;

import java.util.ArrayList;

// Afisam commiturile de pe Branchul curent

public class LogOperation extends VcsOperation {
    public LogOperation(OperationType type, ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }

    @Override
    public final int execute(Vcs vcs) {
        int size = vcs.getCurrentBranch().getBranch().size();
        int last = 1;
        for (Commit commit : vcs.getCurrentBranch().getBranch()) {
            vcs.getOutputWriter().write("Commit id: " + commit.getId() + "\n");
            vcs.getOutputWriter().write("Message: " + commit.getName() + "\n");
            if (size != last) {
                vcs.getOutputWriter().write("\n");
            }
            ++last;
        }

        return ErrorCodeManager.OK;
    }
}
