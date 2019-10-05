package vcs;

import utils.ErrorCodeManager;
import utils.OperationType;

import java.util.ArrayList;
import java.util.List;

// Pentru a implementa aceasta operatie a trebuit sa verific mai intai daca
// este corect argumentul primit
// Erorile apareau fie cand stagingul nu era gol, fie cand primeam un id de commit
// inexistent, fie cand primeam un branch are nu exista
// Altfel ne-am mutat fie de pe un branch pe altul, fie de un commit pe altul

public class CheckoutOperation extends VcsOperation {
    private static final int THREE = 3;

    public CheckoutOperation(OperationType type, ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }

    @Override
    public final int execute(Vcs vcs) {
        int existBranch = 0;
        int existId = 0;

        if (vcs.getStaging() != null) {
            return ErrorCodeManager.VCS_STAGED_OP_CODE;
        }

        if (operationArgs.get(0).startsWith("-c")) {
            int id = Integer.parseInt(operationArgs.get(0).substring(THREE));
            for (Commit commit : vcs.getCurrentBranch().getBranch()) {
                if (id == commit.getId()) {
                    existId = 1;
                    break;
                }
            }
            if (existId == 1) {
                List<Commit> commitList = new ArrayList<>();
                for (Commit commit : vcs.getCurrentBranch().getBranch()) {
                    if (commit.getId() <= id) {
                        commitList.add(commit);
                    }
                }
                vcs.getCurrentBranch().setBranch(commitList);
                int size = vcs.getCurrentBranch().getBranch().size();
                vcs.setHead(vcs.getCurrentBranch().getBranch().get(size - 1));
                vcs.setActiveSnapshot(vcs.getCurrentBranch().getBranch().get(size - 1).
                        getFileSystemSnapshot());
            }
            if (existId == 0) {
                return ErrorCodeManager.VCS_BAD_PATH_CODE;
            }
        } else {
            for (Branch branch : vcs.getBranches()) {
                if (branch.getName().equals(operationArgs.get(0))) {
                    existBranch = 1;
                    vcs.setCurrentBranch(branch);
                    break;
                }
            }
            if (existBranch == 0) {
                return ErrorCodeManager.VCS_BAD_CMD_CODE;
            }
        }

        return ErrorCodeManager.OK;
    }
}
