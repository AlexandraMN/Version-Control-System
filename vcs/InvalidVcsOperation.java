package vcs;

import utils.ErrorCodeManager;
import utils.OperationType;

import java.util.ArrayList;

// Verificam daca operatia de vcs este valida

public class InvalidVcsOperation extends VcsOperation {
    public InvalidVcsOperation(OperationType type, ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }

    @Override
    public final int execute(Vcs vcs) {
        return ErrorCodeManager.VCS_BAD_CMD_CODE;
    }
}
