package vcs;

import utils.ErrorCodeManager;
import utils.OperationType;

import java.util.ArrayList;

// Branch operation adauga noul branch in lista de branchuri din VCS
// impreuna cu toate informatiile despre el

public class BranchOperation extends VcsOperation {
    public BranchOperation(OperationType type, ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }

    @Override
    public final int execute(Vcs vcs) {
        Branch branch = new Branch();
        for (Branch alreadyBranch : vcs.getBranches()) {
            if (alreadyBranch.getName().equals(operationArgs.get(0))) {
                return ErrorCodeManager.VCS_BAD_CMD_CODE;
            }
        }
        branch.setName(operationArgs.get(0));
        vcs.getBranches().add(branch);
        return ErrorCodeManager.OK;
    }
}
