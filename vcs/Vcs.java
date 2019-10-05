package vcs;

import filesystem.FileSystemOperation;
import filesystem.FileSystemSnapshot;
import utils.IDGenerator;
import utils.OutputWriter;
import utils.Visitor;

import java.util.ArrayList;
import java.util.List;

public final class Vcs implements Visitor {
    private final OutputWriter outputWriter;
    private FileSystemSnapshot activeSnapshot;

    // Am creat campuri pentru a retine toate branchurile din VCS,
    // branchul curent, si headul

    private List<Branch> branches;
    private Commit head;
    private Branch currentBranch;

    public Branch getCurrentBranch() {
        return currentBranch;
    }

    public void setCurrentBranch(Branch currentBranch) {
        this.currentBranch = currentBranch;
    }

    public Commit getHead() {
        return head;
    }

    public void setHead(Commit head) {
        this.head = head;
    }

    public List<Branch> getBranches() {
        return branches;
    }

    public void setBranches(List<Branch> branches) {
        this.branches = branches;
    }

    private List<String> staging;

    public List<String> getStaging() {
        return staging;
    }

    public void setStaging(List<String> staging) {
        this.staging = staging;
    }

    /**
     * Vcs constructor.
     *
     * @param outputWriter the output writer
     */
    public Vcs(OutputWriter outputWriter) {
        this.outputWriter = outputWriter;
    }

    /**
     * Does initialisations.
     */
    public void init() {
        this.activeSnapshot = new FileSystemSnapshot(outputWriter);

        //TODO other initialisations

        // Am facut initializarile primului branch si ale primului commit.
        Branch branch = new Branch();
        branch.setName("master");

        Commit commit = new Commit();
        commit.setName("First commit");
        commit.setId(IDGenerator.generateCommitID());
        commit.setFileSystemSnapshot(activeSnapshot.cloneFileSystem());
        setHead(commit);

        List<Branch> branchList = new ArrayList<>();
        branchList.add(branch);
        setBranches(branchList);

        List<Commit> commitList = new ArrayList<>();
        commitList.add(commit);
        branch.setBranch(commitList);
        setCurrentBranch(branch);
    }

    /**
     * Visits a file system operation.
     *
     * @param fileSystemOperation the file system operation
     * @return the return code
     */
    public int visit(FileSystemOperation fileSystemOperation) {
        return fileSystemOperation.execute(this.activeSnapshot);
    }

    /**
     * Visits a vcs operation.
     *
     * @param vcsOperation the vcs operation
     * @return return code
     */
    @Override
    public int visit(VcsOperation vcsOperation) {
        //TODO
        return vcsOperation.execute(this);
    }

    //TODO methods through which vcs operations interact with this

    // AM avut nevoie de urmatorii getteri si setteri in realizarea temei.
    public OutputWriter getOutputWriter() {
        return outputWriter;
    }

    public FileSystemSnapshot getActiveSnapshot() {
        return activeSnapshot;
    }

    public void setActiveSnapshot(FileSystemSnapshot activeSnapshot) {
        this.activeSnapshot = activeSnapshot;
    }

}
