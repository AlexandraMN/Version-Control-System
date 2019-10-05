package vcs;

import utils.ErrorCodeManager;
import utils.IDGenerator;
import utils.OperationType;

import java.util.ArrayList;

// Acesta operatie creeaza un nou commit in care copiem fileSystemSnapshotul
// din vcs

public class CommitOperation extends VcsOperation {
    public CommitOperation(OperationType type, ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }

    @Override
    public final int execute(Vcs vcs) {
        if (vcs.getStaging() == null) {
            return ErrorCodeManager.VCS_BAD_CMD_CODE;
        }
        vcs.setStaging(null);
        Commit commit = new Commit();
        commit.setName(operationArgs.get(0));
        commit.setId(IDGenerator.generateCommitID());
        vcs.getCurrentBranch().getBranch().add(commit);
        vcs.setHead(commit);
        commit.setFileSystemSnapshot(vcs.getActiveSnapshot().cloneFileSystem());
        return ErrorCodeManager.OK;
    }
}
