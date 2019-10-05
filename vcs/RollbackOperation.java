package vcs;

import utils.ErrorCodeManager;
import utils.OperationType;

import java.util.ArrayList;

// Aceasta comanda ne intoarce la versiunea de ultimul commit

public class RollbackOperation extends VcsOperation {
    public RollbackOperation(OperationType type, ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }

    @Override
    public final int execute(Vcs vcs) {
        vcs.setStaging(null);
        vcs.setActiveSnapshot(vcs.getHead().getFileSystemSnapshot());
        return ErrorCodeManager.OK;
    }
}
